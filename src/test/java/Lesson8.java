import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.AttachmentsHelper.*;

public class Lesson8 {
    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
        addListener("AllureSelenide", new AllureSelenide());
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.startMaximized = true;

        if(System.getProperty("remote_driver") != null) {
            // config for Java + Selenide
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;
            Configuration.remote = System.getProperty("remote_driver");
        }

    }

    @AfterEach
    public void afterEach() {
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser console logs", getConsoleLogs());
        if(System.getProperty("video_storage") != null)
            attachVideo();
        closeWebDriver();
    }

    @Test
    void practiceFormTest() {

        open("https://demoqa.com/automation-practice-form");
        String[] months = {"January", "February", "March", "April", "May", "June", "July","August","September", "October", "November", "December"};
        Faker faker = new Faker();
        String firstName = faker.name().firstName(),
                lastName = faker.name().lastName(),
                email = faker.internet().emailAddress(),
                address = faker.address().fullAddress(),
                phoneNumber = Long.toString(faker.number().numberBetween(1000000000L,9999999999L)),
                birthYear = Integer.toString(faker.number().numberBetween(1900,2100)),
                birthMonth = months[faker.number().numberBetween(0,11)],
                birthDay =  Integer.toString(faker.number().numberBetween(1,19)),
                gender = faker.demographic().sex();

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(phoneNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionContainingText(birthMonth);
        $(".react-datepicker__year-select").selectOptionContainingText(birthYear);
        $$(".react-datepicker__week div").findBy(text(birthDay)).click();
        $("#subjectsInput").setValue("en");
        $("#react-select-2-option-1").click();
        $("label[for='hobbies-checkbox-1']").click();
        $("label[for='hobbies-checkbox-2']").click();
        $("label[for='hobbies-checkbox-3']").click();
        $("#uploadPicture").uploadFromClasspath("picture.jpg");
        $("#currentAddress").setValue(address);
        $("#state").click();
        $("#react-select-3-option-2").click();
        $("#city").click();
        $("#react-select-4-option-1").click();
        $("#submit").click();

        ElementsCollection submitElements = $$(".table tbody tr");
        submitElements.get(0).shouldHave(text(firstName+" "+lastName));
        submitElements.get(1).shouldHave(text(email));
        submitElements.get(2).shouldHave(text(gender));
        submitElements.get(3).shouldHave(text(phoneNumber));
        submitElements.get(4).shouldHave(text(birthDay+" "+birthMonth+","+birthYear));
        submitElements.get(5).shouldHave(text("Computer Science"));
        submitElements.get(6).shouldHave(text("Sports, Reading, Music"));
        submitElements.get(7).shouldHave(text("picture.jpg"));
        submitElements.get(8).shouldHave(text(address));
        submitElements.get(9).shouldHave(text("Haryana Panipat"));
        $("#closeLargeModal").click();
    }
}
