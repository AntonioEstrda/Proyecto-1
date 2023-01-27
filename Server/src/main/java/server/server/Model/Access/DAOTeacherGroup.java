/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.server.Model.Access;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import server.server.Model.Domain.TeacherGroup;

/**
 *
 * @author Fernando
 */
public interface DAOTeacherGroup extends JpaRepository<TeacherGroup, Long>{
    
    @Query(
            value = """
                    SELECT academicHoursTeacher (:teacherId) FROM DUAL; 
                    """,
            nativeQuery = true
    )
    public int academicHoursteacher(@Param("teacherId") long teacherId);
    
        
        @Query(
            value = """
                    SELECT hours FROM vinculations where TEACHERID= :teacherId
                    """,
            nativeQuery = true
    )
    public int hourVinculation(@Param("teacherId") long teacherId);
    
    
    @Query(
            value = """
                    UPDATE `teacher` SET `ISDISABLE`=1 WHERE TEACHERID = :teacherId
                    """,
            nativeQuery = true
    )
    public int disableTeacher(@Param("teacherId") long teacherId);
}
