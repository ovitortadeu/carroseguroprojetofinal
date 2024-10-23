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
    private int vl_problema;

    @NotBlank // Nome não pode ser vazio ou nulo, semelhante ao atributo 'nome' do exemplo.
    private String nmProblema;

    @NotBlank // Tipo de peça do problema também não pode ser vazio ou nulo.
    private String tpPecaProblema;

    private String dcProblema; // Pode ser opcional, não precisa de validação.

    @Positive // ID do carro deve ser positivo.
    private int idCarro;

    public ProblemasTO() {
    }

    public ProblemasTO(int idProblema, int vl_problema, String nmProblema, String tpPecaProblema, String dcProblema, int idCarro) {
        this.idProblema = idProblema;
        this.vl_problema = vl_problema;
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

    public int getVl_problema() {
        return vl_problema;
    }

    public void setVl_problema(int vl_problema) {
        this.vl_problema = vl_problema;
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

    public ArrayList<String> gerarProblemaPeca() {
        ArrayList<String> pecas = new ArrayList<>();
        pecas.add("Bateria");
        pecas.add("Velas de ignição");
        pecas.add("Freios");
        pecas.add("Embreagem");
        pecas.add("Suspensão");

        return pecas;
    }

    public double gerarProblemaValor(String peca) {

        double resultado = 0;
        if (tpPecaProblema.equalsIgnoreCase("Suspensão")) {
            Random r = new Random();

            double randomDouble = r.nextDouble();
            double min = 5000;
            double max = 7000;

            double result = min + (max - min) * randomDouble;
            resultado = Math.round(result * 100.0) / 100.0;
        } else if (tpPecaProblema.equalsIgnoreCase("Bateria")) {
            Random r = new Random();

            double randomDouble = r.nextDouble();
            double min = 1500;
            double max = 2800;

            double result = min + (max - min) * randomDouble;
            resultado = Math.round(result * 100.0) / 100.0;
        } else if (tpPecaProblema.equalsIgnoreCase("Embreagem")) {
            Random r = new Random();

            double randomDouble = r.nextDouble();
            double min = 4000;
            double max = 6000;

            double result = min + (max - min) * randomDouble;
            resultado = Math.round(result * 100.0) / 100.0;
        } else if (tpPecaProblema.equalsIgnoreCase("Freios")) {
            Random r = new Random();

            double randomDouble = r.nextDouble();
            double min = 2500;
            double max = 4000;

            double result = min + (max - min) * randomDouble;
            resultado = Math.round(result * 100.0) / 100.0;
        } else if (tpPecaProblema.equalsIgnoreCase("Velas de ignição")) {
            Random r = new Random();

            double randomDouble = r.nextDouble();
            double min = 500;
            double max = 2000;

            double result = min + (max - min) * randomDouble;
            resultado = Math.round(result * 100.0) / 100.0;
        }
        return resultado;
    }



    }

