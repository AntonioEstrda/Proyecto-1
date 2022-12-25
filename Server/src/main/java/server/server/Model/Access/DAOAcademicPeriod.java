/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.server.Model.Access;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import server.server.Model.Domain.AcademicPeriod;

/**
 *
 * @author Fernando
 */
public interface DAOAcademicPeriod extends JpaRepository<AcademicPeriod, Long>{
    
    @Query(
          value="""
                SELECT * FROM academicperiod WHERE ACADEMICPERIDODID=getCrrntAcdPer();
                """,
          nativeQuery=true
    )
    public AcademicPeriod findCurrent();
   
}
