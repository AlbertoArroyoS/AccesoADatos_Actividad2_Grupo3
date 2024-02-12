package config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Clase de configuración de la base de datos. Proporciona métodos para cargar
 * las propiedades de conexión a la base de datos y para crear conexiones con la base de datos.
 */
public class ConfigBBDD {

    private static Properties properties; 

    static {
        cargarPropiedades();
    }

    /**
     * Carga las propiedades de conexión desde el archivo de propiedades.
     */
    private static void cargarPropiedades() {
        try (InputStream fichero = ConfigBBDD.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (fichero == null) {
                System.out.println("No se pudo cargar el archivo de configuración 'config.properties'");
                return;
            }
            // Carga las propiedades del archivo
            properties = new Properties();
            properties.load(fichero);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
	 * Método que devuelve un valor de una propiedad a partir de la clave
	 * @param key la clave de la propiedad
	 * @return valor de la propiedad. Null en caso de que no exista.
	 */
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
	
	

    /**
     * Crea una conexión a la base de datos basada en las propiedades cargadas.
     *
     * @return una nueva conexión a la base de datos
     */
    public static Connection crearConexion() {
        try {            
            // Crear y retornar la conexión
            return DriverManager.getConnection(
                    properties.getProperty("url").toString(),
                    properties.getProperty("user").toString(),
                    properties.getProperty("password").toString());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No se ha podido conectar a la base de datos");
            return null;
        }
    }
}

