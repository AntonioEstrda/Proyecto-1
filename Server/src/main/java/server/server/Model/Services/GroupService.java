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
import server.server.Model.Access.DAOGroup;
import server.server.Model.Domain.Group;
import server.server.utilities.Labels;
import server.server.utilities.errors.GroupErrors;

/**
 *
 * @author Fernando
 */
@Service
@EnableTransactionManagement
public class GroupService implements IGroupService{
    
    @Autowired 
    private DAOGroup groupRepo; 
    
    @Autowired
    private IAcademicPeriodService apService;
    
    @Autowired
    private ISubjectService subjService;

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public Group find(Group group) {
        return groupRepo.findById(group.getGroupId()).orElse(null);
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> save(Group group) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList errors = new ArrayList();
        if (this.find(group) != null) {
            errors.add(GroupErrors.GROUP102.name());
            returns.put(Labels.objectReturn, null);
        } else {
            if (apService.find(group.getAcademicPeriod()) != null) {
                Group entitySaved = groupRepo.save(group);
                returns.put(Labels.objectReturn, entitySaved);
            }else if (subjService.find(group.getSubject()) != null) {
                errors.add(GroupErrors.GROUP105.name());
            }else {
                errors.add(GroupErrors.GROUP106.name());
            }
            Group entitySaved = groupRepo.save(group);
            returns.put(Labels.objectReturn, entitySaved);
        }
        returns.put(Labels.errors, errors);
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public ArrayList<Group> getAll() {
        return (ArrayList<Group>) groupRepo.findAll();
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> update(Group group) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList(); 
        Group old = this.find(group);
        if (old == null) {
            errors.add(GroupErrors.GROUP101.name());  
        } else {
            old = groupRepo.save(group);
        }
        returns.put(Labels.errors, errors);  
        returns.put(Labels.objectReturn, old);   
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> delete(Long GroupId) {

        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList(); 
        Group old = groupRepo.findById(GroupId).orElse(null);
        if(old != null){
            groupRepo.delete(old);
        }else{
            errors.add(GroupErrors.GROUP101.name());  
        }
        returns.put(Labels.errors, errors); 
        returns.put(Labels.objectReturn, old); 
        return returns; 
    }
    
    @Override
    public Group findById(long group) {
        return groupRepo.findById(group).orElse(null);
    }
}
