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
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import server.server.Model.Access.DAOTeacher;
import server.server.Model.Domain.Teacher;
import server.server.utilities.Labels;
import server.server.utilities.errors.TeacherErrors;

/**
 *
 * @author Fernando
 */
@Service 
@EnableTransactionManagement
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
    public Map<Labels, Object> save(Teacher teacher) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList errors = new ArrayList();
        if (this.find(teacher) != null) {
            errors.add(TeacherErrors.TCH102.name());
            returns.put(Labels.objectReturn, null);
        } else {
                Teacher entitySaved = teacherRepo.save(teacher);
                returns.put(Labels.objectReturn, entitySaved);
        }
        returns.put(Labels.errors, errors);
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public ArrayList<Teacher> getAll() {
        return (ArrayList<Teacher>) teacherRepo.findAll();
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> update(Teacher teacher) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList(); 
        Teacher old = this.find(teacher);
        if (old == null) {
            errors.add(TeacherErrors.TCH101.name());  
        } else {
            old = teacherRepo.save(teacher);
        }
        returns.put(Labels.errors, errors);  
        returns.put(Labels.objectReturn, old);   
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> delete(Long TeacherId) {

        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList(); 
        Teacher old = teacherRepo.findById(TeacherId).orElse(null);
        if(old != null){
            teacherRepo.delete(old);
        }else{
            errors.add(TeacherErrors.TCH101.name());  
        }
        returns.put(Labels.errors, errors); 
        returns.put(Labels.objectReturn, old); 
        return returns; 
    }
}
