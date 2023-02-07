/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.server.Model.Services;

import java.util.List;
import java.util.Map;
import server.server.Model.Domain.Event;
import server.server.Model.Services.Impls.CustomUserDetails;
import server.server.utilities.Labels;

/**
 *
 * @author anmon
 */
public interface IEventService {

    public Map<Labels, Object> create(Event event);

    public Map<Labels, Object> delete(long eventId, long departmentId);

    public Map<Labels, Object> update(Event event);

    public Map<Labels, Object> findbyId(long eventId);

    public Map<Labels, Object> findbyCode(String code);

    public Map<Labels, Object> findByDeparmentId(List<Long> departmentId);

    public Map<Labels, Object> findByDepartmentIdAndEventId(long dpo, long eve);

    public Map<Labels, Object> findAllByDepartmentAndEvenType(long departmentId, List<String> types);
    
    public boolean validateUserEvent(long event, CustomUserDetails user);  

}
