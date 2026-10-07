package com.ticket.code.tickets.project.controller;

import com.ticket.code.tickets.project.model.ProjectModel;
import com.ticket.code.tickets.project.service.IProjectService;
import com.ticket.code.tickets.utils.modelsutils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private IProjectService serv;

    @GetMapping("/getAllProject")
    public ResponseEntity<ApiResponse<List<ProjectModel>>> getAllProject() {

        List<ProjectModel> list = serv.allProject();
        String message = list.isEmpty() ? "No se encontro projects" : "projects encontrados.";
        ApiResponse<List<ProjectModel>> respose = new ApiResponse<>(
                true,
                message,
                list
        );

        return new ResponseEntity<>(respose, HttpStatus.OK);

    }

    @GetMapping("/{projectID}")
    public ResponseEntity<ApiResponse<ProjectModel>> getProjectByID(@PathVariable("projectID") Long projectID) {

        Optional<ProjectModel> project = serv.findByID(projectID);

        if (project.isPresent()){
            ApiResponse<ProjectModel> resp = new ApiResponse<>(
                    true,
                    "Project encontrado",
                    project.get()
            );

            return ResponseEntity.ok(resp);
        }

        ApiResponse<ProjectModel> resp = new ApiResponse<>(
                false,
                "Project no encontrado",
                null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);

    }

    @PostMapping("/createProject")
    public ResponseEntity<ApiResponse<ProjectModel>> createProject(
            @RequestBody ProjectModel project) {
        try {

            Optional<ProjectModel> existingProject =
                    serv.findByNameIgnoreCase(project.getName());

            if (existingProject.isPresent()) {

                ApiResponse<ProjectModel> response = new ApiResponse<>(
                        false,
                        "Ya existe un project con el nombre: "
                                + project.getName(),
                        null
                );

                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(response);
            }

            ProjectModel createdProject = serv.save(project);

            ApiResponse<ProjectModel> response = new ApiResponse<>(
                    true,
                    "Project creado correctamente.",
                    createdProject
            );

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);

        } catch (Exception e) {

            ApiResponse<ProjectModel> response = new ApiResponse<>(
                    false,
                    "Ocurrió un error al crear el project: " + e.getMessage(),
                    null
            );

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

    @PutMapping("/UpdateProject")
    public ResponseEntity<ApiResponse<ProjectModel>> updateProject(
            @RequestBody ProjectModel project) {

        try {

            // 1.verificar que la project exista
            Optional<ProjectModel> existingProject =
                    serv.findByID(project.getProjectID());

            if (existingProject.isEmpty()) {

                ApiResponse<ProjectModel> response = new ApiResponse<>(
                        false,
                        "El project con ID " + project.getProjectID()
                                + " no existe.",
                        null
                );

                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(response);
            }

            // 2.verificar que el nuevo nombre no exista
            Optional<ProjectModel> projectWithSameName =
                    serv.findByNameIgnoreCase(project.getName());

            if (projectWithSameName.isPresent()
                    && !projectWithSameName.get()
                    .getProjectID()
                    .equals(project.getProjectID())) {

                ApiResponse<ProjectModel> response = new ApiResponse<>(
                        false,
                        "Ya existe un project con el nombre: "
                                + project.getName(),
                        null
                );

                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(response);
            }

            // 3.obtener la project existente
            ProjectModel projectToUpdate = existingProject.get();

            // 4.actualizar los campos
            projectToUpdate.setName(project.getName());

            // 5.guardar cambios
            ProjectModel updatedProject =
                    serv.update(projectToUpdate);

            ApiResponse<ProjectModel> response = new ApiResponse<>(
                    true,
                    "Project actualizada correctamente.",
                    updatedProject
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            ApiResponse<ProjectModel> response = new ApiResponse<>(
                    false,
                    "Ocurrió un error al actualizar el project: "
                            + e.getMessage(),
                    null
            );

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

    @DeleteMapping("/DeleteProject/{projectID}")
    public ResponseEntity<ApiResponse<ProjectModel>> deleteProject (@PathVariable("projectID") Long projectID) {

        try {

            // 1.verificar que la project exista
            Optional<ProjectModel> existingProject =
                    serv.findByID(projectID);

            if (existingProject.isEmpty()) {

                ApiResponse<ProjectModel> response = new ApiResponse<>(
                        false,
                        "El project con ID " + projectID + " no existe.",
                        null
                );

                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(response);
            }

            // 2.eliminar project
            serv.deleteProject(projectID);

            ApiResponse<ProjectModel> response = new ApiResponse<>(
                    true,
                    "Project eliminado correctamente.",
                    existingProject.get()
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            ApiResponse<ProjectModel> response = new ApiResponse<>(
                    false,
                    "Ocurrió un error al eliminar el project: "
                            + e.getMessage(),
                    null
            );

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }

    }

}
