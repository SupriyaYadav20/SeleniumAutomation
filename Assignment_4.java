package Assignment;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.*;

public class Assignment_4 {

	public WebDriver driver;

	// ****************Purchase foreign currency cash **********************
	@Test(groups= {"Smoke"})
	public void Test1() {
		// Open URL
		driver.get("http://zero.webappsecurity.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		assertEquals(driver.getTitle(), "Zero - Personal Banking - Loans - Credit Cards", "Test Failed");
		driver.findElement(By.id("searchTerm")).sendKeys("Banking");
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		driver.findElement(By.tagName("button")).click();
		driver.findElement(By.name("user_login")).sendKeys("username");
		driver.findElement(By.cssSelector("i.icon-question-sign")).click();
		driver.findElement(By.cssSelector("[type='password']")).sendKeys("password");
		driver.findElement(By.cssSelector("input#user_remember_me[name='user_remember_me']")).click();

		driver.findElement(By.name("submit")).click();
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.partialLinkText("Proceed to zero")).click();
		assertEquals(driver.getTitle(), "Zero - Account Summary", "Test Failed");
		// Click on Pay Bills
		driver.findElement(By.xpath("//a[contains(text(),'Pay Bills')]")).click();

		// Click on Purchase Foreign Currency
		driver.findElement(By.xpath("//a[contains(text(),'Purchase Foreign Currency')]")).click();
		WebDriverWait ewait = new WebDriverWait(driver, 30);
		ewait.until(ExpectedConditions.textToBe(By.xpath("//h2[contains(text(),'Purchase foreign currency cash')]"),
				"Purchase foreign currency cash"));
		WebElement radioButton = driver.findElement(By.id("pc_inDollars_true"));
		boolean rbIsSelected = radioButton.isSelected();
		if (!rbIsSelected) {
			radioButton.click();
			System.out.println("RadioButton was not selected.I have to click on it now!!");
		}
		WebElement currency = driver.findElement(By.id("pc_currency"));
		Select sel = new Select(currency);
		sel.selectByVisibleText("Switzerland (franc)");
		driver.findElement(By.id("purchase_cash")).click();

		// Alert
		Alert jsAlert = driver.switchTo().alert();
		String alertText = jsAlert.getText();
		System.out.println("Text on Asert is::" + alertText);
		jsAlert.accept();
	}

	// ********************Fund Transfer***********************************
	@Test(groups= {"Smoke"})
	public void Test2() {
		assertEquals(driver.getTitle(), "Zero - Pay Bills", "Test Failed");
		driver.findElement(By.linkText("Transfer Funds")).click();
		WebDriverWait ewait = new WebDriverWait(driver, 30);
		ewait.until(ExpectedConditions.textToBe(By.xpath("//h2[contains(text(),'Transfer Money & Make Payments')]"),
				"Transfer Money & Make Payments"));
		WebElement FromAccount = driver.findElement(By.name("fromAccountId"));
		Select From_Account = new Select(FromAccount);
		From_Account.selectByIndex(2);

		WebElement ToAccount = driver.findElement(By.className("input-xlarge"));
		Select To_Account = new Select(ToAccount);
		To_Account.selectByValue("1");

		driver.findElement(By.id("tf_amount")).sendKeys("50");
		driver.findElement(By.id("tf_description")).sendKeys("Electricity Bill");
		driver.findElement(By.tagName("button")).click();

		driver.findElement(By.id("btn_submit")).click();
		assertEquals(
				driver.findElement(By.xpath("//div[contains(text(),'You successfully submitted your transaction.')]"))
						.getText(),
				"You successfully submitted your transaction.", "Test Failed");
	}

