package br.com.carroseguro.dao;

import br.com.carroseguro.to.LogradouroTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LogradouroDAO {
    private Connection con;

    public LogradouroDAO(Connection con) {
        this.con = con;
    }
    public Connection getCon() {
        return con;
    }

    public String inserir(LogradouroTO logradouroTO) {
        String sql = "insert into T_CS_LOGRADOURO(id_logradouro, nm_logradouro, nr_logradouro, nr_cep,t_cs_bairro_id_bairro, t_cs_usuario_id_usuario) values(?,?,?,?,?,?)";
        try(PreparedStatement ps = getCon().prepareStatement(sql);) {
            ps.setInt(1, logradouroTO.getIdLogradouro());
            ps.setString(2, logradouroTO.getNmLogradouro());
            ps.setInt(3, logradouroTO.getNrLogradouro());
            ps.setInt(4, logradouroTO.getNrCep());
            ps.setInt(5, logradouroTO.getIdBairro());
            ps.setInt(6, logradouroTO.getIdUsuario());
            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso";
            } else{
                return "Erro ao inserir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }
    public String alterar(LogradouroTO logradouroTO) {
        String sql = "update T_CS_LOGRADOURO set nm_logradouro=?, nr_logradouro=?, nr_cep=?,id_usuario=?,id_bairro=? where id_logradouro=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql);) {
            ps.setString(1, logradouroTO.getNmLogradouro());
            ps.setInt(2, logradouroTO.getNrLogradouro());
            ps.setInt(3, logradouroTO.getNrCep());
            ps.setInt(4, logradouroTO.getIdUsuario());
            ps.setInt(5, logradouroTO.getIdBairro());
            ps.setInt(6, logradouroTO.getIdLogradouro());
            if (ps.executeUpdate() > 0) {
                return "Alterado com sucesso";
            } else {
                return "Erro ao alterar";
            }
        } catch(SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }
    public String excluir(LogradouroTO logradouroTO) {
        String sql = "delete from T_CS_LOGRADOURO where id_logradouro=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql);){
            ps.setInt(1, logradouroTO.getIdLogradouro());
            if (ps.executeUpdate() > 0) {
                return "Excluído com sucesso";
            } else {
                return "Erro ao deletar";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }
    public ArrayList<LogradouroTO> listarTodos() {
        String sql = "select * from T_CS_LOGRADOURO order by id_logradouro";
        ArrayList<LogradouroTO> listaLogradouroTO = new ArrayList<LogradouroTO>();
        try(PreparedStatement ps = getCon().prepareStatement(sql);) {
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
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }
    public int obterNovoIdLogradouro(Connection con) throws SQLException {
        String sql = "SELECT MAX(id_logradouro) FROM T_CS_LOGRADOURO";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) + 1;
        } else {
            return 1;
        }
    }



}





