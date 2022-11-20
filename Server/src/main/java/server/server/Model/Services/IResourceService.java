/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.server.Model.Services;

import java.util.ArrayList;
import server.server.Model.Domain.Resource;

/**
 *
 * @author anmon
 */
public interface IResourceService {

    /**
     *
     * @param id
     * @return
     */
    public Resource delete(Long id);

    /**
     *
     * @param env
     * @return
     */
    public Resource update(Resource env);

    /**
     *
     * @param env
     * @return
     */
    public Resource save(Resource env);

    /**
     *
     * @param envType
     * @return
     */
    public Resource find(Resource envType);

    /**
     *
     * @return
     */
    public ArrayList<Resource> getAll();
    
}
