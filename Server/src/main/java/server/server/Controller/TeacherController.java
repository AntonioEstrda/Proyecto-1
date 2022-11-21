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
import server.server.Model.Domain.Teacher;
import server.server.Model.Services.ITeacherService;

/**
 *
 * @author Fernando
 */
@RestController
@RequestMapping("/Teacher")
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
        consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Teacher> add(@RequestBody Teacher teacher) {     
       
       teacher = teacherService.save(teacher); 
       if (teacher == null){ 
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", "found an instance");
            return new ResponseEntity<>(teacher,headers,HttpStatus.NOT_MODIFIED);
       } else {
            return new ResponseEntity<>(teacher,null,HttpStatus.ACCEPTED);
       }
    }
    
    @PutMapping
    @RequestMapping("/update")
    public ResponseEntity<Teacher> update(@RequestBody Teacher teacher) {     
       
       teacher = teacherService.update(teacher); 
       if (teacher != null){ 
            return new ResponseEntity<>(teacher,null,HttpStatus.ACCEPTED);
       } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", "Not found");
            return new ResponseEntity<>(teacher,headers,HttpStatus.NOT_MODIFIED);
       }
    }
    
    @DeleteMapping
    @RequestMapping("/delete/{TeacherId}")
    public ResponseEntity<Teacher> delete(@PathVariable  Long TeacherId) {     
       
       Teacher teacher = teacherService.delete(TeacherId); 
       if (teacher != null){ 
            return new ResponseEntity<>(teacher,null,HttpStatus.ACCEPTED);
       } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", "Not found");
            return (new ResponseEntity<>(teacher, headers,HttpStatus.NOT_MODIFIED));
       }
    }    
}
