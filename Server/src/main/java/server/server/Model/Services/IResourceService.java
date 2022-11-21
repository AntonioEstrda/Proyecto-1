/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.server.Model.Services;

import java.util.ArrayList;
import server.server.Model.Domain.Resource;

/**
 * Resource Service Interface 
 * @author anmon
 */
public interface IResourceService {

    /**
     * Disables a Resource 
     * @param id ResourceId
     * @return Resource
     */
    public Resource delete(Long id);

    /**
     * Updates a Resource 
     * @param res  Resource to map
     * @return Resource 
     */
    public Resource update(Resource res);

    /**
     * Add a new one Resource 
     * @param res Resource to map
     * @return Resource
     */
    public Resource save(Resource res);

    /**
     * Find a resource 
     * @param res  Resource to map
     * @return Resource 
     */
    public Resource find(Resource res);

    /**
     * list all Resources 
     * @return ArrayList Resources 
     */
    public ArrayList<Resource> getAll();
    
}
