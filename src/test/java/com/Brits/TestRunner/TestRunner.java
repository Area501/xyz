package com.Brits.TestRunner;


import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)


@CucumberOptions(	features="src/test/resources/feature",
glue={"com.Brits.Step_definition","com.Brits.Hooks"},
tags= "~@error_validation or ~@alpha or @regression",

		plugin = {
				"pretty",
				"html:target/cucumber-html-report.html",
				"json:target/cucumber.json",
				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"

				
		},
		dryRun = false,
		monochrome = true
		
		
		)


public class TestRunner {
//"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
	//"pretty",
//	"html:target/cucumber-html-report.html",
}
