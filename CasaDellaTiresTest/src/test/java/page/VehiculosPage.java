package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class VehiculosPage extends BasePage{
	
	private By nuevoVehiculoModalButtom;
	private By PlacaUnoField;
	private By PlacaDosField;
	private By TipoSelect;
	private By marcaSelect;
	private By modeloField;
	private By crearVehiculoButtom;
	private By buscarvehiculoField;
	private By buscarVehiculoButtom;
	

	public VehiculosPage(WebDriver driver) {
		super(driver);
		nuevoVehiculoModalButtom = By.cssSelector("a[href=\"#agregarVehiculo]\"");
		PlacaUnoField = By.name("placa1");
		PlacaUnoField = By.name("placa2");
		TipoSelect = By.name("tipo");
		marcaSelect = By.name("marca");
		modeloField = By.name("modelo");
		crearVehiculoButtom = By.name("sbt_agregar");
		buscarvehiculoField = By.name("input_vehiculo_cc");
		buscarVehiculoButtom = By.id("buscar_vehiculo");
		
	}
	public void pressnVehiculoModal() {
		getDriver().findElement(nuevoVehiculoModalButtom).click();
		
	}
	public void enterPlacaUno(String placa1) {
		getDriver().findElement(PlacaUnoField).sendKeys(placa1);
	}
	public void enterPlacaDos(int placa2) {
		getDriver().findElement(PlacaDosField).sendKeys(Integer.toString(placa2));
	}
	public void enterTipo(int tipo) {
		Select sltipo = new Select(getDriver().findElement(TipoSelect));
		sltipo.selectByIndex(tipo);
		
	}
	public void enterMarca(int marca) {
		Select slmarca = new Select(getDriver().findElement(marcaSelect));
		slmarca.selectByIndex(marca);
	}
	public void enterModelo(String modelo) {
		getDriver().findElement(PlacaDosField).sendKeys(modelo);
	}
	public void pressnCrearVehiculoButtom() {
		getDriver().findElement(crearVehiculoButtom).click();
		
	}
	public void enterBuscarVehiculo(String buscav) {
		getDriver().findElement(buscarvehiculoField).sendKeys(buscav);
	}
	
	public void pressBuscarVehiculoButtom() {
		getDriver().findElement(buscarVehiculoButtom).click();
		
	}
}
