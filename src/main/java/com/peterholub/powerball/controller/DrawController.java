package com.peterholub.powerball.controller;

import com.peterholub.powerball.powerballengine.Powerball;
import com.peterholub.powerball.repository.TicketRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class DrawController {
    private Powerball powerball;
    private TicketRepository ticketRepository;

    public DrawController(Powerball powerball, TicketRepository ticketRepository) {
        this.powerball = powerball;
        this.ticketRepository = ticketRepository;
    }

    @PostMapping("/draw")
    public String draw(Model model) {

        //Create draw numbers
        List<Integer> draw = powerball.draw();

        //Get all  registered tickets
        List<List> allTickets = ticketRepository.getTickets();

        //Get all wins from all tickets
        List<Integer> ticketWins = powerball.collectTicketWins(allTickets, draw);

        // Get amount of winning combos that happened in draw
        long[] statistic = powerball.statistic(ticketWins);

        //print them to page
        model.addAttribute("jackpot", statistic[0]);
        model.addAttribute("fivePlusZero", statistic[1]);
        model.addAttribute("fourPlusOne", statistic[2]);
        model.addAttribute("fourPlusZero", statistic[3]);
        model.addAttribute("threePlusOne", statistic[4]);
        model.addAttribute("threePlusZero", statistic[5]);
        model.addAttribute("twoPlusOne", statistic[6]);
        model.addAttribute("onePlusOne", statistic[7]);
        model.addAttribute("zeroPlusOne", statistic[8]);
        model.addAttribute("totalPlayers", statistic[9]);

        return "result";
    }

}
