package controller;

import dao.ClienteDAO;
import dao.Conexao;
import dao.EnderecoDAO;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.Cliente;
import model.Endereco;
import view.AtualizarClienteView;
import view.CadastroClienteView;
import view.ClientePane;

public class ClienteController extends EnderecoController {

    private AtualizarClienteView atualizarView;
    private CadastroClienteView cadastroView;
    private ClientePane view;

    public ClientePane getView() {
        return view;
    }

    public void setView(ClientePane view) {
        this.view = view;
    }

    public ClienteController(CadastroClienteView cadastroView) {
        this.cadastroView = cadastroView;
    }
    
    public ClienteController(AtualizarClienteView atualizarView, CadastroClienteView cadastroView, ClientePane view) {
        this.atualizarView = atualizarView;
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
    public void readTabelaCliente(JTable tabela) throws SQLException{
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel(); //Pega o modelo da tabela 
        modelo.setNumRows(0); //Seta o numero de linhas como 0, isso evita a tabela repetir informções quando atualizada
        tabela.setRowSorter(new TableRowSorter(modelo)); //Classifica as linha da tabela 
        
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
        boolean existe, campoEmBranco, cpfValido, telefoneValido, cepValido = true;//Váriaveis para validações
        Cliente clienteParaCadastro = informacaoDosCamposPessoais(); //Pega os campos pessoais preenchidos

        //Realiza a conexão
        Connection conexao = new Conexao().getConnection();
        ClienteDAO clienteDao = new ClienteDAO(conexao);

        campoEmBranco = campoNuloDadosPessoais();//Verifica se tem campo em branco
        cpfValido = verificaCPFisValido(clienteParaCadastro.getCpf());//Verifica se o cpf é valido
        existe = clienteDao.existeClientePorCPF(clienteParaCadastro.getCpf());//Verifica se o cliente existe
        telefoneValido = verificaTelefoneValido(clienteParaCadastro.getTelefone());
        if(ativado) cepValido = verificaCEPisValido(cadastroView.getCampoCep().getText());
        

        //Se algo estiver invalido ele entra
        if (campoEmBranco || !cpfValido || existe || !telefoneValido || !cepValido) {
            avisosErro(campoEmBranco, !cpfValido, existe, !telefoneValido, !cepValido);
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
        return telefone.length()==15;
    }
    
    public boolean verificaCEPisValido(String cep){
        return cep.length()==9;
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
    public void avisosErro(boolean campoEmBranco, boolean cpfInvalido, boolean clienteExiste, boolean telefoneInvalido, boolean cepInvalido) {
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
        else if(cepInvalido){
            JOptionPane.showMessageDialog(null, "CEP invalido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @SuppressWarnings("unchecked")
    public void buscarCliente(JTextField nome, JTextField cpf, JTable tabela) throws SQLException{
        if(view!=null){
            view.getCampoPesquisaId().setText(""); //Campo do id vazio
        }
        
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel(); //Pega o modelo da tabela 
        modelo.setNumRows(0);
        tabela.setRowSorter(new TableRowSorter(modelo)); //Classifica as linha da tabela 
    
        //Realiza a conexão
        Connection conexao = new Conexao().getConnection();
        ClienteDAO clienteDao = new ClienteDAO(conexao);
        
        Cliente clientePesquisa =  new Cliente();
        
        clientePesquisa.setNome(nome.getText());
        clientePesquisa.setCpf(cpf.getText());
        
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
    
    public void limparCamposPesquisa(){
        view.getCampoPesquisaId().setText("");
        view.getCampoPesquisaCPF().setText("");
        view.getCampoPesquisaNome().setText("");
    }
    
    
    public void preencherInfoClienteAtualizar() throws SQLException{
        int id_cliente = (int) view.getTabelaCliente().getValueAt(view.getTabelaCliente().getSelectedRow(), 0);//Pega o id do campo de texto e transforma em int

         //Realiza a conexão
        Connection conexao = new Conexao().getConnection();
        ClienteDAO clienteDao = new ClienteDAO(conexao);
        
        Cliente clienteInfo = clienteDao.selectClientePorID(id_cliente);
        
        preencherCamposInfoCliente(clienteInfo);
        
        if(clienteInfo.getId_endereco()>0){
            EnderecoDAO enderecoDao = new EnderecoDAO(conexao);
            Endereco enderecoCliente = enderecoDao.selectEnderecoCompletoPorIdEndereco(clienteInfo.getId_endereco());
            preencherCamposEnderecoCliente(enderecoCliente);
            
            clienteInfo.setCep(enderecoCliente.getCep());
            clienteInfo.setUf(enderecoCliente.getUf());
            clienteInfo.setCidade(enderecoCliente.getCidade());
            clienteInfo.setBairro(enderecoCliente.getBairro());
            clienteInfo.setLogradouro(enderecoCliente.getLogradouro());
            clienteInfo.setNumero(enderecoCliente.getNumero());
            clienteInfo.setComplemento(enderecoCliente.getComplemento());
        }
        habilitarEnderecoAtualizar(clienteInfo.getId_endereco()>0);
        atualizarView.setCliente(clienteInfo);
    }
    
    public void habilitarEnderecoAtualizar(boolean habilitar){
        atualizarView.getBtPesquisarEndereco().setEnabled(habilitar);
        atualizarView.getTxCEP().setEnabled(habilitar);
        atualizarView.getCbEstado().setEnabled(habilitar);
        atualizarView.getCbUF().setEnabled(habilitar);
        atualizarView.getCbCidade().setEnabled(habilitar);
        atualizarView.getCbBairro().setEnabled(habilitar);
        atualizarView.getCbLogradouro().setEnabled(habilitar);
        atualizarView.getTxNumero().setEnabled(habilitar);
        atualizarView.getTxComplemento().setEnabled(habilitar);
    }
    
    public void preencherCamposInfoCliente(Cliente clienteInfo){
        atualizarView.getTxNome().setText(clienteInfo.getNome());
        atualizarView.getTxCPF().setText(clienteInfo.getCpf());
        atualizarView.getTxTelefone().setText(clienteInfo.getTelefone());
        atualizarView.getTxaObservacao().setText(clienteInfo.getObservacao());
    }
    
    public void preencherCamposEnderecoCliente(Endereco endereco){
        atualizarView.getCheckEndereco().setSelected(true);
        atualizarView.getTxCEP().setText(endereco.getCep());
        atualizarView.getCbUF().setSelectedItem(endereco.getUf());
        atualizarView.getCbCidade().setSelectedItem(endereco.getCidade());
        atualizarView.getCbBairro().setSelectedItem(endereco.getBairro());
        atualizarView.getCbLogradouro().setSelectedItem(endereco.getLogradouro());
        atualizarView.getTxNumero().setText(endereco.getNumero());
        atualizarView.getTxComplemento().setText(endereco.getComplemento());
    }
    
    public void apagarCamposAtualizar(){
        atualizarView.getTxNome().setText("");
        atualizarView.getTxCPF().setText("");
        atualizarView.getTxTelefone().setText("");
        atualizarView.getTxaObservacao().setText("");
        apagarCamposEnderecoAtualizar();
    }
    
    public void apagarCamposEnderecoAtualizar(){
        atualizarView.getCheckEndereco().setSelected(false);
        atualizarView.getTxCEP().setText("");
        atualizarView.getCbEstado().setSelectedIndex(-1);
        atualizarView.getCbUF().setSelectedIndex(-1);
        atualizarView.getCbCidade().setSelectedIndex(-1);
        atualizarView.getCbBairro().setSelectedIndex(-1);
        atualizarView.getCbLogradouro().setSelectedIndex(-1);
        atualizarView.getTxNumero().setText("");
        atualizarView.getTxComplemento().setText("");
    }
    
    public void desfazerAlteracao(){
        atualizarView.getTxNome().setText(atualizarView.getCliente().getNome());
        atualizarView.getTxCPF().setText(atualizarView.getCliente().getCpf());
        atualizarView.getTxTelefone().setText(atualizarView.getCliente().getTelefone());
        atualizarView.getTxaObservacao().setText(atualizarView.getCliente().getObservacao());
        atualizarView.getCheckEndereco().setSelected(atualizarView.getCliente().getId_endereco()>0);
        habilitarEnderecoAtualizar(atualizarView.getCliente().getId_endereco()>0);
        if(atualizarView.getCliente().getId_endereco()>0){
            atualizarView.getTxCEP().setText(atualizarView.getCliente().getCep());
            atualizarView.getCbUF().setSelectedItem(atualizarView.getCliente().getUf());
            atualizarView.getCbCidade().setSelectedItem(atualizarView.getCliente().getCidade());
            atualizarView.getCbBairro().setSelectedItem(atualizarView.getCliente().getBairro());
            atualizarView.getCbLogradouro().setSelectedItem(atualizarView.getCliente().getLogradouro());
            atualizarView.getTxNumero().setText(atualizarView.getCliente().getNumero());
            atualizarView.getTxComplemento().setText(atualizarView.getCliente().getComplemento());
        }else{
            apagarCamposEnderecoAtualizar();
        }
    }
    
    public boolean campoNuloAtualizar(){
        String telefone = atualizarView.getTxTelefone().getText();
        boolean enderecoNulo = false;
        
        if(atualizarView.getCheckEndereco().isSelected()){
            String cep = atualizarView.getTxCEP().getText();
            String estado = (String) atualizarView.getCbEstado().getSelectedItem();
            String uf = (String) atualizarView.getCbUF().getSelectedItem();
            String cidade = (String) atualizarView.getCbCidade().getSelectedItem();
            String bairro = (String) atualizarView.getCbBairro().getSelectedItem();
            String logradouro = (String) atualizarView.getCbLogradouro().getSelectedItem();
            String numero = atualizarView.getTxNumero().getText();
        
            enderecoNulo = cep.isEmpty() || estado.isEmpty() || uf.isEmpty() || cidade.isEmpty() || bairro.isEmpty() || logradouro.isEmpty() || numero.isEmpty();
        }
        return telefone.isEmpty() || enderecoNulo;
    
    }
    
    public void salvarAlteracao(Cliente cliente) throws SQLException{
        boolean jaPossuiEndereco, enderecoHabilitado, campoEmBranco, telefoneValido, cepValido = true;
        
        //Verificações
        jaPossuiEndereco = atualizarView.getCliente().getId_endereco()>0;
        enderecoHabilitado = atualizarView.getCheckEndereco().isSelected();
        campoEmBranco = campoNuloAtualizar();
        telefoneValido = verificaTelefoneValido(atualizarView.getTxTelefone().getText());
        
        if(atualizarView.getCheckEndereco().isSelected()) cepValido = verificaCEPisValido(atualizarView.getTxCEP().getText());
        
        //Se algum campo está fora das conformidades ele entra 
        if(campoEmBranco || !telefoneValido || !cepValido){
            avisosErro(campoEmBranco, false, false, !telefoneValido, !cepValido);
        }
        else{
            realizarUpdate(enderecoHabilitado, cliente);
            readTabelaCliente(view.getTabelaCliente());
            JOptionPane.showMessageDialog(null, "Informações modificadas", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
            atualizarView.dispose();
        }
    }
    
    public void realizarUpdate(Boolean enderecoHabilitado, Cliente clienteComInfoDesatualizadas) throws SQLException{
        
        //Realizar Conexão
        Connection conexao = new Conexao().getConnection();
        ClienteDAO clienteDao = new ClienteDAO(conexao);
        
        Cliente clienteAtualizado = informacaoDosCamposPessoaisCliente();
        clienteAtualizado.setId(clienteComInfoDesatualizadas.getId());
        clienteDao.update(clienteAtualizado);
        
        //Se ele já tem um endereço e teraEndereco = false;
        if(clienteComInfoDesatualizadas.getId_endereco()>0 && !enderecoHabilitado){
            clienteDao.deleteEndereco(clienteComInfoDesatualizadas.getId());//Desvincula o endereco do cliente
            removerEnderecoComId(clienteComInfoDesatualizadas.getId_endereco());//Remover o endereco do banco de dados
        }else if(clienteComInfoDesatualizadas.getId_endereco()==0 && enderecoHabilitado){//Se ele não possui um endereco cadastrado e teraEndereco = true;
           Endereco endereco = informacaoDosCamposEnderecoAtualizar();//Pega as informações do endereco dos campos
           int id_endereco = cadastroEndereco(endereco, false);//Inserir novo endereço no banco de dados
           clienteDao.updateEndereco(id_endereco, clienteComInfoDesatualizadas.getId());//Vincular o usuario com o endereço
        } else if(enderecoHabilitado){//Caso contrário apenas realizar o update
            Endereco endereco = informacaoDosCamposEnderecoAtualizar();//Pega as informações do endereco dos campos
            endereco.setId_endereco(clienteComInfoDesatualizadas.getId_endereco());
            atualizarEndereco(endereco);
        }   
    }
    
    public Endereco informacaoDosCamposEnderecoAtualizar(){
        String cep = atualizarView.getTxCEP().getText();
        String uf = (String) atualizarView.getCbUF().getSelectedItem();
        String cidade = (String) atualizarView.getCbCidade().getSelectedItem();;
        String bairro = (String) atualizarView.getCbBairro().getSelectedItem();;
        String logradouro = (String) atualizarView.getCbLogradouro().getSelectedItem();;
        String numero = atualizarView.getTxNumero().getText();
        String complemento = atualizarView.getTxComplemento().getText();
        
        Endereco endereco = new Endereco(logradouro, bairro, cidade, cep, numero, uf, complemento);
        return endereco;
    }
    
    public Cliente informacaoDosCamposPessoaisCliente(){
        String nome = atualizarView.getTxNome().getText();
        String cpf = atualizarView.getTxCPF().getText();
        String telefone = atualizarView.getTxTelefone().getText();
        String observacao = atualizarView.getTxaObservacao().getText();
        Cliente cliente = new Cliente(nome, cpf, telefone, observacao);
        return cliente;   
    }
    
    public void preencherCamposEnderecoAtualizar(String cep) throws SQLException{
        Endereco endereco = buscarEndereco(cep);
        
        if (endereco != null) {
            // Define os valores nos campos JTextField com os dados do endereço
            atualizarView.getCbUF().setSelectedItem(endereco.getSigla());
            atualizarView.getCbEstado().setSelectedIndex(atualizarView.getCbUF().getSelectedIndex());
            atualizarView.getCbCidade().setSelectedItem(endereco.getCidade());
            atualizarView.getCbBairro().setSelectedItem(endereco.getBairro());
            atualizarView.getCbLogradouro().setSelectedItem(endereco.getLogradouro());
            atualizarView.getTxNumero().setText(endereco.getNumero());
        } else {
            // Se o endereço não for encontrado, limpe os campos
            atualizarView.getCbBairro().setSelectedIndex(-1);
            atualizarView.getCbCidade().setSelectedIndex(-1);
            atualizarView.getCbUF().setSelectedIndex(-1);
            atualizarView.getCbEstado().setSelectedIndex(-1);
            atualizarView.getCbLogradouro().setSelectedIndex(-1);
            atualizarView.getTxNumero().setText("");
        }
    }
    
    public void preencherCEPAtualizar(){
        Endereco endereco = buscarCEP(informacaoDosCamposEnderecoAtualizar());//Chama a função para buscar o CEP pelas informções do endereço
        atualizarView.getTxCEP().setText(endereco.getCep());
        atualizarView.getCbBairro().setSelectedItem(endereco.getBairro());
        atualizarView.getTxNumero().setText(endereco.getNumero());
    }
    
    public void removerCliente(){
        int id_cliente = (int) view.getTabelaCliente().getValueAt(view.getTabelaCliente().getSelectedRow(), 0);
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente deletar o cliente " + view.getTabelaCliente().getValueAt(view.getTabelaCliente().getSelectedRow(), 1), "Alerta", JOptionPane.YES_NO_OPTION);
        if(resposta == JOptionPane.YES_OPTION){
            try{
                //Realizar Conexão
                Connection conexao = new Conexao().getConnection();
                ClienteDAO clienteDao = new ClienteDAO(conexao);
                try{
                    clienteDao.delete(id_cliente);
                    readTabelaCliente(view.getTabelaCliente());
                    JOptionPane.showMessageDialog(null, "Cliente removido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Este cliente não pode ser removido!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Erro na conexão com o BD!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }    
}
