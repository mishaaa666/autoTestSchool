package org.lesson13.swaglabs.test;

import com.codeborne.selenide.Configuration;
import org.lesson13.swaglabs.pages.LoginPage;
import org.lesson13.swaglabs.pages.cart.CartElement;
import org.lesson13.swaglabs.pages.cart.CartPage;
import org.lesson13.swaglabs.pages.checkout.CheckoutComplete;
import org.lesson13.swaglabs.pages.checkout.CheckoutPage1;
import org.lesson13.swaglabs.pages.checkout.CheckoutPage2;
import org.lesson13.swaglabs.pages.home.HomePage;
import org.lesson13.swaglabs.pages.home.ProductElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;


public class LoginTest {
    @Test
    public static void setUp() {

//Зробити успішний логін
        Configuration.baseUrl = "https://www.saucedemo.com/";
        Configuration.browserSize = "1920x1080";


        LoginPage loginPage = new LoginPage();

        HomePage homePage = loginPage.login("standard_user", "secret_sauce");

//Покласти в кошик товари з вартістю $7.99 та $9.99
        double price1 = 7.99;
        double price2 = 9.99;
        ArrayList<Double> neededPrices = new ArrayList<>();
        neededPrices.add(price1);
        neededPrices.add(price2);


        ArrayList<String> addedProducts = new ArrayList<>();

        for (ProductElement productElement : homePage.getProducts()) {
            productElement.getPrice();

            var price = Double.parseDouble(productElement.getPrice().text().replace("$", ""));
            if (neededPrices.contains(price)) {
                productElement.addToCart();
                addedProducts.add(productElement.getName().text());
            }
        }

//Перейти в кошик і зробити перевірку, що саме ці товари додались в кошик
        CartPage cartPage = homePage.goToCart();
        for (CartElement cartElement : cartPage.getCartElements()) {
            Assert.assertTrue(addedProducts.contains(cartElement.getName().text()));
        }

//Перейти на сторінку інформації та заповнити форму
        CheckoutPage1 checkoutPage1 = cartPage.goToCheckout();
        CheckoutPage2 checkoutPage2 = checkoutPage1.submitCheckoutForm("John", "Smith", "01234");

//Перейти на сторінку чекаута й вивести в консоль відсоток податка
        System.out.println("Відсоток податка: " + checkoutPage2.calculateTaxPercent() + "%");

//Перейти на останню сторінку про успішну покупку і зробити перевірку, що відображається текст Thank you for your order!
        CheckoutComplete checkoutComplete = checkoutPage2.finishCheckout();
        String checkoutFinalText = "Thank you for your order!";
        Assert.assertEquals(checkoutComplete.COMPLETE_TEXT.text(), checkoutFinalText);

    }

}
