package modelo.persistencia.interfaces;

import java.util.List;
import java.util.Map;

import modelo.entidad.Coche;
import modelo.entidad.PasajeroEnCoche;
/**
 * Esta interfaz proporciona métodos para gestionar la relación entre Coches y Pasajeros en una base de datos.
 * Implementar esta interfaz permite la interacción con la capa de persistencia para la relación entre Coches y Pasajeros.
 * 
 * @author Alberto Arroyo Santofimia
 * @version 2.0
 */
public interface DaoPasajeroEnCoche {
	
	int addPasajeroCoche(int idCoche, int idPasajero);
	int eliminarPasajeroCoche(int idCoche, int idPasajero);
	List<PasajeroEnCoche> pasajerosEnCochePorId(int idCoche);
	Map<Coche, Integer> mostrarCochesConNumeroPasajeros();
	List<Coche> mostrarCochesSinPasajeros();
	List<PasajeroEnCoche> mostrarCochesConPasajeros();
	
}
