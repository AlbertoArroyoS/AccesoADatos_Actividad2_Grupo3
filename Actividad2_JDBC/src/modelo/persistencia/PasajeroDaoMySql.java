package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Pasajero;
import modelo.persistencia.interfaces.PasajeroDao;

/**
 * La clase PasajeroDaoMySql implementa la interfaz PasajeroDao y proporciona
 * métodos para acceder y manipular datos de pasajeros en una base de datos
 * MySQL.
 */

public class PasajeroDaoMySql implements PasajeroDao {

	private Connection conexion;

	/**
	 * Abre una conexión a la base de datos utilizando las credenciales
	 * especificadas en el archivo de propiedades.
	 *
	 * @return true si la conexión se abre con éxito, false en caso contrario.
	 */
	public boolean abrirConexion() {
		try {
			ConexionBD conexionBD = new ConexionBD();
			conexion = DriverManager.getConnection(conexionBD.getUrl(), conexionBD.getUsuario(),
					conexionBD.getPassword());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Cierra la conexión a la base de datos.
	 *
	 * @return true si la conexión se cierra con éxito, false en caso contrario.
	 */
	public boolean cerrarConexion() {
		try {
			if (conexion != null) {
				conexion.close();
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Elimina un pasajero de la base de datos utilizando su ID.
	 *
	 * @param id el ID del pasajero a eliminar.
	 * @return true si el pasajero se elimina correctamente, false en caso
	 *         contrario.
	 */
	@Override
	public boolean eliminarPasajero(int id) {
		if (!abrirConexion()) {
			return false;
		}
		String query = "DELETE FROM Pasajero WHERE id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			int filasAfectadas = ps.executeUpdate();
			return filasAfectadas > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			cerrarConexion();
		}
	}

	/**
	 * Busca un pasajero en la base de datos utilizando su ID.
	 *
	 * @param id el ID del pasajero a buscar.
	 * @return el pasajero encontrado o null si no se encuentra.
	 */
	@Override
	public Pasajero buscarPasajeroPorId(int id) {
		if (!abrirConexion()) {
			return null;
		}
		String query = "SELECT * FROM Pasajero WHERE id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Pasajero pasajero = new Pasajero();
				pasajero.setId(rs.getInt("id"));
				pasajero.setNombre(rs.getString("nombre"));
				pasajero.setEdad(rs.getInt("edad"));
				pasajero.setPeso(rs.getDouble("peso"));
				return pasajero;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return null;
	}

	/**
	 * Obtiene una lista de todos los pasajeros almacenados en la base de datos.
	 *
	 * @return una lista de pasajeros.
	 */
	@Override
	public List<Pasajero> listarPasajeros() {
		if (!abrirConexion()) {
			return null;
		}
		List<Pasajero> pasajeros = new ArrayList<>();
		String query = "SELECT * FROM Pasajero";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Pasajero pasajero = new Pasajero();
				pasajero.setId(rs.getInt("id"));
				pasajero.setNombre(rs.getString("nombre"));
				pasajero.setEdad(rs.getInt("edad"));
				pasajero.setPeso(rs.getDouble("peso"));
				pasajeros.add(pasajero);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return pasajeros;
	}

	/**
	 * Crea un nuevo pasajero en la base de datos.
	 *
	 * @param pasajero el objeto Pasajero a crear.
	 * @return true si el pasajero se crea correctamente, false en caso contrario.
	 */
	@Override
	public boolean crearPasajero(Pasajero pasajero) {
		if (!abrirConexion()) {
			return false;
		}
		String query = "INSERT INTO Pasajero (nombre, edad, peso) VALUES (?, ?, ?)";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, pasajero.getNombre());
			ps.setInt(2, pasajero.getEdad());
			ps.setDouble(3, pasajero.getPeso());
			int filasAfectadas = ps.executeUpdate();
			return filasAfectadas > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			cerrarConexion();
		}
	}

	/**
	 * Agrega un pasajero a un coche en la base de datos.
	 *
	 * @param idPasajero el ID del pasajero a agregar.
	 * @param idCoche    el ID del coche al que se agregará el pasajero.
	 * @return true si el pasajero se agrega al coche correctamente, false si ocurre
	 *         algún error.
	 */
	@Override
	public boolean agregarPasajeroACoche(int idPasajero, int idCoche) {
		if (!abrirConexion()) {
			return false;
		}

		String query = "INSERT INTO Pasajero_Coche (id_pasajero, id_coche) VALUES (?, ?)";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, idPasajero);
			ps.setInt(2, idCoche);
			int filasAfectadas = ps.executeUpdate();
			return filasAfectadas > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			cerrarConexion();
		}
	}

	/**
	 * Elimina un pasajero de un coche en la base de datos.
	 *
	 * @param idPasajero el ID del pasajero a eliminar del coche.
	 * @param idCoche    el ID del coche del que se eliminará el pasajero.
	 * @return true si el pasajero se elimina del coche correctamente, false si
	 *         ocurre algún error.
	 */
	@Override
	public boolean eliminarPasajeroDeCoche(int idPasajero, int idCoche) {
		if (!abrirConexion()) {
			return false;
		}

		String query = "DELETE FROM Pasajero_Coche WHERE id_pasajero = ? AND id_coche = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, idPasajero);
			ps.setInt(2, idCoche);
			int filasAfectadas = ps.executeUpdate();
			return filasAfectadas > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			cerrarConexion();
		}
	}

	/**
	 * Obtiene una lista de pasajeros asociados a un coche en la base de datos.
	 *
	 * @param idCoche el ID del coche del que se obtendrán los pasajeros asociados.
	 * @return una lista de pasajeros asociados al coche especificado, null si
	 *         ocurre algún error.
	 */
	@Override
	public List<Pasajero> listarPasajerosDeCoche(int idCoche) {
		if (!abrirConexion()) {
			return null;
		}

		List<Pasajero> pasajeros = new ArrayList<>();
		String query = "SELECT p.id, p.nombre, p.edad, p.peso " + "FROM Pasajero p "
				+ "JOIN Pasajero_Coche pc ON p.id = pc.id_pasajero " + "WHERE pc.id_coche = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, idCoche);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Pasajero pasajero = new Pasajero();
				pasajero.setId(rs.getInt("id"));
				pasajero.setNombre(rs.getString("nombre"));
				pasajero.setEdad(rs.getInt("edad"));
				pasajero.setPeso(rs.getDouble("peso"));
				pasajeros.add(pasajero);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return pasajeros;
	}

}
