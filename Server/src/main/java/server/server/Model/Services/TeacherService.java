/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Services;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.server.Model.Access.DAOTeacher;
import server.server.Model.Domain.Teacher;

/**
 *
 * @author Fernando
 */
@Service
public class TeacherService implements ITeacherService{
    
    
    @Autowired 
    private DAOTeacher teacherRepo; 

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public Teacher find(Teacher teacher) {
        return teacherRepo.findById(teacher.getTeacherID()).orElse(null);
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Teacher save(Teacher teacher) {
        if (this.find(teacher) == null) {
            Teacher entitySaved = teacherRepo.save(teacher);
            return entitySaved;
        }
        return null;
    }

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public ArrayList<Teacher> getAll() {
        return (ArrayList<Teacher>) teacherRepo.findAll();
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Teacher update(Teacher teacher) {
        Teacher old = this.find(teacher);
        if (old == null) {
            return null;
        } else {
            return teacherRepo.save(teacher);
        }
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Teacher delete(Long TeacherId) {

        Teacher old = teacherRepo.findById(TeacherId).orElse(null);
        if (old == null) {
            return null;
        } else {
            teacherRepo.delete(old);
            return old;
        }
    }
}
