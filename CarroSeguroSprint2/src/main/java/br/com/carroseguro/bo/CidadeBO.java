package br.com.carroseguro.bo;

import br.com.carroseguro.dao.CidadeDAO;
import br.com.carroseguro.dao.EstadoDAO;
import br.com.carroseguro.to.CidadeTO;
import br.com.carroseguro.to.EstadoTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CidadeBO {
    private CidadeDAO cidadeDAO;

    /**
     * Retorna uma lista contendo todos as cidades cadastrados.
     *
     * <p>
     * Este método não realiza validações ou regras de negócios específicas,
     * apenas recupera e retorna todos os registros de estados armazenados
     * no banco de dados.
     * </p>
     *
     * @return uma lista de objetos {@code EstadoTO} representando todos os estados cadastrados
     */
    public ArrayList<CidadeTO> listarTodos() {
        cidadeDAO = new CidadeDAO();
        // Sem validações significativas para o metodo de listar.
        return cidadeDAO.listarTodos();
    }

    /**
     * Insere uma cidade no banco de dados após validação do nome e DDD.
     *
     * <p>
     * Este método verifica se as informações da cidade, como o nome e o DDD,
     * são válidas antes de realizar a inserção no banco de dados. Caso a validação
     * seja bem-sucedida, a cidade é inserida; caso contrário, uma mensagem de erro
     * é exibida. Método também instancia a classe de Estado, para capturar o idEstado
     * que é uma ForeignKey na tabela de Cidade.
     * </p>
     *
     * @param cidadeTO objeto contendo os dados da cidade ser inserido (nome e DDD)
     * @return o objeto {@code CidadeTO} inserido no banco de dados, ou {@code null} se a validação falhar
     */
    public CidadeTO inserir(CidadeTO cidadeTO) throws SQLException {
        cidadeDAO = new CidadeDAO();
        EstadoTO estadoTO = new EstadoTO();
        cidadeTO.setIdEstado(estadoTO.getIdEstado());
        cidadeTO.setIdCidade(cidadeDAO.obterNovoIdCidade(cidadeTO));
        try {
            if (cidadeDAO.validarCidadeEDDD(cidadeTO.getNmCidade(), cidadeTO.getNrDDD())) {
                return cidadeDAO.inserir(cidadeTO);
            } else {
                System.out.println("Erro: Cidade ou DDD incorretos");
            }
        } catch (Exception e) {
            System.out.println("Erro geral: " + e.getMessage());
        }
        return null;
    }

    /**
     * Exclui um estado do banco de dados com base no seu ID.
     *
     * <p>
     * Este método remove uma cidade do banco de dados com base no ID fornecido.
     * Não realiza validações adicionais ou regras de negócios, apenas executa
     * a exclusão do registro correspondente ao ID informado.
     * </p>
     *
     * @param idCidade o ID do estado que será excluído
     * @return {@code true} se a exclusão for bem-sucedida, {@code false} caso contrário
     */
    public boolean excluir (int idCidade) {
        cidadeDAO = new CidadeDAO();
        // Nenhuma regra de negócio significativa para o metodo de excluir
        return cidadeDAO.excluir(idCidade);
    }

    /**
     * Altera uma cidade no banco de dados após validação do nome e do DDD.
     *
     * <p>
     * Este método altera se as informações da cidade, como o nome e o DDD,
     * são válidas antes de realizar a alteração no banco de dados. Caso a validação
     * seja bem-sucedida, a cidade é alterada; caso contrário, uma mensagem de erro
     * é exibida.
     * </p>
     *
     * @param cidadeTO objeto contendo os dados da cidade a ser alterado (nome e DDD)
     * @return o objeto {@code CidadeTO} alterado no banco de dados, ou {@code null} se a validação falhar
     */
    public CidadeTO alterar(CidadeTO cidadeTO) {
        cidadeDAO = new CidadeDAO();
        try {
            if (cidadeDAO.validarCidadeEDDD(cidadeTO.getNmCidade(), cidadeTO.getNrDDD())) {
                return cidadeDAO.inserir(cidadeTO);
            } else {
                System.out.println("Erro: Cidade ou DDD incorretos");
            }
        } catch (Exception e) {
            System.out.println("Erro geral: " + e.getMessage());
        }
        return null;
    }

}
