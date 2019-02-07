package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VehiculosPage extends BasePage{

	private By nuevoVehiculoModalButton;
	private By placa1Field;
	
	public VehiculosPage(WebDriver driver) {
		super(driver);
		nuevoVehiculoModalButton = By.cssSelector("a[href=\"#agregarVehiculo\"]");
		placa1Field = By.name("placa1");
	}
	
}
