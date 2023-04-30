/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Services;

import java.util.List;
import java.util.Map;
import server.server.Model.Domain.Schedule;
import server.server.utilities.Labels;

/**
 *
 * @author anmon
 */
public interface IScheduleService {

    public Map<Labels, Object> find(Schedule schedule);

    public Map<Labels, Object> add(Schedule schedule, Long departId);

    public Map<Labels, Object> update(Schedule schedule, Long departId);

    public Map<Labels, Object> delete(long SchedId, Long departId);

    public Map<Labels, Object> findByProgSem(long prog, long sem);

    public Map<Labels, Object> findByTypesEventsAndDepartment(long departId, List<String> types);

    public Map<Labels, Object> findByEnvId(long envId);

}
