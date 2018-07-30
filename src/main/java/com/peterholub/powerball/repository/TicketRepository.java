package com.peterholub.powerball.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//repository class with database simulation
@Repository
public class TicketRepository {

    private List<List> pseudoRepository = new ArrayList<>();

    public void saveTicket(List<Integer> ticket) {
        pseudoRepository.add(ticket);
        }

    public List<List> getTickets() {
        return pseudoRepository;
    }


}
