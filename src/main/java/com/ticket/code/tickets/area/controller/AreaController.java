package com.ticket.code.tickets.area.controller;

import com.ticket.code.tickets.area.model.AreaModel;
import com.ticket.code.tickets.area.service.IAreaService;
import com.ticket.code.tickets.utils.modelsutils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/api/area")
public class AreaController {

    @Autowired
    private IAreaService serv;

    @GetMapping("/getAllArea")
    public ResponseEntity<ApiResponse<List<AreaModel>>> getAllArea() {

        List<AreaModel> list = serv.allArea();

        String message = list.isEmpty() ? "No se encontraron areas" : "areas encontradas.";
        ApiResponse<List<AreaModel>> respose = new ApiResponse<>(
                true,
                message,
                list
        );

        return new ResponseEntity<>(respose, HttpStatus.OK);

    }

    @GetMapping("/{areaID}")
    public ResponseEntity<ApiResponse<AreaModel>> getAreaByID(@PathVariable("areaID") Long areaID) {

        Optional<AreaModel> area = serv.findByID(areaID);

        if (area.isPresent()){
            ApiResponse<AreaModel> resp = new ApiResponse<>(
                    true,
                    "Area encontrada",
                    area.get()
            );

            return ResponseEntity.ok(resp);
        }

        ApiResponse<AreaModel> resp = new ApiResponse<>(
                false,
                "Area no encontrada",
                null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);

    }

    @PostMapping("/createArea")
    public ResponseEntity<ApiResponse<AreaModel>> createArea(
            @RequestBody AreaModel area) {
        try {

            Optional<AreaModel> existingArea =
                    serv.findByNameIgnoreCase(area.getName());

            if (existingArea.isPresent()) {

                ApiResponse<AreaModel> response = new ApiResponse<>(
                        false,
                        "Ya existe una area con el nombre: "
                                + area.getName(),
                        null
                );

                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(response);
            }

            AreaModel createdArea = serv.save(area);

            ApiResponse<AreaModel> response = new ApiResponse<>(
                    true,
                    "area creada correctamente.",
                    createdArea
            );

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);

        } catch (Exception e) {

            ApiResponse<AreaModel> response = new ApiResponse<>(
                    false,
                    "Ocurrió un error al crear la area: " + e.getMessage(),
                    null
            );

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

    @PutMapping("/UpdateArea")
    public ResponseEntity<ApiResponse<AreaModel>> updateArea(
            @RequestBody AreaModel area) {

        try {

            // 1.verificar que la area exista
            Optional<AreaModel> existingArea =
                    serv.findByID(area.getAreaID());

            if (existingArea.isEmpty()) {

                ApiResponse<AreaModel> response = new ApiResponse<>(
                        false,
                        "El area con ID " + area.getAreaID()
                                + " no existe.",
                        null
                );

                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(response);
            }

            // 2.verificar que el nuevo nombre no exista
            Optional<AreaModel> areaWithSameName =
                    serv.findByNameIgnoreCase(area.getName());

            if (areaWithSameName.isPresent()
                    && !areaWithSameName.get()
                    .getAreaID()
                    .equals(area.getAreaID())) {

                ApiResponse<AreaModel> response = new ApiResponse<>(
                        false,
                        "Ya existe un area con el nombre: "
                                + area.getName(),
                        null
                );

                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(response);
            }

            // 3.obtener la area existente
            AreaModel areaToUpdate = existingArea.get();

            // 4.actualizar los campos
            areaToUpdate.setName(area.getName());

            // 5.guardar cambios
            AreaModel updatedArea =
                    serv.update(areaToUpdate);

            ApiResponse<AreaModel> response = new ApiResponse<>(
                    true,
                    "Area actualizada correctamente.",
                    updatedArea
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            ApiResponse<AreaModel> response = new ApiResponse<>(
                    false,
                    "Ocurrió un error al actualizar el area: "
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
