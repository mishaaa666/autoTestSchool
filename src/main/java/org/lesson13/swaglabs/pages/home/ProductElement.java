package org.lesson13.swaglabs.pages.home;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProductElement {
    int id;

    public ProductElement(int id) {
        this.id = id;
    }

//    public String getNameText() {
//        return getName().getText();
//    }

    public SelenideElement getName() {
        return $(".inventory_list > .inventory_item:nth-child(" + this.id + ")  .inventory_item_name");
    }

    public SelenideElement getImage() {
        return $(".inventory_list > .inventory_item:nth-child(" + this.id + ") .inventory_item_img");
    }

    public SelenideElement getDesc() {
        return $(".inventory_list > .inventory_item:nth-child(" + this.id + ") .inventory_item_desc");
    }

    public SelenideElement getPrice() {
        return $(".inventory_list > .inventory_item:nth-child(" + this.id + ") .inventory_item_price");
    }

    public SelenideElement getAddToCartButton() {
        return $(".inventory_list > .inventory_item:nth-child(" + this.id + ") .btn_inventory");
    }

    public void addToCart() {
        getAddToCartButton().shouldBe(visible).click();
    }
}
