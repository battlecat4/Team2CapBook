package com.cg.project.stepdefinitions;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.cg.project.pagebeans.ChangePasswordPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
 
public class CapbookChangePasswordStepDefinition {
	private WebDriver driver;
	private ChangePasswordPage changePasswordPage;

	@Given("^User visits the Change Password page$")
	public void user_visits_the_Change_Password_page() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "D:\\Backup_5Sept\\Softwares\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.get("https://localhost:4200/login");
		changePasswordPage=PageFactory.initElements(driver, ChangePasswordPage.class);
	}

	@When("^User enters the valid credentials to change password$")
	public void user_enters_the_valid_credentials_to_change_password() throws Throwable {
		changePasswordPage.setEmailId("sara");
		changePasswordPage.setOldPassword("sara");
		changePasswordPage.setNewPassword("roopam");
		changePasswordPage.clickSubmit();
	}

	@Then("^Success message appears$")
	public void success_message_appears() throws Throwable {
		String expectedErrorMessage = "Password successfully changed";
		Assert.assertEquals(expectedErrorMessage, changePasswordPage.getActualErrorMessage());
		driver.close();
	}

	@When("^User enters the invalid credentials to change password$")
	public void user_enters_the_invalid_credentials_to_change_password() throws Throwable {
		changePasswordPage.setEmailId("sara");
		changePasswordPage.setOldPassword("roopam");
		changePasswordPage.setNewPassword("sara");
		changePasswordPage.clickSubmit();
	}

	@Then("^Error message appears$")
	public void error_message_appears() throws Throwable {
		String expectedErrorMessage = "Incorrect credentials";
		Assert.assertEquals(expectedErrorMessage, changePasswordPage.getActualErrorMessage());
		driver.close();
	}
	}
