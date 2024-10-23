package br.com.carroseguro.dao;

import br.com.carroseguro.to.CidadeTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CidadeDAO {
    private Connection con;

    public CidadeDAO(Connection con) {
        this.con = con;
    }
    public Connection getCon() {
        return con;
    }

    public String inserir(CidadeTO cdd) {
        String sql = "insert into T_CS_CIDADE(id_cidade, nm_cidade, nr_ddd, t_cs_estado_id_estado) values(?,?,?,?)";
        try(PreparedStatement ps = getCon().prepareStatement(sql);) {
            ps.setInt(1, cdd.getIdCidade());
            ps.setString(2, cdd.getNmCidade());
            ps.setInt(3, cdd.getNrDDD());
            ps.setInt(4, cdd.getIdEstado());
            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso";
            } else{
                return "Erro ao inserir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }
    public String alterar(CidadeTO cdd) {
        String sql = "update T_CS_CIDADE set nm_cidade=?, nr_ddd=?, id_estado=? where id_cidade=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql);) {
            ps.setString(1, cdd.getNmCidade());
            ps.setInt(2, cdd.getNrDDD());
            ps.setInt(3, cdd.getIdEstado());
            ps.setInt(4, cdd.getIdCidade());
            if (ps.executeUpdate() > 0) {
                return "Alterado com sucesso";
            } else {
                return "Erro ao alterar";
            }
        } catch(SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }
    public String excluir(CidadeTO cdd) {
        String sql = "delete from T_CS_CIDADE where id_cidade=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql);){
            ps.setInt(1, cdd.getIdCidade());
            if (ps.executeUpdate() > 0) {
                return "Excluído com sucesso";
            } else {
                return "Erro ao deletar";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }
    public ArrayList<CidadeTO> listarTodos() {
        String sql = "select * from T_CS_CIDADE order by id_cidade";
        ArrayList<CidadeTO> listaCidadeTO = new ArrayList<CidadeTO>();
        try(PreparedStatement ps = getCon().prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    CidadeTO cdd = new CidadeTO();
                    cdd.setIdCidade(rs.getInt("id_cidade"));
                    cdd.setNmCidade(rs.getString("nm_cidade"));
                    cdd.setNrDDD(rs.getInt("nr_ddd"));
                    listaCidadeTO.add(cdd);
                }
                return listaCidadeTO;
            } else{
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }
    public int obterNovoIdCidade(Connection con) throws SQLException {
        String sql = "SELECT MAX(id_cidade) FROM T_CS_CIDADE";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) + 1;
        } else {
            return 1;
        }
    }

}
