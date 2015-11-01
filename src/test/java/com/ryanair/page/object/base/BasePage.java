package com.ryanair.page.object.base;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
	//fields	
	protected final WebDriver driver;
	
	//locators
	private final By loadPanelLocator = By.xpath(".//img[@src='img/ui/loading.gif']");
	
	//constructors
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}	
	
	public void waitForPageLoaded() {
		(new WebDriverWait(driver, 100)).
		until(ExpectedConditions.invisibilityOfElementLocated(loadPanelLocator));
	}
	
	public String activeMenuItemName() {
		WebElement menu = driver.findElement(By.tagName("nav"));
		List<WebElement> menuItems = menu.findElements(By.tagName("li"));
		for(Iterator<WebElement> i = menuItems.iterator(); i.hasNext(); ) {
			WebElement item = i.next();
		    if(item.getAttribute("class").contains("cur")){
		    	return item.getText().trim();
		    }
		}
		return null;
	}
	
	public void scrollPage() {
		JavascriptExecutor jsx = (JavascriptExecutor)driver;
		jsx.executeScript("window.scrollBy(0,1500)", "");
	}
}
