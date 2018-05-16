package Utilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;

public class Logout {
	
	@Test (description = "Logout")
	public void logout()
	{
		List<MobileElement> navigationContainer = Setup.driver.findElements(By.id("bottom_navigation_container"));
		navigationContainer.get(3).click(); //Click on "Me"
		WebElement logout = Setup.driver.findElement(By.id("logout_btn"));
		logout.click();
		
	}	

}
