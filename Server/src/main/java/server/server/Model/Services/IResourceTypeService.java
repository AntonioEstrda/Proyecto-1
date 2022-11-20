/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.server.Model.Services;

import java.util.ArrayList;
import server.server.Model.Domain.ResourceType;

/**
 * Interface for ResourceType Service 
 * @author anmon
 */
public interface IResourceTypeService {
    
    /**
     * finAll ResourceTypes 
     * @param o args
     * @return ArrayList of ResourceType
     */
    public ArrayList<ResourceType> findAll(Object o); 
    
    /**
     *
     * @param o args
     * @return ResourceType
     */
    public ResourceType find(Object o);

    /**
     * find one resource type
     * @param o args
     * @return ResourceType
     */
    public ResourceType save(Object o);  
    
    /**
     * updates a resource type
     * @param o
     * @return
     */
    public ResourceType update(Object o);  
    
    /**
     * disables a resource type 
     * @param id
     * @return
     */
    public ResourceType delete(Long id);  
}
