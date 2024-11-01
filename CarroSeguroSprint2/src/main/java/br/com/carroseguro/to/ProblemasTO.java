package br.com.carroseguro.to;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.*;

public class ProblemasTO {

    @Positive
    private int idProblema;
    @Positive
    private int idCarro;
    @NotBlank
    private String tpPecaProblema;
    @NotBlank
    private String dcProblema;



    public ProblemasTO() {
    }

    public ProblemasTO(int idProblema, String tpPecaProblema, String dcProblema, int idCarro) {
        this.idProblema = idProblema;
        this.tpPecaProblema = tpPecaProblema;
        this.dcProblema = dcProblema;
        this.idCarro = idCarro;
    }

    public int getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(int idProblema) {
        this.idProblema = idProblema;
    }

    public String getTpPecaProblema() {
        return tpPecaProblema;
    }

    public void setTpPecaProblema(String tpPecaProblema) {
        this.tpPecaProblema = tpPecaProblema;
    }

    public String getDcProblema() {
        return dcProblema;
    }

    public void setDcProblema(String dcProblema) {
        this.dcProblema = dcProblema;
    }

    public int getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }





    }

