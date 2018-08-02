package com.peterholub.powerball.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DrawPage {
    private By drawSubmitId = By.id("drawSubmit");
    private By registerAnotherTicketId = By.id("registerAnotherTicket");


    private final WebDriver driver;

    public DrawPage(WebDriver driver) {
        this.driver = driver;
    }

    public void performDraw() {

        driver.findElement(drawSubmitId).submit();
    }

    public void registerAnotherTicket() {
        driver.findElement(registerAnotherTicketId).submit();
    }


}
