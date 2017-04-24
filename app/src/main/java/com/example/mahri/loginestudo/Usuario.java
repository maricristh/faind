package com.example.mahri.loginestudo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mahri on 23/04/2017.
 */

public class Usuario {
    @SerializedName("id")
    private long id;
    @SerializedName("nome")
    private String nome;

    public long getId() {
        return id;
    }
    public void setCiditem(Long ciditem) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}