package org.lesson13.swaglabs.pages.cart;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartElement {

    int id;

    public CartElement(int id) {
        this.id = id;
    }

    public SelenideElement getElement() {
        return $$(".cart_item").get(this.id);
    }

    public SelenideElement getName() {
        return getElement().find(byCssSelector(".inventory_item_name"));
    }

    public SelenideElement getCartQuantity() {
        return getElement().find(byCssSelector(".cart_quantity"));
    }

    public SelenideElement getDesc() {
        return getElement().find(byCssSelector(".inventory_item_desc"));
    }

    public SelenideElement getPrice() {
        return getElement().find(byCssSelector(".inventory_item_price"));
    }

}
