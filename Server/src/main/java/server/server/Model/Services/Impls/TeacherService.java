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
import server.server.Model.Access.DAOTeacher;
import server.server.Model.Domain.Teacher;
import server.server.Model.Services.ITeacherService;
import server.server.utilities.Labels;
import server.server.utilities.errors.TeacherErrors;

/**
 *
 * @author Fernando
 */
@Service
@EnableTransactionManagement
public class TeacherService implements ITeacherService {

    @Autowired
    private DAOTeacher teacherRepo;

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public Teacher find(Teacher teacher) {
        return teacherRepo.findById(teacher.getTeacherID()).orElse(null);
    }

    //Nos sirve para validar si el dato identificación que estamos recibiendo es numerico
    private static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> save(Teacher teacher) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList errors = new ArrayList();
        if (isNumeric(teacher.getNumIden())) {
            if (this.find(teacher) != null) {
                errors.add(TeacherErrors.TCH102.name());
                returns.put(Labels.objectReturn, null);
            } else {
                if (teacherRepo.findByNumIden(teacher.getNumIden()) == null) {
                    Teacher entitySaved = teacherRepo.save(teacher);
                    returns.put(Labels.objectReturn, entitySaved);
                } else {
                    errors.add(TeacherErrors.TCH108.name());
                }
            }
        } else {
            errors.add(TeacherErrors.TCH106.name());
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
            if (isNumeric(teacher.getNumIden())) {
                old = teacherRepo.save(teacher);
            } else {
                errors.add(TeacherErrors.TCH106.name());
            }
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
        if (old != null) {
            teacherRepo.delete(old);
        } else {
            errors.add(TeacherErrors.TCH101.name());
        }
        returns.put(Labels.errors, errors);
        returns.put(Labels.objectReturn, old);
        return returns;
    }

    @Override
    public Teacher findByNumIden(String numIden) {
        return teacherRepo.findByNumIden(numIden);
    }

    @Override
    public long findAssDepartment(long teacher) {
        return teacherRepo.findAssDepartment(teacher);
    }
}
