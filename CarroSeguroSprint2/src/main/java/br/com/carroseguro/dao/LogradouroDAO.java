package br.com.carroseguro.dao;

import br.com.carroseguro.to.LogradouroTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LogradouroDAO extends Repository{

    public LogradouroTO inserir(LogradouroTO logradouroTO) {
        String sql = "insert into T_CS_LOGRADOURO(id_logradouro, nm_logradouro, nr_logradouro, nr_cep,t_cs_bairro_id_bairro, t_cs_usuario_id_usuario) values(?,?,?,?,?,?)";
        try(PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ps.setInt(1, logradouroTO.getIdLogradouro());
            ps.setString(2, logradouroTO.getNmLogradouro());
            ps.setInt(3, logradouroTO.getNrLogradouro());
            ps.setInt(4, logradouroTO.getNrCep());
            ps.setInt(5, logradouroTO.getIdBairro());
            ps.setInt(6, logradouroTO.getIdUsuario());
            if (ps.executeUpdate() > 0) {
                return logradouroTO;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL ao inserir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }
    public LogradouroTO alterar(LogradouroTO logradouroTO) {
        String sql = "update T_CS_LOGRADOURO set nm_logradouro=?, nr_logradouro=?, nr_cep=?,id_usuario=?,id_bairro=? where id_logradouro=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ps.setString(1, logradouroTO.getNmLogradouro());
            ps.setInt(2, logradouroTO.getNrLogradouro());
            ps.setInt(3, logradouroTO.getNrCep());
            ps.setInt(4, logradouroTO.getIdUsuario());
            ps.setInt(5, logradouroTO.getIdBairro());
            ps.setInt(6, logradouroTO.getIdLogradouro());
            if (ps.executeUpdate() > 0) {
                return logradouroTO;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL ao inserir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }
    public boolean excluir(int idLogradouro) {
        String sql = "delete from T_CS_LOGRADOURO where id_logradouro=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);){
            ps.setInt(1, idLogradouro);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro de SQL ao excluir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }
    public ArrayList<LogradouroTO> listarTodos() {
        String sql = "select * from T_CS_LOGRADOURO order by id_logradouro";
        ArrayList<LogradouroTO> listaLogradouroTO = new ArrayList<LogradouroTO>();
        try(PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    LogradouroTO logradouroTO = new LogradouroTO();
                    logradouroTO.setIdLogradouro(rs.getInt("id_logradouro"));
                    logradouroTO.setNmLogradouro(rs.getString("nm_logradouro"));
                    logradouroTO.setNrLogradouro(rs.getInt("nr_logradouro"));
                    logradouroTO.setNrCep(rs.getInt("nr_cep"));
                    listaLogradouroTO.add(logradouroTO);
                }
                return listaLogradouroTO;
            } else{
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL na consulta: " + e.getMessage());
            return null;
        } finally {
            closeConnection();
        }
    }
    public int obterNovoIdLogradouro(LogradouroTO logradouroTO) throws SQLException {
        String sql = "SELECT MAX(id_logradouro) FROM T_CS_LOGRADOURO";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) + 1;
        } else {
            return 1;
        }
    }



}





