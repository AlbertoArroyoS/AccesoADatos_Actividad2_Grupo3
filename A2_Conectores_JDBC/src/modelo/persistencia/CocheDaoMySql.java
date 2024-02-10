package modelo.persistencia;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import modelo.entidad.Coche;
import modelo.persistencia.interfaces.CocheDao;

public class CocheDaoMySql implements CocheDao{
	
	//Declaramos las variables que vamos a necesitar para implementar la interfaz CocheDao y el acceso a datos.

	private Connection con;   
    PreparedStatement ps;
    ResultSet rs;
    int filas;
    
    /**
     * Constructor de la clase, inicializamos desde aquí la conexión a la base de datos.
     */

    public CocheDaoMySql() {       
    	this.con = Conexion.abrirConexion();
    }
       

	/**
	 * Método para dar de alta un  nuevo coche en la base de datos.
	 * 
	 * @param coche objeto de tipo Coche a dar de alta en la base de datos.
	 * @return true, si se ha dado de alta correctamente, false en caso contrario.
	 */
	
	@Override
	public boolean altaCoche(Coche coche) {
		
		if(con == null){
			return false;
		}
		
		boolean altaCoche = true;		
		String query = "INSERT INTO coches (año, km, marca, modelo) VALUES (?,?,?,?)";
		
		try {
			ps = con.prepareStatement(query);			
			ps.setInt(1,  coche.getAño());
			ps.setInt(2,  coche.getKm());			
			ps.setString(3, coche.getMarca());
			ps.setString(4, coche.getModelo());			
			
			filas = ps.executeUpdate();
			if (filas == 0) {
				altaCoche = false;
			}
			
		} catch (SQLException e) {
			System.out.println(" Error al dar de alta el coche : " + coche);
			altaCoche = false;
			e.printStackTrace();
		}
		return altaCoche;	
		
	}
	
	/**
	 * Método para dar de baja un objeto coche de la base de datos.
	 * 
	 * @param id ID del coche que deseamos dar de baja.
	 * @return true, si la baja en la base de datos se ha realizado correctamente, false en caso contrario.
	 * 
	 */
	
	@Override
	public boolean bajaCoche(int id) {
		if(con == null){
			return false;
		}
		
		boolean bajaCoche = true;
		
		String query = "Delete from coches where id = ?";
		
		try {
		    ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			filas= ps.executeUpdate();
			
			if(filas==0) {
				bajaCoche=false;
			}
			
		} catch (SQLException e) {
			System.out.println(" No se ha podido dar de baja el coche con id : " + id);
			bajaCoche=false;
			e.printStackTrace();
		}
		return bajaCoche;
	}
	
	/**
	 * Método para localizar un objeto coche en la base de datos a partir de su identificador.
	 * 
	 * @param id ID identificador único del coche a buscar.
	 * @return Coche encontrado, null en caso de que no se localice.
	 * 
	 */
	
	@Override
	public Coche buscarCoche(int id) {
		if(con == null){
			return null;
		}
		Coche coche=null;		
		String query = "SELECT * FROM coches WHERE id = ?";
		
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, id);			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				coche = new Coche();							
				coche.setId(rs.getInt("id"));
				coche.setAño(rs.getInt("año"));
				coche.setKm(rs.getInt("km"));		
				coche.setMarca(rs.getString("marca"));
				coche.setModelo(rs.getString("modelo"));
				break;		
			}			
			
		} catch (SQLException e) {
			System.out.println("error al obtener el coche con id : " + id);
			e.printStackTrace();
		}		
		return coche;
	}
	
	/**
     * Modifica todos los datos de un coche en la base de datos por su ID.
     *
     * @param coche Coche con los nuevos datos.
     * @return true si la operación es exitosa, false en caso contrario.
     */
	@Override
	public boolean modificarCoche(Coche coche) {
		
		if(con == null){
			return false;
		}
		boolean modificado=true;
		String query = "Update coches set AÑO=?, KM=?,MARCA=?, MODELO=?  WHERE ID=?";
		
		try {
			ps= con.prepareStatement(query);
			ps.setInt(1, coche.getAño());
			ps.setInt(2, coche.getKm());
			ps.setString(3, coche.getMarca());
			ps.setString(4, coche.getModelo());
			ps.setInt(5, coche.getId());			
			
			filas=ps.executeUpdate();		

			if(filas==0) 
				modificado=false;			
			
			
		} catch (SQLException e) {
			System.out.println(" error al modificar el coche : " + coche );
			modificado = false;
			e.printStackTrace();
		}
		return modificado;
		
		
	}

	 /**
     * Listar todos los coches de la base de datos.
     *
     * @return Lista de coches.
     */
	
	@Override
	public List<Coche> listarTodos() {
		if(con == null){
			return null;
		}				
		List<Coche>lista = new ArrayList<>();		 
		String query = "Select * from coches";		 
		
		try {
			ps= con.prepareStatement(query);
			rs=ps.executeQuery();
			
			while(rs.next()) {				
				Coche coche = new Coche();								
				coche.setId(rs.getInt(1));
				coche.setAño(rs.getInt(2));
				coche.setKm(rs.getInt(3));
				coche.setMarca(rs.getString(4));
				coche.setModelo(rs.getString(5));				
				
				lista.add(coche);
			}
		} catch (SQLException e) {
			System.out.println("error al obtener el listado de coches ");					
			e.printStackTrace();
		}
		return lista;
	}	

}
