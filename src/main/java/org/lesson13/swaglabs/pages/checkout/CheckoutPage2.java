package org.lesson13.swaglabs.pages.checkout;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CheckoutPage2 {
    private static final SelenideElement FINISH_BUTTON = $("#finish");
    private static final SelenideElement ITEM_TOTAL = $(".summary_subtotal_label");
    private static final SelenideElement TAX = $(".summary_tax_label");

    @Step("Finish checkout form")
    public void finishCheckout() {
        FINISH_BUTTON.shouldBe(visible).click();
    }

    private static double extractDouble(String input) {
        String regex = "\\$([\\d.]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            String totalString = matcher.group(1);
            return Double.parseDouble(totalString);
        } else {
            throw new IllegalArgumentException("Unable to extract total value from the input string");
        }
    }

    public double getTax() {
        return extractDouble(TAX.text());
    }

    public double getTotal() {
        return extractDouble(ITEM_TOTAL.text());
    }

    public double calculateTaxPercent() {
        return getTax() / getTotal() * 100;
    }
}
