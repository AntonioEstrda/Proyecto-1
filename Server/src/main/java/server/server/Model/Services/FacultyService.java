/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import server.server.Model.Access.DAOFaculty;
import server.server.Model.Domain.Faculty;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author anmon
 */
@Service 
@EnableTransactionManagement
public class FacultyService implements IFacultyService {
    
    @Autowired 
    private DAOFaculty facultyRepo; 

    @Override
    @Transactional(value="DataTransactionManager", readOnly=true)
    public Faculty find(Faculty faculty) {        
        return facultyRepo.findById(faculty.getFacultyId()).orElse(null); 
    }

    @Override 
    @Transactional(value="DataTransactionManager")
    public String save(Faculty faculty){
        String response;   
        Faculty entitySaved = facultyRepo.save(faculty);  
        response = (entitySaved != null)? "success" : "failed";    
        return response;  
    }
}
