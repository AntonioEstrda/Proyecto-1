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
     *
     * @param o
     * @return
     */
    public ArrayList<EnvironmentType> findAll(Object o);  
    
    /**
     *
     * @param o
     * @return
     */
    public EnvironmentType find(Object o);  
    
    /**
     *
     * @param o
     * @return
     */
    public EnvironmentType save(Object o);  
    
    /**
     *
     * @param o
     * @return
     */
    public EnvironmentType update(Object o);  
    
    /**
     *
     * @param o
     * @return
     */
    public EnvironmentType delete(Object o);  
    
    
}
