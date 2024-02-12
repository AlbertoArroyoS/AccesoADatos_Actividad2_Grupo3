package modelo.negocio;

import java.util.List;

import modelo.entidad.Coche;
import modelo.persistencia.dao.CocheDao;
import modelo.persistencia.dao.impl.CocheDaoImplMySql;

/**
 * Clase que gestiona la lógica de negocio para la entidad Coche.
 */
public class GestorCoche {

	private CocheDao cocheDao = new CocheDaoImplMySql();
	
	/**
     * Método para agregar un nuevo coche, con validaciones previas.
     * 
     * @param coche El coche a agregar.
     * @return true si el coche se agregó con éxito, false en caso contrario.
     */
    public boolean agregarCoche(Coche coche) {
        if (validarCoche(coche)) {
            return cocheDao.add(coche);
        }
        return false;
    }

    /**
     * Método para actualizar un coche existente, con validaciones previas.
     * 
     * @param coche El coche a actualizar.
     * @return true si el coche se actualizó con éxito, false en caso contrario.
     */
    public boolean actualizarCoche(Coche coche) {
        if (validarCoche(coche) && coche.getId() > 0) {
            return cocheDao.update(coche);
        }
        return false;
    }

    /**
     * Método para eliminar un coche por su ID.
     * 
     * @param id El ID del coche a eliminar.
     * @return true si el coche se eliminó con éxito, false en caso contrario.
     */
    public boolean eliminarCoche(int id) {
        return cocheDao.delete(id);
    }

    /**
     * Método para buscar un coche por su ID.
     * 
     * @param id El ID del coche a buscar.
     * @return El coche si se encontró, o null en caso contrario.
     */
    public Coche buscarCoche(int id) {
        return cocheDao.searchById(id);
    }

    /**
     * Método para listar todos los coches.
     * 
     * @return Una lista de todos los coches.
     */
    public List<Coche> listarCoches() {
        return cocheDao.list();
    }

    /**
     * Valida los datos de marca y modelo del coche antes de realizar operaciones de persistencia.
     * 
     * @param coche El coche a validar.
     * @return true si el coche es válido, false en caso contrario.
     */
    private boolean validarCoche(Coche coche) {
        if (coche.getMarca().isEmpty() || coche.getMarca() == null ) {
        	System.out.println("Indica una marca");
            return false;
        }
        if (coche.getModelo().isEmpty()|| coche.getModelo() == null) {
        	System.out.println("Indica un modelo");
            return false;
        }
        return true;
    }
}
