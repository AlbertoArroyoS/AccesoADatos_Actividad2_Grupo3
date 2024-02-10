package modelo.negocio;

import java.util.List;

import modelo.entidad.Coche;
import modelo.persistencia.CocheDaoMySql;
import modelo.persistencia.interfaces.CocheDao;
public class GestorCoche {
	
	//Instanciamos un objeto de la clase CocheDao para comunicar la lógica de negocio con la capa
	//de acceso a datos
	
	private CocheDao cdao = new CocheDaoMySql();
	
	/**
	 * Método que da de alta un coche en la base de datos 'gestion_coches'. Los atributos modelo y 
	 * marca no pueden ser campos vacíos.
	 *  
	 * @param coche coche a dar de alta.
	 * @return true en caso de que hayamos dado de alta el coche correctamente, false en caso 
	 * de algun error en el alta.
	 */

	public boolean alta(Coche coche){
		if (coche.getMarca().isEmpty() || coche.getModelo().isEmpty()) {
			return false;
		}else {
			return cdao.altaCoche(coche);
		}
	}
	
	
	/**
	 * Método que da de baja un coche en la base de datos 'gestion_coches'. 
	 *  
	 * @param id identificador único del coche a dar de baja.
	 * @return true en caso de que hayamos dado de baja el coche correctamente, false en caso 
	 * de algún error en la baja.
	 */
	
	public boolean baja(int id){
		boolean baja = cdao.bajaCoche(id);
		return baja;
		
	}
	
	/**
	 * Método que busca un coche por su identificador único en la base de datos 'gestion_coches'.
	 *  
	 * @param id Identificador único del coche a buscar.
	 * @return Objeto Coche encontrado, null si no se encontró ningún coche con el ID proporcionado.
	 */
	
	public Coche buscarCoche(int id){
		Coche coche = cdao.buscarCoche(id);
		if (coche == null) {
			System.out.println("No se encontró el coche con id: " + id);
	    } 
		return coche;
	}		
	
	
	/**
	 * Método que da modifica un coche en base de datos. Los  campos modelo y marca 
	 * no pueden estar vacíos.
	 * 
	 * @param coche coche a modificar. 
	 * @return 1 si la modificación se realiza con éxito, 0 si no se puede modificar.
     *         Se asigna automáticamente el ID actualizado al objeto coche en caso de éxito.
	 */
	
	public int modificar(Coche coche){
		
		if(!coche.getMarca().isEmpty()&& !coche.getModelo().isEmpty()){
			
			boolean modificado = cdao.modificarCoche(coche);				

	        // Verificamos si la modificación fue exitosa
	        if (modificado) {
	            return 0; // Modificación exitosa
	        } else {
	            return 1; // No se pudo modificar
	        }
	    } else {
	        return 0; // Los campos modelo o marca están vacíos
	    }
	}
	
	/**
	 * Método que lista todos los coches registrados en la base de datos 'gestion_coches'.
	 * 
	 * @return Lista de objetos Coche, o lista vacía si no hay coches registrados.
	 */
	
	public List<Coche> listar(){
		List<Coche> listarCoches = cdao.listarTodos();
		return listarCoches;
	}
	
	
	
}
