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
import server.server.Controller.Utilities.Utility;
import server.server.Model.Domain.TeacherGroup;
import server.server.Model.Domain.TeacherGroup;
import server.server.Model.Services.ITeacherGroupService;
import server.server.utilities.Labels;

/**
 *
 * @author Fernando
 */
@RestController
@RequestMapping("/teacher_group")
public class TeacherGroupController {
    @Autowired
    public ITeacherGroupService teacherGroupService; 
    
    @GetMapping(value = "/all") 
    public ArrayList<TeacherGroup> all(){
        return teacherGroupService.getAll();  
    }          
   
    @GetMapping(value = "/{teacherGroupId}")
    @ResponseBody
    public TeacherGroup getTeacherGroup(@PathVariable  Long teacherGroupId) {
        TeacherGroup teacherGroup = new TeacherGroup();
        teacherGroup.setTeacherGroupID(teacherGroupId);
        return teacherGroupService.find(teacherGroup);
    }
    
    @PostMapping(
        consumes = {"application/json"})
    public ResponseEntity<TeacherGroup> add(@RequestBody TeacherGroup teacherGroup, Errors errors) {     
       HttpHeaders headers = new HttpHeaders();
        if (errors.hasErrors()) {
            ArrayList<String> setErrors = Utility.setErrors(errors);
            headers.add("Errors", Utility.setErrors(errors).toString());
            return new ResponseEntity<>(teacherGroup, headers, HttpStatus.NOT_MODIFIED);
        } else {
            Map<Labels, Object> returns = teacherGroupService.save(teacherGroup);
            ArrayList<String> errors2 = (ArrayList<String>) returns.get(Labels.errors);
            TeacherGroup th = (TeacherGroup) returns.get(Labels.objectReturn);
            if (!errors2.isEmpty() || th == null) {
                headers.add(Labels.errors.name(), errors2.toString());
                return new ResponseEntity<>(teacherGroup, headers, HttpStatus.NOT_MODIFIED);
            } else {
                return new ResponseEntity<>(teacherGroup, null, HttpStatus.ACCEPTED);

            }
        }
    }
    
    @PutMapping
    @RequestMapping("/update")
    public ResponseEntity<TeacherGroup> update(@RequestBody @Valid TeacherGroup teacherGroup, Errors errors) {
        
        HttpHeaders headers = new HttpHeaders();
        if (errors.hasErrors()) {
            ArrayList<String> setErrors = Utility.setErrors(errors);
            headers.add(Labels.errors.name(), setErrors.toString());
            return new ResponseEntity<>(teacherGroup, headers, HttpStatus.NOT_MODIFIED);
        } else {
            Map<Labels, Object> update = teacherGroupService.update(teacherGroup);
            ArrayList<String> errors2 = (ArrayList<String>) update.get(Labels.errors);
            TeacherGroup ac = (TeacherGroup) update.get(Labels.objectReturn);
            if (!errors2.isEmpty() || ac == null) {
                headers.add(Labels.errors.name(), errors2.toString());
                return new ResponseEntity<>(teacherGroup, headers, HttpStatus.NOT_MODIFIED);
            }
            return new ResponseEntity<>(teacherGroup, null, HttpStatus.ACCEPTED);
        }
        
    }
    
    @DeleteMapping
    @RequestMapping("/delete/{teacherGroupId}")
    public ResponseEntity<TeacherGroup> delete(@PathVariable Long teacherGroupId) {

        Map<Labels, Object> delete = teacherGroupService.delete(teacherGroupId);
        ArrayList<String> errors = (ArrayList<String>) delete.get(Labels.errors);
        TeacherGroup ac = (TeacherGroup) delete.get(Labels.objectReturn);
        if (ac != null) {
            return new ResponseEntity<>(ac, null, HttpStatus.ACCEPTED);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add(Labels.errors.name(), errors.toString());
            return (new ResponseEntity<>(ac, headers, HttpStatus.NOT_MODIFIED));
        }
    }    
}
