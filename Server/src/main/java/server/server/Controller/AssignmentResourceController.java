/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.server.Model.Services.IAssignmentResourceService;

/**
 *
 * @author anmon
 */
@RestController
@RequestMapping("/AssignmentResource")
public class AssignmentResourceController {
    
    @Autowired 
    private IAssignmentResourceService asigResService; 
    
    
}
