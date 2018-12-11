package com.cg.project.stepdefinitions;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.cg.project.pagebeans.ForgetPasswordPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
 
public class CapbookForgetPasswordStepDefinition {
	private WebDriver driver;
	private ForgetPasswordPage forgetPasswordPage;
	@Given("^User visits the forget password option on Login Page$")
	public void user_visits_the_forget_password_option_on_Login_Page() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.get("");
		forgetPasswordPage=PageFactory.initElements(driver, ForgetPasswordPage.class);
	}
 
	@When("^User enters the valid details$")
	public void user_enters_the_valid_details() throws Throwable {
		forgetPasswordPage.setEmailId("sara");
		forgetPasswordPage.setStatus("corsa	");
		forgetPasswordPage.setNewPassword("kajan");
		forgetPasswordPage.clickSubmit();
	}
 
	@Then("^Display user account$")
	public void display_user_account() throws Throwable {
		String actualTitle = driver.getTitle();
		String expectedTitle = "InfoPage";
		Assert.assertEquals(expectedTitle, actualTitle);
		driver.close();
	}
 
	@When("^User enters the invalid details$")
	public void user_enters_the_invalid_details() throws Throwable {
		forgetPasswordPage.setEmailId("sara");
		forgetPasswordPage.setStatus("alto");
		forgetPasswordPage.setNewPassword("kajan");
		forgetPasswordPage.clickSubmit();
	}
 
	@Then("^Display error message$")
	public void display_error_message() throws Throwable {
		String expectedErrorMessage = "Invalid credentials";
		Assert.assertEquals(expectedErrorMessage, forgetPasswordPage.getActualErrorMessage());
		driver.close();
}
}