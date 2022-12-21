/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.server.Model.Services;

import java.util.List;
import java.util.Map;
import server.server.Model.Domain.Location;
import server.server.utilities.Labels;

/**
 *
 * @author anmon
 */
public interface ILocationService {

    public Location find(long locationId);

    public Map<Labels, Object> delete(long locationId);

    public Map<Labels, Object> update(Location locationId);

    public Map<Labels, Object> save(Location location);
    
    public List<Location> all();

}
