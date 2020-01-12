package webdriver_api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic08_Dropdownlist {
	WebDriver driver;
	Select select;
	Select day;
	Select month;
	Select year;
	WebDriverWait waitExplicit;
	By numberAllItems = By.xpath("//ul[@id='number-menu']/li");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	
	public void TC_01_normalDropdownlist() throws InterruptedException {
		//Step 01
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Thao tác với Job Role 01 dropdown
		select = new Select(driver.findElement(By.name("user_job1")));
		
		//Step 02: Kiểm tra dropdown này không được phép chọn nhiều
		boolean userDropdownStatus = select.isMultiple();
		System.out.println("User Status =" + userDropdownStatus);
		Assert.assertFalse(userDropdownStatus);
		
		//Step 03: Chọn giá trị Mobile Testing trong dropdown = phương thức selectByVisibleText
		select.selectByVisibleText("Mobile Testing");
		Thread.sleep(1000);
		
		//Step 04: Kiểm tra giá trị đã được chọn thành công
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Testing");
		
		//Step 05: Chọn giá trị Manual Testing trong dropdown bằng phương thức selectByValue
		select.selectByValue("manual");
		Thread.sleep(1000);
		//Step 06: Kiểm tra giá trị đã được chọn thành công
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Testing");
		
		//Step 07: Chọn giá trị Functional UI testing trong dropdown = phương thức selectByIndex
		select.selectByIndex(9);
		Thread.sleep(1000);
		//Step 08: Kiểm tra giá trị được chọn thành công
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Functional UI Testing");
		
		//Step 09: Kiểm tra dropdown có đủ 10 giá trị
		int userNumber = select.getOptions().size();
		System.out.println("User Dropdown có " + userNumber + "giá trị");
		Assert.assertEquals(userNumber, 10);
		
		//Step 10: Kiểm tra dropdown Job Role 02 - Multiple Dropdown có hỗ trợ thuộc tính multiple select
		select = new Select(driver.findElement(By.name("user_job2")));
		boolean role2DropdownStatus = select.isMultiple();
		Assert.assertTrue(role2DropdownStatus);
		
		//Step 11: Chọn nhiều giá trị cùng một lúc: Automation, Mobile, Desktop
		select.selectByVisibleText("Automation");
		select.selectByVisibleText("Mobile");
		select.selectByVisibleText("Desktop");
		
		//Step 12: Kiểm tra các giá trị được chọn thành công
		List <WebElement> optionSelected = select.getAllSelectedOptions();
		Assert.assertEquals(optionSelected.size(), 3);
		
		List <String> arraySelected = new ArrayList<String>();
		for(WebElement select: optionSelected) {
			System.out.println(select.getText());
			arraySelected.add(select.getText());
		}
		
		Assert.assertTrue(arraySelected.contains("Automation"));
		Assert.assertTrue(arraySelected.contains("Mobile"));
		Assert.assertTrue(arraySelected.contains("Desktop"));
		
		//Deselect cả 3 giá trị một lúc
		select.deselectAll();
		
		//Kiểm tra không còn giá trị nào được chọn
		List <WebElement> optionUnSelected = select.getAllSelectedOptions();
		Assert.assertEquals(optionUnSelected.size(), 0);		
		
	}

	
	public void TC_02_registerWithDropdownList() {
		//Step 01: truy cập vào trang https://demo.nopcommerce.com
		driver.get("https://demo.nopcommerce.com");
		//Click Register link trên Header
		driver.findElement(By.xpath("//a[@class = 'ico-register']")).click();
		
		//Input các thông tin hợp lệ vào form
		driver.findElement(By.xpath("//input[@value = 'M']")).click();
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("John");
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Wick");
		
		day = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		day.selectByVisibleText("1");
		
		int dayNumber = day.getOptions().size();
		System.out.println("User Dropdown có " + dayNumber + "giá trị");
		Assert.assertEquals(dayNumber, 32);
		
		month = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		month.selectByVisibleText("May");
		
		int monthNumber = month.getOptions().size();
		System.out.println("User Dropdown có " + monthNumber + "giá trị");
		Assert.assertEquals(monthNumber, 13);
		
		year = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		year.selectByVisibleText("1980");
		
		int yearNumber = year.getOptions().size();
		System.out.println("User Dropdown có " + yearNumber + "giá trị");
		Assert.assertEquals(yearNumber, 112);
		
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("johnwick1@gmail.com");
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("12345678");
		driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("12345678");
		//Click Register button
		driver.findElement(By.xpath("//input[@id='register-button']")).click();
		
		//Verify đăng ký thành công
		driver.findElement(By.xpath("//a[@class='ico-account']")).isDisplayed();
		driver.findElement(By.xpath("//a[@class='ico-logout']")).isDisplayed();
		driver.findElement(By.xpath("//div[@class='result']")).isDisplayed();		
		
	}

	@Test
	public void TC_03_jQuery() throws InterruptedException {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");	
		//Chọn item cuối cùng số 19
		//1. Click vào thẻ để nó xổ ra hết giá trị trong dropdown
		driver.findElement(By.xpath("//span[@id='number-button']")).click();

		//2. Khai báo 1 list chứa tất cả các items bên trong list web element 
		List <WebElement> allItems = driver.findElements(By.xpath("//ul[@id='number-menu']/li"));
		
		//3. Wait cho tất cả item được xuất hiện ở trong DOM => dùng Explicit wait
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(numberAllItems));
		
		//4. Get text từng item sau đó so sánh với item mình cần chọn
		for(WebElement item: allItems) {
			System.out.println(item.getText());
			//5. Kiểm tra item nào đúng với cái mình cần chọn thì click vào
			if(item.getText().equals("5")) {
				item.click();
				break;
			}
		}
	
		//6. Kiểm tra item đã được chọn thành công
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='5']")).isDisplayed());
		Thread.sleep(3000);
	}
	
	

	@AfterClass
	public void afterClass() {
		//tắt trình duyệt
		driver.quit();
	}

}