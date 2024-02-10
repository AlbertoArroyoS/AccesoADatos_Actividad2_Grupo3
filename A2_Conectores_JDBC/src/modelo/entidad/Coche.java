package modelo.entidad;

public class Coche {
	
	//Definimos los atributos de la clase
	
	private int id, año, km;
	private String marca, modelo;
	
	
	//Constructor
	
	public Coche() {
		super();
	}


	public Coche(int id, int año, int km, String marca, String modelo) {
		super();
		this.id = id;
		this.año = año;
		this.km = km;
		this.marca = marca;
		this.modelo = modelo;
	}

	//Getter and setter
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getAño() {
		return año;
	}


	public void setAño(int año) {
		this.año = año;
	}


	public int getKm() {
		return km;
	}


	public void setKm(int km) {
		this.km = km;
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

	//toString
	
	@Override
	public String toString() {
		return "Coche [id=" + id + ", año=" + año + ", km=" + km + ", marca=" + marca + ", modelo=" + modelo + "]";
	}
	
	

}
