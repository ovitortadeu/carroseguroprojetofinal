package br.com.carroseguro.bo;

import br.com.carroseguro.dao.LogradouroDAO;
import br.com.carroseguro.to.BairroTO;
import br.com.carroseguro.to.LogradouroTO;
import br.com.carroseguro.to.UsuarioTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class LogradouroBO {

    LogradouroDAO logradouroDAO;

    /**
     * Valida os dados de um objeto {@link LogradouroTO}.
     * <p>
     * Este método verifica se os atributos do logradouro respeitam as seguintes regras:
     * <ul>
     *   <li>O nome do logradouro não pode exceder 30 caracteres.</li>
     *   <li>O número do logradouro deve ter no máximo 7 dígitos.</li>
     *   <li>O número do CEP deve ter no máximo 8 dígitos.</li>
     * </ul>
     * </p>
     *
     * @param logradouroTO O objeto {@link LogradouroTO} que contém os dados do logradouro a serem validados.
     * @return {@code true} se todos os atributos forem válidos; {@code false} caso contrário.
     */
    public boolean validarLogradouro(LogradouroTO logradouroTO){
        try {
            if (logradouroTO.getNmLogradouro().length() > 30) {
                System.out.println("Erro: Nome de logradouro não pode ter acima de 30 letras");
                return false;
            } else if (logradouroTO.getNrLogradouro() > 9999999) {
                System.out.println("Erro: Número do logradouro não pode ter mais que 7 números");
                return false;
            } else if (logradouroTO.getNrCep() > 99999999){
                System.out.println("Erro: Número de CEP não pode ter mais de 8 números");
                return false;
            }
            return true;
        } catch (Exception e) {
            System.out.println("Erro geral: " + e.getMessage());
            return false;
        }
    }



    /**
     * Retorna uma lista contendo todos os logradouros cadastrados.
     *
     * <p>
     * Este método não realiza validações ou regras de negócios específicas,
     * apenas recupera e retorna todos os registros de logradouros armazenados
     * no banco de dados.
     * </p>
     *
     * @return uma lista de objetos {@code LogradouroTO} representando todos os logradouros cadastrados
     */
    public ArrayList<LogradouroTO> listarTodos() {
        logradouroDAO = new LogradouroDAO();
        // Sem validações significativas para o metodo de listar.
        return logradouroDAO.listarTodos();
    }

    /**
     * Insere um objeto {@link LogradouroTO} no banco de dados após validação.
     * <p>
     * Este método valida os dados do logradouro e, se forem válidos, utiliza
     * a instância de {@link LogradouroDAO} para inserir o logradouro no banco de dados.
     * Caso ocorra alguma exceção durante o processo, o método captura a exceção e retorna {@code null}.
     * Método também instancia as classe de Bairro e Usuario, para capturar o idBairro e idUsuario
     *  que são ForeignKeys das tabelas de Bairro e Usuario respectivamente.
     * </p>
     *
     * @param logradouroTO O objeto {@link LogradouroTO} que contém os dados do logradouro a serem inseridos.
     * @return O objeto {@link LogradouroTO} inserido, caso a operação seja bem-sucedida;
     *         {@code null} se os dados forem inválidos ou ocorrer uma exceção.
     */
    public LogradouroTO inserir(LogradouroTO logradouroTO) throws SQLException {
        logradouroDAO = new LogradouroDAO();
        BairroTO brr = new BairroTO();
        UsuarioTO user = new UsuarioTO();
        logradouroTO.setIdBairro(brr.getIdBairro());
        logradouroTO.setIdUsuario(user.getIdUsuario());
        logradouroTO.setIdLogradouro(logradouroDAO.obterNovoIdLogradouro(logradouroTO));
        try {
            if (validarLogradouro(logradouroTO)) {
                return logradouroDAO.inserir(logradouroTO);
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
     * Este método remove um logradouro do banco de dados com base no ID fornecido.
     * Não realiza validações adicionais ou regras de negócios, apenas executa
     * a exclusão do registro correspondente ao ID informado.
     * </p>
     *
     * @param idLogradouro o ID do logradouro que será excluído
     * @return {@code true} se a exclusão for bem-sucedida, {@code false} caso contrário
     */
    public boolean excluir (int idLogradouro) {
        logradouroDAO = new LogradouroDAO();
        // Nenhuma regra de negócio significativa para o metodo de excluir.
        return logradouroDAO.excluir(idLogradouro);
    }

    /**
     * Altera os dados de um objeto {@link LogradouroTO} no banco de dados após validação.
     * <p>
     * Este método valida os dados do logradouro e, se forem válidos, utiliza
     * a instância de {@link LogradouroDAO} para atualizar o logradouro no banco de dados.
     * Caso ocorra alguma exceção durante o processo, o método captura a exceção e retorna {@code null}.
     * </p>
     *
     * @param logradouroTO O objeto {@link LogradouroTO} que contém os dados atualizados do logradouro.
     * @return O objeto {@link LogradouroTO} atualizado, caso a operação seja bem-sucedida;
     *         {@code null} se os dados forem inválidos ou ocorrer uma exceção.
     */
    public LogradouroTO alterar(LogradouroTO logradouroTO) {
        logradouroDAO = new LogradouroDAO();
        try {
            if (validarLogradouro(logradouroTO)) {
                return logradouroDAO.alterar(logradouroTO);
            }
        } catch (Exception e) {
            System.out.println("Erro geral: " + e.getMessage());
        }
        return null;
    }
}
