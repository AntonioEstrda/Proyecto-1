/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Services;

import java.util.Map;
import server.server.Model.Domain.Schedule;
import server.server.utilities.Labels;

/**
 *
 * @author anmon
 */
public interface IScheduleService {

    public Map<Labels, Object> find(Schedule schedule);

    public Map<Labels, Object> add(Schedule schedule);

    public Map<Labels, Object> update(Schedule schedule);

    public Map<Labels, Object> delete(long id);
    
    public Map<Labels, Object> findByProgSem(long prog, long sem);  
    
}
