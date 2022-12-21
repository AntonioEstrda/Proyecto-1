/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.server.Model.Services;

import java.util.ArrayList;
import server.server.Model.Domain.TeacherGroup;

/**
 *
 * @author Fernando
 */
public interface ITeacherGroupService {
    /**
     * Find an teacher
     * @param teacher
     * @return TeacherGroup 
     */
    public TeacherGroup find(TeacherGroup teacher); 
    
    /**
     * save a teacher 
     * @param teacher
     * @return TeacherGroup
     */
    public TeacherGroup save(TeacherGroup teacher); 

    /**
     * list all teachers 
     * @return ArrayList
     */
    public ArrayList<TeacherGroup> getAll();  
    
    
    /**
     * Updates an teacher
     * @param teacher
     * @return TeacherGroup
     */
    public TeacherGroup update(TeacherGroup teacher);  
    
    
    /**
     * Deactivates an TeacherGroup
     * @param teacherId
     * @return TeacherGroup
     */
    public TeacherGroup delete(Long teacherId);
    
}
