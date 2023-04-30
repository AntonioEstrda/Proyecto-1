/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Access;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import server.server.Model.Domain.Schedule;

/**
 *
 * @author anmon
 */
@Repository
public interface DAOSchedule extends JpaRepository<Schedule, Long> {

    @Query(
            value = """
                    SELECT validateScheduleAssignmentOnUpd(:envId, :startime, :endtime , :days, :schId) FROM DUAL;  
                    """,
            nativeQuery = true
    )
    public int validateScheduleAssignment(@Param("envId") Long envId, @Param("startime") LocalTime startime,
            @Param("endtime") LocalTime endtime, @Param("days") String days,
            @Param("schId") Long schId);

    @Query(
            value = """
                    SELECT existsAssigmentOnUpd(:vday, :groupId, :schId) FROM DUAL;  
                    """,
            nativeQuery = true
    )
    public int existsAssigmentOnUpd(@Param("vday") String vday, @Param("groupId") Long groupId,
            @Param("schId") Long schId);

    @Query(
            value = """
                    SELECT CrossSubjectCOnUpd(:vday, :groupId, :startime, :endtime, :schId) FROM DUAL;  
                    """,
            nativeQuery = true
    )
    public int CrossSubjectCOnUpd(@Param("vday") String vday, @Param("groupId") Long groupId,
            @Param("startime") LocalTime startime, @Param("endtime") LocalTime endtime,
            @Param("schId") Long schId);

    @Query(
            value = """
                    SELECT validateHourAssignment(:groupId, :crrtime, :uptTime, :call_to_action) FROM DUAL;  
                    """,
            nativeQuery = true
    )
    public int validateHourAssignment(@Param("groupId") Long groupId, @Param("crrtime") long crrtime, @Param("uptTime") long uptTime,
            @Param("call_to_action") String call);

    @Query(
            value = """
                    SELECT validateAssOverFaculty(:envId, :groupId) FROM DUAL;  
                    """,
            nativeQuery = true
    )
    public int validateAssOverFaculty(@Param("envId") long envId, @Param("groupId") long groupId);

    @Query(
            value = """
                    WITH academicSchedule as (
                    	SELECT DISTINCT S.IDSCHEDEULE FROM `schedule` S 
                            INNER JOIN groupt G ON S.IDGROUP = G.IDGROUP
                            INNER JOIN subject SB ON G.IDSUBJECT = SB.IDSUBJECT
                            INNER JOIN program PG ON  SB.IDPROGRAM = PG.IDPROGRAM 
                        WHERE G.ACADEMICPERIDODID = getCrrntAcdPer() 
                            AND S.TYPE = 'ACADEMICO'
                        	AND PG.IDPROGRAM=:programId
                        	AND SB.SEMESTER=:semester 
                    ), prestamoMateria as (
                    	SELECT DISTINCT S.IDSCHEDEULE FROM `schedule` S 
                            INNER JOIN  `event` E ON S.eventId = E.id 
                            INNER JOIN  groupt G ON  S.IDGROUP = G.IDGROUP 
                            INNER JOIN subject SB ON G.IDSUBJECT = SB.IDSUBJECT
                            INNER JOIN program PG ON  SB.IDPROGRAM = PG.IDPROGRAM 
                        WHERE G.ACADEMICPERIDODID = getCrrntAcdPer() 
                            AND S.TYPE = 'EVENTO'
                        	AND E.type = 'PRESTAMO_POR_MATERIA'
                        	AND PG.IDPROGRAM=:programId
                        	AND SB.SEMESTER=:semester 
                    ), schedules as(
                    	SELECT * FROM  academicSchedule
                    		UNION    
                        SELECT * FROM  prestamoMateria
                    )SELECT * FROM `schedule` WHERE IDSCHEDEULE in (SELECT IDSCHEDEULE FROM  schedules);
                    """,
            nativeQuery = true
    )
    public List<Schedule> findByProgramIdAndSemester(@Param("programId") long programId, @Param("semester") long semester);

    @Query(
            value = """
                  WITH EventSchedule as ( 
                      SELECT S.IDSCHEDEULE FROM  `schedule` S 
                          INNER JOIN  `event` E ON S.eventId = E.id
                      WHERE E.academicPeriodId = getCrrntAcdPer() 
                       AND E.departmentId =:departmentId
                       AND E.type in (:types)
                  )SELECT * FROM `schedule` S  
                  WHERE S.IDSCHEDEULE in (SELECT DISTINCT IDSCHEDEULE FROM EventSchedule);
                  """,
            nativeQuery = true
    )
    public List<Schedule> findByTypesAndEventsAndDepartment(@Param("departmentId") long departmentId, @Param("types") List<String> types);

    @Query(
            value = "SELECT permitEvents(:id) from dual;",
            nativeQuery = true
    )
    public int permitEvents(@Param("id") long departmentId);

    @Query(
            value = "SELECT validateAssignmentEnvDpoGro(:departmentId, :groupId, :envId) from dual;",
            nativeQuery = true
    )
    public int validateAssignmentEnvProGro(@Param("departmentId") Long departmentId, @Param("groupId") Long groupId, @Param("envId") Long envId);

    @Query(
            value = "SELECT existAssigmentScheudleDpto(:schId,:departmentId) from dual;",
            nativeQuery = true
    )
    public int existAssigmentScheudleDpto(@Param("departmentId") Long departmentId, @Param("schId") Long schId);

    @Query(
            value = """
                    WITH academicSchedule as (
                        SELECT DISTINCT S.IDSCHEDEULE FROM `schedule` S 
                        INNER JOIN groupt G ON S.IDGROUP = G.IDGROUP
                        INNER JOIN subject SB ON G.IDSUBJECT = SB.IDSUBJECT
                        INNER JOIN program PG ON  SB.IDPROGRAM = PG.IDPROGRAM 
                        WHERE G.ACADEMICPERIDODID = getCrrntAcdPer() 
                        AND S.TYPE = 'ACADEMICO'
                        AND S.resourceId =:envId 
                    ), prestamoMateria as (
                        SELECT DISTINCT S.IDSCHEDEULE FROM `schedule` S 
                        INNER JOIN  `event` E ON S.eventId = E.id 
                        INNER JOIN  groupt G ON  S.IDGROUP = G.IDGROUP 
                        INNER JOIN subject SB ON G.IDSUBJECT = SB.IDSUBJECT
                        INNER JOIN program PG ON  SB.IDPROGRAM = PG.IDPROGRAM 
                        WHERE G.ACADEMICPERIDODID = getCrrntAcdPer() 
                        AND S.TYPE = 'EVENTO'
                        AND E.type = 'PRESTAMO_POR_MATERIA'
                        AND S.resourceId =:envId 
                    ), schedules as(
                        SELECT * FROM  academicSchedule
                        UNION    
                        SELECT * FROM  prestamoMateria
                    )
                    SELECT * FROM `schedule` WHERE IDSCHEDEULE in (SELECT IDSCHEDEULE FROM  schedules);
                    """,
            nativeQuery = true
    )
    public ArrayList<Schedule> findByEnvId(@Param("envId") long envId);

}
