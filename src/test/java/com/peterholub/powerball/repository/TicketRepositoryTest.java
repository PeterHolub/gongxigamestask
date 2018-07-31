package com.peterholub.powerball.repository;

import org.testng.annotations.*;
import java.lang.reflect.Field;
import java.util.*;

import static org.junit.Assert.*;

public class TicketRepositoryTest {

    private TicketRepository ticketRepository;

    @BeforeTest
    public void setUp() {
        ticketRepository = new TicketRepository();
    }

    //Testing that method saves lists to field that imitates database
    @Test
    public void saveTicket() throws NoSuchFieldException, IllegalAccessException {
        List<Integer> ticket = Arrays.asList(4, 5, 18, 3, 52, 6);

        ticketRepository.saveTicket(ticket);

        Class reflection = ticketRepository.getClass();

        Field field = reflection.getDeclaredField("pseudoRepository");

        field.setAccessible(true);

        List<List> pseudoRepository = (List<List>) field.get(ticketRepository);

        assertEquals(1, pseudoRepository.size());

        field.setAccessible(false);
    }

    //Make sure that method don't returns null
    @Test
    public void getTickets() {

        List<List> pseudoRepository = ticketRepository.getTickets();

        assertNotNull(pseudoRepository);

    }
}