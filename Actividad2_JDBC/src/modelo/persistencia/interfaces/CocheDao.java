package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Coche;

/**
 * Interfaz que define los métodos para interactuar con la persistencia de
 * coches.
 */
public interface CocheDao {

	/**
	 * Agrega un nuevo coche a la base de datos.
	 *
	 * @param coche el coche a agregar.
	 * @return true si el coche se agrega correctamente, false si ocurre algún
	 *         error.
	 */
	public boolean agregarCoche(Coche coche);

	/**
	 * Elimina un coche de la base de datos por su ID.
	 *
	 * @param id el ID del coche a eliminar.
	 * @return true si el coche se elimina correctamente, false si ocurre algún
	 *         error.
	 */
	public boolean eliminarCoche(int id);

	/**
	 * Busca un coche en la base de datos por su ID.
	 *
	 * @param id el ID del coche a buscar.
	 * @return el coche encontrado, o null si no se encuentra o si ocurre algún
	 *         error.
	 */
	public Coche buscarCochePorId(int id);

	/**
	 * Modifica la información de un coche en la base de datos.
	 *
	 * @param coche el coche con la información actualizada.
	 * @return true si el coche se modifica correctamente, false si ocurre algún
	 *         error.
	 */
	public boolean modificarCoche(Coche coche);

	/**
	 * Obtiene una lista de todos los coches almacenados en la base de datos.
	 *
	 * @return una lista de coches, o null si ocurre algún error.
	 */
	public List<Coche> listarCoches();
}
