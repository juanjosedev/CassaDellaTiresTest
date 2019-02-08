package test;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import page.InicioPage;
import page.InicioSesionPage;
import page.VehiculosPage;
import utils.Data;

public class vehiculosTest {
	private WebDriver driver;
	private InicioSesionPage isp;
	private InicioPage ip;
	private VehiculosPage vp;

	@Before
	public void setUp() throws Exception{
		
		System.setProperty(Data.DriverKey, Data.DriverKey);
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
	public void CP_001_crearVehiculoPlacaNoRegistrada() {

		isp = new InicioSesionPage(driver);
		isp.sendForm(Data.userAdmin, Data.passwordAdmin);
		ip = new InicioPage(driver);
		vp = ip.getMenu().goToVehiculosPage();
		vp.pressnVehiculoModal();
		vp.enterPlacaUno("inf");
		vp.enterPlacaDos(456);
		vp.enterTipo(1);
		vp.pressnCrearVehiculoButtom();

		String expected = " Se agregó el vehículo correctamente";
		String actual = vp.getAlertMessage(By.id("art_exito"));

		assertEquals(expected, actual);

	}

	public void CP_002_crearVehiculoPlacaResgistrada() {
		isp = new InicioSesionPage(driver);
		isp.sendForm(Data.userAdmin, Data.passwordAdmin);
		ip = new InicioPage(driver);
		vp = ip.getMenu().goToVehiculosPage();
		vp.pressnVehiculoModal();
		vp.enterPlacaUno("asd");
		vp.enterPlacaDos(123);
		vp.enterTipo(4);
		vp.pressnCrearVehiculoButtom();

		String expected = "  Placa ya registrada";
		String actual = vp.getAlertMessage(By.id("art_error"));

		assertEquals(expected, actual);
	}

	public void CP_003_campoPlacaObligatorio() {
		isp = new InicioSesionPage(driver);
		isp.sendForm(Data.userAdmin, Data.passwordAdmin);
		ip = new InicioPage(driver);
		vp = ip.getMenu().goToVehiculosPage();
		vp.pressnVehiculoModal();
		vp.enterTipo(3);
		vp.pressnCrearVehiculoButtom();

		String expected = "Rellena este campo";
		String actual = vp.getAlertMessage(By.id("art_vacio_pl1"));

		assertEquals(expected, actual);

	}

	public void CP_004_campoTipoObligatorio() {
		isp = new InicioSesionPage(driver);
		isp.sendForm(Data.userAdmin, Data.passwordAdmin);
		ip = new InicioPage(driver);
		vp = ip.getMenu().goToVehiculosPage();
		vp.pressnVehiculoModal();
		vp.enterPlacaUno("asd");
		vp.enterPlacaDos(123);
		vp.pressnCrearVehiculoButtom();

		String expected = "Rellena este campo";
		String actual = vp.getAlertMessage(By.id("art_vacio_pl1"));

		assertEquals(expected, actual);

	}


}
