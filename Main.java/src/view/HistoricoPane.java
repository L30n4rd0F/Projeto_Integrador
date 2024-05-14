
package view;

import controller.HistoricoController;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HistoricoPane extends javax.swing.JPanel {
    
    private final HistoricoController controller;

    public HistoricoPane() {
        initComponents();
        
        this.controller = new HistoricoController(this);

        try {
            controller.readTabelaHistorico();
        } catch (SQLException ex) {
            Logger.getLogger(HistoricoPane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public JButton getBotaoApagarCampos() {
        return BotaoApagarCampos;
    }

    public void setBotaoApagarCampos(JButton BotaoApagarCampos) {
        this.BotaoApagarCampos = BotaoApagarCampos;
    }

    public JButton getBotaoVerInformacao() {
        return BotaoVerInformacao;
    }

    public void setBotaoVerInformacao(JButton BotaoVerInformacao) {
        this.BotaoVerInformacao = BotaoVerInformacao;
    }

    public JTextField getCampoId() {
        return CampoId;
    }

    public void setCampoId(JTextField CampoId) {
        this.CampoId = CampoId;
    }

    public JTextField getCampoTextoPesquisaCliente() {
        return CampoTextoPesquisaCliente;
    }

    public void setCampoTextoPesquisaCliente(JTextField CampoTextoPesquisaCliente) {
        this.CampoTextoPesquisaCliente = CampoTextoPesquisaCliente;
    }

    public JTextField getCampoTextoPesquisaVendedor() {
        return CampoTextoPesquisaVendedor;
    }

    public void setCampoTextoPesquisaVendedor(JTextField CampoTextoPesquisaVendedor) {
        this.CampoTextoPesquisaVendedor = CampoTextoPesquisaVendedor;
    }

    public JTable getTabelaHistorico() {
        return TabelaHistorico;
    }

    public void setTabelaHistorico(JTable TabelaHistorico) {
        this.TabelaHistorico = TabelaHistorico;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TabelaHistorico = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        CampoId = new javax.swing.JTextField();
        BotaoApagarCampos = new javax.swing.JButton();
        CampoTextoPesquisaVendedor = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        CampoTextoPesquisaCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        BotaoVerInformacao = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        TabelaHistorico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Data", "Tempo", "Preço Total", "Método de pagamento", "Cliente", "Funcionário"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TabelaHistorico);
        if (TabelaHistorico.getColumnModel().getColumnCount() > 0) {
            TabelaHistorico.getColumnModel().getColumn(0).setResizable(false);
            TabelaHistorico.getColumnModel().getColumn(1).setResizable(false);
            TabelaHistorico.getColumnModel().getColumn(2).setResizable(false);
            TabelaHistorico.getColumnModel().getColumn(3).setResizable(false);
            TabelaHistorico.getColumnModel().getColumn(4).setResizable(false);
            TabelaHistorico.getColumnModel().getColumn(5).setResizable(false);
        }

        jLabel2.setText("Id");

        CampoId.setEditable(false);
        CampoId.setEnabled(false);

        BotaoApagarCampos.setText("Limpar Campos");
        BotaoApagarCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoApagarCamposActionPerformed(evt);
            }
        });

        jLabel1.setText("Vendedor");

        jLabel3.setText("Cliente");

        BotaoVerInformacao.setText("Ver mais informações");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BotaoVerInformacao, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CampoId, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1)
                                    .addComponent(CampoTextoPesquisaVendedor)
                                    .addComponent(BotaoApagarCampos, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CampoTextoPesquisaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(50, 50, 50))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CampoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CampoTextoPesquisaVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CampoTextoPesquisaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(BotaoApagarCampos)
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addGap(45, 45, 45)
                .addComponent(BotaoVerInformacao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BotaoApagarCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoApagarCamposActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoApagarCamposActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotaoApagarCampos;
    private javax.swing.JButton BotaoVerInformacao;
    private javax.swing.JTextField CampoId;
    private javax.swing.JTextField CampoTextoPesquisaCliente;
    private javax.swing.JTextField CampoTextoPesquisaVendedor;
    private javax.swing.JTable TabelaHistorico;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
