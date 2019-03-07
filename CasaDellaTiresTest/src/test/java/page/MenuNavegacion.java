package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Data;

public class MenuNavegacion {

	private WebDriver driver;
	private By inicioButtonNav;
	private By liquidacionesButtonNav;
	private By clientesButtonNav;
	private By vehiculosButtonNav;
	private By serviciosButtonNav;
	private By operadoresButtonNav;
//	private By cuentaButtonNav;
	private By cerrarSesionButtonNav;
	protected WebDriverWait wait;
	
	public MenuNavegacion(WebDriver driver) {
		this.driver = driver;
		inicioButtonNav = By.cssSelector("a[href=\"Inicio.jsp\"]");
		liquidacionesButtonNav = By.cssSelector("a[href=\"Liquidaciones.jsp\"]");
		clientesButtonNav = By.cssSelector("a[href=\"Clientes.jsp\"]");
		vehiculosButtonNav = By.cssSelector("a[href=\"Vehiculos.jsp\"]");
		serviciosButtonNav = By.cssSelector("a[href=\"Servicios2.jsp\"]");
		operadoresButtonNav = By.cssSelector("a[href=\"Operadores.jsp\"]");
//		cuentaButtonNav = By.cssSelector("a[href=\"#\"]");
		cerrarSesionButtonNav = By.cssSelector("a[href=\"../../index.jsp\"]");
		wait = new WebDriverWait(driver, 5);
	}
	
	public InicioPage goToInicio() {
		driver.findElement(inicioButtonNav).click();
		return new InicioPage(driver);
	}
	
	public LiquidacionesPage goToLiquidaciones() {
		driver.findElement(liquidacionesButtonNav).click();
		return new LiquidacionesPage(driver);
	}
	
	public ClientesPage goToClientes() {
		driver.findElement(clientesButtonNav).click();
		return new ClientesPage(driver);
	}
	
	public VehiculosPage goToVehiculosPage() {
		driver.findElement(vehiculosButtonNav).click();
		return new VehiculosPage(driver);
	}
	
	public ServiciosPage goToServiciosPage() {
		driver.findElement(serviciosButtonNav).click();
		return new ServiciosPage(driver);
	}
	
	public OperadoresPage goToOperadoresPage() {
		driver.findElement(operadoresButtonNav).click();
		return new OperadoresPage(driver);
	}
	
	public void cerrarSesion() {
		wait.until(ExpectedConditions.elementToBeClickable(cerrarSesionButtonNav)).click();
	}
	
}
