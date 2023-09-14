package MyPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;

public class ResoluteAIAssignment {
	
	public static void main(String[] args) throws InterruptedException {
		
		//Handling ConnectionFailedException
		EdgeOptions options = new EdgeOptions();
		options.addArguments("--remote-allow-origins=*");
		
		WebDriver driver = new EdgeDriver(options);
		driver.manage().window().maximize();          // Maximise browser window
		driver.get("https://facegenie-ams-school.web.app/");  //launch the web app
		driver.manage().timeouts().getImplicitWaitTimeout(); //apply implicit wait
		
	/******************** Test 1.Login test case with valid credentials ********************/
		driver.findElement(By.id("email")).sendKeys("testbams@gmail.com"); //Enter valid email address
		driver.findElement(By.id("password")).sendKeys("facegenie");       //Enter valid password
		driver.findElement(By.xpath("//button[@type='submit']")).click();  //Click Log In button
		Thread.sleep(5000);  //As home page's loading time is too low so hard wait is applied, not recommended for real time
		//Successfull login assertion
		String actualURL = driver.getCurrentUrl();
		String expectedURL ="https://facegenie-ams-school.web.app/dashboard/home";
		Assert.assertEquals(actualURL, expectedURL, "URL strings does not match"); //home page url verification after successful login
		
	    /*	if(actualURL.equals(expectedURL))
			System.out.println("Login successfull");   */
	
	/******************** Test 2.Web page title verification test case - for Dashboard module *****************/
		String actualTitle = driver.getTitle();
		String expectedTitle = "Rai-Kpsr-Bams";
		Assert.assertEquals(actualTitle, expectedTitle, "Title does not match");
		
	
	/********************* Test 3.Web page URL verification test case - for Dashboard module ************/
		String actualURL1 = driver.getCurrentUrl();
		String expectedURL1 ="https://facegenie-ams-school.web.app/dashboard/home";
		Assert.assertEquals(actualURL1, expectedURL1, "URL strings does not match for Dashboard module");
		
		
	/********************* Test 4.App logo is present or not verification test case *****************/
		WebElement logo = driver.findElement(By.xpath("//img[@style='width: 80%; height: auto;']")); //locate logo element
		Assert.assertTrue(logo.isDisplayed()); //Verify logo is present or not
		
		/*	boolean flag = driver.findElement(By.xpath("//img[@style='width: 80%; height: auto;']")).isDisplayed();
			if(flag==true)
				System.out.println("Logo present");    */
		
	/********************** Test 5.Logout test case *********************/
		Thread.sleep(10000); //As home page's loading time is too low so hard wait is applied, not recommended for real time
		
		//Click Log Out menu
		driver.findElement(By.xpath("//span[normalize-space()='Log Out']")).click();
		
		//Click OK button on confirm logout dialogue box
		driver.findElement(By.xpath("//button[@style='background-color: rgb(250, 38, 9); color: white; margin: 10px;']")).click();
		
		//Verify successful logout message is displayed at left bottom corner of screen
		boolean flag1 = driver.findElement(By.xpath("//div[@class='MuiAlert-message css-1xsto0d']")).isDisplayed();
		if(flag1==true)
			System.out.println(" ******************* Logged out successfully *****************");
		
		Thread.sleep(10000);
		driver.quit();
}
}