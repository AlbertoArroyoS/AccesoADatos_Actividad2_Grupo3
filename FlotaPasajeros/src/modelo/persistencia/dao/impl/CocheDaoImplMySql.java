package modelo.persistencia.dao.impl;

import modelo.entidad.Coche;
import modelo.persistencia.dao.CocheDao;
import config.ConfigBBDD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz CocheDao para MySQL utilizando JDBC.
 */
public class CocheDaoImplMySql implements CocheDao {

    /**
     * Inserta un nuevo coche en la base de datos.
     *
     * @param coche el objeto Coche a insertar.
     * @return true si la inserción fue exitosa, false de lo contrario.
     */
    @Override
    public boolean add(Coche coche) {
        String sql = "INSERT INTO coches (marca, modelo, año, km) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConfigBBDD.crearConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, coche.getMarca());
            ps.setString(2, coche.getModelo());
            ps.setInt(3, coche.getAño());
            ps.setString(4, coche.getKm());
            
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Actualiza los datos de un coche existente en la base de datos.
     *
     * @param coche el objeto Coche a actualizar.
     * @return true si la actualización fue exitosa, false de lo contrario.
     */
    @Override
    public boolean update(Coche coche) {
        String sql = "UPDATE coches SET marca = ?, modelo = ?, año = ?, km = ? WHERE id = ?";
        try (Connection conn = ConfigBBDD.crearConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, coche.getMarca());
            ps.setString(2, coche.getModelo());
            ps.setInt(3, coche.getAño());
            ps.setString(4, coche.getKm());
            ps.setInt(5, coche.getId());
            
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Elimina un coche de la base de datos por su ID.
     *
     * @param id el ID del coche a eliminar.
     * @return true si la eliminación fue exitosa, false de lo contrario.
     */
    @Override
    public boolean delete(int id) {
    	String sql = "DELETE FROM coches WHERE id = ?";
        try (Connection conn = ConfigBBDD.crearConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Busca un coche en la base de datos por su ID.
     *
     * @param id el ID del coche a buscar.
     * @return el objeto Coche si fue encontrado, null de lo contrario.
     */
    @Override
    public Coche searchById(int id) {
        String sql = "SELECT * FROM coches WHERE id = ?";
        try (Connection conn = ConfigBBDD.crearConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Coche coche = new Coche();
                coche.setId(rs.getInt("id"));
                coche.setMarca(rs.getString("marca"));
                coche.setModelo(rs.getString("modelo"));
                coche.setAño(rs.getInt("año"));
                coche.setKm(rs.getString("km"));
                return coche;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Lista todos los coches en la base de datos.
     *
     * @return una lista de objetos Coche.
     */
    public List<Coche> list() {
        List<Coche> coches = new ArrayList<>();
        String sql = "SELECT * FROM coches";
        try (Connection conn = ConfigBBDD.crearConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Coche coche = new Coche();
                coche.setId(rs.getInt("id"));
                coche.setMarca(rs.getString("marca"));
                coche.setModelo(rs.getString("modelo"));
                coche.setAño(rs.getInt("año"));
                coche.setKm(rs.getString("km"));
                coches.add(coche);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coches;
    }

        		
}
