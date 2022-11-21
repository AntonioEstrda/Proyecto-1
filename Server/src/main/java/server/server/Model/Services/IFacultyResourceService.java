/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.server.Model.Services;

import java.util.Map;
import server.server.Model.Domain.FacultyResource;

/**
 *
 * @author anmon
 */
public interface IFacultyResourceService {
    
    public Map<String, Object> save(FacultyResource fr);

    public Map<String, Object> update(FacultyResource fr);
    
    public Map<String, Object> findByResource(long resourceId); 
}
