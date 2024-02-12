package vista;

import modelo.entidad.Pasajero;
import modelo.entidad.PasajeroEnCoche;
import modelo.negocio.GestorPasajero;
import modelo.negocio.GestorPasajeroEnCoche;
import java.util.Scanner;
import java.util.List;

/**
 * Clase principal de la vista que muestra el menú y gestiona las interacciones del usuario.
 */
public class VistaPasajeros {

    private Scanner leer = new Scanner(System.in);
    private GestorPasajero gestorPasajero = new GestorPasajero();
    private GestorPasajeroEnCoche gestorPasajeroEnCoche = new GestorPasajeroEnCoche();
    private VistaPrincipal vp = new VistaPrincipal();

    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = Integer.parseInt(leer.nextLine());

            switch (opcion) {
                case 1:
                    crearNuevoPasajero();
                    break;
                case 2:
                    borrarPasajeroPorId();
                    break;
                case 3:
                    consultaPasajeroPorId();
                    break;
                case 4:
                    listarTodosLosPasajeros();
                    break;
                case 5:
                    agregarPasajeroACoche();
                    break;
                case 6:
                    eliminarPasajeroDeCoche();
                    break;
                case 7:
                    listarPasajerosDeCoche();
                    break;
                case 8:
                    System.out.println("Regresando al menú principal...");
                    vp.iniciar();
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elige una opción entre 1 y 8.");
            }
        } while (opcion != 8);
    }

    private void mostrarMenu() {
        System.out.println("\n********* Menú de Gestión de Pasajeros *********\n\n"
                + "1. Crear nuevo pasajero\n"
                + "2. Borrar pasajero por ID\n"
                + "3. Consulta pasajero por ID\n"
                + "4. Listar todos los pasajeros\n"
                + "5. Añadir pasajero a coche\n"
                + "6. Eliminar pasajero de coche\n"
                + "7. Listar todos los pasajeros de un coche\n"
                + "8. Regresar al menú principal\n"
                + "Elige una opción: ");
    }
    
    // Opciones

    private void crearNuevoPasajero() {
    	System.out.println("Introduce el nombre del pasajero:");
        String nombre = leer.nextLine();
        System.out.println("Introduce la edad del pasajero:");
        int edad = Integer.parseInt(leer.nextLine()); // Asegúrate de manejar NumberFormatException
        System.out.println("Introduce el peso del pasajero:");
        double peso = Double.parseDouble(leer.nextLine()); // Asegúrate de manejar NumberFormatException

        Pasajero pasajero = new Pasajero();
        pasajero.setNombre(nombre);
        pasajero.setEdad(edad);
        pasajero.setPeso(peso);

        boolean resultado = gestorPasajero.agregarPasajero(pasajero);
        if (resultado) {
            System.out.println("Pasajero agregado con éxito.");
        } else {
            System.out.println("Error al agregar el pasajero.");
        }
    }

    private void borrarPasajeroPorId() {
    	System.out.println("Introduce el ID del pasajero a borrar:");
        int id = Integer.parseInt(leer.nextLine());

        boolean resultado = gestorPasajero.eliminarPasajero(id);
        if (resultado) {
            System.out.println("Pasajero eliminado con éxito.");
        } else {
            System.out.println("Error al eliminar el pasajero.");
        }
    }

    private void consultaPasajeroPorId() {
    	System.out.println("Introduce el ID del pasajero a consultar:");
        int id = Integer.parseInt(leer.nextLine());

        Pasajero pasajero = gestorPasajero.buscarPasajeroPorId(id);
        if (pasajero != null) {
            System.out.println("Pasajero encontrado: " + pasajero);
        } else {
            System.out.println("No se encontró un pasajero con el ID especificado.");
        }
    }

    private void listarTodosLosPasajeros() {
    	 List<Pasajero> pasajeros = gestorPasajero.listarPasajeros();
    	    if (pasajeros.isEmpty()) {
    	        System.out.println("No hay pasajeros registrados.");
    	    } else {
    	        System.out.println("Listado de pasajeros:");
    	        for (Pasajero pasajero : pasajeros) {
    	            System.out.println(pasajero);
    	        }
    	    }
    }

    private void agregarPasajeroACoche() {
    	System.out.println("Introduce el ID del pasajero:");
        listarTodosLosPasajeros();
        int idPasajero = Integer.parseInt(leer.nextLine());
        System.out.println("Introduce el ID del coche:");
        vp.listarCoches();
        int idCoche = Integer.parseInt(leer.nextLine());

        boolean resultado = gestorPasajeroEnCoche.agregarPasajeroACoche(idPasajero, idCoche);
        if (resultado) {
            System.out.println("Pasajero agregado al coche con éxito.");
        } else {
            System.out.println("Error al agregar el pasajero al coche.");
        }
    }

    private void eliminarPasajeroDeCoche() {
    	System.out.println("Introduce el ID del pasajero a eliminar del coche:");
    	listarTodosLosPasajeros();
        int idPasajero = Integer.parseInt(leer.nextLine());

        boolean resultado = gestorPasajeroEnCoche.eliminarPasajeroDeCoche(idPasajero);
        if (resultado) {
            System.out.println("Pasajero eliminado del coche con éxito.");
        } else {
            System.out.println("Error al eliminar el pasajero del coche.");
        }
    }

    private void listarPasajerosDeCoche() {
    	System.out.println("Introduce el ID del coche para listar sus pasajeros:");
    	vp.listarCoches();
        int idCoche = Integer.parseInt(leer.nextLine());

        List<PasajeroEnCoche> pasajerosEnCoche = gestorPasajeroEnCoche.listarPasajerosPorCoche(idCoche);
        if (pasajerosEnCoche.isEmpty()) {
            System.out.println("No hay pasajeros registrados en este coche.");
        } else {
            System.out.println("Pasajeros del coche " + idCoche + ":");
            for (PasajeroEnCoche pec : pasajerosEnCoche) {
                 System.out.println(gestorPasajero.buscarPasajeroPorId(pec.getIdPasajero()));
            }
        }
    }
}

