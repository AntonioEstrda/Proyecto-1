/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Services;

import java.util.ArrayList;
import server.server.Model.Domain.EnvironmentType;

/**
 *
 * @author anmon
 */
public interface IEnvironmentTypeService {
    
    /**
     * find all EnvironmentTypes 
     * @param o Object Args 
     * @return
     */
    public ArrayList<EnvironmentType> findAll(Object o);  
    
    /**
     * find an EnvironmentType
     * @param o Object Args 
     * @return
     */
    public EnvironmentType find(Object o);  
    
    /**
     * Saves a new one EnvironmentType
     * @param o Object Args 
     * @return
     */
    public EnvironmentType save(Object o);  
    
    /**
     * Update a EnvironmentType
     * @param o
     * @return
     */
    public EnvironmentType update(Object o);  
    
    /**
     * Disables a EnviromentType
     * @param o Object Args 
     * @return
     */
    public EnvironmentType delete(Long o);  
    
    
}
