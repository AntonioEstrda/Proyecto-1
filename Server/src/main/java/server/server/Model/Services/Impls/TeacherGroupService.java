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
import server.server.Model.Access.DAOTeacherGroup;
import server.server.Model.Domain.TeacherGroup;
import server.server.Model.Services.IGroupService;
import server.server.Model.Services.ITeacherGroupService;
import server.server.Model.Services.ITeacherService;
import server.server.utilities.Labels;
import server.server.utilities.errors.GroupErrors;
import server.server.utilities.errors.TeacherErrors;
import server.server.utilities.errors.TeacherGroupErrors;

/**
 *
 * @author Fernando
 */
@Service
@EnableTransactionManagement
public class TeacherGroupService implements ITeacherGroupService{
    
    @Autowired 
    private DAOTeacherGroup teacherGroupRepo; 
    
    @Autowired
    private ITeacherService tchService;
    
    @Autowired
    private IGroupService gpService;

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public TeacherGroup find(TeacherGroup teacherGroup) {
        return teacherGroupRepo.findById(teacherGroup.getTeacherGroupID()).orElse(null);
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> save(TeacherGroup teacherGroup) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList errors = new ArrayList();
        if (this.find(teacherGroup) != null) {
            errors.add(TeacherGroupErrors.TG105.name());
            returns.put(Labels.objectReturn, null);
        } else {
            if (tchService.find(teacherGroup.getTeacher()) != null) {
                if (gpService.find(teacherGroup.getGroup()) != null) {
                    if((teacherGroup.getGroup().getSubject().getIntensity())<=(((teacherGroupRepo.hourVinculation(teacherGroup.getTeacher().getTeacherID()))-(teacherGroupRepo.academicHoursteacher(teacherGroup.getTeacher().getTeacherID()))))){
                        TeacherGroup entitySaved = teacherGroupRepo.save(teacherGroup);
                        returns.put(Labels.objectReturn, entitySaved);
                    }
                    else{
                        errors.add(TeacherGroupErrors.TG107.name());
                }
                }else{
                    errors.add(GroupErrors.GROUP101.name());
                    errors.add(TeacherGroupErrors.TG101.name());
                }      
            }else{
                errors.add(TeacherErrors.TCH101.name());
                errors.add(TeacherGroupErrors.TG102.name());
            }
        }
        returns.put(Labels.errors, errors);
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public ArrayList<TeacherGroup> getAll() {
        return (ArrayList<TeacherGroup>) teacherGroupRepo.findAll();
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> update(TeacherGroup teacherGroup) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList(); 
        TeacherGroup old = this.find(teacherGroup);
        if (old == null) {
            errors.add(TeacherGroupErrors.TG106.name());  
        } else {
            old = teacherGroupRepo.save(teacherGroup);
        }
        returns.put(Labels.errors, errors);  
        returns.put(Labels.objectReturn, old);   
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> delete(Long TeacherGroupId) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList(); 
        TeacherGroup old = teacherGroupRepo.findById(TeacherGroupId).orElse(null);
        if(old != null){
            teacherGroupRepo.delete(old);
        }else{
            errors.add(TeacherGroupErrors.TG106.name());  
        }
        returns.put(Labels.errors, errors); 
        returns.put(Labels.objectReturn, old); 
        return returns; 
    }
}
