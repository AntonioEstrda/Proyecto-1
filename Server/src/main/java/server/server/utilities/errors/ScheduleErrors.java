/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.utilities.errors;

/**
 *
 * @author anmon
 */
public enum ScheduleErrors {
    SCH101,
    SCH102,
    /**
     * Bad dates
     */
    SCH103,
    /**
     * bad times
     */
    SCH104,
    /**
     * Dates must be in academic period date range
     */
    SCH105,
    /**
     * Time must be in the allowed range
     */
    SCH106,
    /**
     * Academic Schedule must have same initial and final date that the current
     * active academic period
     */
    SCH107,
    /**
     * Invalid hour range
     */
    SCH108, 
    
    /**
     * Invalid time for Academic Schedule
     */
    SCH109, 
    
    /**
     * Academic Schedule must not have an event 
     */
    SCH110, 
    
    /**
     * Academic schedule must have a group   
     */
    SCH111;  

}
