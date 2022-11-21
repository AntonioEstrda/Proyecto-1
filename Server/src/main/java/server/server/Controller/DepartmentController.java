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
import server.server.Model.Domain.Department;
import server.server.Model.Services.IDepartmentService;

/**
 *
 * @author Fernando
 */
@RestController
@RequestMapping("/Department")
public class DepartmentController {
    
    @Autowired
    public IDepartmentService deptService;

    @GetMapping(value = "/all")
    public ArrayList<Department> all() {
        return deptService.getAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Department get(@PathVariable Long id) {
        Department env = new Department();
        env.setDepartmentId(id);
        return deptService.find(env);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> add(@RequestBody @Valid Department env, Errors errors) {
        if (errors.hasErrors()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Errors", Utility.setErrors(errors).toString());
            return new ResponseEntity<>(env, headers, HttpStatus.NOT_MODIFIED);
        }

        env = deptService.save(env);
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
    public ResponseEntity<Department> update(@RequestBody @Valid Department env, Errors errors) {
        
        if (errors.hasErrors()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Errors", Utility.setErrors(errors).toString());
            return new ResponseEntity<>(env, headers, HttpStatus.NOT_MODIFIED);
        }
        
        env = deptService.update(env);
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
    public ResponseEntity<Department> delete(@PathVariable Long id) {

        Department env = deptService.delete(id);
        if (env != null) {
            return new ResponseEntity<>(env, null, HttpStatus.ACCEPTED);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", "Not found");
            return (new ResponseEntity<>(env, headers, HttpStatus.NOT_MODIFIED));
        }
    }
    
}
