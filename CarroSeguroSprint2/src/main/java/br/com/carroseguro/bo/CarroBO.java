package br.com.carroseguro.bo;

import br.com.carroseguro.dao.BairroDAO;
import br.com.carroseguro.dao.CarroDAO;
import br.com.carroseguro.to.BairroTO;
import br.com.carroseguro.to.CarroTO;
import br.com.carroseguro.to.UsuarioTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CarroBO {
    CarroDAO carroDAO;

    /**
     * Valida os dados de um objeto {@link CarroTO}.
     * <p>
     * Este método verifica se os atributos do carro respeitam as seguintes regras:
     * <ul>
     *   <li>O modelo do carro não pode exceder 20 caracteres.</li>
     *   <li>A marca do carro deve ter no máximo 20 caracteres.</li>
     * </ul>
     * </p>
     *
     * @param carroTO O objeto {@link CarroTO} que contém os dados do carro a serem validados.
     * @return {@code true} se todos os atributos forem válidos; {@code false} caso contrário.
     */
    public boolean validarCarro(CarroTO carroTO){
        try {
            if (carroTO.getModeloCarro().length() > 20) {
                System.out.println("Erro: Modelo do carro não pode ter acima de 20 letras!");
                return false;
            } else if (carroTO.getMarcaCarro().length() > 20) {
                System.out.println("Erro: Nome da marca do carro não pode ter mais de 20 letras!");
                return false;
            }
            return true;
        } catch (Exception e) {
            System.out.println("Erro geral: " + e.getMessage());
            return false;
        }
    }



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
     * <p>
     * Este método valida os dados do carro e, se forem válidos, utiliza
     * a instância de {@link CarroDAO} para inserir o carro no banco de dados.
     * Caso ocorra alguma exceção durante o processo, o método captura a exceção e retorna {@code null}.
     * Método também instancia a classe de Usuario, para capturar o idUsuario e usa o método de gerar novo ID.
     * que é uma ForeignKey na tabela de Usuario.
     * </p>
     *
     * @param carroTO O objeto {@link CarroTO} que contém os dados do carro a serem inseridos.
     * @return O objeto {@link CarroTO} inserido, caso a operação seja bem-sucedida;
     *         {@code null} se os dados forem inválidos ou ocorrer uma exceção.
     */
    public CarroTO inserir(CarroTO carroTO) throws SQLException {
        carroDAO = new CarroDAO();
        UsuarioTO usuario = new UsuarioTO();
        carroTO.setIdUsuario(usuario.getIdUsuario());
        carroTO.setIdCarro(carroDAO.obterNovoIdCarro(carroTO));
        try {
            if (validarCarro(carroTO)) {
                return carroDAO.inserir(carroTO);
            }
        } catch (Exception e) {
            System.out.println("Erro geral: " + e.getMessage());
        }
        return null;
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
     * <p>
     * Este método valida os dados do carro e, se forem válidos, utiliza
     * a instância de {@link CarroDAO} para atualizar o carro no banco de dados.
     * Caso ocorra alguma exceção durante o processo, o método captura a exceção e retorna {@code null}.
     * </p>
     *
     * @param carroTO O objeto {@link CarroTO} que contém os dados atualizados do carro.
     * @return O objeto {@link CarroTO} atualizado, caso a operação seja bem-sucedida;
     *         {@code null} se os dados forem inválidos ou ocorrer uma exceção.
     */
    public CarroTO alterar(CarroTO carroTO) {
        carroDAO = new CarroDAO();
        try {
            if (validarCarro(carroTO)) {
                return carroDAO.alterar(carroTO);
            }
        } catch (Exception e) {
            System.out.println("Erro geral: " + e.getMessage());
        }
        return null;
    }
}
