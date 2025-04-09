package co.poligran.ventas;

import java.io.PrintWriter;
import java.util.Random;

/**
 * Esta clase contiene el método para generar archivos de prueba
 * con información aleatoria de productos, vendedores y ventas.
 *
 * @author Rome
 */
public class GenerateInfoFiles {

    /**
     * Método principal que se ejecuta al iniciar la clase.
     * Genera los archivos de prueba de productos, información de vendedores
     * y archivos de ventas para varios vendedores.
     * Muestra mensajes de éxito o error durante el proceso.
     *
     * @param args 
     */
    public static void main(String[] args) {
        System.out.println("Generador de Archivos de Prueba");

        try {
            createProductsFile(10);
            createSalesManInfoFile(5);

            for (int i = 1; i <= 5; i++) {
                String nombre = "vendedor" + i;
                long id = 1000 + i;
                createSalesFile(20, nombre, id);
            }

            System.out.println(" Archivos generados exitosamente.");
        } catch (Exception e) {
            System.out.println(" Error al generar los archivos: " + e.getMessage());
        }
    }

    /**
     * Genera un archivo de texto llamado "Productos.txt" con información
     * aleatoria de productos.
     *
     * @param productCount 
     */
    public static void createProductsFile(int productCount) {
        String fileName = "Productos.txt";
        try (PrintWriter writer = new PrintWriter(fileName)) {
            for (int i = 1; i <= productCount; i++) {
                String id = "P" + i;
                String nombre = "Producto" + i;
                double precio = Math.round((Math.random() * 100 + 1) * 100.0) / 100.0;
                writer.println(id + ";" + nombre + ";" + precio);
            }
            System.out.println(" Archivo de productos creado: " + fileName);
        } catch (Exception e) {
            System.out.println(" Error creando archivo de productos: " + e.getMessage());
        }
    }

    /**
     * Genera un archivo de texto  llamado "Vendedores.txt" con información
     * aleatoria de vendedores.
     *
     * @param count 
     */
    public static void createSalesManInfoFile(int count) {
        String fileName = "Vendedores.txt";
        try (PrintWriter writer = new PrintWriter(fileName)) {
            for (int i = 1; i <= count; i++) {
                String tipoDocumento = "CC";
                String numeroDocumento = "100" + i;
                String nombre = "Nombre" + i;
                String apellidos = "Apellido" + i;
                writer.println(tipoDocumento + ";" + numeroDocumento + ";" + nombre + ";" + apellidos);
            }
            System.out.println(" Archivo de vendedores creado: " + fileName);
        } catch (Exception e) {
            System.out.println(" Error creando archivo de vendedores: " + e.getMessage());
        }
    }

    /**
     * Genera un archivo de texto plano con información aleatoria de ventas
     * para un vendedor específico. 
     * @param count  
     * @param nombre 
     * @param id     
     */
    public static void createSalesFile(int count, String nombre, long id) {
        String fileName = nombre + "_ventas.txt";
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.println("CC;" + id);

            Random random = new Random();
            for (int i = 1; i <= count; i++) {
                String productoID = "P" + ((i % 10) + 1);
                int cantidad = random.nextInt(10) + 1;
                writer.println(productoID + ";" + cantidad);
            }
            System.out.println(" Archivo de ventas creado: " + fileName);
        } catch (Exception e) {
            System.out.println(" Error creando archivo de ventas: " + e.getMessage());
        }
    }
}