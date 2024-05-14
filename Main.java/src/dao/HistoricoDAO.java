package dao;

import java.sql.Connection;
import model.Historico;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.ArrayList;

public class HistoricoDAO {

    private final Connection connection;

    public HistoricoDAO() throws SQLException {
        this.connection = new Conexao().getConnection();
    }

    public int adicionarCarrinhoHistorico(float precoTotal, int id_usuario, Integer id_cliente, String metodoPagamento) throws SQLException, ParseException {
        Historico historico = new Historico(precoTotal, id_usuario, id_cliente);
        // Formatando a data para o formato "dia-mês-ano"
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dataFormatada = dateFormat.format(historico.getData());

        // Formatando o tempo para o formato "hora:minuto:segundo:milissegundo"
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss.SSS");
        String tempoFormatado = timeFormat.format(historico.getTempo());
        String sql;

        if (id_cliente == 0) {
            sql = "INSERT INTO historico (data, tempo, preco_total, fk_id_usuario, metodo_pagamento) VALUES (?, ?, ?, ?, ?)";

        } else {
            sql = "INSERT INTO historico (data, tempo, preco_total, fk_id_usuario, fk_id_cliente, metodo_pagamento) VALUES (?, ?, ?, ?, ?, ?)";
        }

        PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        statement.setString(1, dataFormatada);
        statement.setString(2, tempoFormatado);
        statement.setFloat(3, historico.getPrecoTotal());
        statement.setInt(4, historico.getFk_id_usuario());
        if (id_cliente != 0) {
            statement.setInt(5, historico.getFk_id_cliente());
            statement.setString(6, metodoPagamento);
        } else {
            statement.setString(5, metodoPagamento);
        }

        statement.executeUpdate();

        // Obtendo o ID do histórico inserido
        ResultSet generatedKeys = statement.getGeneratedKeys();
        int id = -1;
        if (generatedKeys.next()) {
            id = generatedKeys.getInt(1);
        }

        // Fechando as conexões e retornando o ID do histórico
        statement.close();
        generatedKeys.close();
        return id;
    }

    public ArrayList<Historico> readHistorico() throws SQLException {
        String sql = "SELECT h.id_historico, h.data, h.tempo, h.preco_total, c.nome AS nome_cliente, u.nome AS nome_usuario, h.metodo_pagamento "
            + "FROM HISTORICO h "
            + "LEFT JOIN cliente c ON h.fk_id_cliente = c.id_cliente "
            + "INNER JOIN usuario u ON h.fk_id_usuario = u.id_usuario";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();

        ArrayList<Historico> historico = new ArrayList<>();

        //busca todos os produtos
        while (resultSet.next()) {

            int id_historico = resultSet.getInt("id_historico");
            String data = resultSet.getString("data");
            String tempo = resultSet.getString("tempo");
            float preco_total = resultSet.getFloat("preco_total");
            String nome_cliente = resultSet.getString("nome_cliente");
            String nome_usuario = resultSet.getString("nome_usuario");
            String metodo_pagamento = resultSet.getString("metodo_pagamento");

            Historico historicoDados = new Historico(id_historico, preco_total, nome_cliente, nome_usuario, data, tempo, metodo_pagamento);

            historico.add(historicoDados);//adiciona o produto dentro do array
        }

        resultSet.close();
        statement.close();
        return historico;
    }

}
