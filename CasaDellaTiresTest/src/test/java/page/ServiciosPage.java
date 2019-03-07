package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
		wait.until(ExpectedConditions.visibilityOfElementLocated(nombreServicioField)).sendKeys(nombreServicio);
	}
	
	public void enterPrecioServicioTipoVehiculo(String precio, By selector) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(selector)).sendKeys(precio);
	}
	
	public void pressTipoVehiculoCheckbox(By selector) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(selector)).click();
	}
	
	public void pressNuevoServicioModalButton() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(nuevoServicioModalButton)).click();
	}
	
	public void pressCrearServicioButton() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(crearServicioButton)).click();
//		getDriver().findElement(crearServicioButton).click();
	}
	
}
