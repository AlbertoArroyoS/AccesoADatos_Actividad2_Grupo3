package modelo.negocio;

import java.util.List;

import modelo.entidad.Pasajero;
import modelo.persistencia.dao.PasajeroDao;
import modelo.persistencia.dao.impl.PasajeroDaoImplMySql;

/**
 * Clase que gestiona la lógica de negocio para la entidad Pasajero.
 */
public class GestorPasajero {
	private PasajeroDao pasajeroDao = new PasajeroDaoImplMySql();
	
    /**
     * Agrega un nuevo pasajero a la base de datos si pasa las validaciones.
     * 
     * @param pasajero El pasajero a agregar.
     * @return true si el pasajero se ha agregado con éxito, false en caso contrario.
     */
    public boolean agregarPasajero(Pasajero pasajero) {
        if (validarPasajero(pasajero)) {
            return pasajeroDao.add(pasajero);
        }
        return false;
    }

    /**
     * Actualiza los datos de un pasajero existente si pasa las validaciones.
     * 
     * @param pasajero El pasajero con los datos actualizados.
     * @return true si el pasajero se ha actualizado con éxito, false en caso contrario.
     */
    public boolean actualizarPasajero(Pasajero pasajero) {
        if (validarPasajero(pasajero) && pasajero.getId() > 0) {
            return pasajeroDao.update(pasajero);
        }
        return false;
    }

    /**
     * Elimina un pasajero de la base de datos por su ID.
     * 
     * @param id El ID del pasajero a eliminar.
     * @return true si el pasajero se ha eliminado con éxito, false en caso contrario.
     */
    public boolean eliminarPasajero(int id) {
        return pasajeroDao.delete(id);
    }

    /**
     * Busca un pasajero en la base de datos por su ID.
     * 
     * @param id El ID del pasajero a buscar.
     * @return El pasajero encontrado o null si no existe.
     */
    public Pasajero buscarPasajeroPorId(int id) {
        return pasajeroDao.searchById(id);
    }

    /**
     * Lista todos los pasajeros registrados en la base de datos.
     * 
     * @return Una lista de pasajeros.
     */
    public List<Pasajero> listarPasajeros() {
        return pasajeroDao.list();
    }

    /**
     * Realiza la validación de los datos del pasajero.
     * 
     * @param pasajero El pasajero a validar.
     * @return true si el pasajero es válido, false en caso contrario.
     */
    private boolean validarPasajero(Pasajero pasajero) {
        if (pasajero == null) {
            return false;
        }
        if (pasajero.getNombre().isBlank()) {
            return false;
        }
        if (pasajero.getEdad() <= 0) {
            return false;
        }
        if (pasajero.getPeso() <= 0) {
            return false;
        }

        return true;
    }
}
