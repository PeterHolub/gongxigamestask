package com.peterholub.powerball.powerballengine;
import com.peterholub.powerball.powerballengine.powerballconstants.PowerballConstants;
import org.testng.annotations.*;
import java.lang.reflect.*;
import java.util.*;
import static org.junit.Assert.*;

public class PowerballTest {

    private Powerball powerball;
    private Class reflection;

    @BeforeTest
    public void setUp() {
        powerball = new Powerball();
        reflection = powerball.getClass();
    }

    @Test
    public void draw() {
        //Checking that draw method returns list with 6 elements
        List<Integer> drawList = powerball.draw();

        //Checking that first 5 numbers (white ball numbers) are unique
        boolean whiteNumbersAreUnique = true;
        for (int i = 0; i < drawList.size() - 1; i++) {

            for (int j = i + 1; j < drawList.size() - 1; j++) {

                if (drawList.get(i).equals(drawList.get(j))) {

                    whiteNumbersAreUnique = false;
                }
            }
        }
        assertEquals(6, drawList.size());
        assertTrue(whiteNumbersAreUnique);
    }

    @Test
    public void winningNumbers() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        //Create ticket and draw with situation where 4 numbers match, to make sure that method will write them to list

        List<Integer> ticket = Arrays.asList(12, 14, 15, 16, 18, 21);

        List<Integer> draw = Arrays.asList(12, 14, 15, 18, 19, 13);

        Method method = reflection.getDeclaredMethod("winningNumbers", List.class, List.class);

        method.setAccessible(true);

        List<Integer> winningNumbers = (List<Integer>) method.invoke(powerball, ticket, draw);

        assertEquals(4, winningNumbers.size());

        method.setAccessible(false);
    }

    //Testing method behavior with all available
    @Test(dataProvider = "winningCombos")
    public void ticketWinningCombo(int expected, List<Integer> winningNumbers) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method method = reflection.getDeclaredMethod("ticketWinningCombo", List.class);

        method.setAccessible(true);

        int winningCombo = (int) method.invoke(powerball, winningNumbers);

        assertEquals(expected, winningCombo);

        method.setAccessible(false);
    }

    //Testing method that collects winning combo ids. Actually this method invokes previous tested methods: ticketWinningCombo and winningNumbers
    @Test
    public void collectTicketWins() {
        //Create three tickets with 3 different winning combo

        //0
        List<Integer> ticketOne = Arrays.asList(44, 33, 1, 11, 7, 1);
        //Jackpot
        List<Integer> ticketTwo = Arrays.asList(14, 17, 18, 51, 19, 13);
        //0+1
        List<Integer> ticketThree = Arrays.asList(12, 11, 1, 52, 39, 13);

        List<Integer> draw = Arrays.asList(14, 17, 51, 18, 19, 13);

        List<List> allTickets = Arrays.asList(ticketOne, ticketTwo, ticketThree);

        List<Integer> ticketWins = powerball.collectTicketWins(allTickets, draw);
        //0
        assertTrue(ticketWins.contains(0));
        //Jackpot
        assertTrue(ticketWins.contains(9));
        //0+1
        assertTrue(ticketWins.contains(1));

    }

    @Test
    public void statistic() {

        //Creating 11 tickets
        //4 tickets without wins
        //2 tickets with 0+1 combo
        //1 ticket with 4+0
        //2 ticket with 1+1
        //2 tickets 4+1
        //1 with jackpot

        List<Integer> ticketWins = Arrays.asList(0, 0, 0, 1, 1, 6, 2, 2, 7, 7, 9);

        long[] statistic = powerball.statistic(ticketWins);

        //making sure that total amount 11
        assertEquals(11, statistic[9]);
        //2 tickets with 0+1 combo
        assertEquals(2, statistic[8]);
        //1 ticket with 4+0
        assertEquals(1, statistic[3]);
        //2 ticket with 1+1
        assertEquals(2, statistic[7]);
        //2 tickets 4+1
        assertEquals(2, statistic[2]);
        //1 with jackpot
        assertEquals(1, statistic[0]);
        }


    @DataProvider(name = "winningCombos")
    public Object[][] getWinningCombos() {

        List<Integer> jackpot = Arrays.asList(0, 1, 2, 3, 4, 5);
        List<Integer> fivePlusZero = Arrays.asList(0, 1, 2, 3, 4);
        List<Integer> fourPlusOne = Arrays.asList(0, 1, 2, 3, 5);
        List<Integer> fourPlusZero = Arrays.asList(0, 1, 2, 4);
        List<Integer> threePlusOne = Arrays.asList(2, 3, 4, 5);
        List<Integer> threePlusZero = Arrays.asList(2, 3, 4);
        List<Integer> twoPlusOne = Arrays.asList(3, 4, 5);
        List<Integer> onePlusOne = Arrays.asList(4, 5);
        List<Integer> zeroPlusOne = Collections.singletonList(5);

        return new Object[][]{
                {PowerballConstants.WINNING_COMBO_JACKPOT, jackpot},
                {PowerballConstants.WINNING_COMBO_5_PLUS_0, fivePlusZero},
                {PowerballConstants.WINNING_COMBO_4_PLUS_1, fourPlusOne},
                {PowerballConstants.WINNING_COMBO_4_PLUS_0, fourPlusZero},
                {PowerballConstants.WINNING_COMBO_3_PLUS_1, threePlusOne},
                {PowerballConstants.WINNING_COMBO_3_PLUS_0, threePlusZero},
                {PowerballConstants.WINNING_COMBO_2_PLUS_1, twoPlusOne},
                {PowerballConstants.WINNING_COMBO_1_PLUS_1, onePlusOne},
                {PowerballConstants.WINNING_COMBO_0_PLUS_1, zeroPlusOne},
        };
    }

}