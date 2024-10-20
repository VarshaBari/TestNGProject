package testcases;

import org.testng.annotations.AfterMethod;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;
	public XSSFWorkbook wbook;
	public XSSFSheet sheet;

	private ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

	public void SetDriver(WebDriver driver) {
		threadLocalDriver.set(driver);
	}

	public WebDriver GetDriver() {

		return threadLocalDriver.get();

	}

	@BeforeMethod(alwaysRun = true)
	public void setUpDriver() throws MalformedURLException {

//String browser = System.getProperty("Browser");
		String browser = "chrome";

		if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.chromedriver().clearDriverCache().setup();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("remote-chrome")) {

			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setPlatform(Platform.LINUX);
			cap.setBrowserName("chrome");

			URL hub = new URL("http://localhost:4444/");
			driver = new RemoteWebDriver(hub, cap);

		} else {
			WebDriverManager.chromedriver().clearDriverCache().setup();
			driver = new ChromeDriver();
		}

		SetDriver(driver);
		GetDriver().get("https://simplilearn.com/");
		GetDriver().manage().window().maximize();
		GetDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	
		

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		GetDriver().quit();
	}

	@BeforeTest
	public void SetupExcel() throws IOException {

	}

	@AfterTest
	public void CloseExcel() throws IOException {

		// wbook.close();

	}

}
