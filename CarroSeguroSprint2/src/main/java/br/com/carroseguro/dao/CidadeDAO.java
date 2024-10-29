package br.com.carroseguro.dao;

import br.com.carroseguro.to.CidadeTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CidadeDAO extends Repository{

    public CidadeTO inserir(CidadeTO cdd) {
        String sql = "insert into T_CS_CIDADE(id_cidade, nm_cidade, nr_ddd, t_cs_estado_id_estado) values(?,?,?,?)";
        try(PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ps.setInt(1, cdd.getIdCidade());
            ps.setString(2, cdd.getNmCidade());
            ps.setInt(3, cdd.getNrDDD());
            ps.setInt(4, cdd.getIdEstado());
            if (ps.executeUpdate() > 0) {
                return cdd;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL ao inserir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }
    public CidadeTO alterar(CidadeTO cdd) {
        String sql = "update T_CS_CIDADE set nm_cidade=?, nr_ddd=?, id_estado=? where id_cidade=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ps.setString(1, cdd.getNmCidade());
            ps.setInt(2, cdd.getNrDDD());
            ps.setInt(3, cdd.getIdEstado());
            ps.setInt(4, cdd.getIdCidade());
            if (ps.executeUpdate() > 0) {
                return cdd;
            }
        } catch(SQLException e) {
            System.out.println("Erro de SQL ao alterar: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }
    public boolean excluir(int idCidade) {
        String sql = "delete from T_CS_CIDADE where id_cidade=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);){
            ps.setInt(1, idCidade);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro de SQL ao apagar: " + e.getMessage());;
        } finally {
            closeConnection();
        }
        return false;
    }
    public ArrayList<CidadeTO> listarTodos() {
        String sql = "select * from T_CS_CIDADE order by id_cidade";
        ArrayList<CidadeTO> listaCidadeTO = new ArrayList<CidadeTO>();
        try(PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    CidadeTO cdd = new CidadeTO();
                    cdd.setIdCidade(rs.getInt("id_cidade"));
                    cdd.setNmCidade(rs.getString("nm_cidade"));
                    cdd.setNrDDD(rs.getInt("nr_ddd"));
                    listaCidadeTO.add(cdd);
                }
            } else{
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL na consulta: " + e.getMessage());
            return null;
        } finally {
            closeConnection();
        }
        return listaCidadeTO;
    }

    public boolean validarCidadeEDDD(String nomeCidade, int ddd) {
        String sql = "SELECT COUNT(*) FROM T_CS_CIDADE WHERE UPPER(nm_cidade) = UPPER(?) AND nr_ddd = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, nomeCidade);
            ps.setInt(2, ddd);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao validação: " + e.getMessage());
        }

        return false;
    }


    public int obterNovoIdCidade(CidadeTO cidadeTO) throws SQLException {
        String sql = "SELECT MAX(id_cidade) FROM T_CS_CIDADE";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) + 1;
        } else {
            return 1;
        }
    }

}
