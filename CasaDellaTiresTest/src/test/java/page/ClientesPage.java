package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ClientesPage extends BasePage{

	private By nuevoClienteModalButton;
	private By cedulaField;
	private By nombreField;
	private By primerApellidoField;
	private By segundoApellidoField;
	private By telefonoField;
	private By direccionField;
	private By agregarButton;
	
	public ClientesPage(WebDriver driver) {
		super(driver);
		nuevoClienteModalButton = By.cssSelector("a[href=\"#agregarCliente\"]");
		cedulaField = By.name("cc_agregar");
		nombreField = By.name("nombre");
		primerApellidoField = By.name("primer_apellido");
		segundoApellidoField = By.name("segundo_apellido");
		telefonoField = By.name("telefono");
		direccionField = By.name("direccion");
		agregarButton = By.id("submit_nuevo_cliente");
		
	}
	
	public void pressNuevoClienteModalButton() {
		getDriver().findElement(nuevoClienteModalButton).click();
	}
	
	public void enterCedula(String cedula) {
		getDriver().findElement(cedulaField).sendKeys(cedula);
	}
	
	public void enterNombre(String nombre) {
		getDriver().findElement(nombreField).sendKeys(nombre);
	}
	
	public void enterPrimerApellido(String primerApellido) {
		getDriver().findElement(primerApellidoField).sendKeys(primerApellido);
	}
	
	public void enterSegundoApellido(String segundoApellido) {
		getDriver().findElement(segundoApellidoField).sendKeys(segundoApellido);
	}
	
	public void enterTelefeno(String telefono) {
		getDriver().findElement(telefonoField).sendKeys(telefono);
	}
	
	public void enterDireccion(String direccion) {
		getDriver().findElement(direccionField).sendKeys(direccion);
	}

	public void pressAgregarButton() {
		getDriver().findElement(agregarButton).click();
	}
	
}
