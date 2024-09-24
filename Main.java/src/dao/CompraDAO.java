
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Compra;
import java.sql.ResultSet;

public class CompraDAO {
    private final Connection connection;

    public CompraDAO() throws SQLException {
        this.connection = new Conexao().getConnection();
    }
    
    public void adicionarCarrinhoCompra(float preco, String unidade, int quantidade, int id_historico, int id_produto) throws SQLException{

        String sql = "INSERT INTO compra (preco, unidade, quantidade, fk_id_historico, fk_id_produto) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setFloat(1, preco);
        statement.setString(2, unidade);
        statement.setInt(3, quantidade);
        statement.setInt(4, id_historico);
        statement.setInt(5, id_produto);

        statement.executeUpdate();
    }
    
    public ArrayList<Compra> readCompra(int id_historico) throws SQLException {
        String sql = "SELECT p.nome, c.preco, c.quantidade, c.unidade " +
         "FROM compra c " +
         "JOIN produto p ON c.fk_id_produto = p.id_produto " + 
         "WHERE c.fk_id_historico = ?"; 
             
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_historico);
        statement.executeQuery();
        ResultSet resultSet = statement.getResultSet();

        ArrayList<Compra> historico = new ArrayList<>();

        //busca todos os produtos
        while (resultSet.next()) {

            String nomeProduto = resultSet.getString("nome");
            float preco = resultSet.getFloat("preco");
            int quantidade = resultSet.getInt("quantidade");
            String unidade = resultSet.getString("unidade");
            Compra historicoDados = new Compra(nomeProduto, preco, quantidade, unidade);
            historico.add(historicoDados);//adiciona o produto dentro do array
        }

        resultSet.close();
        statement.close();
        return historico;
    }
}
