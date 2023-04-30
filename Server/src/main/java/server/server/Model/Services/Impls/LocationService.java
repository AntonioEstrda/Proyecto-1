/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Services.Impls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import server.server.Model.Access.DAOLocation;
import server.server.Model.Domain.Location;
import server.server.Model.Services.ILocationService;
import server.server.utilities.Labels;
import server.server.utilities.errors.LocationErrors;

@Service
@EnableTransactionManagement
public class LocationService implements ILocationService {

    @Autowired
    private DAOLocation locationRepo;

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public Location find(long locationId) {
        return locationRepo.findById(locationId).orElse(null);
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> delete(long locationId) {
        Map<Labels, Object> returns = new HashMap();
        Location l = locationRepo.findById(locationId).orElse(null);
        ArrayList<String> errors = new ArrayList();
        if (l != null) {
            locationRepo.delete(l);
        } else {
            l = null;
            errors.add(LocationErrors.LOC101.name());
        }
        returns.put(Labels.errors, errors);
        returns.put(Labels.objectReturn, l);
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> update(Location location) {
        Map<Labels, Object> returns = new HashMap();
        Location l = locationRepo.findById(location.getLocationId()).orElse(null);
        ArrayList<String> errors = new ArrayList();
        if (l != null) {
            location = locationRepo.save(location);
        } else {
            location = null;
            errors.add(LocationErrors.LOC101.name());
        }
        returns.put(Labels.errors, errors);
        returns.put(Labels.objectReturn, location);
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> save(Location location) {
        Map<Labels, Object> returns = new HashMap();
        Location l = locationRepo.findById(location.getLocationId()).orElse(null);
        ArrayList<String> errors = new ArrayList();
        if (l == null) {
            location = locationRepo.save(location);
        } else {
            location = null;
            errors.add(LocationErrors.LOC103.name());
        }
        returns.put(Labels.errors, errors);
        returns.put(Labels.objectReturn, location);
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public List<Location> all() {
        return locationRepo.findAll();
    }

}
