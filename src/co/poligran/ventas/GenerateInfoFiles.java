package co.poligran.ventas;
/*
*@Rome
*/
import java.io.PrintWriter;
public class GenerateInfoFiles {
public static void main(String[] args ) {
	System.out.println("Generador de Archivos");
	try {
		createProductsFile(10);
		createSalesManInfoFile(5);
		
		for (int a = 1; a<=5; a++ ){
			String Nombre= "vendedor" + a;
			long id=1000+a;
			createSalesMenFile(20, Nombre, id);
			
			
		}
		System.out.println("Archivos Generados");
	}
	catch (Exception e) {
		System.out.println("Error");
	}
}
public static void createProductsFile(int productsCount) {
	String fileName ="Productos.txt";
	try(PrintWriter writer = new PrintWriter(fileName)){
		for (int b = 1; b<=productsCount;b++) {
			String id ="P"+b;
			String Nombre = "Producto"+b;
			double Precio = Math.round((Math.random() *100+1)*100.0)/100.0;
			writer.println(id+";"+Nombre+";"+Precio);
		}
	System.out.println("Archivo Creado");
		
		}catch(Exception e) {
			System.out.println("Error"+e.getMessage());
	}
}
public static void createSalesManInfoFile(int count) {
	String fileName= "Vendedores.txt";
	try (PrintWriter writer= new PrintWriter(fileName)){
		for(int c=1; c<=count;c++) {
			String TipodeDocumento="CC";
			String NumerodeDocumento="N°"+c;
			String Nombre="Nombre"+c;
			String Apellidos="Apellidos"+c;
			
writer.println(TipodeDocumento + ";" + NumerodeDocumento + ";" + Nombre + ";" + Apellidos  );
		}
		System.out.println("Archivo Vendedor Creado");
	}
	catch(Exception e) {
	System.out.println("Error");
	}
}
public static void createSalesMenFile(int count, String nombre, long id) {
    String fileName=nombre+ "_ventas.txt";
    try(PrintWriter writer= new PrintWriter(fileName)){
    	for(int e=1;e<=count;e++) {
    		String ProductoID="p"+(e%10+1);
    		int cantidad=(int)(Math.random()*e);
    		writer.println(ProductoID+";"+cantidad);
    	}
    	System.out.println("Archivo ventas creado");
    }
    catch(Exception e) {
    	System.out.println("error");
    }
    	
    
}

}
