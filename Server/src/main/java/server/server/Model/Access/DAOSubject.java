/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.server.Model.Access;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import server.server.Model.Domain.Subject;

/**
 *
 * @author Fernando
 */
public interface DAOSubject extends JpaRepository<Subject, Long>{
    
   @Query(
          value= """
                   SELECT DISTINCT DC.DEPARTMENTID FROM  overviewintensityassignedhours DC
                   WHERE DC.IDSUBJECT =:SubjectID  
                 """, 
           nativeQuery=true
   )
   public Long findDepartmentAss(@Param("SubjectID") long subjectId); 
}
