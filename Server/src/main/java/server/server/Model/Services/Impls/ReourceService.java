/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Services.Impls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import server.server.Model.Access.DAOResource;
import server.server.Model.Domain.Resource;
import server.server.Model.Services.ILocationService;
import server.server.Model.Services.IResourceService;
import server.server.Model.Services.IResourceTypeService;
import server.server.utilities.Labels;
import server.server.utilities.errors.EnvErrors;
import server.server.utilities.errors.LocationErrors;
import server.server.utilities.errors.ResErrors;
import server.server.utilities.errors.ResTypErrors;

/**
 *
 * @author anmon
 */
@Service
@EnableTransactionManagement
public class ReourceService implements IResourceService {

    @Autowired
    private DAOResource resRepo;

    @Autowired
    private IResourceTypeService resTypeServ;

    @Autowired
    private ILocationService locService;

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public Resource find(Resource env) {
        return resRepo.findById(env.getResourceId()).orElse(null);
    }

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public ArrayList<Resource> getAll() {
        return (ArrayList<Resource>) resRepo.findAll();
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> save(Resource res) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList();
        if (this.find(res) != null) {
            errors.add(ResErrors.RES107.name());
        }
        if (resTypeServ.find(res.getResourceType()) == null) {
            errors.add(ResTypErrors.RESTYP101.name());
        } else if (IResourceTypeService.ENVIRONMENTTYPE == resTypeServ.globalType(
                res.getResourceType().getResourceTypeId())) {
            ArrayList<String> validateResourceEnv = validateResourceEnv(res);
            errors.addAll(validateResourceEnv);
        }
        if (resRepo.findByCodeAndNumber(res.getCode(), res.getNumber()) != null) {
            errors.add(ResErrors.RES111.name());
        }
        if (errors.isEmpty()) {
            res = resRepo.save(res);
        }
        returns.put(Labels.errors, errors);
        returns.put(Labels.objectReturn, res);
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> update(Resource res) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList();

        if (this.find(res) == null) {
            errors.add(ResErrors.RES101.name());
        }
        if (resTypeServ.find(res.getResourceType()) == null) {
            errors.add(ResTypErrors.RESTYP101.name());
        } else if (IResourceTypeService.ENVIRONMENTTYPE == resTypeServ.globalType(
                res.getResourceType().getResourceTypeId())) {
            ArrayList<String> validateResourceEnv = validateResourceEnv(res);
            errors.addAll(validateResourceEnv);
        }
        res.setCode(res.getCode().toUpperCase());
        Resource r2 = resRepo.findByCodeAndNumber(res.getCode(), res.getNumber());
        if (r2 != null && r2.getResourceId() != res.getResourceId()) {
            errors.add(ResErrors.RES111.name());
        }
        if (errors.isEmpty()) {
            res = resRepo.save(res);
        }
        returns.put(Labels.errors, errors);
        returns.put(Labels.objectReturn, res);
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> delete(Long id) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList();
        Resource r = resRepo.findById(id).orElse(null);
        if (r == null) {
            errors.add(ResErrors.RES101.name());
        } else {
            r.setDisable(true);
            r = resRepo.save(r);
        }
        returns.put(Labels.errors, errors);
        returns.put(Labels.objectReturn, r);
        return returns;
    }

    private ArrayList<String> validateResourceEnv(Resource res) {
        ArrayList<String> errors = new ArrayList();
        if (res.getCapacity() == null) {
            errors.add(EnvErrors.ENV106.name());
        } else if (res.getCapacity() < 1) {
            errors.add(EnvErrors.ENV107.name());
        }
        if (res.getLocation() == null) {
            errors.add(EnvErrors.ENV102.name());
        } else if (locService.find(res.getLocation().getLocationId()) == null) {
            errors.add(LocationErrors.LOC101.name());
        }
        return errors;
    }

    @Override
    public Resource findByCodeAndNumber(String code, Integer number) {
        return resRepo.findByCodeAndNumber(code, number);
    }

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public Resource findById(long id) {
        return resRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public List<Resource> findByIds(List<Long> ids) {
        
        List<Resource> findAllById = resRepo.findAllById(ids); 
        return findAllById; 
    }

    @Override
    public boolean findAssFaculty(long resourceID, long facultyId) {
        boolean ban = false;
        Resource find = this.resRepo.findById(resourceID).orElse(null);
        if (find != null) {
            ban = resRepo.findAssFaculty(resourceID, facultyId);
        }
        return ban;
    }
}
