/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package server.server.utilities.errors;

/**
 *
 * @author Fernando
 */
public enum SubjErrors {
    
    /**
     *  Subject not found
     */
    SUBJ101,

    /**
     * Subject registered with this ID previously	
     */
    SUBJ102,

    /**
     * Subject name must not be null
     */
    SUBJ103,

    /**
     * The Subject requirements field must not be null
     */
    SUBJ104,
    
    /**
     * Semester field must not be equal to or less than 0
     */
    SUBJ105,
    
    /**
     * The field intensity must not be equal to or less than 0
     */
    SUBJ106,
    
    /**
     * Modality field must not be null
     */
    SUBJ107,
    
    /**
     * The isDisable field must contain a value
     */
    SUBJ108,
    
    /**
     * The type field must not be null
     */
    SUBJ109;

    
}
