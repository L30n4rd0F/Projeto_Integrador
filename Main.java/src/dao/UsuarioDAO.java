package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Usuario;

public class UsuarioDAO {

    private final Connection connection;
    public int id;
    public String nome;
    public String cpf;
    public String senha;
    public String telefone;
    public boolean admin;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    //Função para inserir usuário
    public void insert(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario(nome, cpf, senha, telefone, admin, observacao) VALUES(?, ?, ?, ?, ?, ?);";

        //Utiliza a conexão recebida e a String de sql para criar um um objeto de PreparedStatement
        PreparedStatement statement = connection.prepareStatement(sql);
        
        //Define os valores dos '?'
        statement.setString(1, usuario.getNome());
        statement.setString(2, usuario.getCpf());
        statement.setString(3, usuario.getSenha());
        statement.setString(4, usuario.getTelefone());
        statement.setBoolean(5, usuario.isAdmin());
        statement.setString(6, usuario.getObservacao());
        
        //Executa e fecha 
        statement.execute();
        statement.close();
    }
    
    public void insertComEndereco(Usuario usuario, int id_endereco) throws SQLException{
        String sql = "INSERT INTO usuario(nome, cpf, senha, telefone, admin, observacao, fk_id_endereco) VALUES(?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, usuario.getNome());
        statement.setString(2, usuario.getCpf());
        statement.setString(3, usuario.getSenha());
        statement.setString(4, usuario.getTelefone());
        statement.setBoolean(5, usuario.isAdmin());
        statement.setString(6, usuario.getObservacao());
        statement.setInt(7, id_endereco);
        statement.execute();
        statement.close();
    }
    
