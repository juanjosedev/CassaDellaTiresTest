package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
	
	private WebDriver driver;
	private MenuNavegacion menu;
	private By title;
	WebDriverWait wait;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		menu = new MenuNavegacion(driver);
		title = By.cssSelector("body > div.container-fluid.title_maestro.bg-indigo > div > div > h2");
		wait = new WebDriverWait(getDriver(), 5000);
	}
	
	public MenuNavegacion getMenu() {
		return menu;
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public String getTitle() {
		return driver.findElement(title).getText();
	}
	
	public String getAlertMessage(By selector) {
		wait.until(ExpectedConditions.presenceOfElementLocated(selector));
		return getDriver().findElement(selector).getText();
	}
	
	public String getText(By selector) {
		return getDriver().findElement(selector).getText();
	}
	
}
