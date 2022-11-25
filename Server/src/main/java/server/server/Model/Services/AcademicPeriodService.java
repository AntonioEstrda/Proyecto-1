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
import server.server.Model.Access.DAOAcademicPeriod;
import server.server.Model.Domain.AcademicPeriod;

/**
 *
 * @author Fernando
 */
@Service
@EnableTransactionManagement
public class AcademicPeriodService implements IAcademicPeriodService{
    
    @Autowired 
    private DAOAcademicPeriod deptRepo; 

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public AcademicPeriod find(AcademicPeriod academicPeriod) {
        return deptRepo.findById(academicPeriod.getAcademicPeriodID()).orElse(null);
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public AcademicPeriod save(AcademicPeriod academicPeriod) {
        if (this.find(academicPeriod) == null) {
            AcademicPeriod entitySaved = deptRepo.save(academicPeriod);
            return entitySaved;
        }
        return null;
    }

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public ArrayList<AcademicPeriod> getAll() {
        return (ArrayList<AcademicPeriod>) deptRepo.findAll();
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public AcademicPeriod update(AcademicPeriod academicPeriod) {
        AcademicPeriod old = this.find(academicPeriod);
        if (old == null) {
            return null;
        } else {
            return deptRepo.save(academicPeriod);
        }
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public AcademicPeriod delete(Long AcademicPeriodId) {

        AcademicPeriod old = deptRepo.findById(AcademicPeriodId).orElse(null);
        if (old == null) {
            return null;
        } else {
            deptRepo.delete(old);
            return old;
        }
    }
}
