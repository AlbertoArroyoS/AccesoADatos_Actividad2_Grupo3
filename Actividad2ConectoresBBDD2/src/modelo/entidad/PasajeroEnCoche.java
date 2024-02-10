package modelo.entidad;
/**
 * La clase PasajeroEnCoche representa la relación entre un objeto Coche y un objeto Pasajero.
 * Esta clase permite asociar un coche con un pasajero.
 * @author Alberto Arroyo Santofimia
 * @version 2.0
 */
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
