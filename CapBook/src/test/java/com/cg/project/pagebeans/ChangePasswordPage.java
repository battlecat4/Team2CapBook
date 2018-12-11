package com.cg.project.pagebeans;
 
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
 
public class ChangePasswordPage {
 
	@FindBy(how=How.NAME,id="emailId")
	private WebElement emailId;
 
	@FindBy(how=How.NAME,id="oldPassword")
	private WebElement oldPassword;
 
	@FindBy(how=How.NAME,id="newPassword")
	private WebElement newPassword;
 
	@FindBy(className="btn")
	private WebElement button;
 
	@FindBy(how=How.XPATH,xpath="")
	private WebElement actualErrorMessage;
 
	public ChangePasswordPage() {
		super();
	}
 
	public String getEmailId() {
		return emailId.getAttribute("value");
	}
 
	public void setEmailId(String username) {
		this.emailId.sendKeys(username);
	}
 
	public String getOldPassword() {
		return oldPassword.getAttribute("value");
	}
 
	public void setOldPassword(String oldPassword) {
		this.oldPassword.sendKeys(oldPassword);
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