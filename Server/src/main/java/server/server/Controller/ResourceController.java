/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Controller;

import server.server.Controller.Utilities.Utility;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.server.Model.Domain.FacultyResource;
import server.server.Model.Domain.Resource;
import server.server.Model.Services.IFacultyResourceService;
import server.server.Model.Services.IResourceService;
import server.server.utilities.Labels;

/**
 * Resource Controller
 *
 * @author anmon
 */
@RestController
@RequestMapping("/Resource")
public class ResourceController {

    @Autowired
    public IResourceService envService;

    @Autowired
    public IFacultyResourceService facResService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Resource> all(@RequestParam("facultyId") long facultyId) {
        return facResService.findByFacultyIdRes(facultyId);
    }

    @GetMapping(value = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource> get(@RequestParam("facultyId") long facultyId, @RequestParam("resourceId") long resourceId) {
        FacultyResource returns = facResService.findByFacultyIdResourceId(facultyId, resourceId);
        if (returns == null) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(returns.getResourceFR(), null, HttpStatus.OK);
        }
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/add")
    public ResponseEntity<Resource> add(
            @RequestParam("facultyId") long facultyId,
            @RequestBody @Valid Resource env, Errors errors) {
        if (errors.hasErrors()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add(Labels.errors.name(), Utility.setErrors(errors).toString());
            return new ResponseEntity<>(env, headers, HttpStatus.NOT_MODIFIED);
        } else {
            Map<Labels, Object> returns = envService.save(env);
            ArrayList<String> errors2 = (ArrayList<String>) returns.get(Labels.errors);
            Resource res2 = (Resource) returns.get(Labels.objectReturn);

            if (!errors2.isEmpty() || res2 == null) {
                HttpHeaders headers = new HttpHeaders();
                headers.add(Labels.errors.name(), errors2.toString());
                return new ResponseEntity<>(env, headers, HttpStatus.NOT_MODIFIED);
            } else {
                return new ResponseEntity<>(env, null, HttpStatus.ACCEPTED);
            }

        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/update")
    public ResponseEntity<Resource> update(@RequestBody @Valid Resource res, Errors errors) {
        HttpHeaders headers = new HttpHeaders();
        if (errors.hasErrors()) {
            ArrayList<String> setErrors = Utility.setErrors(errors);
            headers.add(Labels.errors.name(), setErrors.toString());
            return new ResponseEntity<>(res, headers, HttpStatus.NOT_MODIFIED);
        } else {
            Map<Labels, Object> returns = envService.update(res);
            ArrayList<String> errors2 = (ArrayList<String>) returns.get(Labels.errors);
            Resource res2 = (Resource) returns.get(Labels.objectReturn);
            if (!errors2.isEmpty() || res2 == null) {
                headers.add(Labels.errors.name(), errors2.toString());
                return new ResponseEntity<>(res, headers, HttpStatus.NOT_MODIFIED);
            }
            return new ResponseEntity<>(res, null, HttpStatus.ACCEPTED);
        }

    }

    @DeleteMapping
    @RequestMapping("/delete/{id}")
    public ResponseEntity<Resource> delete(@PathVariable Long id) {

        Map<Labels, Object> returns = envService.delete(id);
        ArrayList<String> errors = (ArrayList<String>) returns.get(Labels.errors);
        Resource res = (Resource) returns.get(Labels.objectReturn);

        if (res != null) {
            return new ResponseEntity<>(res, null, HttpStatus.ACCEPTED);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add(Labels.errors.name(), errors.toString());
            return (new ResponseEntity<>(res, headers, HttpStatus.NOT_MODIFIED));
        }
    }
}
