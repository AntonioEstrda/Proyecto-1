/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.server.Model.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import server.server.Model.Domain.Resource;
import server.server.utilities.Labels;

/**
 * Resource Service Interface
 *
 * @author anmon
 */
public interface IResourceService {

    /**
     * Disables a Resource
     *
     * @param id ResourceId
     * @return Resource
     */
    public Map<Labels, Object> delete(Long id);

    /**
     * Updates a Resource
     *
     * @param res Resource to map
     * @return Resource
     */
    public Map<Labels, Object> update(Resource res);

    /**
     * Add a new one Resource
     *
     * @param res Resource to map
     * @return Resource
     */
    public Map<Labels, Object> save(Resource res);

    /**
     * Find a resource
     *
     * @param res Resource to map
     * @return Resource
     */
    public Resource find(Resource res);

    /**
     * list all Resources
     *
     * @return ArrayList Resources
     */
    public ArrayList<Resource> getAll();

    /**
     * find by code and number
     *
     * @param code
     * @param number
     * @return
     */
    public Resource findByCodeAndNumber(String code, Integer number);

    /**
     *
     * @param id
     * @return
     */
    public Resource findById(long id);

    public List<Resource> findByIds(List<Long> ids);

    public boolean findAssFaculty(long resource, long facultyId);
}
