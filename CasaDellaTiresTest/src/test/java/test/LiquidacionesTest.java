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
		
		assertTrue(actual.contains(expected));
		
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
		
		assertTrue(actual.contains(expected));
		
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
		lp.pressServicioCheckbox(By.cssSelector("input[value=\"132\"]"));
		lp.pressCrearLiquidacionButton();
		
		Calendar calendar = Calendar.getInstance();
		
		String hourStr = lp.getText(By.id("info_hora_actual"));
		
		int hourExpected = calendar.get(Calendar.HOUR_OF_DAY);
		int hourActual = Integer.parseInt(hourStr.substring(0, 2));
		
		assertEquals(hourExpected, hourActual);
		
		float minuteExpected = calendar.get(Calendar.MINUTE);
		float minuteActual = Float.parseFloat(hourStr.substring(3, hourStr.length()));
		
		assertEquals(minuteExpected, minuteActual, 1);
		
	}
	
	@Test
	public void CP_007_VerificarHoraFinal() {
		
		isp = new InicioSesionPage(driver);
		isp.sendForm(Data.userAdmin, Data.passwordAdmin);
		ip = new InicioPage(driver);
		lp = ip.getMenu().goToLiquidaciones();
		lp.pressButton(By.cssSelector("a[href=\"#detalle5\"]"));
		lp.pressButton(By.className("btn_terminar"));
		
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		lp.pressButton(By.cssSelector("a[href=\"#detalle5\"]"));
		
		Calendar calendar = Calendar.getInstance();
		
		String date_str = lp.getText(By.xpath("//*[@id=\"detalle5\"]/div/div/div[2]/div[3]/div[1]/div/div[2]/ul/li[3]/i"));
		
		String hour_str = date_str.substring(date_str.length()-5, date_str.length());
		
		float actualHour = Float.parseFloat(hour_str.substring(0, 2));
		float expectedHour = calendar.get(Calendar.HOUR_OF_DAY);

		assertEquals(expectedHour, actualHour, 1);
		
		float actualMinutes = Float.parseFloat(hour_str.substring(3, 5));
		float expectedMinutes = calendar.get(Calendar.MINUTE);
		
		assertEquals(expectedMinutes, actualMinutes, 1);
		
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
		lp.pressServicioCheckbox(By.cssSelector("input[value=\"132\"]"));
		
		int expected = 30;
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
		lp.pressServicioCheckbox(By.cssSelector("input[value=\"132\"]"));
		lp.pressServicioCheckbox(By.cssSelector("input[value=\"136\"]"));
		lp.pressServicioCheckbox(By.cssSelector("input[value=\"151\"]"));
		lp.pressServicioCheckbox(By.cssSelector("input[value=\"152\"]"));
		
		int expected = 15;
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
		lp.pressServicioCheckbox(By.cssSelector("input[value=\"132\"]"));
		lp.pressServicioCheckbox(By.cssSelector("input[value=\"136\"]"));
		
		int expected = 30 + 15;
		int actual = Integer.parseInt(lp.getText(By.id("valor_total")));
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void CP_011_VerificarCamposRestringidos() {
		
		isp = new InicioSesionPage(driver);
		isp.sendForm(Data.userAdmin, Data.passwordAdmin);
		ip = new InicioPage(driver);
		lp = ip.getMenu().goToLiquidaciones();
		lp.pressNuevaLiquidacionModalButton();
		lp.enterPlaca(Data.placa);
		lp.enterCedula(Data.cedula);
		lp.sendKeys(By.id("inp_precio"), "35");
		
		int expected = 35;
		int actual = Integer.parseInt(lp.getInputValue(By.id("inp_precio")));
		
		assertNotEquals(expected, actual);
		
	}
}
