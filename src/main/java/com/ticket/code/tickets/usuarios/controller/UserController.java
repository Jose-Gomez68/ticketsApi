package com.ticket.code.tickets.usuarios.controller;

import com.ticket.code.tickets.usuarios.model.UserModel;
import com.ticket.code.tickets.usuarios.service.IUserService;
import com.ticket.code.tickets.utils.modelsutils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService userServ;

    @GetMapping(path = "/getAllUsers",produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResponse<List<UserModel>>> getAllUsers() {

        List<UserModel> list = userServ.allUsers();

        String message = list.isEmpty() ? "No se encontraron usuarios" : "Usuarios encontrados";
        ApiResponse<List<UserModel>> response = new ApiResponse<>(
                true,
                message,
                list
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping( path = "/{userID}", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<ApiResponse<UserModel>> getUserByID( @PathVariable("userID") Long userID) {

        Optional<UserModel> user = userServ.findByID(userID);

        if (user.isPresent()) {

            ApiResponse<UserModel> response = new ApiResponse<>(
                    true,
                    "Usuario encontrado",
                    user.get()
            );

            return ResponseEntity.ok(response);

        }

        ApiResponse<UserModel> response = new ApiResponse<>(
                false,
                "Usuario no encontrado",
                null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }

    @PostMapping("/CreateUser")
    public ResponseEntity<ApiResponse<UserModel>> createUser(@RequestBody UserModel user) {

        try {

            Optional<UserModel> existingUser =
                    userServ.findByEmailIgnoreCase(user.getEmail());

            if (existingUser.isPresent()){
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(new ApiResponse<>(
                                false,
                                "Ya existe un usuario con el correo: "
                                        + user.getEmail(),
                                user
                        ));
            }

            // El ID lo genera la BD
            user.setUserID(null);

            UserModel createdUser = userServ.save(user);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(
                            true,
                            "Usuario creado correctamente",
                            createdUser
                    ));

        }catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(
                            false,
                            "Ocurrió un error al crear el usuario: "
                                    + e.getMessage(),
                            null
                    ));
        }

    }

    @PutMapping("/UpdateCategory")
    public ResponseEntity<ApiResponse<UserModel>> updateUser(
            @RequestBody UserModel user) {

        try {

            // 1.verificar que la categoría exista
            Optional<UserModel> existingUser =
                    userServ.findByID(user.getUserID());

            if (existingUser.isEmpty()) {

                ApiResponse<UserModel> response = new ApiResponse<>(
                        false,
                        "El usuario con ID " + user.getUserID()
                                + " no existe.",
                        null
                );

                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(response);
            }

            // 2.verificar que el nuevo usuario no exista
            Optional<UserModel> userWithSameEmail =
                    userServ.findByEmailIgnoreCase(user.getEmail());

            if (userWithSameEmail.isPresent()
                    && !userWithSameEmail.get()
                    .getUserID()
                    .equals(user.getUserID())) {

                ApiResponse<UserModel> response = new ApiResponse<>(
                        false,
                        "Ya existe una categoría con el nombre: "
                                + user.getName(),
                        null
                );

                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(response);
            }

            // 3.obtener la categoría existente
            UserModel userToUpdate = existingUser.get();

            // 4.actualizar los campos
            userToUpdate.setName(user.getName());//todo AGREGAR TODOS LOS CAMPOS
            userToUpdate.setApPaterno(user.getApPaterno());
            userToUpdate.setApMaterno(user.getApMaterno());

            // 5.guardar cambios
            UserModel updatedUser =
                    userServ.update(userToUpdate);

            ApiResponse<UserModel> response = new ApiResponse<>(
                    true,
                    "El usuario fue actualizada correctamente.",
                    updatedUser
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            ApiResponse<UserModel> response = new ApiResponse<>(
                    false,
                    "Ocurrió un error al actualizar al usuario: "
                            + e.getMessage(),
                    null
            );

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

}
