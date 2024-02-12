package modelo.persistencia.dao;

import java.util.List;

import modelo.entidad.Pasajero;

/**
 * Interfaz para el acceso a datos de la entidad Pasajero. Define las operaciones CRUD estándar.
 */
public interface PasajeroDao {
    boolean add(Pasajero pasajero);
    boolean update(Pasajero pasajero);
    boolean delete(int id);
    Pasajero searchById(int id);
    List<Pasajero> list();
}
