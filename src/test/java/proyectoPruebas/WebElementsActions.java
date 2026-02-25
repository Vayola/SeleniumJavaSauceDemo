package proyectoPruebas;

import java.awt.Desktop.Action;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WebElementsActions {
	
	WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	By radioButton = By.cssSelector("[value='radio2']");
	By autoSuggest = By.id("autocomplete");
	By suggestResults = By.xpath("//ul[@id='ui-id-1']/li");
	By suggestListResult = By.id("ui-id-1");
	By dropdownSelect = By.id("dropdown-class-example");
	By checkbox2 = By.id("checkBoxOption2");
	By inputName = By.id("name");
	By alertButton = By.id("alertbtn");
	By buttonHover = By.id("mousehover");
	By topOptionHover = By.cssSelector("[href='#top']");
	By displayedText = By.id("displayed-text");
	By hideButton = By.id("hide-textbox");
	By showButton = By.id("show-textbox");
	
	@BeforeMethod
	public void setUp() {
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		
		
	}
	
	@Test
	public void radioTest() {
		
		WebElement radioButton2 = driver.findElement(radioButton);
		
		wait.until(ExpectedConditions.visibilityOf(radioButton2));
		radioButton2.click();
		Assert.assertTrue(radioButton2.isSelected(), "El radio button 2 no fue seleccionado");		
	}
	
	
	@Test
	public void autoCompleteTest() {
		
		WebElement autoComplete = driver.findElement(autoSuggest);
		autoComplete.sendKeys("Uni");
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(suggestListResult)));
		
		List<WebElement> suggestedList = driver.findElements(suggestResults);
		
		for(WebElement option : suggestedList) {
			
			if(option.getText().equals("Tunisia")) {
				option.click();
				break;
			}
		}
		
	}
	
	@Test
	public void dropdownTest() throws InterruptedException  {
		
		WebElement dropdown = driver.findElement(dropdownSelect);
		
		Select select = new Select(dropdown);
		select.selectByVisibleText("Option3");
		Thread.sleep(3000);
		select.selectByValue("option1");
		Thread.sleep(3000);
		
	}
	
	@Test
	public void checkBoxTest() throws InterruptedException  {
		
		WebElement checkbox = driver.findElement(checkbox2);
		checkbox.click();
		Assert.assertTrue(checkbox.isSelected());
		Thread.sleep(3000);
		checkbox.click();
		Assert.assertFalse(checkbox.isSelected());
		Thread.sleep(3000);
		
	}
	
	@Test
	public void alertTest() throws InterruptedException  {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement alertName = driver.findElement(inputName);
		WebElement alertNameButton = driver.findElement(alertButton);
		
		alertName.sendKeys("Rodrigo");
		alertNameButton.click();
		
		wait.until(ExpectedConditions.alertIsPresent());
		
		Alert alert = driver.switchTo().alert();
	    Assert.assertTrue(alert.getText().contains("Rodrigo"));
	    alert.accept();
		
	}
	
	@Test
	public void mouseHover() throws InterruptedException  {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement mouseHoverButton = driver.findElement(buttonHover);
		WebElement topOption = driver.findElement(topOptionHover);
		
		Actions action =  new Actions(driver);
		
		action.moveToElement(mouseHoverButton);
		action.moveToElement(topOption);
		action.click().build().perform();
		
		wait.until(ExpectedConditions.urlContains("#top"));
		Assert.assertTrue(driver.getCurrentUrl().contains("#top"), "The url doesn't change");
		
		Thread.sleep(3000);
		
	}
	
	@Test
	public void displayedElement() throws InterruptedException  {
		
		driver.findElement(displayedText).sendKeys("Mensaje");
		driver.findElement(hideButton).click();
		Assert.assertFalse(driver.findElement(displayedText).isDisplayed());
		driver.findElement(showButton).click();
		Assert.assertTrue(driver.findElement(displayedText).isDisplayed());
		
		Thread.sleep(3000);
		
	}
	
	
	@Test
	public void newTapWindows() throws InterruptedException  {
		
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("https://www.epochconverter.com/");
		Thread.sleep(3000);
		ArrayList<String> allWindows = new ArrayList<>(driver.getWindowHandles());
		
		Assert.assertEquals(allWindows.size(), 2, "Deberian ser 2 ventanas abiertas");
		driver.switchTo().window(allWindows.get(0));
		Thread.sleep(3000);
		driver.switchTo().window(allWindows.get(1));
		Thread.sleep(3000);
		
		
		
	}
	
	@Test
	public void newWindows() throws InterruptedException  {
		
		driver.switchTo().newWindow(WindowType.WINDOW);
		driver.get("https://www.epochconverter.com/");
		Thread.sleep(3000);
		ArrayList<String> allWindows = new ArrayList<>(driver.getWindowHandles());
		
		Assert.assertEquals(allWindows.size(), 2, "Deberian ser 2 ventanas abiertas");
		driver.switchTo().window(allWindows.get(0));
		Thread.sleep(3000);
		driver.switchTo().window(allWindows.get(1));
		Thread.sleep(3000);
		
	}
	
	@Test
	public void tableHandled() throws InterruptedException  {
		
		List<WebElement> filas = driver.findElements(By.xpath("//table[@name='courses']/tbody/tr"));
		
		System.out.println("Cantidad de filas: " + filas.size());
		
		
		List<WebElement> columnas = driver.findElements(By.xpath("//table[@name='courses']/tbody/tr[1]/th"));
		
		System.out.println("Cantidad de columnas: " + columnas.size());
		
		
	    
		for(int i = 2; i < filas.size(); i++) {
			
			String course = driver.findElement(By.xpath("//table[@name='courses']/tbody/tr["+i+"]/td[2]")).getText();
			
			if(course.contains("WebServices")) {
				System.out.println("Encotrado");
				System.out.println("Posicion de la fila: " + i);
				break;
			}
		}
		
		
	}
	
	
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
	

}
