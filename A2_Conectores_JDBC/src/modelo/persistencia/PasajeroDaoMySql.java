package modelo.persistencia;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.persistencia.interfaces.PasajeroDao;

public class PasajeroDaoMySql implements PasajeroDao{
	
	//Declaramos las variables que vamos a necesitar para implementar la interfaz CocheDao y el acceso a datos.

		private Connection con;	   
	    PreparedStatement ps;
	    ResultSet rs;
	    String query;
	    int filas;
	    
	    /**
	     * Constructor de la clase, inicializamos desde aquí la conexión a la base de datos.
	     */
	    public PasajeroDaoMySql() {	       
	    	this.con = Conexion.abrirConexion();
	    }
	   
	    

	    /**
		 * Método para dar de alta un  nuevo pasajero en la base de datos.
		 * 
		 * @param pasajero objeto de tipo Pasajero a dar de alta en la base de datos.
		 * @return true, si se ha dado de alta correctamente, false en caso contrario.
		 */
			@Override
			public boolean altaPasajero(Pasajero pasajero) {
				if(con == null){
					return false;
				}
				boolean alta = true;
				query= "insert into pasajeros (NOMBRE, EDAD,PESO) values (?,?,?)";
				
				try {
					ps=con.prepareStatement(query);
					ps.setString(1, pasajero.getNombre());
					ps.setInt(2, pasajero.getEdad());
					ps.setDouble(3, pasajero.getPeso());
					
					filas=ps.executeUpdate();
					
					if(filas== 0) {
						alta=false;
					}
				} catch (SQLException e) {
					
					alta=false;
					e.printStackTrace();
				}				
				return alta;
			}
	
		/**
		 * Método para dar de baja un objeto pasajero de la base de datos.
		 * 
		 * @param id ID del pasajero que deseamos dar de baja.
		 * @return true, si la baja en la base de datos se ha realizado correctamente, false en caso contrario.
		 * 
		 */
			@Override
			public boolean bajaPasajero(int id) {
				if(con == null){
					return false;
				}
				boolean baja=true;
				query= "delete from pasajeros where id = ?";
				try {
					ps = con.prepareStatement(query);
					ps.setInt(1, id);
					
					filas = ps.executeUpdate();
					if(filas == 0) {
						baja=false;;				
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
				return baja;
			}
		/**
		 * Método para localizar un objeto pasajero en la base de datos a partir de su identificador.
		 * 
		 * @param id ID identificador único del pasajero a buscar.
		 * @return pasajero encontrado, null en caso de que no se localice.
		 * 
		 */

			@Override
			public Pasajero buscarPasajero(int id) {		
				if(con == null){
					return null;
				}
					Pasajero pasajero= null;	
					query = "SELECT * FROM pasajeros WHERE id = ?";
					
					try {
						ps = con.prepareStatement(query);
						ps.setInt(1, id);			
						rs = ps.executeQuery();
						
						while(rs.next()) {
							pasajero = new Pasajero();							
							pasajero.setId(rs.getInt("id"));
							pasajero.setNombre(rs.getString("nombre"));
							pasajero.setEdad(rs.getInt("edad"));		
							pasajero.setPeso(rs.getDouble("peso"));					
							break;		
						}			
						
					} catch (SQLException e) {
						
						e.printStackTrace();
					}		
					return pasajero;
			}
	 /**
     * Listar todos los pasajeros de la base de datos.
     *
     * @return Lista de pasajeros.
     */
	
			@Override
			public List<Pasajero> listarTodos() {
				if(con == null){
					return null;
				}			
				List<Pasajero> lista = new ArrayList<>();		 
				query = "Select * from pasajeros";		 
				
				try {
					ps= con.prepareStatement(query);
					rs=ps.executeQuery();
					
					while(rs.next()) {				
						Pasajero pasajero = new Pasajero();								
						pasajero.setId(rs.getInt(1));
						pasajero.setNombre(rs.getString(2));
						pasajero.setEdad(rs.getInt(3));
						pasajero.setPeso(rs.getDouble(4));
					
						lista.add(pasajero);
					}
				} catch (SQLException e) {
									
					e.printStackTrace();
				}			
				return lista;
			}	
	
		
		}


	
