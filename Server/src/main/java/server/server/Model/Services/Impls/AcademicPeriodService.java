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
import server.server.Model.Access.DAOAcademicPeriod;
import server.server.Model.Domain.AcademicPeriod;
import server.server.Model.Services.IAcademicPeriodService;
import server.server.utilities.Labels;
import server.server.utilities.errors.APErrors;

/**
 *
 * @author Fernando
 */
@Service
@EnableTransactionManagement
public class AcademicPeriodService implements IAcademicPeriodService{
    
    @Autowired 
    private DAOAcademicPeriod academicPeriodRepo; 
    
    /*@Autowired
    private IFacultyService facultyService;*/

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public AcademicPeriod find(AcademicPeriod academicPeriod) {
        return academicPeriodRepo.findById(academicPeriod.getAcademicPeriodID()).orElse(null);
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> save(AcademicPeriod academicPeriod) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList errors = new ArrayList();
        if (this.find(academicPeriod) != null) {
            errors.add(APErrors.AP102.name());
            returns.put(Labels.objectReturn, null);
        } else {
                AcademicPeriod entitySaved = academicPeriodRepo.save(academicPeriod);
                returns.put(Labels.objectReturn, entitySaved);
        }
        returns.put(Labels.errors, errors);
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public ArrayList<AcademicPeriod> getAll() {
        return (ArrayList<AcademicPeriod>) academicPeriodRepo.findAll();
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> update(AcademicPeriod academicPeriod) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList(); 
        AcademicPeriod old = this.find(academicPeriod);
        if (old == null) {
            errors.add(APErrors.AP101.name());  
        } else {
            old = academicPeriodRepo.save(academicPeriod);
        }
        returns.put(Labels.errors, errors);  
        returns.put(Labels.objectReturn, old);   
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> delete(Long AcademicPeriodId) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList(); 
        AcademicPeriod old = academicPeriodRepo.findById(AcademicPeriodId).orElse(null);
        if(old != null){
            academicPeriodRepo.delete(old);
        }else{
            errors.add(APErrors.AP101.name());  
        }
        returns.put(Labels.errors, errors); 
        returns.put(Labels.objectReturn, old); 
        return returns; 
    }

    @Override
    public Map<Labels, Object> getCurrent() {
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList(); 
        AcademicPeriod findCurrent = academicPeriodRepo.findCurrent();  
        returns.put(Labels.errors, errors); 
        returns.put(Labels.objectReturn, findCurrent); 
        return returns; 
    }
}
