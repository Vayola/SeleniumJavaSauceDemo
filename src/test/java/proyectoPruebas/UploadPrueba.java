package proyectoPruebas;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UploadPrueba {
	
	@Test
	public void primeraPruebaSelenium() throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/upload");
		driver.manage().window().maximize();
		
		WebElement upload = driver.findElement(By.id("file-upload"));
		
		upload.sendKeys("C:\\Users\\SHIRLEY\\Documents\\WebElements.pptx");
		
		Thread.sleep(3000);
		
		driver.findElement(By.id("file-submit")).click();
		
		
	}

}
