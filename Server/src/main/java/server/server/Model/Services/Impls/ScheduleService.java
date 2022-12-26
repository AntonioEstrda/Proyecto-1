/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Services.Impls;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import server.server.Model.Domain.AcademicPeriod;
import server.server.Model.Domain.Resource;
import server.server.Model.Domain.Schedule;
import server.server.Model.Domain.Subject;
import server.server.Model.Services.IAcademicPeriodService;
import server.server.Model.Services.IGroupService;
import server.server.Model.Services.IResourceService;
import server.server.Model.Services.IResourceTypeService;
import server.server.Model.Services.IScheduleService;
import server.server.utilities.Labels;
import server.server.utilities.errors.EnvErrors;
import server.server.utilities.errors.ResErrors;
import server.server.utilities.errors.ScheduleErrors;

/**
 *
 * @author anmon
 */
@Service
@EnableTransactionManagement
public class ScheduleService implements IScheduleService {

    @Autowired
    private IAcademicPeriodService acpeService;

    @Autowired
    private IGroupService groupService;

    @Autowired
    private IResourceService resourceService;

    @Autowired
    private IResourceTypeService resTypeServ;

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public Map<Labels, Object> find(Schedule schedule) {
        Map<Labels, Object> returns = new HashMap();

        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> add(Schedule schedule) {
        Map<Labels, Object> returns = new HashMap();
        if (schedule.getType() == Schedule.scheduleType.ACADEMICO) {
            returns = validateAcademicSchedule(schedule);
        } else if (schedule.getType() == Schedule.scheduleType.EVENTO) {
            returns = validateEventSchedule(schedule);
        }
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> update(Schedule schedule) {
        Map<Labels, Object> returns = new HashMap();

        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> delete(long id) {
        Map<Labels, Object> returns = new HashMap();

        return returns;
    }

    private Map<Labels, Object> validateAcademicSchedule(Schedule schedule) {
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList();

        if (schedule.getEvent() != null) {
            errors.add(ScheduleErrors.SCH107.name());
        }

        if (errors.isEmpty() && schedule.getGroup() != null
                && groupService.findById(schedule.getGroup().getGroupId()) == null) {
            errors.add(ScheduleErrors.SCH111.name());
        }

        if (errors.isEmpty()) {

        }

        if (errors.isEmpty()) {
            errors.addAll(validateDates(schedule.getInitialdate(), schedule.getFinaldate()));
        }
        if (errors.isEmpty()) {
            Map<Labels, Object> current = acpeService.getCurrent();
            AcademicPeriod ap = (AcademicPeriod) current.get(Labels.objectReturn);
            if (!ap.getInitDate().equals(schedule.getInitialdate()) || !ap.getFinalDate().equals(schedule.getFinaldate())) {
                errors.add(ScheduleErrors.SCH107.name());
            }
        }
        if (errors.isEmpty()) {
            errors.addAll(validateTime(schedule.getStartime(), schedule.getEndtime()));
        }

        if (errors.isEmpty()) {
            errors.addAll(validateTimeAcademicSchedule(schedule));
        }

        return returns;
    }

    private Map<Labels, Object> validateEventSchedule(Schedule schedule) {
        Map<Labels, Object> returns = new HashMap();

        return returns;
    }

    private ArrayList<String> validateDates(Date initialdate, Date finaldate) {
        ArrayList<String> errors = new ArrayList();
        if (initialdate.after(finaldate)) {
            errors.add(ScheduleErrors.SCH103.name());
        }
        if (errors.isEmpty()) {
            Map<Labels, Object> current = acpeService.getCurrent();
            AcademicPeriod ap = (AcademicPeriod) current.get(Labels.objectReturn);
            if (initialdate.compareTo(ap.getInitDate()) < 0 || initialdate.compareTo(ap.getFinalDate()) > 0
                    || finaldate.compareTo(ap.getInitDate()) < 0 || finaldate.compareTo(ap.getFinalDate()) > 0) {
                errors.add(ScheduleErrors.SCH105.name());
            }
        }
        return errors;
    }

    private ArrayList<String> validateTime(LocalTime startime, LocalTime endtime) {
        ArrayList<String> errors = new ArrayList();
        LocalTime startimeallowed = LocalTime.parse("07:00:00");
        LocalTime endtimeallowed = LocalTime.parse("22:00:00");
        if (startime.isAfter(endtime) || startime.compareTo(endtime) == 0) {
            errors.add(ScheduleErrors.SCH104.name());
        }
        if (errors.isEmpty()) {
            if (startime.compareTo(startimeallowed) < 0 || startime.compareTo(endtimeallowed) >= 0
                    || endtime.compareTo(startimeallowed) <= 0 || endtime.compareTo(endtimeallowed) > 0) {
                errors.add(ScheduleErrors.SCH106.name());
            }
        }
        return errors;
    }

    private ArrayList<String> validateTimeAcademicSchedule(Schedule schedule) {
        ArrayList<String> errors = new ArrayList();

        LocalTime startime = schedule.getStartime();
        LocalTime endtime = schedule.getEndtime();
        if (startime.getMinute() != 0 || startime.getSecond() != 0
                || endtime.getMinute() != 0 || endtime.getSecond() != 0) {
            errors.add(ScheduleErrors.SCH109.name());
        }

        if (errors.isEmpty()) {
            long until = schedule.getStartime().until(schedule.getEndtime(), ChronoUnit.HOURS);
            Subject subject = schedule.getGroup().getSubject();
            if (subject.getType() == Subject.Type.FISH) {
                if (until != 2 || until != 4) {
                    errors.add(ScheduleErrors.SCH108.name());
                }
            } else {
                if (until != 2) {
                    errors.add(ScheduleErrors.SCH108.name());
                }
            }
        }
        return errors;
    }

    private ArrayList<String> validateAmbienteEnvironment(Schedule schedule) {
        ArrayList<String> errors = new ArrayList();
        Resource obj = resourceService.find(schedule.getRes());
        if (obj == null) {
            errors.add(EnvErrors.ENV101.name());
        }
        if (obj != null) {
            long globalType = resTypeServ.globalType(obj.getResourceType().getResourceTypeId());
            if (globalType != IResourceTypeService.ENVIRONMENTTYPE) {
                errors.add(ResErrors.RES112.name());
            }
        }
        if (errors.isEmpty()) {
            Map<String, ArrayList<Long>> requirements1 = new HashMap();
            for (Map.Entry<String, ArrayList<Long>> entry : requirements1.entrySet()) {
                System.out.println("Key = " + Long.parseLong(entry.getKey())
                        + ", Value = " + entry.getValue());
            }
        }
        return errors;
    }
}
