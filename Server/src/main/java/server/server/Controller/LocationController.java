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
import org.springframework.security.access.prepost.PreAuthorize;
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
import server.server.Model.Domain.Location;
import server.server.Model.Services.ILocationService;
import server.server.utilities.Labels;

/**
 *
 * @author anmon
 */
@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/location")
public class LocationController {

    @Autowired
    public ILocationService locService;

    @GetMapping(value = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Location>> all() {
        ResponseEntity<ArrayList<Location>> responseEntity;
        List<Location> all = locService.all();
        responseEntity = new ResponseEntity<>((ArrayList<Location>) all, null, HttpStatus.ACCEPTED);
        return responseEntity;
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Location> get(@PathVariable Long id) {
        Location l = locService.find(id);
        ResponseEntity responseEntity = new ResponseEntity<>(l, null, HttpStatus.ACCEPTED);
        return responseEntity;
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/add")
    public ResponseEntity<Location> add(@RequestBody @Valid Location location, Errors errors) {

        Map<Labels, Object> returns = locService.save(location);
        ArrayList<String> errors2 = (ArrayList<String>) returns.get(Labels.errors);
        Location location2 = (Location) returns.get(Labels.objectReturn);
        ResponseEntity responseEntity;

        if (location2 != null) {
            responseEntity = new ResponseEntity<>(location2, null, HttpStatus.ACCEPTED);
        } else {
            HttpHeaders headers = new HttpHeaders();
            ArrayList<String> setErrors = Utility.setErrors(errors);
            errors2.addAll(setErrors);
            headers.add(Labels.errors.name(), errors2.toString());
            responseEntity = new ResponseEntity<>(location, headers, HttpStatus.NOT_MODIFIED);
        }

        return responseEntity;
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/update")
    public ResponseEntity<Location> update(@RequestBody Location location, Errors errors) {
        ResponseEntity<Location> responseEntity;
        Map<Labels, Object> returns = locService.update(location);
        ArrayList<String> errors2 = (ArrayList<String>) returns.get(Labels.errors);
        Location location2 = (Location) returns.get(Labels.objectReturn);

        if (location2 != null) {
            responseEntity = new ResponseEntity<>(location2, null, HttpStatus.ACCEPTED);
        } else {
            HttpHeaders headers = new HttpHeaders();
            ArrayList<String> setErrors = Utility.setErrors(errors);
            errors2.addAll(setErrors);
            headers.add(Labels.errors.name(), errors2.toString());
            responseEntity = new ResponseEntity<>(location, headers, HttpStatus.NOT_MODIFIED);
        }

        return responseEntity;
    }

    @DeleteMapping
    @RequestMapping("/delete/{id}")
    public ResponseEntity<Location> delete(@PathVariable Long id) {

        ResponseEntity<Location> responseEntity;
        Map<Labels, Object> returns = locService.delete(id);
        ArrayList<String> errors = (ArrayList<String>) returns.get(Labels.errors);
        Location location = (Location) returns.get(Labels.objectReturn);
        if (location != null) {
            responseEntity = new ResponseEntity<>(location, null, HttpStatus.ACCEPTED);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add(Labels.errors.name(), errors.toString());
            responseEntity = (new ResponseEntity<>(location, headers, HttpStatus.NOT_MODIFIED));
        }
        return responseEntity;
    }
}
