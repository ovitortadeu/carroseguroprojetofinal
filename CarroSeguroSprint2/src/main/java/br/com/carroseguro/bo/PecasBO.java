package br.com.carroseguro.bo;

import br.com.carroseguro.dao.PecasDAO;

import br.com.carroseguro.to.PecasTO;

import java.util.ArrayList;

public class PecasBO {
    PecasDAO pecas;

    /**
     * Lista todas as peças disponíveis no sistema.
     *
     * @return uma lista de objetos PecasTO que contém as informações de todas as peças.
     */
    public ArrayList<PecasTO> listarTodos() {
        pecas = new PecasDAO();
        // Sem validações significativas para o metodo de listar.
        return pecas.listarTodos();
    }

    /**
     * Retorna uma peça específica com base no código (ID) fornecido.
     *
     * @param idPeca o código único da peça que se deseja visualizar
     * @return um objeto PecasTO que contém as informações da peça correspondente
     * @throws IllegalArgumentException se o ID da peça for inválido (por exemplo, negativo)
     */
    public PecasTO vizualizarPeloCodigo (int idPeca) {
        pecas = new PecasDAO();
        // Sem regras de negócios relevantes para vizualização
        return pecas.vizualizarPeloCodigo(idPeca);
    }
}
