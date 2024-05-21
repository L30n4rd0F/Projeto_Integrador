package controller;

import dao.ClienteDAO;
import dao.Conexao;
import dao.EnderecoDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.Cliente;
import model.Endereco;
import view.CadastroClienteView;
import view.ClientePane;

public class ClienteController extends EnderecoController {

    private CadastroClienteView cadastroView;
    private ClientePane view;

    public ClienteController(CadastroClienteView cadastroView, ClientePane view) {
        this.cadastroView = cadastroView;
        this.view = view;
    }

    public void apagarTodosCampos() {
        apagarCamposCadastroEndereco();
        apagarCamposInformacaoCliente();
    }

    //Apagar campos de informacoes do endereço
    public void apagarCamposCadastroEndereco() {
        cadastroView.getComboBoxBairro().setSelectedIndex(-1);
        cadastroView.getComboBoxCidade().setSelectedIndex(-1);
        cadastroView.getComboBoxEstado().setSelectedIndex(-1);
        cadastroView.getComboBoxUF().setSelectedIndex(-1);
        cadastroView.getComboBoxLogradouro().setSelectedIndex(-1);
        cadastroView.getCampoCep().setText("");
        cadastroView.getCampoComplemento().setText("");
        cadastroView.getCampoNumero().setText("");

    }

    //Apagar campos de informacoes do cliente
    public void apagarCamposInformacaoCliente() {
        cadastroView.getCampoNomeCliente().setText("");
        cadastroView.getCampoCpfCliente().setText("");
        cadastroView.getCampoTelefoneCliente().setText("");
        cadastroView.getCampoObservacaoCliente().setText("");
    }

    //Função para habilitar e desabilitar os campos de endereco
    public void enderecoHabilitado(boolean ativado) {
        cadastroView.getCampoCep().setEnabled(ativado);
        cadastroView.getCampoNumero().setEnabled(ativado);
        cadastroView.getCampoComplemento().setEnabled(ativado);
        cadastroView.getBotaoAtualizarCEP().setEnabled(ativado);
        cadastroView.getComboBoxEstado().setEnabled(ativado);
        cadastroView.getComboBoxUF().setEnabled(ativado);
        cadastroView.getComboBoxCidade().setEnabled(ativado);
        cadastroView.getComboBoxBairro().setEnabled(ativado);
        cadastroView.getComboBoxLogradouro().setEnabled(ativado);
        cadastroView.getBotaoApagarCamposEndereco().setEnabled(ativado);
        
        apagarCamposCadastroEndereco();
    }
    
    @SuppressWarnings("unchecked")//Função para ler os dados do bd e colocar na tabela do cliente
    public void readTabelaCliente() throws SQLException{
        DefaultTableModel modelo = (DefaultTableModel) view.getTabelaCliente().getModel(); //Pega o modelo da tabela 
        modelo.setNumRows(0); //Seta o numero de linhas como 0, isso evita a tabela repetir informções quando atualizada
        view.getTabelaCliente().setRowSorter(new TableRowSorter(modelo)); //Classifica as linha da tabela 
        
        //Realiza a conexao
        Connection conexao = new Conexao().getConnection();
        ClienteDAO clienteDao = new ClienteDAO(conexao);
        
        //loop para preencher as linhas com os dados encontrados na função readCliente
        for(Cliente cliente : clienteDao.readCliente()){
            //Se o cliente tem um id_endereço maior que 0 significa que ele possui um endereço cadastrado
            if(cliente.getId_endereco()>0){
                //Realiza a conexao e a busca pelo cep do logradouro com o id do endereco.
                EnderecoDAO enderecoDao = new EnderecoDAO(conexao);
                cliente.setCep(enderecoDao.selectCEPPorIdEndereco(cliente.getId_endereco()));
            }
            //Adiciona os dados em cada linha e coluna na tabela
            modelo.addRow(new Object[]{
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getTelefone(),
                cliente.getCep()           
            });
        }
    }
    
    //Preenche tudo com o endereco do cep
    public void preencherCamposEndereco(String cep) throws SQLException {
        Endereco endereco = buscarEndereco(cep);

        if (endereco != null) {
            // Define os valores nos campos JTextField com os dados do endereço
            cadastroView.getComboBoxUF().setSelectedItem(endereco.getSigla());
            cadastroView.getComboBoxEstado().setSelectedIndex(cadastroView.getComboBoxUF().getSelectedIndex());
            cadastroView.getComboBoxCidade().setSelectedItem(endereco.getCidade());
            cadastroView.getComboBoxBairro().setSelectedItem(endereco.getBairro());
            cadastroView.getComboBoxLogradouro().setSelectedItem(endereco.getLogradouro());
            cadastroView.getCampoNumero().setText(endereco.getNumero());
        } else {
            // Se o endereço não for encontrado, limpe os campos
            cadastroView.getComboBoxBairro().setSelectedIndex(-1);
            cadastroView.getComboBoxCidade().setSelectedIndex(-1);
            cadastroView.getComboBoxUF().setSelectedIndex(-1);
            cadastroView.getComboBoxEstado().setSelectedIndex(-1);
            cadastroView.getComboBoxLogradouro().setSelectedIndex(-1);
            cadastroView.getCampoNumero().setText("");
        }
    }

