package com.peterholub.gongxigamestask.controller;

import com.peterholub.gongxigamestask.powerball.Powerball;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class IndexController {

    private Powerball powerball;

    public IndexController(Powerball powerball) {
        this.powerball = powerball;
    }

    @PostMapping("/play")
    public String registerTicket(@RequestParam("firstNumber") int firstNumber,
                                 @RequestParam("secondNumber") int secondNumber,
                                 @RequestParam("thirdNumber") int thirdNumber,
                                 @RequestParam("fourthNumber") int fourthNumber,
                                 @RequestParam("fifthNumber") int fifthNumber,
                                 @RequestParam("powerBallNumber") int powerBallNumber,
                                 Model model) {
        //Register ticket
        List<Integer> ticket = Arrays.asList(firstNumber, secondNumber, thirdNumber, fourthNumber, fifthNumber, powerBallNumber);
        //Making draw numbers
        List<Integer> draw = powerball.draw();
        //Get winning numbers
        List<Integer> winningNumbers = powerball.winningNumbers(ticket, draw);
        //Prints outcome
        String result = powerball.collectWins(winningNumbers);

        model.addAttribute("result", result);
        return "result";
    }
}
