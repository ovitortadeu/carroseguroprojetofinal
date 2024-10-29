package br.com.carroseguro.dao;

import br.com.carroseguro.to.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProblemasDAO extends Repository{

    public ProblemasTO inserir(ProblemasTO problemas) {
        String sql = "insert into T_CS_PROBLEMAS(id_problema, vl_problema, nm_problema, tp_peca_problema, dc_problema, T_CS_CARRO_ID_CARRO) values(?,?,?,?,?,?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ps.setInt(1, problemas.getIdProblema());
            ps.setInt(2, problemas.getVlProblema());
            ps.setString(3, problemas.getNmProblema());
            ps.setString(4, problemas.getTpPecaProblema());
            ps.setString(5, problemas.getDcProblema());
            ps.setInt(6, problemas.getIdCarro());
            if (ps.executeUpdate() > 0) {
                return problemas;
            }
        }
        catch (SQLException e) {
            System.out.println("Erro de SQL ao inserir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
        }

    public ProblemasTO alterar(ProblemasTO problemas) {
        String sql = "update T_CS_PROBLEMAS set vl_problema=?, nm_problema=?, tp_peca_problema=?, dc_problema=?, T_CS_CARRO_ID_CARRO where id_problema=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ps.setInt(1, problemas.getVlProblema());
            ps.setString(2, problemas.getNmProblema());
            ps.setString(3, problemas.getTpPecaProblema());
            ps.setString(4, problemas.getDcProblema());
            ps.setInt(5, problemas.getIdCarro());
            ps.setInt(6, problemas.getIdProblema());
            if (ps.executeUpdate() > 0) {
                return problemas;
            }
        }
        catch (SQLException e) {
        System.out.println("Erro de SQL ao inserir: " + e.getMessage());
    } finally {
        closeConnection();
    }
        return null;
}

    public boolean excluir(int idProblema) {
        String sql = "delete from T_CS_PROBLEMAS where id_problema=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);){
            ps.setInt(1, idProblema);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro de SQL ao excluir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }

    public ArrayList<ProblemasTO> listarTodos() {
        String sql = "select * from T_CS_PROBLEMAS order by id_problema";
        ArrayList<ProblemasTO> listaProblemas = new ArrayList<ProblemasTO>();
        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    ProblemasTO problemas = new ProblemasTO();
                    problemas.setIdProblema(rs.getInt("id_problema"));
                    problemas.setNmProblema(rs.getString("nm_problema"));
                    problemas.setVlProblema(rs.getInt("vl_problema"));
                    problemas.setTpPecaProblema(rs.getString("tp_peca_problema"));
                    problemas.setDcProblema(rs.getString("dc_problema"));
                    listaProblemas.add(problemas);
                }
                return listaProblemas;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }

    public int obterNovoIdProblema(ProblemasTO problemasTO) throws SQLException {
        String sql = "SELECT MAX(id_problema) FROM T_CS_PROBLEMAS";
        PreparedStatement ps = getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) + 1;
        } else {
            return 1;
        }
    }
}