package modelo.presentacion;

import java.util.Scanner;

import modelo.presentacion.opciones.OpcionesMenuPasajeros;
import modelo.presentacion.opciones.OpcionesVistaPrincipal;


/**
 * Clase que representa la interfaz de usuario principal para la gestión de coches y pasajeros.
 * Proporciona un menú interactivo con opciones para realizar diversas operaciones.
 * 
 * <p>Esta clase utiliza las clases para gestionar
 * las operaciones disponibles en los menús principal y de gestión de pasajeros, respectivamente.
 * 
 * 
 * @see OpcionesVistaPrincipal
 * @see OpcionesMenuPasajeros
 * 
 * @author Alberto Arroyo Santofimia
 * @version 2.0
 * @since 2024-02-08
 */
public class VistaPrincipal {

	private static Scanner leer;
	//Para poder leer las opciones del menu	que estan en un metodo estatico
	static {
			leer = new Scanner(System.in);
	}

	public static void main(String[] args) {
		OpcionesVistaPrincipal opciones = new OpcionesVistaPrincipal();
		OpcionesMenuPasajeros opcionMenuPasajeros = new OpcionesMenuPasajeros();
		opciones.iniciarPrograma();	

		boolean continuar = true;
		
		do {		
        	//Cargamos el menu inicial y recuperamos la opción elegida
			int opcion = menu();
			//Si la opcion está fuera del rango de opciones se repetira el menu
			while (opcion<1 || opcion>8){
				opcion = menu();
			}

	            switch (opcion) {
	                case 1://login del usuario, lectura del archivo
	                	opciones.opcion1(leer);
	                    break;
	                case 2://nuevo usuario, escritura del archivo si no existe ya
	                	opciones.opcion2(leer);
	                    break;
	                case 3:
	                	opciones.opcion3(leer);
	                    break;
	                case 4:
	                	opciones.opcion4(leer);
	                    break;
	                case 5:
	                	opciones.opcion5(leer);
	                    break;
	                case 6:
	                	boolean continuarPasajeros = true;
	                	
	            		do {		
	                    	//Cargamos el menu inicial y recuperamos la opción elegida
	            			int opcionPasajeros = menuGestionPasajeros();
	            			//Si la opcion está fuera del rango de opciones se repetira el menu
	            			while (opcionPasajeros<1 || opcionPasajeros>9){
	            				opcionPasajeros = menuGestionPasajeros();
	            			}

	            	            switch (opcionPasajeros) {
	            	                case 1://login del usuario, lectura del archivo
	            	                	opcionMenuPasajeros.opcion1(leer);
	            	                    break;
	            	                case 2://nuevo usuario, escritura del archivo si no existe ya
	            	                	opcionMenuPasajeros.opcion2(leer);
	            	                    break;
	            	                case 3:
	            	                	opcionMenuPasajeros.opcion3(leer);
	            	                    break;
	            	                case 4:
	            	                	opcionMenuPasajeros.opcion4(leer);
	            	                    break;
	            	                case 5:
	            	                	opcionMenuPasajeros.opcion5(leer);
	            	                    break;
	            	                case 6:
	            	                	opcionMenuPasajeros.opcion6(leer);
	            	                    break;
	            	                case 7:
	            	                	opcionMenuPasajeros.opcion7(leer);	                	
	            	                    break;
	            	                case 8:
	            	                	continuarPasajeros= false;	                	
	            	                    break;
	            	                case 9:	            	                	
	            	                	System.out.println("Programa terminado");
	            	                	continuarPasajeros= false;
	            	                    continuar=false;
	            	                    break;
	            	                default:
	            	                    System.out.println("Opción no válida. Inténtalo de nuevo.");
	            	            }
	            			
	                    } while (continuarPasajeros);
	                    break;
	                case 7:
	                	opciones.opcion7();	                	
	                    break;
	                case 8:
	                	System.out.println("Programa terminado");
	                    continuar=false;
	                    break;
	                default:
	                    System.out.println("Opción no válida. Inténtalo de nuevo.");
	            }
			
        } while (continuar);
		

        // Cierro el scanner
        if (leer != null) {
            leer.close();
        }
				
}

	/**
     * Método que muestra el menú principal y recupera la opción elegida por el usuario.
     *
     * @return La opción elegida por el usuario.
     */
	public static int menu() {
		
		int opcion = 0;
		System.out.println("----------------------------------------------------");
	System.out.println("|                MENU  PRINCIPAL                   |");
	System.out.println("----------------------------------------------------");
	System.out.println("1. Añadir nuevo coche");
	System.out.println("2. Borrar coche ");
	System.out.println("3. Consulta coche ");
	System.out.println("4. Modificar coche ");
	System.out.println("5. Listado de coches");
	System.out.println("6. Gestión de pasajeros");
	System.out.println("7. Datos de conexión BBDD");
	System.out.println("8. Terminar el programa");
	System.out.println("----------------------------------------------------");
	System.out.println("Introduzca una opción del 1 al 8, si quiere salir 8");
	System.out.println("----------------------------------------------------");
	
	try {
		opcion = leer.nextInt();
		
	} catch (java.util.InputMismatchException e) {
	    // Atrapar la excepción si se ingresa algo que no es un entero
	    System.out.println("Entrada no válida. Ingrese un número entero.");
	    leer.next(); // Limpiar el búfer de entrada para evitar un bucle infinito
	}
	
	if (opcion<1 || opcion > 8) {
		System.out.println("OPCION INCORRECTA");
		}
		
		return opcion;	
	}
	
	/**
     * Método que muestra el menú de gestión de pasajeros y recupera la opción elegida por el usuario.
     *
     * @return La opción elegida por el usuario en el menú de gestión de pasajeros.
     */
	public static int menuGestionPasajeros() {
		
		int opcion = 0;
		System.out.println("----------------------------------------------------");
	System.out.println("|            MENU  GESTIÓN PASAJEROS               |");
	System.out.println("----------------------------------------------------");
	System.out.println("1. Crear nuevo pasajero");
	System.out.println("2. Borrar pasajero por id ");
	System.out.println("3. Consulta pasajero por id ");
	System.out.println("4. Listar todos los pasajeros");
	System.out.println("5. Añadir pasajero a coche");
	System.out.println("6. Eliminar pasajero de un coche");
	System.out.println("7. Listar todos los pasajeros de un coche");
	System.out.println("8. Volver al menú principal");
	System.out.println("9. Terminar el programa");
	System.out.println("----------------------------------------------------");
	System.out.println("Introduzca una opción del 1 al 9, si quiere salir 9");
	System.out.println("----------------------------------------------------");
	
	try {
		opcion = leer.nextInt();
		
	} catch (java.util.InputMismatchException e) {
	    // Atrapar la excepción si se ingresa algo que no es un entero
	    System.out.println("Entrada no válida. Ingrese un número entero.");
	    leer.next(); // Limpiar el búfer de entrada para evitar un bucle infinito
	}
	
	if (opcion<1 || opcion > 9) {
		System.out.println("OPCION INCORRECTA");
	}
	
	return opcion;	
	}

}
