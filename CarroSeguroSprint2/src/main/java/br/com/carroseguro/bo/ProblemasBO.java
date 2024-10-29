package br.com.carroseguro.bo;

import br.com.carroseguro.dao.BairroDAO;
import br.com.carroseguro.dao.ProblemasDAO;
import br.com.carroseguro.to.BairroTO;
import br.com.carroseguro.to.CarroTO;
import br.com.carroseguro.to.ProblemasTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProblemasBO {
    ProblemasDAO problemasDAO;


    /**
     * Retorna uma lista contendo todos os problemas cadastrados.
     *
     * <p>
     * Este método não realiza validações ou regras de negócios específicas,
     * apenas recupera e retorna todos os registros de bairros armazenados
     * no banco de dados.
     * </p>
     *
     * @return uma lista de objetos {@code BairroTO} representando todos os logradouros cadastrados
     */
    public ArrayList<ProblemasTO> listarTodos() {
        problemasDAO = new ProblemasDAO();
        // Sem validações significativas para o metodo de listar.
        return problemasDAO.listarTodos();
    }

    /**
     * Insere um objeto {@link ProblemasTO} no banco de dados.
     * <p>
     * Este método utilizaa instância de {@link ProblemasDAO} para inserir o problema do carro
     * no banco de dados Caso ocorra alguma exceção durante o processo, o método captura a exceção
     * e retorna {@code null}. Também instancia a classe de {@link CarroTO} para capturar o id da ForeignKey,
     * e usa o método de gerar um novo Id para a primaryKey do banco de dados.
     * </p>
     *
     * @param problemasTO O objeto {@link ProblemasTO} que contém os dados do problema a ser inserido.
     * @return O objeto {@link BairroTO} inserido, caso a operação seja bem-sucedida;
     * {@code null} se os dados forem inválidos ou ocorrer uma exceção.
     */
    public ProblemasTO inserir(ProblemasTO problemasTO) throws SQLException {
        problemasDAO = new ProblemasDAO();
        CarroTO crrTO = new CarroTO();
        problemasTO.setIdProblema(problemasDAO.obterNovoIdProblema(problemasTO));
        problemasTO.setIdCarro(crrTO.getIdCarro());
        return problemasDAO.inserir(problemasTO);
    }

    /**
     * Exclui um problema do banco de dados com base no seu ID.
     *
     * <p>
     * Este método remove um problema do banco de dados com base no ID fornecido.
     * Não realiza validações adicionais ou regras de negócios, apenas executa
     * a exclusão do registro correspondente ao ID informado.
     * </p>
     *
     * @param idProblemas o ID do problema que será excluído
     * @return {@code true} se a exclusão for bem-sucedida, {@code false} caso contrário
     */
    public boolean excluir(int idProblemas) {
        problemasDAO = new ProblemasDAO();
        // Nenhuma regra de negócio significativa para o metodo de excluir.
        return problemasDAO.excluir(idProblemas);
    }

    /**
     * Altera os dados de um objeto {@link ProblemasTO} no banco de dados após validação.
     * <p>
     * Este método utiliza a instância de {@link BairroDAO} para atualizar o problema no banco de dados.
     * Não tem regras de negócios específicas, simplesmente altera um problema no banco de dados.
     * Caso ocorra alguma exceção durante o processo, o método captura a exceção e retorna {@code null}.
     * </p>
     *
     * @param problemasTO O objeto {@link ProblemasTO} que contém os dados atualizados do problema.
     * @return O objeto {@link ProblemasTO} atualizado, caso a operação seja bem-sucedida;
     * {@code null} se os dados forem inválidos ou ocorrer uma exceção.
     */
    public ProblemasTO alterar(ProblemasTO problemasTO) {
        problemasDAO = new ProblemasDAO();
        // Sem regras de negócios relevantes para a alteração de um problema.
        return problemasDAO.alterar(problemasTO);
    }
}
