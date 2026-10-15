package com.ticket.code.tickets.usuarios.service.implement;

import com.ticket.code.tickets.area.entity.AreaEntity;
import com.ticket.code.tickets.area.repository.AreaRepository;
import com.ticket.code.tickets.usuarios.entity.UserEntity;
import com.ticket.code.tickets.usuarios.model.UserConverter;
import com.ticket.code.tickets.usuarios.model.UserModel;
import com.ticket.code.tickets.usuarios.model.UserRole;
import com.ticket.code.tickets.usuarios.repository.UserRepository;
import com.ticket.code.tickets.usuarios.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.ticket.code.tickets.usuarios.model.UserConverter.convertToEntity;
import static com.ticket.code.tickets.usuarios.model.UserConverter.convertToModel;

@Service
@Primary
public class UserServiceJpa implements IUserService {

    @Autowired
    private UserRepository rep;

    @Autowired
    private AreaRepository areaRep;          // el repository de tu AreaEntity

   /* @Autowired
    private PasswordEncoder passwordEncoder;*/ // BCrypt (bean de Spring Security)

    @Override
    public List<UserModel> allUsers() {
        return rep.findAll()
                .stream()
                .map(UserConverter::convertToModel)
                .toList();
    }

    @Transactional
    @Override
    public UserModel save(UserModel user) {
        validateRoleAndArea(user);

        UserEntity entity = convertToEntity(user);
        entity.setUserID(null);                                   // asegura que sea INSERT
        //entity.setPassword(passwordEncoder.encode(user.getPassword()));
        entity.setArea(resolveArea(user));

        UserEntity saved = rep.save(entity);
        return convertToModel(saved);
    }

    @Transactional
    @Override
    public UserModel update(UserModel user) {

        validateRoleAndArea(user);

        // Cargamos el existente para no perder password, createdBy, etc.
        UserEntity entity = rep.findByUserID(user.getUserID());
        if (entity == null) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }

        entity.setUserName(user.getUserName());
        entity.setName(user.getName());
        entity.setApPaterno(user.getApPaterno());
        entity.setApMaterno(user.getApMaterno());
        entity.setEmail(user.getEmail());
        entity.setRole(user.getRole());
        entity.setArea(resolveArea(user));
        entity.setUpdatedBy(user.getUpdatedBy());

        // Solo cambia el password si mandan uno nuevo
        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            //entity.setPassword(passwordEncoder.encode(user.getPassword()));
            entity.setPassword(user.getPassword());
        }

        UserEntity saved = rep.save(entity);   // @PreUpdate llena updatedDate
        return convertToModel(saved);
    }

    @Transactional
    @Override
    public UserModel deleteUser(Long userID) {
        UserEntity user = rep.findByUserID(userID);

        if (user == null) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }


        user.setActive(false);//false es eliminado

        UserEntity result = rep.save(user);
        user.setDeletedDate(new Date());

        return convertToModel(result);
    }

    @Override
    public Optional<UserModel> findByID(Long userID) {
        UserEntity entity = rep.findByUserID(userID);

        if (entity == null) {
            return Optional.empty();
        }

        return Optional.of(convertToModel(entity));
    }

    @Override
    public Optional<UserModel> findByEmailIgnoreCase(String email) {

        UserEntity entity = rep.findByEmailIgnoreCase(email);

        if (entity == null) {
            return Optional.empty();
        }

        return Optional.of(convertToModel(entity));

    }

    private void validateRoleAndArea(UserModel user) {
        if (user.getRole() == null) {
            throw new IllegalArgumentException("El tipo de usuario es obligatorio");
        }
        if ((user.getRole() == UserRole.SUPERVISOR || user.getRole() == UserRole.AGENT)
                && user.getAreaID() == null) {
            throw new IllegalArgumentException("Este rol requiere un área asignada");
        }
    }

    private AreaEntity resolveArea(UserModel user) {
        // El ADMIN no lleva área aunque la manden
        if (user.getRole() == UserRole.ADMIN || user.getAreaID() == null) {
            return null;
        }
        return areaRep.findById(user.getAreaID())
                .orElseThrow(() -> new IllegalArgumentException("Área no encontrada"));
    }


}
