package com.peterholub.powerball.powerballengine;

import com.peterholub.powerball.powerballengine.powerballconstants.PowerballConstants;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.*;

@Service
public class Powerball {

    //Method to simulate draw
    public List<Integer> draw() {

        Random random = new Random();
        //generating list of numbers from 1 to 69
        List<Integer> generateNumbers = IntStream.range(1, 69).boxed().collect(Collectors.toCollection(ArrayList::new));
        //shuffle the list
        Collections.shuffle(generateNumbers);
        //take 5 random numbers
        List<Integer> randomNumbers = generateNumbers.subList(0, 5);
        //generate powerball value
        int powerball = random.nextInt(26) + 1;

        randomNumbers.add(powerball);

        return randomNumbers;
    }

    //Checking what indexes are matched from the ticket in compare with draw numbers
    //If numbers are match its write to array index of matched number, which helps us to know if powerball number are match!
    private List<Integer> winningNumbersIndex(List<Integer> ticket, List<Integer> draw) {

        List<Integer> winningNumbersIndex = new ArrayList<>();
        int index;

        //checking if powerball numbers are match
        if (ticket.get(5).equals(draw.get(5))) {

            winningNumbersIndex.add(5);
        }

       //checking white balls
        for (int i = 0; i < ticket.size()-1 ; i++) {
            for (int j = 0; j < ticket.size()-1 ; j++) {

                if (ticket.get(i).equals(draw.get(j))) {
                    index = i;
                    winningNumbersIndex.add(index);
                }
            }
        }
        return winningNumbersIndex;
    }

    // Method that check winning combo for each ticket, by list size and "if contains index 5"(which is index of powerball value)
    private int ticketWinningCombo(List<Integer> winningNumbersIndex) {
        //init value 0, if no winning, this value will be not overwriting, otherwise, it will overwrite with a number of winning combo
        int winningCombo = 0;

        //5+1
        if (winningNumbersIndex.size() == 6) {

            winningCombo = PowerballConstants.WINNING_COMBO_JACKPOT;

        }

        //5+0
        if (!winningNumbersIndex.contains(5) && winningNumbersIndex.size() == 5) {

            winningCombo = PowerballConstants.WINNING_COMBO_5_PLUS_0;

        }

        //4+1
        if (winningNumbersIndex.contains(5) && winningNumbersIndex.size() == 5) {

            winningCombo = PowerballConstants.WINNING_COMBO_4_PLUS_1;

        }

        //4+0
        if (!winningNumbersIndex.contains(5) && winningNumbersIndex.size() == 4) {

            winningCombo = PowerballConstants.WINNING_COMBO_4_PLUS_0;

        }

        //3+1
        if (winningNumbersIndex.contains(5) && winningNumbersIndex.size() == 4) {

            winningCombo = PowerballConstants.WINNING_COMBO_3_PLUS_1;

        }

        //3+0
        if (!winningNumbersIndex.contains(5) && winningNumbersIndex.size() == 3) {

            winningCombo = PowerballConstants.WINNING_COMBO_3_PLUS_0;
        }

        //2+1
        if (winningNumbersIndex.contains(5) && winningNumbersIndex.size() == 3) {
            winningCombo = PowerballConstants.WINNING_COMBO_2_PLUS_1;

        }

        //1+1
        if (winningNumbersIndex.contains(5) && winningNumbersIndex.size() == 2) {
            winningCombo = PowerballConstants.WINNING_COMBO_1_PLUS_1;

        }

        //0+1
        if (winningNumbersIndex.contains(5) && winningNumbersIndex.size() == 1) {

            winningCombo = PowerballConstants.WINNING_COMBO_0_PLUS_1;
        }

        return winningCombo;
    }

    // Method that writes winning combo id's to list for total statistic
    public List<Integer> collectTicketWins(List<List> allTickets, List<Integer> draw) {

        List<Integer> ticketWins = new ArrayList<>();

        for (List ticket : allTickets) {

            List<Integer> winningNumbersIndex = winningNumbersIndex(ticket, draw);
            int winningCombo = ticketWinningCombo(winningNumbersIndex);

            ticketWins.add(winningCombo);
        }
        return ticketWins;
    }

    // Method that getting amount of winners for each combo using lambda + filter
    public long[] statistic(List<Integer> ticketWins) {

        //Getting total amount of players by list size
        long totalPlayers = ticketWins.size();

        long jackpot = ticketWins.stream().filter(i -> i == PowerballConstants.WINNING_COMBO_JACKPOT).mapToInt(i -> i).count();

        long fivePlusZero = ticketWins.stream().filter(i -> i == PowerballConstants.WINNING_COMBO_5_PLUS_0).mapToInt(i -> i).count();

        long fourPlusOne = ticketWins.stream().filter(i -> i == PowerballConstants.WINNING_COMBO_4_PLUS_1).mapToInt(i -> i).count();

        long fourPlusZero = ticketWins.stream().filter(i -> i == PowerballConstants.WINNING_COMBO_4_PLUS_0).mapToInt(i -> i).count();

        long threePlusOne = ticketWins.stream().filter(i -> i == PowerballConstants.WINNING_COMBO_3_PLUS_1).mapToInt(i -> i).count();

        long threePlusZero = ticketWins.stream().filter(i -> i == PowerballConstants.WINNING_COMBO_3_PLUS_0).mapToInt(i -> i).count();

        long twoPlusOne = ticketWins.stream().filter(i -> i == PowerballConstants.WINNING_COMBO_2_PLUS_1).mapToInt(i -> i).count();

        long onePlusOne = ticketWins.stream().filter(i -> i == PowerballConstants.WINNING_COMBO_1_PLUS_1).mapToInt(i -> i).count();

        long zeroPlusOne = ticketWins.stream().filter(i -> i == PowerballConstants.WINNING_COMBO_0_PLUS_1).mapToInt(i -> i).count();

        return new long[]{jackpot, fivePlusZero, fourPlusOne, fourPlusZero, threePlusOne, threePlusZero, twoPlusOne, onePlusOne, zeroPlusOne, totalPlayers};
    }

    public  void experimentalProbability (long [] statistic){

        long total = statistic[9];

        long jackpot = statistic[0]/statistic[9];



    }
}
