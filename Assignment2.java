package SeleniumBasics;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment2 {

	public static void main(String[] args) throws InterruptedException {
		// Set system path for browser driver
		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumBrowserDrivers\\chromedriver.exe");

		// Open Browser
		WebDriver driver = new ChromeDriver();

		// Open URL
		driver.get("http://zero.webappsecurity.com/");
		driver.manage().window().maximize();

		// Assertion
		String expected = "Zero - Personal Banking - Loans - Credit Cards";
		String actual = driver.getTitle();
		assertEquals(actual, expected, "Test Failed");

		//Verification
		try 
		{
			String brand = driver.findElement(By.className("brand")).getText();
			assertEquals(brand, "Zero Bank", "Failed");
		} 
		catch (AssertionError e) 
		{
			e.printStackTrace();
		}

		driver.findElement(By.className("search-query")).sendKeys("Online Banking:");

		// Login to the application
		
		driver.findElement(By.tagName("button")).click();
		driver.findElement(By.name("user_login")).sendKeys("username");
		driver.findElement(By.cssSelector("i.icon-question-sign")).click();
		driver.findElement(By.cssSelector("[type='password']")).sendKeys("password");
		driver.findElement(By.cssSelector("input#user_remember_me[name='user_remember_me']")).click();
		Thread.sleep(1000);

		driver.findElement(By.name("submit")).click();
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.partialLinkText("Proceed to zero")).click();
		
		//Click on Pay Bills
		driver.findElement(By.xpath("//a[contains(text(),'Pay Bills')]")).click();
		Thread.sleep(1000);
		
		//Add New Payee
		driver.findElement(By.xpath("//a[contains(text(),'Add New Payee')]")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.id("np_new_payee_name")).sendKeys("Sanjay");
		driver.findElement(By.name("address")).sendKeys("Pune");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='np_new_payee_account']")).sendKeys("50");
		driver.findElement(By.xpath("//input[@id='np_new_payee_details']")).sendKeys("Elcecticity Bills");
		driver.findElement(By.cssSelector("input#add_new_payee")).click();
	
		//Logout
		driver.findElement(By.xpath("//body/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[3]/a[1]")).click();
		driver.findElement(By.xpath("//a[@id='logout_link']")).click();
		
		
		//Feedback Form
		driver.findElement(By.xpath("//strong[contains(text(),'Feedback')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("name")).sendKeys("Supriya");
		driver.findElement(By.id("email")).sendKeys("supriya@gmail.com");
		driver.findElement(By.id("subject")).sendKeys("No registration page");
		driver.findElement(By.id("comment")).sendKeys("There is no registration page for login");
		driver.findElement(By.name("submit")).click();

		Thread.sleep(2000);

		// Close Browser
		driver.close();

		// Kill Browser
		driver.quit();
	}

}
