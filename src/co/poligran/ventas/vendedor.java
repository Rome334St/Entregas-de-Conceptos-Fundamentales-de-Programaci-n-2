package co.poligran.ventas;
/**
 * @ROME
 */
public class vendedor {
private String TipodeDocumento;
private String NumerodeDocumento;
private String Nombre;
private String Apellidos;

public vendedor(String TipodeDocumento, String NumerodeDocumento, String Nombre, String Apellidos ) {
	this.TipodeDocumento = TipodeDocumento;
	this.NumerodeDocumento = NumerodeDocumento;
	this.Nombre = Nombre;
	this.Apellidos = Apellidos;
}
public String getTipodeDocumento(){
	return TipodeDocumento;
}
public String getNumerodeDocumento(){
	return NumerodeDocumento;
}
public String getNombre(){
	return Nombre;

}
public String getApellidos(){
return Apellidos;
}
public String toString() {
	return TipodeDocumento + ";" + NumerodeDocumento + ";" + Nombre + ";" + Apellidos;
}
}
