package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic09_Button_Checkbox_Radio_Alert {
	WebDriver driver;
	JavascriptExecutor javascript;
	Alert alert;
	String fullname = "thaont";
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		javascript = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	
	public void TC_01_Button() throws InterruptedException {
		
		driver.get("http://live.demoguru99.com/");
		//Click vào button My Account ở dưới footer
		javascript.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")));
		Thread.sleep(5000);
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		//Click vào button Create my account
		javascript.executeScript("arguments[0].click();", driver.findElement(By.xpath("//form[@id='login-form']//a[@class='button']")));
		Thread.sleep(5000);
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
		
	}

	public void TC_02_Checkbox() throws InterruptedException {
		driver.get("https://demos.telerik.com/kendo-ui/styling/checkboxes");
		String checkboxInput = "//label[text()='Heated front and rear seats']/preceding-sibling::input";
		
		clickByJS(checkboxInput);
		Thread.sleep(3000);
		
		elementSelectedStatus(checkboxInput);
		Assert.assertTrue(isElementSelected(checkboxInput));
		
		clickByJS(checkboxInput);
		Thread.sleep(3000);
		elementSelectedStatus(checkboxInput);
		Thread.sleep(3000);
		
		elementSelectedStatus(checkboxInput);
		Assert.assertFalse(isElementSelected(checkboxInput));
		
		
	}
	
	public void TC_03_RadioButton() throws InterruptedException{
		driver.get("https://demos.telerik.com/kendo-ui/styling/radios");
		String radioButtonInput = "//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input";
		clickByJS(radioButtonInput);
		Thread.sleep(3000);
		elementSelectedStatus(radioButtonInput);
		Assert.assertTrue(isElementSelected(radioButtonInput));
		
	}

	public void TC_04_acceptAlert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String locator = "//button[text()='Click for JS Alert']";
		String resultMessage = "//p[@id='result']";
		driver.findElement(By.xpath(locator)).click();
		alert = driver.switchTo().alert();
		//Kiểm tra text
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath(resultMessage)).getText(), "You clicked an alert successfully");
		}
	

	public void TC_05_confirmAlert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String locator = "//button[text()='Click for JS Confirm']";
		String resultMessage = "//p[@id='result']";
		driver.findElement(By.xpath(locator)).click();
		alert = driver.switchTo().alert();
		//Kiểm tra text
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath(resultMessage)).getText(), "You clicked: Ok");
		
		driver.navigate().refresh();
		driver.findElement(By.xpath(locator)).click();
		alert = driver.switchTo().alert();
		//Kiểm tra text
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.xpath(resultMessage)).getText(), "You clicked: Cancel");
		
		}

	public void TC_06_promptAlert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String locator = "//button[text()='Click for JS Prompt']";
		String resultMessage = "//p[@id='result']";
		
		driver.findElement(By.xpath(locator)).click();
		alert = driver.switchTo().alert();
		//Kiểm tra text
		alert.sendKeys(fullname);
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath(resultMessage)).getText(), "You entered: "+ fullname);
		
		driver.navigate().refresh();
		
		driver.findElement(By.xpath(locator)).click();
		alert = driver.switchTo().alert();
		//Kiểm tra text
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.xpath(resultMessage)).getText(), "You entered: null");
	}
	@Test
	public void TC_07_Authentication_Alert() throws InterruptedException {
		String usernameAndPass = "admin";
		String url = "http://the-internet.herokuapp.com/basic_auth";
		
		url = "http://" + usernameAndPass +":" + usernameAndPass + "@the-internet.herokuapp.com/basic_auth";
		driver.get(url);
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations!')]")).isDisplayed());
		Thread.sleep(3000);
		
	}
	
	public void checkToCheckbox(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if(!element.isSelected()) {
			element.click();
		}
	}
	public void uncheckToCheckbox(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if(element.isSelected()) {
			element.click();
		}
	}
	
	public void clickByJS(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		javascript.executeScript("arguments[0].click();", element);
	}
	public void elementSelectedStatus(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isEnabled()) {
			System.out.println("Element is enabled");
		}else {
			System.out.println("Element is disabled");
		}
	}
	public boolean isElementSelected(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if(element.isSelected()) {
			return true;
		}else {
			return false;
		}
	}

	@Test
	public void TC_02_() {
		driver.get("");	
	}

	@Test
	public void TC_03_() {
		driver.get("");	
	}

	@AfterClass
	public void afterClass() {
		//tắt trình duyệt
		driver.quit();
	}

}