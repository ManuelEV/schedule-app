package cl.schedulebackend.controller;

import cl.schedulebackend.model.Schedule;
import cl.schedulebackend.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @GetMapping()
    public ResponseEntity<List<Schedule>> getAll() {

        List<Schedule> allSchedules = scheduleRepository.findAll();

        return new ResponseEntity<>(allSchedules, HttpStatus.OK);
    }

}
