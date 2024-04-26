package controller;

import dao.Conexao;
import dao.EnderecoDAO;
import dao.UsuarioDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.Endereco;
import model.Usuario;
import org.jdesktop.swingx.sort.SortUtils;
import view.AtualizarUsuarioView;
import view.CadastroUsuarioView;
import view.UsuarioPanel;

public class UsuarioController extends EnderecoController {

    private UsuarioPanel view;
    private CadastroUsuarioView viewCadastro;
    private AtualizarUsuarioView viewAtualizar;
    private boolean pesquisaAdm;

    //Construtor
    public UsuarioController(UsuarioPanel view, CadastroUsuarioView viewCadastro, AtualizarUsuarioView viewAtualizar) {
        this.view = view;
        this.viewCadastro = viewCadastro;
        this.viewAtualizar = viewAtualizar;
    }

    //Remove o usuário selecionado na tabela
    public void removerUsuario() throws SQLException {

        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente deletar o usuario " + view.getTabelaUsuario().getValueAt(view.getTabelaUsuario().getSelectedRow(), 1), "Alerta", JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(view.getCampoPesquisaId().getText());//Pega o id do usuario que esta no campo de pesquisa bloqueado
            
            Usuario usuarioParaRemover = new Usuario(id);

            //Realiza a conexao
            Connection conexao = new Conexao().getConnection();
            UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
           
            //Se o usuário possui endereço ele entra e realiza o delete do usuario e do endereço
            if(usuarioDao.possuiEndereco(id)){
                int id_endereco = usuarioDao.selectIdEndereco(id);//Pega o id do endereço
                usuarioDao.delete(usuarioParaRemover); // Chama função para deletar usuário
                removerEnderecoComId(id_endereco);//Remove o endereço com o id do endereço
            }
            //Caso contrário apenas deleta o usuário
            else{
                usuarioDao.delete(usuarioParaRemover); // Chama função para deletar usuário
            }

            readTabelaUsuario(); // Releitura da tabela
            apagarCampos(); // Apaga os campos de pesquisa
            JOptionPane.showMessageDialog(null, "Usuario foi removido com sucesso!", "sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //Função para habilitar e desabilitar os campos de endereco
    public void enderecoHabilitado(boolean ativado) {
        viewCadastro.getCampoCadastroCEP().setEnabled(ativado);
        viewCadastro.getCampoCadastroNumero().setEnabled(ativado);
        viewCadastro.getCampoCadastroComplemento().setEnabled(ativado);
        viewCadastro.getBotaoAtualizarCEP().setEnabled(ativado);
        viewCadastro.getComboBoxEstado().setEnabled(ativado);
        viewCadastro.getComboBoxUF().setEnabled(ativado);
        viewCadastro.getComboBoxCidade().setEnabled(ativado);
        viewCadastro.getComboBoxBairro().setEnabled(ativado);
        viewCadastro.getComboBoxLogradouro().setEnabled(ativado);
        viewCadastro.getBotaoApagarCamposCadastroEndereco().setEnabled(ativado);
        
        apagarCamposCadastroEndereco(); 
    }


    //Apagar campos de informacoes do endereço
    public void apagarCamposCadastroEndereco() {
        viewCadastro.getComboBoxBairro().setSelectedIndex(-1);
        viewCadastro.getComboBoxCidade().setSelectedIndex(-1);
        viewCadastro.getComboBoxEstado().setSelectedIndex(-1);
        viewCadastro.getComboBoxUF().setSelectedIndex(-1);
        viewCadastro.getComboBoxLogradouro().setSelectedIndex(-1);
        viewCadastro.getCampoCadastroCEP().setText("");
        viewCadastro.getCampoCadastroComplemento().setText("");
        viewCadastro.getCampoCadastroNumero().setText("");
    }
    

    //Apaga os campos de pesquisa
    public void apagarCampos() {

        view.getCampoPesquisaId().setText("");
        view.getCampoPesquisaNome().setText("");
        view.getCampoPesquisaCPF().setText("");
        view.getComboBoxPesquisa().setSelectedIndex(0);
    }

    public void apagarCamposCadastro() {
        apagarCamposCadastroDadosIdentificacao();
        apagarCamposCadastroEndereco();
    }

    //Preenche o comboBox com base no tipo de usuario selecionado
    public int comboBoxPreenchimento() {
        if ((boolean) view.getTabelaUsuario().getValueAt(view.getTabelaUsuario().getSelectedRow(), 4)) {
            return 2;
        } else {
            return 1;
        }
    }

    //Apaga os campos do cadastro
    public void apagarCamposCadastroDadosIdentificacao() {

        viewCadastro.getCampoTextoNome().setText("");
        viewCadastro.getCampoTextoCpf().setText("");
        viewCadastro.getCampoTextoTelefone().setText("");
        viewCadastro.getCampoTextoSenhaUsuario().setText("");
        viewCadastro.getCampoTextoConfirmaSenhaUsuario().setText("");
        viewCadastro.getCampoCadastroObservacaoUsuario().setText("");
        viewCadastro.getCheckAdmin().setSelected(false);
    }

    //Preenche tudo com o endereco do cep
    public void preencherCamposEndereco(String cep) throws SQLException {
        Endereco endereco = buscarEndereco(cep);

        if (endereco != null) {
            // Define os valores nos campos JTextField com os dados do endereço
            viewCadastro.getComboBoxUF().setSelectedItem(endereco.getSigla());
            viewCadastro.getComboBoxEstado().setSelectedIndex(viewCadastro.getComboBoxUF().getSelectedIndex());
            viewCadastro.getComboBoxCidade().setSelectedItem(endereco.getCidade());
            viewCadastro.getComboBoxBairro().setSelectedItem(endereco.getBairro());
            viewCadastro.getComboBoxLogradouro().setSelectedItem(endereco.getLogradouro());
            viewCadastro.getCampoCadastroNumero().setText(endereco.getNumero());
        } else {
            // Se o endereço não for encontrado, limpe os campos
            viewCadastro.getComboBoxBairro().setSelectedIndex(-1);
            viewCadastro.getComboBoxCidade().setSelectedIndex(-1);
            viewCadastro.getComboBoxUF().setSelectedIndex(-1);
            viewCadastro.getComboBoxEstado().setSelectedIndex(-1);
            viewCadastro.getComboBoxLogradouro().setSelectedIndex(-1);
            viewCadastro.getCampoCadastroNumero().setText("");
        }
    }

    //Pega o que está escrito nos campos de endereco
    public Endereco enderecoDosCamposPreenchidos() throws SQLException {

        String cep = viewCadastro.getCampoCadastroCEP().getText();
        String logradouro = (String) viewCadastro.getComboBoxLogradouro().getSelectedItem();
        String cidade = (String) viewCadastro.getComboBoxCidade().getSelectedItem();
        String uf = (String) viewCadastro.getComboBoxUF().getSelectedItem();
        String bairro = (String) viewCadastro.getComboBoxBairro().getSelectedItem();
        String numero = viewCadastro.getCampoCadastroNumero().getText();
        String complemento = viewCadastro.getCampoCadastroComplemento().getText();

        Endereco endereco = new Endereco(logradouro, bairro, cidade, cep, numero, uf, complemento);
        return endereco;

    }

    //Função para preenchar o CEP com informações do endereço
    public void preencherCEP() throws SQLException {

        Endereco endereco = buscarCEP(enderecoDosCamposPreenchidos());//Chama a função para buscar o CEP pelas informções do endereço
        viewCadastro.getCampoCadastroCEP().setText(endereco.getCep());
        viewCadastro.getComboBoxBairro().setSelectedItem(endereco.getBairro());
        viewCadastro.getCampoCadastroNumero().setText(endereco.getNumero());
    }

    //Leitura da tabela
    @SuppressWarnings("unchecked")
    public void readTabelaUsuario() throws SQLException {

        DefaultTableModel modelo = (DefaultTableModel) view.getTabelaUsuario().getModel(); //Pega o modelo da tabela 
        modelo.setNumRows(0); //Seta o numero de linhas como 0, isso evita a tabela repetir informções quando atualizada
        view.getTabelaUsuario().setRowSorter(new TableRowSorter(modelo)); //Classifica as linha da tabela 

        //Realiza a conexao
        Connection conexao = new Conexao().getConnection();
        UsuarioDAO usuarioDao = new UsuarioDAO(conexao);

        //Chama a função de leitura de usuario e adiciona nas linhas e colunas
        for (Usuario usuario : usuarioDao.readUsuario()) {

            modelo.addRow(new Object[]{
                usuario.getId(),
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getTelefone(),
                usuario.isAdmin()
            });
        }
    }

    //Verifica o tipo de usuario selecionado no combobox
    public void comboBoxAdmin() {
        if (view.getComboBoxPesquisa().getSelectedIndex() != 0) {
            if (view.getComboBoxPesquisa().getSelectedIndex() == 1) {
                pesquisaAdm = false;
            }
            if (view.getComboBoxPesquisa().getSelectedIndex() == 2) {
                pesquisaAdm = true;
            }
        }
    }

    //Função para busca de usuário
    @SuppressWarnings("unchecked")
    public void buscarUsuario() throws SQLException {

        view.getCampoPesquisaId().setText("");//Seta o campo de id como vazio

        DefaultTableModel modelo = (DefaultTableModel) view.getTabelaUsuario().getModel(); //Pega o modelo da tabela 
        modelo.setNumRows(0);
        view.getTabelaUsuario().setRowSorter(new TableRowSorter(modelo)); //Classifica as linha da tabela 

        //Realiza a conexao
        Connection conexao = new Conexao().getConnection();
        UsuarioDAO usuarioDao = new UsuarioDAO(conexao);

        Usuario usuarioPesquisa = new Usuario();

        usuarioPesquisa.setNome(view.getCampoPesquisaNome().getText());
        usuarioPesquisa.setCpf(view.getCampoPesquisaCPF().getText());

        //Se não for selecionado o tipo de usuario para pesquisa é executado o if
        if (view.getComboBoxPesquisa().getSelectedIndex() == 0) {

            //Chama a função de leitura de usuario e adiciona nas linhas e colunas
            for (Usuario usuario : usuarioDao.buscarUsuarioNOMEeCPF(usuarioPesquisa)) {

                modelo.addRow(new Object[]{
                    usuario.getId(),
                    usuario.getNome(),
                    usuario.getCpf(),
                    usuario.getTelefone(),
                    usuario.isAdmin()
                });
            }
        } //Caso contrário é realizado outro tipo de pesquisa, pelo nome, cpf e adm
        else {
            comboBoxAdmin();//Verifica se é admin ou não

            usuarioPesquisa.setAdmin(pesquisaAdm);

            for (Usuario usuario : usuarioDao.buscarUsuarioNOMEeCPFeADM(usuarioPesquisa)) {
                modelo.addRow(new Object[]{
                    usuario.getId(),
                    usuario.getNome(),
                    usuario.getCpf(),
                    usuario.getTelefone(),
                    usuario.isAdmin()
                });
            }
        }
    }

    //Função para realizar o cadastro do endereço -- retorna o id_endereço
    public int realizarCadastroEndereco() throws SQLException {
        int id_endereco;
        Endereco endereco = enderecoDosCamposPreenchidos();//Pega as informações dos campos de endereçp
        id_endereco = cadastroEndereco(endereco, campoNuloEndereco());//Chama a função para cadastrar o endereço e armazena o id retornado

        return id_endereco;
    }

    //Verifica se tem campo nulo em endereço -- retorna true or false
    public boolean campoNuloEndereco() {
        String cep = viewCadastro.getCampoCadastroCEP().getText();
        String numero = viewCadastro.getCampoCadastroNumero().getText();
        String estado = (String) viewCadastro.getComboBoxEstado().getSelectedItem();
        String bairro = (String) viewCadastro.getComboBoxBairro().getSelectedItem();
        String logradouro = (String) viewCadastro.getComboBoxLogradouro().getSelectedItem();

        return cep.isEmpty() || numero.isEmpty() || estado.isEmpty() || bairro.isEmpty() || logradouro.isEmpty();//Caso algum campos está nulo ele retorna true    
    }

    //Função para realização do cadastro do cliente, ela recebe um true or false do Radio Button para habilitar ou não o endereço
    public void realizarCadastro(boolean ativado) throws SQLException {
        
        Usuario usuarioCadastrar = informacaoDosCamposPessoais();//Pega os campos pessoais preenchidos
        char[] senhaCharConfirma = viewCadastro.getCampoTextoConfirmaSenhaUsuario().getPassword();//Pega o que foi escrito de senha confirma
        String senhaConfirma = new String(senhaCharConfirma);//Transforma a senha character em string
        int id_endereco = -1;//id do endereço é setado como -1
        boolean campoEmBranco, existe, senhaCorreta, cpfValido, telefoneValido; //Variáveis para armazenar as verificações

        //Realiza a conexão
        Connection conexao = new Conexao().getConnection();
        UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
        
        campoEmBranco = verificaCampoPreenchido(); //verifica se os campos estão preenchidos
        existe = usuarioDao.verificaExistencia(usuarioCadastrar); //Verifica a existencia do usuario
        senhaCorreta = comparacaoStrings(usuarioCadastrar.getSenha(), senhaConfirma); //Verifica se a senha esta compativel nos dois bancos
        cpfValido = verificaCPFvalido(usuarioCadastrar.getCpf());// Verifica se o CPF é valido
        telefoneValido = verificaTelefoneValido(usuarioCadastrar.getTelefone());

        //Se os campos não estiverem de acordo com as validações ele entra e avisa o erro
        if (campoEmBranco || existe || !senhaCorreta || !cpfValido || !telefoneValido) {
            avisosErro(campoEmBranco, existe, !senhaCorreta, !cpfValido, !telefoneValido);
        } else {
            //Se os campos de endereço estejam ativados ele entra
            if (ativado) {
                id_endereco = realizarCadastroEndereco(); //Insere o endereço no banco de dados e pega o seu id

                //Caso o id do endereço for maior do que 0 significa que o cadastro pode continuar
                if (id_endereco >= 0) {
                    realizarCadastroUsuarioComEndereco(id_endereco, usuarioCadastrar);//Função para o cadastro do cliente com endereço, é necessário enviar o id_endereco
                }
            } //Caso o Radio Button esteja desativo o cadastro será realizado sem endereço
            else {
                realizarCadastroUsuarioSemEndereco(usuarioCadastrar);//Função para cadastro sem endereço
            }
        }
    }

    //Função para realizar o cadastro do usuário com endereço
    public void realizarCadastroUsuarioComEndereco(int id_endereco, Usuario usuarioCadastrar) throws SQLException {

        //Realiza a conexão
        Connection conexao = new Conexao().getConnection();
        UsuarioDAO usuarioDao = new UsuarioDAO(conexao);

        usuarioDao.insertComEndereco(usuarioCadastrar, id_endereco);//Função para inserir usuario sem endereço
        JOptionPane.showMessageDialog(null, "Usuário cadastrado", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
        viewCadastro.dispose();//fecha a tela de cadastro
    }

    //Cadastro de usuario
    public void realizarCadastroUsuarioSemEndereco(Usuario usuarioCadastrar) throws SQLException {

        //Realiza a conexao e envia para usuarioDao
        Connection conexao = new Conexao().getConnection();
        UsuarioDAO usuarioDao = new UsuarioDAO(conexao);

        usuarioDao.insert(usuarioCadastrar);
        JOptionPane.showMessageDialog(null, "Usuário cadastrado", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
        viewCadastro.dispose();//fecha a tela de cadastro
    }

    //Função para pegar as informações dos campos pessoais
    public Usuario informacaoDosCamposPessoais() {
        String nome, cpf, telefone, observacao, senha;
        char[] senhaChar;
        boolean admin;

        //Pega o que foi inserido nos campos e armazena em váriaveis
        nome = viewCadastro.getCampoTextoNome().getText();
        cpf = viewCadastro.getCampoTextoCpf().getText();
        telefone = viewCadastro.getCampoTextoTelefone().getText();
        observacao = viewCadastro.getCampoCadastroObservacaoUsuario().getText();
        senhaChar = viewCadastro.getCampoTextoSenhaUsuario().getPassword();
        admin = viewCadastro.getCheckAdmin().isSelected();

        //Transforma a senha character em string
        senha = new String(senhaChar);

        Usuario clienteComDados = new Usuario(nome, senha, cpf, telefone, admin, observacao);
        return clienteComDados;

    }

    //Comparar strings e ver se são iguais
    public boolean comparacaoStrings(String string, String string2) {
        return string.intern().equals(string2.intern());//Se são iguais retorna true
    }

    // verifica se a algum campo em branco antes de cadastrar o usuario
    public boolean verificaCampoPreenchido() {
        String nome = viewCadastro.getCampoTextoNome().getText();
        String cpf = viewCadastro.getCampoTextoCpf().getText();
        char[] senha = viewCadastro.getCampoTextoSenhaUsuario().getPassword();
        char[] senhaConfirma = viewCadastro.getCampoTextoConfirmaSenhaUsuario().getPassword();
        String telefone = viewCadastro.getCampoTextoTelefone().getText();

        return nome.isEmpty() || cpf.isEmpty() || senha.length == 0 || senhaConfirma.length == 0 || telefone.isEmpty(); //Se algum desses campos for vazio será retornado true
    }

    //Função para verificar o se o cpf é valido
    public boolean verificaCPFvalido(String cpf) {
        return cpf.length() == 14; //Se o tamanho do cpf for 14 retorna true
    }

    //Função para verificar se o telefone é válido
    public boolean verificaTelefoneValido(String telefone) {
        return telefone.length() >= 13;
    }

    //Função para mandar os avisos de erro de acordo com os erros
    public void avisosErro(boolean campoEmBranco, boolean usuarioExiste, boolean senhaIncorreta, boolean cpfInvalido, boolean telefoneInvalido) {
        if (campoEmBranco) {
            JOptionPane.showMessageDialog(null, "Campo(s) em branco!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (cpfInvalido) {
            JOptionPane.showMessageDialog(null, "CPF inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (usuarioExiste) {
            JOptionPane.showMessageDialog(null, "Usuário já existe!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (senhaIncorreta) {
            JOptionPane.showMessageDialog(null, "Campos de senha discrepantes!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (telefoneInvalido) {
            JOptionPane.showMessageDialog(null, "Telefone incompleto!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    //Função para habilitar e desabilitar os campos de atualização
    public void habilitarEditarAtulizar(boolean ativado){
        habilitarCamposAtualizarEndereco(ativado, viewAtualizar.getCheckBoxEndereco().isSelected());
        viewAtualizar.getCheckBoxEndereco().setEnabled(ativado);
        viewAtualizar.getCampoTextoNome().setEnabled(ativado);
        viewAtualizar.getCampoTextoCPF().setEnabled(ativado);
        viewAtualizar.getCampoTextoTelefone().setEnabled(ativado);
        viewAtualizar.getCampoTextoObservacao().setEnabled(ativado);
        viewAtualizar.getCheckBoxAdm().setEnabled(ativado);
        viewAtualizar.getRadioButtonSenha().setEnabled(ativado);
        viewAtualizar.getRadioButtonSenha().setSelected(false);
        viewAtualizar.getCampoTextoSenha().setEnabled(false);
        viewAtualizar.getCampoTextoConfirmarSenha().setEnabled(false);
        viewAtualizar.getBotaoSalvarAlteracao().setEnabled(ativado);
       
    }
    
    
    //Função para habilitar campos de endereço na atualização
    public void habilitarCamposAtualizarEndereco(boolean editarAtivado, boolean comEndereco){
        if (!editarAtivado) {comEndereco = false;}//Verifica se está habilitado a função de editar
        
        viewAtualizar.getCampoTextoCEP().setEnabled(comEndereco);
        viewAtualizar.getCampoTextoNumero().setEnabled(comEndereco);
        viewAtualizar.getCampoTextoComplemento().setEnabled(comEndereco);
        viewAtualizar.getBotaoAtulizarCEP().setEnabled(comEndereco);
        viewAtualizar.getComboBoxEstado().setEnabled(comEndereco);
        viewAtualizar.getComboBoxUF().setEnabled(comEndereco);
        viewAtualizar.getComboBoxCidade().setEnabled(comEndereco);
        viewAtualizar.getComboBoxBairro().setEnabled(comEndereco);
        viewAtualizar.getComboBoxLogradouro().setEnabled(comEndereco);
        
    }
    
    //Função para habilitar e desabilitar os campos de senha em atualizar
    public void habilitarCamposAtualizarSenha(boolean ativado){
        viewAtualizar.getCampoTextoSenha().setEnabled(ativado);
        viewAtualizar.getCampoTextoConfirmarSenha().setEnabled(ativado);
        
    }
    
    //Função para preenchaer os campos com as informações do usuario
    public void preencherInformacaoUsuarioAtualizar() throws SQLException{
        int id_usuario = Integer.parseInt(view.getCampoPesquisaId().getText());//Pega o id do campo de texto e transforma em int
        
        //Realiza a conexão
        Connection conexao = new Conexao().getConnection();
        UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
        
        Usuario usuarioAtualizar = usuarioDao.selectUsuarioPorId(id_usuario);//pega informações do usuário pelo id
        viewAtualizar.setUsuario(usuarioAtualizar);
        
        preencherCamposAtualizarInfoUsuario(usuarioAtualizar);//Preenche campos de identificação do usuário
        
        //Se o id do endereço for maior que 0 significa que possui endereço e entra
        if(usuarioAtualizar.getId_endereco()>0){
            viewAtualizar.getCheckBoxEndereco().setSelected(true);
            habilitarCamposAtualizarEndereco(true, true);
            EnderecoDAO enderecoDao = new EnderecoDAO(conexao);//Realiza a conexao
            Endereco enderecoUsuario = enderecoDao.selectEnderecoCompletoPorIdEndereco(usuarioAtualizar.getId_endereco());//Pega O endereço completo pelo seu id
            preencherCamposAtualizarInfoEndereco(enderecoUsuario);//Preenche os campos de endereçp
        } 
    }
    
    //Função para preencher os campos de identificacao do usuário
    public void preencherCamposAtualizarInfoUsuario(Usuario usuarioAtualizar){     
        viewAtualizar.getCampoTextoNome().setText(usuarioAtualizar.getNome());
        viewAtualizar.getCampoTextoCPF().setText(usuarioAtualizar.getCpf());
        viewAtualizar.getCampoTextoTelefone().setText(usuarioAtualizar.getTelefone());
        viewAtualizar.getCampoTextoObservacao().setText(usuarioAtualizar.getObservacao());
        viewAtualizar.getCheckBoxAdm().setSelected(usuarioAtualizar.isAdmin());      
    }
    
    //Função para preencher os campos de endereco
    public void preencherCamposAtualizarInfoEndereco(Endereco enderecoUsuarioAtualizar){
        viewAtualizar.getCampoTextoCEP().setText(enderecoUsuarioAtualizar.getCep());
        viewAtualizar.getComboBoxEstado().setSelectedItem(enderecoUsuarioAtualizar.getEstado());
        viewAtualizar.getComboBoxUF().setSelectedItem(enderecoUsuarioAtualizar.getUf());
        viewAtualizar.getCampoTextoNumero().setText(enderecoUsuarioAtualizar.getNumero());
        viewAtualizar.getCampoTextoComplemento().setText(enderecoUsuarioAtualizar.getComplemento());
        viewAtualizar.getComboBoxCidade().setSelectedItem(enderecoUsuarioAtualizar.getCidade());
        viewAtualizar.getComboBoxBairro().setSelectedItem(enderecoUsuarioAtualizar.getBairro());
        viewAtualizar.getComboBoxLogradouro().setSelectedItem(enderecoUsuarioAtualizar.getLogradouro());
        
    }
    
    //Função para quando iniciar o atualizar ele manter o padrão
    public void inicializacaoCamposAtualizar(){
        viewAtualizar.setEstadoSelecionado(-1);
        viewAtualizar.setCidadeSelecionada(-1);
        viewAtualizar.setBairroSelecionado(-1);
        viewAtualizar.getCampoTextoCEP().setText("");
        viewAtualizar.getCampoTextoNumero().setText("");
        viewAtualizar.getCampoTextoComplemento().setText("");
        viewAtualizar.getComboBoxEstado().setSelectedIndex(-1);
        viewAtualizar.getComboBoxUF().setSelectedIndex(-1);
        viewAtualizar.getComboBoxCidade().setSelectedIndex(-1);
        viewAtualizar.getComboBoxBairro().setSelectedIndex(-1);
        viewAtualizar.getComboBoxLogradouro().setSelectedIndex(-1);
        viewAtualizar.getCampoTextoNome().setText("");
        viewAtualizar.getCampoTextoCPF().setText("");
        viewAtualizar.getCampoTextoTelefone().setText("");
        viewAtualizar.getCampoTextoObservacao().setText("");
        viewAtualizar.getCheckBoxAdm().setSelected(false);
        viewAtualizar.getRadioButtonSenha().setSelected(false);
        viewAtualizar.getCampoTextoSenha().setEnabled(false);
        viewAtualizar.getCampoTextoConfirmarSenha().setEnabled(false);
        viewAtualizar.getRadioButtonEditar().setSelected(true);
        viewAtualizar.getCampoTextoSenha().setText("");
        viewAtualizar.getCampoTextoConfirmarSenha().setText("");
        viewAtualizar.getCheckBoxEndereco().setSelected(false);
        habilitarEditarAtulizar(true);
    }
    
    
    //Função para salvar atualização do usuário -- Incompleta
    public void salvarAtualizacao(Usuario usuarioPadrao) throws SQLException{
        //Váriaveis para verificação
        boolean jaPossuiEndereco, editarSenhaHabilitado, enderecoHabilitado, campoEmBranco, senhasIguais;
        
        String senha = new String(viewAtualizar.getCampoTextoSenha().getPassword());
        String senhaConfirma = new String(viewAtualizar.getCampoTextoConfirmarSenha().getPassword());
        
        //Verificações
        jaPossuiEndereco = usuarioTemEndereco(usuarioPadrao.getId_endereco());//Verifica se ele possui endereçp
        editarSenhaHabilitado = viewAtualizar.getRadioButtonSenha().isSelected();
        enderecoHabilitado = viewAtualizar.getCheckBoxEndereco().isSelected();
        campoEmBranco = campoNuloAtualizar();
        senhasIguais = comparacaoStrings(senha, senhaConfirma);
        
        //Se algum campo está fora das conformidades ele entra 
        if(campoEmBranco || !senhasIguais){
            avisosErro(campoEmBranco, false, !senhasIguais, false, false);//Função para mostrar o aviso de erro conforme o caso
        }
        
        //Caso contrário ele entra para realizar o update
        else{
            if(jaPossuiEndereco){//Se ja possui endereço ele entra
                realizarUpdateDeUsuarioQueJaPossuiEndereco(editarSenhaHabilitado, enderecoHabilitado, usuarioPadrao);
            }
            else{//Caso não possui ele entra neste else
                realizarUpdateDeUsuarioQueNãoPossuiEndereco(editarSenhaHabilitado, enderecoHabilitado, usuarioPadrao);
            }
        }   
    } 
    
    //Função para atualizar usuario que já possui endereço -- Incompleta
    public void realizarUpdateDeUsuarioQueJaPossuiEndereco(boolean editarSenha, boolean teraEndereco, Usuario usuarioParaModificar) throws SQLException{
        
        //Realiza a conexao
        Connection conexao = new Conexao().getConnection();
        UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
        
        Usuario usuarioModificado = informacaoDosCamposPessoaisAtualizar();
        usuarioModificado.setId(usuarioParaModificar.getId());
        
        //Realizar o update dos dados normais
        usuarioDao.update(usuarioModificado);
        
        //verificações para o update
        if(editarSenha){//Se as edições de senha estão ativadas, ele entra
            String senha = new String(viewAtualizar.getCampoTextoSenha().getPassword());
            //usuarioDao.updateSenha(usuarioParaModificar.getId(), senha); -- Ainda não implementada
        }
        
        if(!teraEndereco){//Se a for modificado para que o usuário não tenha um endereço, ele entra 
            //Deletar o endereço cadastrado
        }
        
        
    }
    
    //Função para atualizar usuario que não possui endereço -- Incompleta
    public void realizarUpdateDeUsuarioQueNãoPossuiEndereco(boolean editarSenha, boolean teraEndereco, Usuario usuarioParaModificar){
        
        //verificações para o update
        
    }
    
    //Função para verificar se tem campos nulos em atualizar
    public boolean campoNuloAtualizar(){
        boolean nomeNulo = viewAtualizar.getCampoTextoNome().getText().isEmpty();
        boolean cpfNulo = viewAtualizar.getCampoTextoCPF().getText().isEmpty();
        boolean telefoneNulo = viewAtualizar.getCampoTextoTelefone().getText().isEmpty();
        boolean senhaNulo = false;
        boolean enderecoNulo = false;
        
        if(viewAtualizar.getRadioButtonSenha().isSelected()){
            char[] senha = viewAtualizar.getCampoTextoSenha().getPassword();
            char[] senhaConfirmar = viewAtualizar.getCampoTextoConfirmarSenha().getPassword();
            
            //Caso um dos campos de senha esteje vazio ele retorna true
            senhaNulo = senha.length==0 || senhaConfirmar.length==0;
        }
        
        //Se a check box de endereço estiver ativada ele verifica se os campos estão preenchidos
        if(viewAtualizar.getCheckBoxEndereco().isSelected()){
            String cep = viewAtualizar.getCampoTextoCEP().getText();
            String uf = (String) viewAtualizar.getComboBoxUF().getSelectedItem();
            String cidade = (String) viewAtualizar.getComboBoxCidade().getSelectedItem();
            String bairro = (String) viewAtualizar.getComboBoxBairro().getSelectedItem();
            String logradouro = (String) viewAtualizar.getComboBoxLogradouro().getSelectedItem();
            String numero = viewAtualizar.getCampoTextoNumero().getText();
            
            //Caso algum campo esteja em branco ele retorna true
            enderecoNulo = cep.isEmpty() || uf.isEmpty() || cidade.isEmpty() || bairro.isEmpty() || logradouro.isEmpty() || numero.isEmpty();
        }
        
        return nomeNulo || cpfNulo || telefoneNulo || senhaNulo || enderecoNulo;
    }
    
    //public boolean verificaCampoSenha(){}
    
    //Função para verificar se o usuário tem Endereço
    public boolean usuarioTemEndereco(int id){
        return id>0;
    }
    
    //Função para pegar as informações dos campos pessoais em Atualizar
    public Usuario informacaoDosCamposPessoaisAtualizar() {
        String nome, cpf, telefone, observacao, senha;
        char[] senhaChar;
        boolean admin;

        //Pega o que foi inserido nos campos e armazena em váriaveis
        nome = viewAtualizar.getCampoTextoNome().getText();
        cpf = viewAtualizar.getCampoTextoCPF().getText();
        telefone = viewAtualizar.getCampoTextoTelefone().getText();
        observacao = viewAtualizar.getCampoTextoComplemento().getText();
        admin = viewAtualizar.getCheckBoxAdm().isSelected();

        Usuario clienteComDados = new Usuario(nome, cpf, telefone, admin, observacao);
        return clienteComDados;

    }
}
