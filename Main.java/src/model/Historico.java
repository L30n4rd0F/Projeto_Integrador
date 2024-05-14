package model;

import java.util.Date;
import java.sql.Timestamp;

public class Historico {

    private int id_historico;
    private Date data;
    private Timestamp tempo;
    private float precoTotal;
    private int fk_id_cliente;
    private int fk_id_usuario;
    private String nome_cliente;
    private String nome_funcionario;
    private String date;
    private String time;
    private String metodo_pagamento;

    public Historico(float precoTotal, int fk_id_usuario, int fk_id_cliente) {
        this.data = new Date(); // Cria a data atual
        this.tempo = new Timestamp(System.currentTimeMillis()); // Cria o timestamp atual
        this.precoTotal = precoTotal;
        this.fk_id_cliente = fk_id_cliente;
        this.fk_id_usuario = fk_id_usuario;
    }

    public Historico(int id_historico, float precoTotal, String nome_cliente, String nome_funcionario, String date, String time, String metodo_pagamento) {
        this.id_historico = id_historico;
        this.precoTotal = precoTotal;
        this.nome_cliente = nome_cliente;
        this.nome_funcionario = nome_funcionario;
        this.date = date;
        this.time = time;
        this.metodo_pagamento = metodo_pagamento;
    }


    public String getMetodo_pagamento() {
        return metodo_pagamento;
    }

    public void setMetodo_pagamento(String metodo_pagamento) {
        this.metodo_pagamento = metodo_pagamento;
    }
    
    

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getNome_funcionario() {
        return nome_funcionario;
    }

    public void setNome_funcionario(String nome_funcionario) {
        this.nome_funcionario = nome_funcionario;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    

    public int getId_historico() {
        return id_historico;
    }

    public void setId_historico(int id_historico) {
        this.id_historico = id_historico;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Timestamp getTempo() {
        return tempo;
    }

    public void setTempo(Timestamp tempo) {
        this.tempo = tempo;
    }

    public float getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(float precoTotal) {
        this.precoTotal = precoTotal;
    }

    public int getFk_id_cliente() {
        return fk_id_cliente;
    }

    public void setFk_id_cliente(int fk_id_cliente) {
        this.fk_id_cliente = fk_id_cliente;
    }

    public int getFk_id_usuario() {
        return fk_id_usuario;
    }

    public void setFk_id_usuario(int fk_id_usuario) {
        this.fk_id_usuario = fk_id_usuario;
    }
    
    

}
