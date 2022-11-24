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
import server.server.Model.Domain.Faculty;
import server.server.Model.Services.IFacultyService;
import server.server.utilities.Labels;

/**
 *
 * @author anmon
 */
@RestController
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    public IFacultyService facultyService;

    @GetMapping(value = "/all")
    public ArrayList<Faculty> all() {
        return facultyService.getAll();
    }

    @GetMapping(value = "/{FacultyId}")
    @ResponseBody
    public Faculty getFaculty(@PathVariable Long FacultyId) {
        Faculty faculty = new Faculty();
        faculty.setFacultyId(FacultyId);
        return facultyService.find(faculty);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Faculty> add(@RequestBody @Valid Faculty faculty, Errors errors) {

        Map<Labels, Object> returns = facultyService.save(faculty);
        
        ArrayList<String> errors2 =  (ArrayList<String>) returns.get(Labels.errors);  
        Faculty fac = (Faculty) returns.get(Labels.objectReturn);  
        
        if ( errors2.isEmpty() || fac == null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Errors", "found an instance");
            return new ResponseEntity<>(faculty, headers, HttpStatus.NOT_MODIFIED);
        } else {
            return new ResponseEntity<>(faculty, null, HttpStatus.ACCEPTED);
        }
    }

    @PutMapping
    @RequestMapping("/update")
    public ResponseEntity<Faculty> update(@RequestBody Faculty faculty) {

        faculty = facultyService.update(faculty);
        if (faculty != null) {
            return new ResponseEntity<>(faculty, null, HttpStatus.ACCEPTED);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", "Not found");
            return new ResponseEntity<>(faculty, headers, HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping
    @RequestMapping("/delete/{FacultyId}")
    public ResponseEntity<Faculty> delete(@PathVariable Long FacultyId) {

        Faculty faculty = facultyService.delete(FacultyId);
        if (faculty != null) {
            return new ResponseEntity<>(faculty, null, HttpStatus.ACCEPTED);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", "Not found");
            return (new ResponseEntity<>(faculty, headers, HttpStatus.NOT_MODIFIED));
        }
    }

}
