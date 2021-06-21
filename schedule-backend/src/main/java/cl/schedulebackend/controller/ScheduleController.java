package cl.schedulebackend.controller;

import cl.schedulebackend.model.Schedule;
import cl.schedulebackend.projection.CustomSchedule;
import cl.schedulebackend.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/api/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @GetMapping()
    public ResponseEntity<List<CustomSchedule>> getAll() {

        List<CustomSchedule> allSchedules = scheduleRepository.findAllCustomQuery();

        return new ResponseEntity<>(allSchedules, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Schedule> getOneById(@PathVariable Long id) {

        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);

        return optionalSchedule.map(schedule -> new ResponseEntity<>(schedule, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping
    public ResponseEntity<Schedule> store(@RequestBody Schedule schedule) {

        scheduleRepository.save(schedule);

        return new ResponseEntity<>(schedule, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Schedule> update(@PathVariable Long id, @RequestBody Schedule newSchedule) {

        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);

        if (optionalSchedule.isPresent()) {

            Schedule schedule = optionalSchedule.get();

            schedule.setUpdatedAt(ZonedDateTime.now());

            schedule.setTitle(newSchedule.getTitle());

            schedule.setDescription(newSchedule.getDescription());

            scheduleRepository.save(schedule);

            return new ResponseEntity<>(schedule, HttpStatus.OK);

        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);

        if (optionalSchedule.isPresent()) {

            scheduleRepository.delete(optionalSchedule.get());

            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);

        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
