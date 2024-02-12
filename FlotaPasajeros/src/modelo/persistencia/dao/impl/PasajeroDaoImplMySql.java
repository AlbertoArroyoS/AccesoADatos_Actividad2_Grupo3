package modelo.persistencia.dao.impl;

import modelo.persistencia.dao.PasajeroDao;
import modelo.entidad.Pasajero;
import config.ConfigBBDD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz PasajeroDao para MySQL usando JDBC.
 */
public class PasajeroDaoImplMySql implements PasajeroDao {

    @Override
    public boolean add(Pasajero pasajero) {
        String sql = "INSERT INTO pasajeros (nombre, edad, peso) VALUES (?, ?, ?)";
        try (Connection conn = ConfigBBDD.crearConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pasajero.getNombre());
            ps.setInt(2, pasajero.getEdad());
            ps.setDouble(3, pasajero.getPeso());
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Pasajero pasajero) {
        String sql = "UPDATE pasajeros SET nombre = ?, edad = ?, peso = ? WHERE id = ?";
        try (Connection conn = ConfigBBDD.crearConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pasajero.getNombre());
            ps.setInt(2, pasajero.getEdad());
            ps.setDouble(3, pasajero.getPeso());
            ps.setInt(4, pasajero.getId());
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM pasajeros WHERE id = ?";
        try (Connection conn = ConfigBBDD.crearConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Pasajero searchById(int id) {
        String sql = "SELECT * FROM pasajeros WHERE id = ?";
        try (Connection conn = ConfigBBDD.crearConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Pasajero pasajero = new Pasajero();
                    pasajero.setId(rs.getInt("id"));
                    pasajero.setNombre(rs.getString("nombre"));
                    pasajero.setEdad(rs.getInt("edad"));
                    pasajero.setPeso(rs.getDouble("peso"));
                    return pasajero;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Pasajero> list() {
        List<Pasajero> pasajeros = new ArrayList<>();
        String sql = "SELECT * FROM pasajeros";
        try (Connection conn = ConfigBBDD.crearConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Pasajero pasajero = new Pasajero();
                pasajero.setId(rs.getInt("id"));
                pasajero.setNombre(rs.getString("nombre"));
                pasajero.setEdad(rs.getInt("edad"));
                pasajero.setPeso(rs.getDouble("peso"));
                pasajeros.add(pasajero);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pasajeros;
    }
}
