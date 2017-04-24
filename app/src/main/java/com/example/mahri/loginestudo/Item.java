package com.example.mahri.loginestudo;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Vinicius on 20/04/2017.
 */

public class Item {
    @SerializedName("ciditem")
    private Long ciditem;
    @SerializedName("cnome")
    private String cnome;
    @SerializedName("cdescricao")
    private String cdescricao;
    @SerializedName("clocal")
    private String clocal;
    @SerializedName("cfoto")
    private String cfoto;
    @SerializedName("ctipo")
    private String ctipo;
    @SerializedName("cpalavrachave")
    private String cpalavrachave;
    @SerializedName("ccontato")
    private String ccontato;
    @SerializedName("cidusuario")
    private String cidusuario;
    @SerializedName("cauxiliar")
    private String cauxiliar;
    @SerializedName("cdataevento")
    private Date cdataevento;

    public Long getCiditem() {
        return ciditem;
    }

    public void setCiditem(Long ciditem) {
        this.ciditem = ciditem;
    }

    public String getCnome() {
        return cnome;
    }

    public void setCnome(String cnome) {
        this.cnome = cnome;
    }

    public String getCdescricao() {
        return cdescricao;
    }

    public void setCdescricao(String cdescricao) {
        this.cdescricao = cdescricao;
    }

    public String getClocal() {
        return clocal;
    }

    public void setClocal(String clocal) {
        this.clocal = clocal;
    }

    public String getCfoto() {
        return cfoto;
    }

    public void setCfoto(String cfoto) {
        this.cfoto = cfoto;
    }

    public String getCtipo() {
        return ctipo;
    }

    public void setCtipo(String ctipo) {
        this.ctipo = ctipo;
    }

    public String getCpalavrachave() {
        return cpalavrachave;
    }

    public void setCpalavrachave(String cpalavrachave) {
        this.cpalavrachave = cpalavrachave;
    }

    public String getCcontato() {
        return ccontato;
    }

    public void setCcontato(String ccontato) {
        this.ccontato = ccontato;
    }

    public String getCid_usuario() {
        return cidusuario;
    }

    public void setCid_usuario(String cidusuario) {
        this.cidusuario = cidusuario;
    }

    public String getCauxiliar() {
        return cauxiliar;
    }

    public void setCauxiliar(String cauxiliar) {
        this.cauxiliar = cauxiliar;
    }

    public Date getCdataevento() {
        return cdataevento;
    }

    public void setCdataevento(Date cdataevento) {
        this.cdataevento = cdataevento;
    }
}
