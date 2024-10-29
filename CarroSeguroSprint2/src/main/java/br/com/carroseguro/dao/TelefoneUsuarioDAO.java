package br.com.carroseguro.dao;

import br.com.carroseguro.to.TelefoneUsuarioTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TelefoneUsuarioDAO extends Repository{


    public TelefoneUsuarioTO inserir(TelefoneUsuarioTO tel) {
        String sql = "insert into T_CS_TELEFONE_USUARIO(id_telefone, nr_ddi, nr_ddd, nr_telefone, tp_telefone, st_telefone, t_cs_usuario_id_usuario) values(?,?,?,?,?,?,?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ps.setInt(1, tel.getIdTelefone());
            ps.setInt(2, tel.getNrDDI());
            ps.setInt(3, tel.getNrDDD());
            ps.setInt(4, tel.getNrTelefone());
            ps.setString(5, tel.getTpTelefone());
            ps.setString(6, tel.getStTelefone());
            ps.setInt(7, tel.getIdUsuario());
            if (ps.executeUpdate() > 0) {
                return tel;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());;
        } finally {
            closeConnection();
        }
        return null;
    }

    public TelefoneUsuarioTO alterar(TelefoneUsuarioTO tel) {
        String sql = "update T_CS_TELEFONE_USUARIO set nr_ddi=?, nr_ddd=?, nr_telefone=?, tp_telefone=?, st_telefone=?, id_usuario=?  where id_telefone=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ps.setInt(1, tel.getNrDDI());
            ps.setInt(2, tel.getNrDDD());
            ps.setInt(3, tel.getNrTelefone());
            ps.setString(4, tel.getTpTelefone());
            ps.setString(5, tel.getStTelefone());
            ps.setInt(6, tel.getIdUsuario());
            ps.setInt(7, tel.getIdTelefone());
            if (ps.executeUpdate() > 0) {
                return tel;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());;
        } finally {
            closeConnection();
        }
        return null;
    }

    public boolean excluir(int idTelefoneUsuario) {
        String sql = "delete from T_CS_TELEFONE_USUARIO where id_telefone=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);){
            ps.setInt(1, idTelefoneUsuario);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro de SQL ao excluir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }

    public ArrayList<TelefoneUsuarioTO> listarTodos() {
        String sql = "select * from T_CS_TELEFONE_USUARIO order by id_telefone";
        ArrayList<TelefoneUsuarioTO> listaTelefones = new ArrayList<TelefoneUsuarioTO>();
        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    TelefoneUsuarioTO tel = new TelefoneUsuarioTO();
                    tel.setIdTelefone(rs.getInt("id_telefone"));
                    tel.setNrTelefone(rs.getInt("nr_telefone"));
                    tel.setNrDDD(rs.getInt("nr_ddd"));
                    tel.setNrDDI(rs.getInt("nr_ddi"));
                    tel.setStTelefone(rs.getString("st_telefone"));
                    tel.setTpTelefone(rs.getString("tp_telefone"));
                    listaTelefones.add(tel);
                }
                return listaTelefones;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }

    public int obterNovoIdTelefone(TelefoneUsuarioTO telTO) throws SQLException {
        String sql = "SELECT MAX(id_usuario) FROM T_CS_TELEFONE_USUARIO";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) + 1;
        } else {
            return 1;
        }


    }
}