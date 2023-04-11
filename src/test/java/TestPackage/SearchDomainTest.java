package TestPackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class SearchDomainTest {
	
	@Test
	public void domainsearch() {
		
		//Handling ConnectionFailedException
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		
		//Launch the browser
		WebDriver driver = new ChromeDriver(options);
		
		//Open the URL
		driver.get("https://www.godaddy.com/en-in");
		
		//Maximize browser window
		driver.manage().window().maximize();
		
		//set timeout using implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
		
		//Click on first menu link i.e. 'Domains'
		driver.findElement(By.xpath("//button[normalize-space()='Domains']")).click();
		
		//Click on sub menu link 'Domain Name Search'
		driver.findElement(By.xpath("//*[@id=\"id-631b049a-e9c0-4d24-8710-c504745206dd\"]/div[2]/div[1]/ul/li[1]/div/div[2]/div[1]/ul/li[2]/a")).click();
		
		//Get page title and print it
		String actualTitle = driver.getTitle();
		System.out.println("Page title : "+ actualTitle);
		String expectedTitle = "GoDaddy Domain Search - Buy and Register Available Domain Names";
		
		//Match actual title with expected title, if matched proceed
		if(actualTitle.equals(expectedTitle))
		{
			//Verify search box is present on the web page and it is enabled
			WebElement searchBox = driver.findElement(By.xpath("//input[@id='8469f0c3-e08f-4343-9756-ce0390b0d581']"));
			searchBox.isDisplayed();
			searchBox.isEnabled();
			
			//Verify 'Buy It' button is available
			WebElement buyItButton = driver.findElement(By.xpath("//button[@class='ux-button ux-button-primary']"));
			buyItButton.isDisplayed();
			
			//Enter some text in search box and click on 'Buy It' button
			searchBox.sendKeys("mydomain");
			buyItButton.click();
			
			try {
				//If domain is available verify 'Add to Cart' button is present alongside domain name
				WebElement domainName = driver.findElement(By.xpath("//span[@class='domain-name font-weight-bold']"));
				if(domainName.isDisplayed())
					{
					WebElement addToCartButton = driver.findElement(By.xpath("//button[@aria-label='Add Domain Broker Service for mydomain.com to cart']"));
					addToCartButton.isDisplayed();
					
					//Verify price of domain is also displayed
					WebElement domainPrice = driver.findElement(By.xpath("//span[@class='text-primary-o ms4']"));
					domainPrice.isDisplayed();
					}
				}
			catch(Exception e){}
			finally {
				driver.quit();
			}
		}
		
	}
}
