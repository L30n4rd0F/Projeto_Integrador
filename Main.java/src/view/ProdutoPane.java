
package view;

import controller.ProdutoController;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class ProdutoPane extends javax.swing.JPanel {

    private final ProdutoController controller;
    CadastrarProdutoView viewCadastro = new CadastrarProdutoView();
    CadastrarCategoriaView viewCadastroCategoria = new CadastrarCategoriaView();
    
    public ProdutoPane() {
        initComponents();
        AutoCompleteDecorator.decorate(ComboBoxCategoria);
        
        this.controller = new ProdutoController(this);
        try {
            controller.readCategorias();
            controller.readTabelaProdutoPane();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoPane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public JButton getBotaoAtualizar() {
        return BotaoAtualizar;
    }

    public void setBotaoAtualizar(JButton BotaoAtualizar) {
        this.BotaoAtualizar = BotaoAtualizar;
    }

    public JButton getBotaoNovoProduto() {
        return BotaoNovoProduto;
    }

    public void setBotaoNovoProduto(JButton BotaoNovoProduto) {
        this.BotaoNovoProduto = BotaoNovoProduto;
    }

    public JButton getBotaoRemoverProduto() {
        return BotaoRemoverProduto;
    }

    public void setBotaoRemoverProduto(JButton BotaoRemoverProduto) {
        this.BotaoRemoverProduto = BotaoRemoverProduto;
    }

    public JTextField getCampoPesquisaNome() {
        return CampoPesquisaNome;
    }

    public void setCampoPesquisaNome(JTextField CampoPesquisaNome) {
        this.CampoPesquisaNome = CampoPesquisaNome;
    }

    public JComboBox<String> getComboBoxCategoria() {
        return ComboBoxCategoria;
    }

    public void setComboBoxCategoria(JComboBox<String> ComboBoxCategoria) {
        this.ComboBoxCategoria = ComboBoxCategoria;
    }

    public JTable getTabelaProduto() {
        return TabelaProduto;
    }

    public void setTabelaProduto(JTable TabelaProduto) {
        this.TabelaProduto = TabelaProduto;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CampoPesquisaNome = new javax.swing.JTextField();
        ComboBoxCategoria = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        BotaoNovoProduto = new javax.swing.JButton();
        BotaoRemoverProduto = new javax.swing.JButton();
        BotaoAtualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelaProduto = new javax.swing.JTable();
        BotaoCadastrarCategoria = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        CampoPesquisaNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoPesquisaNomeActionPerformed(evt);
            }
        });
        CampoPesquisaNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CampoPesquisaNomeKeyReleased(evt);
            }
        });

        ComboBoxCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboBoxCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxCategoriaActionPerformed(evt);
            }
        });

        jLabel1.setText("Buscar produto por nome:");

        jLabel2.setText("Buscar produto por categora:");

        BotaoNovoProduto.setText("Novo Produto");
        BotaoNovoProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoNovoProdutoActionPerformed(evt);
            }
        });

        BotaoRemoverProduto.setText("Remover Produto");
        BotaoRemoverProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoRemoverProdutoActionPerformed(evt);
            }
        });

        BotaoAtualizar.setText("Ver mais informações");
        BotaoAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoAtualizarActionPerformed(evt);
            }
        });

        TabelaProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nome", "Categoria", "Descrição", "Quantidade", "Unidade", "Preço"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Float.class
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
        TabelaProduto.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(TabelaProduto);
        if (TabelaProduto.getColumnModel().getColumnCount() > 0) {
            TabelaProduto.getColumnModel().getColumn(0).setResizable(false);
            TabelaProduto.getColumnModel().getColumn(1).setResizable(false);
            TabelaProduto.getColumnModel().getColumn(2).setResizable(false);
            TabelaProduto.getColumnModel().getColumn(3).setResizable(false);
            TabelaProduto.getColumnModel().getColumn(4).setResizable(false);
            TabelaProduto.getColumnModel().getColumn(5).setResizable(false);
        }

        BotaoCadastrarCategoria.setText("Cadastrar/Remover Categoria");
        BotaoCadastrarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoCadastrarCategoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(ComboBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(72, 72, 72)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(CampoPesquisaNome, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BotaoNovoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BotaoAtualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BotaoRemoverProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BotaoCadastrarCategoria)))
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CampoPesquisaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotaoNovoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoCadastrarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoRemoverProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(95, 95, 95))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ComboBoxCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxCategoriaActionPerformed
        if(getComboBoxCategoria().getSelectedIndex()>=0){
            int index = getComboBoxCategoria().getSelectedIndex(); 
            String nomeProduto = getComboBoxCategoria().getItemAt(index);
            try {
                controller.buscarPorCategoria(nomeProduto);
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoPane.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_ComboBoxCategoriaActionPerformed

    private void BotaoNovoProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoNovoProdutoActionPerformed
        viewCadastro.setLocationRelativeTo(null);
        viewCadastro.setVisible(true);
    }//GEN-LAST:event_BotaoNovoProdutoActionPerformed

    private void CampoPesquisaNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CampoPesquisaNomeKeyReleased
        try {
            controller.buscarProdutoPane(getCampoPesquisaNome().getText());
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoPane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_CampoPesquisaNomeKeyReleased

    private void BotaoAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoAtualizarActionPerformed
        try {
            controller.readTabelaProdutoPane();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoPane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BotaoAtualizarActionPerformed

    private void CampoPesquisaNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoPesquisaNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoPesquisaNomeActionPerformed

    private void BotaoCadastrarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoCadastrarCategoriaActionPerformed
        viewCadastroCategoria.setLocationRelativeTo(null);
        viewCadastroCategoria.setVisible(true);
    }//GEN-LAST:event_BotaoCadastrarCategoriaActionPerformed

    private void BotaoRemoverProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoRemoverProdutoActionPerformed
        try {
            controller.removerProduto(getTabelaProduto().getValueAt(getTabelaProduto().getSelectedRow(),1).toString());
            controller.readTabelaProdutoPane();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoPane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BotaoRemoverProdutoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotaoAtualizar;
    private javax.swing.JButton BotaoCadastrarCategoria;
    private javax.swing.JButton BotaoNovoProduto;
    private javax.swing.JButton BotaoRemoverProduto;
    private javax.swing.JTextField CampoPesquisaNome;
    private javax.swing.JComboBox<String> ComboBoxCategoria;
    private javax.swing.JTable TabelaProduto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
