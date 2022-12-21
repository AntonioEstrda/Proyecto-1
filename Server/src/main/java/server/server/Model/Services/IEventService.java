/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.server.Model.Services;

import java.util.Map;
import server.server.Model.Domain.Event;
import server.server.utilities.Labels;

/**
 *
 * @author anmon
 */
public interface IEventService {

    public Map<Labels, Object> create(Event event, long teacherId);

    public Map<Labels, Object> delete(long eventId);

    public Map<Labels, Object> update(Event event);

    public Map<Labels, Object> findbyId(long eventId);

}
