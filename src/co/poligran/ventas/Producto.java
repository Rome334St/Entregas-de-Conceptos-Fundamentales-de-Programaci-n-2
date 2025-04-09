package co.poligran.ventas;

/**
 * Esta clase representa un producto con su identificador único, nombre
 * y precio por unidad. Se utiliza para almacenar la información de cada
 * producto disponible en el sistema.
 *
 * @author Rome
 */
public class Producto {
    private String id;
    private String nombre;
    private double precioPorUnidad;

    /**
     * Constructor para crear un nuevo objeto Producto.
     *
     * @param id              
     * @param nombre          
     * @param precioPorUnidad 
     */
    public Producto(String id, String nombre, double precioPorUnidad) {
        this.id = id;
        this.nombre = nombre;
        this.precioPorUnidad = precioPorUnidad;
    }

    /**
     * Obtiene el id del producto.
     *
     * @return El ID del producto.
     */
    public String getId() {
        return id;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return El nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el precio por unidad del producto.
     *
     * @return El precio por unidad del producto.
     */
    public double getPrecioPorUnidad() {
        return precioPorUnidad;
    }

    /**
     * 
     *
     * @return El precio por unidad del producto
     */
    public double getPrecio() {
        return getPrecioPorUnidad();
    }

    /**
     * Devuelve una cadena del objeto Producto..
     *
     * @return Una cadena que representa este producto.
     *
     */

    public String toString() {
        return id + ";" + nombre + ";" + precioPorUnidad;
    }
}