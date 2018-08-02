package com.peterholub.powerball.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IndexPage {


    private final WebDriver driver;

    public IndexPage(WebDriver driver) {
        this.driver = driver;
    }

    private By firstNumberId = By.id("firstNumber");
    private By secondNumberId = By.id("secondNumber");
    private By thirdNumberId = By.id("thirdNumber");
    private By fourthNumberId = By.id("fourthNumber");
    private By fifthNumberId = By.id("fifthNumber");
    private By powerBallNumberId = By.id("powerBallNumber");
    private By submitTicketButtonId = By.id("submitTicket");
    private By generatorId = By.id("generator");
    private By submitGenerationButton = By.id("generateButton");


    public IndexPage typeFirstNumber(String firstNumber) {

        driver.findElement(firstNumberId).sendKeys(firstNumber);

        return this;
    }

    public IndexPage typeSecondNumber(String secondNumber) {

        driver.findElement(secondNumberId).sendKeys(secondNumber);

        return this;
    }

    public IndexPage typeThirdNumber(String thirdNumber) {

        driver.findElement(thirdNumberId).sendKeys(thirdNumber);

        return this;
    }

    public IndexPage typeFourthNumber(String fourthNumber) {

        driver.findElement(fourthNumberId).sendKeys(fourthNumber);
        return this;
    }


    public IndexPage typeFifthNumber(String fifthNumber) {

        driver.findElement(fifthNumberId).sendKeys(fifthNumber);

        return this;
    }

    public IndexPage powerBallNumber(String powerBallNumber) {

        driver.findElement(powerBallNumberId).sendKeys(powerBallNumber);
        return this;
    }

    public void submitTicketButton() {

        driver.findElement(submitTicketButtonId).submit();

        }

    public IndexPage typeAmount(String amount) {
        driver.findElement(generatorId).sendKeys(amount);
        return this;

    }


    public void submitGenerateButton() {

        driver.findElement(submitGenerationButton).submit();
        }

}
