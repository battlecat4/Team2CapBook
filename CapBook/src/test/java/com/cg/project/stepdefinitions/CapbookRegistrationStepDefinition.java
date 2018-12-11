package com.cg.project.stepdefinitions;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.cg.project.pagebeans.RegistrationPage;

import java.util.List;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
 
public class CapbookRegistrationStepDefinition {
	private WebDriver driver;
	private RegistrationPage registrationPage;
	@Given("^User visits registration page$")
	public void user_visits_registration_page() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "D:\\Backup_5Sept\\Softwares\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.get("https://localhost:4200/registration");
		registrationPage=PageFactory.initElements(driver, RegistrationPage.class);
	}
 
	@When("^User enters form details$")
	public void user_enters_form_details(DataTable userCredentials) throws Throwable {
	  List<List<String>> data= userCredentials.raw();
	  registrationPage.getFirstName().sendKeys(data.get(0).get(0));
	  registrationPage.getLastName().sendKeys(data.get(0).get(1));
	  registrationPage.getEmailId().sendKeys(data.get(0).get(2));
	  registrationPage.getPassword().sendKeys(data.get(0).get(3));
	  registrationPage.getAge().sendKeys(data.get(0).get(4));
	  registrationPage.getGender().sendKeys(data.get(0).get(5));
	  registrationPage.getBirthDate().sendKeys(data.get(0).get(6));
	  registrationPage.getFirstCar().sendKeys(data.get(0).get(7));
	  registrationPage.clickSignIn();
	}
 
	@Then("^Display login page$")
	public void display_login_page() throws Throwable {
		String actualTitle = driver.getTitle();
		String expectedTitle = "LoginPage";
		Assert.assertEquals(expectedTitle, actualTitle);
		driver.close();
	}
 
	@When("^User enters invalid details$")
	public void user_enters_invalid_details(DataTable userCredentials) throws Throwable {
		List<List<String>> data= userCredentials.raw();
//		  driver.findElement(By.id("log")).sendKeys(data.get(1).get(0));
//		  registrationPage.setLastName().sendKeys(data.get(1).get(1));
//		  registrationPage.setEmailId().sendKeys(data.get(1).get(2));
//		  registrationPage.setPassword().sendKeys(data.get(1).get(3));
//		  registrationPage.setAge().sendKeys(data.get(1).get(4));
//		  registrationPage.setGender().sendKeys(data.get(1).get(5));
//		  registrationPage.setBirthDate().sendKeys(data.get(1).get(6));
//		  registrationPage.setFirstCar().sendKeys(data.get(1).get(7));
//		  registrationPage.clickSignIn();
	}
 
	@Then("^Display validation message$")
	public void display_validation_message() throws Throwable {
		String expectedErrorMessage = "Enter emailId";
		Assert.assertEquals(expectedErrorMessage, registrationPage.getActualErrorMessage());
		driver.close();
	}
 
}