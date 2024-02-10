package modelo.vista;

import java.util.List;
import java.util.Scanner;
import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.entidad.PasajeroAsociadoCoche;
import modelo.negocio.GestorCoche;
import modelo.negocio.GestorPasajero;
import modelo.negocio.GestorPasajeroAsociadoCoche;
	
	public class Main {
	    static GestorCoche gc = new GestorCoche();
	    static GestorPasajero gp = new GestorPasajero();
	    static GestorPasajeroAsociadoCoche gpac = new GestorPasajeroAsociadoCoche();
	    static Scanner leer = new Scanner(System.in);
	
	    public static void main(String[] args) {
	        int opcion = 0;
	        do {
	            opcion = menu();
	
	            switch (opcion) {
	                case 1:
	                    altaCoche();
	                    break;
	                case 2:
	                    bajaCoche();
	                    break;
	                case 3:
	                    buscarCoche();
	                    break;
	                case 4:
	                    modificarCoche();
	                    break;
	                case 5:
	                    listarTodos();
	                    System.out.println();
	                    break;
	                case 6:
	                    gestionPasajeros();
	                    System.out.println();
	                    break;
	                case 7:
	                    System.out.println("Fin Programa");
	                    break;
	            }
	
	        } while (opcion != 7);
	    }

	    // Métodos propios
	
	    // 1. Alta de un objeto coche en la base de datos.
	    public static void altaCoche() {
	        System.out.println("Introducir año de fabricación: ");
	        int año = leer.nextInt();
	        leer.nextLine();	
	        System.out.println("Introducir kilometros vehículo: ");
	        int km = leer.nextInt();
	        leer.nextLine();	
	        System.out.println("Introducir marca: ");
	        String marca = leer.nextLine();	
	        System.out.println("Introducir modelo: ");
	        String modelo = leer.nextLine();
	
	        if (!marca.trim().isEmpty() && !modelo.trim().isEmpty()) {
	            Coche coche = new Coche(0, año, km, marca, modelo);
	
	            if (gc.alta(coche)) {
	                System.out.println("Coche dado de alta correctamente ");
	            } else {
	                System.out.println("Error al dar de alta el coche");
	            }
	        } else {
	            System.out.println("La marca y el modelo no pueden estar vacíos");
	        }
	    }
	
	    // 2. Baja de un objeto coche en la base de datos.
	    public static void bajaCoche() {
	        System.out.println("Introduce el id del coche a dar de baja ");
	        int id = leer.nextInt();
	
	        if (gc.baja(id)) {
	            System.out.println("Coche dado de baja correctamente");
	        } else {
	            System.out.println("Error al dar de baja el coche");
	        }
	    }
	
	    // 3. Localizar un objeto coche dentro de la base de datos.
	    public static void buscarCoche() {
	        System.out.println("Introduce el id del coche a buscar ");
	        int id = leer.nextInt();
	        Coche coche = gc.buscarCoche(id);
	
	        if (coche != null) {
	            System.out.println("Coche encontrado : " + coche);
	        } else {
	            System.out.println("No se encontró el coche con id: " + id);
	        }
	    }
	
	    // 4. Modificar los atributos de un objeto coche de la base de datos.
	    public static void modificarCoche() {
	        System.out.println("Introduce el id del coche a modificar ");
	        int id = leer.nextInt();
	        leer.nextLine();
	        Coche cocheExiste = gc.buscarCoche(id);
	
	        if (cocheExiste != null) {
	            System.out.println("Introducir año de fabricación: ");
	            int año = leer.nextInt();
	            leer.nextLine();
	            System.out.println("Introducir kilometros vehículo: ");
	            int km = leer.nextInt();
	            leer.nextLine();
	            System.out.println("Introducir marca: ");	            
	            String marca = leer.nextLine();	          
	            System.out.println("Introducir modelo: ");
	            String modelo = leer.nextLine();
	
	            // No pueden quedar vacios los campos marca y modelo.
	            
	            if (marca.trim().isEmpty() || modelo.trim().isEmpty()) {
	                System.out.println("La marca y el modelo no pueden estar vacíos");
	            } else {
	                Coche cocheModificado = new Coche(id, año, km, marca, modelo);	
	                int resultado = gc.modificar(cocheModificado);  
		                  
	                System.out.println("Coche modificado correctamente");		                
	            	}
	        } else {
	            System.out.println("No se encontró el coche con id: " + id);
	        }
	    }
	
	    // 5. Listar todos los coches de la base de datos.
	    
	    public static void listarTodos() {
	        List<Coche> listaCoches = gc.listar();
	
	        if (!listaCoches.isEmpty()) {
	            for (Coche coche : listaCoches) {
	                System.out.println(coche);
	            }
	        } else {
	            System.out.println("No hay coches registrados");
	        }
	    }
	
	    // Submenu dentro de la opción gestionar pasajeros
	    
	    public static void gestionPasajeros() {
	        int opcion;
	        do {
	        	System.out.println("");
	            System.out.println("***** MENU GESTION PASAJEROS *****");
	            System.out.println("----------------------------------");
	            System.out.println("1. Crear nuevo pasajero");
	            System.out.println("2. Borrar pasajero por id");
	            System.out.println("3. Consultar pasajero por id");
	            System.out.println("4. Listar todos los pasajeros");
	            System.out.println("5. Añadir pasajero a coche");
	            System.out.println("6. Eliminar pasajero de un coche");
	            System.out.println("7. Listar todos los pasajeros de un coche");
	            System.out.println("8. Volver al menú principal");
	            System.out.print("Seleccione una opción: ");
	            opcion = leer.nextInt();
	
	            switch (opcion) {
	                case 1:
	                    altaPasajero();
	                    break;
	
	                case 2:
	                    bajaPasajero();
	                    break;
	
	                case 3:
	                    buscarPasajero();
	                    break;
	
	                case 4:
	                    listarTodosPasajeros();
	                    break;
	
	                case 5:
	                    agregarPasajeroACoche();
	                    break;
	
	                case 6:
	                    eliminarPasajeroDeCoche();
	                    break;
	
	                case 7:
	                    listarPasajerosCoche();
	                    break;
	
	                case 8:
	                    System.out.println("Volviendo al menú principal");
	                    break;
	
	                default:
	                    System.out.println("Opción no válida");
	            }
	        } while (opcion != 8);
	    }
	    
	    
	    //Métodos propios del submenu
	      
	
	  //1. Listar los pasajeros de un coche
	    private static void listarPasajerosCoche() {
	        System.out.println("Introduce el id del coche para listar pasajeros: ");
	        int idCoche = leer.nextInt();
	        Coche coche = gc.buscarCoche(idCoche);

	        if (coche != null) {
	            List<PasajeroAsociadoCoche> pasajerosAsociados = gpac.listarPasajerosDeCoche(idCoche);
	            if (!pasajerosAsociados.isEmpty()) {
	                System.out.println("Pasajeros del coche con id " + idCoche + ":");

	                for (PasajeroAsociadoCoche pac : pasajerosAsociados) {
	                    Pasajero pasajero = pac.getPasajero(); // Accede al Pasajero asociado
	                    System.out.println(pasajero);
	                }
	            } else {
	                System.out.println("El coche no tiene pasajeros asignados.");
	            }
	        } else {
	            System.out.println("No se encontró el coche con id: " + idCoche);
	        }
	    }
	
	    //2. Dar de baja  a un pasajero de un coche
	    
	    private static void eliminarPasajeroDeCoche() {
	    	// Mostrar coches y sus pasajeros asociados
	        List<Coche> coches = gc.listar();
	        
	        if (!coches.isEmpty()) {
	            System.out.println("Coches disponibles con sus pasajeros:");
	            for (Coche coche : coches) {
	                System.out.println("ID Coche: " + coche.getId());
	               
	                                
	                List<PasajeroAsociadoCoche> pasajerosAsociados = gpac.listarPasajerosDeCoche(coche.getId());

	                if (!pasajerosAsociados.isEmpty()) {
	                    System.out.println("Pasajeros asociados:");

	                    for (PasajeroAsociadoCoche pac : pasajerosAsociados) {
	                        Pasajero pasajero = pac.getPasajero();
	                        System.out.println("ID Pasajero: " + pasajero.getId() + ", Nombre: " + pasajero.getNombre());
	                    }
	                } else {
	                    System.out.println("No hay pasajeros asociados a este coche.");
	                }

	                System.out.println("---------------------------");
	            }

	            System.out.println("Introduce el id del coche: ");
	            int idCoche = leer.nextInt();

	            System.out.println("Introduce el id del pasajero a eliminar: ");
	            int idPasajero = leer.nextInt();

	            if (gpac.eliminarPasajeroDeCoche(idPasajero, idCoche)) {
	                System.out.println("Pasajero dado de baja del coche correctamente.");
	            } else {
	                System.out.println("Error al dar de baja al pasajero del coche.");
	            }
	        } else {
	            System.out.println("No hay coches registrados para eliminar pasajeros.");
	        }
	    }
	    
	
	    //3. Añadir pasajero a un coche.
	    
	    private static void agregarPasajeroACoche() {
	        // Mostrar coches disponibles
	        List<Coche> cochesDisponibles = gpac.cochesDisponibles();
	        
	        if (!cochesDisponibles.isEmpty()) {
	            System.out.println("Coches disponibles:");
	            for (Coche coche : cochesDisponibles) {
	                System.out.println(coche);
	            }

	            System.out.println("Introduce el id del coche: ");
	            int idCoche = leer.nextInt();

	            System.out.println("Introduce el id del pasajero a agregar: ");
	            int idPasajero = leer.nextInt();

	            if (gpac.añadirPasajeroACoche(idPasajero, idCoche)) {
	                System.out.println("Pasajero agregado al coche correctamente.");
	            } else {
	                System.out.println("Error al agregar pasajero al coche.");
	            }
	        } else {
	            System.out.println("No hay coches disponibles para asignar pasajeros.");
	        }
	    }
	    
	    //4. Listar todos los pasajeros.
	    
	    private static void listarTodosPasajeros() {
	        List<Pasajero> listaPasajeros = gp.listarTodos();
	
	        if (!listaPasajeros.isEmpty()) {
	            for (Pasajero p : listaPasajeros) {
	                System.out.println(p);
	            }
	        } else {
	            System.out.println("No hay pasajeros registrados");
	        }
	    }
	    
	    //5. Buscar un pasajero.
	
	    private static void buscarPasajero() {
	        System.out.println("Introduce el id del pasajero a buscar ");
	        int id = leer.nextInt();
	        Pasajero p = gp.buscarPasajero(id);
	
	        if (p != null) {
	            System.out.println("Pasajero encontrado : " + p);
	        } else {
	            System.out.println("No se encontró el pasajero con id: " + id);
	        }
	    }
	    
	    //6. Dar de baja a un pasajero.
	
	    private static void bajaPasajero() {
	        System.out.println("Introduce el id del pasajero a dar de baja ");
	        int id = leer.nextInt();
	
	        if (gp.baja(id)) {
	            System.out.println("Pasajero dado de baja correctamente");
	        } else {
	            System.out.println("Error al dar de baja el pasajero");
	        }
	    }
	    
	    //7. Alta de un pasajero nuevo.
	
	    private static void altaPasajero() {
	        System.out.println("Introducir nombre del pasajero: ");
	        String nombre = leer.next();	
	        System.out.println("Introducir edad del pasajero: ");
	        int edad = leer.nextInt();	
	        System.out.println("Introducir peso del pasajero: ");
	        double peso = leer.nextDouble();
	
	        Pasajero p = new Pasajero(0, nombre, edad, peso);
	
	        if (gp.alta(p)) {
	            System.out.println("Pasajero dado de alta correctamente");
	        } else {
	            System.out.println("Error al dar de alta el pasajero");
	        }
	    }
	
	    // Diseño interfaz de Menú.
	    
	    public static int menu() {
	        int opcion;
	
	        System.out.println("");
	        System.out.println("***** MENU *****");
	        System.out.println("----------------");
	        System.out.println("1. Alta coche");
	        System.out.println("2. Baja coche por id");
	        System.out.println("3. Buscar coche por id");
	        System.out.println("4. Modificar coche por id");
	        System.out.println("5. Listar todos los coches");
	        System.out.println("6. Gestionar pasajeros");
	        System.out.println("7. Terminar el programa");
	        System.out.println("Introduce una opción");
	        opcion = leer.nextInt();
	
	        while (opcion < 1 || opcion > 7) {
	            System.out.println("Recuerda, del 1 al 7");
	            opcion = leer.nextInt();
	        }
	
	        return opcion;
	    }
}
