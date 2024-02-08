package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Coche;
import modelo.persistencia.interfaces.CocheDao;

/**
 * Implementación de la interfaz CocheDao para la persistencia de coches en una
 * base de datos MySQL.
 */

public class CocheDaoMySql implements CocheDao {

	private Connection conexion;

	/**
	 * Abre una conexión a la base de datos.
	 *
	 * @return true si la conexión se abre correctamente, false si ocurre algún
	 *         error.
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
	 * @return true si la conexión se cierra correctamente, false si ocurre algún
	 *         error.
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
	 * Agrega un nuevo coche a la base de datos.
	 *
	 * @param coche el coche a agregar.
	 * @return true si el coche se agrega correctamente, false si ocurre algún
	 *         error.
	 */

	@Override
	public boolean agregarCoche(Coche coche) {
		if (!abrirConexion()) {
			return false;
		}
		boolean alta = true;

		String query = "INSERT INTO Coche (marca, modelo, anoFabricacion, km) " + " VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, coche.getMarca());
			ps.setString(2, coche.getModelo());
			ps.setInt(3, coche.getAnoFabricacion());
			ps.setInt(4, coche.getKm());

			int numeroFilasAfectadas = ps.executeUpdate();
			if (numeroFilasAfectadas == 0)
				alta = false;
		} catch (SQLException e) {
			alta = false;
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}

		return alta;

	}

	/**
	 * Busca un coche por su identificador en la base de datos.
	 *
	 * @param id el identificador del coche a buscar.
	 * @return el coche encontrado, null si no se encuentra.
	 */

	@Override
	public Coche buscarCochePorId(int id) {
		if (!abrirConexion()) {
			return null;
		}

		Coche coche = null;
		String query = "SELECT * FROM Coche WHERE id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				coche = new Coche();
				coche.setId(rs.getInt("id"));
				coche.setMarca(rs.getString("marca"));
				coche.setModelo(rs.getString("modelo"));
				coche.setAnoFabricacion(rs.getInt("anoFabricacion"));
				coche.setKm(rs.getInt("km"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}

		return coche;
	}

	/**
	 * Obtiene una lista de todos los coches almacenados en la base de datos.
	 *
	 * @return una lista de coches.
	 */

	@Override
	public List<Coche> listarCoches() {
		if (!abrirConexion()) {
			return null;
		}

		List<Coche> listaCoches = new ArrayList<>();
		String query = "SELECT * FROM Coche";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Coche coche = new Coche();
				coche.setId(rs.getInt("id"));
				coche.setMarca(rs.getString("marca"));
				coche.setModelo(rs.getString("modelo"));
				coche.setAnoFabricacion(rs.getInt("anoFabricacion"));
				coche.setKm(rs.getInt("km"));
				listaCoches.add(coche);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}

		return listaCoches;
	}

	/**
	 * Elimina un coche de la base de datos.
	 *
	 * @param id el identificador del coche a eliminar.
	 * @return true si el coche se elimina correctamente, false si ocurre algún
	 *         error.
	 */

	@Override
	public boolean eliminarCoche(int id) {
		if (!abrirConexion()) {
			return false;
		}

		boolean eliminado = true;
		String query = "DELETE FROM Coche WHERE id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			int numeroFilasAfectadas = ps.executeUpdate();
			if (numeroFilasAfectadas == 0) {
				eliminado = false;
			}
		} catch (SQLException e) {
			eliminado = false;
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}

		return eliminado;
	}

	/**
	 * Modifica los datos de un coche en la base de datos.
	 *
	 * @param coche el coche con los datos actualizados.
	 * @return true si el coche se modifica correctamente, false si ocurre algún
	 *         error.
	 */

	@Override
	public boolean modificarCoche(Coche coche) {
		if (!abrirConexion()) {
			return false;
		}

		boolean modificado = true;
		String query = "UPDATE Coche SET marca = ?, modelo = ?, anoFabricacion = ?, km = ? WHERE id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, coche.getMarca());
			ps.setString(2, coche.getModelo());
			ps.setInt(3, coche.getAnoFabricacion());
			ps.setInt(4, coche.getKm());
			ps.setInt(5, coche.getId());

			int numeroFilasAfectadas = ps.executeUpdate();
			if (numeroFilasAfectadas == 0) {
				modificado = false;
			}
		} catch (SQLException e) {
			modificado = false;
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}

		return modificado;
	}

}