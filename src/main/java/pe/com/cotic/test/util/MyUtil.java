package pe.com.cotic.test.util;

public class MyUtil {
	
	public static String baseurl() {
		String ruta = System.getProperty("ruta_server") != null ? System.getProperty("ruta_server") : "";
		return  ruta + "/ProyectoWeb/"; //"http://localhost:8080/ProyectoWeb/";
	}

	public static String basepathlogin() {
		return "/ProyectoWeb/";
	}
	
	public static String basepath() {
		String ruta = System.getProperty("ruta_server") != null ? System.getProperty("ruta_server") : "";
		return  ruta + "/ProyectoWeb/views/";  //"http://localhost:8080/ProyectoWeb/views/";
	}
}
