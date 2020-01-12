package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic00_Template {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_FindEmail() {
		System.out.println("http://live.demoguru99.com/index.php/customer/account/login/");	
		
		
		
		
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("autotest@gmail.com");
		
		driver.findElement(By.name("login[username]")).sendKeys("autotest@gmail.com");
		
		//ID
		driver.findElement(By.id("email")).sendKeys("abc@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("abcabc");
		
		//NAME
		
		
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