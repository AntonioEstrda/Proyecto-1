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
import server.server.Model.Access.DAOFaculty;
import server.server.Model.Domain.Faculty;
import org.springframework.transaction.annotation.Transactional;
import server.server.Model.Services.IFacultyService;
import server.server.Model.Services.ILocationService;
import server.server.utilities.Labels;
import server.server.utilities.errors.FacErrors;
import server.server.utilities.errors.LocationErrors;

/**
 *
 * @author anmon
 */
@Service
@EnableTransactionManagement
public class FacultyService implements IFacultyService {

    @Autowired
    private DAOFaculty facultyRepo;

    @Autowired
    private ILocationService locService;

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public Faculty find(Faculty faculty) {
        return facultyRepo.findById(faculty.getFacultyId()).orElse(null);
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> save(Faculty faculty) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList errors = new ArrayList();
        if (this.find(faculty) != null) {
            errors.add(FacErrors.FAC102.name());
            returns.put(Labels.objectReturn, null);
        } else {
            if (locService.find(faculty.getLocation().getLocationId()) != null) {
                Faculty entitySaved = facultyRepo.save(faculty);
                returns.put(Labels.objectReturn, entitySaved);
            } else {
                errors.add(LocationErrors.LOC101.name());
                returns.put(Labels.objectReturn, null);
            }
        }
        returns.put(Labels.errors, errors);
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public ArrayList<Faculty> getAll() {
        return (ArrayList<Faculty>) facultyRepo.findAll();
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> update(Faculty faculty) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList();
        Faculty old = this.find(faculty);
        if (old == null) {
            errors.add(FacErrors.FAC101.name());
        } else {
            if (locService.find(faculty.getLocation().getLocationId()) != null) {
                old = facultyRepo.save(faculty);
            } else {
                errors.add(LocationErrors.LOC101.name());
            }
        }
        returns.put(Labels.errors, errors);
        returns.put(Labels.objectReturn, old);
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> delete(Long FacultyId) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList();
        Faculty old = facultyRepo.findById(FacultyId).orElse(null);
        if (old != null) {
            facultyRepo.delete(old);
        } else {
            errors.add(FacErrors.FAC101.name());
        }
        returns.put(Labels.errors, errors);
        returns.put(Labels.objectReturn, old);
        return returns;
    }

    @Override
    public Faculty findById(long faculty) {
        return facultyRepo.findById(faculty).orElse(null);
    }
}
