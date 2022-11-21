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
import server.server.Model.Domain.Subject;
import server.server.Model.Services.ISubjectService;

/**
 *
 * @author Fernando
 */
@RestController
@RequestMapping("/Subject")
public class SubjectController {
    
    @Autowired
    public ISubjectService subjectService; 
    
    @GetMapping(value = "/all") 
    public ArrayList<Subject> all(){
        return subjectService.getAll();  
    }          
   
    @GetMapping(value = "/{SubjectId}")
    @ResponseBody
    public Subject getSubject(@PathVariable  Long SubjectId) {
        Subject subject = new Subject();
        subject.setSubjectID(SubjectId);
        return subjectService.find(subject);
    }
    
    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Subject> add(@RequestBody Subject subject) {     
       
       subject = subjectService.save(subject); 
       if (subject == null){ 
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", "found an instance");
            return new ResponseEntity<>(subject,headers,HttpStatus.NOT_MODIFIED);
       } else {
            return new ResponseEntity<>(subject,null,HttpStatus.ACCEPTED);
       }
    }
    
    @PutMapping
    @RequestMapping("/update")
    public ResponseEntity<Subject> update(@RequestBody Subject subject) {     
       
       subject = subjectService.update(subject); 
       if (subject != null){ 
            return new ResponseEntity<>(subject,null,HttpStatus.ACCEPTED);
       } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", "Not found");
            return new ResponseEntity<>(subject,headers,HttpStatus.NOT_MODIFIED);
       }
    }
    
    @DeleteMapping
    @RequestMapping("/delete/{SubjectId}")
    public ResponseEntity<Subject> delete(@PathVariable  Long SubjectId) {     
       
       Subject subject = subjectService.delete(SubjectId); 
       if (subject != null){ 
            return new ResponseEntity<>(subject,null,HttpStatus.ACCEPTED);
       } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", "Not found");
            return (new ResponseEntity<>(subject, headers,HttpStatus.NOT_MODIFIED));
       }
    }
    
}
