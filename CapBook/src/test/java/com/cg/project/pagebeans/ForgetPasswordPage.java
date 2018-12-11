package com.cg.project.pagebeans;
 
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
 
public class ForgetPasswordPage {
 
 
	@FindBy(how=How.NAME,id="emailId")
	private WebElement emailId;
 
	@FindBy(how=How.NAME,id="status")
	private WebElement status;
 
	@FindBy(how=How.NAME,id="newPassword")
	private WebElement newPassword;
 
	@FindBy(className="btn")
	private WebElement button;
 
	@FindBy(how=How.XPATH,xpath="")
	private WebElement actualErrorMessage;
 
	public ForgetPasswordPage() {
		super();
	}
 
	public String getEmailId() {
		return emailId.getAttribute("value");
	}
 
	public void setEmailId(String username) {
		this.emailId.sendKeys(username);
	}
 
	public String getStatus() {
		return status.getAttribute("value");
	}
 
	public void setStatus(String status) {
		this.status.sendKeys(status);
	}
 
	public String getNewPassword() {
		return newPassword.getAttribute("value");
	}
 
	public void setNewPassword(String newPassword) {
		this.newPassword.sendKeys(newPassword);
	}
	public String getActualErrorMessage() {
		return actualErrorMessage.getText();
	}
 
	public void clickSubmit() {
		button.submit();
	}
 
}