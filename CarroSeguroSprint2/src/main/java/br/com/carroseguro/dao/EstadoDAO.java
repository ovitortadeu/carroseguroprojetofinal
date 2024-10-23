package br.com.carroseguro.dao;

import br.com.carroseguro.to.EstadoTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EstadoDAO extends Repository {

    public EstadoTO inserir(EstadoTO estadoTO) {
        String sql = "insert into T_CS_ESTADO(id_estado, sg_estado, nm_estado) values(?,?,?)";
        try(PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ps.setInt(1, estadoTO.getIdEstado());
            ps.setString(2, estadoTO.getSgEstado());
            ps.setString(3, estadoTO.getNmEstado());
            if (ps.executeUpdate() > 0) {
                return estadoTO;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }
    public EstadoTO alterar(EstadoTO estadoTO) {
        String sql = "update T_CS_ESTADO set nm_estado=?, sg_estado=? where id_estado=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ps.setString(1, estadoTO.getNmEstado());
            ps.setString(2, estadoTO.getSgEstado());
            ps.setInt(3, estadoTO.getIdEstado());
            if (ps.executeUpdate() > 0) {
                return estadoTO;
            } else {
                return null;
            }
        } catch(SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());;
        } finally {
            closeConnection();
        }
        return null;
    }
    public boolean excluir(int idEstado) {
        String sql = "delete from T_CS_ESTADO where id_estado=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);){
            ps.setInt(1, idEstado);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }

    public ArrayList<EstadoTO> listarTodos() {
        String sql = "select * from T_CS_ESTADO order by id_estado";
        ArrayList<EstadoTO> listaEstadoTO = new ArrayList<EstadoTO>();
        try(PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    EstadoTO estadoTO = new EstadoTO();
                    estadoTO.setIdEstado(rs.getInt("id_estado"));
                    estadoTO.setNmEstado(rs.getString("nm_estado"));
                    estadoTO.setSgEstado(rs.getString("sg_estado"));
                    listaEstadoTO.add(estadoTO);
                }
            } else {
                return null;
            }
        } catch(SQLException e){
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally{
            closeConnection();
        }
        return listaEstadoTO;
    }

    public int obterNovoIdEstado(Connection con) throws SQLException {
        String sql = "SELECT MAX(id_estado) FROM T_CS_ESTADO";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) + 1;
            } else {
                return 1;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return 0;
    }
}
