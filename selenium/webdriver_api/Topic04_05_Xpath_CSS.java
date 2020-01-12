package webdriver_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic04_05_Xpath_CSS {
	WebDriver driver;
	String firstName = "Automation";
	String lastName = "Testing";
	String validPassword = "123123";
	String validEmail = "automation_13@gmail.com";

	@BeforeClass(description = "Chạy trước cho tất cả test bên dưới")
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@BeforeMethod(description = "Chạy trước cho mỗi test bên dưới")
	public void beforeMethod() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	}
	@Test
	public void TC_01_LoginwithEmptyEmailandPassword() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		String emailErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals(emailErrorMsg, "This is a required field.");

		String passwordErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(passwordErrorMsg, "This is a required field.");
	}
	@Test
	public void TC_02_LoginwithInvalidEmail() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("12341234@1234.1234");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		String emailErrorMsg = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals(emailErrorMsg, "Please enter a valid email address. For example johndoe@domain.com.");

		String passwordErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(passwordErrorMsg, "This is a required field.");
	}
	@Test
	public void TC_03_LoginwithPasswordLessThan6Chars() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		String passwordErrorMsg = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(passwordErrorMsg, "Please enter 6 or more characters without leading or trailing spaces.");

	}
	@Test
	public void TC_04_LoginwithIncorrectPassword() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		String ErrorMsg = driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).getText();
		Assert.assertEquals(ErrorMsg, "Invalid login or password.");
	}
	@Test
	public void TC_05_CreateNewAccount() {
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();

		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys("Automation" + randomNumber() + "@gmail.com");
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(validPassword);
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(validPassword);
		driver.findElement(By.xpath("//button[@title='Register']")).click();

		String myDashboard = driver.findElement(By.xpath("//div[@class='page-title']//h1[text()='My Dashboard']")).getText();
		Assert.assertEquals(myDashboard, "MY DASHBOARD");
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Hello, " + firstName + " " + lastName + "!']")).isDisplayed());

		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());

		// Step 7: Logout khỏi hệ thống
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		// Step 8: Kiểm tra hệ thống navigate về Homepage thành công
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);
	}

	public int randomNumber() {
		Random rand = new Random();
		int n = rand.nextInt(9999);
		return n;
	}

	@Test
	public void TC_06_LoginWithValidEmailAndPassword() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(validEmail);
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(validPassword);
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']//h1[text()='My Dashboard']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Hello, " + firstName + " " + lastName + "!']")).isDisplayed());
		// div[@class='box-content']/p[contains(text(),'Automation Testing')]

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(text(),'" + firstName + " " + lastName + "')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(.,'"+ validEmail +"')]")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		// tắt trình duyệt
		driver.quit();
	}

}