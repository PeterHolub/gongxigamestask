package com.peterholub.gongxigamestask.repository;

import com.peterholub.gongxigamestask.domain.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Long> {

}
