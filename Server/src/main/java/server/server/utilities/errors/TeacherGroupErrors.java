/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package server.server.utilities.errors;

/**
 *
 * @author Fernando
 */
public enum TeacherGroupErrors {
    /**
     *  Teacher not found
     */
    TG101,

    /**
     * Group not found		
     */
    TG102,

    /**
     * Teacher Id must not be null
     */
    TG103,

    /**
     * Group Id must not be null
     */
    TG104,
    
    /**
     * Teacher Group previously registered with this ID
     */
    TG105,
    
    /**
     * Teacher Group not found
     */
    TG106
}
