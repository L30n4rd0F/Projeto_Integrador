package controller;

import dao.HistoricoDAO;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.Historico;
import view.HistoricoPane;
import java.sql.SQLException;

public class HistoricoController {

    private HistoricoPane view;

    public HistoricoController(HistoricoPane view) {
        this.view = view;
    }

    public void readTabelaHistorico() throws SQLException {
        DefaultTableModel modelo = (DefaultTableModel) view.getTabelaHistorico().getModel();
        modelo.setNumRows(0);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
        view.getTabelaHistorico().setRowSorter(sorter);

        for (Historico historico : new HistoricoDAO().readHistorico()) {
            modelo.addRow(new Object[]{
                historico.getDate(),
                historico.getTime(),
                historico.getPrecoTotal(),
                historico.getMetodo_pagamento(),
                historico.getNome_cliente(),
                historico.getNome_funcionario(),
            });
        }
        
    }
}
