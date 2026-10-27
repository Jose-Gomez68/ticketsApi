package com.ticket.code.tickets.ticket.service.implement;

import com.ticket.code.tickets.Priority.repository.PriorityRepository;
import com.ticket.code.tickets.area.repository.AreaRepository;
import com.ticket.code.tickets.kind.repository.KindRepository;
import com.ticket.code.tickets.project.model.ProjectConverter;
import com.ticket.code.tickets.project.repository.ProjectRepository;
import com.ticket.code.tickets.status.repository.StatusRepository;
import com.ticket.code.tickets.ticket.entity.TicketEntity;
import com.ticket.code.tickets.ticket.model.TicketConverter;
import com.ticket.code.tickets.ticket.model.TicketCreateModel;
import com.ticket.code.tickets.ticket.model.TicketModel;
import com.ticket.code.tickets.ticket.repository.TicketRepository;
import com.ticket.code.tickets.ticket.service.ITicketService;
import com.ticket.code.tickets.usuarios.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ticket.code.tickets.ticket.model.TicketConverter.convertToModel;

@Service
@Primary
public class TicketServiceJpa implements ITicketService {

    @Autowired
    private TicketRepository rep;

    @Autowired
    private KindRepository kindRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private PriorityRepository priorityRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public List<TicketModel> allTicket() {
        return rep.findAll()
                .stream()
                .map(TicketConverter::convertToModel)
                .toList();
    }

    @Override
    public Page<TicketModel> findByActiveTruePage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.DESC, "ticketID")
        );

        return rep.findAll(pageable)
                .map(TicketConverter::convertToModel);//TODO Modificar para que solo traiga a las que no estan eliminadas osea active=false
    }

    @Override
    public TicketModel save(TicketCreateModel ticket) {
        /*System.out.println("=== INICIO CREACIÓN TICKET ===");
        System.out.println("Título: " + ticket.getTitle());
        System.out.println("Kind: " + ticket.getKind());
        System.out.println("User: " + ticket.getUser());
        System.out.println("AssignedID: " + ticket.getAssignedID());
        System.out.println("Project: " + ticket.getProject());
        System.out.println("AreaID: " + ticket.getAreaID());
        System.out.println("PriorityID: " + ticket.getPriorityID());
        System.out.println("StatusID: " + ticket.getStatusID());*/

        TicketEntity entity = convertToEntity2(ticket);
        //System.out.println("=== ENTIDADES RELACIONADAS CARGADAS ===");

        TicketEntity ticketResult = rep.save(entity);
        //System.out.println("=== TICKET GUARDADO ===");

        TicketModel result = convertToModel(ticketResult);
        //System.out.println("=== TICKET CONVERTIDO A MODELO ===");
        return result;
    }

    @Override
    public TicketModel update(TicketCreateModel ticket) {
        TicketEntity ticketResult = rep.save(convertToEntity2(ticket));

        return convertToModel(ticketResult);
    }

    @Override
    public TicketModel deleteTicket(Long ticketID) {
        TicketEntity ticket = rep.findByTicketID(ticketID);

        ticket.setActive(false);//false es eliminado

        TicketEntity result = rep.save(ticket);

        return convertToModel(result);
    }

    @Override
    public Optional<TicketModel> findByID(Long ticketID) {
        TicketEntity entity = rep.findByTicketID(ticketID);

        if (entity == null) {
            return Optional.empty();
        }

        return Optional.of(convertToModel(entity));
    }

    @Override
    public Optional<TicketModel> findByTitleIgnoreCase(String name) {
        TicketEntity entity = rep.findByTitleIgnoreCase(name);

        if (entity == null) {
            return Optional.empty();
        }

        return Optional.of(convertToModel(entity));
    }


    private TicketEntity convertToEntity2(TicketCreateModel model) {

        TicketEntity entity = new TicketEntity();

        entity.setTicketID(model.getTicketID());
        entity.setTitle(model.getTitle());
        entity.setDescription(model.getDescription());

        // Valores predeterminados para un ticket nuevo
        entity.setActive(true);

        // Buscar las entidades relacionadas por su ID
        entity.setKind(
                kindRepository.findById(model.getKind())
                        .orElseThrow(() ->
                                new RuntimeException("Tipo de ticket no encontrado"))
        );

        entity.setUser(
                userRepository.findById(model.getUser())
                        .orElseThrow(() ->
                                new RuntimeException("Usuario creador no encontrado"))
        );

        // El ticket puede crearse sin un usuario asignado
        if (model.getAssignedID() != null) {
            entity.setAssignedID(
                    userRepository.findById(model.getAssignedID())
                            .orElseThrow(() ->
                                    new RuntimeException("Usuario asignado no encontrado"))
            );
        }

        entity.setProject(
                projectRepository.findById(model.getProject())
                        .orElseThrow(() ->
                                new RuntimeException("Proyecto no encontrado"))
        );

        entity.setAreaID(
                areaRepository.findById(model.getAreaID())
                        .orElseThrow(() ->
                                new RuntimeException("Área no encontrada"))
        );

        entity.setPriorityID(
                priorityRepository.findById(model.getPriorityID())
                        .orElseThrow(() ->
                                new RuntimeException("Prioridad no encontrada"))
        );

        entity.setStatusID(
                statusRepository.findById(model.getStatusID())
                        .orElseThrow(() ->
                                new RuntimeException("Estado no encontrado"))
        );

        return entity;
    }

}
