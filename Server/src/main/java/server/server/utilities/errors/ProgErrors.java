/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.utilities.errors;

/**
 *
 * @author anmon
 */
public enum ProgErrors {

    /**
     * Program not found
     */
    PRG101,
    /**
     * Program registered with that ID previously		
     */
    PRG102,
    /**
     * Program name must not be null
     */
    PRG103,
    /**
     * Program code must not be null
     */
    PRG104,
    /**
     * Program Location must not be null
     */
    PRG105,
    /**
     * Department ID cannot be null
     */
    PRG106,
    
    /**
     * Alphabetical min 2, max 6
     */
    PRG107,
    /**
     * previous existence of codex-number program
     */
    PRG108,
    /**
     * empty color field
     */
    PRG109
}
