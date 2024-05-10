
package controller;

public class TextoController {
    
    //Verificação do campo de texto CPF, se é possível digitar ele retorna true, caso contrário retorna false
    public boolean formatacaoCPF(java.awt.event.KeyEvent evt, String cpf){
        return (limiteCPF(cpf) && apenasNumero(evt));
    }
    
    //Verifica se o que esta sendo digitado é um numero
    public boolean apenasNumero(java.awt.event.KeyEvent evt){
        String caracteresPermitidos = "0123456789";
        return caracteresPermitidos.contains(evt.getKeyChar() + ""); 
        
    }
    
    //Define limite para campo do CPF
    public boolean limiteCPF(String cpf){
        return cpf.length()<=13;
     
    }
    
    //Cria uma espécie de mascara para o cpf
    public String mascaraCPF(String cpf){
        int tamanho = cpf.length();
        
        if(tamanho == 3 || tamanho == 7){
            cpf = cpf + ".";
        }
        if(tamanho == 11){
            cpf = cpf + "-";
        }
        return cpf;
     }
    
    //Verificação do campo de texto telefone, se é possível digitar ele retorna true, caso contrário retorna false
    public boolean formatacaoTelefone(java.awt.event.KeyEvent evt, String telefone){
        return (limiteTelefone(telefone) && apenasNumero(evt));
    }
    
    //Verifica se o limite do telefone não foi batido
    public boolean limiteTelefone(String telefone){
        return telefone.length()<=14;
    }
    
    //Cria uma espécie de máscara para o telefone
    public String mascaraTelefone(String telefone){
        int tamanho = telefone.length();
        
        if(tamanho==0){
            telefone = "(" + telefone;
        }
        if(tamanho==3){
            telefone = telefone + ")" + " ";
        }
        if(tamanho==10){
            telefone = telefone + "-";
        }
        return telefone;
    }
    
    public boolean formatacaoCEP(java.awt.event.KeyEvent evt, String cep){
        return (limiteCEP(cep) && apenasNumero(evt));
    }
    
    private boolean limiteCEP(String cep){
        return cep.length()<=8;
    }
    
    public String mascaraCEP(String cep){
        int tamanho = cep.length();
        
        if(tamanho==5){
            cep += "-";
        }
        return cep;
    }

}
