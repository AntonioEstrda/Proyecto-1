/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Services;

import java.util.ArrayList;
import java.util.Map;
import server.server.Model.Domain.Faculty;
import server.server.utilities.Labels;

/**
 * Faculty Service interface 
 * @author anmon
 */
public interface IFacultyService {

    /**
     * Receives a Faculty instance to map for search an associated 
     * register in the repository 
     * @param faculty Faculty instance
     * @return
     */
    public Faculty find(Faculty faculty);  
    
    /**
     * Saves a new one  or updates a faculty register in the repository 
     * 
     * @param faculty Faculty instance 
     * @return String - contains information about saving process (status, an errors if those exist) 
     */
    public Map<Labels, Object> save(Faculty faculty); 

    /**
     * All faculties in system 
     * @return ArrayList
     */
    public ArrayList<Faculty> getAll();
    
    /**
     * update a faculty 
     * @param faculty
     * @return 
     */
    public Map<Labels, Object> update(Faculty faculty);
    
    /**
     * Delete a Faculty 
     * @param FacultyId
     * @return 
     */
    public Map<Labels, Object> delete(Long FacultyId);
    
}
