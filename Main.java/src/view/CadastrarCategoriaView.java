
package view;

import controller.ProdutoController;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class CadastrarCategoriaView extends javax.swing.JFrame {
    
    private final ProdutoController controller;

    public CadastrarCategoriaView() {
        initComponents();
        
        this.controller = new ProdutoController(this);
        
        
        controller.apagarTodosCamposCategoria();
        
        try {
            controller.readCategoriasCadastroCategoria();
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarCategoriaView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public JButton getBotaoRemoverCategoria() {
        return BotaoRemoverCategoria;
    }

    public void setBotaoRemoverCategoria(JButton BotaoRemoverCategoria) {
        this.BotaoRemoverCategoria = BotaoRemoverCategoria;
    }
    
    public JButton getBotaoCadastrarCategoria() {
        return BotaoCadastrarCategoria;
    }

    public void setBotaoCadastrarCategoria(JButton BotaoCadastrarCategoria) {
        this.BotaoCadastrarCategoria = BotaoCadastrarCategoria;
    }

    public JButton getBotaoConcluido() {
        return BotaoConcluido;
    }

    public void setBotaoConcluido(JButton BotaoConcluido) {
        this.BotaoConcluido = BotaoConcluido;
    }

    public JTextField getCampoCategoria() {
        return CampoCategoria;
    }

    public void setCampoCategoria(JTextField CampoCategoria) {
        this.CampoCategoria = CampoCategoria;
    }

    public JButton getCancelar() {
        return Cancelar;
    }

    public void setCancelar(JButton Cancelar) {
        this.Cancelar = Cancelar;
    }

    public JComboBox<String> getComboBoxCategoria() {
        return ComboBoxCategoria;
    }

    public void setComboBoxCategoria(JComboBox<String> ComboBoxCategoria) {
        this.ComboBoxCategoria = ComboBoxCategoria;
    }


    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        CampoCategoria = new javax.swing.JTextField();
        BotaoCadastrarCategoria = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        ComboBoxCategoria = new javax.swing.JComboBox<>();
        BotaoConcluido = new javax.swing.JButton();
        BotaoRemoverCategoria = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Cadastrar Categoria para Produto(s)");

        BotaoCadastrarCategoria.setText("Cadastrar");
        BotaoCadastrarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoCadastrarCategoriaActionPerformed(evt);
            }
        });

        Cancelar.setText("Cancelar");
        Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarActionPerformed(evt);
            }
        });

        jLabel2.setText("Remover Categoria para Produto(s)");

        ComboBoxCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        BotaoConcluido.setText("Concluido");
        BotaoConcluido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoConcluidoActionPerformed(evt);
            }
        });

        BotaoRemoverCategoria.setText("Remover");
        BotaoRemoverCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoRemoverCategoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(CampoCategoria, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(BotaoCadastrarCategoria)
                            .addGap(18, 18, 18)
                            .addComponent(Cancelar))))
                .addGap(103, 103, 103)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ComboBoxCategoria, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BotaoRemoverCategoria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BotaoConcluido)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CampoCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotaoCadastrarCategoria)
                    .addComponent(Cancelar)
                    .addComponent(BotaoConcluido)
                    .addComponent(BotaoRemoverCategoria))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActionPerformed
        try {
            controller.readTabelaProdutoPane();
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarCategoriaView.class.getName()).log(Level.SEVERE, null, ex);
        }
        controller.apagarTodosCamposCategoria();
        this.dispose();
    }//GEN-LAST:event_CancelarActionPerformed

    private void BotaoConcluidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoConcluidoActionPerformed
        try {
            controller.readTabelaProdutoPane();
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarCategoriaView.class.getName()).log(Level.SEVERE, null, ex);
        }
        controller.apagarTodosCamposCategoria();
        this.dispose();
    }//GEN-LAST:event_BotaoConcluidoActionPerformed

    private void BotaoRemoverCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoRemoverCategoriaActionPerformed
        try {
            controller.removerCategoria(getComboBoxCategoria().getSelectedItem().toString());
            controller.readCategoriasCadastroCategoria();
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarCategoriaView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BotaoRemoverCategoriaActionPerformed

    private void BotaoCadastrarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoCadastrarCategoriaActionPerformed
        try {
            controller.cadastrarCategoria(getCampoCategoria().getText());
            controller.readTabelaProdutoPane();
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarCategoriaView.class.getName()).log(Level.SEVERE, null, ex);
        }
        controller.apagarTodosCamposCategoria();
    }//GEN-LAST:event_BotaoCadastrarCategoriaActionPerformed


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
            java.util.logging.Logger.getLogger(CadastrarCategoriaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastrarCategoriaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastrarCategoriaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastrarCategoriaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastrarCategoriaView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotaoCadastrarCategoria;
    private javax.swing.JButton BotaoConcluido;
    private javax.swing.JButton BotaoRemoverCategoria;
    private javax.swing.JTextField CampoCategoria;
    private javax.swing.JButton Cancelar;
    private javax.swing.JComboBox<String> ComboBoxCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
