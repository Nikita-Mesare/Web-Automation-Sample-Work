package TestPackage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.annotations.Test;

public class BrokenLinksTestClass {
		
		@Test
		public void brokenLink()
		{	
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			
			WebDriver driver = new ChromeDriver(options);
			
			driver.manage().window().maximize();
			driver.get("https://www.rightmove.co.uk/");
			driver.manage().timeouts().getImplicitWaitTimeout();
			List<WebElement> links = driver.findElements(By.tagName("a"));
			System.out.println("Total links on the web page are :" + links.size());
			for (int i=0; i<links.size();i++)
			{
			    WebElement link = links.get(i);
			    String linkurl = link.getAttribute("href");
			    try {
				    	if (isUrlValid(linkurl)) 
				    	{
						URL urlObj = new URL(linkurl);
						HttpURLConnection httpConnect = (HttpURLConnection)urlObj.openConnection();
						httpConnect.connect();
						int rescode = httpConnect.getResponseCode();
							if(rescode>300)
							{
								System.out.println(linkurl + " ----------- is broken link");
							}
							else
							{
								System.out.println(linkurl + " ----------- is valid link");
							}
				    	
				    	}
				    }
	            catch (IOException e) 
			    {
					e.printStackTrace();
				} 
			}
			driver.close();
		}
		
	    public static boolean isUrlValid(String UrlToCheck)
		{
			try {
				URL obj = new URL(UrlToCheck);
				obj.toURI();
				return true;
			}
			catch(MalformedURLException e)
			{
				return false;
			}
			catch (URISyntaxException e)
			{
				return false;
			}
		}
		
}
