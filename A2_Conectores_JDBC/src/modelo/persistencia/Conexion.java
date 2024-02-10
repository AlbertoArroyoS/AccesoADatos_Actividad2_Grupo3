package modelo.persistencia;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {
	
	/**
	 * Clase que gestiona la conexión a la base de datos utilizando un fichero config.properties.
	 * Se utiliza un bloque estático para inicializar la conexión al cargar la clase en la JVM.
	 * Definimos los atributos de la clase: uno de tipo Connection y otro de tipo Properties.
	 */
	
	private static Connection con;
    private static Properties properties;
    
    
    /**
     * Bloque estático para inicializar la conexión y cargar las propiedades desde el archivo config.properties.
     * De esta manera nos aseguramos que se ejecuta una sola vez al arrancar el programa. 
     */

    static {
        try (InputStream ficheroProp = Conexion.class.getClassLoader().getResourceAsStream("config.properties")) {
        	
            properties = new Properties();                
            properties.load(ficheroProp);           
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
        	// Obtenemos los datos de configuración para la conexión a la base de datos.
            String url = properties.getProperty("db.url");
            String usuario = properties.getProperty("db.usuario");
            String password = properties.getProperty("db.password");
            
            con = DriverManager.getConnection(url, usuario, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * Método que nos permite abrir la conexión a la base de datos.
     *
     * @return La conexión a la base de datos.
     */

    public static Connection abrirConexion() {
        return con;
    }
    
    /**
     * Método que cierra la conexión a la base de datos.
     */

    public static void cerrarConexion() {
        try {            
             con.close();            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
