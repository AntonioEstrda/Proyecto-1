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
@RequestMapping("/groupt")
public class GroupController {
    @Autowired
    public IDepartmentService deptService;

    @GetMapping(value = "/all")
    public ArrayList<Department> all() {
        return deptService.getAll();
    }

    @GetMapping(value = "/{departmentId}")
    @ResponseBody
    public Department get(@PathVariable Long departmentId) {
        Department department = new Department();
        department.setDepartmentId(departmentId);
        return deptService.find(department);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> add(@RequestBody @Valid Department department, Errors errors) {
        if (errors.hasErrors()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Errors", Utility.setErrors(errors).toString());
            return new ResponseEntity<>(department, headers, HttpStatus.NOT_MODIFIED);
        }

        department = deptService.save(department);
        if (department != null) {
            return new ResponseEntity<>(department, null, HttpStatus.ACCEPTED);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", "found an instance");
            return new ResponseEntity<>(department, headers, HttpStatus.NOT_MODIFIED);
        }
    }

    @PutMapping
    @RequestMapping("/update")
    public ResponseEntity<Department> update(@RequestBody @Valid Department department, Errors errors) {
        
        if (errors.hasErrors()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Errors", Utility.setErrors(errors).toString());
            return new ResponseEntity<>(department, headers, HttpStatus.NOT_MODIFIED);
        }
        
        department = deptService.update(department);
        if (department != null) {
            return new ResponseEntity<>(department, null, HttpStatus.ACCEPTED);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", "Not found");
            return new ResponseEntity<>(department, headers, HttpStatus.NOT_MODIFIED);
        }
        
    }

    @DeleteMapping
    @RequestMapping("/delete/{departmentId}")
    public ResponseEntity<Department> delete(@PathVariable Long departmentId) {

        Department department = deptService.delete(departmentId);
        if (department != null) {
            return new ResponseEntity<>(department, null, HttpStatus.ACCEPTED);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", "Not found");
            return (new ResponseEntity<>(department, headers, HttpStatus.NOT_MODIFIED));
        }
    }
}
