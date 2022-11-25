/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import server.server.Model.Domain.Environment;
import server.server.Model.Access.DAOEnvironment;
import server.server.utilities.Labels;
import server.server.utilities.errors.EnvErrors;

/**
 *
 * @author anmon
 */
@Service
@EnableTransactionManagement
public class EnvironmentService implements IEnvironmentService{
    
    @Autowired 
    private DAOEnvironment envRepo; 
    
    @Override
    @Transactional(value="DataTransactionManager", readOnly=true)
    public Environment find(Environment enviroment) {        
        return envRepo.findById(enviroment.getEnvironmentId()).orElse(null); 
    }
    
    @Override
    @Transactional(value="DataTransactionManager", readOnly=true)
    public ArrayList<Environment> getAll() {
        return (ArrayList<Environment>) envRepo.findAll();  
    }
    
    @Override 
    @Transactional(value="DataTransactionManager")
    public Map<String, Object> save(Environment env){
        Map<String, Object> returns = new HashMap();   
        ArrayList<String> errors = new ArrayList(); 
        Environment old = this.find(env);  
        if (old != null){
            returns.put(Labels.objectReturn.name(), null);  
            errors.add(EnvErrors.ENV112.name()); 
        }
        else{   
            returns.put(Labels.objectReturn.name(), env);  
        }
        returns.put(Labels.errors.name(), errors); 
        return returns; 
    }

    @Override
    @Transactional(value="DataTransactionManager")
    public Map<String, Object> update(Environment env) {
        Environment old = this.find(env);  
        if (old == null){
            return null;  
        }    
        else{  
            return null;  
            // return envRepo.save(env);  
        }
    }

    @Override
    @Transactional(value="DataTransactionManager")
    public Map<String, Object> delete(Long EviromentId) {
        
        Environment old = envRepo.findById(EviromentId).orElse(null);  
        if (old == null){
            return null;  
        }    
        else{   
            return null; 
         // old.setDisable(true);
            //return envRepo.save(old);  
        }
    }
}
