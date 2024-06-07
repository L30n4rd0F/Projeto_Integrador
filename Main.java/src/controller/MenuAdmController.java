/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.AdmView;

/**
 *
 * @author luizf
 */
public class MenuAdmController {
    
    private AdmView view;

    
    public MenuAdmController(AdmView view) {
        this.view = view;
    }

    public void menuPrincipal(){
        view.getMenuPrincipal().setVisible(true);
        view.getUsuarioPane().setVisible(false);
        view.getProdutoPane().setVisible(false);
        view.getClientePane().setVisible(false);
        view.getHistoricoPane().setVisible(false);
        view.getVendaPane().setVisible(false);
    }
    
    public void usuarioPane(){
        view.getUsuarioPane().setVisible(true);
        view.getMenuPrincipal().setVisible(false);
        view.getProdutoPane().setVisible(false);
        view.getClientePane().setVisible(false);
        view.getHistoricoPane().setVisible(false);
        view.getVendaPane().setVisible(false);
    }
    
    public void produtoPane(){
        view.getProdutoPane().setVisible(true);
        view.getUsuarioPane().setVisible(false);
        view.getMenuPrincipal().setVisible(false);
        view.getClientePane().setVisible(false); 
        view.getHistoricoPane().setVisible(false);
        view.getVendaPane().setVisible(false);
        try {
            view.getProdutoPane().getController().readTabelaProdutoPane();
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Erro na leitura da tabela do Banco de Dados", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void clientePane(){
        view.getClientePane().setVisible(true);
        view.getClientePane().refresh();
        view.getProdutoPane().setVisible(false);
        view.getUsuarioPane().setVisible(false);
        view.getMenuPrincipal().setVisible(false);
        view.getHistoricoPane().setVisible(false);
        view.getVendaPane().setVisible(false);
    }
    
    public void historicoPane(){
        view.getHistoricoPane().setVisible(true);
        view.getClientePane().setVisible(false);  
        view.getProdutoPane().setVisible(false);
        view.getUsuarioPane().setVisible(false);
        view.getMenuPrincipal().setVisible(false);
        view.getVendaPane().setVisible(false);
        view.getHistoricoPane().setTabelaHistorico();
    }
    
    public void vendaPane(){
        view.getHistoricoPane().setVisible(false);
        view.getClientePane().setVisible(false);  
        view.getProdutoPane().setVisible(false);
        view.getUsuarioPane().setVisible(false);
        view.getMenuPrincipal().setVisible(false);
        view.getVendaPane().setVisible(true);
        view.getVendaPane().setTabelaProduto();
    }
        
}