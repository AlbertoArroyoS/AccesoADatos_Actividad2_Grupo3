package modelo.negocio;

import java.util.List;
import modelo.entidad.Coche;
import modelo.persistencia.CocheDaoMySql;

/**
 * GestorCoche es una clase que gestiona las operaciones relacionadas con los
 * coches. Se encarga de interactuar con el objeto CocheDaoMySql para realizar
 * operaciones CRUD en la base de datos.
 */
public class GestorCoche {

	private CocheDaoMySql cocheDao;

	/**
	 * Constructor de la clase GestorCoche. Inicializa el objeto CocheDaoMySql para
	 * interactuar con la capa de persistencia.
	 */
	public GestorCoche() {
		this.cocheDao = new CocheDaoMySql();
	}

	/**
	 * Agrega un nuevo coche a la base de datos.
	 *
	 * @param coche el coche a agregar.
	 * @return true si el coche se agrega correctamente, false si hay un error o la
	 *         marca/modelo están vacíos.
	 */
	public boolean agregarCoche(Coche coche) {
		if (coche.getMarca() == null || coche.getMarca().isEmpty()) {
			return false;
		}

		if (coche.getModelo() == null || coche.getModelo().isEmpty()) {
			return false;
		}

		return cocheDao.agregarCoche(coche);
	}

	/**
	 * Elimina un coche de la base de datos.
	 *
	 * @param id el identificador del coche a eliminar.
	 * @return true si el coche se elimina correctamente, false si hay un error.
	 */
	public boolean eliminarCoche(int id) {
		return cocheDao.eliminarCoche(id);
	}

	/**
	 * Busca un coche por su identificador en la base de datos.
	 *
	 * @param id el identificador del coche a buscar.
	 * @return el coche encontrado, null si no se encuentra.
	 */
	public Coche buscarCochePorId(int id) {
		return cocheDao.buscarCochePorId(id);
	}

	/**
	 * Modifica los datos de un coche en la base de datos.
	 *
	 * @param coche el coche con los datos actualizados.
	 * @return true si el coche se modifica correctamente, false si hay un error.
	 */
	public boolean modificarCoche(Coche coche) {
		return cocheDao.modificarCoche(coche);
	}

	/**
	 * Obtiene una lista de todos los coches almacenados en la base de datos.
	 *
	 * @return una lista de coches.
	 */
	public List<Coche> listarCoches() {
		return cocheDao.listarCoches();
	}
}