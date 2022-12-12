/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.utilities.errors;

/**
 *
 * @author anmon
 */
public enum EnvErrors {

    /**
     * Environment not found 
     */
    ENV101,

    /**
     * Location must be not null 
     */
    ENV102,

    /**
     * IsDisable must have a value 
     */
    ENV105,

    /**
     * Capacity must be not null 
     */
    ENV106,

    /**
     * Capacity value must be higher or equal than 1 
     */
    ENV107,

    /**
     * environment must have a type 
     */
    ENV108,

    /**
     * Number can't be null 
     */
    ENV109,

    /**
     * Code must have a value 
     */
    ENV110,    
    /**
     * Environment previous  instance 
     */
    ENV112,
    /**
     * Environment is disabled 
     */
    ENV113;
}
