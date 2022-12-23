/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Services.Impls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import server.server.Model.Access.DAOHourlyAssignment;
import server.server.Model.Domain.HourlyAssignment;
import server.server.Model.Services.IDepartmentService;
import server.server.Model.Services.IHourlyAssignmentService;
import server.server.Model.Services.ITeacherService;
import server.server.utilities.Labels;
import server.server.utilities.errors.HAErrors;

/**
 *
 * @author Fernando
 */
@Service
@EnableTransactionManagement
public class HourlyAssignmentService implements IHourlyAssignmentService{
    
    @Autowired 
    private DAOHourlyAssignment hourlyAssignmentRepo; 
    
    @Autowired
    private IDepartmentService deptService;
    
    @Autowired
    private ITeacherService thService;

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public HourlyAssignment find(HourlyAssignment hourlyAssignment) {
        return hourlyAssignmentRepo.findById(hourlyAssignment.getVinculationId()).orElse(null);
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> save(HourlyAssignment hourlyAssignment) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList errors = new ArrayList();
        if (this.find(hourlyAssignment) != null) {
            errors.add(HAErrors.HA102.name());
            returns.put(Labels.objectReturn, null);
        } else {
            if (deptService.find(hourlyAssignment.getDepartment()) != null) {
                HourlyAssignment entitySaved = hourlyAssignmentRepo.save(hourlyAssignment);
                returns.put(Labels.objectReturn, entitySaved);
            }else if (thService.find(hourlyAssignment.getTeacher()) != null) {
                errors.add(HAErrors.HA103.name());
            }else {
                errors.add(HAErrors.HA104.name());
            }
            HourlyAssignment entitySaved = hourlyAssignmentRepo.save(hourlyAssignment);
            returns.put(Labels.objectReturn, entitySaved);
        }
        returns.put(Labels.errors, errors);
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public ArrayList<HourlyAssignment> getAll() {
        return (ArrayList<HourlyAssignment>) hourlyAssignmentRepo.findAll();
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> update(HourlyAssignment hourlyAssignment) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList(); 
        HourlyAssignment old = this.find(hourlyAssignment);
        if (old == null) {
            errors.add(HAErrors.HA101.name());  
        } else {
            old = hourlyAssignmentRepo.save(hourlyAssignment);
        }
        returns.put(Labels.errors, errors);  
        returns.put(Labels.objectReturn, old);   
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> delete(Long vinculationId) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList(); 
        HourlyAssignment old = hourlyAssignmentRepo.findById(vinculationId).orElse(null);
        if(old != null){
            hourlyAssignmentRepo.delete(old);
        }else{
            errors.add(HAErrors.HA101.name());  
        }
        returns.put(Labels.errors, errors); 
        returns.put(Labels.objectReturn, old); 
        return returns; 
    }
}
