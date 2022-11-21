/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.server.Model.Services;

import java.util.ArrayList;
import server.server.Model.Domain.Program;

/**
 *
 * @author Fernando
 */
public interface IProgramService {
    /**
     * Find an program
     * @param program
     * @return Program 
     */
    public Program find(Program program); 
    
    /**
     * save a program 
     * @param program
     * @return Program
     */
    public Program save(Program program); 

    /**
     * list all programs 
     * @return ArrayList
     */
    public ArrayList<Program> getAll();  
    
    
    /**
     * Updates an program
     * @param program
     * @return Program
     */
    public Program update(Program program);  
    
    
    /**
     * Deactivates an Program
     * @param programId
     * @return Program
     */
    public Program delete(Long programId);
    
}
