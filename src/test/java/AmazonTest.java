import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import org.junit.jupiter.api.Test;

public class AmazonTest {

    @Test
    void selenideSearchTest() {
        // Open Amazon
        open("https://www.amazon.com/");

        // Search book
        $("#twotabsearchtextbox").setValue("book").pressEnter();

        // Check that Book cards are shown
        $("li[class*='carousel-card']").shouldBe(visible);
    }
} 