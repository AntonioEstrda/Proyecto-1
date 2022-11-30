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
import server.server.Model.Access.DAOFacultyResource;
import server.server.Model.Domain.Faculty;
import server.server.Model.Domain.FacultyResource;
import server.server.Model.Domain.Resource;
import server.server.utilities.Labels;
import server.server.utilities.errors.FacErrors;
import server.server.utilities.errors.FacResErrors;
import server.server.utilities.errors.ResErrors;

/**
 *
 * @author anmon
 */
@Service
@EnableTransactionManagement
public class FacultyResourceService implements IFacultyResourceService {

    @Autowired
    private DAOFacultyResource facRscRep;

    @Autowired
    private IFacultyService facultyService;

    @Autowired
    private IResourceService resourceService;

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> save(FacultyResource fr) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errs = new ArrayList();

        if (facultyService.find(fr.getFacultyFR()) == null) {
            errs.add(FacErrors.FAC101.name());
        }
        if (resourceService.find(fr.getResourceFR()) == null) {
            errs.add(ResErrors.RES101.name());
        }

        if (facRscRep.findByFacultyIdResourceId(
                fr.getFacultyFR().getFacultyId(),
                fr.getResourceFR().getResourceId()) != null) {
            errs.add(FacResErrors.FACRES102.name());
        }

        if (facRscRep.findByResourceId(fr.getResourceFR().getResourceId()) != null) {
            errs.add(FacResErrors.FACRES103.name());
        }

        if (errs.isEmpty()) {
            fr.setRegistrerDate(new Date(System.currentTimeMillis()));
            fr = facRscRep.save(fr);
        }

        returns.put(Labels.errors, errs);
        returns.put(Labels.objectReturn, fr);
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> update(FacultyResource fr) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errs = new ArrayList();

        if (facultyService.find(fr.getFacultyFR()) == null) {
            errs.add(FacErrors.FAC101.name());
        }
        if (resourceService.find(fr.getResourceFR()) == null) {
            errs.add(ResErrors.RES101.name());
        }

        fr = facRscRep.findByFacultyIdResourceId(
                fr.getFacultyFR().getFacultyId(),
                fr.getResourceFR().getResourceId());

        if (errs.isEmpty() && fr != null) {
            fr.setFinalDate(new Date(System.currentTimeMillis()));
            fr.setDisable(true);
            fr = facRscRep.save(fr);
        } else {
            fr = null;
            errs.add(FacResErrors.FACRES101.name());
        }

        returns.put(Labels.errors, errs);
        returns.put(Labels.objectReturn, fr);
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public ArrayList<FacultyResource> findByFacultyId(long facultyId) {
        return (ArrayList<FacultyResource>) facRscRep.findByFacultyId(facultyId);
    }

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public ArrayList<Resource> findByFacultyIdRes(long facultyId) {
        ArrayList<Resource> resources = new ArrayList();
        for (FacultyResource fr : facRscRep.findByFacultyId(facultyId)) {
            resources.add(fr.getResourceFR());
        }
        return resources;
    }

    @Override
    public FacultyResource findByFacultyIdResourceId(long facultyId, long resourceId) {
        return facRscRep.findByFacultyIdResourceId(facultyId, resourceId);
    }

    @Override
    public Map<Labels, Object> addNewOneReource(long facultyId, Resource res) {

        Map<Labels, Object> returns = new HashMap();
        Faculty fac = facultyService.findById(facultyId);
        ArrayList<String> errors2 = new ArrayList();

        if (fac == null) {
            errors2.add(FacErrors.FAC101.name());
        } else {
            returns = resourceService.save(res);
            Resource res2 = (Resource) returns.get(Labels.objectReturn);
            errors2.addAll((ArrayList<String>) returns.get(Labels.errors));
            if (!errors2.isEmpty() || res2 == null) {
                res = null;
            } else {
                FacultyResource fr = new FacultyResource();
                fr.setFacultyFR(fac);
                fr.setResourceFR(res2);
                fr.setRegistrerDate(new Date(System.currentTimeMillis()));
                res = res2;
                facRscRep.save(fr);
            }
        }
        returns.put(Labels.errors, errors2);
        returns.put(Labels.objectReturn, res);
        return returns;
    }

    @Override
    public ArrayList<String> validateAssignment(long facultyId, long resourceId) {
        ArrayList<String> errors = new ArrayList();
        Faculty fac = facultyService.findById(facultyId);
        Resource res  = resourceService.findById(resourceId); 
        if (fac == null){
            errors.add(FacErrors.FAC101.name()); 
        }else if (res == null){
            errors.add(ResErrors.RES101.name()); 
        }else {
            FacultyResource facres = facRscRep.findByFacultyIdResourceId(facultyId,resourceId);
            if(facres == null){
                errors.add(FacResErrors.FACRES101.name()); 
            }
        }
        return errors;
    }

}
