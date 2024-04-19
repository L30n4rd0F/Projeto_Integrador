package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Produto;

public class ProdutoDAO {

    private final Connection connection;

    public ProdutoDAO() throws SQLException {
        this.connection = new Conexao().getConnection(); // Obtém a conexão corretamente
    }

    public ArrayList<Produto> readProduto() throws SQLException {

        String sql = "select * from produtos";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();

        ArrayList<Produto> produtos = new ArrayList<>();

        //busca todos os produtos
        while (resultSet.next()) {

            int id_produto = resultSet.getInt("id_produto");
            String nome = resultSet.getString("nome");
            String categoria = resultSet.getString("fk_nome_categoria");
            String descricao = resultSet.getString("descricao");
            int quantidade = resultSet.getInt("quantidade");
            String unidade = resultSet.getString("unidade");
            float preco = resultSet.getFloat("preco");

            Produto produtoComDadosDoBanco = new Produto(id_produto, nome, categoria, descricao, quantidade, unidade, preco);

            produtos.add(produtoComDadosDoBanco);//adiciona o produto dentro do array
        }

        resultSet.close();
        statement.close();
        return produtos;
    }

    public ArrayList<Produto> buscarProduto(String nomeProduto) throws SQLException {
        String sql = "select * from produtos where lower(nome) like ?";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, "%" + nomeProduto.toLowerCase() + "%");
        ResultSet resultSet = statement.executeQuery();
        // Cria uma lisa com varios produtos
        ArrayList<Produto> produtos = new ArrayList<>();

        // Itera sobre os resultados da consulta e adiciona os produtos a lista
        while (resultSet.next()) {
            String nome = resultSet.getString("nome");
            String descricao = resultSet.getString("descricao");
            float preco = resultSet.getFloat("preco");
            String unidade = resultSet.getString("unidade");
            int quantidade = resultSet.getInt("quantidade");
            String categoria = resultSet.getString("fk_nome_categoria");

            // Cria o produto com base nas váriaveis 
            Produto produto = new Produto(nome, categoria, descricao, quantidade, unidade, preco);
            // Adiciona o produto criado na lista produto
            produtos.add(produto);
        }

        // Fecha as conexões com o banco de dados
        resultSet.close();
        statement.close();

        return produtos;
    }

    public void aumentarQuantidade(int quantidade, String nome) throws SQLException {
        String sql = "update produtos set quantidade = quantidade + ? where nome = ?";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, quantidade);
        statement.setString(2, nome);

        statement.executeUpdate();
        statement.close();
    }

    public void diminuirQuantidade(int quantidade, String nome) throws SQLException {
        String sql = "update produtos set quantidade = quantidade - ? where nome = ?";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, quantidade);
        statement.setString(2, nome);

        statement.executeUpdate();
        statement.close();
    }
    
    public ArrayList<Produto> buscarProdutoPorCategoria(String nomeProduto) throws SQLException {
        String sql = "select * from produtos where lower(fk_nome_categoria) like ?";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, "%" + nomeProduto.toLowerCase() + "%");
        ResultSet resultSet = statement.executeQuery();
        // Cria uma lisa com varios produtos
        ArrayList<Produto> produtos = new ArrayList<>();

        // Itera sobre os resultados da consulta e adiciona os produtos a lista
        while (resultSet.next()) {
            String nome = resultSet.getString("nome");
            String descricao = resultSet.getString("descricao");
            float preco = resultSet.getFloat("preco");
            String unidade = resultSet.getString("unidade");
            int quantidade = resultSet.getInt("quantidade");
            String categoria = resultSet.getString("fk_nome_categoria");

            // Cria o produto com base nas váriaveis 
            Produto produto = new Produto(nome, categoria, descricao, quantidade, unidade, preco);
            // Adiciona o produto criado na lista produto
            produtos.add(produto);
        }

        // Fecha as conexões com o banco de dados
        resultSet.close();
        statement.close();

        return produtos;
    }
    
    public List<String> readCategorias() throws SQLException{
        String sql = "SELECT nome_categoria FROM categoria";

        PreparedStatement statement = connection.prepareStatement(sql);
        
        ResultSet resultSet = statement.executeQuery();
        
        List<String> categorias = new ArrayList<>();

        while (resultSet.next()) {
            String categoria = resultSet.getString("nome_categoria");

            categorias.add(categoria);
        }
        resultSet.close();
        statement.close();

        return categorias;
    }
    
    public void cadastrarProduto(String nomeProduto,String nomeCategoria,int quantidade,String unidade,float preco,String descricao) throws SQLException {
        String sql = "INSERT INTO produtos (nome, descricao, preco, unidade, quantidade, fk_nome_categoria) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, nomeProduto);
        statement.setString(2, descricao);
        statement.setFloat(3, preco);
        statement.setString(4, unidade);
        statement.setInt(5, quantidade);
        statement.setString(6, nomeCategoria);
        
        statement.executeQuery();
    }
    

}
