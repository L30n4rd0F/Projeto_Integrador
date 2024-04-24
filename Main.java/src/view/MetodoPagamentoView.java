package view;

import controller.ProdutoController;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import static view.VendaPane.concluirVenda;

public class MetodoPagamentoView extends javax.swing.JFrame {

    private ProdutoController controller = null;

    public MetodoPagamentoView() {
        initComponents();

        buttonGroup1.add(RadioButtonDinheiro);
        buttonGroup1.add(RadioButtonCredito);
        buttonGroup1.add(RadioButtonPix);
        buttonGroup1.add(RadioButtonDebito);
    }

    public ProdutoController getController() {
        return controller;
    }

    public void setController(ProdutoController controller) {
        this.controller = controller;
    }

    public JButton getBotaoConfirmarVenda() {
        return BotaoConfirmarVenda;
    }

    public void setBotaoConfirmarVenda(JButton BotaoConfirmarVenda) {
        this.BotaoConfirmarVenda = BotaoConfirmarVenda;
    }

    public JRadioButton getRadioButtonCredito() {
        return RadioButtonCredito;
    }

    public void setRadioButtonCredito(JRadioButton RadioButtonCredito) {
        this.RadioButtonCredito = RadioButtonCredito;
    }

    public JRadioButton getRadioButtonDebito() {
        return RadioButtonDebito;
    }

    public void setRadioButtonDebito(JRadioButton RadioButtonDebito) {
        this.RadioButtonDebito = RadioButtonDebito;
    }

    public JRadioButton getRadioButtonDinheiro() {
        return RadioButtonDinheiro;
    }

    public void setRadioButtonDinheiro(JRadioButton RadioButtonDinheiro) {
        this.RadioButtonDinheiro = RadioButtonDinheiro;
    }

    public JRadioButton getRadioButtonPix() {
        return RadioButtonPix;
    }

    public void setRadioButtonPix(JRadioButton RadioButtonPix) {
        this.RadioButtonPix = RadioButtonPix;
    }

    public ButtonGroup getButtonGroup1() {
        return buttonGroup1;
    }

    public void setButtonGroup1(ButtonGroup buttonGroup1) {
        this.buttonGroup1 = buttonGroup1;
    }

    public JTextField getCampoDinherio() {
        return CampoDinherio;
    }

    public void setCampoDinherio(JTextField CampoDinherio) {
        this.CampoDinherio = CampoDinherio;
    }

    public JTextField getCampoTroco() {
        return CampoTroco;
    }

    public void setCampoTroco(JTextField CampoTroco) {
        this.CampoTroco = CampoTroco;
    }

    public JTextField getCampoValorTotal() {
        return CampoValorTotal;
    }

    public void setCampoValorTotal(JTextField CampoValorTotal) {
        this.CampoValorTotal = CampoValorTotal;
    }

    public JComboBox<String> getComboBoxParcela() {
        return ComboBoxParcela;
    }

    public void setComboBoxParcela(JComboBox<String> ComboBoxParcela) {
        this.ComboBoxParcela = ComboBoxParcela;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        CampoValorTotal = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        CampoDinherio = new javax.swing.JTextField();
        CampoTroco = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        ComboBoxParcela = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        RadioButtonDinheiro = new javax.swing.JRadioButton();
        BotaoConfirmarVenda = new javax.swing.JButton();
        RadioButtonCredito = new javax.swing.JRadioButton();
        RadioButtonPix = new javax.swing.JRadioButton();
        RadioButtonDebito = new javax.swing.JRadioButton();

        jLabel9.setText("jLabel9");

        jRadioButton2.setText("jRadioButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Escolha o método de pagamento:");

        CampoValorTotal.setEnabled(false);
        CampoValorTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoValorTotalActionPerformed(evt);
            }
        });

        jLabel7.setText("Valor total R$");

        CampoDinherio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CampoDinherioKeyReleased(evt);
            }
        });

        CampoTroco.setEnabled(false);
        CampoTroco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoTrocoActionPerformed(evt);
            }
        });

        jLabel8.setText("Troco");

        ComboBoxParcela.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1x", "2x", "3x", "4x" , "5x" , "6x" }));

        jLabel10.setText("Parcelas");

        RadioButtonDinheiro.setText("Dinheiro");
        RadioButtonDinheiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButtonDinheiroActionPerformed(evt);
            }
        });

        BotaoConfirmarVenda.setText("Confirmar Venda");
        BotaoConfirmarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoConfirmarVendaActionPerformed(evt);
            }
        });

        RadioButtonCredito.setText("Crédito");

        RadioButtonPix.setText("Pix");

        RadioButtonDebito.setText("Débito");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(RadioButtonDinheiro)
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(RadioButtonCredito)
                                .addGap(72, 72, 72)
                                .addComponent(jLabel4))
                            .addComponent(jLabel10)
                            .addComponent(ComboBoxParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RadioButtonDebito)
                            .addComponent(BotaoConfirmarVenda)
                            .addComponent(RadioButtonPix)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(CampoTroco, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                .addComponent(CampoDinherio, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(161, 161, 161)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(CampoValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CampoValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(RadioButtonDinheiro)
                    .addComponent(RadioButtonCredito)
                    .addComponent(RadioButtonPix))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CampoDinherio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(RadioButtonDebito))
                .addGap(13, 13, 13)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CampoTroco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboBoxParcela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoConfirmarVenda))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CampoValorTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoValorTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoValorTotalActionPerformed

    private void CampoTrocoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoTrocoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoTrocoActionPerformed

    private void RadioButtonDinheiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButtonDinheiroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RadioButtonDinheiroActionPerformed

    private void BotaoConfirmarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoConfirmarVendaActionPerformed
        Enumeration<AbstractButton> buttons = buttonGroup1.getElements();
        int contador = 0;
        while (buttons.hasMoreElements()) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                try {
                    concluirVenda(button.getText(), controller);
                    getCampoValorTotal().setText("");
                    getCampoTroco().setText("");
                    getCampoDinherio().setText("");
                    this.dispose();
                    return;
                } catch (SQLException ex) {
                    Logger.getLogger(MetodoPagamentoView.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                contador++;
                if (contador >= 4) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione um método de pagamento.");
                }
            }
        }

    }//GEN-LAST:event_BotaoConfirmarVendaActionPerformed

    private void CampoDinherioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CampoDinherioKeyReleased
        String pagamento;
        String valorTotal;
        pagamento = getCampoDinherio().getText();
        valorTotal = getCampoValorTotal().getText();
        valorTotal = valorTotal.replace(',', '.');
        pagamento = pagamento.replace(',', '.');
        float total = Float.parseFloat(pagamento) - Float.parseFloat(valorTotal);
        String totalFormatado = String.format("%.2f", total).replace('.', ',');
        getCampoTroco().setText(totalFormatado);
    }//GEN-LAST:event_CampoDinherioKeyReleased

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
            java.util.logging.Logger.getLogger(MetodoPagamentoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MetodoPagamentoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MetodoPagamentoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MetodoPagamentoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MetodoPagamentoView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotaoConfirmarVenda;
    private javax.swing.JTextField CampoDinherio;
    private javax.swing.JTextField CampoTroco;
    private javax.swing.JTextField CampoValorTotal;
    private javax.swing.JComboBox<String> ComboBoxParcela;
    private javax.swing.JRadioButton RadioButtonCredito;
    private javax.swing.JRadioButton RadioButtonDebito;
    private javax.swing.JRadioButton RadioButtonDinheiro;
    private javax.swing.JRadioButton RadioButtonPix;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton jRadioButton2;
    // End of variables declaration//GEN-END:variables
}
