/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.ObjectError;
import server.server.Model.Access.DAOFacultyResource;
import server.server.Model.Domain.FacultyResource;

/**
 *
 * @author anmon
 */
@Service
@EnableTransactionManagement
public class FacultyResourceService implements IFacultyResourceService{
    
    @Autowired 
    private DAOFacultyResource facRscRep;
    
    @Autowired 
    private IFacultyService facultyService; 
    
    @Autowired 
    private IResourceService resourceService;  
    
    @Override
    @Transactional(value="DataTransactionManager")
    public Map<String, Object> save(FacultyResource fr){
        Map<String, Object> args = new HashMap();
        ArrayList<ObjectError> errs = new ArrayList();  
        
        if (facultyService.find(fr.getFacultyFR()) == null){
            errs.add(new ObjectError("facultyErr", "faculty not found")); 
        }
        if (resourceService.find(fr.getResourceFR()) == null){
            errs.add(new ObjectError("resourceErr", "resource not found")); 
        }
        
        if(errs.isEmpty() || facRscRep.findById(fr.getFacResId()) == null){
            fr.setRegistrerDate(new Date(System.currentTimeMillis()));
            fr = facRscRep.save(fr);
        }else{
            fr = null;
            errs.add(new ObjectError("frErrorSave", "an instance of fr encountered"));
        }
        
        args.put("errors" , errs); 
        args.put("FacultyResource", fr); 
        return args; 
    }
    
    @Override
    @Transactional(value="DataTransactionManager")
    public Map<String, Object> update(FacultyResource fr){
        Map<String, Object> args = new HashMap();
        ArrayList<ObjectError> errs = new ArrayList();  
        
        if (facultyService.find(fr.getFacultyFR()) == null){
            errs.add(new ObjectError("facultyErr", "faculty not found")); 
        }
        if (resourceService.find(fr.getResourceFR()) == null){
            errs.add(new ObjectError("resourceErr", "resource not found")); 
        }
        
        if(errs.isEmpty() || facRscRep.findById(fr.getFacResId()) != null){
            fr.setFinalDate(new Date(System.currentTimeMillis()));
            fr = facRscRep.save(fr);
        }else{
            fr = null;
            errs.add(new ObjectError("frErrorUpdate", "an instance of fr was not encounter"));
        }
        
        args.put("errors" , errs); 
        args.put("FacultyResource", fr); 
        return args; 
    }
    
    @Override
    @Transactional(value="DataTransactionManager", readOnly = true)
    public Map<String, Object> findByResource(long resourceId){
        Map<String, Object> args = new HashMap();
        
        facRscRep.findByResourceIdAndFinaldate(resourceId, null); 
        return args; 
    }
}
