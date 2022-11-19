/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import server.server.Model.Domain.Faculty;
import server.server.Model.Services.IFacultyService;

/**
 *
 * @author anmon
 */

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    
    @Autowired
    public IFacultyService facultyService; 
    
    @GetMapping(value = "/all") 
    public ArrayList<Faculty> all(){
        return facultyService.getAll();  
    }          
   
    @GetMapping(value = "/{FacultyId}")
    @ResponseBody
    public Faculty getFaculty(@PathVariable  Long FacultyId) {
        Faculty faculty = new Faculty();
        faculty.setFacultyId(FacultyId);
        return facultyService.find(faculty);
    }
    
    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Faculty> add(@RequestBody Faculty faculty) {     
       System.out.println(faculty);
       String response = facultyService.save(faculty); 
       if ( response.compareTo("success") == 0){ 
            return new ResponseEntity<Faculty>(faculty,null,HttpStatus.ACCEPTED);
       } else {
            return new ResponseEntity<Faculty>(faculty,null,HttpStatus.NOT_MODIFIED);
       }
    }
    
}
