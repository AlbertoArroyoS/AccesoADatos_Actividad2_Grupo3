package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Coche;

public  interface CocheDao {
	
	boolean altaCoche(Coche coche);
	boolean bajaCoche(int id);
	Coche buscarCoche(int id);
	boolean modificarCoche(Coche coche);
	List<Coche>listarTodos();
	
	}
		
	

