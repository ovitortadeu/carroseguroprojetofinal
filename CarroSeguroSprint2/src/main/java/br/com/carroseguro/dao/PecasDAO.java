package br.com.carroseguro.dao;

import br.com.carroseguro.to.PecasTO;
import br.com.carroseguro.to.ProblemasTO;
import br.com.carroseguro.to.UsuarioTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PecasDAO extends Repository{

    public ArrayList<PecasTO> listarTodos() {
        String sql = "select * from T_CS_PECAS order by id_peca";
        ArrayList<PecasTO> listaPecas = new ArrayList<PecasTO>();
        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    PecasTO pecas = new PecasTO();
                    pecas.setIdPeca(rs.getInt("id_peca"));
                    pecas.setPreco(rs.getInt("preco"));
                    pecas.setDescricao(rs.getString("descricao"));
                    listaPecas.add(pecas);
                }
                return listaPecas;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }

    public PecasTO vizualizarPeloCodigo(int idPeca) {
        PecasTO peca = new PecasTO();
        String sql = "select * from T_CS_PECA where id_peca=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);){
            ps.setInt(1, idPeca);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                peca.setIdPeca(rs.getInt("id_peca"));
                peca.setPreco(rs.getInt("preco"));
                peca.setDescricao(rs.getString("descricao"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL ao mostrar peça: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return peca;
    }
}
