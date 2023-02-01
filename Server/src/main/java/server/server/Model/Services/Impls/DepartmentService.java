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
import server.server.Model.Access.DAODepartment;
import server.server.Model.Domain.Department;
import server.server.Model.Services.IDepartmentService;
import server.server.Model.Services.IFacultyService;
import server.server.Model.Services.ILocationService;
import server.server.utilities.Labels;
import server.server.utilities.errors.DeptErrors;
import server.server.utilities.errors.FacErrors;
import server.server.utilities.errors.LocationErrors;

/**
 *
 * @author Fernando
 */
@Service
@EnableTransactionManagement
public class DepartmentService implements IDepartmentService {

    @Autowired
    private DAODepartment deptRepo;

    @Autowired
    private IFacultyService facService;

    @Autowired
    private ILocationService locService;

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public Department find(Department department) {
        return deptRepo.findById(department.getDepartmentId()).orElse(null);
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> save(Department department) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList errors = new ArrayList();
        if (this.find(department) != null) {
            errors.add(DeptErrors.DEPT102.name());  
        } else {
            if (facService.find(department.getFacultad()) != null) {
                if (deptRepo.findByCode(department.getCode()) != null) {
                    errors.add(DeptErrors.DEPT112.name());
                }else if (department.getLocation() == null) {
                    errors.add(DeptErrors.DEPT105.name());
                }else if (locService.find(department.getLocation().getLocationId()) == null) {
                    errors.add(LocationErrors.LOC101.name());
                }else if (errors.isEmpty()) {
                    department = deptRepo.save(department);
                }
            } else {
                errors.add(FacErrors.FAC101.name());
                errors.add(DeptErrors.DEPT110.name());
            }
        }

        returns.put(Labels.errors, errors);
        returns.put(Labels.objectReturn, department);
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public ArrayList<Department> getAll() {
        return (ArrayList<Department>) deptRepo.findAll();
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> update(Department department) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList();
        Department old = this.find(department);
        if (old == null) {
            errors.add(DeptErrors.DEPT101.name());
        } else {
            if (facService.find(department.getFacultad()) != null) {
                Department entitySaved = deptRepo.save(department);
                old = deptRepo.save(department);

            } else {
                errors.add(FacErrors.FAC101.name());
            }

        }
        department.setCode(department.getCode().toUpperCase());
        Department r2 = deptRepo.findByCode(department.getCode());
        if (r2 != null && r2.getDepartmentId() != department.getDepartmentId()) {
            errors.add(DeptErrors.DEPT111.name());
        }
        if (errors.isEmpty()) {
            department = deptRepo.save(department);
        }
        returns.put(Labels.errors, errors);
        returns.put(Labels.objectReturn, old);
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> delete(Long DepartmentId) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList();
        Department old = deptRepo.findById(DepartmentId).orElse(null);
        if (old != null) {
            deptRepo.delete(old);
        } else {
            errors.add(DeptErrors.DEPT101.name());
        }
        returns.put(Labels.errors, errors);
        returns.put(Labels.objectReturn, old);
        return returns;
    }

    @Override
    public Department findById(long department) {
        return deptRepo.findById(department).orElse(null);
    }
    
    @Override
    public Department findByCode(String code) {
        return deptRepo.findByCode(code);
    }
}
