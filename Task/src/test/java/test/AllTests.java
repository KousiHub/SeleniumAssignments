package test;

import myOldCode.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import spreeAppInPOM.*;

import java.io.IOException;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AllTests extends BasePage {

    @Test(priority = 0)
    public void validateLogInAndOut() throws InterruptedException, IOException {
        HomePage home = new HomePage(driver);
        home.clickLogInLink();
        LoginPage login = new LoginPage(driver);
        login.sendUserName();
        login.sendLoginPassword();
        login.clickLogInButton();
        AccountsPage account = new AccountsPage(driver);
        System.out.println("Log In");
        assertEquals(account.getLoginMessage(),"Logged in successfully");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/logout']")));
        account.clickLogOutButton();
        System.out.println("Log Out");
        assertEquals(account.getLogOutMessage(),"Signed out successfully.");
    }

    @Test(priority = 9)
    public void validateLogInForAllMethods() throws InterruptedException, IOException {
        HomePage home = new HomePage(driver);
        home.clickLogInLink();
        LoginPage login = new LoginPage(driver);
        login.sendUserName();
        login.sendLoginPassword();
        login.clickLogInButton();
        System.out.println("Log In Again");
    }

    @DataProvider(name = "prodNames")
    public Object[][] productNameProvider() {
        return new Object[][]{{"Ruby"},{"Apache"}};
    }

    @Test(dataProvider = "prodNames", priority = 1)
    public void validateSearchUsingProductName(String pName){
        SearchPage search = new SearchPage(driver);
        search.searchAndValidateProductsAgainstInput(pName);
        System.out.println("Search using: "+pName);
        assertTrue(search.searchAndValidateProductsAgainstInput(pName));
    }

    @Test(priority = 2)
    public void validateProductsFilter() {
        FilterProductByPrice filter = new FilterProductByPrice(driver);
        filter.filterByPrice(15,18);
        System.out.println("Filter using price");
        assertTrue(filter.filterByPrice(15,18));
    }

    @Test(priority = 3, dependsOnMethods = {"validateLogInForAllMethods"})
    public void validateMugsPriceAfterCheckout() throws InterruptedException {
        MugsOrder mugs = new MugsOrder(driver);
        CheckoutPage check = new CheckoutPage(driver);
        mugs.selectedMugAndCheckout();
        ShoppingCartPage cartPage = new ShoppingCartPage(driver);
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='lead']")));
        double totalCart = cartPage.getTotalAmountAndCheckout();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("summary-order-total")));
        double totalCheckout = check.getOrderTotal();
        System.out.println("Mugs price on Checkout");
        assertEquals(totalCart,totalCheckout);
    }

    @Test(priority = 4, dependsOnMethods = {"validateLogInForAllMethods"})
    public void validateEmptyCart() throws InterruptedException {
        int quantityRequired = 3;
        String expectedMessage = "Your cart is empty";
        String actualMessage;
        MugsOrder mugs = new MugsOrder(driver);
        ShoppingCartPage cartPage = new ShoppingCartPage(driver);
        mugs.selectedMugAndCheckout();
        cartPage.addQuantity(quantityRequired);
        actualMessage = cartPage.emptyCart();
        System.out.println("Empty Cart");
        assertEquals(expectedMessage,actualMessage);
    }

    @Test(priority = 5, dependsOnMethods = {"validateLogInForAllMethods"})
    public void validateLogOutFinally() throws InterruptedException, IOException {
        AccountsPage account = new AccountsPage(driver);
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/logout']")));
        account.clickLogOutButton();
        System.out.println("Log Out Finally");
        assertEquals(account.getLogOutMessage(),"Signed out successfully.");
    }
}
