/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import server.server.Controller.Utilities.Utility;
import server.server.Model.Domain.HourlyAssignment;
import server.server.Model.Services.IHourlyAssignmentService;
import server.server.utilities.Labels;

/**
 *
 * @author Fernando
 */
@RestController
@RequestMapping("/hourlyassignment")
public class HourlyAssignmentController {

    @Autowired
    public IHourlyAssignmentService hourlyAssignmentService;

    @GetMapping(value = "/all")
    public ArrayList<HourlyAssignment> all() {
        return hourlyAssignmentService.getAll();
    }

    @GetMapping(value = "/{HourlyAssignmentId}")
    @ResponseBody
    public HourlyAssignment get(@PathVariable Long HourlyAssignmentId) {
        HourlyAssignment hourlyAssignment = new HourlyAssignment();
        hourlyAssignment.setVinculationId(HourlyAssignmentId);
        return hourlyAssignmentService.find(hourlyAssignment);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HourlyAssignment> add(@RequestBody @Valid HourlyAssignment hourlyAssignment, Errors errors) {
        HttpHeaders headers = new HttpHeaders();
        if (errors.hasErrors()) {
            headers.add("Errors", Utility.setErrors(errors).toString());
            return new ResponseEntity<>(hourlyAssignment, headers, HttpStatus.NOT_MODIFIED);
        } else {
            Map<Labels, Object> returns = hourlyAssignmentService.save(hourlyAssignment);
            ArrayList<String> errors2 = (ArrayList<String>) returns.get(Labels.errors);
            HourlyAssignment subj = (HourlyAssignment) returns.get(Labels.objectReturn);
            if (!errors2.isEmpty() || subj == null) {
                headers.add(Labels.errors.name(), errors2.toString());
                return new ResponseEntity<>(hourlyAssignment, headers, HttpStatus.NOT_MODIFIED);
            } else {
                return new ResponseEntity<>(hourlyAssignment, null, HttpStatus.ACCEPTED);

            }
        }
    }

    @PutMapping
    @RequestMapping("/update")
    public ResponseEntity<HourlyAssignment> update(@RequestBody HourlyAssignment hourlyAssignment, Errors errors) {
        HttpHeaders headers = new HttpHeaders();
        if (errors.hasErrors()) {
            ArrayList<String> setErrors = Utility.setErrors(errors);
            headers.add(Labels.errors.name(), setErrors.toString());
            return new ResponseEntity<>(hourlyAssignment, headers, HttpStatus.NOT_MODIFIED);
        } else {
            Map<Labels, Object> update = hourlyAssignmentService.update(hourlyAssignment);
            ArrayList<String> errors2 = (ArrayList<String>) update.get(Labels.errors);
            HourlyAssignment subj = (HourlyAssignment) update.get(Labels.objectReturn);
            if (!errors2.isEmpty() || subj == null) {
                headers.add(Labels.errors.name(), errors2.toString());
                return new ResponseEntity<>(hourlyAssignment, headers, HttpStatus.NOT_MODIFIED);
            }
            return new ResponseEntity<>(hourlyAssignment, null, HttpStatus.ACCEPTED);
        }
    }

    @DeleteMapping
    @RequestMapping("/delete/{vinculationId}")
    public ResponseEntity<HourlyAssignment> delete(@PathVariable Long vinculationId
    ) {
        Map<Labels, Object> delete = hourlyAssignmentService.delete(vinculationId);
        ArrayList<String> errors = (ArrayList<String>) delete.get(Labels.errors);
        HourlyAssignment subj = (HourlyAssignment) delete.get(Labels.objectReturn);
        if (subj != null) {
            return new ResponseEntity<>(subj, null, HttpStatus.ACCEPTED);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add(Labels.errors.name(), errors.toString());
            return (new ResponseEntity<>(subj, headers, HttpStatus.NOT_MODIFIED));
        }
    }
}
