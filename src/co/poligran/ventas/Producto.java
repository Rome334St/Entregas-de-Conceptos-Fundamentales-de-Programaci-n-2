package co.poligran.ventas;
/**
 * @ROME
 */
public class Producto {
	private String id;
	private String nombre;
	private double precioporunidad;

	public Producto(String id, String nombre, double precioporunidad) {
		this.id=id;
		this.nombre=nombre;
		this.precioporunidad=precioporunidad;
		
	}
	public String getid(){
		return id;
	}
	public String getnombre() {
		return nombre;
	}
	public double getprecioporunidad(){
		return precioporunidad;
	}
	
	public String toString() {
		return id+";"+nombre+";"+precioporunidad;
	}
	
}
