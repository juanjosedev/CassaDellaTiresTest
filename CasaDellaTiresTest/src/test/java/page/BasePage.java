package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
	
	private WebDriver driver;
	private MenuNavegacion menu;
	WebDriverWait wait;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		menu = new MenuNavegacion(driver);
		wait = new WebDriverWait(getDriver(), 5);
	}
	
	public MenuNavegacion getMenu() {
		return menu;
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public String getTitle() {
		return this.driver.getCurrentUrl();
	}
	
	public String getAlertMessage(By selector) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
		return driver.findElement(selector).getText();
	}
	
	public String getText(By selector) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
		return driver.findElement(selector).getText();
	}
	
	public String getInputValue(By selector) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(selector)).getAttribute("value");
	}
	
	public void sendKeys(By selector, String value) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(selector)).sendKeys(value);;
	}
	
	public void pressButton(By selector) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(selector)).click();
	}
	
}
