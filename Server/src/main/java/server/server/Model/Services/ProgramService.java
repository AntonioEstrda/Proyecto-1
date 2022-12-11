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
import server.server.Model.Access.DAOProgram;
import server.server.Model.Domain.Program;
import server.server.utilities.Labels;
import server.server.utilities.errors.ProgErrors;

/**
 *
 * @author Fernando
 */
@Service
@EnableTransactionManagement
public class ProgramService implements IProgramService{
    
    @Autowired 
    private DAOProgram programRepo; 
    
    @Autowired
    private IDepartmentService deptService;

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public Program find(Program program) {
        return programRepo.findById(program.getProgramId()).orElse(null);
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> save(Program program) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList errors = new ArrayList();
        if (this.find(program) != null) {
            errors.add(ProgErrors.PRG102.name());
            returns.put(Labels.objectReturn, null);
        } else {
            if (deptService.find(program.getDepartment()) != null) {
                Program entitySaved = programRepo.save(program);
                returns.put(Labels.objectReturn, entitySaved);
            } else {
                errors.add(ProgErrors.PRG101.name());
            }
            Program entitySaved = programRepo.save(program);
            returns.put(Labels.objectReturn, entitySaved);
        }
        returns.put(Labels.errors, errors);
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public ArrayList<Program> getAll() {
        return (ArrayList<Program>) programRepo.findAll();
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> update(Program program) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList(); 
        Program old = this.find(program);
        if (old == null) {
            errors.add(ProgErrors.PRG101.name());  
        } else {
            old = programRepo.save(program);
        }
        returns.put(Labels.errors, errors);  
        returns.put(Labels.objectReturn, old);   
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> delete(Long ProgramId) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList(); 
        Program old = programRepo.findById(ProgramId).orElse(null);
        if(old != null){
            programRepo.delete(old);
        }else{
            errors.add(ProgErrors.PRG101.name());  
        }
        returns.put(Labels.errors, errors); 
        returns.put(Labels.objectReturn, old); 
        return returns; 
    }
    
    @Override
    public Program findById(long program) {
        return programRepo.findById(program).orElse(null);
    }
}
