package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Coche;
import modelo.entidad.PasajeroAsociadoCoche;

public interface PasajeroAsociadoCocheDao {
	
	boolean addPasajeroCoche(int idPasajero, int idCoche);
    boolean eliminarPasajeroCoche(int idPasajero, int idCoche);
    List<PasajeroAsociadoCoche> listarPasajerosCoche(int idCoche);   
    List<Coche> cochesDisponibles();
    List<PasajeroAsociadoCoche> listCochesPasajeros();

}
