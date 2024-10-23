package br.com.carroseguro.dao;

import br.com.carroseguro.to.BairroTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BairroDAO {
    private Connection con;

    public BairroDAO(Connection con) {
        this.con = con;
    }
    public Connection getCon() {
        return con;
    }

    public String inserir(BairroTO brr) {
        String sql = "insert into T_CS_BAIRRO(id_bairro, nm_bairro, nm_zona_bairro, t_cs_cidade_id_cidade) values(?,?,?,?)";
        try(PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, brr.getIdBairro());
            ps.setString(2, brr.getNmBairro());
            ps.setString(3, brr.getNmZonaBairro());
            ps.setInt(4, brr.getIdCidade());
            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso";
            } else{
                return "Erro ao inserir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }
    public String alterar(BairroTO brr) {
        String sql = "update T_CS_BAIRRO set nm_bairro=?, nm_zona_bairro=? where id_bairro=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql);) {
            ps.setString(1, brr.getNmBairro());
            ps.setString(2, brr.getNmZonaBairro());
            ps.setInt(3, brr.getIdBairro());
            if (ps.executeUpdate() > 0) {
                return "Alterado com sucesso";
            } else {
                return "Erro ao alterar";
            }
        } catch(SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }
    public String excluir(BairroTO brr) {
        String sql = "delete from T_CS_BAIRRO where id_bairro=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql);){
            ps.setInt(1, brr.getIdBairro());
            if (ps.executeUpdate() > 0) {
                return "Excluído com sucesso";
            } else {
                return "Erro ao deletar";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }
    public ArrayList<BairroTO> listarTodos() {
        String sql = "select * from T_CS_BAIRRO order by id_bairro";
        ArrayList<BairroTO> listaBairroTO = new ArrayList<BairroTO>();
        try(PreparedStatement ps = getCon().prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    BairroTO brr = new BairroTO();
                    brr.setIdBairro(rs.getInt("id_bairro"));
                    brr.setNmBairro(rs.getString("nm_bairro"));
                    brr.setNmZonaBairro(rs.getString("nm_zona_bairro"));
                    listaBairroTO.add(brr);
                }
                return listaBairroTO;
            } else{
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }
    public int obterNovoIdBairro(Connection con) throws SQLException {
        String sql = "SELECT MAX(id_bairro) FROM T_CS_BAIRRO";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) + 1;
        } else {
            return 1;
        }
    }

}
