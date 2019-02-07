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
import page.LiquidacionesPage;
import utils.Data;

public class LiquidacionesTest {

	private WebDriver driver;
	private InicioSesionPage isp;
	private InicioPage ip;
	private LiquidacionesPage lp;
	
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
	public void CP_001_IngresarClienteExistente() {
		
		isp = new InicioSesionPage(driver);
		isp.sendForm(Data.userAdmin, Data.passwordAdmin);
		ip = new InicioPage(driver);
		lp = ip.getMenu().goToLiquidaciones();
		lp.pressNuevaLiquidacionModalButton();
		lp.enterCedula(1321);
		lp.enterPlaca(""); // necesario para que muestre el alerta de la cédula
		
		String expected = "Cédula correcta";
		String actual = lp.getAlertMessage(By.id("art_cc_existe"));
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void CP_002_IngresarClienteNoExistente() {
		
		isp = new InicioSesionPage(driver);
		isp.sendForm(Data.userAdmin, Data.passwordAdmin);
		ip = new InicioPage(driver);
		lp = ip.getMenu().goToLiquidaciones();
		lp.pressNuevaLiquidacionModalButton();
		lp.enterCedula(9999);
		lp.enterPlaca("");
		
		String expected = "La cédula no existe";
		String actual = lp.getAlertMessage(By.id("art_cc_no_existe"));
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void CP_003_IngresarVehiculoExistente() {
		
		isp = new InicioSesionPage(driver);
		isp.sendForm(Data.userAdmin, Data.passwordAdmin);
		ip = new InicioPage(driver);
		lp = ip.getMenu().goToLiquidaciones();
		lp.pressNuevaLiquidacionModalButton();
		lp.enterPlaca("qwe789");
		lp.enterCedula(0);
		
		String expected = "Placa correcta";
		String actual = lp.getAlertMessage(By.id("art_placa_existe"));
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void CP_004_IngresarVehiculoNoExistente() {
		
		isp = new InicioSesionPage(driver);
		isp.sendForm(Data.userAdmin, Data.passwordAdmin);
		ip = new InicioPage(driver);
		lp = ip.getMenu().goToLiquidaciones();
		lp.pressNuevaLiquidacionModalButton();
		lp.enterPlaca("qwe789");
		lp.enterCedula(0);
		
		String expected = "La placa no existe";
		String actual = lp.getAlertMessage(By.id("art_placa_no_existe"));
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void CP_005_VerificarConsecutivo() {
		fail("Áún no implementado");
	}
	
	@Test
	public void CP_006_VerificarHoraInicio() {
		fail("Áún no implementado");
	}
	
	@Test
	public void CP_007_VerificarHoraFinal() {
		fail("Áún no implementado");
	}
	
	@Test
	public void CP_008_VerificarSubtotal() {
		fail("Áún no implementado");
	}
	
	@Test
	public void CP_009_VerificarAplicacionDelDescuento() {
		fail("Áún no implementado");
	}
	
	@Test
	public void CP_010_VerificarTotal() {
		fail("Áún no implementado");
	}
	
	@Test
	public void CP_011_VerificarCamposRestringidos() {
		fail("Áún no implementado");
	}
}
