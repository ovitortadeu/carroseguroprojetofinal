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
     * Insere um novo usuário no sistema, verificando se o email do usuário já está cadastrado.
     * <p>
     * Caso o email já exista no sistema, o método não insere o usuário e retorna {@code null}.
     * Se o email não estiver cadastrado, o método obtém um novo ID para o usuário e realiza a inserção.
     * </p>
     *
     * @param usuarioTO um objeto {@link UsuarioTO} contendo as informações do usuário a ser inserido.
     * @return o objeto {@link UsuarioTO} com o ID gerado se a inserção for bem-sucedida,
     *         ou {@code null} se o email já estiver cadastrado.
     * @throws SQLException se ocorrer um erro de acesso ao banco de dados durante a inserção.
     */
    public UsuarioTO inserir(UsuarioTO usuarioTO) throws SQLException {
        usuarioDAO = new UsuarioDAO();

        // Verifica se o email já está cadastrado
        if (usuarioDAO.emailExistente(usuarioTO.getEmailUsuario())) {
            System.out.println("Email já cadastrado!");
            return null;
        }

        // Obtem novo ID e insere o usuário
        usuarioTO.setIdUsuario(usuarioDAO.obterNovoIdUsuario(usuarioTO));
        return usuarioDAO.inserir(usuarioTO);
    }


    /**
     * Retorna as informações de um usuário com base no seu ID.
     * <p>
     * Este método consulta o banco de dados para obter os dados do usuário correspondente ao ID fornecido.
     * Não há regras de negócios adicionais aplicadas na visualização.
     * </p>
     *
     * @param idUsuario o ID do usuário que se deseja visualizar.
     * @return um objeto {@link UsuarioTO} contendo as informações do usuário,
     *         ou {@code null} se o usuário não for encontrado.
     */
    public UsuarioTO vizualizarPeloCodigo (int idUsuario) {
        usuarioDAO = new UsuarioDAO();
        // Sem regras de negócios relevantes para vizualização
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
