package br.com.carroseguro.bo;

import br.com.carroseguro.dao.MecanicoDAO;
import br.com.carroseguro.to.MecanicoTO;

import java.util.ArrayList;

public class MecanicoBO {
    MecanicoDAO mecanico;

    /**
     * Lista todos os mecânicos disponíveis no sistema.
     *
     * @return uma lista de objetos MecanicoTO que contém as informações de todos os mecânicos.
     */
    public ArrayList<MecanicoTO> listarTodos() {
        mecanico = new MecanicoDAO();
        // Sem validações significativas para o metodo de listar.
        return mecanico.listarTodos();
    }

    /**
     * Retorna um mecânico específico com base no código (ID) fornecido.
     *
     * @param idMecanico o código único do mecânico que se deseja visualizar
     * @return um objeto MecanicoTO que contém as informações do mecânico correspondente
     * @throws IllegalArgumentException se o ID do mecânico for inválido (por exemplo, negativo)
     */
    public MecanicoTO vizualizarPeloCodigo (int idMecanico) {
        mecanico = new MecanicoDAO();
        // Sem regras de negócios relevantes para vizualização
        return mecanico.vizualizarPeloCodigo(idMecanico);
    }
}
