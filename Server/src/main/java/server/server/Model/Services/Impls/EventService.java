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
import server.server.Model.Access.DAOEvent;
import server.server.Model.Domain.AcademicPeriod;
import server.server.Model.Domain.Event;
import server.server.Model.Domain.Teacher;
import server.server.Model.Services.IAcademicPeriodService;
import server.server.Model.Services.IEventService;
import server.server.Model.Services.ITeacherService;
import server.server.utilities.Labels;
import server.server.utilities.errors.EvtErrors;
import server.server.utilities.errors.TeacherErrors;

/**
 *
 * @author anmon
 */
@Service
@EnableTransactionManagement
public class EventService implements IEventService {

    @Autowired
    private DAOEvent repo;

    private IAcademicPeriodService apService;

    @Autowired
    private ITeacherService teacherServ;

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> create(Event event) {

        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList();
        Event ev = repo.findById(event.getId()).orElse(null);
        event.setCode(event.getCode().toUpperCase());
        if (ev != null || repo.findbyCode(event.getCode()) != null) {
            errors.add(EvtErrors.EVT102.name());
        } else {
            Teacher teacher = teacherServ.find(event.getTeacher());
            if (teacher == null) {
                errors.add(TeacherErrors.TCH101.name());
            } else {
                Map<Labels, Object> current = apService.getCurrent();
                AcademicPeriod get = (AcademicPeriod) current.get(Labels.objectReturn);

                if (get.getAcademicPeriodID() != event.getAp().getAcademicPeriodID()) {
                    errors.add(EvtErrors.EVT107.name());
                } else {
                    event = repo.save(event);
                }
            }
        }
        returns.put(Labels.objectReturn, event);
        returns.put(Labels.errors, errors);
        return returns;
    }

    @Override
    public Map<Labels, Object> delete(long eventId, long departmentId) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList();

        Event ev = repo.findById(eventId).orElse(null);
        if (ev == null) {
            errors.add(EvtErrors.EVT101.name());
            ev = null;
        } else {
            repo.delete(ev);
        }

        returns.put(Labels.objectReturn, ev);
        returns.put(Labels.errors, errors);
        return returns;

    }

    @Override
    public Map<Labels, Object> update(Event event) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList();

        Event ev = repo.findById(event.getId()).orElse(null);

        if (ev == null) {
            errors.add(EvtErrors.EVT101.name());
        }else {
            ev = repo.findbyCode(event.getCode());
            if (ev != null && ev.getId() != event.getId()) {
                errors.add(EvtErrors.EVT102.name());
                ev = null;
            } else {
                Map<Labels, Object> current = apService.getCurrent();
                AcademicPeriod get = (AcademicPeriod) current.get(Labels.objectReturn);

                if (get.getAcademicPeriodID() != event.getAp().getAcademicPeriodID()) {
                    errors.add(EvtErrors.EVT107.name());
                } else {
                    ev = repo.save(event);
                }
            }
        }

        returns.put(Labels.objectReturn, ev);
        returns.put(Labels.errors, errors);
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public Map<Labels, Object> findbyId(long eventId) {
        Map<Labels, Object> returns = new HashMap();
        Event ev = repo.findById(eventId).orElse(null);
        returns.put(Labels.objectReturn, ev);
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public Map<Labels, Object> findbyCode(String code) {
        Map<Labels, Object> returns = new HashMap();
        Event ev = repo.findbyCode(code);
        returns.put(Labels.objectReturn, ev);
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public Map<Labels, Object> findByDeparmentId(List<Long> departmentId) {
        Map<Labels, Object> returns = new HashMap();
        List<Event> ev  = repo.findByDeparmentId(departmentId);
        returns.put(Labels.objectReturn, ev);
        return returns;
    }

    @Override
    public Map<Labels, Object> findByDepartmentIdAndEventId(long dpo, long eve) {
        Map<Labels, Object> returns = new HashMap();
        Event ev  = repo.findByDepartmentidAndId(dpo, eve);
        returns.put(Labels.objectReturn, ev);
        return returns;
    }

}
