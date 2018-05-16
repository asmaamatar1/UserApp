package Utilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.MobileElement;

public class OrderPage {
	
	@Test(priority = 1, description ="Add items to Cart")
	public void addItems() {
		//Pressing See All Link in best offer section
		WebElement seeAll = Setup.driver.findElement(By.id("see_all_best_offers"));
		seeAll.click();
		
		//Getting list of all the visible items as all "Add to Cart" buttons have the same ID
		List<MobileElement> addToCart = Setup.driver.findElements(By.id("item_menu_add_to_cart_view"));
		//Looping in the first two visible items to avoid scrolling
		for (int i= 0 ; i < 2; i++) { 
			addToCart.get(i).click();
		}
			List<MobileElement> plus_btn = Setup.driver.findElements(By.id("product_detail_add_btn"));
			List<MobileElement> minus_btn = Setup.driver.findElements(By.id("product_detail_subtract_btn"));
			
			//Adding 3 quantities for each item then decrementing 1
			for(int i=0; i<2; i++) {
				for (int z=0; z<2; z++) 
						plus_btn.get(i).click(); 
				minus_btn.get(i).click();
			}
	}
	
	@Test(priority = 2, description ="View Cart to confirm Order",dependsOnMethods ="addItems")
	public void viewCart() {
		//Clicking Cart Icon to view selected items
		WebElement cart = Setup.driver.findElement(By.id("cart_icon"));
		cart.click();
	}
	
	@Test (priority = 3, description ="Confirm Order",dependsOnMethods ="viewCart")
	public void confirmOrder() {
		//Confirming order after viewing selected items in the cart
		WebElement confirmOrder = Setup.driver.findElement(By.id("confirm_order_btn"));
		confirmOrder.click();
	}
	

	@Test (priority = 4, description = "Change Delivery time",dependsOnMethods ="confirmOrder")
	public void changeTime() {
		
		//Open delivery times ddl
		WebElement delivertTime = Setup.driver.findElement(By.id("choose_delivery_time"));
		delivertTime.click();
		
		//Select from the opened times ddl
		List<MobileElement> times = Setup.driver.findElements(By.id("text1"));
		times.get(1).click();
		
		//Clicking "OK" to confirm the selected option.
		WebElement OK = Setup.driver.findElement(By.id("button1"));
		OK.click();
	}
	
	
	@Test (priority = 5, description = "Scroll down till KNET then choose it and fill in copoun textbox",dependsOnMethods ="changeTime")
	public void choosePaymentMethod() {
			//Scroll until "KNET" is visible then click it
		 String uiSelector = "new UiSelector().textMatches(\"KNET\")";
		 String command = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView("+ uiSelector + ");";
		 ((FindsByAndroidUIAutomator<MobileElement>) Setup.driver).findElementByAndroidUIAutomator(command).click();
		 
		 //Type a copoun code
		 WebElement copoun = Setup.driver.findElement(By.id("promo_code_et"));
		 copoun.sendKeys("100100");
		 
		 //Click on Apply copoun
		 WebElement apply_btn = Setup.driver.findElement(By.id("promo_code_btn"));
		 apply_btn.click();	  
		 
		 //Press OKAY in the dialogue box appears after pressing "Apply Copoun"
		 
		 WebElement OKAY = Setup.driver.findElement(By.id("button1"));
		 OKAY.click();
		 
	}
	
	@Test (priority = 6, description = "Press on Checkout button",dependsOnMethods="choosePaymentMethod")
	public void checkout() {
	  WebElement checkout = Setup.driver.findElement(By.id("confirm_order_btn"));
	  checkout.click();
	}
	
	

}
