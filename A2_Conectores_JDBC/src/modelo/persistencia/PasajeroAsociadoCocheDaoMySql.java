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
import modelo.entidad.PasajeroAsociadoCoche;
import modelo.persistencia.interfaces.PasajeroDao;
import modelo.persistencia.interfaces.PasajeroAsociadoCocheDao;

public class PasajeroAsociadoCocheDaoMySql implements PasajeroAsociadoCocheDao {
	
	private Connection con;    
    PreparedStatement ps;
    ResultSet rs;
    String query;
    int filas;
	    
	    
    public PasajeroAsociadoCocheDaoMySql() {	       
    	this.con = Conexion.abrirConexion();
    }
	   	    
	    /**
	     * Método que añade  un pasajero a un coche en la base de datos.
	     *
	     * @param idPasajero ID del pasajero a vincular al coche.
	     * @param idCoche    ID del coche al que se asociará el pasajero.
	     * @return true si la operación es exitosa, false en caso contrario.
	     */

		@Override
		public boolean addPasajeroCoche(int idPasajero, int idCoche) {
			if(con == null){
				return false;
			}		
		   
					boolean alta = true;
		            query = "INSERT INTO pasajeros_asociados_coches VALUES (?, ?)";
		            
			 try {                 
		            ps = con.prepareStatement(query);
		            ps.setInt(1, idPasajero);
		            ps.setInt(2, idCoche);
		
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
		 * Elimina la asociación de un pasajero a un coche en la base de datos.
		 *
		 * @param idPasajero ID del pasajero a desasociar del coche.
		 * @param idCoche    ID del coche del que se desasociará el pasajero.
		 * @return true si la operación es exitosa, false en caso contrario.
		 */
	
		@Override
		public boolean eliminarPasajeroCoche(int idPasajero, int idCoche) {
			if(con == null){
				return false;
			}
			boolean baja=true;
			query = "DELETE FROM pasajeros_asociados_coches WHERE id_pasajero = ? AND id_coche = ?";
			try {
				
	            ps = con.prepareStatement(query);
	            ps.setInt(1, idPasajero);
	            ps.setInt(2, idCoche);
				
				filas = ps.executeUpdate();
				if(filas == 0) {
					baja=false;;				
				}
			} catch (SQLException e) {			
				e.printStackTrace();
			}				
			return baja;
		}
			
		/**
		 * Lista de pasajeros asociados a un coche en la base de datos.
		 *
		 * @param idCoche ID del coche del que se listarán los pasajeros asociados a él.
		 * @return Lista de pasajeros asociados al coche.
		 */
		@Override
	    public List<PasajeroAsociadoCoche> listarPasajerosCoche(int idCoche) {
			if(con == null){
				return null;
			}
	
	        List<PasajeroAsociadoCoche> listarPasajerosCoche = new ArrayList<>();
	        query = "SELECT * FROM pasajeros_asociados_coches WHERE id_coche = ?";
	        try {
	            ps = con.prepareStatement(query);
	            ps.setInt(1, idCoche);
	            rs = ps.executeQuery();

	            while (rs.next()) {
	                PasajeroAsociadoCoche pasajeroCoche = new PasajeroAsociadoCoche();
	                int idPasajero = rs.getInt("id_pasajero");

	                PasajeroDao daoPasajero = new PasajeroDaoMySql();
	                Pasajero pasajero = daoPasajero.buscarPasajero(idPasajero);

	                pasajeroCoche.setCoche(new CocheDaoMySql().buscarCoche(idCoche));
	                pasajeroCoche.setPasajero(pasajero);

	                listarPasajerosCoche.add(pasajeroCoche);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return listarPasajerosCoche;
	    }
		
		 /**
	     * Método que muestra la lista de coches disponibles, es decir, 
	     * aquellos coches que no tienen pasajeros asociados.
	     *
	     * @return Lista de coches disponibles.
	     */

		@Override
		public List<Coche> cochesDisponibles() {
		    List<Coche> aux = new ArrayList<>();
		    query = "SELECT * FROM coches WHERE id NOT IN (SELECT id_coche FROM pasajeros_asociados_coches)";
		    
		    try {
		        ps = con.prepareStatement(query);
		        rs = ps.executeQuery();
		        
		        while (rs.next()) {
		            Coche coche = new Coche();
		            coche.setId(rs.getInt("id"));
		            coche.setAño(rs.getInt("año"));
		            coche.setKm(rs.getInt("km"));
		            coche.setMarca(rs.getString("marca"));
		            coche.setModelo(rs.getString("modelo"));
		            aux.add(coche);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return aux;
		}
		
		 /**
	     * Método que muestra la lista de pasajeros asociados a coches.
	     *
	     * @return Lista de objetos PasajeroAsociadoCoche que contiene la información del coche y el pasajero asociado.
	     */

		  @Override
		    public List<PasajeroAsociadoCoche> listCochesPasajeros() {
		        List<PasajeroAsociadoCoche> listaPasajeroCoche = new ArrayList<>();
		        query = "SELECT * FROM pasajeros_asociados_coches";
		        try {
		            ps = con.prepareStatement(query);
		            rs = ps.executeQuery();

		            while (rs.next()) {
		                Coche coche = new Coche();
		                Pasajero pasajero = new Pasajero();

		                int idCoche = rs.getInt("coche_id");
		                coche.setId(idCoche);
		                coche.setAño(rs.getInt("coche_año"));
		                coche.setKm(rs.getInt("coche_km"));
		                coche.setMarca(rs.getString("coche_marca"));
		                coche.setModelo(rs.getString("coche_modelo"));

		                int idPasajero = rs.getInt("pasajero_id");
		                if (idPasajero > 0) {
		                    pasajero.setId(idPasajero);
		                    pasajero.setNombre(rs.getString("pasajero_nombre"));
		                    pasajero.setEdad(rs.getInt("pasajero_edad"));
		                    pasajero.setPeso(rs.getDouble("pasajero_peso"));
		                }

		                PasajeroAsociadoCoche pasajeroAsociadoCoche = new PasajeroAsociadoCoche(coche, pasajero);
		                listaPasajeroCoche.add(pasajeroAsociadoCoche);
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }

		        return listaPasajeroCoche;
		    }
}