package br.com.carroseguro.bo;

import br.com.carroseguro.dao.CarroDAO;
import br.com.carroseguro.to.CarroTO;
import br.com.carroseguro.to.UsuarioTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CarroBO {
    CarroDAO carroDAO;


    /**
     * Retorna uma lista contendo todos os carros cadastrados.
     *
     * <p>
     * Este método não realiza validações ou regras de negócios específicas,
     * apenas recupera e retorna todos os registros de carros armazenados
     * no banco de dados.
     * </p>
     *
     * @return uma lista de objetos {@link CarroTO} representando todos os carros cadastrados
     */
    public ArrayList<CarroTO> listarTodos() {
        carroDAO = new CarroDAO();
        // Sem validações significativas para o metodo de listar.
        return carroDAO.listarTodos();
    }

    /**
     * Insere um objeto {@link CarroTO} no banco de dados após validação.
     *
     * @param carroTO O objeto {@link CarroTO} que contém os dados do carro a serem inseridos.
     * @return O objeto {@link CarroTO} inserido, caso a operação seja bem-sucedida;
     *         {@code null} se os dados forem inválidos ou ocorrer uma exceção.
     * @throws SQLException se ocorrer um erro ao inserir o carro no banco de dados.
     */
    public CarroTO inserir(CarroTO carroTO) throws SQLException {
        carroDAO = new CarroDAO();
        return carroDAO.inserir(carroTO);
    }

    /**
     * Exclui um carro do banco de dados com base no seu ID.
     *
     * <p>
     * Este método remove um carro do banco de dados com base no ID fornecido.
     * Não realiza validações adicionais ou regras de negócios, apenas executa
     * a exclusão do registro correspondente ao ID informado.
     * </p>
     *
     * @param idCarro o ID do carro que será excluído
     * @return {@code true} se a exclusão for bem-sucedida, {@code false} caso contrário
     */
    public boolean excluir (int idCarro) {
        carroDAO = new CarroDAO();
        // Nenhuma regra de negócio significativa para o metodo de excluir.
        return carroDAO.excluir(idCarro);
    }

    /**
     * Altera os dados de um objeto {@link CarroTO} no banco de dados após validação.
     *
     * @param carroTO O objeto {@link CarroTO} que contém os dados atualizados do carro.
     * @return O objeto {@link CarroTO} atualizado, caso a operação seja bem-sucedida;
     *         {@code null} se os dados forem inválidos ou ocorrer uma exceção.
     */
    public CarroTO alterar(CarroTO carroTO) {
        carroDAO = new CarroDAO();
        // sem regras de negócio significativas para alteração
        return carroDAO.alterar(carroTO);
    }

    /**
     * Visualiza um carro no banco de dados com base no seu código.
     *
     * <p>
     * Este método recupera os dados de um carro específico utilizando o ID fornecido.
     * </p>
     *
     * @param idCarro o ID do carro a ser visualizado
     * @return O objeto {@link CarroTO} correspondente ao ID fornecido,
     *         ou {@code null} se o carro não for encontrado.
     */
    public CarroTO vizualizarPeloCodigo(int idCarro) {
        carroDAO = new CarroDAO();

        return carroDAO.vizualizarPeloCodigo(idCarro);
    }

}
