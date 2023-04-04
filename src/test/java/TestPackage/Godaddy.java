package TestPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class Godaddy {
		
		WebDriver driver = new EdgeDriver();
		String expectedTitle = "Domain Names, Websites, Hosting & Online Marketing Tools - GoDaddy IN";
		
		@BeforeTest
		public void launch() {
			driver.manage().window().maximize();
			driver.get("https://www.godaddy.com/");
		}
		
		@AfterTest
		public void teardown() {	
		driver.quit(); 
		}

		
		
		@Test
		public void verifyTitle() {
			String expectedTitle = "Domain Names, Websites, Hosting & Online Marketing Tools - GoDaddy IN";
			String actualTitle = driver.getTitle();
			Assert.assertEquals(actualTitle, expectedTitle, "Title does not match");
		}
		
		
		@Test
		public void verifyURL() {
			String expectedURL = "https://www.godaddy.com/en-in";
			String actualURL = driver.getCurrentUrl();
			Assert.assertEquals(actualURL, expectedURL, "URL does not match");
		}

		@Test
		public void getPageSource() {
			System.out.println(driver.getPageSource());
		}
	
}
	


