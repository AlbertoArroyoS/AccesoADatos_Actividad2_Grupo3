package modelo.entidad;

import java.util.Objects;

/**
 * Clase que representa un coche dentro de la aplicación.
 * Contiene información básica de un vehículo como su ID, marca, modelo,
 * año de fabricación y kilometraje.
 */
public class Coche {

    private int id;
    private String marca;
    private String modelo;
    private int año;
    private String km;

 
    // Constructor 
	public Coche() {
		super();
		
	}
	
	
	// Getters y Setters
    
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public int getAño() {
		return año;
	}
	public void setAño(int año) {
		this.año = año;
	}
	public String getKm() {
		return km;
	}
	public void setKm(String km) {
		this.km = km;
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
		Coche other = (Coche) obj;
		return año == other.año && id == other.id && Objects.equals(km, other.km) && Objects.equals(marca, other.marca)
				&& Objects.equals(modelo, other.modelo);
	}	

	
			
	// Método toString
	
	@Override
	public String toString() {
		return "Coche [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", año=" + año + ", km=" + km + "]";
		}


	@Override
	public int hashCode() {
		return Objects.hash(año, id, km, marca, modelo);
	}
		

}