    //Pega o que está escrito nos campos de endereco
    public Endereco enderecoDosCamposPreenchidos() throws SQLException {

        String cep = cadastroView.getCampoCep().getText();
        String logradouro = (String) cadastroView.getComboBoxLogradouro().getSelectedItem();
        String cidade = (String) cadastroView.getComboBoxCidade().getSelectedItem();
        String uf = (String) cadastroView.getComboBoxUF().getSelectedItem();
        String bairro = (String) cadastroView.getComboBoxBairro().getSelectedItem();
        String numero = cadastroView.getCampoNumero().getText();
        String complemento = cadastroView.getCampoComplemento().getText();

        Endereco endereco = new Endereco(logradouro, bairro, cidade, cep, numero, uf, complemento);
        return endereco;

    }

    //Função para realização do cadastro do cliente, ela recebe um true or false do Radio Button para habilitar ou não o endereço 
    //Tem todas as verificações para o cadastro
    public void realizarCadastro(boolean ativado) throws SQLException {
        int id_endereco = -1;//id do endereço é setado como -1
        boolean existe, campoEmBranco, cpfValido, telefoneValido;//Váriaveis para validações
        Cliente clienteParaCadastro = informacaoDosCamposPessoais(); //Pega os campos pessoais preenchidos

        //Realiza a conexão
        Connection conexao = new Conexao().getConnection();
        ClienteDAO clienteDao = new ClienteDAO(conexao);

        campoEmBranco = campoNuloDadosPessoais();//Verifica se tem campo em branco
        cpfValido = verificaCPFisValido(clienteParaCadastro.getCpf());//Verifica se o cpf é valido
        existe = clienteDao.existeClientePorCPF(clienteParaCadastro.getCpf());//Verifica se o cliente existe
        telefoneValido = verificaTelefoneValido(clienteParaCadastro.getTelefone());

        //Se algo estiver invalido ele entra
        if (campoEmBranco || !cpfValido || existe || !telefoneValido) {
            avisosErro(campoEmBranco, !cpfValido, existe, !telefoneValido);
        }
        //Caso contrário ele realiza o cadastro do cliente
        else {
            //Se os campos de endereço estejam ativados ele entra
            if (ativado) {
                id_endereco = realizarCadastroEndereco(); //Insere o endereço no banco de dados e pega o seu id

                //Caso o id do endereço for maior do que 0 significa que o cadastro pode continuar
                if (id_endereco >= 0) {
                    realizarCadastroClienteComEndereco(id_endereco, clienteParaCadastro);//Função para o cadastro do cliente com endereço, é necessário enviar o id_endereco

                }
            } //Caso o Radio Button esteja desativo o cadastro será realizado sem endereço
            else {
                realizarCadastroClienteSemEndereco(clienteParaCadastro);//Função para cadastro sem endereço
            }
        }
    }

