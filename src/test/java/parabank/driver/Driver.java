package parabank.driver;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Driver {
	/*** VARIABLES ***/
	// Driver
	protected WebDriver driver;
	
	/*** METHODS ***/
	// Getter
	public WebDriver getDriver() {
		return driver;
	}
	
	// Driver Configuration
	public void configurate() throws MalformedURLException {
		ChromeOptions options = new ChromeOptions();
		
		String os = System.getProperty("os.name").toLowerCase();
		
		if (os.contains("linux")) {
			System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver"); // GitHub Actions path
            
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
		} else if (os.contains("win")) {
			System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver/154/chromedriver.exe");
            
            options.addArguments("--start-maximized");
		}
		
		this.driver = new ChromeDriver(options);
	}
	
	// Close Connection
	public void finish() {
		if (driver != null) {
			driver.quit();
			
			driver = null;
		}
	}
}