import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class Lesson2 {

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    void practiceFormTest() {

        String firstName = "FirstName";
        String lastName = "lastName";

        open("https://demoqa.com/automation-practice-form");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue("aa@aa.aa");
        $("label[for='gender-radio-1']").click();
        $("#userNumber").setValue("0123456789");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionContainingText("June");
        $(".react-datepicker__year-select").selectOptionContainingText("1977");
        $$(".react-datepicker__week div").findBy(text("17")).click();
        $("#subjectsInput").setValue("en");
        $("#react-select-2-option-1").click();
        $("label[for='hobbies-checkbox-1']").click();
        $("label[for='hobbies-checkbox-2']").click();
        $("label[for='hobbies-checkbox-3']").click();
        $("#uploadPicture").uploadFromClasspath("picture.jpg");
        $("#currentAddress").setValue("currentAddress here");
        $("#state").click();
        $("#react-select-3-option-2").click();
        $("#city").click();
        $("#react-select-4-option-1").click();
        $("#submit").click();

        ElementsCollection submitElements = $$(".table tbody tr");
        submitElements.get(0).shouldHave(text("FirstName lastName"));
        submitElements.get(1).shouldHave(text("aa@aa.aa"));
        submitElements.get(2).shouldHave(text("Male"));
        submitElements.get(3).shouldHave(text("0123456789"));
        submitElements.get(4).shouldHave(text("17 June,1977"));
        submitElements.get(5).shouldHave(text("Computer Science"));
        submitElements.get(6).shouldHave(text("Sports, Reading, Music"));
        submitElements.get(7).shouldHave(text("picture.jpg"));
        submitElements.get(8).shouldHave(text("currentAddress here"));
        submitElements.get(9).shouldHave(text("Haryana Panipat"));
        $("#closeLargeModal").click();
    }
}
