package com.Brits.HelperBase;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.Brits.Hooks.Hooks;

import io.qameta.allure.Allure;

public class HelperBase {
	/**
	 * The purpose of theHelper base is create the reusable selenium methods with less complexity.
	 * 
	 * --> Feature file
	 * --> Step defenition
	 * --> Page Object Model
	 * --> test Runner
	 */
	
	Select sel;
	
	 String name;
	 
	 
  
  
 
  
  public void click(WebElement element, String message) {
	  try {
		  element.click();
		  Allure.step(message);
	  }
	  catch (ElementClickInterceptedException e) {
		System.out.println("Element couldn't able to be clicked please try with JS ");
	}
	  
  }
  
  public void mouseOver(WebElement e1, String message) {
	  Actions  ref =new Actions(Hooks.driver);
	ref.moveToElement(e1).perform();
	Allure.step(message);
  }
  
  public void click(WebElement element) {
	  try {
		  element.click();
		  Allure.step(element.getText()+" is clicked ");
	  }
	  catch (ElementClickInterceptedException e) {
	//	System.out.println("Element couldn't able to be clicked please try with JS");
		Allure.step("Element couldn't able to be clicked please try with JS");
	} 
  }
  
  public void send_Text(WebElement e1, String Input, String message) {
	  e1.sendKeys(Input);
	 // System.out.println(message);
	  Allure.step(message);
	  
	  
  }
  
  public void Send_Text_Enter(WebElement e1) {
	  e1.sendKeys(Keys.ENTER);
  }
                                 
  public void select_from_DropDown_Index(WebElement e1,int index) {
     sel=new Select(e1);
     sel.selectByIndex(index);
  }
                                  //select[@id='Select0'], US
  public void select_from_DropDown_Value(WebElement e1,String value) {
	     sel=new Select(e1);
	     sel.selectByValue(value);
	  }
                                 //select[@id='Select0'], United States
  public void select_from_DropDown_VisibleText(WebElement e1,String Text) {
	     sel=new Select(e1);
	     sel.selectByVisibleText(Text);
	  }
                                      
  public  List<WebElement> select_All(WebElement e1) {
	  sel=new Select(e1);
	  List<WebElement> op = sel.getOptions();
	  return op;
  }

  
  
}
