/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Endereco;
import java.sql.PreparedStatement;

/**
 *
 * @author luizf
 */
public class EnderecoDAO {
    private final Connection connection;

    public EnderecoDAO(Connection connection) {
        this.connection = connection;
    }
    
    
    //Inserir novo endereco na tabela Endereco
    public int insertEndereco(String complemento, int numero, int id_logradouro) throws SQLException{
        String sql = "INSERT INTO endereco(numero, complemento, fk_id_logradouro) VALUES(?,?,?)";
        int id_endereco = -1;
        
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, numero);
        statement.setString(2, complemento);
        statement.setInt(3, id_logradouro);
        statement.execute();

        
        ResultSet resultSet = statement.getGeneratedKeys();

        if(resultSet.next()){
            id_endereco = resultSet.getInt("id_endereco");
        }
        
        statement.close();
        
        return id_endereco;
    }
    
    public void deleteEndereco(int id) throws SQLException{
        String sql = "DELETE FROM endereco WHERE id_endereco = ?";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.execute();
        statement.close();
    }
    
    public void updateEndereco(Endereco endereco) throws SQLException{
        String sql = "UPDATE endereco SET numero = ?, complemento = ?, fk_id_logradouro = ? WHERE id_endereco = ?";
        Integer numero = Integer.parseInt(endereco.getNumero());
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, numero);
        statement.setString(2, endereco.getComplemento());
        statement.setInt(3, endereco.getId_logradouro());
        statement.setInt(4, endereco.getId_endereco());
        statement.execute();
        statement.close();
    } 
    
    //Le a tabela de estados no BD      
    
    //Le a tabela de estados no BD
    public ArrayList<Endereco> readEstado() throws SQLException{
        String sql = "SELECT * FROM estados";
        
        //Realiza a conexao
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
        
        ResultSet resultSet = statement.getResultSet(); //Pega resultados
        
        
        //Cria um array de Endereco para armazenar todos os resultados
        ArrayList<Endereco> estados = new ArrayList<>();
        
        //Enquanto tiver resultado ele roda
        while(resultSet.next()){
            String nome = resultSet.getString("nome");
            String uf = resultSet.getString("sigla");
            
            //Novo objeto Endereco
            Endereco estadoEndereco = new Endereco();
            estadoEndereco.setEstado(nome);
            estadoEndereco.setSigla(uf);
            
            estados.add(estadoEndereco);//Envia o objeto pro array de Endereco
        }
        statement.close();
        
        return estados; //Retorna o array
        
    }
    
    //Pega as informações do logradouro pelo seu id
    public Endereco selectLogradouroPorId(int id_logradouro) throws SQLException{
        String sql = "SELECT * FROM logradouros WHERE id_logradouro = ?";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_logradouro);
        statement.execute();
        
        ResultSet resultSet = statement.getResultSet();
        Endereco enderecoComDados = new Endereco();
        
        while(resultSet.next()){
            String cep = resultSet.getString("cep");
            String nome = resultSet.getString("nome");
            String uf = resultSet.getString("uf");
            int id_cidade = resultSet.getInt("fk_id_cidades");
            int id_bairro = resultSet.getInt("fk_id_bairro");
                   
            enderecoComDados.setLogradouro(nome);
            enderecoComDados.setCep(cep);
            enderecoComDados.setSigla(uf);
            enderecoComDados.setId_logradouro(id_logradouro);
            enderecoComDados.setId_cidade(id_cidade);
            enderecoComDados.setId_bairro(id_bairro);
            
        }
        statement.close();
        return enderecoComDados;
    }
    
    //Retorna o cep do endereço pelo seu id
    public String selectCEPPorIdEndereco(int id) throws SQLException{
        int id_logradouro = 0;
        
        String sql = "SELECT * FROM endereco WHERE id_endereco = ?";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.execute();
        
        ResultSet resultSet = statement.getResultSet();
        
        while(resultSet.next()){
           id_logradouro = resultSet.getInt("fk_id_logradouro");//Pega o id_logradouro do endereço cadastrado
        }
        
        Endereco enderecoLogradouro = selectLogradouroPorId(id_logradouro);//Chama a função para pegar os dados do logradouro
        
        return enderecoLogradouro.getCep();//retorna o cep
    }
    
    //Função para retornar o nome da cidade pelo seu id
    public String selectNomeCidadePorId(int id) throws SQLException{
        String cidade = "";
        
        String sql = "SELECT * FROM cidades WHERE id_cidades = ?";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.execute();
        
        ResultSet resultSet = statement.getResultSet();
        
        while(resultSet.next()){
           cidade = resultSet.getString("nome");
        }
        return  cidade;
    }
    
    //Função para retornar o nome do bairro pelo seu id
    public String selectNomeBairroPorId(int id) throws SQLException{
        String bairro = "";
        
        String sql = "SELECT * FROM bairros WHERE id_bairro = ?";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.execute();
        
        ResultSet resultSet = statement.getResultSet();
        
        while(resultSet.next()){
           bairro = resultSet.getString("nome");
        }
        
        return  bairro;
    }
    
    //Função para retornar um endereço completo apenas com seu id
    public Endereco selectEnderecoCompletoPorIdEndereco(int id) throws SQLException{
        //Definição e inicialização de variáveis
        int id_logradouro, id_cidade, id_bairro;
        id_logradouro = id_cidade = id_bairro = 0;
        int numero = 0;
        String complemento = "";
        
        String sql = "SELECT * FROM endereco WHERE id_endereco = ?";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.execute();
        
        ResultSet resultSet = statement.getResultSet();
        
        while(resultSet.next()){
           id_logradouro = resultSet.getInt("fk_id_logradouro");//Pega o id_logradouro do endereço cadastrado
           numero = resultSet.getInt("numero");
           complemento = resultSet.getString("complemento");
        }
        
        Endereco enderecoLogradouro = selectLogradouroPorId(id_logradouro);//Chama a função para pegar os dados do logradouro
        enderecoLogradouro.setCidade(selectNomeCidadePorId(enderecoLogradouro.getId_cidade()));//Chama função para pegar o nome da cidade
        enderecoLogradouro.setBairro(selectNomeBairroPorId(enderecoLogradouro.getId_bairro()));//Chama função para pegar o nome do bairro
        enderecoLogradouro.setComplemento(complemento);
        enderecoLogradouro.setNumero(Integer.toString(numero));
        
        statement.close();
        return enderecoLogradouro;
    }
        
    
    
    //Leitura das cidades -- Não está sendo utilizada!
    public ArrayList<Endereco> readCidade() throws SQLException{
        String sql = "SELECT * FROM cidades";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
        
        ResultSet resultSet = statement.executeQuery();
        
        ArrayList<Endereco> cidades = new ArrayList<>();
        
        while(resultSet.next()){
            String cidade = resultSet.getString("nome");
            
            Endereco cidadeEndereco = new Endereco();
            cidadeEndereco.setCidade(cidade);
            
            cidades.add(cidadeEndereco);
        }
        
        statement.close();
        return cidades;
    }
    
    
    //Leitura das cidades por estados
    public ArrayList<Endereco> readCidadePorEstado(String sigla) throws SQLException{
        String sql = "SELECT * FROM cidades WHERE fk_sigla = ?";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, sigla);
        statement.execute();
        
        ResultSet resultSet = statement.executeQuery();
        
        ArrayList<Endereco> cidades = new ArrayList<>();
        
        while(resultSet.next()){
            String cidade = resultSet.getString("nome");
            
            Endereco cidadeEndereco = new Endereco();
            cidadeEndereco.setCidade(cidade);
            
            cidades.add(cidadeEndereco);
        }
        
        statement.close();
        return cidades;
    }
    
    //Pegar o id da cidade pela sigla do estado e seu nome
    public int pegarIdCidade(String sigla, String nome_cidade) throws SQLException{
        String sql = "SELECT * FROM cidades WHERE fk_sigla = ? AND nome = ?";
        int id_cidade = 0;
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, sigla);
        statement.setString(2, nome_cidade);
        statement.execute();
        
        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next()){
            id_cidade = resultSet.getInt("id_cidades");
        }
        
       return id_cidade;
    }
    
    //Pegar id do logradouro pelo seu CEP
    public int pegerIdLogradouro(String cep) throws SQLException{
        String sql = "SELECT * FROM logradouros WHERE cep = ?";
        int id_logradouro = 0;
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, cep);
        statement.execute();
        
        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next()){
            id_logradouro = resultSet.getInt("id_logradouro");
        }
        
       return id_logradouro;      

    } 
    
    //Pegar id do bairro pelo seu nome
    public int pegarIdBairro(String nome, int id_cidade) throws SQLException{
        String sql = "SELECT * FROM bairros WHERE nome = ? AND fk_id_cidades = ? ";
        int id_bairro = 0;
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, nome);
        statement.setInt(2, id_cidade);
        statement.execute();
        
        ResultSet resultSet = statement.executeQuery();
        
        
        while(resultSet.next()){
            id_bairro = resultSet.getInt("id_bairro");
        }
        
        return id_bairro; 
    }
    
    
    //Ler logradouro pelo id do bairro
    public ArrayList<Endereco> readLogradouroPorBairro(int id_bairro) throws SQLException{
        String sql = "SELECT * FROM logradouros WHERE fk_id_bairro = ?";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_bairro);
        
        ResultSet resultSet = statement.executeQuery();
        
        ArrayList<Endereco> logradouros = new ArrayList<>();
        
        while(resultSet.next()){
            String logradouro = resultSet.getString("nome");
            Endereco logradouroEndereco = new Endereco();
            logradouroEndereco.setLogradouro(logradouro);
            
            logradouros.add(logradouroEndereco);
        }
        statement.close();
        return logradouros;
        
    }
    
    
    //Leitura dos bairros por cidade
    public ArrayList<Endereco> readBairroPorCidade(int id_cidade) throws SQLException{
        String sql = "SELECT * FROM bairros WHERE fk_id_cidades = ?";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_cidade);
        
        ResultSet resultSet = statement.executeQuery();
        
        ArrayList<Endereco> bairros = new ArrayList<>();
        
        while(resultSet.next()){
            String bairro = resultSet.getString("nome");
            Endereco bairroEndereco = new Endereco();
            bairroEndereco.setBairro(bairro);
            
            bairros.add(bairroEndereco);
        }
        statement.close();
        return bairros;
    }
    
    public boolean existeCEP(String cep) throws SQLException{
        String sql = "SELECT * FROM logradouros WHERE cep = ?";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, cep);
        statement.execute();
        
        ResultSet resultSet = statement.getResultSet();
        return resultSet.next();
    }
    
    public void novoLogradouro(Endereco endereco, int id_cidade, int id_bairro) throws SQLException{
        String sql = "INSERT INTO logradouros(cep, nome, uf, fk_id_cidades, fk_id_bairro) VALUES(?,?,?,?,?)";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, endereco.getCep());
        statement.setString(2, endereco.getLogradouro());
        statement.setString(3, endereco.getSigla());
        statement.setInt(4, id_cidade);
        statement.setInt(5, id_bairro);
        statement.execute();
        
        
    }
    
    public boolean existeBairro(String bairro, int id_cidade) throws SQLException{
        String sql = "SELECT * FROM bairros WHERE fk_id_cidades = ? AND nome = ?";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_cidade);
        statement.setString(2, bairro);
        statement.execute();
        
        ResultSet resultSet = statement.getResultSet();
        
        return resultSet.next();
    }
    
    public void novoBairro(String bairro, int id_cidade) throws SQLException{
        String sql = "INSERT INTO bairros(nome,fk_id_cidades) VALUES(?,?)";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, bairro);
        statement.setInt(2, id_cidade);
        statement.execute(); 
        statement.close();
    }
}
