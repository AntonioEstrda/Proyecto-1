/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Access;

import java.time.LocalTime;
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
                    SELECT validateHourAssignment(:groupId, :crrtime, :uptTime) FROM DUAL;  
                    """,
            nativeQuery = true
    )
    public int validateHourAssignment(@Param("groupId") Long groupId, @Param("crrtime") long crrtime, @Param("uptTime") long uptTime);

    
    
    @Query(
            value = """
                    SELECT validateAssOverFaculty(:envId, :groupId) FROM DUAL;  
                    """,
            nativeQuery = true
    )
    public int validateAssOverFaculty(@Param("envId") long envId, @Param("groupId") long groupId); 
    
    
    @Query(
            value = """
                    WITH consulta as ( 
                    	SELECT S.IDSCHEDEULE as Id FROM `schedule` S 
                            INNER JOIN groupt G ON S.IDGROUP = G.IDGROUP
                            INNER JOIN subject SB ON G.IDSUBJECT = SB.IDSUBJECT 
                            INNER JOIN program PG ON PG.IDPROGRAM = SB.IDPROGRAM
                        WHERE G.ACADEMICPERIDODID = getCrrntAcdPer() 
                    	AND PG.IDPROGRAM=:programId
                        AND SB.SEMESTER=:semester
                    ) SELECT * FROM `schedule` S WHERE S.IDSCHEDEULE in (SELECT distinct id FROM consulta);;  
                    """,
            nativeQuery = true
    )
    public List<Schedule> findByProgramIdAndSemester(@Param("programId") long programId, @Param("semester") long semester);
      
}
