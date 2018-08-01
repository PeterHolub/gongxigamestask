package com.peterholub.powerball;
import com.peterholub.powerball.powerballengine.Powerball;
import com.peterholub.powerball.repository.TicketRepository;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.*;

public class IntegrationTest {

    private Powerball powerball;
    private TicketRepository ticketRepository;

    @BeforeTest
    public void setUp() {
        powerball = new Powerball();
        ticketRepository = new TicketRepository();

    }

    @Test
    public void integrationTest() {
        //Create user ticket

        List<Integer> ticket = Arrays.asList(15, 16, 19, 40, 56, 19);

        //Save user ticket
        ticketRepository.saveTicket(ticket);

        //Create draw numbers
        List<Integer> draw = powerball.draw();

        //Get all  registered tickets
        List<List> allTickets = ticketRepository.getTickets();

        //Get all wins from all tickets
        List<Integer> ticketWins = powerball.collectTicketWins(allTickets, draw);

        // Get amount of winning combos that happened in draw
        long[] statistic = powerball.statistic(ticketWins);

        //getting statistic information that in draw performance was one ticket
        Assert.assertEquals(1, statistic[9]);

    }

}
