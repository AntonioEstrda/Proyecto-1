/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.server.Model.Services;

import java.util.ArrayList;
import java.util.Map;
import server.server.Model.Domain.FacultyResource;
import server.server.Model.Domain.Resource;
import server.server.utilities.Labels;

/**
 *
 * @author anmon
 */
public interface IFacultyResourceService {

    public Map<Labels, Object> save(FacultyResource fr);

    public Map<Labels, Object> update(FacultyResource fr);

    public ArrayList<FacultyResource> findByFacultyId(long facultyId);
    
    public ArrayList<Resource> findByFacultyIdRes(long facultyId);  
    
    public FacultyResource findByFacultyIdResourceId(long facultyId, long resourceId);  
    
    public Map<Labels, Object> addNewOneReource(long facultyId, Resource res);  
}
