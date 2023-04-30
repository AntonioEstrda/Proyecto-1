/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.utilities.errors;

/**
 *
 * @author anmon
 */
public enum GroupErrors {

    /**
     * Resource not found
     */
    GROUP101,
    /**
     * Existence of a previous instance with the same id  		
     */
    GROUP102,
    /**
     * Group name must contain a value
     */
    GROUP103,
    /**
     * Capacity must contain a value
     */
    GROUP104,
    /**
     * Subject ID cannot be null
     */
    GROUP105,
    /**
     * The Academic Period ID cannot be null
     */
    GROUP106,
    /**
     * Alphabetical min 1, max 2
     */
    GROUP107
}
