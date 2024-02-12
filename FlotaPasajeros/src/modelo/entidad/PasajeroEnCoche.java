package modelo.entidad;

/**
 * Clase que representa la relación entre un coche y los pasajeros que lleva.
 * Esta clase asocia un coche con uno o más pasajeros a través de sus IDs respectivos.
 */
public class PasajeroEnCoche {
    private int idCoche;
    private int idPasajero;
    
 // Constructor
    
	public PasajeroEnCoche() {
		super();

	}
	
    // Getters y setters 
	
	public int getIdCoche() {
		return idCoche;
	}
	public void setIdCoche(int idCoche) {
		this.idCoche = idCoche;
	}
	public int getIdPasajero() {
		return idPasajero;
	}
	public void setIdPasajero(int idPasajero) {
		this.idPasajero = idPasajero;
	}

	
	// Método equals 
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PasajeroEnCoche other = (PasajeroEnCoche) obj;
		return idCoche == other.idCoche && idPasajero == other.idPasajero;
	}
	
	
	// Método toString
	
	@Override
	public String toString() {
		return "PasajeroEnCoche [idCoche=" + idCoche + ", idPasajero=" + idPasajero + "]";
	}


    
}
