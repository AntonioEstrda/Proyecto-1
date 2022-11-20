/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Controller.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.validation.Errors;

/**
 *
 * @author anmon
 */
public class Utility {
    public static ArrayList<String> setErrors(Errors errors){
        ArrayList<String> errs = new ArrayList();
        errors.getAllErrors().forEach(err -> errs.add(err.getDefaultMessage()));
        return errs;  
    }
}
