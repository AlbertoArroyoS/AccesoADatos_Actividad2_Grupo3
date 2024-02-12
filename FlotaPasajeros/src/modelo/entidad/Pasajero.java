package modelo.entidad;

import java.util.Objects;

/**
 * Clase que representa un pasajero dentro de la aplicación.
 * Contiene detalles del pasajero como ID, nombre, edad y peso.
 */
public class Pasajero {
    private int id;
    private String nombre;
    private int edad;
    private double peso;
	
    // Constructor 
    public Pasajero() {
		super();
		
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
	public int hashCode() {
		return Objects.hash(edad, id, nombre, peso);
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
		Pasajero other = (Pasajero) obj;
		return edad == other.edad && id == other.id && Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(peso) == Double.doubleToLongBits(other.peso);
	}

    
    // Método toString
	
	@Override
	public String toString() {
		return "Pasajero [id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", peso=" + peso + "]";
	}


}
