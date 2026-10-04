package com.ticket.code.tickets.Priority.controller;


import com.ticket.code.tickets.Priority.model.PriorityModel;
import com.ticket.code.tickets.Priority.service.IPriorityService;
import com.ticket.code.tickets.utils.modelsutils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/api/priority")
public class PriorityController {

    @Autowired
    private IPriorityService serv;

    @GetMapping("/getAllPriority")
    public ResponseEntity<ApiResponse<List<PriorityModel>>> getAllPriority() {

        List<PriorityModel> list = serv.allPriority();

        String message = list.isEmpty() ? "No se encontraron prioritys" : "Priority encontrada";
        ApiResponse<List<PriorityModel>> response = new ApiResponse<>(
                true,
                message,
                list
        );

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/{priorityID}")
    public  ResponseEntity<ApiResponse<PriorityModel>> getPriorityByID(@PathVariable("priorityID") Long priorityID) {

        Optional<PriorityModel> priority = serv.findByID(priorityID);

        if (priority.isPresent()) {
            ApiResponse<PriorityModel> resp = new ApiResponse<>(
                    true,
                    "Priority encontrada",
                    priority.get()
            );

            return ResponseEntity.ok(resp);

        }

        ApiResponse<PriorityModel> resp = new ApiResponse<>(
                false,
                "Priority no encontrada",
                null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);

    }

    @PostMapping("/createPriority")
    public ResponseEntity<ApiResponse<PriorityModel>> createPriority(
            @RequestBody PriorityModel priority) {
        try {

            Optional<PriorityModel> existingPriority =
                    serv.findByNameIgnoreCase(priority.getName());

            if (existingPriority.isPresent()) {

                ApiResponse<PriorityModel> response = new ApiResponse<>(
                        false,
                        "Ya existe una priority con el nombre: "
                                + priority.getName(),
                        null
                );

                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(response);
            }

            PriorityModel createdPriority = serv.save(priority);

            ApiResponse<PriorityModel> response = new ApiResponse<>(
                    true,
                    "priority creada correctamente.",
                    createdPriority
            );

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);

        } catch (Exception e) {

            ApiResponse<PriorityModel> response = new ApiResponse<>(
                    false,
                    "Ocurrió un error al crear la priority: " + e.getMessage(),
                    null
            );

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

    @PutMapping("/UpdatePriority")
    public ResponseEntity<ApiResponse<PriorityModel>> updatePriority(
            @RequestBody PriorityModel priority) {

        try {

            // 1.verificar que la priority exista
            Optional<PriorityModel> existingPriority =
                    serv.findByID(priority.getPriorityID());

            if (existingPriority.isEmpty()) {

                ApiResponse<PriorityModel> response = new ApiResponse<>(
                        false,
                        "El priority con ID " + priority.getPriorityID()
                                + " no existe.",
                        null
                );

                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(response);
            }

            // 2.verificar que el nuevo nombre no exista
            Optional<PriorityModel> priorityWithSameName =
                    serv.findByNameIgnoreCase(priority.getName());

            if (priorityWithSameName.isPresent()
                    && !priorityWithSameName.get()
                    .getPriorityID()
                    .equals(priority.getPriorityID())) {

                ApiResponse<PriorityModel> response = new ApiResponse<>(
                        false,
                        "Ya existe un priority con el nombre: "
                                + priority.getName(),
                        null
                );

                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(response);
            }

            // 3.obtener la priority existente
            PriorityModel priorityToUpdate = existingPriority.get();

            // 4.actualizar los campos
            priorityToUpdate.setName(priority.getName());

            // 5.guardar cambios
            PriorityModel updatedPriority =
                    serv.update(priorityToUpdate);

            ApiResponse<PriorityModel> response = new ApiResponse<>(
                    true,
                    "Priority actualizada correctamente.",
                    updatedPriority
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            ApiResponse<PriorityModel> response = new ApiResponse<>(
                    false,
                    "Ocurrió un error al actualizar el priority: "
                            + e.getMessage(),
                    null
            );

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

    @DeleteMapping("/DeletePriority/{priorityID}")
    public ResponseEntity<ApiResponse<PriorityModel>> deletePriority (@PathVariable("priorityID") Long priorityID) {

        try {

            // 1.verificar que la priority exista
            Optional<PriorityModel> existingPriority =
                    serv.findByID(priorityID);

            if (existingPriority.isEmpty()) {

                ApiResponse<PriorityModel> response = new ApiResponse<>(
                        false,
                        "El priority con ID " + priorityID + " no existe.",
                        null
                );

                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(response);
            }

            // 2.eliminar priority
            serv.deletePriority(priorityID);

            ApiResponse<PriorityModel> response = new ApiResponse<>(
                    true,
                    "Priority eliminada correctamente.",
                    existingPriority.get()
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            ApiResponse<PriorityModel> response = new ApiResponse<>(
                    false,
                    "Ocurrió un error al eliminar el Priority: "
                            + e.getMessage(),
                    null
            );

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }

    }

}
