package modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Pasajero;
import modelo.persistencia.PasajeroDaoMySql;
import modelo.persistencia.interfaces.PasajeroDao;

		/**
		 * Clase que gestiona la lógica de negocio relacionada con los pasajeros.
		 */
		public class GestorPasajero {
	
		    // Instanciamos un objeto de la clase PasajeroDao para comunicar la lógica de negocio con la capa
		    // de acceso a datos
		    private final PasajeroDao pdao = new PasajeroDaoMySql();
	
	    /**
	     * Da de alta un nuevo pasajero en la base de datos.
	     *
	     * @param pasajero Pasajero a dar de alta.
	     * @return true si se ha dado de alta correctamente, false en caso contrario.
	     */
	    public boolean alta(Pasajero pasajero) {	        
	        return pdao.altaPasajero(pasajero);
	    }
	
	    /**
	     * Da de baja un pasajero en la base de datos.
	     *
	     * @param id Identificador único del pasajero a dar de baja.
	     * @return true si se ha dado de baja correctamente, false en caso contrario.
	     */
	    public boolean baja(int id) {
	        return pdao.bajaPasajero(id);
	    }
	
	    /**
	     * Busca un pasajero por su identificador único en la base de datos.
	     *
	     * @param id Identificador único del pasajero a buscar.
	     * @return Objeto Pasajero encontrado, null si no se encontró ningún pasajero con el ID proporcionado.
	     */
	    public Pasajero buscarPasajero(int id) {
	        return pdao.buscarPasajero(id);
	    }
	
	   
	    /**
	     * Lista todos los pasajeros registrados en la base de datos.
	     *
	     * @return Lista de objetos Pasajero, o lista vacía si no hay pasajeros registrados.
	     */
	    public List<Pasajero> listarTodos() {
	        return pdao.listarTodos();
	    }
	
		}