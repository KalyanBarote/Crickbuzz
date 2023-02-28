package crickinfo;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Crickinfo {

	// Create object for Chromedriver
	WebDriver driver = new ChromeDriver();

	// create the object of explicit waits
	WebDriverWait expWait = new WebDriverWait(driver, Duration.ofSeconds(10));
	String country = "India v Sri Lanka";

	@Test
	public void launchUrl() throws InterruptedException {

		// Set system property so that code should know the location of Chromedriver
		System.setProperty("webdriver.chrome.driver", "D:\\app\\WebDriver\\chromedriver.exe");
		// Launch the url
		driver.get("https://www.espncricinfo.com/");
		// Maximize the window
		driver.manage().window().maximize();
		Thread.sleep(3000);
	}
	
	

/*	@Test
	public void popup() throws InterruptedException {
		
		try
		{   //Thread.sleep(6000);
			expWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@class=\"No thanks\"]")))).click();
		}
		catch(NoSuchElementException e)
		{
			System.out.println("no popup");
		}
	}*/

	
	@Test
	public void allMatches() throws InterruptedException {
		
		expWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[text()='Series']"))))
				.click();
		List<WebElement> matches = driver
				.findElements(By.xpath("//div[@class=\"tab-content active\"]/div/ul/li/a"));

		for (int i = 0; i < matches.size(); i++) {
			String match = matches.get(i).getText();

			if (match.equalsIgnoreCase(country)) {
				expWait.until(ExpectedConditions.visibilityOf(matches.get(i))).click();
				System.out.println("Lokking for -" + country);
				break;
			} 
			else {
				System.out.println("not found");
			}
		}
	}



}
