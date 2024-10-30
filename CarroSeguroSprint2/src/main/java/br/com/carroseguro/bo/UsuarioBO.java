package br.com.carroseguro.bo;

import br.com.carroseguro.dao.BairroDAO;
import br.com.carroseguro.dao.UsuarioDAO;
import br.com.carroseguro.to.BairroTO;
import br.com.carroseguro.to.UsuarioTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioBO {
    UsuarioDAO usuarioDAO;


    /**
     * Retorna uma lista contendo todos os usuários cadastrados.
     *
     * <p>
     * Este método não realiza validações ou regras de negócios específicas,
     * apenas recupera e retorna todos os registros de bairros armazenados
     * no banco de dados.
     * </p>
     *
     * @return uma lista de objetos {@code UsuarioTO} representando todos os usuários cadastrados
     */
    public ArrayList<UsuarioTO> listarTodos() {
        usuarioDAO = new UsuarioDAO();
        // Sem validações significativas para o metodo de listar.
        return usuarioDAO.listarTodos();
    }

    /**
     * Insere um objeto {@link UsuarioTO} no banco de dados após validação.
     * <p>
     * Este método utiliza instância de {@link UsuarioDAO} para inserir o bairro no banco de dados.
     * Também usa o método de obter novo ID para a inserção no banco de dados.
     * </p>
     *
     * @param usuarioTO O objeto {@link UsuarioTO} que contém os dados do usuário a serem inseridos.
     * @return O objeto {@link UsuarioTO} inserido.
     */
    public UsuarioTO inserir(UsuarioTO usuarioTO) throws SQLException {
        usuarioDAO = new UsuarioDAO();
        usuarioTO.setIdUsuario(usuarioDAO.obterNovoIdUsuario(usuarioTO));
        try {
            if (usuarioDAO.verificarEmailSenha(usuarioTO)) {
                return usuarioDAO.inserir(usuarioTO);
            }
        } catch (Exception e ) {
            System.out.println("Erro geral: " + e.getMessage());
        }
    return null;
    }


    public UsuarioTO vizualizarPeloCodigo (int idUsuario) {
        usuarioDAO = new UsuarioDAO();

        return usuarioDAO.vizualizarPeloCodigo(idUsuario);
    }

    /**
     * Exclui um usuário do banco de dados com base no seu ID.
     *
     * <p>
     * Este método remove um usuário do banco de dados com base no ID fornecido.
     * Não realiza validações adicionais ou regras de negócios, apenas executa
     * a exclusão do registro correspondente ao ID informado.
     * </p>
     *
     * @param idUsuario o ID do bairro que será excluído
     * @return {@code true} se a exclusão for bem-sucedida, {@code false} caso contrário
     */
    public boolean excluir (int idUsuario) {
        usuarioDAO = new UsuarioDAO();
        // Nenhuma regra de negócio significativa para o metodo de excluir.
        return usuarioDAO.excluir(idUsuario);
    }

    /**
     * Altera os dados de um objeto {@link UsuarioTO} no banco de dados após validação.
     * <p>
     * Este método valida os dados do usuário utiliza a instância de {@link UsuarioDAO}
     * para atualizar o usuário no banco de dados.
     * </p>
     *
     * @param usuarioTO O objeto {@link UsuarioTO} que contém os dados atualizados do usuário.
     * @return O objeto {@link UsuarioTO} atualizado, caso a operação seja bem-sucedida;
     */
    public UsuarioTO alterar(UsuarioTO usuarioTO) {
        usuarioDAO = new UsuarioDAO();
        // Sem regras de negócio relevantes para a alteração.
        return usuarioDAO.alterar(usuarioTO);
    }
}
