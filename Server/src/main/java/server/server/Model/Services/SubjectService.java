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
import server.server.Model.Access.DAOSubject;
import server.server.Model.Domain.Subject;

/**
 *
 * @author Fernando
 */
@Service 
@EnableTransactionManagement
public class SubjectService implements ISubjectService{
    
    @Autowired 
    private DAOSubject subjRepo; 

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public Subject find(Subject subject) {
        return subjRepo.findById(subject.getSubjectID()).orElse(null);
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Subject save(Subject subject) {
        if (this.find(subject) == null) {
            Subject entitySaved = subjRepo.save(subject);
            return entitySaved;
        }
        return null;
    }

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public ArrayList<Subject> getAll() {
        return (ArrayList<Subject>) subjRepo.findAll();
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Subject update(Subject subject) {
        Subject old = this.find(subject);
        if (old == null) {
            return null;
        } else {
            return subjRepo.save(subject);
        }
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Subject delete(Long SubjectId) {

        Subject old = subjRepo.findById(SubjectId).orElse(null);
        if (old == null) {
            return null;
        } else {
            subjRepo.delete(old);
            return old;
        }
    }
    
}
