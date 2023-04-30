/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Controller.errors;

import java.util.ArrayList;
import javax.validation.ConstraintViolation;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import server.server.utilities.Labels;
import server.server.utilities.errors.StdErrors;

/**
 *
 * @author anmon
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice()
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(value = {javax.validation.ConstraintViolationException.class})
    public ResponseEntity handleConstraint(javax.validation.ConstraintViolationException ex){
        ArrayList<String> errors = new ArrayList();  
        HttpHeaders headers = new HttpHeaders();
        for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getMessage());
        }
        headers.add(Labels.errors.name(), errors.toString());
        return new ResponseEntity<>(null, headers, HttpStatus.NOT_MODIFIED);
    }
    
    @ExceptionHandler(value = {java.sql.SQLIntegrityConstraintViolationException.class})
    public ResponseEntity handleConstraint(java.sql.SQLIntegrityConstraintViolationException ex){
        ArrayList<String> errors = new ArrayList();  
        HttpHeaders headers = new HttpHeaders();
        errors.add(StdErrors.STD101.name()); 
        headers.add(Labels.errors.name(), errors.toString());
        return new ResponseEntity<>(null, headers, HttpStatus.NOT_MODIFIED);
    }
    
}
