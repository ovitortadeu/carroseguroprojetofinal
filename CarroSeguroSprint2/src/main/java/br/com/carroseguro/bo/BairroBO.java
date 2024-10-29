package br.com.carroseguro.bo;

import br.com.carroseguro.dao.BairroDAO;
import br.com.carroseguro.dao.LogradouroDAO;
import br.com.carroseguro.to.BairroTO;
import br.com.carroseguro.to.CidadeTO;
import br.com.carroseguro.to.EstadoTO;
import br.com.carroseguro.to.LogradouroTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class BairroBO {
    BairroDAO bairroDAO;

    /**
     * Valida os dados de um objeto {@link BairroTO}.
     * <p>
     * Este método verifica se os atributos do bairro respeitam as seguintes regras:
     * <ul>
     *   <li>O nome do bairro não pode exceder 45 caracteres.</li>
     *   <li>O nome da zona do bairro deve ter no máximo 20 caracteres.</li>
     * </ul>
     * </p>
     *
     * @param bairroTO O objeto {@link BairroTO} que contém os dados do bairro a serem validados.
     * @return {@code true} se todos os atributos forem válidos; {@code false} caso contrário.
     */
    public boolean validarBairro(BairroTO bairroTO){
        try {
            if (bairroTO.getNmBairro().length() > 45) {
                System.out.println("Erro: Nome do bairro não pode ter acima de 45 letras!");
                return false;
            } else if (bairroTO.getNmZonaBairro().length() > 20) {
                System.out.println("Erro: Nome da zona do bairro não pode ter mais de 20 letras!");
                return false;
            }
            return true;
        } catch (Exception e) {
            System.out.println("Erro geral: " + e.getMessage());
            return false;
        }
    }



    /**
     * Retorna uma lista contendo todos os bairros cadastrados.
     *
     * <p>
     * Este método não realiza validações ou regras de negócios específicas,
     * apenas recupera e retorna todos os registros de bairros armazenados
     * no banco de dados.
     * </p>
     *
     * @return uma lista de objetos {@code BairroTO} representando todos os logradouros cadastrados
     */
    public ArrayList<BairroTO> listarTodos() {
        bairroDAO = new BairroDAO();
        // Sem validações significativas para o metodo de listar.
        return bairroDAO.listarTodos();
    }

    /**
     * Insere um objeto {@link BairroTO} no banco de dados após validação.
     * <p>
     * Este método valida os dados do bairro e, se forem válidos, utiliza
     * a instância de {@link BairroDAO} para inserir o bairro no banco de dados.
     * Caso ocorra alguma exceção durante o processo, o método captura a exceção e retorna {@code null}.
     * Método também instancia a classe de Cidade, para capturar o idCidade e também usa o método de gerar novo ID.
     * que é uma ForeignKey na tabela de Cidade.
     * </p>
     *
     * @param bairroTO O objeto {@link BairroTO} que contém os dados do logradouro a serem inseridos.
     * @return O objeto {@link BairroTO} inserido, caso a operação seja bem-sucedida;
     *         {@code null} se os dados forem inválidos ou ocorrer uma exceção.
     */
    public BairroTO inserir(BairroTO bairroTO) throws SQLException {
        bairroDAO = new BairroDAO();
        CidadeTO cidadeTO = new CidadeTO();
        bairroTO.setIdCidade(cidadeTO.getIdCidade());
        bairroTO.setIdBairro(bairroDAO.obterNovoIdBairro(bairroTO));
        try {
            if (validarBairro(bairroTO)) {
                return bairroDAO.inserir(bairroTO);
            }
        } catch (Exception e) {
            System.out.println("Erro geral: " + e.getMessage());
        }
        return null;
    }

    /**
     * Exclui um bairro do banco de dados com base no seu ID.
     *
     * <p>
     * Este método remove um bairro do banco de dados com base no ID fornecido.
     * Não realiza validações adicionais ou regras de negócios, apenas executa
     * a exclusão do registro correspondente ao ID informado.
     * </p>
     *
     * @param idBairro o ID do bairro que será excluído
     * @return {@code true} se a exclusão for bem-sucedida, {@code false} caso contrário
     */
    public boolean excluir (int idBairro) {
        bairroDAO = new BairroDAO();
        // Nenhuma regra de negócio significativa para o metodo de excluir.
        return bairroDAO.excluir(idBairro);
    }

    /**
     * Altera os dados de um objeto {@link BairroTO} no banco de dados após validação.
     * <p>
     * Este método valida os dados do bairro e, se forem válidos, utiliza
     * a instância de {@link BairroDAO} para atualizar o logradouro no banco de dados.
     * Caso ocorra alguma exceção durante o processo, o método captura a exceção e retorna {@code null}.
     * </p>
     *
     * @param bairroTO O objeto {@link BairroTO} que contém os dados atualizados do logradouro.
     * @return O objeto {@link BairroTO} atualizado, caso a operação seja bem-sucedida;
     *         {@code null} se os dados forem inválidos ou ocorrer uma exceção.
     */
    public BairroTO alterar(BairroTO bairroTO) {
        bairroDAO = new BairroDAO();
        try {
            if (validarBairro(bairroTO)) {
                return bairroDAO.alterar(bairroTO);
            }
        } catch (Exception e) {
            System.out.println("Erro geral: " + e.getMessage());
        }
        return null;
    }
}
