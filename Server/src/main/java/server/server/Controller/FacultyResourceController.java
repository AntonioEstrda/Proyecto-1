/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Controller;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.server.Model.Domain.FacultyResource;
import server.server.Model.Services.IFacultyResourceService;
import server.server.utilities.Labels;

/**
 *
 * @author anmon
 */
@RestController
    @RequestMapping("/FacultyResource")
public class FacultyResourceController {

    @Autowired
    private IFacultyResourceService facResService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/assign")
    public ResponseEntity<FacultyResource> assignResourceToFaculty(@RequestParam("facultyId") long facultyId,
            @RequestParam("resourceId") long resourceId) {
        Map<Labels, Object> returns = facResService.save(facultyId, resourceId);
        ArrayList<String> errors = (ArrayList<String>) returns.get(Labels.errors);
        FacultyResource fr = (FacultyResource) returns.get(Labels.objectReturn);

        if (!errors.isEmpty() || fr == null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add(Labels.errors.name(), errors.toString());
            return (new ResponseEntity<>(null, headers, HttpStatus.NOT_MODIFIED));
        }

        return (new ResponseEntity<>(fr, null, HttpStatus.ACCEPTED));
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/unassign")
    public ResponseEntity<FacultyResource> unassignResourceToFaculty(@RequestParam("facultyId") long facultyId,
            @RequestParam("resourceId") long resourceId) {
        Map<Labels, Object> returns = facResService.deactivate(facultyId, resourceId);
        ArrayList<String> errors = (ArrayList<String>) returns.get(Labels.errors);
        FacultyResource fr = (FacultyResource) returns.get(Labels.objectReturn);

        if (!errors.isEmpty() || fr == null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add(Labels.errors.name(), errors.toString());
            return (new ResponseEntity<>(null, headers, HttpStatus.NOT_MODIFIED));
        }

        return (new ResponseEntity<>(fr, null, HttpStatus.ACCEPTED));
    }
}
