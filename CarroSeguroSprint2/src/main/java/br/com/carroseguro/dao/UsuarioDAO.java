package br.com.carroseguro.dao;

import br.com.carroseguro.to.UsuarioTO;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO extends Repository{

    public UsuarioTO inserir(UsuarioTO usuarioTO) {
        String sql = "INSERT INTO T_CS_USUARIO(id_usuario, us_cpf, nm_usuario, em_usuario, sn_usuario) VALUES(?,?,?,?,?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, usuarioTO.getIdUsuario());
            ps.setBigDecimal(2, new BigDecimal(usuarioTO.getUsCPF()));
            ps.setString(3, usuarioTO.getNmUsuario());
            ps.setString(4, usuarioTO.getEmailUsuario());
            ps.setString(5, usuarioTO.getSenhaUsuario());
            if (ps.executeUpdate() > 0) {
                return usuarioTO;
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 1) { // Código de erro para duplicidade em Oracle
                System.out.println("CPF já cadastrado.");
            } else {
                System.out.println("Erro de SQL: " + e.getMessage());
            }
        } finally {
            closeConnection();
        }
        return null;
    }


    public UsuarioTO alterar(UsuarioTO usuarioTO) {
        String sql = "update T_CS_USUARIO set nm_usuario=?, us_cpf=?, em_usuario=?, sn_usuario=? where id_usuario=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ps.setString(1, usuarioTO.getNmUsuario());;
            ps.setLong(2, usuarioTO.getUsCPF());
            ps.setString(3, usuarioTO.getEmailUsuario());
            ps.setString(4, usuarioTO.getSenhaUsuario());
            ps.setInt(5, usuarioTO.getIdUsuario());
            if (ps.executeUpdate() > 0) {
                return usuarioTO;
            }
        } catch(SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());;
        } finally {
            closeConnection();
        }
        return null;
    }

    public boolean excluir(int idUsuario) {
        String sql = "delete from T_CS_USUARIO where id_usuario=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);){
            ps.setInt(1, idUsuario);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro de SQL ao excluir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }
    public UsuarioTO vizualizarPeloCodigo(int idUsuario) {
        UsuarioTO user = new UsuarioTO();
        String sql = "select * from T_CS_USUARIO where id_usuario=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);){
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                user.setIdUsuario(rs.getInt("id_usuario"));
                user.setUsCPF(rs.getLong("us_cpf"));
                user.setNmUsuario(rs.getString("nm_usuario"));
                user.setEmailUsuario(rs.getString("em_usuario"));
                user.setSenhaUsuario(rs.getString("sn_usuario"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL ao mostrar usuário: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return user;
    }

    public ArrayList<UsuarioTO> listarTodos() {
        String sql = "select * from T_CS_USUARIO order by id_usuario";
        ArrayList<UsuarioTO> listaUsuarioTO = new ArrayList<UsuarioTO>();
        try(PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    UsuarioTO usuarioTO = new UsuarioTO();
                    usuarioTO.setIdUsuario(rs.getInt("id_usuario"));
                    usuarioTO.setUsCPF(rs.getLong("us_cpf"));
                    usuarioTO.setNmUsuario(rs.getString("nm_usuario"));
                    usuarioTO.setEmailUsuario(rs.getString("em_usuario"));
                    usuarioTO.setSenhaUsuario(rs.getString("sn_usuario"));
                    listaUsuarioTO.add(usuarioTO);
                }
                return listaUsuarioTO;
            } else{
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }

    public int obterNovoIdUsuario(UsuarioTO usuarioTO) throws SQLException {
        String sql = "SELECT MAX(id_usuario) FROM T_CS_USUARIO";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) + 1;
        } else {
            return 1;
        }
    }

    public boolean emailExistente(String email) {
        String sql = "SELECT 1 FROM T_CS_USUARIO WHERE em_usuario = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();  // Retorna true se encontrar o email
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar email: " + e.getMessage());
        }
        return false;
    }


}





