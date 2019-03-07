package test;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import page.ClientesPage;
import page.InicioPage;
import page.InicioSesionPage;
import utils.Data;

public class ClientesTest {

	private WebDriver driver;
	private InicioSesionPage isp;
	private InicioPage ip;
	private ClientesPage cp;
	
	@Before
	public void setUp() {
		
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
	public void CP_001_CrearClienteCedulaNoRegistrada() {
		
		isp = new InicioSesionPage(driver);
		isp.sendForm(Data.userAdmin, Data.passwordAdmin);
		ip = new InicioPage(driver);
		cp = ip.getMenu().goToClientes();
		cp.pressNuevoClienteModalButton();
		cp.enterCedula("1012");
		cp.enterNombre("Mariano");
		cp.enterPrimerApellido("Delgado");
		cp.enterSegundoApellido("");
		cp.enterTelefeno("5168512");
		cp.enterDireccion("Andalucía");
		cp.pressAgregarButton();
		
		String expected = "El cliente se registró correctamente";
		String actual = cp.getAlertMessage(By.id("alert_agregar_exitoso"));
		
		assertTrue(actual.contains(expected));
		
	}
	
	@Test
	public void CP_002_CrearClienteCedulaRegistrada() {
		
		isp = new InicioSesionPage(driver);
		isp.sendForm(Data.userAdmin, Data.passwordAdmin);
		ip = new InicioPage(driver);
		cp = ip.getMenu().goToClientes();
		cp.pressNuevoClienteModalButton();
		cp.enterCedula("1321");
		cp.enterNombre("Alicia");
		cp.enterPrimerApellido("Sanz");
		cp.enterSegundoApellido("");
		cp.enterTelefeno("");
		cp.enterDireccion("");
		cp.pressAgregarButton();
		
		String expected = "Cédula ya registrada";
		String actual = cp.getAlertMessage(By.id("alert_agregar_error"));
		
		assertTrue(actual.contains(expected));
		
	}

	@Test
	public void CP_003_ObligatoriedadCedula() {
		
		isp = new InicioSesionPage(driver);
		isp.sendForm(Data.userAdmin, Data.passwordAdmin);
		ip = new InicioPage(driver);
		cp = ip.getMenu().goToClientes();
		cp.pressNuevoClienteModalButton();
		cp.pressAgregarButton();
		
		String expected = "Rellena el campo cédula";
		String actual = cp.getAlertMessage(By.id("alert_validacion_cc"));
		
		assertTrue(actual.contains(expected));
		
	}
	
	@Test
	public void CP_004_ObligatoriedadNombre() {
		
		isp = new InicioSesionPage(driver);
		isp.sendForm(Data.userAdmin, Data.passwordAdmin);
		ip = new InicioPage(driver);
		cp = ip.getMenu().goToClientes();
		cp.pressNuevoClienteModalButton();
		cp.enterCedula("1311");
		cp.pressAgregarButton();
		
		String expected = "Rellena el campo nombre";
		String actual = cp.getAlertMessage(By.id("alert_agregar_nombre"));
		
		assertTrue(actual.contains(expected));
		
	}
	
	@Test
	public void CP_005_ObligatoriedadPrimerApellido() {
		
		isp = new InicioSesionPage(driver);
		isp.sendForm(Data.userAdmin, Data.passwordAdmin);
		ip = new InicioPage(driver);
		cp = ip.getMenu().goToClientes();
		cp.pressNuevoClienteModalButton();
		cp.enterCedula("1012");
		cp.enterNombre("Mariano");
		cp.pressAgregarButton();
		
		String expected = "Rellena el campo primer apellido";
		String actual = cp.getAlertMessage(By.id("alert_agregar_apellido"));
		
		assertTrue(actual.contains(expected));
		
	}
	
	@Test
	public void CP_006_VerificarTipoDatoTelefono() {
		fail("Aún no implementado");
	}
	
	@Test
	public void CP_007_ModificarCampoUnicosCedula() {
		
		isp = new InicioSesionPage(driver);
		isp.sendForm(Data.userAdmin, Data.passwordAdmin);
		ip = new InicioPage(driver);
		cp = ip.getMenu().goToClientes();
		cp.pressButton(By.cssSelector("a[href=\"Clientes.jsp?user=1321\"]"));
		cp.pressButton(By.cssSelector("a[href=\"#modificarCliente1321\"]"));
		cp.sendKeys(By.id("cc"), "1322");
		
		int expected = 1321;
		int actual = Integer.parseInt(cp.getInputValue(By.id("cc")));
		
		assertEquals(expected, actual);
		
	}
	
}
