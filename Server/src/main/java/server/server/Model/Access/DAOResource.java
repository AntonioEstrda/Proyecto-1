/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.server.Model.Access;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import server.server.Model.Domain.Resource;

/**
 * Resource repository
 * @author anmon
 */
@Repository
public interface DAOResource extends JpaRepository<Resource, Long>{
    
 
    public Resource findByCodeAndNumber(String code, Integer number); 

    @Query(
            value="""
                  SELECT * FROM faculty_resource 
                  WHERE RESOURCEID =:RESOURCEID and FACULTYID= :FACULTYID;
                  """, 
            nativeQuery = true
    )
    public boolean findAssFaculty(@Param("RESOURCEID")long RESOURCEID, @Param("FACULTYID")long FACULTYID);
       
}
