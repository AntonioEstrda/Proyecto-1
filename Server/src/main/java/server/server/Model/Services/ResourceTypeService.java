/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Services;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.server.Model.Access.DAOResourceType;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import server.server.Model.Domain.ResourceType;

/**
 * Resource Type Service Class 
 * @author anmon
 */
@Service 
@EnableTransactionManagement
public class ResourceTypeService implements IResourceTypeService {
    @Autowired 
    private DAOResourceType resTypeRepo;  

    @Override
    @Transactional(value="DataTransactionManager", readOnly=true)
    public ArrayList<ResourceType> findAll(Object o) {
        return (ArrayList<ResourceType>) resTypeRepo.findAll(); 
    }

    @Override
    @Transactional(value="DataTransactionManager", readOnly=true)
    public ResourceType find(Object o) {
        ResourceType f = (ResourceType) o;  
        return resTypeRepo.findById(f.getResourceTypeId()).orElse(null);  
    }

    @Override
    @Transactional(value="DataTransactionManager")
    public ResourceType save(Object o) {
        ResourceType et = (ResourceType) o; 
        if (et.getName() == null 
                || et.getResourceTypeId() < 0 
                || this.find(et) != null){
            return null;  
        }
        Long etP = et.getParent();  
        if(etP != null && resTypeRepo.findById(etP) == null){
            return null; 
        }
        
        return resTypeRepo.save(et);  
    }

    @Override
    @Transactional(value="DataTransactionManager")
    public ResourceType update(Object o) {
        ResourceType et = (ResourceType) o; 
        if (et.getName() == null || et.getResourceTypeId() < 0 || this.find(et) == null){
            return null;  
        }
        return resTypeRepo.save(et); 
    }

    @Override
    @Transactional(value="DataTransactionManager")
    public ResourceType delete(Long id) {
        ResourceType et2 = resTypeRepo.findById(id).orElse(null); 
        if (et2 == null){
            return null;
        }else{
            et2.setDisable(true);
            return resTypeRepo.save(et2);
        }
    }
}
