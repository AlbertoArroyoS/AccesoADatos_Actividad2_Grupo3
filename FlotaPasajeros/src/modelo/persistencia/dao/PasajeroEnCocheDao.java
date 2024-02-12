package modelo.persistencia.dao;

import java.util.List;

import modelo.entidad.PasajeroEnCoche;

/**
 * Interfaz para el acceso a datos de la relación entre Pasajero y Coche.
 */
public interface PasajeroEnCocheDao {
    boolean agregarPasajeroACoche(int idPasajero, int idCoche);
    boolean eliminarPasajeroDeCoche(int idPasajero);
    List<PasajeroEnCoche> listarPasajerosPorCoche(int idCoche);
}
