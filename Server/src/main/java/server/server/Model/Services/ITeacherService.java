/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.server.Model.Services;

import java.util.ArrayList;
import server.server.Model.Domain.Teacher;

/**
 *
 * @author Fernando
 */
public interface ITeacherService {
    /**
     * Find an teacher
     * @param teacher
     * @return Teacher 
     */
    public Teacher find(Teacher teacher); 
    
    /**
     * save a teacher 
     * @param teacher
     * @return Teacher
     */
    public Teacher save(Teacher teacher); 

    /**
     * list all teachers 
     * @return ArrayList
     */
    public ArrayList<Teacher> getAll();  
    
    
    /**
     * Updates an teacher
     * @param teacher
     * @return Teacher
     */
    public Teacher update(Teacher teacher);  
    
    
    /**
     * Deactivates an Teacher
     * @param teacherId
     * @return Teacher
     */
    public Teacher delete(Long teacherId);
    
}
