package com.peterholub.gongxigamestask.controller;

import com.peterholub.gongxigamestask.domain.Ticket;
import com.peterholub.gongxigamestask.repository.TicketRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    private TicketRepository ticketRepository;

    public IndexController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public String registerTicket(@RequestParam("firstNumber") String firstNumber,
                                 @RequestParam("secondNumber") String secondNumber,
                                 @RequestParam("thirdNumber") String thirdNumber,
                                 @RequestParam("fourthNumber") String fourthNumber,
                                 @RequestParam("fifthNumber") String fifthNumber,
                                 @RequestParam("powerBallNumber") String powerBallNumber,
                                 Model model) {

        Ticket ticket = new Ticket();

        ticketRepository.save(ticket);

        return "index";
    }
}
