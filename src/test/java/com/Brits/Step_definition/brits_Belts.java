package com.Brits.Step_definition;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import com.Brits.Helper.Heper;
import com.Brits.Hooks.Hooks;
import com.Brits.PageObjectModel.brits_Shopping_POM;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class brits_Belts {
	
	   
	brits_Shopping_POM ref=null;

	@Given("I want to launch the {string}")
	public void i_want_to_launch_the(String browserName)throws Throwable {
	    Heper.return_Browser(browserName);
		 Hooks.launch_Browser();
	}
	@Given("I want to hit the {string}")
	public void i_want_to_hit_the(String url)throws Throwable {
		 Heper.retun_URL(url);
		 Hooks.get_Url();
	}
	//===============================================================================> Launching condition is over
	
	@Given("User close the popup")
	public void user_close_the_popup() {
		ref= new brits_Shopping_POM();
		ref.check_Popup_And_Close();
	}
	
	@When("User mouseover to the mens belt section and selects {string}")
	public void user_mouseover_to_the_mens_belt_section_and_selects(String message) throws Throwable {
		ref.mouseover_MensBelt();
		ref.select_CasualBelt(message);
		}
	@When("user select the model as {string}")
	public void user_select_the_model_as(String msg) throws InterruptedException {
		System.out.println(">>>>>>>>>>>>>>"+msg);
		ref= new brits_Shopping_POM();
		ref.select_Product_Based_On_Input(msg);
	}
	@When("User select the colour as {string} and size as {string}")
	public void user_select_the_colour_as_and_size_as(String colour, String size) throws InterruptedException {
	    ref.select_Colour(colour);
	  // ref.select_Size(size);
	}
	@Then("click on Add to cart")
	public void click_on_add_to_cart() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}


	@When("User mouseover to the {string} and selects {string}")
	public void user_mouseover_to_the_and_selects(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User select the colour as {string} and quantity as {string}")
	public void user_select_the_colour_as_and_quantity_as(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	
	
	@When("user enters the data in the search box {string}")
	public void user_enters_the_data_in_the_search_box(String input) {
	    // Write code here that turns the phrase above into concrete actions
		ref.click_On_SearchIcon();
		ref.send_Input_toSearchField(input);
	}

	@When("user validates the error messages from {string} with key as {string}")
	public void user_validates_the_error_messages_from_with_key_as(String filename, String key) throws IOException, ParseException {
	    // Write code here that turns the phrase above into concrete actions
		ref.validate_Error_Msg_NoSuchResult(filename, key);
	}

	
	



}
