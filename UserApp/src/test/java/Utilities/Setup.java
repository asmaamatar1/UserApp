package Utilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Setup {
	public static  AppiumDriver<MobileElement> driver;

	@BeforeClass (description = "Setup desire capabilities and launch app on the given device")
	public void launch() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "My Phone");
		caps.setCapability("udid", "86MDU16329009973"); //Give Device ID of your mobile phone
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "5.1.1");
		caps.setCapability("autoGrantPermissions","true");
		caps.setCapability("appPackage", "com.instasalla.app");
		caps.setCapability("appActivity", "com.instasalla.app.splash.SplashActivity");
		caps.setCapability("noReset", "true");
		
		//Instantiate Appium Driver
		try {
				driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
				
			
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	

}
