/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package server.server.utilities.errors;

/**
 *
 * @author Fernando
 */
public enum DeptErrors {
    /**
     *  Department not found
     */
    DEPT101,

    /**
     * Existence of a previous instance with the same id  		
     */
    DEPT102,

    /**
     * Department name must contain a value
     */
    DEPT103,

    /**
     * Department code must have a value
     */
    DEPT104,
    
    /**
     * Location must contain a value
     */
    DEPT105,
    
    /**
     * Location must contain at least 5 characters
     */
    DEPT106,
    
    /**
     * Location must contain a value
     */
    DEPT107,
    
    /**
     * Alphabetical min 2, max 6
     */
    DEPT108,
    
    /**
     * The Faculty ID field cannot be null
     */
    DEPT109;
}
