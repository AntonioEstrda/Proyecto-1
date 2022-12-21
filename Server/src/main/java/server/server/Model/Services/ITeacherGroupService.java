/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.server.Model.Services;

import java.util.ArrayList;
import java.util.Map;
import server.server.Model.Domain.TeacherGroup;
import server.server.utilities.Labels;

/**
 *
 * @author Fernando
 */
public interface ITeacherGroupService {
    /**
     * Find an teacherGroup
     * @param teacherGroup
     * @return TeacherGroup 
     */
    public TeacherGroup find(TeacherGroup teacherGroup); 
    
    /**
     * save a teacherGroup 
     * @param teacherGroup
     * @return TeacherGroup
     */
    public Map<Labels, Object> save(TeacherGroup teacherGroup); 

    /**
     * list all teacherGroups 
     * @return ArrayList
     */
    public ArrayList<TeacherGroup> getAll();  
    
    
    /**
     * Updates an teacherGroup
     * @param teacherGroup
     * @return TeacherGroup
     */
    public Map<Labels, Object> update(TeacherGroup teacherGroup);  
    
    
    /**
     * Deactivates an TeacherGroup
     * @param teacherGroupId
     * @return TeacherGroup
     */
    public Map<Labels, Object> delete(Long teacherGroupId);
    
}
