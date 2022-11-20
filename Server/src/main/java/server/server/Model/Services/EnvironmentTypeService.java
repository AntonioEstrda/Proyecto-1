/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Services;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import server.server.Model.Access.DAOEnvironmentType;
import server.server.Model.Domain.EnvironmentType;

/**
 * Environment Service Class
 * @author anmon
 */
@Service 
@EnableTransactionManagement
public class EnvironmentTypeService implements IEnvironmentTypeService{
    
    @Autowired 
    private DAOEnvironmentType envTypeRepo;  

    @Override
    @Transactional(value="DataTransactionManager", readOnly=true)
    public ArrayList<EnvironmentType> findAll(Object o) {
        return (ArrayList<EnvironmentType>) envTypeRepo.findAll(); 
    }

    @Override
    @Transactional(value="DataTransactionManager", readOnly=true)
    public EnvironmentType find(Object o) {
        EnvironmentType f = (EnvironmentType) o;  
        return envTypeRepo.findById(f.getResourceTypeId()).orElse(null);  
    }

    @Override
    @Transactional(value="DataTransactionManager")
    public EnvironmentType save(Object o) {
        EnvironmentType et = (EnvironmentType) o; 
        if (et.getName() == null 
                || et.getResourceTypeId() < 0 
                || this.find(et) != null){
            return null;  
        }
        Long etP = et.getParent();  
        if(etP != null && envTypeRepo.findById(etP) == null){
            return null; 
        }
        
        return envTypeRepo.save(et);  
    }

    @Override
    @Transactional(value="DataTransactionManager")
    public EnvironmentType update(Object o) {
        EnvironmentType et = (EnvironmentType) o; 
        if (et.getName() == null || et.getResourceTypeId() < 0 || this.find(et) == null){
            return null;  
        }
        return envTypeRepo.save(et); 
    }

    @Override
    @Transactional(value="DataTransactionManager")
    public EnvironmentType delete(Long id) {
        EnvironmentType et2 = envTypeRepo.findById(id).orElse(null); 
        if (et2 == null){
            return null;
        }else{
            et2.setDisable(true);
            return envTypeRepo.save(et2);
        }
    }
    
    
}
