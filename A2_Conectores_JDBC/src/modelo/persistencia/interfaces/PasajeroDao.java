package modelo.persistencia.interfaces;

import java.util.List;
import modelo.entidad.Pasajero;

public interface PasajeroDao {
	
	boolean altaPasajero(Pasajero pasajero);
	boolean bajaPasajero(int id);
	Pasajero buscarPasajero(int id);	
	List<Pasajero>listarTodos();
	
	

}
