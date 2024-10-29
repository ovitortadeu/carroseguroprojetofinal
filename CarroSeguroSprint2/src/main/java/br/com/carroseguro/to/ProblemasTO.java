package br.com.carroseguro.to;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.*;

public class ProblemasTO {

    // ID do carro deve ser positivo.
    @Positive
    private int idProblema;

    @PositiveOrZero // Substitui @NotNull pois um problema pode ter valor zero ou positivo.
    private int vlProblema;

    @NotBlank // Nome não pode ser vazio ou nulo, semelhante ao atributo 'nome' do exemplo.
    private String nmProblema;

    @NotBlank // Tipo de peça do problema também não pode ser vazio ou nulo.
    private String tpPecaProblema;

    private String dcProblema; // Pode ser opcional, não precisa de validação.

    @Positive // ID do carro deve ser positivo.
    private int idCarro;

    public ProblemasTO() {
    }

    public ProblemasTO(int idProblema, int vlProblema, String nmProblema, String tpPecaProblema, String dcProblema, int idCarro) {
        this.idProblema = idProblema;
        this.vlProblema = vlProblema;
        this.nmProblema = nmProblema;
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

    public int getVlProblema() {
        return vlProblema;
    }

    public void setVlProblema(int vlProblema) {
        this.vlProblema = vlProblema;
    }

    public String getNmProblema() {
        return nmProblema;
    }

    public void setNmProblema(String nmProblema) {
        this.nmProblema = nmProblema;
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

