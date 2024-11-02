package br.com.carroseguro.dao;

import br.com.carroseguro.to.CarroTO;

import java.sql.*;
import java.util.ArrayList;

public class CarroDAO extends Repository{

    public CarroTO inserir(CarroTO carroTO) {
        String sql = "insert into T_CS_CARRO(mc_carro, md_carro, T_CS_USUARIO_ID_USUARIO) values(?, ?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql, new String[]{"ID_CARRO"})) {
            ps.setString(1, carroTO.getMarcaCarro());
            ps.setString(2, carroTO.getModeloCarro());
            ps.setInt(3, carroTO.getIdUsuario());
            if (ps.executeUpdate() > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        carroTO.setIdCarro(rs.getInt(1));
                    }
                }
                return carroTO;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL ao inserir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }




    public CarroTO alterar(CarroTO carroTO) {
        String sql = "UPDATE T_CS_CARRO SET md_carro=?, mc_carro=?, t_cs_usuario_id_usuario=? WHERE id_carro=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ps.setString(1, carroTO.getModeloCarro());
            ps.setString(2, carroTO.getMarcaCarro());
            ps.setInt(3, carroTO.getIdUsuario());
            ps.setInt(4, carroTO.getIdCarro());
            if (ps.executeUpdate() > 0) {
                return carroTO;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());;
        } finally {
            closeConnection();
        }
        return null;
    }

    public boolean excluir(int idCarro) {
        String sql = "delete from T_CS_CARRO where id_carro=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);){
            ps.setInt(1, idCarro);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro de SQL ao excluir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }

    public ArrayList<CarroTO> listarTodos() {
        String sql = "select * from T_CS_CARRO order by id_carro";
        ArrayList<CarroTO> listaCarroTO = new ArrayList<CarroTO>();
        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    CarroTO carroTO = new CarroTO();
                    carroTO.setIdCarro(rs.getInt("id_carro"));
                    carroTO.setIdUsuario(rs.getInt("t_cs_usuario_id_usuario"));
                    carroTO.setModeloCarro(rs.getString("md_carro"));
                    carroTO.setMarcaCarro(rs.getString("mc_carro"));
                    listaCarroTO.add(carroTO);
                }
                return listaCarroTO;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }

}



