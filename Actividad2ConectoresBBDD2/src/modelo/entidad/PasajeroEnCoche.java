package modelo.entidad;

public class PasajeroEnCoche {
	
	private Coche coche;
	private Pasajero pasajero;
	public PasajeroEnCoche() {
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
		return "PasajeroEnCoche [coche=" + coche + ", pasajero=" + pasajero + "]";
	}
	
	

}
