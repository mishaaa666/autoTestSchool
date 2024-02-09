package org.lesson13.swaglabs.pages.checkout;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CheckoutPage1 {
    private static final SelenideElement FIRSTNAME_FIELD = $("#first-name");
    private static final SelenideElement LASTNAME_FIELD = $("#last-name");
    private static final SelenideElement ZIPCODE_FIELD = $("#postal-code");
    private static final SelenideElement CONTINUE_BUTTON = $("#continue");

    @Step("Submit checkout form")
    public void submitCheckoutForm(String firstName, String lastName, String zipCode) {
        FIRSTNAME_FIELD.shouldBe(visible).setValue(firstName);
        LASTNAME_FIELD.shouldBe(visible).setValue(lastName);
        ZIPCODE_FIELD.shouldBe(visible).setValue(zipCode);
        CONTINUE_BUTTON.shouldBe(visible).click();
    }
}