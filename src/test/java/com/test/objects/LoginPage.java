package com.test.objects;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.Utilitylibraries.OrangeHRM.ScreenShot;

public class LoginPage {

	WebDriver driver;
	WebDriverWait wait;

	// Using Page Factory to locate elements
	@FindBy(name = "username")
	private WebElement usernameField;

	@FindBy(xpath = "//input[@type='password']")
	private WebElement passwordField;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement loginButton;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this); // Initializing elements
	}

	public void login(String username, String password) {
		driver.get("https://opensource-demo.orangehrmlive.com");
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);

		try {
			ScreenShot.screenShotTC(driver, "Login Page");
		} catch (IOException e) {
			System.out.println("Screenshot error: " + e.getMessage());
		}

		loginButton.click();

	}

	public String verification(String expected_url) throws IOException {
		String curr_Url = driver.getCurrentUrl();

		if ((curr_Url.contains("dashboard")) && (curr_Url.equals(expected_url))) {
			ScreenShot.screenShotTC(driver, "DashBoard_Verified");
			return "Verification successful!";
		} else {
			return "Verification failed!";
		}
//		if ((curr_Url.contains("dashboard"))) {
//			return "Verification successful!";
//		} else {
//			return "Verification failed!";
//		}
	}
}
