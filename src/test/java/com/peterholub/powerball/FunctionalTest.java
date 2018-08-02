package com.peterholub.powerball;

import com.peterholub.powerball.pages.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.annotations.*;
import org.testng.Assert;



public class FunctionalTest {
   private WebDriver webDriver;
    private IndexPage indexPage;
    private DrawPage drawPage;

    @BeforeTest
    public void setUp() {

        webDriver = new ChromeDriver();
        indexPage = new IndexPage(webDriver);
        drawPage = new DrawPage(webDriver);
        webDriver.get("localhost:8080");
    }

    @Test
    public void ticketFormTest() {

        indexPage.typeFirstNumber("13")
                .typeSecondNumber("15").
                typeThirdNumber("48").
                typeFourthNumber("52").
                typeFifthNumber("60").
                powerBallNumber("12").submitTicketButton();

        Assert.assertEquals("Perform Draw", webDriver.getTitle());
    }

    @Test
    public void ticketGeneratorFormTest() {
        indexPage.typeAmount("200").submitGenerateButton();

        Assert.assertEquals("Perform Draw", webDriver.getTitle());
    }

    @Test
    public void performDrawTest() {

        indexPage.typeFirstNumber("13")
                .typeSecondNumber("15").
                typeThirdNumber("48").
                typeFourthNumber("52").
                typeFifthNumber("60").
                powerBallNumber("12").submitTicketButton();

        drawPage.performDraw();

        org.junit.Assert.assertEquals("Game Result", webDriver.getTitle());
    }

    @Test
    public void registerAnotherTicketTest() {
        indexPage.typeAmount("200").submitGenerateButton();

        drawPage.registerAnotherTicket();

        Assert.assertEquals("Register Ticket", webDriver.getTitle());
    }

    @Test
    public void registerTicketWithError() {
        indexPage.typeFirstNumber("13")
                .typeSecondNumber("13").
                typeThirdNumber("13").
                typeFourthNumber("13").
                typeFifthNumber("13").
                powerBallNumber("12").submitTicketButton();


        Assert.assertEquals("Error", webDriver.getTitle());
    }

    @AfterTest
    public void shutDownSelenium() {
        webDriver.quit();
    }
}
