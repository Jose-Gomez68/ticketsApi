package com.ticket.code.tickets.comment.controller;


import com.ticket.code.tickets.comment.model.TicketCommentModel;
import com.ticket.code.tickets.comment.service.ITicketCommentService;
import com.ticket.code.tickets.utils.modelsutils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/api/ticketComment")
public class TicketCommentController {

    @Autowired
    private ITicketCommentService serv;

    @GetMapping("/getAllTicketComment")
    public ResponseEntity<ApiResponse<List<TicketCommentModel>>> getAllTicketComment() {

        List<TicketCommentModel> list = serv.allTicketComment();

        String message = list.isEmpty() ? "No se encontraron ticketsComment" : "ticketsComment encontrados.";
        ApiResponse<List<TicketCommentModel>> respose = new ApiResponse<>(
                true,
                message,
                list
        );

        return new ResponseEntity<>(respose, HttpStatus.OK);

    }

    @GetMapping("/{ticketCommentID}")
    public ResponseEntity<ApiResponse<TicketCommentModel>> getTicketCommentByID(@PathVariable("ticketCommentID") Long ticketCommentID) {

        Optional<TicketCommentModel> ticket = serv.findByID(ticketCommentID);

        if (ticket.isPresent()){
            ApiResponse<TicketCommentModel> resp = new ApiResponse<>(
                    true,
                    "TicketComment encontrada",
                    ticket.get()
            );

            return ResponseEntity.ok(resp);
        }

        ApiResponse<TicketCommentModel> resp = new ApiResponse<>(
                false,
                "TicketComment no encontrada",
                null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);

    }

    @GetMapping("/{ticketID}")
    public ResponseEntity<ApiResponse<List<TicketCommentModel>>> getTicketByID(@PathVariable("ticketID") Long ticketID) {

        List<TicketCommentModel> list = serv.findByTicketID(ticketID);

        String message = list.isEmpty() ? "No se encontraron ticketsComment" : "ticketsComment encontrados.";
        ApiResponse<List<TicketCommentModel>> respose = new ApiResponse<>(
                true,
                message,
                list
        );

        return new ResponseEntity<>(respose, HttpStatus.OK);

    }

    @PostMapping("/createTicketComment")
    public ResponseEntity<ApiResponse<TicketCommentModel>> createTicketComment(
            @RequestBody TicketCommentModel ticket) {
        try {

            TicketCommentModel createdTicketComment = serv.save(ticket);

            ApiResponse<TicketCommentModel> response = new ApiResponse<>(
                    true,
                    "ticketComment creado correctamente.",
                    createdTicketComment
            );

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);

        } catch (Exception e) {

            ApiResponse<TicketCommentModel> response = new ApiResponse<>(
                    false,
                    "Ocurrió un error al crear el ticketComment: " + e.getMessage(),
                    null
            );

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

    @PutMapping("/UpdateTicketComment")
    public ResponseEntity<ApiResponse<TicketCommentModel>> updateTicketComment(
            @RequestBody TicketCommentModel ticket) {

        try {

            // 1.verificar que la ticketComment exista
            Optional<TicketCommentModel> existingTicketComment =
                    serv.findByID(ticket.getCommentID());

            if (existingTicketComment.isEmpty()) {

                ApiResponse<TicketCommentModel> response = new ApiResponse<>(
                        false,
                        "El ticketComment con ID " + ticket.getCommentID()
                                + " no existe.",
                        null
                );

                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(response);
            }



            // obtener el ticketComment existente
            //TicketCommentModel updatedTicketComment = existingTicketComment.get();

            // guardar cambios
            TicketCommentModel updatedTicketComment =
                    serv.update(ticket);

            ApiResponse<TicketCommentModel> response = new ApiResponse<>(
                    true,
                    "TicketComment actualizada correctamente.",
                    updatedTicketComment
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            ApiResponse<TicketCommentModel> response = new ApiResponse<>(
                    false,
                    "Ocurrió un error al actualizar el ticketComment: "
                            + e.getMessage(),
                    null
            );

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

    @DeleteMapping("/DeleteComment/{ticketCommentID}")
    public ResponseEntity<ApiResponse<TicketCommentModel>> deleteTicket (@PathVariable("ticketCommentID") Long ticketCommentID) {

        try {

            // 1.verificar que la ticketComment exista
            Optional<TicketCommentModel> existingTicketComment =
                    serv.findByID(ticketCommentID);

            if (existingTicketComment.isEmpty()) {

                ApiResponse<TicketCommentModel> response = new ApiResponse<>(
                        false,
                        "El ticketComment con ID " + ticketCommentID + " no existe.",
                        null
                );

                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(response);
            }

            // 2.eliminar ticket
            serv.deleteTicket(ticketCommentID);

            ApiResponse<TicketCommentModel> response = new ApiResponse<>(
                    true,
                    "TicketComment eliminada correctamente.",
                    existingTicketComment.get()
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            ApiResponse<TicketCommentModel> response = new ApiResponse<>(
                    false,
                    "Ocurrió un error al eliminar el ticketComment: "
                            + e.getMessage(),
                    null
            );

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }

    }

}
