/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.TextoController;
import controller.UsuarioController;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.Usuario;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author luizf
 */
public class AtualizarUsuarioView extends javax.swing.JFrame {

    private UsuarioController controller;
    private TextoController controllerTexto = new TextoController();
    private Usuario usuario;
    private int estadoSelecionado = -1, cidadeSelecionada = -1, bairroSelecionado = -1;
    
    public AtualizarUsuarioView() { 
        initComponents();
        
        AutoCompleteDecorator.decorate(ComboBoxEstado);
        AutoCompleteDecorator.decorate(ComboBoxCidade);
        AutoCompleteDecorator.decorate(ComboBoxBairro);
        AutoCompleteDecorator.decorate(ComboBoxLogradouro);
    }  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        RadioButtonEditar = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        CampoTextoNome = new javax.swing.JTextField();
        CampoTextoCPF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        CampoTextoTelefone = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        CampoTextoObservacao = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        RadioButtonSenha = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        CampoTextoCEP = new javax.swing.JTextField();
        BotaoAtualizarCEP = new javax.swing.JButton();
        ComboBoxEstado = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        ComboBoxUF = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        ComboBoxCidade = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        ComboBoxLogradouro = new javax.swing.JComboBox();
        CampoTextoNumero = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        CampoTextoComplemento = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        BotaoSalvarAlteracao = new javax.swing.JButton();
        BotaoDesfazerAlteracao = new javax.swing.JButton();
        ComboBoxBairro = new javax.swing.JComboBox();
        CheckBoxAdm = new javax.swing.JCheckBox();
        jLabel14 = new javax.swing.JLabel();
        CheckBoxEndereco = new javax.swing.JCheckBox();
        CampoTextoSenha = new javax.swing.JPasswordField();
        CampoTextoConfirmarSenha = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        RadioButtonEditar.setSelected(true);
        RadioButtonEditar.setText("Editar");
        RadioButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButtonEditarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel4.setText("Atualizar Usuário");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Dados de identificação");

        jLabel2.setText("Nome completo");

