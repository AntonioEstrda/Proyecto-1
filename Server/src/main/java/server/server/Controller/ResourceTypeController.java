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
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import server.server.Controller.Utilities.Utility;
import server.server.Model.Domain.ResourceType;
import server.server.Model.Services.IResourceTypeService;
import server.server.utilities.Labels;

/**
 * ResourceType Controller 
 * @author anmon
 */
@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/ResourceType")
public class ResourceTypeController {

    @Autowired
    public IResourceTypeService resTypeService;

    @GetMapping(value = "/all")
    public ArrayList<ResourceType> all() {
        return resTypeService.findAll(null);
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResourceType get(@PathVariable Long id) {
        ResourceType resType = new ResourceType();
        resType.setResourceTypeId(id);
        return resTypeService.find(resType);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResourceType> add(@RequestBody ResourceType resType, Errors errors) {

        Map<Labels, Object> returns = resTypeService.save(resType);
        ArrayList<String> errors2 = (ArrayList<String>) returns.get(Labels.errors);
        ResourceType resType2 = (ResourceType) returns.get(Labels.objectReturn);
        
        if (resType2 != null) {
            return new ResponseEntity<>(resType, null, HttpStatus.ACCEPTED);
        } else {
            HttpHeaders headers = new HttpHeaders();
            ArrayList<String> setErrors = Utility.setErrors(errors);
            errors2.addAll(setErrors); 
            headers.add(Labels.errors.name(), errors2.toString());
            return new ResponseEntity<>(resType, headers, HttpStatus.NOT_MODIFIED);
        }
    }

    @PutMapping
    @RequestMapping("/update")
    public ResponseEntity<ResourceType> update(@RequestBody ResourceType resType, Errors errors) {

        Map<Labels, Object> returns = resTypeService.update(resType);
        ArrayList<String> errors2 = (ArrayList<String>) returns.get(Labels.errors);
        ResourceType resType2 = (ResourceType) returns.get(Labels.objectReturn);
        
        if (resType2 != null) {
            return new ResponseEntity<>(resType, null, HttpStatus.ACCEPTED);
        } else {
            HttpHeaders headers = new HttpHeaders();
            ArrayList<String> setErrors = Utility.setErrors(errors);
            errors2.addAll(setErrors); 
            headers.add(Labels.errors.name(), errors2.toString());
            return new ResponseEntity<>(resType, headers, HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping
    @RequestMapping("/delete/{id}")
    public ResponseEntity<ResourceType> delete(@PathVariable Long id) {
        Map<Labels, Object> returns = resTypeService.delete(id);
        ArrayList<String> errors = (ArrayList<String>) returns.get(Labels.errors);
        ResourceType resType = (ResourceType) returns.get(Labels.objectReturn);
        if (resType != null) {
            return new ResponseEntity<>(resType, null, HttpStatus.ACCEPTED);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add(Labels.errors.name(), errors.toString());
            return (new ResponseEntity<>(resType, headers, HttpStatus.NOT_MODIFIED));
        }
    }
}
