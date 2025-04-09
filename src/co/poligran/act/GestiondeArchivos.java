package co.poligran.act;

import co.poligran.ventas.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * Clase de utilidades para gestionar la lectura de archivos
 * de vendedores, productos y ventas.
 *
 * También valida y organiza los datos para su posterior procesamiento.
 *
 * @author Rome
 */
public class GestiondeArchivos {

    /**
     * Carga la información de los vendedores desde un archivo de texto .
     *
     * @param ruta 
     * @return lista de objetos 
     */
    public static List<vendedor> cargarVendedores(String ruta) throws IOException {
        List<vendedor> vendedores = new ArrayList<>();
        List<String> lineas = Files.readAllLines(Paths.get(ruta));

        for (String linea : lineas) {
            String[] partes = linea.split(";");
            if (partes.length == 4) {
                String tipoDoc = partes[0];
                long numDoc = Long.parseLong(partes[1]);
                String nombres = partes[2];
                String apellidos = partes[3];
                vendedores.add(new vendedor(tipoDoc, numDoc, nombres, apellidos));
            } else {
                System.err.println(" Línea inválida en archivo de vendedores: " + linea);
            }
        }

        return vendedores;
    }

    /**
     * Carga la información de los productos desde un archivo de texto plano.
     * Se reportarán errores para líneas con formato incorrecto o precios negativos.
     *
     * @param ruta
     * @return Un mapa donde la clave es el ID del producto
     */
    public static Map<String, Producto> cargarProductos(String ruta) throws IOException {
        Map<String, Producto> productos = new HashMap<>();
        List<String> lineas = Files.readAllLines(Paths.get(ruta));

        for (String linea : lineas) {
            String[] partes = linea.split(";");
            if (partes.length == 3) {
                String id = partes[0];
                String nombre = partes[1];
                double precio = Double.parseDouble(partes[2]);
                if (precio >= 0) {
                    productos.put(id, new Producto(id, nombre, precio));
                } else {
                    System.err.println("⚠️ Precio negativo para el producto: " + id);
                }
            } else {
                System.err.println("⚠️ Línea inválida en archivo de productos: " + linea);
            }
        }

        return productos;
    }

    /**
     * Carga la información de las ventas desde múltiples archivos de texto plano
     * ubicados en una carpeta específica.
     * Las siguientes líneas de cada archivo representan las ventas individuales,
     * con el ID del producto y la cantidad vendida
     *
     * @param carpeta    
     * @param vendedores 
     */
    public static Map<vendedor, List<ventas>> cargarVentas(String carpeta, List<vendedor> vendedores) throws IOException {
        Map<vendedor, List<ventas>> ventasPorVendedor = new HashMap<>();

        Files.list(Paths.get(carpeta)).filter(Files::isRegularFile).forEach(path -> {
            try {
                List<String> lineas = Files.readAllLines(path);
                if (lineas.size() < 2) return;

                String[] encabezado = lineas.get(0).split(";");
                if (encabezado.length != 2) return;

                String tipoDoc = encabezado[0];
                long numDoc = Long.parseLong(encabezado[1]);

                // Buscar el vendedor correspondiente
                vendedor vendedor = vendedores.stream()
                        .filter(v -> v.getTipoDocumento().equals(tipoDoc) && v.getNumeroDocumento() == numDoc)
                        .findFirst()
                        .orElse(null);

                if (vendedor == null) {
                    System.err.println(" Vendedor no encontrado para archivo: " + path.getFileName());
                    return;
                }

                List<ventas> ventas = new ArrayList<>();
                for (int i = 1; i < lineas.size(); i++) {
                    String[] partes = lineas.get(i).split(";");
                    if (partes.length != 2) continue;
                    String idProducto = partes[0];
                    int cantidad = Integer.parseInt(partes[1]);
                    if (cantidad > 0) {
                        ventas.add(new ventas(idProducto, cantidad));
                    } else {
                        System.err.println(" Cantidad negativa en archivo: " + path.getFileName());
                    }
                }

                ventasPorVendedor.put(vendedor, ventas);
            } catch (Exception e) {
                System.err.println(" Error leyendo archivo de ventas: " + path.getFileName());
            }
        });

        return ventasPorVendedor;
    }
}