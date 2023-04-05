package TestPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCalculatedEMI {
	
	@Test
	public void testEmi() {
	
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://emicalculator.net/");
		
		WebElement amount = driver.findElement(By.cssSelector("#loanamountslider >span"));
		WebElement rate = driver.findElement(By.cssSelector("#loaninterestslider >span"));
		WebElement tenure = driver.findElement(By.cssSelector("#loantermslider >span"));
		
		Actions act = new Actions(driver);
		act.dragAndDropBy(amount, 83, 0).build().perform();
		act.dragAndDropBy(rate, 88, 0).build().perform();
		act.dragAndDropBy(tenure, -111, 0).build().perform();
		
		WebElement result = driver.findElement(By.cssSelector("#emiamount >p >span"));
		String text = result.getText();
		Assert.assertEquals("86,381", text);
		
		driver.quit();
	}


}
