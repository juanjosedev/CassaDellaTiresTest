package test;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import page.InicioPage;
import page.InicioSesionPage;
import page.ServiciosPage;
import utils.Data;

public class ServiciosTest {

	private WebDriver driver;
	private InicioSesionPage isp;
	private InicioPage ip;
	private ServiciosPage sp;
	
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
	public void CP_001_CrearServicioIDNoRegistrada() {
		
		isp = new InicioSesionPage(driver);
		isp.sendForm(Data.userAdmin, Data.passwordAdmin);
		ip = new InicioPage(driver);
		sp = ip.getMenu().goToServiciosPage();
		sp.pressNuevoServicioModalButton();
		sp.enterNombreServicio("NuevoServicio");
		sp.pressTipoVehiculoCheckbox(By.cssSelector("input[value=\"12\"]"));
		sp.enterPrecioServicioTipoVehiculo("50000", By.xpath("//*[@id=\"inp_precio\"]"));
		sp.pressCrearServicioButton();
		
		String expected = "Se ha registrado un nuevo servicio con éxito";
		String actual = sp.getAlertMessage(By.cssSelector(".alert-success var"));
		
		assertEquals(expected, actual);
		
		
	}
	
	@Test
	public void CP_002_CrearServicioIDRegistrada() {
		fail("Áún no implementado");
	}
	
	@Test
	public void CP_003_ObligatoriedadDelID() {
		
		isp = new InicioSesionPage(driver);
		isp.sendForm(Data.userAdmin, Data.passwordAdmin);
		ip = new InicioPage(driver);
		sp = ip.getMenu().goToServiciosPage();
		sp.pressNuevoServicioModalButton();
		sp.pressCrearServicioButton();
		
		String expected = "El campo nombre es obligatorio";
		String actual = sp.getAlertMessage(By.id("alt_servicio"));
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void CP_004_ObligatoriedadDelTipoDeVehiculo() {
		isp = new InicioSesionPage(driver);
		isp.sendForm(Data.userAdmin, Data.passwordAdmin);
		ip = new InicioPage(driver);
		sp = ip.getMenu().goToServiciosPage();
		sp.pressNuevoServicioModalButton();
		sp.enterNombreServicio("nombreServicio");
		sp.pressCrearServicioButton();
		
		String expected = "Tienes que escoger un servicio";
		String actual = sp.getAlertMessage(By.id("alt_crear_error"));
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void CP_005_ModificarCamposObligatorios() {
		fail("Áún no implementado");
	}
	
	@Test
	public void CP_006_ModificarCamposUnicosTipoDeVehiculos() {
		fail("Áún no implementado");
	}
	
	@Test
	public void CP_007_VerificarTipoDeDatoCampoPrecio() {
		isp = new InicioSesionPage(driver);
		isp.sendForm(Data.userAdmin, Data.passwordAdmin);
		ip = new InicioPage(driver);
		sp = ip.getMenu().goToServiciosPage();
		sp.pressNuevoServicioModalButton();
		sp.enterNombreServicio("nombreServicio");
		sp.pressTipoVehiculoCheckbox(By.cssSelector("input[value=\"12\"]"));
		sp.enterPrecioServicioTipoVehiculo("letras", By.className("inp_precio"));
		
		String expected = "Precio no válido";
		String actual = sp.getAlertMessage(By.id("alt_crear_error"));
		
		assertEquals(expected, actual);
		
	}
	
}
