package com.ticket.code.tickets.status.controller;

import com.ticket.code.tickets.status.model.StatusModel;
import com.ticket.code.tickets.status.service.IStatusService;
import com.ticket.code.tickets.utils.modelsutils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/api/status")
public class StatusController {

    @Autowired
    private IStatusService serv;

    @GetMapping("/getAllStatus")
    public ResponseEntity<ApiResponse<List<StatusModel>>> getAllStatus() {

        List<StatusModel> list = serv.allStatus();

        String message = list.isEmpty() ? "No se encontraron status" : "status encontrado.";
        ApiResponse<List<StatusModel>> respose = new ApiResponse<>(
                true,
                message,
                list
        );

        return new ResponseEntity<>(respose, HttpStatus.OK);

    }

    @GetMapping("/{statusID}")
    public ResponseEntity<ApiResponse<StatusModel>> getStatusByID(@PathVariable("statusID") Long statusID) {

        Optional<StatusModel> status = serv.findByID(statusID);

        if (status.isPresent()){
            ApiResponse<StatusModel> resp = new ApiResponse<>(
                    true,
                    "Status encontrada",
                    status.get()
            );

            return ResponseEntity.ok(resp);
        }

        ApiResponse<StatusModel> resp = new ApiResponse<>(
                false,
                "Status no encontrada",
                null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);

    }

    @PostMapping("/createStatus")
    public ResponseEntity<ApiResponse<StatusModel>> createStatus(
            @RequestBody StatusModel status) {
        try {

            Optional<StatusModel> existingStatus =
                    serv.findByNameIgnoreCase(status.getName());

            if (existingStatus.isPresent()) {

                ApiResponse<StatusModel> response = new ApiResponse<>(
                        false,
                        "Ya existe una status con el nombre: "
                                + status.getName(),
                        null
                );

                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(response);
            }

            StatusModel createdStatus = serv.save(status);

            ApiResponse<StatusModel> response = new ApiResponse<>(
                    true,
                    "status creada correctamente.",
                    createdStatus
            );

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);

        } catch (Exception e) {

            ApiResponse<StatusModel> response = new ApiResponse<>(
                    false,
                    "Ocurrió un error al crear la status: " + e.getMessage(),
                    null
            );

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

    @PutMapping("/UpdateStatus")
    public ResponseEntity<ApiResponse<StatusModel>> updateStatus(
            @RequestBody StatusModel status) {

        try {

            // 1.verificar que la status exista
            Optional<StatusModel> existingStatus =
                    serv.findByID(status.getStatusID());

            if (existingStatus.isEmpty()) {

                ApiResponse<StatusModel> response = new ApiResponse<>(
                        false,
                        "El status con ID " + status.getStatusID()
                                + " no existe.",
                        null
                );

                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(response);
            }

            // 2.verificar que el nuevo nombre no exista
            Optional<StatusModel> statusWithSameName =
                    serv.findByNameIgnoreCase(status.getName());

            if (statusWithSameName.isPresent()
                    && !statusWithSameName.get()
                    .getStatusID()
                    .equals(status.getStatusID())) {

                ApiResponse<StatusModel> response = new ApiResponse<>(
                        false,
                        "Ya existe un status con el nombre: "
                                + status.getName(),
                        null
                );

                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(response);
            }

            // 3.obtener la status existente
            StatusModel statusToUpdate = existingStatus.get();

            // 4.actualizar los campos
            statusToUpdate.setName(status.getName());

            // 5.guardar cambios
            StatusModel updatedStatus =
                    serv.update(statusToUpdate);

            ApiResponse<StatusModel> response = new ApiResponse<>(
                    true,
                    "Status actualizada correctamente.",
                    updatedStatus
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            ApiResponse<StatusModel> response = new ApiResponse<>(
                    false,
                    "Ocurrió un error al actualizar el status: "
                            + e.getMessage(),
                    null
            );

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

    @DeleteMapping("/DeleteStatus/{statusID}")
    public ResponseEntity<ApiResponse<StatusModel>> deleteStatus (@PathVariable("statusID") Long statusID) {

        try {

            // 1.verificar questatus exista
            Optional<StatusModel> existingStatus =
                    serv.findByID(statusID);

            if (existingStatus.isEmpty()) {

                ApiResponse<StatusModel> response = new ApiResponse<>(
                        false,
                        "El status con ID " + statusID + " no existe.",
                        null
                );

                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(response);
            }

            // 2.eliminar status
            serv.deleteStatus(statusID);

            ApiResponse<StatusModel> response = new ApiResponse<>(
                    true,
                    "Status eliminada correctamente.",
                    existingStatus.get()
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            ApiResponse<StatusModel> response = new ApiResponse<>(
                    false,
                    "Ocurrió un error al eliminar el status: "
                            + e.getMessage(),
                    null
            );

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }

    }

}
