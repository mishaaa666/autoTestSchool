package org.lesson13.swaglabs.pages.cart;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.lesson13.swaglabs.pages.checkout.CheckoutPage1;
import org.openqa.selenium.By;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage {
    public ArrayList<CartElement> getCartElements() {
        ArrayList<CartElement> cartElements = new ArrayList<>();


        var elements = $$(".cart_item");

        for (int i = 0; i < elements.size(); i++) {
            cartElements.add(new CartElement(i));
        }
        return cartElements;
    }

    public SelenideElement CHECKOUT_BUTTON = $(By.id("checkout"));

    @Step("Go to checkout page")
    public void goToCheckout(){
        CHECKOUT_BUTTON.shouldBe(visible).click();
    }
}