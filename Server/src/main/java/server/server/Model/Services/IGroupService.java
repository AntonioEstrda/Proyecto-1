/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.server.Model.Services;

import java.util.ArrayList;
import java.util.Map;
import server.server.Model.Domain.Group;
import server.server.utilities.Labels;

/**
 *
 * @author Fernando
 */
public interface IGroupService {
    /**
     * Find an group
     * @param group
     * @return Group 
     */
    public Group find(Group group); 
    
    /**
     * save a group 
     * @param group
     * @return Group
     */

    public Group findById(long group);
    /**
     * Saves a new one  or updates a faculty register in the repository 
     * 
     * @param group Group instance 
     * @return String - contains information about saving process (status, an errors if those exist) 
     */
    
    public Map<Labels, Object> save(Group group); 

    /**
     * list all groups 
     * @return ArrayList
     */
    public ArrayList<Group> getAll();  
    
    
    /**
     * Updates an group
     * @param group
     * @return Group
     */
    public Map<Labels, Object> update(Group group);  
    
    
    /**
     * Deactivates an Group
     * @param groupId
     * @return Group
     */
    public Map<Labels, Object> delete(Long groupId);
}
