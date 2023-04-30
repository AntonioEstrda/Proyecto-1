/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.server.Model.Services;

import java.util.ArrayList;
import java.util.Map;
import server.server.Model.Domain.HourlyAssignment;
import server.server.utilities.Labels;

/**
 *
 * @author Fernando
 */
public interface IHourlyAssignmentService {
    /**
     * Find an hourlyAssignment
     * @param hourlyAssignment
     * @return HourlyAssignment 
     */
    public HourlyAssignment find(HourlyAssignment hourlyAssignment); 
    
    /**
     * save a hourlyAssignment 
     * @param hourlyAssignment
     * @return HourlyAssignment
     */
    public Map<Labels, Object> save(HourlyAssignment hourlyAssignment); 

    /**
     * list all hourlyAssignments 
     * @return ArrayList
     */
    public ArrayList<HourlyAssignment> getAll();  
    
    
    /**
     * Updates an hourlyAssignment
     * @param hourlyAssignment
     * @return HourlyAssignment
     */
    public Map<Labels, Object> update(HourlyAssignment hourlyAssignment);  
    
    
    /**
     * Deactivates an HourlyAssignment
     * @param hourlyAssignmentId
     * @return HourlyAssignment
     */
    public Map<Labels, Object> delete(Long hourlyAssignmentId);
    
}
