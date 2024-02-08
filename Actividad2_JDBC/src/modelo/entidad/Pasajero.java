package modelo.entidad;

/**
 * La clase Pasajero representa a una entidad de pasajero que puede viajar en un
 * coche. Cada pasajero tiene un identificador único, nombre, edad y peso.
 */
public class Pasajero {

	private int id;
	private String nombre;
	private int edad;
	private double peso;

	public Pasajero() {
		// Constructor vacío
	}

	// Getters y setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return "Pasajero [id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", peso=" + peso + "]";
	}

}
