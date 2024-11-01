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

    public PecasTO(int idPeca, int idCarro, int preco, String descricao) {
        this.idPeca = idPeca;
        this.idCarro = idCarro;
        this.preco = preco;
        this.descricao = descricao;
    }

    public PecasTO() {
    }
    public int getIdCarro() {
        return idCarro;
    }
    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }
    public int getIdPeca() {
        return idPeca;
    }
    public void setIdPeca(int idPeca) {
        this.idPeca = idPeca;
    }
    public int getPreco() {
        return preco;
    }
    public void setPreco(int preco) {
        this.preco = preco;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
