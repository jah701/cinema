package com.dev.cinema.service.mapper;

import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.dto.MovieSessionResponseDto;
import com.dev.cinema.model.dto.TicketResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    private final MovieSessionMapper movieSessionMapper;

    public TicketMapper(MovieSessionMapper movieSessionMapper) {
        this.movieSessionMapper = movieSessionMapper;
    }

    public TicketResponseDto ticketToDto(Ticket ticket) {
        MovieSessionResponseDto sessionDto =
                movieSessionMapper.movieSessionToDto(ticket.getMovieSession());
        TicketResponseDto dto = new TicketResponseDto();
        dto.setId(ticket.getId());
        dto.setMovieSessionResponseDto(sessionDto);
        dto.setUserId(ticket.getUser().getId());
        return dto;
    }
}
