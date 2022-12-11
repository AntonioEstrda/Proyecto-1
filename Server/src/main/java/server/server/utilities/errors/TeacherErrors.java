/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package server.server.utilities.errors;

/**
 *
 * @author Fernando
 */
public enum TeacherErrors {
    /**
     *  Teacher not found
     */
    TCH101,

    /**
     * Teacher registered with this ID previously		
     */
    TCH102,

    /**
     * Teacher name must not be null
     */
    TCH103,

    /**
     * The Teacher last name must not be null
     */
    TCH104,
    
    /**
     * The teacher identification number must not be null
     */
    TCH105,
    /**
     * The isDisable field must contain a value
     */
    TCH106
}
