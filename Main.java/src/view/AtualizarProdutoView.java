package view;

import controller.ProdutoController;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.Produto;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public final class AtualizarProdutoView extends javax.swing.JFrame {

    private final ProdutoController controller;

    public AtualizarProdutoView() {
        initComponents();

        this.controller = new ProdutoController(this);

        controller.apagarTodosCamposAtualizar();

        this.setLocationRelativeTo(null);
        AutoCompleteDecorator.decorate(ComboBoxCategoria);
        AutoCompleteDecorator.decorate(ComboBoxUnidade);

    }

    public void produto(String produtoSelecionado) {
        try {
            controller.readCategoriasAtualizarProduto();
            Produto pr = controller.readProdutosSelecionados(produtoSelecionado);
            getCampoDescricao().setText(pr.getDescricao());
            getCampoNomeProduto().setText(pr.getNome());
            getComboBoxCategoria().setSelectedItem(pr.getCategoria());
            getCampoQuantidade().setText(String.valueOf(pr.getQuantidade()));
            getComboBoxUnidade().setSelectedItem(pr.getUnidade());
            getCampoPreco().setText(String.valueOf(pr.getPreco()));
            getCampoIdProduto().setText(String.valueOf(pr.getId_produto()));
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarProdutoView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public JButton getBotaoAtualizarProduto() {
        return BotaoAtualizarProduto;
    }

    public void setBotaoAtualizarProduto(JButton BotaoAtualizarProduto) {
        this.BotaoAtualizarProduto = BotaoAtualizarProduto;
    }

    public JButton getBotaoCancelar() {
        return BotaoCancelar;
    }

    public void setBotaoCancelar(JButton BotaoCancelar) {
        this.BotaoCancelar = BotaoCancelar;
    }

    public JTextArea getCampoDescricao() {
        return CampoDescricao;
    }

    public void setCampoDescricao(JTextArea CampoDescricao) {
        this.CampoDescricao = CampoDescricao;
    }

    public JTextField getCampoNomeProduto() {
        return CampoNomeProduto;
    }

    public void setCampoNomeProduto(JTextField CampoNomeProduto) {
        this.CampoNomeProduto = CampoNomeProduto;
    }

    public JTextField getCampoPreco() {
        return CampoPreco;
    }

    public void setCampoPreco(JTextField CampoPreco) {
        this.CampoPreco = CampoPreco;
    }

    public JTextField getCampoQuantidade() {
        return CampoQuantidade;
    }

    public void setCampoQuantidade(JTextField CampoQuantidade) {
        this.CampoQuantidade = CampoQuantidade;
    }

    public JComboBox<String> getComboBoxCategoria() {
        return ComboBoxCategoria;
    }

    public void setComboBoxCategoria(JComboBox<String> ComboBoxCategoria) {
        this.ComboBoxCategoria = ComboBoxCategoria;
    }

    public JComboBox<String> getComboBoxUnidade() {
        return ComboBoxUnidade;
    }

    public void setComboBoxUnidade(JComboBox<String> ComboBoxUnidade) {
        this.ComboBoxUnidade = ComboBoxUnidade;
    }

    public JTextField getCampoIdProduto() {
        return CampoIdProduto;
    }

    public void setCampoIdProduto(JTextField CampoIdProduto) {
        this.CampoIdProduto = CampoIdProduto;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        CampoDescricao = new javax.swing.JTextArea();
        CampoNomeProduto = new javax.swing.JTextField();
        ComboBoxCategoria = new javax.swing.JComboBox<>();
        BotaoAtualizarProduto = new javax.swing.JButton();
        CampoQuantidade = new javax.swing.JTextField();
        BotaoCancelar = new javax.swing.JButton();
        ComboBoxUnidade = new javax.swing.JComboBox<>();
        CampoPreco = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        CampoIdProduto = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Atualizar Produto");

        jLabel7.setText("Descrição");

        jLabel2.setText("Nome");

        CampoDescricao.setColumns(20);
        CampoDescricao.setRows(5);
        jScrollPane1.setViewportView(CampoDescricao);

        ComboBoxCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboBoxCategoria.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                ComboBoxCategoriaAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        BotaoAtualizarProduto.setText("Atualizar Produto");
        BotaoAtualizarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoAtualizarProdutoActionPerformed(evt);
            }
        });

        BotaoCancelar.setText("Cancelar");
        BotaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoCancelarActionPerformed(evt);
            }
        });

        ComboBoxUnidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Unitario", "Centimetros(cm)"}));

        jLabel3.setText("Categoria");

        jLabel4.setText("Quantidade");

        jLabel5.setText("Unidade");

        jLabel6.setText("Preço R$");

        jLabel8.setText("Id do Produto");

        CampoIdProduto.setEnabled(false);
        CampoIdProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoIdProdutoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(CampoNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ComboBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(BotaoAtualizarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(BotaoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(CampoIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CampoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(ComboBoxUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CampoPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel6)))))))
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8)
                    .addComponent(CampoIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CampoNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CampoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboBoxUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CampoPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 15, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BotaoAtualizarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(158, 158, 158))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ComboBoxCategoriaAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_ComboBoxCategoriaAncestorAdded
        try {
            controller.readCategoriasAtualizarProduto();
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarProdutoView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ComboBoxCategoriaAncestorAdded

    private void BotaoAtualizarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoAtualizarProdutoActionPerformed
        try {
            controller.atualizarProduto();
            controller.readCategoriasAtualizarProduto();
        } catch (SQLException ex) {
            Logger.getLogger(AtualizarProdutoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        controller.apagarTodosCamposAtualizar();
        this.dispose();
    }//GEN-LAST:event_BotaoAtualizarProdutoActionPerformed

    private void BotaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoCancelarActionPerformed
        controller.apagarTodosCamposAtualizar();
        this.dispose();
        try {
            controller.readTabelaProdutoPane();
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarProdutoView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BotaoCancelarActionPerformed

    private void CampoIdProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoIdProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoIdProdutoActionPerformed

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
            java.util.logging.Logger.getLogger(AtualizarProdutoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AtualizarProdutoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AtualizarProdutoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AtualizarProdutoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AtualizarProdutoView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotaoAtualizarProduto;
    private javax.swing.JButton BotaoCancelar;
    private javax.swing.JTextArea CampoDescricao;
    private javax.swing.JTextField CampoIdProduto;
    private javax.swing.JTextField CampoNomeProduto;
    private javax.swing.JTextField CampoPreco;
    private javax.swing.JTextField CampoQuantidade;
    private javax.swing.JComboBox<String> ComboBoxCategoria;
    private javax.swing.JComboBox<String> ComboBoxUnidade;
    private javax.swing.JLabel jLabel1;
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