	@Test(groups= {"Regression"})
	public void Test3() {
		assertEquals(driver.getTitle(), "Zero - Pay Bills", "Test Failed");
		driver.findElement(By.linkText("Transfer Funds")).click();
		WebDriverWait ewait = new WebDriverWait(driver, 30);
		ewait.until(ExpectedConditions.textToBe(By.xpath("//h2[contains(text(),'Transfer Money & Make Payments')]"),
				"Transfer Money & Make Payments"));
		WebElement FromAccount = driver.findElement(By.name("fromAccountId"));
		Select From_Account = new Select(FromAccount);
		From_Account.selectByIndex(2);

		WebElement ToAccount = driver.findElement(By.className("input-xlarge"));
		Select To_Account = new Select(ToAccount);
		To_Account.selectByValue("1");

		driver.findElement(By.tagName("button")).click();

		// driver.findElement(By.id("btn_submit")).click();
		assertEquals(
				driver.findElement(By.xpath("//div[contains(text(),'You successfully submitted your transaction.')]"))
						.getText(),
				"You successfully submitted your transaction.", "Test Failed");
	}

	// ***********Negative Test Cases(Regression Test)***************************
	// **********Invalid Password************************************************
	@Test(groups= {"Regression"})
	public void Test4() {

//Logout
		driver.findElement(By.xpath("//body/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[3]/a[1]")).click();
		driver.findElement(By.xpath("//a[@id='logout_link']")).click();

		driver.findElement(By.id("signin_button")).click();
		driver.findElement(By.name("user_login")).sendKeys("username");
		driver.findElement(By.cssSelector("i.icon-question-sign")).click();
		driver.findElement(By.cssSelector("[type='password']")).sendKeys("password123");
		driver.findElement(By.cssSelector("input#user_remember_me[name='user_remember_me']")).click();
		driver.findElement(By.name("submit")).click();
		assertEquals(driver.getTitle(), "Zero - Account Summary", "Test Failed");
	}

	// ***********Negative Test Cases(Regression Test)***************************
	// **********Invalid Username************************************************
	@Test(groups= {"Regression"})
	public void Test5() {
		// Logout
		driver.findElement(By.xpath("//body/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[3]/a[1]")).click();
		driver.findElement(By.xpath("//a[@id='logout_link']")).click();

		driver.findElement(By.id("signin_button")).click();
		driver.findElement(By.name("user_login")).sendKeys("username123");
		driver.findElement(By.cssSelector("i.icon-question-sign")).click();
		driver.findElement(By.cssSelector("[type='password']")).sendKeys("password");
		driver.findElement(By.cssSelector("input#user_remember_me[name='user_remember_me']")).click();
		driver.findElement(By.name("submit")).click();
		assertEquals(driver.getTitle(), "Zero - Account Summary", "Test Failed");
	}

	// ***********Negative Test Cases(Regression Test)***************************
	// **********Blank Username and
	// Password************************************************
	@Test(groups= {"Regression"})
	public void Test6() {

		// Logout
		driver.findElement(By.xpath("//body/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[3]/a[1]")).click();
		driver.findElement(By.xpath("//a[@id='logout_link']")).click();

		driver.findElement(By.id("signin_button")).click();
		driver.findElement(By.name("user_login"));
		driver.findElement(By.cssSelector("i.icon-question-sign")).click();
		driver.findElement(By.cssSelector("[type='password']"));
		driver.findElement(By.cssSelector("input#user_remember_me[name='user_remember_me']")).click();
		driver.findElement(By.name("submit")).click();
		assertEquals(driver.getTitle(), "Zero - Account Summary", "Test Failed");
	}

	@Test(groups= {"Regression"})
	public void Test7() {

		// Logout
		driver.findElement(By.xpath("//body/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[3]/a[1]")).click();
		driver.findElement(By.xpath("//a[@id='logout_link']")).click();

		driver.findElement(By.id("signin_button")).click();

		driver.findElement(By.cssSelector("input#user_remember_me[name='user_remember_me']")).click();
		driver.findElement(By.name("submit")).click();
		assertEquals(driver.getTitle(), "Zero - Account Summary", "Test Failed");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Before method");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("After method");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("Before class");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("After class");
	}

	@BeforeTest
	public void beforeTest() {

// Set system path for browser driver
		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumBrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

	}

	@AfterTest
	public void afterTest() {
// Close Browser
		driver.close();
// Kill Browser
		driver.quit();
	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("Zero-Bank Application");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("Zero-Bank Application With Positive and Negative Test Cases");
	}

}