# Powerball
Implementation of the lottery game Powerball using Spring Boot (Spring MVC with Thymeleaf), TestNg and Selenium Web Driver
## Prerequisites
* **JDK >= 1.8.0_131**;
## Project structure 
##interface:
Start page *index* provides form with 6 slots for numbers (5 for white balls and one for powerball) and submit button.
And a form for random generated tickets with slot for amount of tickets with submit button as well.
If user will put not unique numbers for 5 white balls it will redirect to *error* page, with message that numbers not unique and automatically redirect after 2 seconds to *index* page.
Otherwise it will redirect to *draw* page with "perform draw" button, and "register another ticket", last one will redirect to start page *index* for ticket registration option.
In the case with performing draw, the user will be redirected to the *result* page with total  draw statistic. 
##Java Classes:
* src\main\java\com.peterholub.powerball\controller\DrawController.java Controller class that provides draw performance, and returning total statistic via Model to result page. 
* src\main\java\com.peterholub.powerball\controller\IndexController.java Controller class that submit values from html forms, saves to repository, and redirect to draw page.
* src\main\java\com.peterholub.powerball\repository\TicketRepository.java Repository class that imitates database. 
*There are 2 methods: 
* *saveTicket* - saves list to *pseudoRepository* field.
* *getTickets* - returns pseudoRepository field.
* src\main\java\com.peterholub.powerball\powerballengine\powerballconstants\PowerballConstants.java  - Class with 9 int constants, which represent winning combo id.
* src\main\java\com.peterholub.powerball\powerballengine\powerball.java - Powerball engine class. With 5 methods:
* *draw* - generates 5 unique numbers for white balls and one for powerball, and return them as List of Integers.
* *winningNumbersIndex* - method has 2 parameters, draw List (with numbers generated from *draw* method ) and ticket List from user input. It compare numbers and return List of numbers indexes that match.
* Goal for this logic, that it can be ease to parse if powerball match.
* *ticketWinningCombo* - method with one parameter (List of indexes  from matched numbers ), that parse what exactly winning combo for ticket. It checks by List method *size* as amount of matched numbers, and collection method *contains* for powerball search.
* For example to find winning combo 4+1, we check size of list (must be 5) and search for powerball (number 5) in list.
* *collectTicketWins* - method with one List parameter, that collect all winning combos from all paced lists. Accepted list paced to *ticketWinningCombo* method and saves winning combo to own list and return it.
* *statistic* - method that accept returned List from *collectTicketWins* method and calculate total amount for each winning combo for all tickets from draw. 
* return array of long values.
##Test
* src\test\java\com.peterholub.powerball\powerballengine\PowerballTest.java - Test class with Unit tests for each method. 
* src\test\java\com.peterholub.powerball\repository\TicketRepository.java - Test class with Unit tests for each method.
* src\test\java\com.peterholub.powerball\IntegrationTest.java - Test class that performs full process, from ticket registration to draw statistic.
* src\test\java\com.peterholub.powerball\FunctionalTest.java - Test class that performs with Selenium Web Driver functional testing.
## Run 

Run script for execution:
```
mvnw spring-boot:run
```
then open localhost:8080 in your favorite browser

Run script for Test
```
mvnw test
```
after app run, needed for functional test run
