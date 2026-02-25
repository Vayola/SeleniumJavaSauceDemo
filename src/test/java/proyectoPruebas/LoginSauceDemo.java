package proyectoPruebas;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginSauceDemo {
	
	
	
	
	@Test
	public void primeraPruebaSelenium() throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		
		WebElement username =  driver.findElement(By.id("user-name"));
		WebElement password =  driver.findElement(By.id("password"));
		WebElement buttonLogin =  driver.findElement(By.id("login-button"));

		username.sendKeys("standard_users");
		username.clear();
		username.sendKeys("standard_user");
		password.sendKeys("secret_sauce");
		buttonLogin.click();
		
		WebElement productTitle = driver.findElement(By.xpath("//span[text()='Products']"));
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(productTitle));
		
		
		driver.close();
		
	}

}
