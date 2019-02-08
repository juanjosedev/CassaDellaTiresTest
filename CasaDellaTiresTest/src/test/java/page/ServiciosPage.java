package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ServiciosPage extends BasePage{

	private By nuevoServicioModalButton;
	private By nombreServicioField;
	private By crearServicioButton;
	
	public ServiciosPage(WebDriver driver) {
		super(driver);
		nuevoServicioModalButton = By.cssSelector("a[href=\"#nuevoServicio\"]");
		nombreServicioField = By.name("inp_servicio");
		crearServicioButton = By.id("sbt_crear_servicio");
	}
	
	public void enterNombreServicio(String nombreServicio) {
		getDriver().findElement(nombreServicioField).sendKeys(nombreServicio);
	}
	
	public void enterPrecioServicioTipoVehiculo(String precio, By selector) {
		getDriver().findElement(selector).sendKeys(precio);
	}
	
	public void pressTipoVehiculoCheckbox(By selector) {
		getDriver().findElement(selector).click();
	}
	
	public void pressNuevoServicioModalButton() {
		getDriver().findElement(nuevoServicioModalButton).click();
	}
	
	public void pressCrearServicioButton() {
		getDriver().findElement(crearServicioButton).click();
	}
	
}
