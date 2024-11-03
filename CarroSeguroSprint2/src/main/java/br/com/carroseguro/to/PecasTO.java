package br.com.carroseguro.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class PecasTO {
    @NotNull
    private Integer idPeca;
    @NotNull
    private Integer idCarro;
    @NotNull
    @PositiveOrZero // O preço não pode ser negativo.
    private Integer preco;
    @NotBlank
    private String descricao;
    @NotBlank
    private String tpPecaProblema;

    public PecasTO(int idPeca, int idCarro, int preco, String descricao, String tpPecaProblema) {
        this.idPeca = idPeca;
        this.idCarro = idCarro;
        this.preco = preco;
        this.descricao = descricao;
        this.tpPecaProblema = tpPecaProblema;
    }

    public PecasTO() {
    }

    public @NotNull Integer getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(@NotNull Integer idPeca) {
        this.idPeca = idPeca;
    }

    public @NotNull Integer getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(@NotNull Integer idCarro) {
        this.idCarro = idCarro;
    }

    public @NotNull @PositiveOrZero Integer getPreco() {
        return preco;
    }

    public void setPreco(@NotNull @PositiveOrZero Integer preco) {
        this.preco = preco;
    }

    public @NotBlank String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank String descricao) {
        this.descricao = descricao;
    }

    public @NotBlank String getTpPecaProblema() {
        return tpPecaProblema;
    }

    public void setTpPecaProblema(@NotBlank String tpPecaProblema) {
        this.tpPecaProblema = tpPecaProblema;
    }
}
