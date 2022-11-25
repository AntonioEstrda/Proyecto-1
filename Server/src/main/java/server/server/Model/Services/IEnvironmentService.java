/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Services;

import java.util.ArrayList;
import java.util.Map;
import server.server.Model.Domain.Environment;

/**
 * Environment Service interface 
 * @author anmon
 */
public interface IEnvironmentService {
    
    
    /**
     * Find an environment
     * @param environment
     * @return Environment 
     */
    public Environment find(Environment environment); 
    
    /**
     * list all environments 
     * @return ArrayList
     */
    public ArrayList<Environment> getAll();  
    
    
    /**
     * Updates an environment
     * @param environment
     * @return Environment
     */
    public Map<String, Object> update(Environment environment);  
    
    
    /**
     * Deactivates an Environment
     * @param environmentId
     * @return Environment
     */
    public Map<String, Object> delete(Long environmentId);
    
     /**
     * save a environment 
     * @param environment
     * @return Environment
     */
    public Map<String, Object> save(Environment environment); 
}
