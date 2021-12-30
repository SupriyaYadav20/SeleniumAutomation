package SeleniumBasics;

import static org.testng.Assert.assertEquals;

import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment3 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		// Set system path for browser driver
		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumBrowserDrivers\\chromedriver.exe");

		// Open Browser
		WebDriver driver = new ChromeDriver();

	// Open URL
		driver.get("http://zero.webappsecurity.com/");
		driver.manage().window().maximize();

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

		// Click on Pay Bills
		driver.findElement(By.xpath("//a[contains(text(),'Pay Bills')]")).click();
		Thread.sleep(1000);

		// Click on Purchase Foreign Currency
		driver.findElement(By.xpath("//a[contains(text(),'Purchase Foreign Currency')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("purchase_cash")).click();

		// Alert
		Alert jsAlert = driver.switchTo().alert();
		String alertText = jsAlert.getText();
		System.out.println("Text on Asert is::" + alertText);
		jsAlert.accept();

		
		//Window Switching
		driver.get("https://www.naukri.com/");
		String pHandle = driver.getWindowHandle();
		System.out.println(pHandle);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//img[contains(@src,'https://company.naukri.com/homepage21/cognizant-hs-tp-21sep2018.gif')]")).click();
		
		Set<String> handles = driver.getWindowHandles();
		System.out.println(handles);

		for (String h : handles) {
			System.out.println("The values of current handle is" + h);
			driver.switchTo().window(h);
			System.out.println(driver.getTitle());
		}
		driver.switchTo().window(pHandle);
		Thread.sleep(2000);

		// ##########################################################
		// Open URL
		driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_confirm");
		//driver.manage().window().maximize();
		Thread.sleep(2000);
		
		WebElement e = driver.findElement(By.id("iframeResult"));
		driver.switchTo().frame(e);
		
		driver.findElement(By.xpath("//button[contains(text(),'Try it')]")).click();
		//driver.findElement(By.id("iframeResult")).click();
		Alert jsConfirm = driver.switchTo().alert();
		String confirmText = jsConfirm.getText();
		Thread.sleep(2000);
		System.out.println("Text on Asert is::" + confirmText);
		jsConfirm.accept();
		
//		// Close Browser
		driver.close();

		// Kill Browser
		driver.quit();
	}

}
