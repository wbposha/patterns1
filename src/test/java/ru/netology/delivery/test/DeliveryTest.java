package ru.netology.delivery.test;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class DeliveryTest {

    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void shouldPlanMeeting () {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Самара");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        String planningDate = generateDate(5, "dd.MM.yyyy");
        $("[data-test-id='date'] input").setValue(generateDate(5, "dd.MM.yyyy"));
        $("[data-test-id='name'] input").setValue("Иван Иванов");
        $("[data-test-id='phone'] input").setValue("+79001488228");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='replan-notification'] span.button__text").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldBe(Condition.text("Встреча успешно запланирована на " + planningDate));
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(generateDate(6, "dd.MM.yyyy"));
        $(".button").click();
        $("[data-test-id='replan-notification'] span.button__text").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldBe(Condition.text("Встреча успешно запланирована на " + generateDate(6, "dd.MM.yyyy")));
    }

    @Test
    public void firstCityTest () {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Samara");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        String planningDate = generateDate(5, "dd.MM.yyyy");
        $("[data-test-id='date'] input").setValue(generateDate(5, "dd.MM.yyyy"));
        $("[data-test-id='name'] input").setValue("Иван Иванов");
        $("[data-test-id='phone'] input").setValue("+79001488228");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='city']")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldBe(Condition.text("Доставка в выбранный город недоступна"));
    }

    @Test
    public void secondCityTest () {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue(" ");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        String planningDate = generateDate(5, "dd.MM.yyyy");
        $("[data-test-id='date'] input").setValue(generateDate(5, "dd.MM.yyyy"));
        $("[data-test-id='name'] input").setValue("Иван Иванов");
        $("[data-test-id='phone'] input").setValue("+79001488228");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='city']")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldBe(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    public void thirdCityTest () {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("123");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        String planningDate = generateDate(5, "dd.MM.yyyy");
        $("[data-test-id='date'] input").setValue(generateDate(5, "dd.MM.yyyy"));
        $("[data-test-id='name'] input").setValue("Иван Иванов");
        $("[data-test-id='phone'] input").setValue("+79001488228");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='city']")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldBe(Condition.text("Доставка в выбранный город недоступна"));
    }

    @Test
    public void firstDateTest () {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Самара");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        String planningDate = generateDate(0, "dd.MM.yyyy");
        $("[data-test-id='date'] input").setValue(generateDate(0, "dd.MM.yyyy"));
        $("[data-test-id='name'] input").setValue("Иван Иванов");
        $("[data-test-id='phone'] input").setValue("+79001488228");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='date']")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldBe(Condition.text("Заказ на выбранную дату невозможен"));
    }

    @Test
    public void secondDateTest () {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Самара");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        String planningDate = generateDate(-3, "dd.MM.yyyy");
        $("[data-test-id='date'] input").setValue(generateDate(-3, "dd.MM.yyyy"));
        $("[data-test-id='name'] input").setValue("Иван Иванов");
        $("[data-test-id='phone'] input").setValue("+79001488228");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='date']")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldBe(Condition.text("Заказ на выбранную дату невозможен"));
    }

    @Test
    public void thirdDateTest () {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Самара");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        String planningDate = generateDate(2, "dd.MM.yyyy");
        $("[data-test-id='date'] input").setValue(generateDate(2, "dd.MM.yyyy"));
        $("[data-test-id='name'] input").setValue("Иван Иванов");
        $("[data-test-id='phone'] input").setValue("+79001488228");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='date']")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldBe(Condition.text("Заказ на выбранную дату невозможен"));
    }

    @Test
    public void firstNameTest () {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Самара");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        String planningDate = generateDate(3, "dd.MM.yyyy");
        $("[data-test-id='date'] input").setValue(generateDate(3, "dd.MM.yyyy"));
        $("[data-test-id='name'] input").setValue("Ivan Ivanov");
        $("[data-test-id='phone'] input").setValue("+79001488228");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='name']")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldBe(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void secondNameTest () {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Самара");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        String planningDate = generateDate(3, "dd.MM.yyyy");
        $("[data-test-id='date'] input").setValue(generateDate(3, "dd.MM.yyyy"));
        $("[data-test-id='name'] input").setValue("");
        $("[data-test-id='phone'] input").setValue("+79001488228");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='name']")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldBe(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    public void thirdNameTest () {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Самара");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        String planningDate = generateDate(3, "dd.MM.yyyy");
        $("[data-test-id='date'] input").setValue(generateDate(3, "dd.MM.yyyy"));
        $("[data-test-id='name'] input").setValue("+-1()&*321");
        $("[data-test-id='phone'] input").setValue("+79001488228");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='name']")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldBe(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void firstPhoneTest () {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Самара");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        String planningDate = generateDate(5, "dd.MM.yyyy");
        $("[data-test-id='date'] input").setValue(generateDate(5, "dd.MM.yyyy"));
        $("[data-test-id='name'] input").setValue("Иван Иванов");
        $("[data-test-id='phone'] input").setValue("+");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='phone']")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldBe(Condition.text("Мобильный телефон Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    //BUG

    @Test
    public void secondPhoneTest () {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Самара");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        String planningDate = generateDate(5, "dd.MM.yyyy");
        $("[data-test-id='date'] input").setValue(generateDate(5, "dd.MM.yyyy"));
        $("[data-test-id='name'] input").setValue("Иван Иванов");
        $("[data-test-id='phone'] input").setValue("+1");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='phone']")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldBe(Condition.text("Мобильный телефон Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    //BUG

    @Test
    public void thirdPhoneTest () {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Самара");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        String planningDate = generateDate(5, "dd.MM.yyyy");
        $("[data-test-id='date'] input").setValue(generateDate(5, "dd.MM.yyyy"));
        $("[data-test-id='name'] input").setValue("Иван Иванов");
        $("[data-test-id='phone'] input").setValue("");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='phone']")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldBe(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    public void firstAgreementTest () {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Самара");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        String planningDate = generateDate(5, "dd.MM.yyyy");
        $("[data-test-id='date'] input").setValue(generateDate(5, "dd.MM.yyyy"));
        $("[data-test-id='name'] input").setValue("Иван Иванов");
        $("[data-test-id='phone'] input").setValue("+79001488228");
        $(".button").click();
        $("[data-test-id='agreement']")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldBe(Condition.text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }

}