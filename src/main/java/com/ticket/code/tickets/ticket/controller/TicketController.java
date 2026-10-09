package com.ticket.code.tickets.ticket.controller;

import com.ticket.code.tickets.ticket.model.TicketModel;
import com.ticket.code.tickets.ticket.service.ITicketService;
import com.ticket.code.tickets.utils.modelsutils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    private ITicketService serv;

    @GetMapping("/getAllTicket")
    public ResponseEntity<ApiResponse<List<TicketModel>>> getAllTicket() {

        List<TicketModel> list = serv.allTicket();

        String message = list.isEmpty() ? "No se encontraron tickets" : "tickets encontrados.";
        ApiResponse<List<TicketModel>> respose = new ApiResponse<>(
                true,
                message,
                list
        );

        return new ResponseEntity<>(respose, HttpStatus.OK);

    }

    @GetMapping("/getAllTicketActive")
    public ResponseEntity<ApiResponse<List<TicketModel>>> getAllTicketActiveFalse() {//todos los tickets que no estan eliminados

        List<TicketModel> list = serv.findByActiveFalse();

        String message = list.isEmpty() ? "No se encontraron tickets" : "tickets encontrados.";
        ApiResponse<List<TicketModel>> respose = new ApiResponse<>(
                true,
                message,
                list
        );

        return new ResponseEntity<>(respose, HttpStatus.OK);

    }

}
