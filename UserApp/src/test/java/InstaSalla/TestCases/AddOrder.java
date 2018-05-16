package InstaSalla.TestCases;
import static org.testng.AssertJUnit.assertNotNull;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.*;

import Utilities.ChooseStore;
import Utilities.Login;
import Utilities.Logout;
import Utilities.OrderPage;
import Utilities.Setup;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class AddOrder {
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
	
	
	@Test(description= "All add order steps", invocationCount=2)
	public void addOrder() {
		order.addItems();
		order.viewCart();
		order.confirmOrder();
		order.changeTime();
		order.choosePaymentMethod();
		order.checkout();
	}
	
	@AfterTest (description = "Takes screenshot for each action performed")
		public void takeScreenshots() throws IOException {
			File srcFile = Setup.driver.getScreenshotAs(OutputType.FILE);
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
