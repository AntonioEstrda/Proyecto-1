/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Services.Impls;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import server.server.Model.Access.DAOSchedule;
import server.server.Model.Domain.AcademicPeriod;
import server.server.Model.Domain.Event;
import server.server.Model.Domain.Event.EventType;
import server.server.Model.Domain.Group;
import server.server.Model.Domain.Resource;
import server.server.Model.Domain.Schedule;
import server.server.Model.Domain.Schedule.scheduleType;
import server.server.Model.Domain.Subject;
import server.server.Model.Services.IAcademicPeriodService;
import server.server.Model.Services.IAssignmentResourceService;
import server.server.Model.Services.IEventService;
import server.server.Model.Services.IGroupService;
import server.server.Model.Services.IResourceService;
import server.server.Model.Services.IResourceTypeService;
import server.server.Model.Services.IScheduleService;
import server.server.Model.Services.utilities.jsonConversor;
import server.server.utilities.Labels;
import server.server.utilities.errors.EnvErrors;
import server.server.utilities.errors.EvtErrors;
import server.server.utilities.errors.GroupErrors;
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

    @Autowired
    private IAssignmentResourceService AsResServ;

    @Autowired
    private IEventService eventService;

    @Autowired
    private DAOSchedule repo;

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public Map<Labels, Object> find(Schedule schedule) {
        Map<Labels, Object> returns = new HashMap();
        Schedule findById = repo.findById(schedule.getId()).orElse(null);
        returns.put(Labels.objectReturn, findById);
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> add(Schedule schedule, Long departId) {
        Map<Labels, Object> returns = new HashMap();
        if (schedule.getType() == Schedule.scheduleType.ACADEMICO  ) {
            try {
                returns = validateAcademicScheduleOnAdd(schedule, departId);
            } catch (JsonProcessingException ex) {
                Logger.getLogger(ScheduleService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (schedule.getType() == Schedule.scheduleType.EVENTO) {
            try {
                returns = validateEventScheduleOnAdd(schedule, departId);
            } catch (JsonProcessingException ex) {
                Logger.getLogger(ScheduleService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> update(Schedule schedule, Long departId) {
        Map<Labels, Object> returns = new HashMap();
        int ban = repo.existAssigmentScheudleDpto(departId, schedule.getId()); 
        if (ban != 0){
            if (schedule.getType() == Schedule.scheduleType.ACADEMICO) {
                try {
                    returns = validateAcademicScheduleOnUpd(schedule, departId);
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(ScheduleService.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (schedule.getType() == Schedule.scheduleType.EVENTO) {
                try {
                    returns = validateEventScheduleOnUpd(schedule, departId);
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(ScheduleService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            ArrayList<String> errors = new ArrayList();
            errors.add(ScheduleErrors.SCH101.name());  
            returns.put(Labels.errors, errors);
            returns.put(Labels.objectReturn, schedule);
        }
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager")
    public Map<Labels, Object> delete(long id, Long departId) {
        Schedule schedule = null;  
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList();
        int ban = repo.existAssigmentScheudleDpto(departId, id); 
        if (ban == 0) {
            errors.add(ScheduleErrors.SCH101.name());
        }
        if (errors.isEmpty()) {
            schedule = repo.findById(id).orElse(null); 
            repo.delete(schedule);
        }
        returns.put(Labels.errors, errors);
        returns.put(Labels.objectReturn, schedule);
        return returns;
    }

    @Override
    @Transactional(value = "DataTransactionManager", readOnly = true)
    public Map<Labels, Object> findByProgSem(long prog, long sem) {
        Map<Labels, Object> returns = new HashMap();
        List<Schedule> schedule = repo.findByProgramIdAndSemester(prog, sem);
        returns.put(Labels.objectReturn, schedule);
        return returns;
    }

    private Map<Labels, Object> validateAcademicScheduleOnAdd(Schedule schedule, Long department) throws JsonProcessingException {
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList();
        Schedule orElse = repo.findById(schedule.getId()).orElse(null);
        if (orElse != null) {
            errors.add(ScheduleErrors.SCH102.name());
        }
        if(errors.isEmpty()){
            int aux = repo.validateAssignmentEnvProGro(department, schedule.getGroup().getGroupId(), schedule.getRes().getResourceId());
            if (aux == 0){
                errors.add(ScheduleErrors.SCH123.name());
            }
        }
        if (errors.isEmpty()) {
            errors.addAll(validateAcademicSchedule(schedule, null, department));
        }
        if (errors.isEmpty()) {
            schedule = repo.save(schedule);
        }
        returns.put(Labels.errors, errors);
        returns.put(Labels.objectReturn, schedule);
        return returns;
    }

    private Map<Labels, Object> validateAcademicScheduleOnUpd(Schedule schedule, Long department) throws JsonProcessingException {
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList();
        Schedule orElse = repo.findById(schedule.getId()).orElse(null);
        if (orElse == null) {
            errors.add(ScheduleErrors.SCH101.name());
        }
        if (errors.isEmpty()) {
            errors.addAll(validateAcademicSchedule(schedule, schedule.getId(), department));
        }
        if (errors.isEmpty()) {
            schedule = repo.save(schedule);
        }
        returns.put(Labels.errors, errors);
        returns.put(Labels.objectReturn, schedule);
        return returns;
    }

    private Map<Labels, Object> validateEventScheduleOnAdd(Schedule schedule, Long departmenId) throws JsonProcessingException {
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList();
        Schedule orElse = repo.findById(schedule.getId()).orElse(null);
        if (orElse != null) {
            errors.add(ScheduleErrors.SCH102.name());
        }
        if(errors.isEmpty()){
            Map<Labels, Object> aux1 = eventService.findByDepartmentIdAndEventId(departmenId, schedule.getEvent().getId());  
            Event aux2 = (Event) aux1.get(Labels.objectReturn);  
            if (aux2 == null){
                errors.add(ScheduleErrors.SCH124.name());
            }
        }
        
        if (errors.isEmpty()) {
            errors.addAll(this.validateEventSchedule(schedule, null, departmenId));
        }
        if (errors.isEmpty()) {
            schedule = repo.save(schedule);
        }
        returns.put(Labels.errors, errors);
        returns.put(Labels.objectReturn, schedule);
        return returns;
    }

    private Map<Labels, Object> validateEventScheduleOnUpd(Schedule schedule, Long departId) throws JsonProcessingException {
        Map<Labels, Object> returns = new HashMap();
        ArrayList<String> errors = new ArrayList();
        Schedule orElse = repo.findById(schedule.getId()).orElse(null);
        if (orElse == null) {
            errors.add(ScheduleErrors.SCH101.name());
        }
        if (errors.isEmpty()) {
            errors.addAll(this.validateEventSchedule(schedule, schedule.getId(), departId));
        }
        if (errors.isEmpty()) {
            schedule = repo.save(schedule);
        }
        returns.put(Labels.errors, errors);
        returns.put(Labels.objectReturn, schedule);
        return returns;
    }

    private ArrayList<String> validateAssignment(Schedule schedule, Long departmentId) {
        ArrayList<String> errors = new ArrayList();
        int ban;
        Long envId = schedule.getRes().getResourceId();
        Long groupId = null;
        if (schedule.getType() == scheduleType.ACADEMICO) {
            groupId = schedule.getGroup().getGroupId();
        }
        ban = repo.validateAssignmentEnvProGro(departmentId, groupId, envId);
        if (ban == 0) {
            errors.add(ScheduleErrors.SCH121.name());
        }
        return errors;
    }

    private ArrayList<String> validateAcademicSchedule(Schedule schedule, Long oldID, Long department) throws JsonProcessingException {
        ArrayList<String> errors = new ArrayList();
        if (schedule.getEvent() != null) {
            errors.add(ScheduleErrors.SCH107.name());
        }    
        if (errors.isEmpty()) {
            errors.addAll(this.validateGroupByAcademicSchd(schedule));
        }
        if (errors.isEmpty()) {
            errors.addAll(this.validateAcademicAssignmentDatetime(schedule));
        }
        if (errors.isEmpty()) {
            errors.addAll(this.validateEnvironment(schedule));
        }
        if (errors.isEmpty()) {
            errors.addAll(this.validateEnvironmentAcademicSchedule(schedule));
        }
        if (errors.isEmpty()) {
            errors.addAll(this.AcademicScheduleAssig(schedule, oldID));
        }
        if (errors.isEmpty()) {
            errors.addAll(this.validateAssignment(schedule, department));
        }
        return errors;
    }

    private ArrayList<String> validateEventSchedule(Schedule schedule, Long oldID, Long department) throws JsonProcessingException {
        ArrayList<String> errors = new ArrayList();
        if (schedule.getGroup() != null) {
            errors.addAll(this.validateGroupByEventSchd(schedule));
        }
        if (errors.isEmpty()) {
            int permitEvents = repo.permitEvents(department);
            if(permitEvents == 0){errors.add(ScheduleErrors.SCH122.name());}
        }
        if (errors.isEmpty()) {
            errors.addAll(this.validateEventAssignment(schedule));
        }
        if (errors.isEmpty()) {
            errors.addAll(this.eventScheduleAssig(schedule, oldID));
        }
        if (errors.isEmpty()) {
            errors.addAll(this.validateAssignment(schedule, department));
        }
        return errors;
    }

    private ArrayList<String> validateEventAssignment(Schedule schedule) throws JsonProcessingException {
        ArrayList<String> errors = new ArrayList();
        Event ev = schedule.getEvent();
        Map<Labels, Object> findbyId = eventService.findbyId(ev.getId());
        ev = (Event) findbyId.get(Labels.objectReturn);

        if (ev == null) {
            errors.add(EvtErrors.EVT101.name());
        }

        if (errors.isEmpty()) {
            errors.addAll(this.validateEnvironment(schedule));
        }

        if (errors.isEmpty() && ev != null) {
            if (ev.getType() == EventType.PRESTAMO_POR_MATERIA && schedule.getGroup() != null) {
                errors.addAll(this.validateTimeAcademicSchedule(schedule));
            } else {
                errors.addAll(this.validateEventAssignmentDatetime(schedule));
            }
            if (errors.isEmpty()) {
                errors.addAll(this.validateEnvironmentAcademicSchedule(schedule));
            }
        }

        return errors;
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

    private ArrayList<String> validateTimeEventSchedule(Schedule schedule) {
        ArrayList<String> errors = new ArrayList();
        Map<Labels, Object> findbyId = eventService.findbyId(schedule.getEvent().getId());
        schedule.setEvent((Event) findbyId.get(Labels.objectReturn));
        LocalTime startime = schedule.getStartime();
        LocalTime endtime = schedule.getEndtime();
        int minTime = 15;

        if (startime.getSecond() != 0 || startime.getMinute() % minTime != 0
                || endtime.getSecond() != 0 || endtime.getMinute() % minTime != 0) {
            errors.add(ScheduleErrors.SCH109.name());
        }
        return errors;
    }

    private ArrayList<String> validateTimeAcademicSchedule(Schedule schedule) {
        ArrayList<String> errors = new ArrayList();

        schedule.setGroup(groupService.findById(schedule.getGroup().getGroupId()));

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

    private ArrayList<String> validateEnvironmentAcademicSchedule(Schedule schedule) throws JsonProcessingException {
        ArrayList<String> errors = new ArrayList();
        if (errors.isEmpty()) {
            if (schedule.getGroup().getCapacity() > schedule.getRes().getCapacity()) {
                errors.add(ScheduleErrors.SCH114.name());
            }
        }
        if (errors.isEmpty()) {
            schedule.setRes(resourceService.findById(schedule.getRes().getResourceId()));
            Map<String, ArrayList<Long>> requirements1 = jsonConversor.getRequirements(schedule.getGroup().getSubject().getRequisits());
            long resTypeId = schedule.getRes().getResourceType().getResourceTypeId();
            Set<String> keySet = requirements1.keySet();
            if (!keySet.contains(String.valueOf(resTypeId))) {
                errors.add(ScheduleErrors.SCH113.name());
            } else {
                ArrayList<Long> get = requirements1.get(String.valueOf(resTypeId));
                if (get != null && !get.isEmpty()) {
                    int AssertAssignments = AsResServ.AssertAssignments(schedule.getRes().getResourceId(), get);
                    if (AssertAssignments < get.size()) {
                        errors.add(ScheduleErrors.SCH114.name());
                    }
                }
            }
        }
        return errors;
    }

    private ArrayList<String> validateEnvironment(Schedule schedule) throws JsonProcessingException {
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
        return errors;
    }

    private ArrayList<String> validateDateTime(Schedule schedule) {
        ArrayList<String> errors = new ArrayList();
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
        return errors;
    }

    private ArrayList<String> validateAcademicAssignmentDatetime(Schedule schedule) {
        ArrayList<String> errors = new ArrayList();
        errors.addAll(this.validateDateTime(schedule));
        if (errors.isEmpty()) {
            errors.addAll(validateTimeAcademicSchedule(schedule));
        }
        return errors;
    }

    private ArrayList<String> validateEventAssignmentDatetime(Schedule schedule) {
        ArrayList<String> errors = new ArrayList();
        errors.addAll(this.validateDateTime(schedule));
        if (errors.isEmpty()) {
            errors.addAll(this.validateTimeEventSchedule(schedule));
        }
        return errors;
    }

    private ArrayList<String> validateGroup(Schedule schedule) {
        ArrayList<String> errors = new ArrayList();
        Group findById;

        if (schedule.getGroup() == null) {
            errors.add(GroupErrors.GROUP101.name());
        }

        if (errors.isEmpty()) {
            findById = groupService.findById(schedule.getGroup().getGroupId());
            if (findById == null) {
                errors.add(ScheduleErrors.SCH111.name());
            } else if (findById.getSubject().isDisable()) {
                errors.add(ScheduleErrors.SCH119.name());
            }
        }

        if (errors.isEmpty()) {
            findById = groupService.findById(schedule.getGroup().getGroupId());
            Map<Labels, Object> current = acpeService.getCurrent();
            AcademicPeriod p = (AcademicPeriod) current.get(Labels.objectReturn);
            if (findById.getAcademicPeriod().getAcademicPeriodID() != p.getAcademicPeriodID()) {
                errors.add(ScheduleErrors.SCH112.name());
            }
        }

        return errors;
    }

    private ArrayList<String> validateGroupByAcademicSchd(Schedule schedule) {
        ArrayList<String> errors = new ArrayList();
        errors.addAll(this.validateGroup(schedule));
        if (errors.isEmpty()) {
            Group findById = groupService.findById(schedule.getGroup().getGroupId());
            if (findById.getSubject().isExtern()) {
                errors.add(ScheduleErrors.SCH120.name());
            }
        }

        return errors;
    }

    private ArrayList<String> validateGroupByEventSchd(Schedule schedule) {
        ArrayList<String> errors = new ArrayList();
        errors.addAll(this.validateGroup(schedule));
        if (errors.isEmpty()) {
            Group findById = groupService.findById(schedule.getGroup().getGroupId());
            if (!findById.getSubject().isExtern()) {
                errors.add(ScheduleErrors.SCH120.name());
            }
        }
        return errors;
    }

    private ArrayList<String> validateAcademicScheduleAssig(Schedule newSc, Long oldSc) {
        ArrayList<String> errors = new ArrayList<>();
        int ban;
        Long groupId = newSc.getGroup().getGroupId();
        if (errors.isEmpty()) {
            ban = repo.CrossSubjectCOnUpd(newSc.getDays().name(), groupId, newSc.getStartime(), newSc.getEndtime(), oldSc);
            if (ban == 1) {
                errors.add(ScheduleErrors.SCH116.name());
            }
        }

        if (errors.isEmpty()) {
            long currntm = 0;
            long updtm = newSc.getStartime().until(newSc.getEndtime(), ChronoUnit.HOURS);
            if (oldSc != null) {
                Schedule findById = repo.findById(oldSc).orElse(null);
                if (findById != null) {
                    currntm = findById.getStartime().until(findById.getEndtime(), ChronoUnit.HOURS);
                }
            }
            ban = repo.validateHourAssignment(groupId, currntm, updtm);
            if (ban == 1) {
                errors.add(ScheduleErrors.SCH115.name());
            }
        }

        return errors;
    }

    private ArrayList<String> validateAssig(Schedule newSc, Long oldSc) {
        ArrayList<String> errors = new ArrayList<>();
        int ban;
        if (errors.isEmpty()) {
            ban = repo.validateScheduleAssignment(newSc.getRes().getResourceId(), newSc.getStartime(), newSc.getEndtime(), newSc.getDays().name(), oldSc);
            if (ban == 1) {
                errors.add(ScheduleErrors.SCH117.name());
            }
        }
        return errors;
    }

    private ArrayList<String> AcademicScheduleAssig(Schedule newSc, Long oldSc) {
        ArrayList<String> errors = new ArrayList<>();
        Long groupId = newSc.getGroup().getGroupId();
        int ban = repo.existsAssigmentOnUpd(newSc.getDays().name(), groupId, oldSc);
        if (ban == 1) {
            errors.add(ScheduleErrors.SCH102.name());
        }
        if (errors.isEmpty()) {
            errors.addAll(this.validateAssig(newSc, oldSc));
        }
        if (errors.isEmpty()) {
            errors.addAll(this.validateAcademicScheduleAssig(newSc, oldSc));
        }
        if (errors.isEmpty()) {
            int validateAssOverFaculty = repo.validateAssOverFaculty(newSc.getRes().getResourceId(), newSc.getGroup().getGroupId());
            if (validateAssOverFaculty == 0) {
                errors.add(ScheduleErrors.SCH118.name());
            }
        }
        return errors;
    }

    private ArrayList<String> eventScheduleAssig(Schedule newSc, Long oldSc) {
        ArrayList<String> errors = new ArrayList<>();
        Long groupId = newSc.getGroup().getGroupId();
        if (errors.isEmpty()) {
            errors.addAll(this.validateAssig(newSc, oldSc));
        }
        if (errors.isEmpty() && newSc.getEvent().getType() == EventType.PRESTAMO_POR_MATERIA) {
            int ban = repo.existsAssigmentOnUpd(newSc.getDays().name(), groupId, oldSc);
            if (ban == 1) {
                errors.add(ScheduleErrors.SCH102.name());
            }
            if (errors.isEmpty()) {
                errors.addAll(this.validateAcademicScheduleAssig(newSc, oldSc));
            }
        }
        return errors;
    }

}
