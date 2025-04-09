package co.poligran.ventas;

/**
 * Representa a un vendedor en el sistema, incluyendo su tipo y número de documento,
 * nombres y apellidos. Esta clase se utiliza para identificar a los vendedores
 * y asociarles sus respectivas ventas.
 *
 * @author Rome
 */
public class vendedor {
    private String tipoDocumento;
    private long numeroDocumento;
    private String nombres;
    private String apellidos;

    /**
     * Constructor para crear un nuevo objeto vendedor.
     *
     * @param tipoDocumento   
     * @param numeroDocumento 
     * @param nombres         
     * @param apellidos      
     */
    public vendedor(String tipoDocumento, long numeroDocumento, String nombres, String apellidos) {
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    /**
     * Obtiene el tipo de documento del vendedor.
     *
     * @return El tipo de documento.
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Obtiene el número de documento del vendedor.
     *
     * @return El número de documento.
     */
    public long getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * Obtiene los nombres del vendedor.
     *
     * @return Los nombres del vendedor.
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Obtiene los apellidos del vendedor.
     *
     * @return Los apellidos del vendedor.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Devuelve el nombre completo del vendedor
     *
     * @return El nombre completo del vendedor.
     */
    public String getNombreCompleto() {
        return nombres + " " + apellidos;
    }

    /**
     * Devuelve una cadena única para identificar al vendedor, combinando el tipo
     * de documento y el número de documento separados por un guion.
     *
     * @return Una cadena de identificación única para el vendedor.
     */
    public String getIdVendedor() {
        return tipoDocumento.toUpperCase() + "-" + numeroDocumento;
    }

    /**
     * Devuelve una representación en cadena del objeto vendedor.
     *
     * @return Una cadena que representa este vendedor.
     */

    public String toString() {
        return tipoDocumento + ";" + numeroDocumento + ";" + nombres + ";" + apellidos;
    }
}    