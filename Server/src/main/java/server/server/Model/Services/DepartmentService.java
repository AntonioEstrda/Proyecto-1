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
import server.server.Model.Access.DAODepartment;
import server.server.Model.Domain.Department;

/**
 *
 * @author Fernando
 */
@Service
@EnableTransactionManagement
public class DepartmentService implements IDepartmentService{
    
    @Autowired 
    private DAODepartment deptRepo; 

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public Department find(Department department) {
        return deptRepo.findById(department.getDepartmentId()).orElse(null);
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Department save(Department department) {
        if (this.find(department) == null) {
            Department entitySaved = deptRepo.save(department);
            return entitySaved;
        }
        return null;
    }

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public ArrayList<Department> getAll() {
        return (ArrayList<Department>) deptRepo.findAll();
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Department update(Department department) {
        Department old = this.find(department);
        if (old == null) {
            return null;
        } else {
            return deptRepo.save(department);
        }
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Department delete(Long DepartmentId) {

        Department old = deptRepo.findById(DepartmentId).orElse(null);
        if (old == null) {
            return null;
        } else {
            deptRepo.delete(old);
            return old;
        }
    }
}
