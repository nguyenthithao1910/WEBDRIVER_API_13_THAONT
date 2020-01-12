package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic01_02_03_Template {
	//khai báo một biến đại diện cho web driver
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		//Khoi tao trinh duyet Firefox
		driver = new FirefoxDriver();
		
		//Chờ cho element được hiển thị trước khi trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Phóng to trình duyệt
		driver.manage().window().maximize();
		
		//Mở ra một trang web (AUT: Application Under Test)
		driver.get("http://demo.guru99.com/v4/");
	}

	@Test
	public void TC_01_ValidateCurrentUrl() {
		// Lấy ra URL của page hiện tại và gán nó vào biến LoginPageUrl
		String loginPageUrl = driver.getCurrentUrl();
		
		//In ra console cho người dùng (Coder) thấy được kết quả test
		System.out.println(loginPageUrl);
		
		//Các hàm verify dữ liệu của testNG (true/fails/equals)
		Assert.assertEquals(loginPageUrl, "http://demo.guru99.com/v4/");
	}

	@Test
	public void TC_02_ValidatePageTitle() {
		// Lấy ra title của page hiện tại và gán nó vào biến loginPageTitle
		String loginPageTitle = driver.getTitle();
		
		//Verify dữ liệu của biến loginPageTitle có bằng với giá trị mình mong muốn.
		Assert.assertEquals(loginPageTitle, "Guru99 Bank Home Page");
	}

	@Test
	public void TC_03_LoginFormDisplayed() {
		// Verify form login được hiển thị trên trang Login
		Assert.assertTrue(driver.findElement(By.xpath("//form[@name='frmLogin']")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		//tắt trình duyệt
		driver.quit();
	}

}