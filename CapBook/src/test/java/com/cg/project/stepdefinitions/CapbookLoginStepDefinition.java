	package com.cg.project.stepdefinitions;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.cg.project.pagebeans.LoginPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
 
public class CapbookLoginStepDefinition {
 
	private WebDriver driver;
	private LoginPage loginPage;
 
	@Given("^user is in the login page$")
	public void user_is_in_the_login_page() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "D:\\Backup_5Sept\\Softwares\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.get("http://localhost:4200/login");
		loginPage=PageFactory.initElements(driver, LoginPage.class);
	}
 
	@When("^user enters wrong password and correct username$")
	public void user_enters_wrong_password_and_correct_username() throws Throwable {
		loginPage.setEmailId("sara");
		loginPage.setPassword("roopam");
		loginPage.clickSignIn();
	}
 
	@Then("^display \"([^\"]*)\" message in the same page$")
	public void display_message_in_the_same_page(String arg1) throws Throwable {
		String expectedErrorMessage="Invalid Login Credentials";
		Assert.assertEquals(expectedErrorMessage, loginPage.getActualErrorMessage());
		driver.close();
	}
 
	@When("^user enters correct password and correct username$")
	public void user_enters_correct_password_and_correct_username() throws Throwable {
		loginPage.setEmailId("sara");
		loginPage.setPassword("sara");
		loginPage.clickSignIn();
	}
 
	@Then("^displays the account$")
	public void displays_the_account() throws Throwable {
		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:4200/login";
		Assert.assertEquals(actualUrl, expectedUrl);
		driver.close();
	}
}