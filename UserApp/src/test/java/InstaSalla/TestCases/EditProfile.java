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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Utilities.ChooseStore;
import Utilities.Login;
import Utilities.Logout;
import Utilities.Setup;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class EditProfile {
	
	Setup launch = new Setup();
	Login login = new Login();
	ChooseStore store = new ChooseStore();
	Logout logout = new Logout();
	
	@BeforeClass
	public void setup() {
		launch.launch();
		login.login();
		store.selectAddress();
		store.filter();
		store.chooseStore();
	}
	
	
	@Test(priority=1, description="Edit Profile")
	public void editProfile() {
		
		//Click on "Me"
	 	List<MobileElement> navigationContainer = Setup.driver.findElements(By.id("bottom_navigation_container"));
		navigationContainer.get(3).click(); 
		
		WebElement editProfile = Setup.driver.findElement(By.id("edit_account_view"));
		editProfile.click();
		
		
		List<MobileElement> profile_fields = Setup.driver.findElements(By.id("et_custom_edittext"));
		profile_fields.get(0).sendKeys("Full Name Update");
		profile_fields.get(2).sendKeys("99885566");
		Setup.driver.hideKeyboard();
		
		WebElement update_btn = Setup.driver.findElement(By.id("email_sign_in_button"));
		update_btn.click();
	}

 	@AfterTest (description = "Takes screenshot for each action performed")
	public void takeScreenshots() throws IOException {
		File srcFile = Setup.driver.getScreenshotAs(OutputType.FILE);
	    String filename = UUID.randomUUID().toString();
	    File targetFile = new File(System.getProperty("user.dir") + "/track_screenshots/" + filename + ".jpg");
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
