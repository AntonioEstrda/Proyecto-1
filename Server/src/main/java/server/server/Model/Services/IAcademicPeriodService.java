/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.server.Model.Services;

import java.util.ArrayList;
import server.server.Model.Domain.AcademicPeriod;

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
    public AcademicPeriod save(AcademicPeriod academicPeriod); 

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
    public AcademicPeriod update(AcademicPeriod academicPeriod);  
    
    
    /**
     * Deactivates an AcademicPeriod
     * @param academicPeriodId
     * @return AcademicPeriod
     */
    public AcademicPeriod delete(Long academicPeriodId);
    
}
