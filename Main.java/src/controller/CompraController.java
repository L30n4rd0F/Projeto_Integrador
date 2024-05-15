
package controller;

import dao.CompraDAO;
import view.HistoricoInformacoesView;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.Compra;

public class CompraController {
    
    private HistoricoInformacoesView view;

    public CompraController(HistoricoInformacoesView view) {
        this.view = view;
    }
    
    public void readTabelaCompra(int id_historico) throws SQLException {
        DefaultTableModel modelo = (DefaultTableModel) view.getTabelaCompra().getModel();
        modelo.setNumRows(0);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
        view.getTabelaCompra().setRowSorter(sorter);

        for (Compra compra : new CompraDAO().readCompra(id_historico)) {
            modelo.addRow(new Object[]{
                compra.getNome_produto(),
                compra.getPreco(),
                compra.getQuantidade(),
                compra.getUnidade(),
            });
        }
        
    }
}
