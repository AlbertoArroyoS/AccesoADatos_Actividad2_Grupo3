package modelo.persistencia.dao;

import java.util.List;

import modelo.entidad.Coche;

/**
 * Interfaz para el acceso a datos de la entidad Coche. Define las operaciones CRUD estándar.
 */
public interface CocheDao {
	 boolean add(Coche coche);
	 boolean update(Coche coche);
	 boolean delete(int id);
	 Coche searchById(int id);
	 List<Coche> list();

}
