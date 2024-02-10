package modelo.negocio;

import java.util.List;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.entidad.PasajeroAsociadoCoche;
import modelo.persistencia.PasajeroAsociadoCocheDaoMySql;
import modelo.persistencia.interfaces.PasajeroAsociadoCocheDao;

public class GestorPasajeroAsociadoCoche {
	
	 private PasajeroAsociadoCocheDao pacDao;

	  public GestorPasajeroAsociadoCoche() {
	      this.pacDao = new PasajeroAsociadoCocheDaoMySql();
	 }
		 /**
	     * Método que añade un pasajero a un coche en la base de datos.
	     *
	     * @param idPasajero ID del pasajero a añadir al coche.
	     * @param idCoche ID del coche al que se añadirá el pasajero.
	     * @return true si la operación es exitosa, false en caso contrario.
	     */
	  
	    public boolean añadirPasajeroACoche(int idPasajero, int idCoche) {
	        return pacDao.addPasajeroCoche(idPasajero, idCoche);
	    }
	
	    /**
	     * Método que elimina un pasajero de un coche en la base de datos.
	     *
	     * @param idPasajero ID del pasajero a eliminar del coche.
	     * @return true si la operación es exitosa, false en caso contrario.
	     */
	    
	    public boolean eliminarPasajeroDeCoche(int idPasajero, int idCoche) {
	        return pacDao.eliminarPasajeroCoche(idPasajero, idCoche);
	    }
	
	    /**
	     * Método que lista todos los pasajeros asociados a un coche en la base de datos.
	     *
	     * @param idCoche ID del coche del que se listarán los pasajeros.
	     * @return Lista de pasajeros asociados al coche.
	     */
	    
	    public List<PasajeroAsociadoCoche> listarPasajerosDeCoche(int idCoche) {
	        return pacDao.listarPasajerosCoche(idCoche);
	    }
	    
	    /**
	     * Método que obtiene la lista de coches disponibles (no asociados a ningún pasajero) en la base de datos.
	     *
	     * @return Lista de coches disponibles.
	     */
	    public List<Coche> cochesDisponibles() {
	        return pacDao.cochesDisponibles();
	    }

	    /**
	     * Método que lista todos los coches y sus pasajeros asociados en la base de datos.
	     *
	     * @return Lista de objetos PasajeroAsociadoCoche con información sobre los coches y sus pasajeros asociados.
	     *         Si no se puede abrir la conexión, devuelve null.
	     */
	    public List<PasajeroAsociadoCoche> mostrarCochesConPasajeros() {
	        return pacDao.listCochesPasajeros();
	    }
}



