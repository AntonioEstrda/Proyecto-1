/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Controller;

import server.server.Controller.Utilities.Utility;
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
import server.server.Model.Domain.Environment;
import server.server.Model.Services.IEnvironmentService;

/**
 *
 * @author anmon
 */
@RestController
@RequestMapping("/Environment")
public class EnvironmentController {

    @Autowired
    public IEnvironmentService envService;

    @GetMapping(value = "/all")
    public ArrayList<Environment> all() {
        return envService.getAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Environment get(@PathVariable Long id) {
        Environment env = new Environment();
        env.setEnvironmentId(id);
        return envService.find(env);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Environment> add(@RequestBody @Valid Environment env, Errors errors) {
        if (errors.hasErrors()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Errors", Utility.setErrors(errors).toString());
            return new ResponseEntity<>(env, headers, HttpStatus.NOT_MODIFIED);
        }

        env = envService.save(env);
        if (env != null) {
            return new ResponseEntity<>(env, null, HttpStatus.ACCEPTED);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", "found an instance");
            return new ResponseEntity<>(env, headers, HttpStatus.NOT_MODIFIED);
        }
    }

    @PutMapping
    @RequestMapping("/update")
    public ResponseEntity<Environment> update(@RequestBody @Valid Environment env, Errors errors) {
        
        if (errors.hasErrors()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Errors", Utility.setErrors(errors).toString());
            return new ResponseEntity<>(env, headers, HttpStatus.NOT_MODIFIED);
        }
        
        env = envService.update(env);
        if (env != null) {
            return new ResponseEntity<>(env, null, HttpStatus.ACCEPTED);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", "Not found");
            return new ResponseEntity<>(env, headers, HttpStatus.NOT_MODIFIED);
        }
        
    }

    @DeleteMapping
    @RequestMapping("/delete/{id}")
    public ResponseEntity<Environment> delete(@PathVariable Long id) {

        Environment env = envService.delete(id);
        if (env != null) {
            return new ResponseEntity<>(env, null, HttpStatus.ACCEPTED);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", "Not found");
            return (new ResponseEntity<>(env, headers, HttpStatus.NOT_MODIFIED));
        }
    }
}
