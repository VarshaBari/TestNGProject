package testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;

public class LoginTest extends BaseClass {

	@Test
	public void TC02_LoginFailureTest() {

		LoginPage lp = new LoginPage();
		lp.LoginFunction("abc@xyz.com", "Abcd@1234");

		lp.ValidateErrorMsg("The email or password you have entered is invalid.");


	}
	
	@Test
	public void TC02_LoginSuccessTest() {
		
		LoginPage lp = new LoginPage();
		lp.LoginFunction("abcd@gmail.com", "ancd@2314");

		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());

	}

}
