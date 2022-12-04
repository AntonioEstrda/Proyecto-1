/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.server.Model.Services;

import java.util.Map;
import server.server.Model.Domain.AssignmentResource;
import server.server.utilities.Labels;

/**
 *
 * @author anmon
 */
public interface IAssignmentResourceService {

    public Map<Labels, Object> save(long facId, long envid, long resId);

    public Map<Labels, Object> detach(long facid, long envid, long resId);

    public Map<Labels, Object> findByEnvId(long facid, long envid);

    public AssignmentResource findByResId(long facid, long resId);

}
