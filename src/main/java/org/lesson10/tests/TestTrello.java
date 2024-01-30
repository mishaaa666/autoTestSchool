package org.lesson10.tests;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestTrello {
    private static final SelenideElement LOGIN_BUTTON = $(byText("Log in"));
    private static final SelenideElement LOGIN_FIELD = $(byId("username"));
    private static final SelenideElement PASS_FIELD = $(byId("password"));
    private static final SelenideElement LOGIN_SUBMIT = $(byId("login-submit"));
    private final SelenideElement WORKSPACES_TITLE = $(".boards-page-section-header-name");
    private final SelenideElement DASHBOARD_BUTTON = $x("//a[@class=\"board-tile\"]");
    private final SelenideElement DASHBOARD_TITLE = $x("//h1[@data-testid=\"board-name-display\"]");
    private final SelenideElement ADD_A_CARD_BUTTON = $x("(//button[@data-testid=\"list-add-card-button\"])[1]");
    private final SelenideElement CARD_TITLE_FIELD = $x("//textarea[@data-testid=\"list-card-composer-textarea\"]");
    private final SelenideElement LAST_CARD_TITLE_FIELD = $("[data-testid=list-card-composer-textarea]");

    private final SelenideElement EDIT_TEST_CARD = $("li[data-testid=\"list-card\"]");
    private final SelenideElement DESCRIPTION_LABEL = $(".js-description-fake-text-area");
    private final SelenideElement DESCRIPTION_INPUT = $("#ak-editor-textarea");
    private final SelenideElement CONFIRMATION_DESC_BUTTON = $(".confirm");
    private final SelenideElement DESCRIPTION_FIELD = $(".js-show-with-desc");
    private final SelenideElement COMMENT_INPUT = $("input[data-testid=\"card-back-new-comment-input-skeleton\"]");
    private final SelenideElement COMMENT_AREA = $x("(//div[@id=\"ak-editor-textarea\"])[2]");
    private final SelenideElement SAVE_COMMENT_BUTTON = $("button[data-testid=\"card-back-comment-save-button\"]");
    private final SelenideElement COMMENT_TEXT = $("div.current-comment > p");
    private final SelenideElement ARCHIVE_CARD_BUTTON = $("a.js-archive-card");
    private final SelenideElement DELETE_CARD_BUTTON = $("a.js-delete-card");
    private final SelenideElement DELETE_CONFIRM_BUTTON = $("input.js-confirm");
    private static boolean isLoggedIn = false;

    public static void login() {
        if (!isLoggedIn) {
            open("");
            LOGIN_BUTTON.shouldBe(visible, Duration.ofSeconds(10));
            LOGIN_BUTTON.click();
            LOGIN_FIELD.setValue("maidan.olesia@gmail.com");
            LOGIN_SUBMIT.click();
            PASS_FIELD.setValue("TesteR135#");
            LOGIN_SUBMIT.click();
            isLoggedIn = true; 
        }
    }

    @BeforeSuite
    public static void setUp() {
        Configuration.baseUrl = "https://trello.com/";
        Configuration.browserSize = "1920x1080";
        login();
    }

    @Test()
    void goToDashboard() {
        WORKSPACES_TITLE.should(visible, Duration.ofSeconds(15));
        DASHBOARD_BUTTON.shouldBe(visible).click();
        DASHBOARD_TITLE.shouldHave(text("Test desk"));
    }


    @Test(dependsOnMethods = "goToDashboard")
    void createTask() {
        ADD_A_CARD_BUTTON.click();
        CARD_TITLE_FIELD.sendKeys("Test Name");
        CARD_TITLE_FIELD.pressEnter();
        LAST_CARD_TITLE_FIELD.pressEscape();
    }

    @Test(dependsOnMethods = "createTask")
    void addDescriptionToTask() {
        sleep(1000);
        EDIT_TEST_CARD.shouldBe(visible).click();
        if (DESCRIPTION_LABEL.exists()) {
            DESCRIPTION_LABEL.shouldBe(visible).click();
        }
        DESCRIPTION_INPUT.shouldBe(visible, Duration.ofSeconds(50)).click();
        DESCRIPTION_INPUT.setValue("Test Desc");
        CONFIRMATION_DESC_BUTTON.click();
        DESCRIPTION_FIELD.shouldHave(text("Test Desc"));
    }

    @Test(dependsOnMethods = "addDescriptionToTask")
    void addCommentToTask() {

        COMMENT_INPUT.shouldBe(visible).click();
        COMMENT_AREA.sendKeys("Test Comment");
        SAVE_COMMENT_BUTTON.click();
        COMMENT_TEXT.shouldHave(text("Test Comment"));
    }

    @Test(dependsOnMethods = "addCommentToTask")
    void deleteTask() {
        ARCHIVE_CARD_BUTTON.click();
        DELETE_CARD_BUTTON.click();
        DELETE_CONFIRM_BUTTON.click();
        EDIT_TEST_CARD.should(disappear);
    }

    @AfterSuite
    public static void close() {
        isLoggedIn = false;
        WebDriverRunner.closeWebDriver();
    }
}

