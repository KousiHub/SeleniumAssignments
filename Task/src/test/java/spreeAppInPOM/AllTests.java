package spreeAppInPOM;

import myOldCode.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AllTests extends BasePage {
    @Test
    public void validateLogInAndOut() throws InterruptedException, IOException {
        HomePage home = new HomePage(driver);
        home.clickLogInLink();
        LoginPage login = new LoginPage(driver);
        login.sendUserName();
        login.sendLoginPassword();
        login.clickLogInButton();
        AccountsPage account = new AccountsPage(driver);
        assertEquals(account.getLoginMessage(),"Logged in successfully");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/logout']")));
        account.clickLogOutButton();
        assertEquals(account.getLogOutMessage(),"Signed out successfully.");
    }

    @DataProvider(name = "prodNames")
    public Object[][] productNameProvider() {
        return new Object[][]{{"Ruby"},{"Apache"}};
    }

    @Test(dataProvider = "prodNames")
    public void validateSearchUsingProductName(String pName){
        SearchPage search = new SearchPage(driver);
        search.searchAndValidateProductsAgainstInput(pName);
        assertTrue(search.searchAndValidateProductsAgainstInput(pName));
    }

    @Test
    public void validateProductsFilter() {
        FilterProductByPrice filter = new FilterProductByPrice(driver);
        filter.filterByPrice(15,18);
        assertTrue(filter.filterByPrice(15,18));
    }

    @Test
    public void validateMugsPriceAfterCheckout() throws InterruptedException {
        MugsOrder mugs = new MugsOrder(driver);
        CheckoutPage check = new CheckoutPage(driver);
        mugs.selectedMugAndCheckout();
        ShoppingCartPage cartPage = new ShoppingCartPage(driver);
        double totalCart = cartPage.getTotalAmountAndCheckout();
        double totalCheckout = check.getOrderTotal();
        assertEquals(totalCart,totalCheckout);
    }

    @Test
    public void validateEmptyCart() throws InterruptedException {
        int quantityRequired = 3;
        String expectedMessage = "Your cart is empty";
        String actualMessage;
        MugsOrder mugs = new MugsOrder(driver);
        ShoppingCartPage cartPage = new ShoppingCartPage(driver);
        mugs.selectedMugAndCheckout();
        cartPage.addQuantity(quantityRequired);
        actualMessage = cartPage.emptyCart();
        assertEquals(expectedMessage,actualMessage);
    }
}
