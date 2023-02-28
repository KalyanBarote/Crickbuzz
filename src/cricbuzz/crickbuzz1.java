package cricbuzz;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class crickbuzz1 {

	public static void main(String[] args) throws InterruptedException {
		int totalScore;
		int indivisualSum;
		int extraRun;
		int calculatedExtras;

		// Create object for Chromedriver
		WebDriver driver = new ChromeDriver();
		// Set system property so that code should know the location of Chromedriver
		System.setProperty("webdriver.chrome.driver", "D:\\app\\WebDriver\\chromedriver.exe");
		// create the object of explicit waits
		WebDriverWait expWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// Launch the url
		driver.get("https://www.cricbuzz.com/");
		// Maximize the window
		driver.manage().window().maximize();
		String country = "India vs Sri Lanka";

		expWait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//div[@class='container'] //a[@id='match-dropdown']"))))
				.click();

		List<WebElement> matches = driver
				.findElements(By.xpath("//div[@id='matchmenu'] //span[@class='cb-mm-mtch-tm']"));

		for (int i = 0; i < matches.size(); i++) {
			String match = matches.get(i).getText();

			if (match.equalsIgnoreCase(country)) {
				expWait.until(ExpectedConditions.visibilityOf(matches.get(i))).click();
				System.out.println("Looking for - " + country);
				break;
			} else {
				System.out.println("not found");
			}
		}
		driver.findElement(By.xpath("//a[text()='Scorecard']")).click();
		String TScore = driver
				.findElement(By.xpath(
						"//div[@id='innings_1'] //div[@class='cb-col cb-col-8 text-bold text-black text-right']"))
				.getText();

		totalScore = Integer.parseInt(TScore);
		System.out.println("1st inning total-" + totalScore);

		List<WebElement> runList = driver.findElements(By.xpath(
				"//div[@id='innings_1']/div[1] //div[@class='cb-col cb-col-100 cb-scrd-itms'] //div[@class='cb-col cb-col-8 text-right text-bold']"));

		indivisualSum = 0;
		for (int i = 0; i < runList.size(); i++) {
			indivisualSum = indivisualSum + Integer.parseInt((runList.get(i).getText()));
		}
		System.out.println("Calculated indivisual score is- " + indivisualSum);
		Thread.sleep(3000);
		String extra = driver
				.findElement(By.xpath(
						"//div[@id='innings_1'] //div[@class='cb-col cb-col-8 text-bold cb-text-black text-right']"))
				.getText();
		extraRun = Integer.parseInt(extra);
		System.out.println(extraRun);

		String extrasString = driver
				.findElement(By.xpath("(//div[@id='innings_1'] //div[@class='cb-col-32 cb-col'])[1]")).getText();
		String[] cutString1 = extrasString.split(" ");
		int size = cutString1.length;

		int sum = 0;
		for (int i = 2; i < size; i += 2) {
			String eachString = cutString1[i].trim();
			String[] cutString2 = eachString.split(",");
			String runs = cutString2[0].replace(")", "");

			int intRun = Integer.parseInt(runs);
			sum = sum + intRun;
		}
		System.out.println(sum + "++++");

	}

}
