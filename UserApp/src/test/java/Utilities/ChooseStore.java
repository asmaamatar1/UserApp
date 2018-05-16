package Utilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;

public class ChooseStore {
	
	@Test (priority=1, description = "Select Address")
	public void selectAddress() {
	// Getting List of addresses then, click one of them
	List <MobileElement> addresses =Setup.driver.findElements(By.id("address_name")); // Will retrieve list of addresses as they're all have the same resource id
	addresses.get(0).click(); //This should click on Jahra as it is the first and allowed address in this user account (99887755,123456)	
	}
	
	@Test (priority =2, description ="Click Filter then choose one of the options",dependsOnMethods ="selectAddress")
	public void filter(){
	
		//Click on Filter button
		WebElement filter =Setup.driver.findElement(By.id("filter_btn"));
		filter.click();
		
		//Getting the options in list.Then, click the first one (Select Hybermarkes&Coop)
		List<MobileElement> filter_options = Setup.driver.findElements(By.id("filter_name"));
		System.out.println("Filter List Size"+filter_options.size());
		filter_options.get(0).click();
	}
	
	@Test (priority=3, description ="Select Store",dependsOnMethods="filter")
	public void chooseStore() {
		//Select Nasiem Store
		List<MobileElement> stores = Setup.driver.findElements(By.id("store_name"));
		stores.get(0).click();	
	}
}
