package demo.qa;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Configuration;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class DemoFormTests {
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "2560x1440";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void checkFormTest() {
        open("/automation-practice-form");

        //Заполнение форм

        $("#firstName").setValue("Konstantin");
        $("#lastName").setValue("Poludnitsyn");
        $("#userEmail").setValue("test@gmail.com");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("8909123456");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("August");
        $(".react-datepicker__year-select").selectOption("2000");
        $(".react-datepicker__day.react-datepicker__day--010").click();
        $("#subjectsInput").setValue("Biology").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();

        $("#uploadPicture").uploadFile(new File("src/test/resources/image.png"));
        $("#currentAddress").setValue("Address");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();

        $("#submit").click();

        //Проверки на формы

        $(".modal-content").shouldBe(visible);
        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive table tbody tr:nth-child(1) td:nth-child(2)").
                shouldHave(text("Konstantin Poludnitsyn"));
        $(".table-responsive table tbody tr:nth-child(2) td:nth-child(2)").
                shouldHave(text("test@gmail.com"));
        $(".table-responsive table tbody tr:nth-child(3) td:nth-child(2)").
                shouldHave(text("Male"));
        $(".table-responsive table tbody tr:nth-child(4) td:nth-child(2)").
                shouldHave(text("8909123456"));
        $(".table-responsive table tbody tr:nth-child(5) td:nth-child(2)").
                shouldHave(text("10 August,2000"));
        $(".table-responsive table tbody tr:nth-child(6) td:nth-child(2)").
                shouldHave(text("Biology"));
        $(".table-responsive table tbody tr:nth-child(7) td:nth-child(2)").
                shouldHave(text("Sports"));
        $(".table-responsive table tbody tr:nth-child(8) td:nth-child(2)").
                shouldHave(text("image.png"));
        $(".table-responsive table tbody tr:nth-child(9) td:nth-child(2)").
                shouldHave(text("Address"));
        $(".table-responsive table tbody tr:nth-child(10) td:nth-child(2)").
                shouldHave(text("NCR Delhi"));
    }
}
