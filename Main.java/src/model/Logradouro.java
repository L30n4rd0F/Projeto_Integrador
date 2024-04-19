
package model;


public class Logradouro {
    private int id_logradouro;
    private int id_cidade;
    private int id_bairro;
    private String cep;
    private String nome;
    private String uf;

    
    
    
    public int getId_logradouro() {
        return id_logradouro;
    }

    public void setId_logradouro(int id_logradouro) {
        this.id_logradouro = id_logradouro;
    }

    public int getId_cidade() {
        return id_cidade;
    }

    public void setId_cidade(int id_cidade) {
        this.id_cidade = id_cidade;
    }

    public int getId_bairro() {
        return id_bairro;
    }

    public void setId_bairro(int id_bairro) {
        this.id_bairro = id_bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
    
    
    
    
    
}
