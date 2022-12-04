/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import server.server.Model.Access.DAOAssignmentResource;
import server.server.Model.Domain.AssignmentResource;
import server.server.Model.Domain.FacultyResource;
import server.server.Model.Domain.Resource;
import server.server.utilities.Labels;
import server.server.utilities.errors.AssResErrors;
import server.server.utilities.errors.EnvErrors;
import server.server.utilities.errors.FacResErrors;
import server.server.utilities.errors.ResErrors;

/**
 *
 * @author anmon
 */
@Service
@EnableTransactionManagement
public class AssignmentResourceService implements IAssignmentResourceService {

    @Autowired
    private DAOAssignmentResource AssResRepo;

    @Autowired
    private FacultyResourceService facResSer;

    @Autowired
    private IResourceTypeService typeSer;

    @Override
    public Map<Labels, Object> save(long facId, long envId, long resId) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList();
        AssignmentResource ar = null;
        if (this.AssResRepo.findByIsDisableAndEnvIdAndResId(false, envId, resId) == null) {
            FacultyResource res = this.facResSer.findByFacultyIdResourceId(facId, resId);
            FacultyResource env = this.facResSer.findByFacultyIdResourceId(facId, envId);
            if (res == null) {
                errors.add(ResErrors.RES101.name());
            }
            if (env == null) {
                errors.add(EnvErrors.ENV101.name());
            }

            Resource resObj, envObj;
            if (res != null && env != null) {
                resObj = res.getResourceFR();
                envObj = env.getResourceFR();
                boolean ban1 = this.typeSer.globalType(resObj.getResourceType().getResourceTypeId())
                        != IResourceTypeService.ENVIRONMENTTYPE;
                boolean ban2 = this.typeSer.globalType(envObj.getResourceType().getResourceTypeId())
                        == IResourceTypeService.ENVIRONMENTTYPE;
                if (ban1 && ban2) {
                    ar = new AssignmentResource();
                    ar.setEnv(envObj);
                    ar.setRes(resObj);
                    ar.setDisable(false);
                    ar.setRegistrerDate(new Date(System.currentTimeMillis()));
                    ar = this.AssResRepo.save(ar);
                }
                if (!ban1) {
                    errors.add(EnvErrors.ENV101.name());
                }
                if (!ban2) {
                    errors.add(EnvErrors.ENV101.name());
                }
            }
        } else {
            errors.add(AssResErrors.ASSRES102.name());
        }

        returns.put(Labels.errors, errors);
        returns.put(Labels.objectReturn, ar);
        return returns;

    }

    @Override
    public Map<Labels, Object> detach(long facId, long envId, long resId) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList();
        AssignmentResource ar = this.AssResRepo.findByIsDisableAndEnvIdAndResId(false, envId, resId);
        if (ar != null) {
            FacultyResource res = this.facResSer.findByFacultyIdResourceId(facId, resId);
            FacultyResource env = this.facResSer.findByFacultyIdResourceId(facId, envId);
            if (res == null) {
                errors.add(ResErrors.RES101.name());
            }
            if (env == null) {
                errors.add(EnvErrors.ENV101.name());
            }
            if (res != null && env != null) {
                ar.setDisable(true);
                ar.setFinalDate(new Date(System.currentTimeMillis()));
                this.AssResRepo.save(ar); 
            }
        } else {
            errors.add(AssResErrors.ASSRES101.name());
        }

        returns.put(Labels.errors, errors);
        returns.put(Labels.objectReturn, ar);
        return returns;
    }

    @Override
    public Map<Labels, Object> findByEnvId(long facId, long envId) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList();
        FacultyResource obj = this.facResSer.findByFacultyIdResourceId(facId, envId);
        ArrayList<Resource> obj2 = null;
        if (obj == null) {
            errors.add(FacResErrors.FACRES101.name());
        } else {
            Resource res = obj.getResourceFR();
            if (this.typeSer.globalType(res.getResourceType().getResourceTypeId())
                    != IResourceTypeService.ENVIRONMENTTYPE) {
                errors.add(ResErrors.RES112.name());
            } else {
                obj2 = new ArrayList();
                List<AssignmentResource> obj3 = this.AssResRepo.findAllByEnvIdAndIsDisable(envId, false);
                for (AssignmentResource o : obj3) {
                    obj2.add(o.getRes());
                }
            }
        }
        returns.put(Labels.errors, errors);
        returns.put(Labels.objectReturn, obj2);
        return returns;
    }

    @Override
    public AssignmentResource findByResId(long facId, long resId) {
        FacultyResource res = this.facResSer.findByFacultyIdResourceId(facId, resId);
        if (res != null) {
            return this.AssResRepo.findByIsDisableAndResId(false, resId);
        }
        return null;
    }

}
