package com.cg.project.pagebeans;
 
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
 
public class RegistrationPage {
 
	@FindBy(how=How.NAME,id="fname")
	private WebElement firstName;
 
	@FindBy(how=How.NAME,id="lname")
	private WebElement lastName;
 
	@FindBy(how=How.NAME,id="emailId")
	private WebElement emailId;
 
	@FindBy(how=How.NAME,id="password")
	private WebElement password;
 
	@FindBy(how=How.NAME,id="age")
	private WebElement age;
 
	@FindBy(how=How.NAME,id="gender")
	private WebElement gender;
 
	@FindBy(how=How.NAME,id="birthDate")
	private WebElement birthDate;
 
	@FindBy(how=How.NAME,id="firstCar")
	private WebElement firstCar;
 
	@FindBy(className="btn")
	private WebElement button;
 
	@FindBy(how=How.XPATH,xpath="//div[@class='container']\"")
	private WebElement actualErrorMessage;
 
	public RegistrationPage() {
		super();
	}
 
	public WebElement getFirstName() {
		return firstName;
	}
 
	public void setFirstName(WebElement firstName) {
		this.firstName = firstName;
	}
 
	public WebElement getLastName() {
		return lastName;
	}
 
	public void setLastName(WebElement lastName) {
		this.lastName = lastName;
	}
 
	public WebElement getEmailId() {
		return emailId;
	}
 
	public void setEmailId(WebElement emailId) {
		this.emailId = emailId;
	}
 
	public WebElement getPassword() {
		return password;
	}
 
	public void setPassword(WebElement password) {
		this.password = password;
	}
 
	public WebElement getAge() {
		return age;
	}
 
	public void setAge(WebElement age) {
		this.age = age;
	}
 
	public WebElement getGender() {
		return gender;
	}
 
	public void setGender(WebElement gender) {
		this.gender = gender;
	}
 
	public WebElement getBirthDate() {
		return birthDate;
	}
 
	public void setBirthDate(WebElement birthDate) {
		this.birthDate = birthDate;
	}
 
	public WebElement getFirstCar() {
		return firstCar;
	}
 
	public void setFirstCar(WebElement firstCar) {
		this.firstCar = firstCar;
	}
 
	public String getActualErrorMessage() {
		return actualErrorMessage.getText();
	}
 
	public void clickSignIn() {
		button.submit();
	}
 
//	@FindBy(how=How.XPATH,xpath="//div[@class='container']\"")
//	private WebElement actualErrorMessage;
 
 
}