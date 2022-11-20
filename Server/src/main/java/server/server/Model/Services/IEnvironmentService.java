/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Services;

import java.util.ArrayList;
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
     * save a environment 
     * @param environment
     * @return Environment
     */
    public Environment save(Environment environment); 

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
    public Environment update(Environment environment);  
    
    
    /**
     * Deactivates an Environment
     * @param environmentId
     * @return Environment
     */
    public Environment delete(Long environmentId);
}
