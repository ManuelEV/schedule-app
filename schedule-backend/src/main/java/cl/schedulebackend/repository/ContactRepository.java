package cl.schedulebackend.repository;

import cl.schedulebackend.model.Contact;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.List;

public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {

    List<Contact> findAllByName(String name, Pageable pageable);

}
