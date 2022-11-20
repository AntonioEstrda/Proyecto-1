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
import server.server.Model.Domain.Environment;
import server.server.Model.Access.DAOEnvironment;

/**
 *
 * @author anmon
 */
@Service
@EnableTransactionManagement
public class EnvironmentService implements IEnvironmentService{
    
    @Autowired 
    private DAOEnvironment envRepo; 

    @Autowired
    private IEnvironmentTypeService envTypeServ;  
    
    @Override
    @Transactional(value="DataTransactionManager", readOnly=true)
    public Environment find(Environment enviroment) {        
        return envRepo.findById(enviroment.getEnvironmentId()).orElse(null); 
    }

    @Override 
    @Transactional(value="DataTransactionManager")
    public Environment save(Environment env){
        Environment old = this.find(env);  
        if ( old != null
                || envTypeServ.find(env.getEnvironmentType()) == null ){
            return null;  
        }    
        else{   
            return envRepo.save(env);  
        }
    }

    @Override
    @Transactional(value="DataTransactionManager", readOnly=true)
    public ArrayList<Environment> getAll() {
        return (ArrayList<Environment>) envRepo.findAll();  
    }

    @Override
    @Transactional(value="DataTransactionManager")
    public Environment update(Environment env) {
        Environment old = this.find(env);  
        if ( envTypeServ.find(env.getEnvironmentType()) == null 
                || old == null){
            return null;  
        }    
        else{   
            return envRepo.save(env);  
        }
    }

    @Override
    @Transactional(value="DataTransactionManager")
    public Environment delete(Long EviromentId) {
        
        Environment old = envRepo.findById(EviromentId).orElse(null);  
        if (old == null){
            return null;  
        }    
        else{   
            old.setDisable(true);
            return envRepo.save(old);  
        }
    }
}
