package br.com.carroseguro.dao;

import br.com.carroseguro.to.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProblemasDAO extends Repository{

    public ProblemasTO inserir(ProblemasTO problemas) {
        String sql = "INSERT INTO T_CS_PROBLEMAS(tp_peca_problema, dc_problema, T_CS_CARRO_ID_CARRO) VALUES (?, ?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, problemas.getTpPecaProblema());
            ps.setString(2, problemas.getDcProblema());
            ps.setInt(3, problemas.getIdCarro());
            if (ps.executeUpdate() > 0) {
                return problemas;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL ao inserir: " + e.getMessage());
        }
        return null;
    }


    public ArrayList<ProblemasTO> listarTodos() {
        String sql = "select * from T_CS_PROBLEMAS order by id_problema";
        ArrayList<ProblemasTO> listaProblemaTO = new ArrayList<ProblemasTO>();
        try(PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    ProblemasTO problemasTO = new ProblemasTO();
                    problemasTO.setIdProblema(rs.getInt("id_problema"));
                    problemasTO.setIdCarro(rs.getInt("t_cs_carro_id_carro"));
                    problemasTO.setDcProblema(rs.getString("dc_problema"));
                    problemasTO.setTpPecaProblema(rs.getString("tp_peca_problema"));
                    listaProblemaTO.add(problemasTO);
                }
                return listaProblemaTO;
            } else{
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }

    public ProblemasTO vizualizarPeloCodigo(int idProblema) {
        ProblemasTO problema = new ProblemasTO();
        String sql = "select * from T_CS_PROBLEMAS where id_problema=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);){
            ps.setInt(1, idProblema);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                problema.setIdProblema(rs.getInt("id_problema"));
                problema.setTpPecaProblema(rs.getString("tp_peca_problema"));
                problema.setDcProblema(rs.getString("dc_problema"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL ao mostrar problema: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return problema;
    }
}