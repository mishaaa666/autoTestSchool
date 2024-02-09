package org.lesson13.swaglabs.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.lesson13.swaglabs.pages.LoginPage;
import org.lesson13.swaglabs.pages.cart.CartElement;
import org.lesson13.swaglabs.pages.cart.CartPage;
import org.lesson13.swaglabs.pages.checkout.CheckoutComplete;
import org.lesson13.swaglabs.pages.checkout.CheckoutPage1;
import org.lesson13.swaglabs.pages.checkout.CheckoutPage2;
import org.lesson13.swaglabs.pages.home.HomePage;
import org.lesson13.swaglabs.pages.home.ProductElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.open;

@Test(groups = "all")
public class LoginTest {
    private LoginPage loginPage = new LoginPage();
    private HomePage homePage = new HomePage();
    private CheckoutPage1 checkoutPage1 = new CheckoutPage1();
    private CheckoutPage2 checkoutPage2 = new CheckoutPage2();
    private CartPage cartPage = new CartPage();
    private CheckoutComplete checkoutComplete = new CheckoutComplete();
    private ArrayList<String> cartProducts = new ArrayList<>();

    @Step("Add products to cart")
    public void addProductsToCart() {
        double price1 = 7.99;
        double price2 = 9.99;
        ArrayList<Double> neededPrices = new ArrayList<>();
        neededPrices.add(price1);
        neededPrices.add(price2);


        for (ProductElement productElement : homePage.getProducts()) {
            productElement.getPrice();

            var price = Double.parseDouble(productElement.getPrice().text().replace("$", ""));
            if (neededPrices.contains(price)) {
                productElement.addToCart();
                cartProducts.add(productElement.getName().text());
            }
        }

    }

    @BeforeTest
    public void configBrowser() {
        Configuration.baseUrl = "https://www.saucedemo.com/";
        Configuration.browserSize = "1920x1080";
        open("");
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
    }

    @Test
    public void testOrderWorkflow() {
//Зробити успішний логін

        loginPage.login("standard_user", "secret_sauce");

//Покласти в кошик товари з вартістю $7.99 та $9.99
        addProductsToCart();

//Перейти в кошик і зробити перевірку, що саме ці товари додались в кошик
        homePage.goToCart();
        for (CartElement cartElement : cartPage.getCartElements()) {
            Assert.assertTrue(cartProducts.contains(cartElement.getName().text()));
        }

//Перейти на сторінку інформації та заповнити форму
        cartPage.goToCheckout();
        checkoutPage1.submitCheckoutForm("John", "Smith", "01234");

//Перейти на сторінку чекаута й вивести в консоль відсоток податка
        System.out.println("Відсоток податка: " + checkoutPage2.calculateTaxPercent() + "%");

//Перейти на останню сторінку про успішну покупку і зробити перевірку, що відображається текст Thank you for your order!
        checkoutPage2.finishCheckout();
        String checkoutFinalText = "Thank you for your order!";
        Assert.assertEquals(checkoutComplete.COMPLETE_TEXT.text(), checkoutFinalText);

    }

}