    public void updateEndereco(int id_endereco, int id_usuario) throws SQLException{
        String sql = "UPDATE usuario SET fk_id_endereco = ? WHERE id_usuario = ?";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_endereco);
        statement.setInt(2, id_usuario);
        statement.execute();
        statement.close();
    }

    public void delete(Usuario usuario) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, usuario.getId());
        statement.execute();
        statement.close();
    }
    
    public void deleteEndereco(int id_usuario) throws SQLException{
        String sql = "UPDATE usuario SET fk_id_endereco = null WHERE id_usuario = ?";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_usuario);
        statement.execute();
        statement.close();
    }
    
    //Verifica se o usuário possui um endereço
    public boolean possuiEndereco(int id) throws SQLException{
        int id_endereco = 0;
        String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.execute();
        
        
        ResultSet resultSet = statement.getResultSet();
        
        while(resultSet.next()){
            id_endereco = resultSet.getInt("fk_id_endereco"); 
            
        }
        return id_endereco>0;
    }
    
    //Retorna o id do endereco com base no id do usuário
    public int selectIdEndereco(int id_usuario) throws SQLException{
        int id_endereco = 0;
        String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_usuario);
        statement.execute();
        
        ResultSet resultSet = statement.getResultSet();
        
        while(resultSet.next()){
            id_endereco = resultSet.getInt("fk_id_endereco");
        }
        return id_endereco;
    }

    public void update(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario SET nome = ?, cpf = ?, telefone = ?, admin = ?, observacao = ? WHERE id_usuario = ?";

        PreparedStatement statement = connection.prepareStatement(sql);

        //Evita SQL Injection 
        statement.setString(1, usuario.getNome());
        statement.setString(2, usuario.getCpf());
        statement.setString(3, usuario.getTelefone());
        statement.setBoolean(4, usuario.isAdmin());
        statement.setString(5, usuario.getObservacao());
        statement.setInt(6, usuario.getId());

        statement.execute();
        statement.close();
    }
    
    public void updateSenha(int id_usuario, String senha) throws SQLException{
        String sql = "UPDATE usuario SET senha = ? WHERE id_usuario = ?";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        
        statement.setString(1, senha);
        statement.setInt(2, id_usuario);
        statement.execute();
        statement.close();  
    }

    public boolean verificaLoginPorCPFeSenha(Usuario usuario) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE cpf = ? AND senha = ? ";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, usuario.getCpf());
        statement.setString(2, usuario.getSenha());
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        return resultSet.next();
    }

    public boolean verificaExistencia(Usuario usuario) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE cpf = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, usuario.getCpf());
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        return resultSet.next();
    }

    public boolean verificaAdmin(Usuario usuario) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE cpf = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, usuario.getCpf());
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        while (resultSet.next()) {
            admin = resultSet.getBoolean("admin");
        }
        return admin;
    }

    //Leitura de todos os usuarios
    public ArrayList<Usuario> readUsuario() throws SQLException {

        String sql = "SELECT * FROM usuario";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();

        ArrayList<Usuario> usuarios = new ArrayList<>();//Array da várivael usuario

        //Enquanto tiver resultado do banco de dados ele continia
        while (resultSet.next()) {

            id = resultSet.getInt("id_usuario");
            nome = resultSet.getString("nome");
            cpf = resultSet.getString("cpf");
            telefone = resultSet.getString("telefone");
            admin = resultSet.getBoolean("admin");

            Usuario usuarioComDadosDoBanco = new Usuario(id, nome, cpf, telefone, admin);//Pega os dados do Banco de dados e envia para um usuario

            usuarios.add(usuarioComDadosDoBanco);//adiciona o usuario dentro do array
        }
        
        return usuarios;
    }

    public ArrayList<Usuario> buscarUsuarioNOMEeCPF(Usuario usuario) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE nome LIKE ? and cpf LIKE ?";

        // Conexao com o bd
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "%" + usuario.getNome() + "%");
        statement.setString(2, "%" + usuario.getCpf() + "%");
        statement.execute();

        // Executando a consulta
        ResultSet resultSet = statement.executeQuery();

        ArrayList<Usuario> usuarios = new ArrayList<>();

        // Iterando sobre os resultados
        while (resultSet.next()) {
            int idUsuario = resultSet.getInt("id_usuario");
            String nomeUsuario = resultSet.getString("nome");
            String cpfUsuario = resultSet.getString("cpf");
            String telefoneUsuario = resultSet.getString("telefone");
            boolean adminUsuario = resultSet.getBoolean("admin");

            Usuario usuarioPesquisado = new Usuario(idUsuario, nomeUsuario, cpfUsuario, telefoneUsuario, adminUsuario);
            usuarios.add(usuarioPesquisado);
        }

        return usuarios;
    }

    public ArrayList<Usuario> buscarUsuarioNOMEeCPFeADM(Usuario usuario) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE admin = ? and nome LIKE ? and cpf LIKE ?";

        //Conexao com o bd
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setBoolean(1, usuario.isAdmin());
        statement.setString(2, "%" + usuario.getNome() + "%");
        statement.setString(3, "%" + usuario.getCpf() + "%");
        statement.execute();

        ResultSet resultSet = statement.executeQuery();

        ArrayList<Usuario> usuarios = new ArrayList<>();

        // Iterando sobre os resultados
        while (resultSet.next()) {
            int idUsuario = resultSet.getInt("id_usuario");
            String nomeUsuario = resultSet.getString("nome");
            String cpfUsuario = resultSet.getString("cpf");
            String telefoneUsuario = resultSet.getString("telefone");
            boolean adminUsuario = resultSet.getBoolean("admin");

            Usuario usuarioPesquisado = new Usuario(idUsuario, nomeUsuario, cpfUsuario, telefoneUsuario, adminUsuario);
            usuarios.add(usuarioPesquisado);
        }
        return usuarios;

    }

    public int buscarIdUsuarioCPF(String cpf) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE cpf = ?";

        // Conexao com o bd
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, cpf);

        // Executando a consulta
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt("id_usuario");
        } else {
            return -1;
        }
    }
    
    public Usuario selectUsuarioPorId(int id) throws SQLException{
        String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
        
        PreparedStatement statement = connection.prepareCall(sql);
        statement.setInt(1, id);
        
        ResultSet resultSet = statement.executeQuery();
       
        Usuario usuarioComDados = null;
        
        while(resultSet.next()){
            String cpf = resultSet.getString("cpf");
            String nome = resultSet.getString("nome");
            String telefone = resultSet.getString("telefone");
            String observacao = resultSet.getString("observacao");
            boolean adm = resultSet.getBoolean("admin");
            int id_endereco = resultSet.getInt("fk_id_endereco");
            
             usuarioComDados = new Usuario(id, nome, cpf, telefone, adm, observacao);
             usuarioComDados.setId_endereco(id_endereco);       
        }
         return usuarioComDados;
    }
}
