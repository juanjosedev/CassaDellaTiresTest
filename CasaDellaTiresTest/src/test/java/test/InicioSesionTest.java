package test;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import page.InicioPage;
import page.InicioSesionPage;
import utils.Data;

public class InicioSesionTest {

	private WebDriver driver;
	private InicioSesionPage isp;
	private InicioPage ip;
	
	@Before
	public void setUp() throws Exception {
		
		System.setProperty(Data.DriverKey, Data.DriverURL);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(Data.URL);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	}
	
	@After
	public void tearDown() {
		driver.close();
	}
	
	@Test
	public void CP_001_IngresoUsuarioExistente() {
		
		isp = new InicioSesionPage(driver);
		isp.sendForm("admin", "admin123");
		ip = new InicioPage(driver);
		
		String expected = "Admin";
		String actual = ip.getText(By.xpath("//*[@id=\"tipo_usuario\"]"));
		
		ip.getMenu().cerrarSesion();
		
		assertTrue(actual.contains(expected));
		
	}
	
	@Test
	public void CP_002_IngresoUsuarioNoExistente() {
		
		isp = new InicioSesionPage(driver);
		isp.sendForm("usuarionoexistente", "admin123");
		
		String expected = "usuario o contraseña incorrectas";
		String actual = isp.getAlertMessage(By.id("alert_error")).toLowerCase();
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void CP_003_IngresoContrasenaExistente() {
		
		isp = new InicioSesionPage(driver);
		isp.sendForm("admin", "admin123");
		ip = new InicioPage(driver);
		
		String expected = "Admin";
		String actual = ip.getText(By.xpath("//*[@id=\"tipo_usuario\"]"));
		
		ip.getMenu().cerrarSesion();
		
		assertTrue(actual.contains(expected));
		
	}
	
	@Test
	public void CP_004_IngresoContrasenaIncorrecta() {
		
		isp = new InicioSesionPage(driver);
		isp.sendForm("admin", "contrasenaincorrecta");
	
		String expected = "usuario o contraseña incorrectas";
		String actual = isp.getAlertMessage(By.id("alert_error")).toLowerCase();
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void CP_005_VerificarObligatoriedadCampoUsuario() {
		
		isp = new InicioSesionPage(driver);
		isp.sendForm("", "password");
		
		String expected = "campo obligatorio";
		String actual = isp.getAlertMessage(By.id("alert_obligatorio_usuario")).toLowerCase();
		
		assertTrue(actual.contains(expected));
		
	}
	
	@Test
	public void CP_006_VerificarObligatoriedadCampoContrasena() {
		
		isp = new InicioSesionPage(driver);
		isp.sendForm("username", "");
		
		String expected = "campo obligatorio";
		String actual = isp.getAlertMessage(By.id("alert_obligatorio_clave")).toLowerCase();
		
		assertTrue(actual.contains(expected));
		
	}
	
}
