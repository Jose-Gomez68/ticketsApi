package com.ticket.code.tickets.ticket.controller;

import com.ticket.code.tickets.project.model.ProjectModel;
import com.ticket.code.tickets.ticket.model.TicketCreateModel;
import com.ticket.code.tickets.ticket.model.TicketModel;
import com.ticket.code.tickets.ticket.service.ITicketService;
import com.ticket.code.tickets.utils.modelsutils.ApiResponse;
import com.ticket.code.tickets.utils.modelsutils.PaginationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    private ITicketService serv;

    /**
     * PARA CUANDO SE ACTUALIZE Y CREE Y ELIMINE UN REGISTRO
     * ENVIARE EL ID DE USUARIO PARA SABER
     * QUIEN ES EL QUE HACE CAMBIO EN LOS REGISTROS DESDE LA APP*/

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
    public ResponseEntity<ApiResponse<PaginationModel<List<TicketModel>>>> getAllTicketActiveTruePage(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {//todos los tickets que no estan eliminados

        if (page < 0 || size < 1 || size > 100) {
            ApiResponse<PaginationModel<List<TicketModel>>> response =
                    new ApiResponse<>(
                            false,
                            "Los parámetros de paginación no son válidos",
                            null
                    );

            return ResponseEntity.badRequest().body(response);
        }

        Page<TicketModel> list = serv.findByActiveTruePage(page, size);

        PaginationModel<List<TicketModel>> pagination =
                new PaginationModel<>(
                        list.getTotalElements(),
                        list.getNumber(),
                        list.getSize(),
                        list.getContent()
                );

        String message = list.isEmpty() ? "No se encontraron tickets" : "tickets encontrados.";
        ApiResponse<PaginationModel<List<TicketModel>>> respose =
                new ApiResponse<>(
                true,
                message,
                pagination
        );

        return new ResponseEntity<>(respose, HttpStatus.OK);

    }

    @GetMapping("/{ticketID}")
    public ResponseEntity<ApiResponse<TicketModel>> getTicketByID(@PathVariable("ticketID") Long ticketID) {

        Optional<TicketModel> ticket = serv.findByID(ticketID);

        if (ticket.isPresent()){
            ApiResponse<TicketModel> resp = new ApiResponse<>(
                    true,
                    "Ticket encontrada",
                    ticket.get()
            );

            return ResponseEntity.ok(resp);
        }

        ApiResponse<TicketModel> resp = new ApiResponse<>(
                false,
                "Ticket no encontrada",
                null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);

    }

    @PostMapping("/createTicket")
    public ResponseEntity<ApiResponse<TicketModel>> createTicket(
            @RequestBody TicketCreateModel ticket) {
        try {

            TicketModel createdTicket = serv.save(ticket);

            ApiResponse<TicketModel> response = new ApiResponse<>(
                    true,
                    "ticket creado correctamente.",
                    createdTicket
            );

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);

        } catch (Exception e) {

            ApiResponse<TicketModel> response = new ApiResponse<>(
                    false,
                    "Ocurrió un error al crear el ticket: " + e.getMessage(),
                    null
            );

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

    @PutMapping("/UpdateTicket")
    public ResponseEntity<ApiResponse<TicketModel>> updateTicket(
            @RequestBody TicketCreateModel ticket) {

        try {

            // 1.verificar que la ticket exista
            Optional<TicketModel> existingTicket =
                    serv.findByID(ticket.getTicketID());

            if (existingTicket.isEmpty()) {

                ApiResponse<TicketModel> response = new ApiResponse<>(
                        false,
                        "El ticket con ID " + ticket.getTicketID()
                                + " no existe.",
                        null
                );

                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(response);
            }



            // obtener el ticket existente
            //TicketModel ticketToUpdate = existingTicket.get();
            System.out.println("TICKET");
            System.out.println(ticket);
            System.out.println(ticket.getTicketID());
            // guardar cambios
            TicketModel  updatedTicket =
                    serv.update(ticket);

            ApiResponse<TicketModel > response = new ApiResponse<>(
                    true,
                    "Ticket actualizada correctamente.",
                    updatedTicket
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            ApiResponse<TicketModel > response = new ApiResponse<>(
                    false,
                    "Ocurrió un error al actualizar el ticket: "
                            + e.getMessage(),
                    null
            );

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

    @DeleteMapping("/DeleteTicket/{ticketID}")
    public ResponseEntity<ApiResponse<TicketModel>> deleteTicket (@PathVariable("ticketID") Long ticketID) {

        try {

            // 1.verificar que la ticket exista
            Optional<TicketModel> existingTicket =
                    serv.findByID(ticketID);

            if (existingTicket.isEmpty()) {

                ApiResponse<TicketModel> response = new ApiResponse<>(
                        false,
                        "El ticket con ID " + ticketID + " no existe.",
                        null
                );

                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(response);
            }

            // 2.eliminar ticket
            serv.deleteTicket(ticketID);

            ApiResponse<TicketModel> response = new ApiResponse<>(
                    true,
                    "Ticket eliminada correctamente.",
                    existingTicket.get()
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            ApiResponse<TicketModel> response = new ApiResponse<>(
                    false,
                    "Ocurrió un error al eliminar el ticket: "
                            + e.getMessage(),
                    null
            );

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }

    }

}
