package co.poligran.ventas;

import co.poligran.act.GestiondeArchivos;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Clase principal para cargar y procesar la información
 * de vendedores, productos y ventas.
 * Calcula el total vendido por cada vendedor y genera
 * archivos de reporte de ventas por vendedor y productos vendidos.
 *
 * @author Rome
 */
public class Procesador {

    private static final String REPORTE_VENTAS_VENDEDORES = "reporte_ventas_vendedores.csv";
    private static final String REPORTE_PRODUCTOS_VENDIDOS = "reporte_productos_vendidos.csv";
    private static final String CARPETA_DATOS = "datos/"; // Constante para la carpeta de datos

    /**
     * Método principal que se ejecuta al iniciar la clase.
     * Carga la información de vendedores, productos y ventas,
     * calcula el total vendido por cada vendedor y genera los
     * archivos de reporte solicitados.
     *
     * @param args 
     */
    public static void main(String[] args) {
        try {
           
            String rutaVendedores = CARPETA_DATOS + "Vendedores.txt";
            String rutaProductos = CARPETA_DATOS + "Productos.txt";
            String carpetaVentas = CARPETA_DATOS; // Procesar todos los archivos en la carpeta 'datos'

            
            List<vendedor> vendedores = GestiondeArchivos.cargarVendedores(rutaVendedores);
            Map<String, Producto> productos = GestiondeArchivos.cargarProductos(rutaProductos);
            Map<vendedor, List<ventas>> ventasPorVendedor = GestiondeArchivos.cargarVentas(carpetaVentas, vendedores);

          
            Map<vendedor, Double> totalPorVendedor = calcularTotalPorVendedor(ventasPorVendedor, productos);

           
            generarReporteVentasVendedores(totalPorVendedor, CARPETA_DATOS + REPORTE_VENTAS_VENDEDORES);

          
            generarReporteProductosVendidos(ventasPorVendedor, productos, CARPETA_DATOS + REPORTE_PRODUCTOS_VENDIDOS);

            System.out.println("\n✅ Procesamiento completado. Reportes generados en la carpeta '" + CARPETA_DATOS + "'.");

        } catch (IOException e) {
            System.err.println("❌ Error al procesar los archivos: " + e.getMessage());
        }
    }

    /**
     * Calcula el total vendido por cada vendedor a partir del mapa de ventas
     * y el mapa de productos.
     *
     * @param ventasPorVendedor 
     * @param productos        
  
     */
    public static Map<vendedor, Double> calcularTotalPorVendedor(
            Map<vendedor, List<ventas>> ventasPorVendedor,
            Map<String, Producto> productos) {
        Map<vendedor, Double> totales = new HashMap<>();
        for (Map.Entry<vendedor, List<ventas>> entry : ventasPorVendedor.entrySet()) {
            vendedor vendedor = entry.getKey();
            List<ventas> listaVentas = entry.getValue();

            double total = 0.0;

            for (ventas venta : listaVentas) {
                Producto producto = productos.get(venta.getIdProducto());
                if (producto != null) {
                    total += venta.getCantidad() * producto.getPrecioPorUnidad();
                } else {
                    System.err.println(" Producto no encontrado: " + venta.getIdProducto());
                }
            }
            totales.put(vendedor, total);
        }
        return totales;
    }

    /**
     * Genera un archivo CSV con la información de todos los vendedores, ordenado
     * por la cantidad de dinero que recaudaron .
     *
     * @param totalPorVendedor 
     * @param rutaArchivo      
     */
    public static void generarReporteVentasVendedores(Map<vendedor, Double> totalPorVendedor, String rutaArchivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaArchivo))) {
            // Ordenar el mapa por el valor (total de ventas) de forma descendente
            List<Map.Entry<vendedor, Double>> listaOrdenada = totalPorVendedor.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .collect(Collectors.toList());

            // Escribir cada entrada en el archivo CSV
            for (Map.Entry<vendedor, Double> entry : listaOrdenada) {
                vendedor vendedor = entry.getKey();
                double totalVentas = entry.getValue();
                writer.println(vendedor.getNombreCompleto() + ";" + String.format("%.2f", totalVentas));
            }
            System.out.println(" Reporte de ventas por vendedor generado: " + rutaArchivo);
        } catch (IOException e) {
            System.err.println(" Error al generar el reporte de ventas por vendedor: " + e.getMessage());
        }
    }

    /**
     * Genera un archivo CSV con la información de los productos vendidos, ordenado
     * por la cantidad total vendida de forma descendente.
     *
     * @param ventasPorVendedor 
     * @param productos         
     * @param rutaArchivo      
     */
    public static void generarReporteProductosVendidos(Map<vendedor, List<ventas>> ventasPorVendedor, Map<String, Producto> productos, String rutaArchivo) {
        Map<String, Integer> cantidadTotalVendidaPorProducto = new HashMap<>();

        // Calcular la cantidad total vendida de cada producto
        for (List<ventas> ventasList : ventasPorVendedor.values()) {
            for (ventas venta : ventasList) {
                cantidadTotalVendidaPorProducto.merge(venta.getIdProducto(), venta.getCantidad(), Integer::sum);
            }
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaArchivo))) {
            // Ordenar los productos por la cantidad total vendida de forma descendente
            List<Map.Entry<String, Integer>> listaOrdenada = cantidadTotalVendidaPorProducto.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .collect(Collectors.toList());

            // Escribir cada producto en el archivo CSV
            for (Map.Entry<String, Integer> entry : listaOrdenada) {
                String idProducto = entry.getKey();
                int cantidadVendida = entry.getValue();
                Producto producto = productos.get(idProducto);
                if (producto != null) {
                    writer.println(producto.getNombre() + ";" + String.format("%.2f", producto.getPrecioPorUnidad()) + ";" + cantidadVendida);
                } else {
                    System.err.println(" Producto no encontrado al generar el reporte: " + idProducto);
                }
            }
            System.out.println(" Reporte de productos vendidos generado: " + rutaArchivo);
        } catch (IOException e) {
            System.err.println(" Error al generar el reporte de productos vendidos: " + e.getMessage());
        }
    }
}