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
import server.server.Model.Access.DAOHourlyAssignment;
import server.server.Model.Domain.HourlyAssignment;

/**
 *
 * @author Fernando
 */
@Service
@EnableTransactionManagement
public class HourlyAssignmentService implements IHourlyAssignmentService{
    
    @Autowired 
    private DAOHourlyAssignment deptRepo; 

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public HourlyAssignment find(HourlyAssignment hourlyAssignment) {
        return deptRepo.findById(hourlyAssignment.getVinculationId()).orElse(null);
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public HourlyAssignment save(HourlyAssignment hourlyAssignment) {
        if (this.find(hourlyAssignment) == null) {
            HourlyAssignment entitySaved = deptRepo.save(hourlyAssignment);
            return entitySaved;
        }
        return null;
    }

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public ArrayList<HourlyAssignment> getAll() {
        return (ArrayList<HourlyAssignment>) deptRepo.findAll();
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public HourlyAssignment update(HourlyAssignment hourlyAssignment) {
        HourlyAssignment old = this.find(hourlyAssignment);
        if (old == null) {
            return null;
        } else {
            return deptRepo.save(hourlyAssignment);
        }
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public HourlyAssignment delete(Long HourlyAssignmentId) {

        HourlyAssignment old = deptRepo.findById(HourlyAssignmentId).orElse(null);
        if (old == null) {
            return null;
        } else {
            deptRepo.delete(old);
            return old;
        }
    }
}
