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
import server.server.Model.Domain.TeacherGroup;
import server.server.Model.Services.ITeacherGroupService;

/**
 *
 * @author Fernando
 */
@RestController
@RequestMapping("/TeacherGroup")
public class TeacherGroupController {
    @Autowired
    public ITeacherGroupService teacherGroupService; 
    
    @GetMapping(value = "/all") 
    public ArrayList<TeacherGroup> all(){
        return teacherGroupService.getAll();  
    }          
   
    @GetMapping(value = "/{TeacherGroupId}")
    @ResponseBody
    public TeacherGroup getTeacherGroup(@PathVariable  Long TeacherGroupId) {
        TeacherGroup teacherGroup = new TeacherGroup();
        teacherGroup.setTeacherGroupID(TeacherGroupId);
        return teacherGroupService.find(teacherGroup);
    }
    
    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeacherGroup> add(@RequestBody TeacherGroup teacherGroup) {     
       
       teacherGroup = teacherGroupService.save(teacherGroup); 
       if (teacherGroup == null){ 
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", "found an instance");
            return new ResponseEntity<>(teacherGroup,headers,HttpStatus.NOT_MODIFIED);
       } else {
            return new ResponseEntity<>(teacherGroup,null,HttpStatus.ACCEPTED);
       }
    }
    
    @PutMapping
    @RequestMapping("/update")
    public ResponseEntity<TeacherGroup> update(@RequestBody TeacherGroup teacherGroup) {     
       
       teacherGroup = teacherGroupService.update(teacherGroup); 
       if (teacherGroup != null){ 
            return new ResponseEntity<>(teacherGroup,null,HttpStatus.ACCEPTED);
       } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", "Not found");
            return new ResponseEntity<>(teacherGroup,headers,HttpStatus.NOT_MODIFIED);
       }
    }
    
    @DeleteMapping
    @RequestMapping("/delete/{TeacherGroupId}")
    public ResponseEntity<TeacherGroup> delete(@PathVariable  Long TeacherGroupId) {     
       
       TeacherGroup teacherGroup = teacherGroupService.delete(TeacherGroupId); 
       if (teacherGroup != null){ 
            return new ResponseEntity<>(teacherGroup,null,HttpStatus.ACCEPTED);
       } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", "Not found");
            return (new ResponseEntity<>(teacherGroup, headers,HttpStatus.NOT_MODIFIED));
       }
    }    
}


