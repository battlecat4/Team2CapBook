package com.cg.project.pagebeans;
 
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
 
public class LoginPage {
 
	@FindBy(how=How.ID,id="emailId")
	private WebElement emailId;
 
	@FindBy(how=How.ID,id="password")
	private WebElement password;
 
	@FindBy(className="btn")
	private WebElement button;
 
	@FindBy(how=How.XPATH,xpath="/html/body/app-root/div/app-login/div[2]/div/div[2]/div/font")
	private WebElement actualErrorMessage;
 
	public LoginPage() {}
 
	public String getEmailId() {
		return emailId.getAttribute("value");
	}
 
	public void setEmailId(String username) {
		this.emailId.sendKeys(username);
	}
 
	public String getPassword() {
		return password.getAttribute("value");
	}
 
	public void setPassword(String password) {
		this.password.sendKeys(password);
	}
 
	public String getActualErrorMessage() {
		return actualErrorMessage.getText();
	}
 
	public void clickSignIn() {
		button.submit();
	}
}