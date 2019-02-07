package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InicioSesionPage extends BasePage{

	private By usuarioField;
	private By passwordField;
	private By submitButton;
	
	public InicioSesionPage(WebDriver driver) {
		super(driver);
		usuarioField = By.name("usuario");
		passwordField = By.name("clave");
		submitButton = By.name("iniciar_sesion");
	}
	
	public void enterUsuario(String usuario) {
		getDriver().findElement(usuarioField).sendKeys(usuario);
	}
	
	public void enterPassword(String password) {
		getDriver().findElement(passwordField).sendKeys(password);
	}
	
	public void pressSubmit() {
		getDriver().findElement(submitButton).click();
	}
	
	public String getErrorMessage() {
		return getDriver().findElement(By.id("alert_error")).getText();
	}
	
	public void sendForm(String usuario, String password) {
		enterUsuario(usuario);
		enterPassword(password);
		pressSubmit();
	}
	
}
