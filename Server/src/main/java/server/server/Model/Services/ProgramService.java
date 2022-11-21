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
import server.server.Model.Access.DAOProgram;
import server.server.Model.Domain.Program;

/**
 *
 * @author Fernando
 */
@Service
@EnableTransactionManagement
public class ProgramService implements IProgramService{
    
    @Autowired 
    private DAOProgram programRepo; 

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public Program find(Program program) {
        return programRepo.findById(program.getProgramID()).orElse(null);
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Program save(Program program) {
        if (this.find(program) == null) {
            Program entitySaved = programRepo.save(program);
            return entitySaved;
        }
        return null;
    }

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public ArrayList<Program> getAll() {
        return (ArrayList<Program>) programRepo.findAll();
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Program update(Program program) {
        Program old = this.find(program);
        if (old == null) {
            return null;
        } else {
            return programRepo.save(program);
        }
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Program delete(Long ProgramId) {

        Program old = programRepo.findById(ProgramId).orElse(null);
        if (old == null) {
            return null;
        } else {
            programRepo.delete(old);
            return old;
        }
    }
}
