/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import server.server.Model.Domain.ResourceType;
import server.server.Model.Services.IResourceTypeService;

/**
 * ResourceType Controller 
 * @author anmon
 */
@RestController
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
    public ResponseEntity<ResourceType> add(@RequestBody ResourceType resType) {

        resType = resTypeService.save(resType);
        if (resType != null) {
            return new ResponseEntity<>(resType, null, HttpStatus.ACCEPTED);
        } else {
            HttpHeaders headers = new HttpHeaders();
            ArrayList k = new ArrayList();
            k.add("found an instance");
            headers.add("Error", k.toString());
            return new ResponseEntity<>(resType, headers, HttpStatus.NOT_MODIFIED);
        }
    }

    @PutMapping
    @RequestMapping("/update")
    public ResponseEntity<ResourceType> update(@RequestBody ResourceType resType) {

        resType = resTypeService.update(resType);
        if (resType != null) {
            return new ResponseEntity<>(resType, null, HttpStatus.ACCEPTED);
        } else {
            HttpHeaders headers = new HttpHeaders();
            ArrayList k = new ArrayList();
            k.add("Not found");
            headers.add("Error", k.toString());
            return new ResponseEntity<>(resType, headers, HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping
    @RequestMapping("/delete/{id}")
    public ResponseEntity<ResourceType> delete(@PathVariable Long id) {

        ResourceType resType = resTypeService.delete(id);
        if (resType != null) {
            return new ResponseEntity<>(resType, null, HttpStatus.ACCEPTED);
        } else {
            HttpHeaders headers = new HttpHeaders();
            ArrayList k = new ArrayList();
            k.add("Not found");
            headers.add("Error", k.toString());
            return (new ResponseEntity<>(resType, headers, HttpStatus.NOT_MODIFIED));
        }
    }
}
