package org.lesson13.swaglabs.pages.home;

import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HomePage {

    public SelenideElement GO_TO_CART_BUTTON = $(".shopping_cart_link");


    public ArrayList<ProductElement> getProducts() {
        ArrayList<ProductElement> productElements = new ArrayList<>();


        var elements = $$(".inventory_list > .inventory_item");

        for (int i = 1; i <= elements.size(); i++) {
            productElements.add(new ProductElement(i));
        }
        return productElements;
    }

    public void goToCart() {
        GO_TO_CART_BUTTON.shouldBe(visible).click();
    }
}
