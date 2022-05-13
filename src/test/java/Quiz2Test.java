import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.*;

class Quiz2Test {
    public static void main(String[] args) throws InterruptedException {
        /**
         * Please uncomment one test at a time
         */
        //firstTest();
        //secondTest();
        //thirdTest();
    }

    public static WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

        return new ChromeDriver();
    }

    public static void firstTest() {
        // did this first step via selenium (to remember some stuff from there), others with selenide
        WebDriver driver = getDriver();
        driver.get("https://demoqa.com/progress-bar");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        By startStopButton = By.id("startStopButton");
        wait.until(ExpectedConditions.presenceOfElementLocated(startStopButton));
        driver.findElement(By.id("startStopButton")).click();
        By resetButton = By.id("resetButton");
        wait.until(ExpectedConditions.presenceOfElementLocated(resetButton));
        System.out.print("100%");
    }

    public static void secondTest() {
        Configuration.holdBrowserOpen = true;
        SoftAssert softAssert = new SoftAssert();
        open("http://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html");

        $(By.id("dropdowm-menu-1")).selectOptionByValue("sql");
        softAssert.assertEquals($("#dropdowm-menu-1").getText(),"SQL");
        $$("input[type='checkbox']").forEach(el -> { if (!Objects.equals(el.getValue(), "option-3")) el.click(); });
        $("input[value='purple']").click();
        // purple does not exist in dropdown, only Orange was disabled so i grabbed it
        softAssert.assertEquals($("option[value='orange']").getAttribute("disabled"),"SQL");
    }

    public static void thirdTest() {
        Configuration.holdBrowserOpen = true;
        open("http://the-internet.herokuapp.com/iframe");
        switchTo().frame(0);
        $("body > p").setValue("Here Goes");
        switchTo().parentFrame();
        $("button[title='Align center']").click();
    }
}