package modelo.persistencia.interfaces;

import java.util.List;
import modelo.entidad.Pasajero;

/**
 * Interfaz que define los métodos para interactuar con la persistencia de
 * pasajeros.
 */
public interface PasajeroDao {

	/**
	 * Crea un nuevo pasajero en la base de datos.
	 *
	 * @param pasajero el pasajero a crear.
	 * @return true si el pasajero se crea correctamente, false si ocurre algún
	 *         error.
	 */
	public boolean crearPasajero(Pasajero pasajero);

	/**
	 * Elimina un pasajero de la base de datos por su ID.
	 *
	 * @param id el ID del pasajero a eliminar.
	 * @return true si el pasajero se elimina correctamente, false si ocurre algún
	 *         error.
	 */
	public boolean eliminarPasajero(int id);

	/**
	 * Busca un pasajero en la base de datos por su ID.
	 *
	 * @param id el ID del pasajero a buscar.
	 * @return el pasajero encontrado, o null si no se encuentra o si ocurre algún
	 *         error.
	 */
	public Pasajero buscarPasajeroPorId(int id);

	/**
	 * Obtiene una lista de todos los pasajeros almacenados en la base de datos.
	 *
	 * @return una lista de pasajeros, o null si ocurre algún error.
	 */
	public List<Pasajero> listarPasajeros();

	/**
	 * Asocia un pasajero a un coche en la base de datos.
	 *
	 * @param idPasajero el ID del pasajero a asociar.
	 * @param idCoche    el ID del coche al que se asociará el pasajero.
	 * @return true si el pasajero se asocia al coche correctamente, false si ocurre
	 *         algún error.
	 */
	public boolean agregarPasajeroACoche(int idPasajero, int idCoche);

	/**
	 * Desasocia un pasajero de un coche en la base de datos.
	 *
	 * @param idPasajero el ID del pasajero a desasociar.
	 * @param idCoche    el ID del coche del que se desasociará el pasajero.
	 * @return true si el pasajero se desasocia del coche correctamente, false si
	 *         ocurre algún error.
	 */
	public boolean eliminarPasajeroDeCoche(int idPasajero, int idCoche);

	/**
	 * Obtiene una lista de pasajeros asociados a un coche en la base de datos.
	 *
	 * @param idCoche el ID del coche del que se obtendrán los pasajeros asociados.
	 * @return una lista de pasajeros asociados al coche especificado, o null si
	 *         ocurre algún error.
	 */
	public List<Pasajero> listarPasajerosDeCoche(int idCoche);
}
