/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.server.Controller.Utilities.Utility;
import server.server.Model.Domain.Schedule;
import server.server.Model.Services.IScheduleService;
import server.server.utilities.Labels;

/**
 *
 * @author anmon
 */
@RestController
@RequestMapping("/Schedule")
public class ScheduleController {

    @Autowired
    public IScheduleService scheduleService;

    @GetMapping(value = "/AcademicSchedule")
    public ResponseEntity<List<Schedule>> allByProgramIdAndSemester(@RequestParam long programId,
            @RequestParam long semester) {
        Map<Labels, Object> returns = scheduleService.findByProgSem(programId, semester);
        List<Schedule> schedule = (List<Schedule>) returns.get(Labels.objectReturn);
        return (new ResponseEntity(schedule, null, HttpStatus.ACCEPTED));
    }

    @GetMapping(value = "/AcademicScheduleByEnvId")
    public ResponseEntity<List<Schedule>> allByEnv(@RequestParam("EnvironmentId") long envId) {
        Map<Labels, Object> returns = scheduleService.findByEnvId(envId);
        ArrayList<String> errors = (ArrayList<String>) returns.get(Labels.errors);
        List<Schedule> schedule = (List<Schedule>) returns.get(Labels.objectReturn);
        HttpHeaders headers = new HttpHeaders();
        if (!errors.isEmpty()) {
            headers.add(Labels.errors.name(), errors.toString());
            return (new ResponseEntity(null, headers, HttpStatus.BAD_REQUEST));
        }

        if (schedule.isEmpty()) {
            return (new ResponseEntity(schedule, null, HttpStatus.NOT_FOUND));
        }

        return (new ResponseEntity(schedule, null, HttpStatus.ACCEPTED));
    }

    @GetMapping(value = "/EventSchedule")
    public ResponseEntity<List<Schedule>> findByTypesEventsAndDepartment(@RequestParam("departId") long departId, @RequestParam("type") List<String> type) {
        Map<Labels, Object> returns = scheduleService.findByTypesEventsAndDepartment(departId, type);
        List<Schedule> schedule = (List<Schedule>) returns.get(Labels.objectReturn);
        return (new ResponseEntity(schedule, null, HttpStatus.ACCEPTED));
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<Schedule> get(@PathVariable Long id) {
        Schedule schedule = new Schedule();
        schedule.setId(id);
        ResponseEntity<Schedule> response;
        Map<Labels, Object> returns = scheduleService.find(schedule);
        schedule = (Schedule) returns.get(Labels.objectReturn);
        if (schedule != null) {
            response = new ResponseEntity(schedule, null, HttpStatus.ACCEPTED);
        } else {
            response = new ResponseEntity(null, null, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/add")
    public ResponseEntity<Schedule> add(@RequestBody @Valid Schedule schedule,
            @RequestParam("departmentId") long departmentId, Errors errors) {
        ResponseEntity<Schedule> reponse;
        HttpHeaders headers = new HttpHeaders();

        if (!errors.hasErrors()) {
            Map<Labels, Object> returns = scheduleService.add(schedule, departmentId);
            if (returns != null) {
                ArrayList<String> errors2 = (ArrayList<String>) returns.get(Labels.errors);
                Schedule schedule2 = (Schedule) returns.get(Labels.objectReturn);
                if (errors2.isEmpty()) {
                    reponse = new ResponseEntity<>(schedule2, null, HttpStatus.ACCEPTED);
                } else {
                    headers.add(Labels.errors.name(), errors2.toString());
                    reponse = new ResponseEntity<>(schedule, headers, HttpStatus.BAD_REQUEST);
                }
            } else {
                reponse = new ResponseEntity<>(schedule, null, HttpStatus.BAD_REQUEST);
            }
        } else {
            ArrayList<String> setErrors = new ArrayList();
            setErrors.addAll(Utility.setErrors(errors));
            headers.add(Labels.errors.name(), setErrors.toString());
            reponse = new ResponseEntity<>(schedule, headers, HttpStatus.BAD_REQUEST);
        }
        return reponse;
    }

    @PutMapping
    @RequestMapping("/update")
    public ResponseEntity<Schedule> update(@RequestBody @Valid Schedule schedule, @RequestParam("departmentId") long departmentId, Errors errors) {
        ResponseEntity<Schedule> reponse;
        HttpHeaders headers = new HttpHeaders();

        if (!errors.hasErrors()) {
            Map<Labels, Object> returns = scheduleService.update(schedule, departmentId);
            if (returns != null) {
                ArrayList<String> errors2 = (ArrayList<String>) returns.get(Labels.errors);
                Schedule schedule2 = (Schedule) returns.get(Labels.objectReturn);
                if (errors2.isEmpty()) {
                    reponse = new ResponseEntity<>(schedule2, null, HttpStatus.ACCEPTED);
                } else {
                    headers.add(Labels.errors.name(), errors2.toString());
                    reponse = new ResponseEntity<>(schedule, headers, HttpStatus.BAD_REQUEST);
                }
            } else {
                reponse = new ResponseEntity<>(schedule, null, HttpStatus.BAD_REQUEST);
            }
        } else {
            ArrayList<String> setErrors = new ArrayList();
            setErrors.addAll(Utility.setErrors(errors));
            headers.add(Labels.errors.name(), setErrors.toString());
            reponse = new ResponseEntity<>(schedule, headers, HttpStatus.BAD_REQUEST);
        }
        return reponse;
    }

    @DeleteMapping
    @RequestMapping("/delete")
    public ResponseEntity<Schedule> delete(@RequestParam("id") long id, @RequestParam("departmentId") long departmentId) {
        ResponseEntity<Schedule> response;
        Map<Labels, Object> returns = scheduleService.delete(id, departmentId);
        ArrayList<String> errors = (ArrayList<String>) returns.get(Labels.errors);
        Schedule schedule = (Schedule) returns.get(Labels.objectReturn);
        if (errors.isEmpty()) {
            response = new ResponseEntity<>(schedule, null, HttpStatus.ACCEPTED);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add(Labels.errors.name(), errors.toString());
            response = new ResponseEntity<>(schedule, headers, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

}
