package modelo.persistencia.dao.impl;

import modelo.persistencia.dao.PasajeroEnCocheDao;
import modelo.entidad.PasajeroEnCoche;
import config.ConfigBBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz PasajeroEnCocheDao para MySQL usando JDBC.
 */
public class PasajeroEnCocheImplMySql implements PasajeroEnCocheDao {

    @Override
    public boolean agregarPasajeroACoche(int idPasajero, int idCoche) {
        String sql = "INSERT INTO pasajeros_en_coches (id_coche, id_pasajero) VALUES (?, ?)";
        try (Connection conn = ConfigBBDD.crearConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCoche);
            ps.setInt(2, idPasajero);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean eliminarPasajeroDeCoche(int idPasajero) {
        String sql = "DELETE FROM pasajeros_en_coches WHERE id_pasajero = ?";
        try (Connection conn = ConfigBBDD.crearConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPasajero);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<PasajeroEnCoche> listarPasajerosPorCoche(int idCoche) {
        List<PasajeroEnCoche> pasajerosEnCochesAux = new ArrayList<>();
        String sql = "SELECT * FROM pasajeros_en_coches WHERE id_coche = ?";
        try (Connection conn = ConfigBBDD.crearConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCoche);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    PasajeroEnCoche pasajeroEnCoche = new PasajeroEnCoche();
                    pasajeroEnCoche.setIdPasajero(rs.getInt("id_pasajero"));
                    pasajeroEnCoche.setIdCoche(rs.getInt("id_coche"));
                    pasajerosEnCochesAux.add(pasajeroEnCoche);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pasajerosEnCochesAux;
    }
}
