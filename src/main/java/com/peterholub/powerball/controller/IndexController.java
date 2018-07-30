package com.peterholub.powerball.controller;

import com.peterholub.powerball.powerballengine.Powerball;
import com.peterholub.powerball.repository.TicketRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
public class IndexController {

    private Powerball powerball;
    private TicketRepository ticketRepository;

    public IndexController(Powerball powerball, TicketRepository ticketRepository) {
        this.powerball = powerball;
        this.ticketRepository = ticketRepository;
    }

    @PostMapping("/register")
    public String registerTicket(@RequestParam("firstNumber") int firstNumber,
                                 @RequestParam("secondNumber") int secondNumber,
                                 @RequestParam("thirdNumber") int thirdNumber,
                                 @RequestParam("fourthNumber") int fourthNumber,
                                 @RequestParam("fifthNumber") int fifthNumber,
                                 @RequestParam("powerBallNumber") int powerBallNumber) {
        //Register ticket
        List<Integer> ticket = Arrays.asList(firstNumber, secondNumber, thirdNumber, fourthNumber, fifthNumber, powerBallNumber);

        //Checking that numbers for white balls are unique
        for (int i = 0; i < ticket.size() - 1; i++) {

            for (int j = i + 1; j < ticket.size() - 1; j++) {

                if (ticket.get(i).equals(ticket.get(j))) {

                    return "error";
                }
            }
        }
        ticketRepository.saveTicket(ticket);
        return "draw";
    }

    @PostMapping("/generate")
    public String generateRandomTickets(@RequestParam("amount") int amount) {

      //generating needed amount of tickets
        for (int i = 0; i < amount; i++) {
            List<Integer> ticket = powerball.draw();
            ticketRepository.saveTicket(ticket);
        }
        return "draw";

    }
    @PostMapping("/index")
    public String returnIndex (){

        return "index";
    }
    }
