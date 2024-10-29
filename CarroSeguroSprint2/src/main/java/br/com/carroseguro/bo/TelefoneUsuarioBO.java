package br.com.carroseguro.bo;

import br.com.carroseguro.dao.BairroDAO;
import br.com.carroseguro.dao.TelefoneUsuarioDAO;
import br.com.carroseguro.to.BairroTO;
import br.com.carroseguro.to.TelefoneUsuarioTO;
import br.com.carroseguro.to.UsuarioTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class TelefoneUsuarioBO {
    TelefoneUsuarioDAO telDAO;



    /**
     * Retorna uma lista contendo todos os telefones cadastrados.
     *
     * <p>
     * Este método não realiza validações ou regras de negócios específicas,
     * apenas recupera e retorna todos os registros de telefones armazenados
     * no banco de dados.
     * </p>
     *
     * @return uma lista de objetos {@code TelefoneUsuarioTO} representando todos os telefones cadastrados
     */
    public ArrayList<TelefoneUsuarioTO> listarTodos() {
        telDAO = new TelefoneUsuarioDAO();
        // Sem validações significativas para o metodo de listar.
        return telDAO.listarTodos();
    }

    /**
     * Insere um objeto {@link TelefoneUsuarioTO} no banco de dados após validação.
     * <p>
     * Este método utiliza a instância de {@link TelefoneUsuarioDAO} para inserir o telefone no banco de dados.
     * Também instância o {@link UsuarioTO} para capturar o id do usuário e
     * utiliza o método de obter novo ID para a inserção.
     * Caso ocorra alguma exceção durante o processo, o método captura a exceção e retorna {@code null}.
     * </p>
     *
     * @param telTO O objeto {@link TelefoneUsuarioTO} que contém os dados do telefone a serem inseridos.
     * @return O objeto {@link TelefoneUsuarioTO} inserido.
     */
    public TelefoneUsuarioTO inserir(TelefoneUsuarioTO telTO) throws SQLException {
        telDAO = new TelefoneUsuarioDAO();
        UsuarioTO userTO = new UsuarioTO();
        telTO.setIdTelefone(telDAO.obterNovoIdTelefone(telTO));
        telTO.setIdUsuario(userTO.getIdUsuario());
        return telDAO.inserir(telTO);
    }

    /**
     * Exclui um Telefone do banco de dados com base no seu ID.
     *
     * <p>
     * Este método remove um telefone do banco de dados com base no ID fornecido.
     * Não realiza validações adicionais ou regras de negócios, apenas executa
     * a exclusão do registro correspondente ao ID informado.
     * </p>
     *
     * @param idTel o ID do telefone que será excluído
     * @return {@code true} se a exclusão for bem-sucedida, {@code false} caso contrário
     */
    public boolean excluir (int idTel) {
        telDAO = new TelefoneUsuarioDAO();
        // Nenhuma regra de negócio significativa para o metodo de excluir.
        return telDAO.excluir(idTel);
    }

    /**
     * Altera os dados de um objeto {@link TelefoneUsuarioTO} no banco de dados.
     * <p>
     * Este método valida os dados do telefone e utiliza instância de {@link TelefoneUsuarioDAO}
     * para atualizar o telefone no banco de dados.
     * </p>
     *
     * @param telTO O objeto {@link TelefoneUsuarioTO} que contém os dados atualizados do telefone.
     * @return O objeto {@link TelefoneUsuarioTO} atualizado, caso a operação seja bem-sucedida;
     *         {@code null} se os dados forem inválidos ou ocorrer uma exceção.
     */
    public TelefoneUsuarioTO alterar(TelefoneUsuarioTO telTO) {
        telDAO = new TelefoneUsuarioDAO();
        // Sem regras de negócios importantes para alteração.
        return telDAO.alterar(telTO);
    }
}