    //Função para cadastro do cliente com endereço
    public void realizarCadastroClienteComEndereco(int id_endereco, Cliente clienteParaCadastro) throws SQLException {

        //Realiza a conexão
        Connection conexao = new Conexao().getConnection();
        ClienteDAO clienteDao = new ClienteDAO(conexao);

        clienteDao.insertComEndereco(clienteParaCadastro, id_endereco);//Função para inserir usuario com endereço
        JOptionPane.showMessageDialog(null, "Cliente foi cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);//Mensagem de sucesso
        cadastroView.dispose();//Fecha a tela de cadastro
    }

    //Função para cadastro do cliente sem endereço
    public void realizarCadastroClienteSemEndereco(Cliente clienteParaCadastro) throws SQLException {

        //Realiza a conexão
        Connection conexao = new Conexao().getConnection();
        ClienteDAO clienteDao = new ClienteDAO(conexao);

        clienteDao.insert(clienteParaCadastro);//Função para inserir usuario sem endereço
        JOptionPane.showMessageDialog(null, "Cliente foi cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);//menssagem de sucesso
        cadastroView.dispose();//Fecha a tela de cadastro 
    }

    //Função para pegar as informações dos campos pessoais
    public Cliente informacaoDosCamposPessoais() {
        String nome, cpf, telefone, observacao;

        nome = cadastroView.getCampoNomeCliente().getText();
        cpf = cadastroView.getCampoCpfCliente().getText();
        telefone = cadastroView.getCampoTelefoneCliente().getText();
        observacao = cadastroView.getCampoObservacaoCliente().getText();

        Cliente clienteComDados = new Cliente(nome, cpf, telefone, observacao);

        return clienteComDados;
    }

    //Função para realizar o cadastro do endereço -- retorna o id_endereço
    public int realizarCadastroEndereco() throws SQLException {
        int id_endereco;
        Endereco endereco = enderecoDosCamposPreenchidos();//Pega as informações dos campos de endereçp
        id_endereco = cadastroEndereco(endereco, campoNuloEndereco());//Chama a função para cadastrar o endereço e armazena o id retornado

        return id_endereco;
    }

    //Função para preenchar o CEP com informações do endereço
    public void preencherCEP() throws SQLException {

        Endereco endereco = buscarCEP(enderecoDosCamposPreenchidos());//Chama a função para buscar o CEP pelas informções do endereço
        cadastroView.getCampoCep().setText(endereco.getCep());
        cadastroView.getComboBoxBairro().setSelectedItem(endereco.getBairro());
        cadastroView.getCampoNumero().setText(endereco.getNumero());
    }

    //Verifica se tem campo nulo nos campos de identificação do cliente
    public boolean campoNuloDadosPessoais() {
        String nome = cadastroView.getCampoNomeCliente().getText();
        String cpf = cadastroView.getCampoCpfCliente().getText();
        String telefone = cadastroView.getCampoTelefoneCliente().getText();

        return nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty();
    }

    public boolean verificaCPFisValido(String cpf) {
        return cpf.length() == 14;
    }
    
    public boolean verificaTelefoneValido(String telefone){
        return telefone.length()>=13;
    }

    //Verifica se tem campo nulo em endereço -- retorna true or false
    public boolean campoNuloEndereco() {
        String cep = cadastroView.getCampoCep().getText();
        String numero = cadastroView.getCampoNumero().getText();
        String estado = (String) cadastroView.getComboBoxEstado().getSelectedItem();
        String bairro = (String) cadastroView.getComboBoxBairro().getSelectedItem();
        String logradouro = (String) cadastroView.getComboBoxLogradouro().getSelectedItem();

        return cep.isEmpty() || numero.isEmpty() || estado.isEmpty() || bairro.isEmpty() || logradouro.isEmpty();

    }

    //Função para mandar os avisos de erro de acordo com os erros
    public void avisosErro(boolean campoEmBranco, boolean cpfInvalido, boolean clienteExiste, boolean telefoneInvalido) {
        if (campoEmBranco) {
            JOptionPane.showMessageDialog(null, "Campo(s) em branco!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (cpfInvalido) {
            JOptionPane.showMessageDialog(null, "CPF invalido", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (clienteExiste) {
            JOptionPane.showMessageDialog(null, "Usuário já existe!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        else if(telefoneInvalido){
            JOptionPane.showMessageDialog(null, "Telefone está incompleto!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @SuppressWarnings("unchecked")
    public void buscarCliente() throws SQLException{
        view.getCampoPesquisaId().setText(""); //Campo do id vazio
        
        DefaultTableModel modelo = (DefaultTableModel) view.getTabelaCliente().getModel(); //Pega o modelo da tabela 
        modelo.setNumRows(0);
        view.getTabelaCliente().setRowSorter(new TableRowSorter(modelo)); //Classifica as linha da tabela 
    
        //Realiza a conexão
        Connection conexao = new Conexao().getConnection();
        ClienteDAO clienteDao = new ClienteDAO(conexao);
        
        Cliente clientePesquisa =  new Cliente();
        
        clientePesquisa.setNome(view.getCampoPesquisaNome().getText());
        clientePesquisa.setCpf(view.getCampoPesquisaCPF().getText());
        
        for(Cliente cliente : clienteDao.buscarClienteCPFeNome(clientePesquisa)){
            //Se o cliente tem um id_endereço maior que 0 significa que ele possui um endereço cadastrado
            if(cliente.getId_endereco()>0){
                //Realiza a conexao e a busca pelo cep do logradouro com o id do endereco.
                EnderecoDAO enderecoDao = new EnderecoDAO(conexao);
                cliente.setCep(enderecoDao.selectCEPPorIdEndereco(cliente.getId_endereco()));
            }
            
            modelo.addRow(new Object[]{
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getTelefone(),
                cliente.getCep()
            });
        }
        
    
    }

}
