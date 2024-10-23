package br.com.carroseguro.dao;

import br.com.carroseguro.to.ProblemasTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProblemasDAO {
    private Connection con;

    public ProblemasDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    public String inserir(ProblemasTO problemasTO) {
        String sql = "insert into T_CS_PROBLEMAS(id_problema, vl_problema, nm_problema, tp_peca_problema, dc_problema, T_CS_CARRO_ID_CARRO) values(?,?,?,?,?,?)";
        try (PreparedStatement ps = getCon().prepareStatement(sql);) {
            ps.setInt(1, problemasTO.getIdProblema());
            ps.setInt(2, problemasTO.getVl_problema());
            ps.setString(3, problemasTO.getNmProblema());
            ps.setString(4, problemasTO.getTpPecaProblema());
            ps.setString(5, problemasTO.getDcProblema());
            ps.setInt(6, problemasTO.getIdCarro());
            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso";
            } else {
                return "Erro ao inserir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String alterar(ProblemasTO problemasTO) {
        String sql = "update T_CS_PROBLEMAS set vl_problema=?, nm_problema=?, tp_peca_problema=?, dc_problema=?, T_CS_CARRO_ID_CARRO where id_problema=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql);) {
            ps.setInt(1, problemasTO.getVl_problema());
            ps.setString(2, problemasTO.getNmProblema());
            ps.setString(3, problemasTO.getTpPecaProblema());
            ps.setString(4, problemasTO.getDcProblema());
            ps.setInt(5, problemasTO.getIdCarro());
            ps.setInt(6, problemasTO.getIdProblema());
            if (ps.executeUpdate() > 0) {
                return "Alterado com sucesso";
            } else {
                return "Erro ao alterar";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String excluir(ProblemasTO problemasTO) {
        String sql = "delete from T_CS_PROBLEMAS where id_problema=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql);) {
            ps.setInt(1, problemasTO.getIdProblema());
            if (ps.executeUpdate() > 0) {
                return "Excluído com sucesso";
            } else {
                return "Erro ao deletar";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public ArrayList<ProblemasTO> listarTodos() {
        String sql = "select * from T_CS_PROBLEMAS order by id_problema";
        ArrayList<ProblemasTO> listaProblemas = new ArrayList<ProblemasTO>();
        try (PreparedStatement ps = getCon().prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    ProblemasTO problemasTO = new ProblemasTO();
                    problemasTO.setIdProblema(rs.getInt("id_problema"));
                    problemasTO.setNmProblema(rs.getString("nm_problema"));
                    problemasTO.setVl_problema(rs.getInt("vl_problema"));
                    problemasTO.setTpPecaProblema(rs.getString("tp_peca_problema"));
                    problemasTO.setDcProblema(rs.getString("dc_problema"));
                    listaProblemas.add(problemasTO);
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

    public int obterNovoIdProblema(Connection con) throws SQLException {
        String sql = "SELECT MAX(id_problema) FROM T_CS_PROBLEMAS";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) + 1;
        } else {
            return 1;
        }


    }
}