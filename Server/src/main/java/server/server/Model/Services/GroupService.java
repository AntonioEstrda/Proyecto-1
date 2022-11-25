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
import server.server.Model.Access.DAOGroup;
import server.server.Model.Domain.Group;

/**
 *
 * @author Fernando
 */
@Service
@EnableTransactionManagement
public class GroupService implements IGroupService{
    
    @Autowired 
    private DAOGroup groupRepo; 

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public Group find(Group group) {
        return groupRepo.findById(group.getGroupID()).orElse(null);
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Group save(Group group) {
        if (this.find(group) == null) {
            Group entitySaved = groupRepo.save(group);
            return entitySaved;
        }
        return null;
    }

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public ArrayList<Group> getAll() {
        return (ArrayList<Group>) groupRepo.findAll();
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Group update(Group group) {
        Group old = this.find(group);
        if (old == null) {
            return null;
        } else {
            return groupRepo.save(group);
        }
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Group delete(Long GroupId) {

        Group old = groupRepo.findById(GroupId).orElse(null);
        if (old == null) {
            return null;
        } else {
            groupRepo.delete(old);
            return old;
        }
    }
}
