/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import view.FuncionarioView;

/**
 *
 * @author Lucas
 */
public class MenuUsuarioController {
      
     private FuncionarioView view;
     
     public MenuUsuarioController(FuncionarioView view){
         this.view = view;
     }

    public void menuPrincipal() {
        
        view.getMenuPrincipal().setVisible(true);
        view.getUsuarioPane().setVisible(false);
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
    }
    
    public void historicoPane(){
        view.getHistoricoPane().setVisible(true);
        view.getClientePane().setVisible(false);  
        view.getProdutoPane().setVisible(false);
        view.getUsuarioPane().setVisible(false);
        view.getMenuPrincipal().setVisible(false);
        view.getVendaPane().setVisible(false);
    }
    
    public void vendaPane(){
        view.getHistoricoPane().setVisible(false);
        view.getClientePane().setVisible(false);  
        view.getProdutoPane().setVisible(false);
        view.getUsuarioPane().setVisible(false);
        view.getMenuPrincipal().setVisible(false);
        view.getVendaPane().setVisible(true);
    }
    
}
