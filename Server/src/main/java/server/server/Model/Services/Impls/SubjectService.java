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
import server.server.Model.Access.DAOSubject;
import server.server.Model.Domain.Subject;
import server.server.Model.Services.IProgramService;
import server.server.Model.Services.ISubjectService;
import server.server.utilities.Labels;
import server.server.utilities.errors.ProgErrors;
import server.server.utilities.errors.SubjErrors;

/**
 *
 * @author Fernando
 */
@Service 
@EnableTransactionManagement
public class SubjectService implements ISubjectService{
    
    @Autowired 
    private DAOSubject subjRepo;
    
    @Autowired
    private IProgramService programService;

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public Subject find(Subject subject) {
        return subjRepo.findById(subject.getSubjectID()).orElse(null);
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> save(Subject subject) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList errors = new ArrayList();
        if (this.find(subject) != null) {
            errors.add(SubjErrors.SUBJ102.name());
            returns.put(Labels.objectReturn, null);
        } else {
            if (programService.find(subject.getProgram()) != null) {
                Subject entitySaved = subjRepo.save(subject);
                returns.put(Labels.objectReturn, entitySaved);
            } else {
                errors.add(ProgErrors.PRG101.name());
            }

        }
        returns.put(Labels.errors, errors);
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public ArrayList<Subject> getAll() {
        return (ArrayList<Subject>) subjRepo.findAll();
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> update(Subject subject) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList();
        Subject old = this.find(subject);
        if (old == null) {
            errors.add(SubjErrors.SUBJ101.name());
        } else {
            if (programService.find(subject.getProgram()) != null) {
                Subject entitySaved = subjRepo.save(subject);
                returns.put(Labels.objectReturn, entitySaved);
            } else {
                errors.add(ProgErrors.PRG101.name());
            }

        }
        returns.put(Labels.errors, errors);
        returns.put(Labels.objectReturn, old);
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> delete(Long SubjectId) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList();
        Subject old = subjRepo.findById(SubjectId).orElse(null);
        if (old != null) {
            subjRepo.delete(old);
        } else {
            errors.add(SubjErrors.SUBJ101.name());
        }
        returns.put(Labels.errors, errors);
        returns.put(Labels.objectReturn, old);
        return returns;
    }
    
    @Override
    public Subject findById(long subject) {
        return subjRepo.findById(subject).orElse(null);
    }

    
}
