package vista;

import java.util.List;
import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.negocio.GestorCoche;

/**
 * Clase principal de la vista que muestra el menú y gestiona las interacciones con el usuario.
 */
public class VistaPrincipal {

    private Scanner leer;
    private GestorCoche gestorCoche;
    private VistaPasajeros vistaPasajeros;

    public VistaPrincipal() {
        this.leer = new Scanner(System.in);
        this.gestorCoche = new GestorCoche();
    }

    /**
     * Inicia la aplicación mostrando el menú principal.
     */
    public void iniciar() {
        int opcion;
        boolean continuar = true;
        do {
            mostrarMenu();
            opcion = Integer.parseInt(leer.nextLine()); 

            switch (opcion) {
                case 1:
                	//Agregar nuevo coche
                    agregarNuevoCoche();
                    break;
                case 2:
                	//Borrar coche por ID
                	borrarCochePorId();
                    break;
                case 3:
                	//Consulta coche por ID
                	consultarCochePorId();
                    break;
                case 4:
                	//Modificar coche por ID
                	modificarCochePorId();
                    break;
                case 5:
                	//Listado de coches
                	listarCoches();
                    break;
                case 6:
                	//Gestión de pasajeros
                    gestionPasajeros();
                    break;
                case 7:
                	//Terminar el programa
                    System.out.println("Terminando el programa...");
                    continuar = false;
                    break;
                default:
                	//Opción no válida
                    System.out.println("Opción no válida. Por favor, elige una opción entre 1 y 7.");
            }
        } while (continuar != false);
    }

    private void mostrarMenu() {
        System.out.println("\n********* Menú Principal *********\n\n"
        		+ "1. Añadir nuevo coche\n"
        		+ "2. Borrar coche por ID\n"
        		+ "3. Consulta coche por ID\n"
        		+ "4. Modificar coche por ID\n"
        		+ "5. Listado de coches\n"
        		+ "6. Gestión de pasajeros\n"
        		+ "7. Terminar el programa\n"
        		+ "Elige una opción: \n");
    }
    
    
    //Opciones
    
    private void agregarNuevoCoche() {
        System.out.println("Introduce la marca del coche:");
        String marca = leer.nextLine();
        System.out.println("Introduce el modelo:");
        String modelo = leer.nextLine();
        System.out.println("Introduce el año de fabricación:");
        int año = Integer.parseInt(leer.nextLine());
        System.out.println("Introduce los km:");
        String km = leer.nextLine();

        Coche coche = new Coche();
        coche.setMarca(marca);
        coche.setModelo(modelo);
        coche.setAño(año);
        coche.setKm(km);;

        boolean resultado = gestorCoche.agregarCoche(coche);
        if (resultado) {
            System.out.println("Coche agregado con éxito.");
        } else {
            System.out.println("Error al agregar el coche.");
        }
    }

    private void borrarCochePorId() {
        System.out.println("Introduce el ID del coche a borrar:");
        listarCoches();
        int id = Integer.parseInt(leer.nextLine());

        boolean resultado = gestorCoche.eliminarCoche(id);
        if (resultado) {
            System.out.println("Coche eliminado con éxito.");
        } else {
            System.out.println("Error al eliminar el coche.");
        }
    }

    private void consultarCochePorId() {
        System.out.println("Introduce el ID del coche a consultar:");
        listarCoches();
        int id = Integer.parseInt(leer.nextLine());

        Coche coche = gestorCoche.buscarCoche(id);
        if (coche != null) {
            System.out.println("Coche encontrado: " + coche);
        } else {
            System.out.println("No se encontró un coche con el ID especificado.");
        }
    }

    private void modificarCochePorId() {
        System.out.println("Introduce el ID del coche a modificar:");
        listarCoches();
        int id = Integer.parseInt(leer.nextLine());

        Coche coche = gestorCoche.buscarCoche(id);
        if (coche == null) {
            System.out.println("No se encontró un coche con el ID especificado.");
            return;
        }

        System.out.println("Introduce la nueva marca del coche (actual: " + coche.getMarca() + "):");
        coche.setMarca(leer.nextLine());
        System.out.println("Introduce el nuevo modelo (actual: " + coche.getModelo() + "):");
        coche.setModelo(leer.nextLine());
        System.out.println("Introduce el nuevo año de fabricación (actual: " + coche.getAño() + "):");
        coche.setAño(Integer.parseInt(leer.nextLine()));
        System.out.println("Introduce los nuevos km (actual: " + coche.getKm() + "):");
        coche.setKm(leer.nextLine());

        boolean resultado = gestorCoche.actualizarCoche(coche);
        if (resultado) {
            System.out.println("Coche actualizado con éxito.");
        } else {
            System.out.println("Error al actualizar el coche.");
        }
    }

    public void listarCoches() {
        List<Coche> coches = gestorCoche.listarCoches();
        if (coches.isEmpty()) {
            System.out.println("No hay coches registrados.");
        } else {
            System.out.println("Listado de coches:");
            for (Coche coche : coches) {
                System.out.println(coche);
            }
        }
    }

    private void gestionPasajeros() {
    	vistaPasajeros = new VistaPasajeros();
    	vistaPasajeros.iniciar();
    }
    
    public static void main(String[] args) {
        VistaPrincipal vistaPrincipal = new VistaPrincipal();
        vistaPrincipal.iniciar();
    }
}
