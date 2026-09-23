package com.ticket.code.tickets.kind.repository;

import com.ticket.code.tickets.kind.entity.KindEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KindRepository extends JpaRepository<KindEntity,Long> {

    KindEntity findByKindID(Long kindID);

    KindEntity findByNameIgnoreCase(String name);

}
