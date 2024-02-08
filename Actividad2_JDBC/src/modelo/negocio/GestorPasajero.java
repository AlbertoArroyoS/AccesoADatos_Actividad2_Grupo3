package modelo.negocio;

import java.util.List;
import modelo.entidad.Pasajero;
import modelo.persistencia.PasajeroDaoMySql;

/**
 * GestorPasajero es una clase que gestiona las operaciones relacionadas con los
 * pasajeros. Se encarga de interactuar con el objeto PasajeroDaoMySql para
 * realizar operaciones CRUD en la base de datos.
 */
public class GestorPasajero {

	private PasajeroDaoMySql pasajeroDao;

	/**
	 * Constructor de la clase GestorPasajero. Inicializa el objeto PasajeroDaoMySql
	 * para interactuar con la capa de persistencia.
	 */
	public GestorPasajero() {
		this.pasajeroDao = new PasajeroDaoMySql();
	}

	/**
	 * Crea un nuevo pasajero en la base de datos.
	 *
	 * @param pasajero el pasajero a crear.
	 * @return true si el pasajero se crea correctamente, false si hay un error.
	 */
	public boolean crearNuevoPasajero(Pasajero pasajero) {
		return pasajeroDao.crearPasajero(pasajero);
	}

	/**
	 * Borra un pasajero de la base de datos por su identificador.
	 *
	 * @param id el identificador del pasajero a borrar.
	 * @return true si el pasajero se borra correctamente, false si hay un error.
	 */
	public boolean borrarPasajeroPorId(int id) {
		return pasajeroDao.eliminarPasajero(id);
	}

	/**
	 * Consulta un pasajero en la base de datos por su identificador.
	 *
	 * @param id el identificador del pasajero a consultar.
	 * @return el pasajero encontrado, null si no se encuentra.
	 */
	public Pasajero consultarPasajeroPorId(int id) {
		return pasajeroDao.buscarPasajeroPorId(id);
	}

	/**
	 * Obtiene una lista de todos los pasajeros almacenados en la base de datos.
	 *
	 * @return una lista de pasajeros.
	 */
	public List<Pasajero> listarTodosLosPasajeros() {
		return pasajeroDao.listarPasajeros();
	}

	/**
	 * Añade un pasajero a un coche en la base de datos.
	 *
	 * @param idPasajero el identificador del pasajero a añadir.
	 * @param idCoche    el identificador del coche al que se añadirá el pasajero.
	 * @return true si el pasajero se añade correctamente al coche, false si ya
	 *         estaba asociado o hay un error.
	 */
	public boolean añadirPasajeroACoche(int idPasajero, int idCoche) {
		if (verificarPasajeroEnCoche(idPasajero, idCoche)) {
			return false; // El pasajero ya está asociado a este coche
		} else {
			return pasajeroDao.agregarPasajeroACoche(idPasajero, idCoche);
		}
	}

	/**
	 * Elimina un pasajero de un coche en la base de datos.
	 *
	 * @param idPasajero el identificador del pasajero a eliminar.
	 * @param idCoche    el identificador del coche del que se eliminará el
	 *                   pasajero.
	 * @return true si el pasajero se elimina correctamente del coche, false si no
	 *         estaba asociado o hay un error.
	 */
	public boolean eliminarPasajeroDeCoche(int idPasajero, int idCoche) {
		return pasajeroDao.eliminarPasajeroDeCoche(idPasajero, idCoche);
	}

	/**
	 * Obtiene una lista de pasajeros asociados a un coche en la base de datos.
	 *
	 * @param idCoche el identificador del coche del que se listarán los pasajeros.
	 * @return una lista de pasajeros asociados al coche especificado.
	 */
	public List<Pasajero> listarPasajerosDeCoche(int idCoche) {
		return pasajeroDao.listarPasajerosDeCoche(idCoche);
	}

	/**
	 * Verifica si un pasajero está asociado a un coche en la base de datos.
	 *
	 * @param idPasajero el identificador del pasajero.
	 * @param idCoche    el identificador del coche.
	 * @return true si el pasajero está asociado al coche, false si no lo está.
	 */
	private boolean verificarPasajeroEnCoche(int idPasajero, int idCoche) {
		List<Pasajero> pasajerosDelCoche = pasajeroDao.listarPasajerosDeCoche(idCoche);
		for (Pasajero pasajero : pasajerosDelCoche) {
			if (pasajero.getId() == idPasajero) {
				return true; // El pasajero ya está asociado a este coche
			}
		}
		return false; // El pasajero no está asociado a este coche
	}
}
