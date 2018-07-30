package com.peterholub.powerball.controller;

import com.peterholub.powerball.powerballengine.Powerball;
import com.peterholub.powerball.repository.TicketRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DrawController {
    private Powerball powerball;
    private TicketRepository repository;

    public DrawController(Powerball powerball) {
        this.powerball = powerball;
    }

    @PostMapping("/draw")
    public String draw() {

        //Create draw numbers
        List<Integer> draw = powerball.draw();

        //Get all  registered tickets
//        List<List> allTickets = repository.getTickets();
//
//
//        List<Integer> statistic = powerball.collectTicketWins(allTickets,draw);



//        List<Integer> winningNumbers

        //Prints outcome
//        String result = powerball.collectWins(winningNumbers);

//        model.addAttribute("result", result);

        return "result";
    }

}
