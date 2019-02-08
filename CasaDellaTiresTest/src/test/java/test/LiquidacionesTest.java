package test;

import static org.junit.Assert.*;

import java.util.Calendar;
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
		lp.enterCedula(Data.cedula);
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
		lp.enterPlaca(Data.placa);
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
		lp.enterPlaca("abc123");
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
		
		isp = new InicioSesionPage(driver);
		isp.sendForm(Data.userAdmin, Data.passwordAdmin);
		ip = new InicioPage(driver);
		lp = ip.getMenu().goToLiquidaciones();
		lp.pressNuevaLiquidacionModalButton();
		lp.enterPlaca(Data.placa);
		lp.enterCedula(Data.cedula);
		lp.pressServicioCheckbox(By.cssSelector("input[value=\"101\"]"));
		lp.pressCrearLiquidacionButton();
		
		Calendar calendar = Calendar.getInstance();
		
		int hourExpected = calendar.get(Calendar.HOUR_OF_DAY);
		int hourActual = Integer.parseInt(lp.getText(By.id("info_hora_actual")).substring(0, 2));
		
		assertEquals(hourExpected, hourActual);
		
		float minuteExpected = calendar.get(Calendar.MINUTE);
		float minuteActual = Float.parseFloat(lp.getText(By.id("info_hora_actual")).substring(3, 5));
		
		assertEquals(minuteExpected, minuteActual, 1);
		
	}
	
	@Test
	public void CP_007_VerificarHoraFinal() {
		fail("Áún no implementado");
	}
	
	@Test
	public void CP_008_VerificarSubtotal() {
		
		isp = new InicioSesionPage(driver);
		isp.sendForm(Data.userAdmin, Data.passwordAdmin);
		ip = new InicioPage(driver);
		lp = ip.getMenu().goToLiquidaciones();
		lp.pressNuevaLiquidacionModalButton();
		lp.enterPlaca(Data.placa);
		lp.enterCedula(Data.cedula);
		lp.pressServicioCheckbox(By.cssSelector("input[value=\"101\"]"));
		
		int expected = 22000;
		int actual = Integer.parseInt(lp.getText(By.id("valor_subtotal")));
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void CP_009_VerificarAplicacionDelDescuento() {
		
		isp = new InicioSesionPage(driver);
		isp.sendForm(Data.userAdmin, Data.passwordAdmin);
		ip = new InicioPage(driver);
		lp = ip.getMenu().goToLiquidaciones();
		lp.pressNuevaLiquidacionModalButton();
		lp.enterPlaca(Data.placa);
		lp.enterCedula(Data.cedula);
		lp.pressServicioCheckbox(By.cssSelector("input[value=\"101\"]"));
		lp.pressServicioCheckbox(By.cssSelector("input[value=\"107\"]"));
		lp.pressServicioCheckbox(By.cssSelector("input[value=\"111\"]"));
		lp.pressServicioCheckbox(By.cssSelector("input[value=\"117\"]"));
		
		int expected = 22000 + 20000 + 38000;
		int actual = Integer.parseInt(lp.getText(By.id("valor_descuento")));
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void CP_010_VerificarTotal() {
		
		isp = new InicioSesionPage(driver);
		isp.sendForm(Data.userAdmin, Data.passwordAdmin);
		ip = new InicioPage(driver);
		lp = ip.getMenu().goToLiquidaciones();
		lp.pressNuevaLiquidacionModalButton();
		lp.enterPlaca(Data.placa);
		lp.enterCedula(Data.cedula);
		lp.pressServicioCheckbox(By.cssSelector("input[value=\"101\"]"));
		lp.pressServicioCheckbox(By.cssSelector("input[value=\"111\"]"));
		
		int expected = 22000 + 20000;
		int actual = Integer.parseInt(lp.getText(By.id("valor_total")));
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void CP_011_VerificarCamposRestringidos() {
		fail("Áún no implementado");
	}
}
