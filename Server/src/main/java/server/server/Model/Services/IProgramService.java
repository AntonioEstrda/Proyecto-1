/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.server.Model.Services;

import java.util.ArrayList;
import java.util.Map;
import server.server.Model.Domain.Program;
import server.server.Model.Services.Impls.CustomUserDetails;
import server.server.utilities.Labels;

/**
 *
 * @author Fernando
 */
public interface IProgramService {

    /**
     * Find an program
     *
     * @param program
     * @return Program
     */
    public Program find(Program program);

    /**
     * save a program
     *
     * @param program
     * @return Program
     */
    public Map<Labels, Object> save(Program program);

    /**
     * list all programs
     *
     * @return ArrayList
     */
    public ArrayList<Program> getAll();

    /**
     * Updates an program
     *
     * @param program
     * @return Program
     */
    public Map<Labels, Object> update(Program program);

    /**
     * Deactivates an Program
     *
     * @param programId
     * @return Program
     */
    public Map<Labels, Object> delete(Long programId);

    public Program findById(long program);

    public Program findByCode(String code);

    public Map<Labels, Object> getAll(long departmentId);

    public boolean validateUserProgram(long programId, CustomUserDetails user);

}
