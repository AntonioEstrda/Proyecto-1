/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Access;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import server.server.Model.Domain.Event;

/**
 * 
 * @author anmon
 */
@Repository
public interface DAOEvent extends JpaRepository<Event, Long> {

    @Query(
            value = """
                    SELECT * FROM `event` WHERE `Code`=:Code
                    """,
            nativeQuery = true
    )
    public Event findbyCode(@Param("Code") String code);
    
    @Query(
            value = """
                    WITH consulta as (
                        SELECT E.id FROM `event` E 
                            INNER JOIN program P ON  P.IDPROGRAM = E.programId 
                            INNER JOIN department D ON D.DEPARTMENTID = P.DEPARTMENTID 
                        WHERE E.academicPeriodId = getCrrntAcdPer() 
                     	       AND D.DEPARTMENTID in :departmentIds
                    )SELECT * FROM `event` E WHERE E.id in (SELECT DISTINCT id FROM consulta);
                    """,
            nativeQuery = true
    )
    public List<Event> findByDeparmentId(@Param("departmentIds") List<Long> departmentIds);

    @Query(
            value = """
                    WITH consulta as (
                        SELECT E.id FROM event E 
                            INNER JOIN program P ON  P.IDPROGRAM = E.programId 
                        WHERE E.academicPeriodId = getCrrntAcdPer() 
                     	       AND P.IDPROGRAM in :programIds
                    )SELECT * FROM `event` E WHERE E.id in (SELECT DISTINCT id FROM consulta);
                    """,
            nativeQuery = true
    )
    public List<Event> findByProgramId(@Param("programIds") List<Long> programIds);
    
    @Query (
            value = """
                    WITH consulta as( 
                        SELECT EV.id FROM event EV 
                        INNER JOIN department DP ON DP.DEPARTMENTID = EV.departmentId
                        WHERE DP.DEPARTMENTID = :dptId and EV.id = :evtId;  
                    )SELECT * FROM event EV where id = (SELECT id FROM consulta);  
                    """
            , nativeQuery = true
    )
    public Event findByDepartmentidAndId(@Param("dptId") long dptId, @Param("evtId") long evtId); 

    
    @Query(
            value="""
                  WITH evts as (
                        SELECT EV.id FROM `event` EV 
                               INNER JOIN department DP ON EV.departmentId = DP.DEPARTMENTID 
                        WHERE EV.academicPeriodId = getCrrntAcdPer() 
                        AND DP.DEPARTMENTID =:departmentId 
                        AND EV.type in (:types)
                  )SELECT * FROM `event` EVT WHERE EVT.id in (SELECT DISTINCT EV.id FROM evts EV) ;
                  """, nativeQuery = true
    )
    public List<Event> findAllByDepartmentAndEvenType(@Param("departmentId") long departmentId, 
                                                        @Param("types") List<String> types);
}
