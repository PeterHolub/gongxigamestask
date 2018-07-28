package com.peterholub.gongxigamestask.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private int firstNumber;
    private int secondNumber;
    private int thirdNumber;
    private int fourthNumber;
    private int fifthNumber;
    private int powerBallNumber;

    public Ticket() {
    }


}
