package br.com.carroseguro.dao;

import br.com.carroseguro.to.MecanicoTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MecanicoDAO extends Repository{
    public ArrayList<MecanicoTO> listarTodos() {
        String sql = "select * from T_CS_MECANICO order by id_mecanico";
        ArrayList<MecanicoTO> listaMecanico = new ArrayList<MecanicoTO>();
        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    MecanicoTO mecanico = new MecanicoTO();
                    mecanico.setIdMecanico(rs.getInt("id_mecanico"));
                    mecanico.setZonaMecanico(rs.getString("zonas_mecanico"));
                    mecanico.setNomeOficina(rs.getString("nome_oficina"));
                    mecanico.setEnderecoOficina(rs.getString("endereco_oficina"));
                    listaMecanico.add(mecanico);
                }
                return listaMecanico;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }

    public MecanicoTO vizualizarPeloCodigo(int idMecanico) {
        MecanicoTO mecanico = new MecanicoTO();
        String sql = "select * from T_CS_MECANICO where id_mecanico=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);){
            ps.setInt(1, idMecanico);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                mecanico.setIdMecanico(rs.getInt("id_mecanico"));
                mecanico.setZonaMecanico(rs.getString("zonas_mecanico"));
                mecanico.setEnderecoOficina(rs.getString("endereco_oficina"));
                mecanico.setNomeOficina(rs.getString("nome_oficina"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL ao mostrar peça: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return mecanico;
    }


}
