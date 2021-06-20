package cl.schedulebackend.controller;

import cl.schedulebackend.model.Contact;
import cl.schedulebackend.model.Schedule;
import cl.schedulebackend.repository.ContactRepository;
import cl.schedulebackend.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @GetMapping
    public ResponseEntity<Page<Contact>> getAll() {

        Pageable firstPageWithFiveElements = PageRequest.of(0, 5);

        Page<Contact> allContacts = contactRepository.findAll(firstPageWithFiveElements);

        return new ResponseEntity<>(allContacts, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Contact> getOneById(@PathVariable("id") Long id) {

        Optional<Contact> optionalContact = contactRepository.findById(id);

        return optionalContact.map(contact -> new ResponseEntity<>(contact, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping
    public ResponseEntity<Contact> store(@RequestParam("scheduleId") Long scheduleId, @RequestBody Contact contact) {

        Optional<Schedule> optionalSchedule = scheduleRepository.findById(scheduleId);

        if (optionalSchedule.isPresent()) {

            contact.setSchedule(optionalSchedule.get());

            this.contactRepository.save(contact);

            return new ResponseEntity<>(contact, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @PutMapping("{id}")
    public ResponseEntity<Contact> update(@PathVariable Long id, @RequestBody Contact newContact) {

        Optional<Contact> optionalContact = contactRepository.findById(id);

        if (optionalContact.isPresent()){

            Contact contact = optionalContact.get();

            contact.setUpdatedAt(ZonedDateTime.now());

            contact.setName(newContact.getName());

            contact.setPhoneNumber(newContact.getPhoneNumber());

            contactRepository.save(contact);

            return new ResponseEntity<>(contact, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {

        Optional<Contact> optionalContact = contactRepository.findById(id);

        if (optionalContact.isPresent()) {
            contactRepository.delete(optionalContact.get());
            return new ResponseEntity<>("Deleted successfully ", HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


}
