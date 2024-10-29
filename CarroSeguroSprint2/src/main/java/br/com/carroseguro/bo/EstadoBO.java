package br.com.carroseguro.bo;

import br.com.carroseguro.dao.EstadoDAO;
import br.com.carroseguro.to.EstadoTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class EstadoBO {
    private EstadoDAO estadoDAO;

    /**
     * Valida se o estado e a sigla fornecidos em {@code estadoTO} correspondem
     * a uma combinação válida de estado e sigla no Brasil.
     * <p>
     * Este método cria duas listas (uma de nomes de estados e outra de siglas) e
     * realiza uma verificação de correspondência usando um loop. Se a combinação
     * de nome do estado e sigla for encontrada, o método retorna {@code true}.
     * Caso contrário, retorna {@code false}.
     * </p>
     *
     * @param estadoTO objeto que contém os dados do estado a ser validado (nome e sigla)
     * @return {@code true} se o nome do estado e a sigla forem uma combinação válida,
     *         {@code false} caso contrário
     */
    private boolean validarEstadoESigla(EstadoTO estadoTO) {
        ArrayList<String> estados = new ArrayList<>(Arrays.asList(
                "Acre", "Alagoas", "Amapá", "Amazonas", "Bahia", "Ceará",
                "Distrito Federal", "Espírito Santo", "Goiás", "Maranhão",
                "Mato Grosso", "Mato Grosso do Sul", "Minas Gerais", "Pará",
                "Paraíba", "Paraná", "Pernambuco", "Piauí", "Rio de Janeiro",
                "Rio Grande do Norte", "Rio Grande do Sul", "Rondônia", "Roraima",
                "Santa Catarina", "São Paulo", "Sergipe", "Tocantins"
        ));
        ArrayList<String> siglas = new ArrayList<>(Arrays.asList(
                "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA",
                "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN",
                "RS", "RO", "RR", "SC", "SP", "SE", "TO"
        ));
        for (int i = 0; i < estados.size(); i++) {
            if (estadoTO.getNmEstado().equalsIgnoreCase(estados.get(i)) &&
                    estadoTO.getSgEstado().equalsIgnoreCase(siglas.get(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna uma lista contendo todos os estados cadastrados.
     *
     * <p>
     * Este método não realiza validações ou regras de negócios específicas,
     * apenas recupera e retorna todos os registros de estados armazenados
     * no banco de dados.
     * </p>
     *
     * @return uma lista de objetos {@code EstadoTO} representando todos os estados cadastrados
     */
    public ArrayList<EstadoTO> listarTodos() {
        estadoDAO = new EstadoDAO();
        // Sem validações significativas para o metodo de listar
        return estadoDAO.listarTodos();
    }

    /**
     * Insere um estado no banco de dados após validação do nome e da sigla.
     *
     * <p>
     * Este método verifica se as informações do estado, como o nome e a sigla,
     * são válidas antes de realizar a inserção no banco de dados. Caso a validação
     * seja bem-sucedida, o estado é inserido; caso contrário, uma mensagem de erro
     * é exibida.
     * </p>
     *
     * @param estadoTO objeto contendo os dados do estado a ser inserido (nome e sigla)
     * @return o objeto {@code EstadoTO} inserido no banco de dados, ou {@code null} se a validação falhar
     */
    public EstadoTO inserir(EstadoTO estadoTO) throws SQLException {
        estadoDAO = new EstadoDAO();
        estadoTO.setIdEstado(estadoDAO.obterNovoIdEstado(estadoTO));
        try {
            if (validarEstadoESigla(estadoTO)) {
                return estadoDAO.inserir(estadoTO);
            } else {
                System.out.println("Erro: Estado ou sigla inválidos.");
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
     * Este método remove um estado do banco de dados com base no ID fornecido.
     * Não realiza validações adicionais ou regras de negócios, apenas executa
     * a exclusão do registro correspondente ao ID informado.
     * </p>
     *
     * @param idEstado o ID do estado que será excluído
     * @return {@code true} se a exclusão for bem-sucedida, {@code false} caso contrário
     */
    public boolean excluir (int idEstado) {
        estadoDAO = new EstadoDAO();
        // Nenhuma regra de negócio significativa para o metodo de excluir
        return estadoDAO.excluir(idEstado);
    }

    /**
     * Altera um estado no banco de dados após validação do nome e da sigla.
     *
     * <p>
     * Este método altera se as informações do estado, como o nome e a sigla,
     * são válidas antes de realizar a alteração no banco de dados. Caso a validação
     * seja bem-sucedida, o estado é alterado; caso contrário, uma mensagem de erro
     * é exibida.
     * </p>
     *
     * @param estadoTO objeto contendo os dados do estado a ser alterado (nome e sigla)
     * @return o objeto {@code EstadoTO} alterado no banco de dados, ou {@code null} se a validação falhar
     */
    public EstadoTO alterar(EstadoTO estadoTO) {
        estadoDAO = new EstadoDAO();
        try {
            if (validarEstadoESigla(estadoTO)) {
                return estadoDAO.alterar(estadoTO);
            } else {
                System.out.println("Erro: Estado ou sigla inválidos.");
            }
        } catch (Exception e) {
            System.out.println("Erro geral: " + e.getMessage());
        }
        return null;
    }
}

