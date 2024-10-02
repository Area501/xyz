package com.Brits.Hooks;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.Brits.Constants.Constant;
import com.Brits.Helper.Heper;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;

public class Hooks {
	private static final Hooks ScreenshotUtil = null;
	/**
	 * @before -> options
	 * @after -.> teardown condition
	 * @Beforestep -> Screen shot methods
	 * @AfterStep ->screen shot conditon
	 * Webdriver driver= new Chromedriver (op);
	 * 
	 */
	public static WebDriver driver;
    public static ChromeOptions op;
	public static EdgeOptions ep;
	public static FirefoxOptions fp;
	
	@Before
	public void optional_Inclusion () throws IOException, InterruptedException {
		String browserType = Heper.load_Properties("browser").toLowerCase();
		System.out.println(browserType);
		switch (browserType) {
		case "chrome":
			op=new ChromeOptions();//
			op.addArguments("--start-maximized");
			op.addArguments("--incognito");
			op.addArguments("--disable-popup-blocking");
			break;
		case "edge" :
			ep=new EdgeOptions();
			ep.addArguments("start-maximized");
			ep.addArguments("inprivate");
			ep.addArguments("disable-popup-blocking");
			break;
			
		case "firefox" :
			fp=new FirefoxOptions();
			fp.addArguments("--start-maximized");
			fp.addArguments("-private");
			fp.addPreference("dom.disable_open_during_load", false);
			 break;

		default:
			break;
		}
		
		
	}

public static void launch_Browser() {
	
	String b = Constant.browser;
	
String name_of_Browser = b.toLowerCase();
	try {
		switch (name_of_Browser) {
		case "chrome":
	    WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver(op);
         break;

		case "edge":
        WebDriverManager.edgedriver().setup();
       driver = new EdgeDriver(ep);
        break;
        
		case "firefox":
        WebDriverManager.firefoxdriver().setup();
        driver= new FirefoxDriver(fp);
        break;
        
		case "ie":
        WebDriverManager.iedriver().setup();
        driver = new InternetExplorerDriver();
        break;
        
        default:
         break;
		}
		
	}
	catch(WebDriverException e) {
		e.printStackTrace();
		
	}

	
}

public static void get_Url() {
	driver.get(Constant.url);
}
//=========================================================================


    public static String captureScreenshot(WebDriver driver, String screenshotName) throws IOException {
        // Capture screenshot as file
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // Path where screenshot will be saved
        String destination = System.getProperty("user.dir") + "/target/screenshots/" + screenshotName + ".png";
        File finalDestination = new File(destination);
        // Copy the screenshot to the desired location
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }


public String takeScreenShot() throws IOException {
		  
	  SimpleDateFormat s1 = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	  //2024-09-21 18:15:47.png
	  Date date = new Date();
	  String d = s1.format(date);
	  String destination = System.getProperty("user.dir") + "/target/screenshots/" + d + ".png";
	  TakesScreenshot tk=(TakesScreenshot)this.driver;
	  File Source = tk.getScreenshotAs(OutputType.FILE);
	  File f_destination = new File(destination);
	  FileUtils.copyFile(Source, f_destination);
	  return destination;

}



@AfterStep
public void tearDown(io.cucumber.java.Scenario scenario) {
        try {
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, scenario.getName());
            File screenshotFile = new File(screenshotPath);
            // Attach screenshot to Allure report
            Allure.addAttachment("Screenshot", new ByteArrayInputStream(FileUtils.readFileToByteArray(screenshotFile)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

@After
public void tear_Down() throws InterruptedException {
	Thread.sleep(5000);
	driver.quit();
	
}

}
