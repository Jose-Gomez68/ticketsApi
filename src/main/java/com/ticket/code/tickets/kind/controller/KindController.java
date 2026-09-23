package com.ticket.code.tickets.kind.controller;

import com.ticket.code.tickets.area.model.AreaModel;
import com.ticket.code.tickets.kind.model.KindModel;
import com.ticket.code.tickets.kind.service.IKindService;
import com.ticket.code.tickets.utils.modelsutils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/api/kind")
public class KindController {

    @Autowired
    private IKindService serv;

    @GetMapping("/getAllKind")
    public ResponseEntity<ApiResponse<List<KindModel>>> getAllKind() {

        List<KindModel> list = serv.allKind();

        String message = list.isEmpty() ? "No se encontraron tipo o kind" : "areas encontradas.";
        ApiResponse<List<KindModel>> respose = new ApiResponse<>(
                true,
                message,
                list
        );

        return new ResponseEntity<>(respose, HttpStatus.OK);

    }

    @GetMapping("/{kindID}")
    public ResponseEntity<ApiResponse<KindModel>> getAreaByID(@PathVariable("kindID") Long kindID) {

        Optional<KindModel> area = serv.findByID(kindID);

        if (area.isPresent()){
            ApiResponse<KindModel> resp = new ApiResponse<>(
                    true,
                    "Tipo o Kind encontrada",
                    area.get()
            );

            return ResponseEntity.ok(resp);
        }

        ApiResponse<KindModel> resp = new ApiResponse<>(
                false,
                "Tipo o Kind no encontrada",
                null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);

    }

    @PostMapping("/createKind")
    public ResponseEntity<ApiResponse<KindModel>> createArea(
            @RequestBody KindModel kind) {
        try {

            Optional<KindModel> existingKind =
                    serv.findByNameIgnoreCase(kind.getName());

            if (existingKind.isPresent()) {

                ApiResponse<KindModel> response = new ApiResponse<>(
                        false,
                        "Ya existe una tipo o kind con el nombre: "
                                + kind.getName(),
                        null
                );

                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(response);
            }

            KindModel createdKind = serv.save(kind);

            ApiResponse<KindModel> response = new ApiResponse<>(
                    true,
                    "tipo o kind creada correctamente.",
                    createdKind
            );

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);

        } catch (Exception e) {

            ApiResponse<KindModel> response = new ApiResponse<>(
                    false,
                    "Ocurrió un error al crear la tipo o kind: " + e.getMessage(),
                    null
            );

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

    @PutMapping("/UpdateKind")
    public ResponseEntity<ApiResponse<KindModel>> updateArea(
            @RequestBody KindModel kind) {

        try {

            // 1.verificar que la kind exista
            Optional<KindModel> existingKind =
                    serv.findByID(kind.getKindID());

            if (existingKind.isEmpty()) {

                ApiResponse<KindModel> response = new ApiResponse<>(
                        false,
                        "El kind con ID " + kind.getKindID()
                                + " no existe.",
                        null
                );

                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(response);
            }

            // 2.verificar que el nuevo nombre no exista
            Optional<KindModel> kindWithSameName =
                    serv.findByNameIgnoreCase(kind.getName());

            if (kindWithSameName.isPresent()
                    && !kindWithSameName.get()
                    .getKindID()
                    .equals(kind.getKindID())) {

                ApiResponse<KindModel> response = new ApiResponse<>(
                        false,
                        "Ya existe un kind con el nombre: "
                                + kind.getName(),
                        null
                );

                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(response);
            }

            // 3.obtener la kind existente
            KindModel kindToUpdate = existingKind.get();

            // 4.actualizar los campos
            kindToUpdate.setName(kind.getName());

            // 5.guardar cambios
            KindModel updatedArea =
                    serv.update(kindToUpdate);

            ApiResponse<KindModel> response = new ApiResponse<>(
                    true,
                    "Area actualizada correctamente.",
                    updatedArea
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            ApiResponse<KindModel> response = new ApiResponse<>(
                    false,
                    "Ocurrió un error al actualizar el kind: "
                            + e.getMessage(),
                    null
            );

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

    @DeleteMapping("/DeleteArea/{areaID}")
    public ResponseEntity<ApiResponse<AreaModel>> deleteArea (@PathVariable("areaID") Long areaID) {

        try {

            // 1.verificar que la area exista
            Optional<AreaModel> existingArea =
                    serv.findByID(areaID);

            if (existingArea.isEmpty()) {

                ApiResponse<AreaModel> response = new ApiResponse<>(
                        false,
                        "El area con ID " + areaID + " no existe.",
                        null
                );

                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(response);
            }

            // 2.eliminar area
            serv.deleteArea(areaID);

            ApiResponse<AreaModel> response = new ApiResponse<>(
                    true,
                    "Area eliminada correctamente.",
                    existingArea.get()
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            ApiResponse<AreaModel> response = new ApiResponse<>(
                    false,
                    "Ocurrió un error al eliminar el area: "
                            + e.getMessage(),
                    null
            );

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }

    }

}
