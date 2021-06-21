package cl.schedulebackend.repository;

import cl.schedulebackend.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findAllByScheduleId(Long id);

}
