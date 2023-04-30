/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.utilities.errors;

/**
 *
 * @author anmon
 */
public enum EvtErrors {

    /**
     * Event not found 
     */
    EVT101, 

    /**
     * Previous assignment 
     */
    EVT102, 

    /**
     * Name must have a value 
     */
    EVT103, 
    /**
     * Description must have a value 
     */
    EVT104, 
    /**
     * Event code, must have a value or contains alphanumeric characters only   
     */
    EVT105, 
    /**
     * Event must have a associated teacher 
     */
    EVT106, 
    /**
     * Event must being associated to an academic period 
     * or must be de current academic period 
     */
    EVT107,
    /**
     * Must have a program
     */
    EVT108, 
    /**
     * Must have a type 
     */
    EVT109;
}