        CampoTextoCPF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CampoTextoCPFKeyTyped(evt);
            }
        });

        jLabel3.setText("CPF");

        CampoTextoTelefone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CampoTextoTelefoneKeyTyped(evt);
            }
        });

        jLabel5.setText("Telefone");

        CampoTextoObservacao.setColumns(20);
        CampoTextoObservacao.setRows(5);
        jScrollPane1.setViewportView(CampoTextoObservacao);

        jLabel6.setText("Observação");

        RadioButtonSenha.setText("Senha");
        RadioButtonSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButtonSenhaActionPerformed(evt);
            }
        });

        jLabel7.setText("Senha");

        jLabel8.setText("Confirmar Senha");

        jLabel10.setText("CEP");

        CampoTextoCEP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CampoTextoCEPKeyTyped(evt);
            }
        });

        BotaoAtualizarCEP.setText("jButton1");
        BotaoAtualizarCEP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoAtualizarCEPActionPerformed(evt);
            }
        });

        ComboBoxEstado.setEditable(true);
        ComboBoxEstado.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                ComboBoxEstadoAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        ComboBoxEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxEstadoActionPerformed(evt);
            }
        });

        jLabel11.setText("Estado");

        ComboBoxUF.setEditable(true);
        ComboBoxUF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxUFActionPerformed(evt);
            }
        });

        jLabel12.setText("UF");

        ComboBoxCidade.setEditable(true);
        ComboBoxCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxCidadeActionPerformed(evt);
            }
        });

        jLabel13.setText("Cidade");

        jLabel15.setText("Logradouro");

        ComboBoxLogradouro.setEditable(true);

        jLabel16.setText("Número");

        jLabel17.setText("Complemento");

        BotaoSalvarAlteracao.setText("Salvar");
        BotaoSalvarAlteracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoSalvarAlteracaoActionPerformed(evt);
            }
        });

        BotaoDesfazerAlteracao.setText("Desfazer Alterações");
        BotaoDesfazerAlteracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoDesfazerAlteracaoActionPerformed(evt);
            }
        });

        ComboBoxBairro.setEditable(true);
        ComboBoxBairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxBairroActionPerformed(evt);
            }
        });

        CheckBoxAdm.setText("Admin");

        jLabel14.setText("Bairro");

        CheckBoxEndereco.setText("Endereço");
        CheckBoxEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckBoxEnderecoActionPerformed(evt);
            }
        });

        CampoTextoSenha.setEnabled(false);

        CampoTextoConfirmarSenha.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(CheckBoxEndereco)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(CampoTextoCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(BotaoAtualizarCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel10))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(ComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(ComboBoxUF, 0, 105, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jScrollPane1)
                                    .addComponent(CampoTextoNome))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(RadioButtonSenha))
                                        .addGap(96, 96, 96))
                                    .addComponent(CampoTextoCPF)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel4)
                                    .addComponent(RadioButtonEditar)
                                    .addComponent(jLabel6))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ComboBoxLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(CampoTextoNumero))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel17)
                                .addComponent(jLabel5)
                                .addComponent(jLabel7)
                                .addComponent(jLabel8)
                                .addComponent(CampoTextoComplemento)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(BotaoDesfazerAlteracao, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                                    .addGap(18, 18, 18)
                                    .addComponent(BotaoSalvarAlteracao, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(CampoTextoTelefone)
                                        .addComponent(jLabel13)
                                        .addComponent(CampoTextoSenha)
                                        .addComponent(ComboBoxCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(ComboBoxBairro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(CheckBoxAdm)
                                                .addComponent(jLabel14))
                                            .addGap(0, 0, Short.MAX_VALUE)))))
                            .addComponent(CampoTextoConfirmarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(105, 105, 105))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(RadioButtonEditar)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CampoTextoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CampoTextoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CampoTextoTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CheckBoxAdm))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(RadioButtonSenha))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(CampoTextoSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CampoTextoConfirmarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(47, 47, 47)
                .addComponent(CheckBoxEndereco)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CampoTextoCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoAtualizarCEP)
                    .addComponent(ComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboBoxUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboBoxCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboBoxBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBoxLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CampoTextoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CampoTextoComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotaoSalvarAlteracao)
                    .addComponent(BotaoDesfazerAlteracao))
                .addGap(49, 49, 49))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void RadioButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButtonEditarActionPerformed
        controller.habilitarEditarAtulizar(getRadioButtonEditar().isSelected());
    }//GEN-LAST:event_RadioButtonEditarActionPerformed

    private void RadioButtonSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButtonSenhaActionPerformed
        controller.habilitarCamposAtualizarSenha(getRadioButtonSenha().isSelected());
    }//GEN-LAST:event_RadioButtonSenhaActionPerformed

    private void ComboBoxEstadoAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_ComboBoxEstadoAncestorAdded

    }//GEN-LAST:event_ComboBoxEstadoAncestorAdded

    @SuppressWarnings("unchecked")
    private void ComboBoxEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxEstadoActionPerformed
        if(getComboBoxEstado().getSelectedIndex()>=0 && this.estadoSelecionado != getComboBoxEstado().getSelectedIndex() && getComboBoxEstado().getItemCount()>=26){
            this.estadoSelecionado = getComboBoxEstado().getSelectedIndex();    
            controller.atualizaComboBoxEstado(ComboBoxEstado, ComboBoxUF, ComboBoxCidade, this.estadoSelecionado);
            ComboBoxLogradouro.removeAllItems();
            this.cidadeSelecionada = -1;
            this.bairroSelecionado = -1;
            ComboBoxBairro.removeAllItems();
        }
    }//GEN-LAST:event_ComboBoxEstadoActionPerformed

    @SuppressWarnings("unchecked")
    private void ComboBoxUFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxUFActionPerformed
        if(getComboBoxUF().getSelectedIndex()>=0 && this.estadoSelecionado != getComboBoxUF().getSelectedIndex() && getComboBoxUF().getItemCount()>=26){
            this.estadoSelecionado = getComboBoxUF().getSelectedIndex();    
            controller.atualizaComboBoxEstado(ComboBoxEstado, ComboBoxUF, ComboBoxCidade, this.estadoSelecionado);
            ComboBoxLogradouro.removeAllItems();
            this.cidadeSelecionada = -1;
            this.bairroSelecionado = -1;
        }
    }//GEN-LAST:event_ComboBoxUFActionPerformed

    @SuppressWarnings("unchecked")
    private void ComboBoxCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxCidadeActionPerformed
        if(this.cidadeSelecionada != getComboBoxCidade().getSelectedIndex() && getComboBoxCidade().getSelectedIndex()>=0){
            this.cidadeSelecionada = getComboBoxCidade().getSelectedIndex();
            try {
                controller.comboBoxBairros(ComboBoxUF, ComboBoxCidade, ComboBoxBairro);
            } catch (SQLException ex) {
                Logger.getLogger(CadastroClienteView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_ComboBoxCidadeActionPerformed

    @SuppressWarnings("unchecked")
    private void ComboBoxBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxBairroActionPerformed
        if(this.bairroSelecionado != getComboBoxBairro().getSelectedIndex() && getComboBoxBairro().getSelectedIndex()>=0){
            this.bairroSelecionado = getComboBoxBairro().getSelectedIndex();
            try {
                controller.comboBoxLogradouros(ComboBoxUF, ComboBoxCidade, ComboBoxBairro, ComboBoxLogradouro);
            } catch (SQLException ex) {
                Logger.getLogger(CadastroClienteView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_ComboBoxBairroActionPerformed

    private void CheckBoxEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckBoxEnderecoActionPerformed
        controller.habilitarCamposAtualizarEndereco(getRadioButtonEditar().isSelected(),getCheckBoxEndereco().isSelected());
    }//GEN-LAST:event_CheckBoxEnderecoActionPerformed

    private void BotaoSalvarAlteracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoSalvarAlteracaoActionPerformed
        try {
            controller.salvarAtualizacao(usuario);
        } catch (SQLException ex) {
            Logger.getLogger(AtualizarUsuarioView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BotaoSalvarAlteracaoActionPerformed

    private void BotaoAtualizarCEPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoAtualizarCEPActionPerformed
        String cep = getCampoTextoCEP().getText();
        
        String logradouro = (String) getComboBoxLogradouro().getSelectedItem();
        String cidade = (String) getComboBoxCidade().getSelectedItem();
        String uf = (String) getComboBoxUF().getSelectedItem();
        
        if(!cep.isEmpty()){
            try {
                controller.preencherCamposEnderecoAtualizar(cep);
            } catch (SQLException ex) {
                Logger.getLogger(AtualizarUsuarioView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            if(!logradouro.isEmpty() && !cidade.isEmpty() && !uf.isEmpty()){
                try {
                    controller.preencherCEPAtualizar();
                } catch (SQLException ex) {
                    Logger.getLogger(AtualizarUsuarioView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_BotaoAtualizarCEPActionPerformed

    private void CampoTextoCPFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CampoTextoCPFKeyTyped
        if(controllerTexto.formatacaoCPF(evt, getCampoTextoCPF().getText())){
            getCampoTextoCPF().setText(controllerTexto.mascaraCPF(getCampoTextoCPF().getText()));
        }
        else{
            evt.consume();
        }
    }//GEN-LAST:event_CampoTextoCPFKeyTyped

    private void CampoTextoTelefoneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CampoTextoTelefoneKeyTyped
        if(controllerTexto.formatacaoTelefone(evt, getCampoTextoTelefone().getText())){
            getCampoTextoTelefone().setText(controllerTexto.mascaraTelefone(getCampoTextoTelefone().getText()));
        }
        else{
            evt.consume();
        }
    }//GEN-LAST:event_CampoTextoTelefoneKeyTyped

    private void CampoTextoCEPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CampoTextoCEPKeyTyped
        if(controllerTexto.formatacaoCEP(evt, getCampoTextoCEP().getText())){
            getCampoTextoCEP().setText(controllerTexto.mascaraCEP(getCampoTextoCEP().getText()));
        }
        else{
            evt.consume();
        }
    }//GEN-LAST:event_CampoTextoCEPKeyTyped

    private void BotaoDesfazerAlteracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoDesfazerAlteracaoActionPerformed
        controller.desfazerAlterecao(usuario);
    }//GEN-LAST:event_BotaoDesfazerAlteracaoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AtualizarUsuarioView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AtualizarUsuarioView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AtualizarUsuarioView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AtualizarUsuarioView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AtualizarUsuarioView().setVisible(true);
            }
        });
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public UsuarioController getController() {
        return controller;
    }

    public void setController(UsuarioController controller) {
        this.controller = controller;
    }

    public JButton getBotaoAtulizarCEP() {
        return BotaoAtualizarCEP;
    }

    public void setBotaoAtulizarCEP(JButton BotaoAtulizarCEP) {
        this.BotaoAtualizarCEP = BotaoAtulizarCEP;
    }

    public JButton getBotaoDesfazerAlteracao() {
        return BotaoDesfazerAlteracao;
    }

    public void setBotaoDesfazerAlteracao(JButton BotaoDesfazerAlteracao) {
        this.BotaoDesfazerAlteracao = BotaoDesfazerAlteracao;
    }

    public JTextField getCampoTextoCEP() {
        return CampoTextoCEP;
    }

    public void setCampoTextoCEP(JTextField CampoTextoCEP) {
        this.CampoTextoCEP = CampoTextoCEP;
    }

    public JTextField getCampoTextoCPF() {
        return CampoTextoCPF;
    }

    public void setCampoTextoCPF(JTextField CampoTextoCPF) {
        this.CampoTextoCPF = CampoTextoCPF;
    }

    public JTextField getCampoTextoComplemento() {
        return CampoTextoComplemento;
    }

    public void setCampoTextoComplemento(JTextField CampoTextoComplemento) {
        this.CampoTextoComplemento = CampoTextoComplemento;
    }

    public JPasswordField getCampoTextoConfirmarSenha() {
        return CampoTextoConfirmarSenha;
    }

    public void setCampoTextoConfirmarSenha(JPasswordField CampoTextoConfirmarSenha) {
        this.CampoTextoConfirmarSenha = CampoTextoConfirmarSenha;
    }

    public JTextField getCampoTextoNome() {
        return CampoTextoNome;
    }
    
    
    public void setCampoTextoNome(JTextField CampoTextoNome) {
        this.CampoTextoNome = CampoTextoNome;
    }

    public JTextField getCampoTextoNumero() {
        return CampoTextoNumero;
    }

    public void setCampoTextoNumero(JTextField CampoTextoNumero) {
        this.CampoTextoNumero = CampoTextoNumero;
    }

    public JTextArea getCampoTextoObservacao() {
        return CampoTextoObservacao;
    }

    public void setCampoTextoObservacao(JTextArea CampoTextoObservacao) {
        this.CampoTextoObservacao = CampoTextoObservacao;
    }

    public JPasswordField getCampoTextoSenha() {
        return CampoTextoSenha;
    }

    public void setCampoTextoSenha(JPasswordField CampoTextoSenha) {
        this.CampoTextoSenha = CampoTextoSenha;
    }
    
    public JTextField getCampoTextoTelefone() {
        return CampoTextoTelefone;
    }

    public void setCampoTextoTelefone(JTextField CampoTextoTelefone) {
        this.CampoTextoTelefone = CampoTextoTelefone;
    }

    public JCheckBox getCheckBoxAdm() {
        return CheckBoxAdm;
    }

    public void setCheckBoxAdm(JCheckBox CheckBoxAdm) {
        this.CheckBoxAdm = CheckBoxAdm;
    }

    public JComboBox getComboBoxEstado() {
        return ComboBoxEstado;
    }

    public void setComboBoxEstado(JComboBox ComboBoxEstado) {
        this.ComboBoxEstado = ComboBoxEstado;
    }

    public JComboBox getComboBoxLogradouro() {
        return ComboBoxLogradouro;
    }

    public void setComboBoxLogradouro(JComboBox ComboBoxLogradouro) {
        this.ComboBoxLogradouro = ComboBoxLogradouro;
    }

    public JComboBox getComboBoxUF() {
        return ComboBoxUF;
    }

    public JCheckBox getCheckBoxEndereco() {
        return CheckBoxEndereco;
    }

    public void setCheckBoxEndereco(JCheckBox CheckBoxEndereco) {
        this.CheckBoxEndereco = CheckBoxEndereco;
    }

    public void setComboBoxUF(JComboBox ComboBoxUF) {
        this.ComboBoxUF = ComboBoxUF;
    }

    public JRadioButton getRadioButtonEditar() {
        return RadioButtonEditar;
    }

    public void setRadioButtonEditar(JRadioButton RadioButtonEditar) {
        this.RadioButtonEditar = RadioButtonEditar;
    }

    public JRadioButton getRadioButtonSenha() {
        return RadioButtonSenha;
    }

    public void setRadioButtonSenha(JRadioButton RadioButtonSenha) {
        this.RadioButtonSenha = RadioButtonSenha;
    }

    public JButton getBotaoSalvarAlteracao() {
        return BotaoSalvarAlteracao;
    }

    public void setBotaoSalvarAlteracao(JButton BotaoSalvarAlteracao) {
        this.BotaoSalvarAlteracao = BotaoSalvarAlteracao;
    }

    public JComboBox getComboBoxCidade() {
        return ComboBoxCidade;
    }

    public void setComboBoxCidade(JComboBox ComboBoxCidade) {
        this.ComboBoxCidade = ComboBoxCidade;
    }

    public JComboBox getComboBoxBairro() {
        return ComboBoxBairro;
    }

    public void setComboBoxBairro(JComboBox ComboBoxBairro) {
        this.ComboBoxBairro = ComboBoxBairro;
    }

    public int getEstadoSelecionado() {
        return estadoSelecionado;
    }

    public void setEstadoSelecionado(int estadoSelecionado) {
        this.estadoSelecionado = estadoSelecionado;
    }

    public int getCidadeSelecionada() {
        return cidadeSelecionada;
    }

    public void setCidadeSelecionada(int cidadeSelecionada) {
        this.cidadeSelecionada = cidadeSelecionada;
    }

    public int getBairroSelecionado() {
        return bairroSelecionado;
    }

    public void setBairroSelecionado(int bairroSelecionado) {
        this.bairroSelecionado = bairroSelecionado;
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotaoAtualizarCEP;
    private javax.swing.JButton BotaoDesfazerAlteracao;
    private javax.swing.JButton BotaoSalvarAlteracao;
    private javax.swing.JTextField CampoTextoCEP;
    private javax.swing.JTextField CampoTextoCPF;
    private javax.swing.JTextField CampoTextoComplemento;
    private javax.swing.JPasswordField CampoTextoConfirmarSenha;
    private javax.swing.JTextField CampoTextoNome;
    private javax.swing.JTextField CampoTextoNumero;
    private javax.swing.JTextArea CampoTextoObservacao;
    private javax.swing.JPasswordField CampoTextoSenha;
    private javax.swing.JTextField CampoTextoTelefone;
    private javax.swing.JCheckBox CheckBoxAdm;
    private javax.swing.JCheckBox CheckBoxEndereco;
    private javax.swing.JComboBox ComboBoxBairro;
    private javax.swing.JComboBox ComboBoxCidade;
    private javax.swing.JComboBox ComboBoxEstado;
    private javax.swing.JComboBox ComboBoxLogradouro;
    private javax.swing.JComboBox ComboBoxUF;
    private javax.swing.JRadioButton RadioButtonEditar;
    private javax.swing.JRadioButton RadioButtonSenha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
