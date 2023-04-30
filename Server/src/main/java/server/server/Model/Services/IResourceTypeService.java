/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.server.Model.Services;

import java.util.ArrayList;
import java.util.Map;
import server.server.Model.Domain.ResourceType;
import server.server.utilities.Labels;

/**
 * Interface for ResourceType Service 
 * @author anmon
 */
public interface IResourceTypeService {
    
    /**
     * Temporal solution 
     */
    public static int ENVIRONMENTTYPE = 4;  
    
    /**
     * finAll ResourceTypes 
     * @param args
     * @return ArrayList of ResourceType
     */
    public ArrayList<ResourceType> findAll(Object args); 
    
    /**
     *
     * @param args
     * @return ResourceType
     */
    public ResourceType find(Object args);

    /**
     * find one resource type
     * @param args
     * @return errors, ResourceType
     */
    public Map<Labels, Object> save(Object args);  
    
    /**
     * updates a resource type
     * @param o
     * @return errors, ResourceType
     */
    public Map<Labels, Object> update(Object o);  
    
    /**
     * disables a resource type 
     * @param id
     * @return errors, ResourceType
     */
    public Map<Labels, Object> delete(Long id);  
    
    
    /**
     * Returns a Resource global type 
     * @param idType type id to determinate its global type
     * @return global type 
     */
    public long globalType(long idType);
    
}
