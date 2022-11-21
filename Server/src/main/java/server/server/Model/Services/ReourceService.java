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
import server.server.Model.Access.DAOResource;
import server.server.Model.Domain.Resource;

/**
 * 
 * @author anmon
 */
@Service 
@EnableTransactionManagement
public class ReourceService implements IResourceService{
    
    @Autowired 
    private DAOResource resRepo;  
    
    @Autowired
    private IResourceTypeService resTypeServ; 
    
    
    @Override
    @Transactional(value="DataTransactionManager", readOnly=true)
    public Resource find(Resource env) {
        return resRepo.findById(env.getResourceId()).orElse(null);  
    }

    @Override
    @Transactional(value="DataTransactionManager")
    public Resource save(Resource res) {
        if(this.find(res) != null){
            return null;  
        }
        if(resTypeServ.find(res.getResourceType()) == null){
            return null;  
        }
        return resRepo.save(res); 
    }
    
    @Override
    @Transactional(value="DataTransactionManager")
    public Resource update(Resource res) {
        if(this.find(res) == null){
            return null;  
        }
        if(resTypeServ.find(res.getResourceType()) == null){
            return null;  
        }
        return resRepo.save(res); 
    }
    
    @Override
    @Transactional(value="DataTransactionManager")
    public Resource delete(Long id) {
        Resource r = resRepo.findById(id).orElse(null); 
        if( r == null){
            return null;  
        }
        r.setDisable(true);
        return resRepo.save(r); 
    }
    
    @Override
    @Transactional(value="DataTransactionManager", readOnly=true)
    public ArrayList<Resource> getAll() {
        return (ArrayList<Resource>) resRepo.findAll();  
    }
    
    
}
