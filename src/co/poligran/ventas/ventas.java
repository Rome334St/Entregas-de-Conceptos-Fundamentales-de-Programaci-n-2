package co.poligran.ventas;

/**
 * Esta clase representa una venta individual de un producto realizada por un vendedor.
 * Contiene el ID del producto vendido y la cantidad de unidades vendidas.
 *
 * @author Rome
 */
public class ventas {
    private String idProducto;
    private int cantidad;

    /**
     * Constructor para crear un nuevo objeto ventas.
     *
     * @param id del Producto
     * @param cantidad
     */
    public ventas(String idProducto, int cantidad) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el ID del producto vendido.
     *
     * @return El ID del producto.
     */
    public String getIdProducto() {
        return idProducto;
    }

    /**
     * da el ID del producto vendido.
     *
     * @param id del Producto 
     */
    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * Obtiene la cantidad de unidades vendidas del producto.
     *
     * @return La cantidad vendida.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad de unidades vendidas del producto.
     *
     * @param cantidad 
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Devuelve una representación en cadena del objeto ventas.
     *
     * @return Una cadena que representa esta venta.
     */
    public String toString() {
        return idProducto + ";" + cantidad;
    }
}