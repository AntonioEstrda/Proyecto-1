/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.server.Model.Access;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import server.server.Model.Domain.Group;

/**
 *
 * @author Fernando
 */
public interface DAOGroup extends JpaRepository<Group, Long>{

    @Query(
            value = """
                    SELECT * FROM groupt GP  
                    WHERE GP.IDSUBJECT =:subjectId 
                    """, 
            nativeQuery = true 
    )
    public List<Group> findBySubjectId(@Param("subjectId") long subjectId);
}
