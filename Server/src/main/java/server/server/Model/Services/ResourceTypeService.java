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
import server.server.Model.Access.DAOResourceType;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import server.server.Model.Domain.ResourceType;
import server.server.utilities.Labels;
import server.server.utilities.errors.ResTypErrors;

/**
 * Resource Type Service Class
 *
 * @author anmon
 */
@Service
@EnableTransactionManagement
public class ResourceTypeService implements IResourceTypeService {

    @Autowired
    private DAOResourceType resTypeRepo;

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public ArrayList<ResourceType> findAll(Object o) {
        return (ArrayList<ResourceType>) resTypeRepo.findAll();
    }

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public ResourceType find(Object o) {
        ResourceType f = (ResourceType) o;
        return resTypeRepo.findById(f.getResourceTypeId()).orElse(null);
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> save(Object o) {
        ResourceType et = (ResourceType) o;
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList();
        if (this.find(et) != null) {
            errors.add(ResTypErrors.RESTYP104.name());
        }

        Long etP = et.getParent();
        if (etP != null && resTypeRepo.findById(etP) == null) {
            errors.add(ResTypErrors.RESTYP105.name());
        }
        if (errors.isEmpty()) {
            et = resTypeRepo.save(et);
        }
        returns.put(Labels.errors, errors);
        returns.put(Labels.objectReturn, et);
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> update(Object o) {
        ResourceType et = (ResourceType) o;
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList();

        if (this.find(et) == null) {
            errors.add(ResTypErrors.RESTYP101.name());
        }
        Long etP = et.getParent();
        if (etP != null && resTypeRepo.findById(etP) == null) {
            errors.add(ResTypErrors.RESTYP105.name());
        }
        
        if (errors.isEmpty()) {
            et = resTypeRepo.save(et);
        }
        
        returns.put(Labels.errors, errors);
        returns.put(Labels.objectReturn, et);
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> delete(Long id) {
        ResourceType et2 = resTypeRepo.findById(id).orElse(null);
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList();
        
        if (et2 == null) {
            errors.add(ResTypErrors.RESTYP101.name());
        } else {
            et2.setDisable(true);
            et2 = resTypeRepo.save(et2);
        }
        returns.put(Labels.errors, errors);
        returns.put(Labels.objectReturn, et2);
        return returns;
    }
}
