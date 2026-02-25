package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductPage;

public class LoginTest extends BaseTest {
	
	
	String productName = "Sauce Labs Onesie";
	
	@Test
	public void loginSuccesTest() {	
		LoginPage loginPage = new LoginPage();
		ProductPage productPage = new ProductPage();
		
		loginPage.login("standard_user", "secret_sauce");
		Assert.assertEquals(productPage.getTitle(), "Products", "El titulo de la pagina producto no es el correcto se obtiene " + productPage.getTitle());
	}
	
	@Test
	public void e2eTest() throws InterruptedException {	
		
		LoginPage loginPage = new LoginPage();
		ProductPage productPage = new ProductPage();
		CartPage cartPage = new CartPage();
		CheckoutPage checkoutPage = new CheckoutPage();
		
		loginPage.login("standard_user", "secret_sauce");
		Assert.assertEquals(productPage.getTitle(), "Products", "El titulo de la pagina producto no es el correcto se obtiene " + productPage.getTitle());
		
		String itemSelectedPrice = productPage.getTextItemPrice(productName);
		
		productPage.ClickOnAddToCartButtonSelectedByName(productName);
		Assert.assertEquals(productPage.getTextRemoveButtonSelected(productName), "Remove", "El boton no fue seleccionado correctamente por que no se tiene el texto Remove");
		//Asercion del contador - tarea
		
		productPage.clickOnCartButton();
		Assert.assertEquals(cartPage.getTitle(), "Your Cart", "El titulo de la pagina cart page no es el correcto se obtiene " + productPage.getTitle());
		Assert.assertEquals(cartPage.getCartItemName(), productName, "El nombre del item no es el correcto verificar si se agrego el item al carrito");
		Assert.assertEquals(cartPage.getCartItemPrice(), itemSelectedPrice, "El precio del item no es el correcto");
		
		cartPage.clickOnCheckout();
		
		Assert.assertEquals(checkoutPage.getTitle(), "Checkout: Your Information", "El titulo de la pagina Checkout: Your Information no es el correcto se obtiene " + productPage.getTitle());
		checkoutPage.enterFirstname("Rodrigo");
		checkoutPage.enterLastName("Amurrio");
		checkoutPage.enterZipCode("00000");
		
		
		checkoutPage.clickOnContinue();
		
		Thread.sleep(3000);
		
		
	}
	
	
}
