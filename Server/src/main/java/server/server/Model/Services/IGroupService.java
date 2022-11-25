/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.server.Model.Services;

import java.util.ArrayList;
import server.server.Model.Domain.Group;

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
    public Group save(Group group); 

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
    public Group update(Group group);  
    
    
    /**
     * Deactivates an Group
     * @param groupId
     * @return Group
     */
    public Group delete(Long groupId);
}
