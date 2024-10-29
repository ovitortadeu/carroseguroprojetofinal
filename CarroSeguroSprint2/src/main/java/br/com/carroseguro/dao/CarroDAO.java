package br.com.carroseguro.dao;

import br.com.carroseguro.to.CarroTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarroDAO extends Repository{

    public CarroTO inserir(CarroTO carroTO) {
        String sql = "insert into T_CS_CARRO(id_carro, mc_carro, md_carro, T_CS_USUARIO_ID_USUARIO) values(?,?,?,?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ps.setInt(1, carroTO.getIdCarro());
            ps.setString(2, carroTO.getMarcaCarro());
            ps.setString(3, carroTO.getModeloCarro());
            ps.setInt(4, carroTO.getIdUsuario());
            if (ps.executeUpdate() > 0) {
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
        String sql = "update T_CS_CARRO set md_carro=?, mc_carro=?, t_cs_usuario_id_usuario where id_carro=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ps.setString(1, carroTO.getModeloCarro());
            ps.setString(2, carroTO.getModeloCarro());
            ps.setInt(3, carroTO.getIdCarro());
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

    public int obterNovoIdCarro(CarroTO carroTO) throws SQLException {
        String sql = "SELECT MAX(id_carro) FROM T_CS_CARRO";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) + 1;
        } else {
            return 1;
        }


    }
}



