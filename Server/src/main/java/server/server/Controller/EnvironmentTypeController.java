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
import server.server.Model.Domain.EnvironmentType;
import server.server.Model.Services.IEnvironmentTypeService;

/**
 *
 * @author anmon
 */
@RestController 
@RequestMapping("/EnvironmentType")
public class EnvironmentTypeController {
    
    @Autowired
    public IEnvironmentTypeService envTypeService; 
    
    @GetMapping(value = "/all") 
    public ArrayList<EnvironmentType> all(){
        return  envTypeService.findAll(null);  
    }          
   
    @GetMapping(value = "/{id}")
    @ResponseBody
    public EnvironmentType get(@PathVariable  Long id) {
        EnvironmentType envType = new EnvironmentType();
        envType.setResourceTypeId(id);
        return envTypeService.find(envType);
    }
    
    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EnvironmentType> add(@RequestBody EnvironmentType envType) {     
       
       envType = envTypeService.save(envType); 
       if (envType != null){
           return new ResponseEntity<>(envType,null,HttpStatus.ACCEPTED);
       } else { 
           HttpHeaders headers = new HttpHeaders();
           headers.add("Error", "found an instance");
           return new ResponseEntity<>(envType,headers,HttpStatus.NOT_MODIFIED);
       }
    }
    
    @PutMapping
    @RequestMapping("/update")
    public ResponseEntity<EnvironmentType> update(@RequestBody EnvironmentType envType) {     
       
       envType = envTypeService.update(envType); 
       if (envType != null){ 
            return new ResponseEntity<>(envType,null,HttpStatus.ACCEPTED);
       } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", "Not found");
            return new ResponseEntity<>(envType,headers,HttpStatus.NOT_MODIFIED);
       }
    }
    
    @DeleteMapping
    @RequestMapping("/delete/{id}")
    public ResponseEntity<EnvironmentType> delete(@PathVariable  Long id) {     
       
       EnvironmentType envType = envTypeService.delete(id); 
       if (envType != null){ 
            return new ResponseEntity<>(envType,null,HttpStatus.ACCEPTED);
       } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", "Not found");
            return (new ResponseEntity<>(envType, headers,HttpStatus.NOT_MODIFIED));
       }
    }
}
