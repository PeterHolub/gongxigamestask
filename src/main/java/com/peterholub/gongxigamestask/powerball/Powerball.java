package com.peterholub.gongxigamestask.powerball;

import com.peterholub.gongxigamestask.powerball.powerballconstants.PowerballConstants;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.*;

@Service
public class Powerball {

    private Random random = new Random();

    public List<Integer> draw() {
        //generating list of numbers from 1 to 69
        List<Integer> drawList = IntStream.range(1, 69).boxed().collect(Collectors.toCollection(ArrayList::new));
        //shuffle the list
        Collections.shuffle(drawList);
        //remove values
        drawList.subList(0, 5);
        //generate powerball value
        int powerball = random.nextInt(26) + 1;

        drawList.add(powerball);
        return drawList;
    }

    public String collectWins(List<Integer> winningNumbers) {

        String winningCombo = "You are win nothing";

        //5+1
        if (winningNumbers.size() == 6) {

            winningCombo = PowerballConstants.WINNING_COMBO_JACKPOT;

        }

        //5+0
        if (!winningNumbers.contains(5) && winningNumbers.size() == 5) {

            winningCombo = PowerballConstants.WINNING_COMBO_5_PLUS_0;

        }

        //4+1
        if (winningNumbers.contains(5) && winningNumbers.size() == 5) {

            winningCombo = PowerballConstants.WINNING_COMBO_4_PLUS_1;

        }

        //4+0
        if (!winningNumbers.contains(5) && winningNumbers.size() == 4) {

            winningCombo = PowerballConstants.WINNING_COMBO_4_PLUS_0;

        }

        //3+1
        if (winningNumbers.contains(5) && winningNumbers.size() == 4) {

            winningCombo = PowerballConstants.WINNING_COMBO_3_PLUS_1;

        }

        //3+0
        if (!winningNumbers.contains(5) && winningNumbers.size() == 3) {

            winningCombo = PowerballConstants.WINNING_COMBO_3_PLUS_0;
        }

        //2+1
        if (winningNumbers.contains(5) && winningNumbers.size() == 3) {
            winningCombo = PowerballConstants.WINNING_COMBO_2_PLUS_1;

        }

        //1+1
        if (winningNumbers.contains(5) && winningNumbers.size() == 2) {
            winningCombo = PowerballConstants.WINNING_COMBO_1_PLUS_1;

        }

        //0+1
        if (winningNumbers.contains(5) && winningNumbers.size() == 1) {

            winningCombo = PowerballConstants.WINNING_COMBO_0_PLUS_1;
        }


        return winningCombo;
    }

    public List<Integer> winningNumbers(List<Integer> ticket, List<Integer> draw) {

        List<Integer> winningNumbers = new ArrayList<>();
        int index;

        for (int i = 0; i < ticket.size(); i++) {
            for (int j = 0; j < ticket.size(); j++) {

                if (ticket.get(i).equals(draw.get(j))) {
                    index = i;
                    winningNumbers.add(index);

                }
            }
        }
        return winningNumbers;
    }
}
