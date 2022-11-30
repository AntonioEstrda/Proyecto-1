/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.utilities.errors;

/**
 *
 * @author anmon
 */
public enum ResErrors {

    /**
     * Resource not found
     */
    RES101,
    /**
     * Description not be null
     */
    RES102,
    /**
     * isDisable must contain a value
     */
    RES103,
    /**
     * Resource must have a type
     */
    RES104,
    /**
     * Resource name must have a value
     */
    RES105,
    /**
     * Resource code must have a value
     */
    RES106,
    /**
     * Previous existence of the resource
     */
    RES107,
    /**
     * number must have a value 
     */
    RES108,
    /**
     * number must have a value higher than 1
     */
    RES109, 
    /**
     * Alphabetical min 2, max 6
     */
    RES110, 
    /**
     * previous existence of codex-number resource  
     */
    RES111
    ;
}
