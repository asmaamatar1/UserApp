package Utilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;

public class Login extends Setup {
	@Test (priority = 1, description = "Login to the app")
	public void login()
	{
		//Define screen fields 
	
		WebElement login_btn = driver.findElement(By.id("tv_login"));
		login_btn.click();
		
		//Get Mobile Number and password fields in list as they've the same id
		List<MobileElement> elements = driver.findElements(By.id("et_custom_edittext"));		
			elements.get(0).sendKeys("99887755");
			elements.get(1).sendKeys("123456");
			
		//Clicking Login button after filling the fields	
		WebElement login = driver.findElement(By.id("btn_login"));
		login.click();	
	}
		
}
