package modelo.persistencia;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * La clase ConexionBD proporciona métodos para establecer la conexión a la base
 * de datos utilizando las credenciales especificadas en el archivo de
 * propiedades.
 */
public class ConexionBD {

	private String url;
	private String usuario;
	private String password;

	/**
	 * Constructor de la clase ConexionBD. Lee las propiedades del archivo de
	 * configuración y asigna los valores correspondientes.
	 */
	public ConexionBD() {
		Properties propiedades = new Properties();
		FileInputStream entrada = null;

		try {
			entrada = new FileInputStream("src/database.properties");
			propiedades.load(entrada);

			// Lee las propiedades del archivo
			url = propiedades.getProperty("db.url");
			usuario = propiedades.getProperty("db.usuario");
			password = propiedades.getProperty("db.password");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (entrada != null) {
				try {
					entrada.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Obtiene la URL de la base de datos.
	 *
	 * @return La URL de la base de datos.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Obtiene el nombre de usuario de la base de datos.
	 *
	 * @return El nombre de usuario de la base de datos.
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Obtiene la contraseña de la base de datos.
	 *
	 * @return La contraseña de la base de datos.
	 */
	public String getPassword() {
		return password;
	}
}

