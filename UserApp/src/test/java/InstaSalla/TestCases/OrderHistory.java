package InstaSalla.TestCases;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utilities.ChooseStore;
import Utilities.Login;
import Utilities.Logout;
import Utilities.OrderPage;
import Utilities.Setup;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class OrderHistory {
	
Setup launch = new Setup();
Login login = new Login();
ChooseStore store = new ChooseStore();
Logout logout = new Logout();
OrderPage order = new OrderPage();

	@BeforeClass
		public void setup() {
			launch.launch();
			login.login();
			store.selectAddress();
			store.filter();
			store.chooseStore();
	}
	
	 @Test (priority = 1 , description = "View Order History")
		public void orderHistory() {
			
		 	//Click on "Me"
		 	List<MobileElement> navigationContainer = Setup.driver.findElements(By.id("bottom_navigation_container"));
			navigationContainer.get(3).click(); 
			
			//Click on Order History
			WebElement orderHistory= Setup.driver.findElement(By.id("order_history_view"));
			orderHistory.click();	
		}
	 
	 @Test (priority = 2, description = "Track order for a random order",dependsOnMethods="orderHistory")
	 public void trackOrder() {
		
		 List<MobileElement> orders =Setup.driver.findElements(By.id("order_number"));
		 orders.get(0).click();
		 
		 WebElement trackOrder = Setup.driver.findElement(By.id("tracking_order_button"));
		 trackOrder.click();
		 
		 Setup.driver.navigate().back(); 
	 }
	 
	 @Test (priority=3, description="Click Reoreder for the first order in the list",dependsOnMethods="trackOrder")
	 public void reorder() {
		 WebElement reorder =Setup.driver.findElement(By.id("reorder_button"));
		 reorder.click();
	 }
	 
	 @Test (priority= 4, description= "Complete order cycle",dependsOnMethods="reorder")
	 public void completeOrder() {
		 order.confirmOrder();
		 order.changeTime();
		 order.choosePaymentMethod();
		 order.checkout();
	 }
	

	@AfterTest (description = "Takes screenshot for each action performed")
	public void takeScreenshots() throws IOException {
		File srcFile =Setup.driver.getScreenshotAs(OutputType.FILE);
	    String filename = UUID.randomUUID().toString();
	    File targetFile = new File(System.getProperty("user.dir") + "/order_screenshots/" + filename + ".jpg");
	    try {
	        FileUtils.copyFile(srcFile, targetFile);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
        
		
	}
		
	@AfterClass (description = "Logout")
	public void logout()
	{
 		logout.logout();
	}
		
		
}
