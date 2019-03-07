package utils;

public abstract class Data {
	
	public static final String DriverKey = "webdriver.chrome.driver";
	public static final String DriverURL = "drivers/chromedriver.exe";
	public static final String URL = "http://localhost:8080/CasaDellaTires/";
	public static final String userAdmin = "admin";
	public static final String passwordAdmin = "admin123";
	public static final String userOperador = "josedev";
	public static final String passwordOperador = "12345678";
	public static final int cedula = 1311;
	public static final String placa = "QWE789";
	
	public static void waiting() {
		
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void waiting(int milliseconds) {
		
		try {
			Thread.sleep(milliseconds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
