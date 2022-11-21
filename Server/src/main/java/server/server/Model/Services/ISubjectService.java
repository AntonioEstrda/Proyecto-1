/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.server.Model.Services;

import java.util.ArrayList;
import server.server.Model.Domain.Subject;

/**
 *
 * @author Fernando
 */
public interface ISubjectService {
    /**
     * Find an subject
     * @param subject
     * @return Subject 
     */
    public Subject find(Subject subject); 
    
    /**
     * save a subject 
     * @param subject
     * @return Subject
     */
    public Subject save(Subject subject); 

    /**
     * list all subjects 
     * @return ArrayList
     */
    public ArrayList<Subject> getAll();  
    
    
    /**
     * Updates an subject
     * @param subject
     * @return Subject
     */
    public Subject update(Subject subject);  
    
    
    /**
     * Deactivates an Subject
     * @param subjectId
     * @return Subject
     */
    public Subject delete(Long subjectId);
    
}
