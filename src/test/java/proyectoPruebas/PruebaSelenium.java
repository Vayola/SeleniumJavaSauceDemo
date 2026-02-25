package proyectoPruebas;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;

public class PruebaSelenium {
	
	@Test
	public void primeraPruebaSelenium() {
		
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
		driver.navigate().refresh();
		driver.manage().window().minimize();
		driver.manage().window().maximize();
		driver.close();
		
	}

}
