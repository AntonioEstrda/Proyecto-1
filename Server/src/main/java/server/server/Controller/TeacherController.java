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
import server.server.Model.Domain.Teacher;
import server.server.Model.Services.ITeacherService;
import server.server.utilities.Labels;

/**
 *
 * @author Fernando
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    
    @Autowired
    public ITeacherService teacherService; 
    
    @GetMapping(value = "/all") 
    public ArrayList<Teacher> all(){
        return teacherService.getAll();  
    }         
   
    @GetMapping(value = "/{TeacherId}")
    @ResponseBody
    public Teacher getTeacher(@PathVariable  Long TeacherId) {
        Teacher teacher = new Teacher();
        teacher.setTeacherID(TeacherId);
        return teacherService.find(teacher);
    }
    
    @PostMapping(
        consumes = {"application/json"})
    public ResponseEntity<Teacher> add(@RequestBody Teacher teacher, Errors errors) {     
       HttpHeaders headers = new HttpHeaders();
        if (errors.hasErrors()) {
            ArrayList<String> setErrors = Utility.setErrors(errors);
            headers.add("Errors", Utility.setErrors(errors).toString());
            return new ResponseEntity<>(teacher, headers, HttpStatus.NOT_MODIFIED);
        } else {
            Map<Labels, Object> returns = teacherService.save(teacher);
            ArrayList<String> errors2 = (ArrayList<String>) returns.get(Labels.errors);
            Teacher th = (Teacher) returns.get(Labels.objectReturn);
            if (!errors2.isEmpty() || th == null) {
                headers.add(Labels.errors.name(), errors2.toString());
                return new ResponseEntity<>(teacher, headers, HttpStatus.NOT_MODIFIED);
            } else {
                return new ResponseEntity<>(teacher, null, HttpStatus.ACCEPTED);

            }
        }
    }
    
    @PutMapping
    @RequestMapping("/update")
    public ResponseEntity<Teacher> update(@RequestBody @Valid Teacher teacher, Errors errors) {
        
        HttpHeaders headers = new HttpHeaders();
        if (errors.hasErrors()) {
            ArrayList<String> setErrors = Utility.setErrors(errors);
            headers.add(Labels.errors.name(), setErrors.toString());
            return new ResponseEntity<>(teacher, headers, HttpStatus.NOT_MODIFIED);
        } else {
            Map<Labels, Object> update = teacherService.update(teacher);
            ArrayList<String> errors2 = (ArrayList<String>) update.get(Labels.errors);
            Teacher ac = (Teacher) update.get(Labels.objectReturn);
            if (!errors2.isEmpty() || ac == null) {
                headers.add(Labels.errors.name(), errors2.toString());
                return new ResponseEntity<>(teacher, headers, HttpStatus.NOT_MODIFIED);
            }
            return new ResponseEntity<>(teacher, null, HttpStatus.ACCEPTED);
        }
        
    }
    
    @DeleteMapping
    @RequestMapping("/delete/{teacherId}")
    public ResponseEntity<Teacher> delete(@PathVariable Long teacherId) {

        Map<Labels, Object> delete = teacherService.delete(teacherId);
        ArrayList<String> errors = (ArrayList<String>) delete.get(Labels.errors);
        Teacher ac = (Teacher) delete.get(Labels.objectReturn);
        if (ac != null) {
            return new ResponseEntity<>(ac, null, HttpStatus.ACCEPTED);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add(Labels.errors.name(), errors.toString());
            return (new ResponseEntity<>(ac, headers, HttpStatus.NOT_MODIFIED));
        }
    }    
}
