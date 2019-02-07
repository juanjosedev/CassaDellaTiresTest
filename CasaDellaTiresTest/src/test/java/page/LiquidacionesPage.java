package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LiquidacionesPage extends BasePage{

	private By nuevaLiquidacionModalButton;
	private By cedulaField;
	private By placaField;
	private By crearLiquidacionButton;
	
	public LiquidacionesPage(WebDriver driver) {
		
		super(driver);
		nuevaLiquidacionModalButton = By.cssSelector("a[href=\"#agregarLiquidacion\"]");
		cedulaField = By.name("cc");
		placaField = By.name("placa");
		crearLiquidacionButton = By.id("sbt_new_lqd");
		
	}
	
	public void enterCedula(int cedula) {
		getDriver().findElement(cedulaField).sendKeys(Integer.toString(cedula));
	}
	
	public void enterPlaca(String placa) {
		getDriver().findElement(placaField).sendKeys(placa);
	}
	
	public void pressServicioCheckbox(By selector) {
		getDriver().findElement(selector).click();
	}
	
	public void pressNuevaLiquidacionModalButton() {
		getDriver().findElement(nuevaLiquidacionModalButton).click();
	}
	
	public void pressCrearLiquidacionButton() {
		getDriver().findElement(crearLiquidacionButton).click();
	}
	
}
