package modelo.entidad;

public class PasajeroAsociadoCoche {
	
	//Atributos de la nueva clase
	
	private Coche coche;
	private Pasajero pasajero;
	
	
	public PasajeroAsociadoCoche(Coche coche, Pasajero pasajero) {
		super();
		this.coche = coche;
		this.pasajero = pasajero;
	}


	public PasajeroAsociadoCoche() {
		super();
	}


	public Coche getCoche() {
		return coche;
	}


	public void setCoche(Coche coche) {
		this.coche = coche;
	}


	public Pasajero getPasajero() {
		return pasajero;
	}


	public void setPasajero(Pasajero pasajero) {
		this.pasajero = pasajero;
	}


	@Override
	public String toString() {
		return "PasajeroAsociadoCoche [coche=" + coche + ", pasajero=" + pasajero + "]";
	}
	
}	
    

