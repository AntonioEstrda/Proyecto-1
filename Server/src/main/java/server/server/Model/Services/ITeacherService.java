/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.server.Model.Services;

import java.util.ArrayList;
import java.util.Map;
import server.server.Model.Domain.Teacher;
import server.server.utilities.Labels;

/**
 *
 * @author Fernando
 */
public interface ITeacherService {
    
    public Teacher find(Teacher teacher); 
    
    public Map<Labels, Object> save(Teacher teacher); 
    
    public ArrayList<Teacher> getAll();  
    
    public Map<Labels, Object> update(Teacher teacher);  
    
    public Map<Labels, Object> delete(Long teacherId);
    
    public Teacher findByNumIden(String numIden);
    
}
