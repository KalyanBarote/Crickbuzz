package cricbuzz;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Crickbuzz {

	String country ;
	int totalScore;
	int indivisualSum2;
	int indivisualSum;
	int extraRun;
	int sumExtra;
	int intRun;
	int sum=0;
	WebDriverWait expWait;
	WebDriver driver;
	
	@Parameters("Browser")
	@Test
	public void setup(String setup) {
		
		if(setup.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
			System.setProperty("webdriver.chrome.driver", "D:\\app\\WebDriver\\chromedriver.exe");
			System.out.println("==========================ChromeTest==============Melbourne Stars vs Brisbane Heat==================");
		}
		else if(setup.equalsIgnoreCase("firefox")){
			driver = new FirefoxDriver();
			System.setProperty("webdriver.gecko.driver", "D:\\app\\WebDriver\\geckodriver.exe");
			System.out.println("==========================FirefoxTest==============MI Cape Town vs Sunrisers Eastern Cape==================");
		}
		else if(setup.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
			System.setProperty("webdriver.edge.driver", "D:\\app\\WebDriver\\edgedriver.exe");
			System.out.println("==========================EdgeTest==============Paarl Royals vs Durban Super Giants==================");
		}
		expWait = new WebDriverWait(driver, Duration.ofSeconds(8));		
	}

	@Test(priority = 1)
	public void launchUrl() throws InterruptedException {
		// Launch the url
		driver.get("https://www.cricbuzz.com/");
		// Maximize the window
		driver.manage().window().maximize();
	}
	@Parameters("Country")
	@Test(priority = 2)
	public void allMatches(String Country) throws InterruptedException {

		expWait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//div[@class='container'] //a[@id='match-dropdown']")))).click();
		if(Country.equalsIgnoreCase("Melbourne Stars vs Brisbane Heat")) {
			country = "Melbourne Stars vs Brisbane Heat";
		}
		else if(Country.equalsIgnoreCase("MI Cape Town vs Sunrisers Eastern Cape")) {
			country="MI Cape Town vs Sunrisers Eastern Cape";
		}
		else if(Country.equalsIgnoreCase("Paarl Royals vs Durban Super Giants")) {
			country="Paarl Royals vs Durban Super Giants";
		} 
				
		List<WebElement> matches = driver
				.findElements(By.xpath("//div[@id='matchmenu'] //span[@class='cb-mm-mtch-tm']"));
		
		for (int i = 0; i < matches.size(); i++) {
			String match = matches.get(i).getText();

			if (match.equalsIgnoreCase(country)) {
				expWait.until(ExpectedConditions.visibilityOf(matches.get(i))).click();
				System.out.println("Looking for    - " + country);
				break;
			} else {
				System.out.println("not found");
			}
		}
	}
	
	@Test(priority = 3)
	public void scoreCard() throws InterruptedException {
		
		driver.findElement(By.xpath("//a[text()='Scorecard']")).click();
		String TScore = driver
				.findElement(By.xpath(
						"//div[@id='innings_1'] //div[@class='cb-col cb-col-8 text-bold text-black text-right']")).getText();
				
		totalScore = Integer.parseInt(TScore);
		System.out.println("1st inning extracted total  -" + totalScore);

		List<WebElement> runList = driver.findElements(By.xpath(
				"//div[@id='innings_1']/div[1] //div[@class='cb-col cb-col-100 cb-scrd-itms'] //div[@class='cb-col cb-col-8 text-right text-bold']"));
				
		indivisualSum = 0;
		for (int i = 0; i < runList.size(); i++) {
			indivisualSum = indivisualSum + Integer.parseInt((runList.get(i).getText()));
		}
		System.out.println("batsman's total score is    -"+indivisualSum);
		
	}
		
		@Test(priority = 4)
		public void extras() throws InterruptedException {
		
	String extra=driver.findElement(By.xpath("//div[@id='innings_1'] //div[@class='cb-col cb-col-8 text-bold cb-text-black text-right']")).getText();
		extraRun=Integer.parseInt(extra);
		System.out.println("Extra run extracted directy - "+extraRun); //15

		
		String extrasString=driver.findElement(By.xpath("(//div[@id='innings_1'] //div[@class='cb-col-32 cb-col'])[1]")).getText();

		 String[] cutString1=extrasString.split(" ");
		 int size=cutString1.length;
		 
		sumExtra=0;
		for(int i=2;i<size;i += 2) {			
		String eachString=cutString1[i].trim();	
		
		String[] cutString2=eachString.split(",");		
		String runs=cutString2[0].replace(")", "");
		
		intRun=Integer.parseInt(runs);		
		sumExtra=sumExtra+intRun;				
		}
		System.out.println("Extra run's calculated      - "+sumExtra);	
		
		Assert.assertEquals(extraRun, sumExtra); 
		
		int totals=indivisualSum+sumExtra;
		System.out.println("batsman's & extra's total is -"+totals);
		Assert.assertEquals(totalScore, totals); 
		
		Thread.sleep(3000);
		driver.quit();	
		Thread.sleep(2000);
		
	}
}
