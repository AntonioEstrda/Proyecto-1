/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import server.server.Controller.Utilities.Utility;
import server.server.Model.Domain.HourlyAssignment;
import server.server.Model.Services.IHourlyAssignmentService;

/**
 *
 * @author Fernando
 */
@RestController
@RequestMapping("/hourlyAssignment")
public class HourlyAssignmentController {
    @Autowired
    public IHourlyAssignmentService deptService;

    @GetMapping(value = "/all")
    public ArrayList<HourlyAssignment> all() {
        return deptService.getAll();
    }

    @GetMapping(value = "/{vinculationId}")
    @ResponseBody
    public HourlyAssignment get(@PathVariable Long vinculationId) {
        HourlyAssignment hourlyAssignment = new HourlyAssignment();
        hourlyAssignment.setVinculationId(vinculationId);
        return deptService.find(hourlyAssignment);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HourlyAssignment> add(@RequestBody @Valid HourlyAssignment hourlyAssignment, Errors errors) {
        if (errors.hasErrors()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Errors", Utility.setErrors(errors).toString());
            return new ResponseEntity<>(hourlyAssignment, headers, HttpStatus.NOT_MODIFIED);
        }

        hourlyAssignment = deptService.save(hourlyAssignment);
        if (hourlyAssignment != null) {
            return new ResponseEntity<>(hourlyAssignment, null, HttpStatus.ACCEPTED);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", "found an instance");
            return new ResponseEntity<>(hourlyAssignment, headers, HttpStatus.NOT_MODIFIED);
        }
    }

    @PutMapping
    @RequestMapping("/update")
    public ResponseEntity<HourlyAssignment> update(@RequestBody @Valid HourlyAssignment hourlyAssignment, Errors errors) {
        
        if (errors.hasErrors()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Errors", Utility.setErrors(errors).toString());
            return new ResponseEntity<>(hourlyAssignment, headers, HttpStatus.NOT_MODIFIED);
        }
        
        hourlyAssignment = deptService.update(hourlyAssignment);
        if (hourlyAssignment != null) {
            return new ResponseEntity<>(hourlyAssignment, null, HttpStatus.ACCEPTED);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", "Not found");
            return new ResponseEntity<>(hourlyAssignment, headers, HttpStatus.NOT_MODIFIED);
        }
        
    }

    @DeleteMapping
    @RequestMapping("/delete/{vinculationId}")
    public ResponseEntity<HourlyAssignment> delete(@PathVariable Long vinculationId) {

        HourlyAssignment hourlyAssignment = deptService.delete(vinculationId);
        if (hourlyAssignment != null) {
            return new ResponseEntity<>(hourlyAssignment, null, HttpStatus.ACCEPTED);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", "Not found");
            return (new ResponseEntity<>(hourlyAssignment, headers, HttpStatus.NOT_MODIFIED));
        }
    }
}
