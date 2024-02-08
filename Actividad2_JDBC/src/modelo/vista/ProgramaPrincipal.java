package modelo.vista;

import java.util.List;
import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.negocio.GestorCoche;
import modelo.negocio.GestorPasajero;

/**
 * La clase ProgramaPrincipal es la clase principal del sistema de gestión de
 * coches y pasajeros. Permite al usuario interactuar con las funcionalidades
 * del sistema a través de un menú de opciones.
 */
public class ProgramaPrincipal {

	private static GestorCoche gestorCoche = new GestorCoche();
	private static GestorPasajero gestorPasajero = new GestorPasajero();

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("\nMENU PRINCIPAL:");
			System.out.println("1. Añadir nuevo coche");
			System.out.println("2. Borrar coche por ID");
			System.out.println("3. Consultar coche por ID");
			System.out.println("4. Modificar coche por ID");
			System.out.println("5. Listado de coches");
			System.out.println("6. Gestionar pasajeros");
			System.out.println("7. Terminar el programa");
			System.out.print("Seleccione una opción: ");

			int opcion = scanner.nextInt();
			scanner.nextLine();

			switch (opcion) {
			case 1:
				agregarCoche(gestorCoche, scanner);
				break;
			case 2:
				eliminarCoche(gestorCoche, scanner);
				break;
			case 3:
				buscarCochePorId(gestorCoche, scanner);
				break;
			case 4:
				modificarCoche(gestorCoche, scanner);
				break;
			case 5:
				listarCoches(gestorCoche);
				break;
			case 6:
				gestionarPasajeros(scanner);
				break;
			case 7:
				System.out.println("Programa terminado.");
				scanner.close();
				return;
			default:
				System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
			}
		}
	}

	/**
	 * Permite al usuario ingresar los datos de un nuevo coche y lo agrega al
	 * sistema.
	 * 
	 * @param gestorCoche El gestor de coches que realizará la operación.
	 * @param scanner     El scanner para leer la entrada del usuario.
	 */

	public static void agregarCoche(GestorCoche gestorCoche, Scanner scanner) {
		System.out.println("Ingrese la marca del coche:");
		String marca = scanner.nextLine();
		System.out.println("Ingrese el modelo del coche:");
		String modelo = scanner.nextLine();
		System.out.println("Ingrese el año de fabricación del coche:");
		int anoFabricacion = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Ingrese los kilómetros del coche:");
		int km = scanner.nextInt();
		scanner.nextLine();

		Coche nuevoCoche = new Coche();
		nuevoCoche.setMarca(marca);
		nuevoCoche.setModelo(modelo);
		nuevoCoche.setAnoFabricacion(anoFabricacion);
		nuevoCoche.setKm(km);

		if (gestorCoche.agregarCoche(nuevoCoche)) {
			System.out.println("Coche agregado correctamente.");
		} else {
			System.out.println("Error al agregar el coche.");
		}
	}

	/**
	 * Permite al usuario eliminar un coche del sistema por su ID.
	 * 
	 * @param gestorCoche El gestor de coches que realizará la operación.
	 * @param scanner     El scanner para leer la entrada del usuario.
	 */

	public static void eliminarCoche(GestorCoche gestorCoche, Scanner scanner) {
		System.out.println("Ingrese el ID del coche a eliminar:");
		int id = scanner.nextInt();
		scanner.nextLine();

		if (gestorCoche.eliminarCoche(id)) {
			System.out.println("Coche eliminado correctamente.");
		} else {
			System.out.println("Error al eliminar el coche.");
		}
	}

	/**
	 * Permite al usuario buscar un coche en el sistema por su ID y muestra su
	 * información.
	 * 
	 * @param gestorCoche El gestor de coches que realizará la operación.
	 * @param scanner     El scanner para leer la entrada del usuario.
	 */

	public static void buscarCochePorId(GestorCoche gestorCoche, Scanner scanner) {
		System.out.println("Ingrese el ID del coche a buscar:");
		int id = scanner.nextInt();
		scanner.nextLine();

		Coche coche = gestorCoche.buscarCochePorId(id);
		if (coche != null) {
			System.out.println("Coche encontrado: " + coche);
		} else {
			System.out.println("Coche no encontrado.");
		}
	}

	/**
	 * Permite al usuario modificar un coche del sistema por su ID.
	 * 
	 * @param gestorCoche El gestor de coches que realizará la operación.
	 * @param scanner     El scanner para leer la entrada del usuario.
	 */

	public static void modificarCoche(GestorCoche gestorCoche, Scanner scanner) {
		System.out.println("Ingrese el ID del coche a modificar:");
		int id = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Ingrese la nueva marca del coche:");
		String nuevaMarca = scanner.nextLine();
		System.out.println("Ingrese el nuevo modelo del coche:");
		String nuevoModelo = scanner.nextLine();
		System.out.println("Ingrese el nuevo año de fabricación del coche:");
		int nuevoAnoFabricacion = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Ingrese los nuevos kilómetros del coche:");
		int nuevosKm = scanner.nextInt();
		scanner.nextLine();

		Coche cocheModificado = new Coche(id, nuevaMarca, nuevoModelo, nuevoAnoFabricacion, nuevosKm);

		if (gestorCoche.modificarCoche(cocheModificado)) {
			System.out.println("Coche modificado correctamente.");
		} else {
			System.out.println("Error al modificar el coche.");
		}
	}

	/**
	 * Lista todos los coches registrados en el sistema.
	 * 
	 * @param gestorCoche El gestor de coches que realizará la operación.
	 */

	public static void listarCoches(GestorCoche gestorCoche) {
		System.out.println("Listado de coches:");
		List<Coche> coches = gestorCoche.listarCoches();
		if (coches != null && !coches.isEmpty()) {
			for (Coche coche : coches) {
				System.out.println(coche);
			}
		} else {
			System.out.println("No hay coches registrados.");
		}
	}

	/**
	 * Permite al usuario acceder al menú de gestión de pasajeros.
	 * 
	 * @param scanner El scanner para leer la entrada del usuario.
	 */

	public static void gestionarPasajeros(Scanner scanner) {
		while (true) {
			System.out.println("\nGESTIÓN DE PASAJEROS:");
			System.out.println("1. Crear nuevo pasajero");
			System.out.println("2. Borrar pasajero por ID");
			System.out.println("3. Consultar pasajero por ID");
			System.out.println("4. Listar todos los pasajeros");
			System.out.println("5. Añadir pasajero a coche");
			System.out.println("6. Eliminar pasajero de un coche");
			System.out.println("7. Listar todos los pasajeros de un coche");
			System.out.println("8. Volver al menú principal");
			System.out.print("Seleccione una opción: ");

			int opcion = scanner.nextInt();
			scanner.nextLine();

			switch (opcion) {
			case 1:
				crearNuevoPasajero(gestorPasajero, scanner);
				break;
			case 2:
				borrarPasajeroPorId(gestorPasajero, scanner);
				break;
			case 3:
				consultarPasajeroPorId(gestorPasajero, scanner);
				break;
			case 4:
				listarTodosLosPasajeros(gestorPasajero);
				break;
			case 5:
				añadirPasajeroACoche(gestorPasajero, scanner);
				break;
			case 6:
				eliminarPasajeroDeCoche(gestorPasajero, scanner);
				break;
			case 7:
				listarPasajerosDeCoche(gestorPasajero, scanner);
				break;
			case 8:
				return; // Volver al menú principal
			default:
				System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
			}
		}
	}

	/**
	 * Permite al usuario crear un nuevo pasajero y agregarlo al sistema.
	 * 
	 * @param gestorPasajero El gestor de pasajeros que realizará la operación.
	 * @param scanner        El scanner para leer la entrada del usuario.
	 */

	public static void crearNuevoPasajero(GestorPasajero gestorPasajero, Scanner scanner) {
		System.out.print("Ingrese el nombre del nuevo pasajero: ");
		String nombre = scanner.nextLine();
		System.out.print("Ingrese la edad del nuevo pasajero: ");
		int edad = scanner.nextInt();
		System.out.print("Ingrese el peso del nuevo pasajero: ");
		double peso = scanner.nextDouble();

		Pasajero nuevoPasajero = new Pasajero();
		nuevoPasajero.setNombre(nombre);
		nuevoPasajero.setEdad(edad);
		nuevoPasajero.setPeso(peso);

		if (gestorPasajero.crearNuevoPasajero(nuevoPasajero)) {
			System.out.println("Pasajero creado correctamente.");
		} else {
			System.out.println("Error al crear el pasajero.");
		}
	}

	/**
	 * Permite al usuario eliminar un pasajero del sistema por su ID.
	 * 
	 * @param gestorPasajero El gestor de pasajeros que realizará la operación.
	 * @param scanner        El scanner para leer la entrada del usuario.
	 */

	public static void borrarPasajeroPorId(GestorPasajero gestorPasajero, Scanner scanner) {
		System.out.print("Ingrese el ID del pasajero a borrar: ");
		int id = scanner.nextInt();
		scanner.nextLine();

		if (gestorPasajero.borrarPasajeroPorId(id)) {
			System.out.println("Pasajero borrado correctamente.");
		} else {
			System.out.println("Error al borrar el pasajero.");
		}
	}

	/**
	 * Permite al usuario buscar un pasajero en el sistema por su ID y muestra su
	 * información.
	 * 
	 * @param gestorPasajero El gestor de pasajeros que realizará la operación.
	 * @param scanner        El scanner para leer la entrada del usuario.
	 */

	public static void consultarPasajeroPorId(GestorPasajero gestorPasajero, Scanner scanner) {
		System.out.print("Ingrese el ID del pasajero a consultar: ");
		int id = scanner.nextInt();
		scanner.nextLine();

		Pasajero pasajero = gestorPasajero.consultarPasajeroPorId(id);
		if (pasajero != null) {
			System.out.println("Información del pasajero: " + pasajero);
		} else {
			System.out.println("Pasajero no encontrado.");
		}
	}

	/**
	 * Lista todos los pasajeros registrados en el sistema.
	 * 
	 * @param gestorPasajero El gestor de pasajeros que realizará la operación.
	 */

	public static void listarTodosLosPasajeros(GestorPasajero gestorPasajero) {
		System.out.println("Listado de todos los pasajeros:");
		List<Pasajero> pasajeros = gestorPasajero.listarTodosLosPasajeros();
		if (pasajeros.isEmpty()) {
			System.out.println("No hay pasajeros registrados.");
		} else {
			for (Pasajero pasajero : pasajeros) {
				System.out.println(pasajero);
			}
		}
	}

	/**
	 * Permite al usuario añadir un pasajero a un coche en el sistema.
	 * 
	 * @param gestorPasajero El gestor de pasajeros que realizará la operación.
	 * @param scanner        El scanner para leer la entrada del usuario.
	 * @return true si el pasajero se añadió correctamente al coche, false en caso
	 *         contrario.
	 */

	public static boolean añadirPasajeroACoche(GestorPasajero gestorPasajero, Scanner scanner) {
		System.out.println("Listado de coches disponibles:");
		List<Coche> cochesDisponibles = gestorCoche.listarCoches();
		for (Coche coche : cochesDisponibles) {
			System.out.println(coche);
		}

		System.out.print("Ingrese el ID del pasajero: ");
		int idPasajero = scanner.nextInt();
		System.out.print("Ingrese el ID del coche: ");
		int idCoche = scanner.nextInt();

		boolean agregado = gestorPasajero.añadirPasajeroACoche(idPasajero, idCoche);
		if (agregado) {
			System.out.println("Pasajero añadido al coche exitosamente.");
		} else {
			System.out.println("Error al agregar el pasajero al coche.");
		}
		return agregado;
	}

	/**
	 * Permite al usuario eliminar un pasajero de un coche en el sistema.
	 * 
	 * @param gestorPasajero El gestor de pasajeros que realizará la operación.
	 * @param scanner        El scanner para leer la entrada del usuario.
	 * @return true si el pasajero se eliminó correctamente del coche, false en caso
	 *         contrario.
	 */

	public static boolean eliminarPasajeroDeCoche(GestorPasajero gestorPasajero, Scanner scanner) {
		System.out.println("Listado de coches y sus pasajeros asociados:");
		List<Coche> coches = gestorCoche.listarCoches();
		for (Coche coche : coches) {
			System.out.println("Coche: " + coche);
			List<Pasajero> pasajerosAsociados = gestorPasajero.listarPasajerosDeCoche(coche.getId());
			if (pasajerosAsociados.size() > 0) {
				System.out.println("Pasajeros asociados:");
				for (Pasajero pasajero : pasajerosAsociados) {
					System.out.println(pasajero);
				}
			} else {
				System.out.println("No hay pasajeros asociados a este coche.");
			}
		}

		System.out.print("Ingrese el ID del pasajero: ");
		int idPasajero = scanner.nextInt();
		System.out.print("Ingrese el ID del coche: ");
		int idCoche = scanner.nextInt();

		boolean eliminado = gestorPasajero.eliminarPasajeroDeCoche(idPasajero, idCoche);
		if (eliminado) {
			System.out.println("Pasajero eliminado del coche exitosamente.");
		} else {
			System.out.println("Error al eliminar el pasajero del coche.");
		}
		return eliminado;
	}

	/**
	 * Lista todos los pasajeros asociados a un coche en el sistema.
	 * 
	 * @param gestorPasajero El gestor de pasajeros que realizará la operación.
	 * @param scanner        El scanner para leer la entrada del usuario.
	 */

	public static void listarPasajerosDeCoche(GestorPasajero gestorPasajero, Scanner scanner) {
		System.out.print("Ingrese el ID del coche: ");
		int idCoche = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Listado de pasajeros del coche con ID " + idCoche + ":");
		List<Pasajero> pasajeros = gestorPasajero.listarPasajerosDeCoche(idCoche);
		if (pasajeros.isEmpty()) {
			System.out.println("No hay pasajeros en este coche.");
		} else {
			for (Pasajero pasajero : pasajeros) {
				System.out.println(pasajero);
			}
		}
	}
}