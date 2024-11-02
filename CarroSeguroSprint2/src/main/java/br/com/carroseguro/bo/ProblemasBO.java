package br.com.carroseguro.bo;

import br.com.carroseguro.dao.ProblemasDAO;
import br.com.carroseguro.dao.UsuarioDAO;
import br.com.carroseguro.to.CarroTO;
import br.com.carroseguro.to.ProblemasTO;
import br.com.carroseguro.to.UsuarioTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProblemasBO {
    ProblemasDAO problemasDAO;


    /**
     * Retorna uma lista contendo todos os problemas cadastrados.
     *
     * <p>
     * Este método não realiza validações ou regras de negócios específicas,
     * apenas recupera e retorna todos os registros de problemas armazenados
     * no banco de dados.
     * </p>
     *
     * @return uma lista de objetos {@link ProblemasTO} representando todos os problemas cadastrados
     */
    public ArrayList<ProblemasTO> listarTodos() {
        problemasDAO = new ProblemasDAO();
        // Sem validações significativas para o metodo de listar.
        return problemasDAO.listarTodos();
    }

    /**
     * Insere um objeto {@link ProblemasTO} no banco de dados.
     *
     * @param problemasTO O objeto {@link ProblemasTO} que contém os dados do problema a ser inserido.
     * @return O objeto {@link ProblemasTO} inserido, caso a operação seja bem-sucedida;
     *         {@code null} se os dados forem inválidos ou ocorrer uma exceção.
     * @throws SQLException se ocorrer um erro ao inserir o problema no banco de dados.
     */
    public ProblemasTO inserir(ProblemasTO problemasTO) throws SQLException {
        problemasDAO = new ProblemasDAO();
        return problemasDAO.inserir(problemasTO);
    }

    /**
     * Retorna um objeto {@link ProblemasTO} com base no código (ID) do problema fornecido.
     *
     * <p>
     * Este método recupera as informações do problema correspondente ao ID fornecido.
     * </p>
     *
     * @param idProblema o código único do problema que se deseja visualizar
     * @return um objeto {@link ProblemasTO} que contém as informações do problema correspondente,
     *         ou {@code null} se o problema não for encontrado.
     * @throws IllegalArgumentException se o ID do problema for inválido (por exemplo, negativo).
     */
    public ProblemasTO vizualizarPeloCodigo (int idProblema) {
        problemasDAO = new ProblemasDAO();
        // Sem regras de negócios relevantes para vizualização
        return problemasDAO.vizualizarPeloCodigo(idProblema);
    }

}
