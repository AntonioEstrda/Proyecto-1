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
import server.server.Model.Domain.Department;
import server.server.Model.Services.IDepartmentService;
import server.server.utilities.Labels;

/**
 *
 * @author Fernando
 */
@RestController
@RequestMapping("/department")
@PreAuthorize("hasRole('ADMIN')")
public class DepartmentController {

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
        HttpHeaders headers = new HttpHeaders();
        if (errors.hasErrors()) {
            ArrayList<String> setErrors = Utility.setErrors(errors);
            headers.add(Labels.errors.name(), setErrors.toString());
            return new ResponseEntity<>(department, headers, HttpStatus.NOT_MODIFIED);
        } else {
            Map<Labels, Object> returns = deptService.save(department);
            ArrayList<String> errors2 = (ArrayList<String>) returns.get(Labels.errors);
            Department dept = (Department) returns.get(Labels.objectReturn);
            if (!errors2.isEmpty() || dept == null) {
                headers.add(Labels.errors.name(), errors2.toString());
                return new ResponseEntity<>(department, headers, HttpStatus.NOT_MODIFIED);
            } else {
                return new ResponseEntity<>(department, null, HttpStatus.ACCEPTED);
            }
        }

    }

    @PutMapping
    @RequestMapping("/update")
    public ResponseEntity<Department> update(@RequestBody @Valid Department department, Errors errors) {
        HttpHeaders headers = new HttpHeaders();
        if (errors.hasErrors()) {
            ArrayList<String> setErrors = Utility.setErrors(errors);
            headers.add(Labels.errors.name(), setErrors.toString());
            return new ResponseEntity<>(department, headers, HttpStatus.NOT_MODIFIED);
        } else {
            Map<Labels, Object> update = deptService.update(department);
            ArrayList<String> errors2 = (ArrayList<String>) update.get(Labels.errors);
            Department dept = (Department) update.get(Labels.objectReturn);
            if (!errors2.isEmpty() || dept == null) {
                headers.add(Labels.errors.name(), errors2.toString());
                return new ResponseEntity<>(department, headers, HttpStatus.NOT_MODIFIED);
            }
            return new ResponseEntity<>(department, null, HttpStatus.ACCEPTED);
        }

    }

    @DeleteMapping
    @RequestMapping("/delete/{departmentId}")
    public ResponseEntity<Department> delete(@PathVariable Long departmentId) {

        Map<Labels, Object> delete = deptService.delete(departmentId);
        ArrayList<String> errors = (ArrayList<String>) delete.get(Labels.errors);
        Department dept = (Department) delete.get(Labels.objectReturn);
        if (dept != null) {
            return new ResponseEntity<>(dept, null, HttpStatus.ACCEPTED);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add(Labels.errors.name(), errors.toString());
            return (new ResponseEntity<>(dept, headers, HttpStatus.NOT_MODIFIED));
        }
    }
}
