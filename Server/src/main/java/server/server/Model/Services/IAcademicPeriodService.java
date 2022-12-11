/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.server.Model.Services;

import java.util.ArrayList;
import java.util.Map;
import server.server.Model.Domain.AcademicPeriod;
import server.server.utilities.Labels;

/**
 *
 * @author Fernando
 */
public interface IAcademicPeriodService {
    /**
     * Find an academicPeriod
     * @param academicPeriod
     * @return AcademicPeriod 
     */
    public AcademicPeriod find(AcademicPeriod academicPeriod); 
    
    /**
     * save a academicPeriod 
     * @param academicPeriod
     * @return AcademicPeriod
     */
    public Map<Labels, Object> save(AcademicPeriod academicPeriod); 

    /**
     * list all academicPeriods 
     * @return ArrayList
     */
    public ArrayList<AcademicPeriod> getAll();  
    
    
    /**
     * Updates an academicPeriod
     * @param academicPeriod
     * @return AcademicPeriod
     */
    public Map<Labels, Object> update(AcademicPeriod academicPeriod);  
    
    
    /**
     * Deactivates an AcademicPeriod
     * @param academicPeriodId
     * @return AcademicPeriod
     */
    public Map<Labels, Object> delete(Long academicPeriodId);
    
}
