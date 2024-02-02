package org.lesson13.swaglabs.pages.checkout;

import com.codeborne.selenide.SelenideElement;
import org.lesson13.swaglabs.pages.home.HomePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CheckoutPage1 {
    private static final SelenideElement FIRSTNAME_FIELD = $("#first-name");
    private static final SelenideElement LASTNAME_FIELD = $("#last-name");
    private static final SelenideElement ZIPCODE_FIELD = $("#postal-code");
    private static final SelenideElement CONTINUE_BUTTON = $("#continue");

    public CheckoutPage2 submitCheckoutForm(String firstName, String lastName, String zipCode) {
        FIRSTNAME_FIELD.shouldBe(visible);
        FIRSTNAME_FIELD.setValue(firstName);
        LASTNAME_FIELD.shouldBe(visible);
        LASTNAME_FIELD.setValue(lastName);
        ZIPCODE_FIELD.shouldBe(visible);
        ZIPCODE_FIELD.setValue(zipCode);
        CONTINUE_BUTTON.shouldBe(visible);
        CONTINUE_BUTTON.click();
        return new CheckoutPage2();
    }
}