package modelo.negocio;

import java.util.List;

import modelo.entidad.PasajeroEnCoche;
import modelo.persistencia.dao.PasajeroEnCocheDao;
import modelo.persistencia.dao.impl.PasajeroEnCocheImplMySql;

public class GestorPasajeroEnCoche {

	private PasajeroEnCocheDao pasajeroEnCocheDao = new PasajeroEnCocheImplMySql();
	private GestorCoche gestorCoche = new GestorCoche();
	private GestorPasajero gestorPasajero = new GestorPasajero();
	
	/**
     * Agrega un pasajero a un coche, con validaciones previas.
     * 
     * @param idPasajero El ID del pasajero a agregar.
     * @param idCoche El ID del coche al cual agregar el pasajero.
     * @return true si el pasajero fue agregado al coche con éxito, false en caso contrario.
     */
    public boolean agregarPasajeroACoche(int idPasajero, int idCoche) {
        // Validar la existencia del coche y del pasajero
        if (gestorCoche.buscarCoche(idCoche) != null && gestorPasajero.buscarPasajeroPorId(idPasajero) != null) {
            return pasajeroEnCocheDao.agregarPasajeroACoche(idPasajero, idCoche);
        }
        return false;
    }

    /**
     * Elimina un pasajero de cualquier coche en el que esté.
     * 
     * @param idPasajero El ID del pasajero a eliminar.
     * @return true si el pasajero fue eliminado de un coche con éxito, false en caso contrario.
     */
    public boolean eliminarPasajeroDeCoche(int idPasajero) {
        return pasajeroEnCocheDao.eliminarPasajeroDeCoche(idPasajero);
    }

    /**
     * Lista todos los pasajeros de un coche específico.
     * 
     * @param idCoche El ID del coche del cual listar los pasajeros.
     * @return Una lista de pasajeros asociados al coche.
     */
    public List<PasajeroEnCoche> listarPasajerosPorCoche(int idCoche) {
        return pasajeroEnCocheDao.listarPasajerosPorCoche(idCoche);
    }
	
}
