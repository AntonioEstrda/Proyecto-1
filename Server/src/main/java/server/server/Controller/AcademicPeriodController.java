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
import server.server.Model.Domain.AcademicPeriod;
import server.server.Model.Services.IAcademicPeriodService;
import server.server.utilities.Labels;

/**
 * This one 
 * @author Fernando
 */
@RestController
@RequestMapping("/academicperiod")
public class AcademicPeriodController {
    @Autowired
    public IAcademicPeriodService academicPeriodService;

    @GetMapping(value = "/all")
    public ArrayList<AcademicPeriod> all() {
        return academicPeriodService.getAll();
    }

    @GetMapping(value = "/{academicPeriodId}")
    @ResponseBody
    public AcademicPeriod get(@PathVariable Long academicPeriodId) {
        AcademicPeriod academicPeriod = new AcademicPeriod();
        academicPeriod.setAcademicPeriodID(academicPeriodId);
        return academicPeriodService.find(academicPeriod);
    }

    @PostMapping(
            consumes = {"application/json"})
    public ResponseEntity<AcademicPeriod> add(@RequestBody @Valid AcademicPeriod academicPeriod, Errors errors) {
        HttpHeaders headers = new HttpHeaders();
        if (errors.hasErrors()) {
            ArrayList<String> setErrors = Utility.setErrors(errors);
            headers.add("Errors", Utility.setErrors(errors).toString());
            return new ResponseEntity<>(academicPeriod, headers, HttpStatus.NOT_MODIFIED);
        } else {
            Map<Labels, Object> returns = academicPeriodService.save(academicPeriod);
            ArrayList<String> errors2 = (ArrayList<String>) returns.get(Labels.errors);
            AcademicPeriod ac = (AcademicPeriod) returns.get(Labels.objectReturn);
            if (!errors2.isEmpty() || ac == null) {
                headers.add(Labels.errors.name(), errors2.toString());
                return new ResponseEntity<>(academicPeriod, headers, HttpStatus.NOT_MODIFIED);
            } else {
                return new ResponseEntity<>(academicPeriod, null, HttpStatus.ACCEPTED);

            }
        }
    }

    @PutMapping
    @RequestMapping("/update")
    public ResponseEntity<AcademicPeriod> update(@RequestBody @Valid AcademicPeriod academicPeriod, Errors errors) {
        
        HttpHeaders headers = new HttpHeaders();
        if (errors.hasErrors()) {
            ArrayList<String> setErrors = Utility.setErrors(errors);
            headers.add(Labels.errors.name(), setErrors.toString());
            return new ResponseEntity<>(academicPeriod, headers, HttpStatus.NOT_MODIFIED);
        } else {
            Map<Labels, Object> update = academicPeriodService.update(academicPeriod);
            ArrayList<String> errors2 = (ArrayList<String>) update.get(Labels.errors);
            AcademicPeriod ac = (AcademicPeriod) update.get(Labels.objectReturn);
            if (!errors2.isEmpty() || ac == null) {
                headers.add(Labels.errors.name(), errors2.toString());
                return new ResponseEntity<>(academicPeriod, headers, HttpStatus.NOT_MODIFIED);
            }
            return new ResponseEntity<>(academicPeriod, null, HttpStatus.ACCEPTED);
        }
        
    }

    @DeleteMapping
    @RequestMapping("/delete/{academicPeriodId}")
    public ResponseEntity<AcademicPeriod> delete(@PathVariable Long academicPeriodId) {

        Map<Labels, Object> delete = academicPeriodService.delete(academicPeriodId);
        ArrayList<String> errors = (ArrayList<String>) delete.get(Labels.errors);
        AcademicPeriod ac = (AcademicPeriod) delete.get(Labels.objectReturn);
        if (ac != null) {
            return new ResponseEntity<>(ac, null, HttpStatus.ACCEPTED);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add(Labels.errors.name(), errors.toString());
            return (new ResponseEntity<>(ac, headers, HttpStatus.NOT_MODIFIED));
        }
    }
}
