/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Services.Impls;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import server.server.Model.Access.DAOTeacherGroup;
import server.server.Model.Domain.TeacherGroup;
import server.server.Model.Services.ITeacherGroupService;

/**
 *
 * @author Fernando
 */
@Service 
@EnableTransactionManagement
public class TeacherGroupService implements ITeacherGroupService{
    
    
    @Autowired 
    private DAOTeacherGroup teacherGroupRepo; 

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public TeacherGroup find(TeacherGroup teacherGroup) {
        return teacherGroupRepo.findById(teacherGroup.getTeacherGroupID()).orElse(null);
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public TeacherGroup save(TeacherGroup teacherGroup) {
        if (this.find(teacherGroup) == null) {
            TeacherGroup entitySaved = teacherGroupRepo.save(teacherGroup);
            return entitySaved;
        }
        return null;
    }

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public ArrayList<TeacherGroup> getAll() {
        return (ArrayList<TeacherGroup>) teacherGroupRepo.findAll();
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public TeacherGroup update(TeacherGroup teacherGroup) {
        TeacherGroup old = this.find(teacherGroup);
        if (old == null) {
            return null;
        } else {
            return teacherGroupRepo.save(teacherGroup);
        }
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public TeacherGroup delete(Long TeacherGroupId) {

        TeacherGroup old = teacherGroupRepo.findById(TeacherGroupId).orElse(null);
        if (old == null) {
            return null;
        } else {
            teacherGroupRepo.delete(old);
            return old;
        }
    }
}
