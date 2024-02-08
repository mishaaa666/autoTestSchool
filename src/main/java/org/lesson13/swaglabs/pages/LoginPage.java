package org.lesson13.swaglabs.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    private static final SelenideElement USERNAME_FIELD = $(byId("user-name"));
    private static final SelenideElement PASS_FIELD = $(byId("password"));
    private static final SelenideElement LOGIN_BUTTON = $(byId("login-button"));

    public void login(String username, String password) {

        USERNAME_FIELD.shouldBe(visible).setValue(username);
        PASS_FIELD.shouldBe(visible).setValue(password);
        LOGIN_BUTTON.shouldBe(visible).click();
    }
}
