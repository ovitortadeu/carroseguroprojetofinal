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
     * @return O objeto {@link ProblemasTO} inserido, caso a operação seja bem-sucedida;
     * {@code null} se os dados forem inválidos ou ocorrer uma exceção.
     */
    public ProblemasTO inserir(ProblemasTO problemasTO) throws SQLException {
        problemasDAO = new ProblemasDAO();
        CarroTO crrTO = new CarroTO();
        problemasTO.setIdCarro(crrTO.getIdCarro());
        return problemasDAO.inserir(problemasTO);
    }

    /**
     * Retorna um objeto ProblemasTO com base no código (ID) do problema fornecido.
     *
     * @param idProblema o código único do problema que se deseja visualizar
     * @return um objeto ProblemasTO que contém as informações do problema correspondente
     * @throws IllegalArgumentException se o ID do problema for inválido (por exemplo, negativo)
     */
    public ProblemasTO vizualizarPeloCodigo (int idProblema) {
        problemasDAO = new ProblemasDAO();
        // Sem regras de negócios relevantes para vizualização
        return problemasDAO.vizualizarPeloCodigo(idProblema);
    }

}
