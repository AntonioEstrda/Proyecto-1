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
    /**
     * Schedule not found
     */
    SCH101,
    /**
     * Precious schedule assignment found
     */
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
    SCH111,
    /**
     * The chosen group does not belong to the current academic period
     */
    SCH112,
    /**
     * Environment type do not correspond with Subject requirements
     */
    SCH113,
    /**
     * Environment do not satisfy the subject requirements
     */
    SCH114,
    /**
     * exceeds the allowed limit of hours to assign
     */
    SCH115,
    /**
     * intersects with another subject
     */
    SCH116,
    /**
     * Cannot allocate in the indicated range
     */
    SCH117, 
    
    /**
     *  Cannot allocate group in resEnv due to group and resEnv belongs to different faculties.
     *  Try on event schedule
     */
    SCH118;

}
