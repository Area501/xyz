package com.Brits.PageObjectModel;

import java.io.IOException;
import java.sql.Driver;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.Brits.Helper.Heper;
import com.Brits.HelperBase.HelperBase;
import com.Brits.Hooks.Hooks;

import io.qameta.allure.Allure;

public class brits_Shopping_POM  extends HelperBase{
	
	private WebDriver driver=null;
	private WebDriverWait wt	=	new WebDriverWait(driver,Duration.ofSeconds(10));
	
	public brits_Shopping_POM() {
	this.driver=Hooks.driver;
	PageFactory.initElements(Hooks.driver, this);
	}
	
	//Explicit wait -> user specific wait
	
	//driver.manage().timeout().implicitWait(10,TimeUnits.Seconds);

	
	@FindBy(xpath="//p[text()='Signup for our newsletter']//parent::div//parent::div//preceding-sibling::button")
	private WebElement pop_Up_Close_Button;
	
	@FindBy(xpath ="(//summary[@class='text-with-icon gap-2.5 bold link-faded-reverse'])[1]")
	private WebElement mousover_MensBelt;
	
	@FindBy(xpath="//a[@class='dropdown-menu__item group']//child::span[contains(text(),'Casual')]")
	private WebElement casual_Belt_MOver;
	
	@FindBy(xpath="//span[@class='product-card__title']//child::a")
	private List<WebElement> select_Product;
	
	@FindBy(xpath="//label[@class='color-swatch      rounded-full']//child::span")
	private List<WebElement> select_Colour_List;
	
	//
	
	@FindBy(xpath="//label[@class='block-swatch  ']//child::span")
	private List<WebElement> select_Size_List;
	
	
	@FindBy(xpath = "//a[@class='hidden tap-area sm:block']")
	private WebElement search_Icon_Btn;
	
	@FindBy(xpath="//input[@type='search']")
	private WebElement input_Field_Search;
	
	@FindBy(xpath="//h1[contains(text(),'No result')]")
	private WebElement error_Message_invalid_Input;
	
	

	//
	
	
	public void click_On_SearchIcon() {
		click(search_Icon_Btn," search icon is clicked");
	}
	
	LinkedHashMap<String,String> mp= new LinkedHashMap<>();
	public void send_Input_toSearchField(String input) {
		send_Text(input_Field_Search, input, input+" is given to search");
		Send_Text_Enter(input_Field_Search);
		mp.put("product", input);
	}
	
	/**
	 *soft assert is called verification -> it lets the code to run even after the asserstion fails
	 *Hard assert is called validation -> it terminates the code at the exact line where the assertion fails.
	 * @param file_name
	 * @param Key
	 * @throws IOException
	 * @throws ParseException
	 */
	public void validate_Error_Msg_NoSuchResult(String file_name, String Key) throws IOException, ParseException {
	    boolean displayed = false;
	     // Adjust timeout as needed

	    try {
	        // Wait until the element is visible
	     boolean   displayed1 = error_Message_invalid_Input.isDisplayed();
	     displayed=displayed1;
	    } catch (Exception e) {
	        Allure.step("No error message is displayed.");
	        System.out.println("No such element found.");
	    } finally {
	        System.out.println(displayed);
	        if (displayed) {
	            Allure.step(error_Message_invalid_Input.getText() + " is equals and true");
	            Assert.assertEquals(error_Message_invalid_Input.getText(), Heper.read_JSON(file_name, Key));
	        } else {
//	            System.out.println(mp.get("product"));
//	            System.out.println(select_Product.get(0).getText()+"<<<<<<");
	            for (WebElement x : select_Product) {
	            	String element_Text = x.getText().toLowerCase();
	            	String input_Text = mp.get("product").toLowerCase();
	                if (element_Text.contains(input_Text)) {
	                    Assert.assertTrue(x.isDisplayed(), "Product is displayed.");
	                    Allure.step("We have a result for the product " + mp.get("product"));
	                  //  System.out.println("<<<<<<<<<<<<<<<<>>>>>>>>>>>>");
	                }
	            }
	        }
	    }
	}
	
	public void check_Popup_And_Close() {
	
	wt.until(ExpectedConditions.visibilityOf(pop_Up_Close_Button));
	click(pop_Up_Close_Button, "Pop-up on the dashboard is closed");
	}
	
	public void mouseover_MensBelt() throws InterruptedException {
		Thread.sleep(500);
		click(mousover_MensBelt, "Mens Belt is clicked");
	}
	
	public void select_CasualBelt(String message) {
		click(casual_Belt_MOver, message+" is clicked");
	}
	
	
	public void select_Product_Based_On_Input(String product) throws InterruptedException {
		for(WebElement x : select_Product) {
			Thread.sleep(100);
			if(x.getText().equals(product)) {
				//click(x,product+" is selected");
				JavascriptExecutor jk=(JavascriptExecutor)driver;
				jk.executeScript("arguments[0].click();", x);
			}
			
			
		}
		Thread.sleep(5000);
	}
	
	public void select_Colour(String input) throws InterruptedException {
			for(WebElement x : select_Colour_List) {
				if(x.getText().equals(input)) {
						
					JavascriptExecutor jk=(JavascriptExecutor)driver;
					jk.executeScript("arguments[0].click();", x);
				//	click(x,input+" is selected");
				}
			}
		}
	
//	public void select_Size(String input) throws InterruptedException {
//		for(WebElement x : select_Size_List) {
//			Thread.sleep(1000);
//			if(x.getText().equals(input)) {
//				
//				JavascriptExecutor jk=(JavascriptExecutor)driver;
//				jk.executeScript("arguments[0].click();", x);
//			//	click(x,input+ " size is selected");
//			}
//		}
//	}
	
}

