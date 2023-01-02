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
import server.server.Model.Domain.AssignmentResource;

/**
 *
 * @author anmon
 */
@Repository
public interface DAOAssignmentResource extends JpaRepository<AssignmentResource, Long> {

    @Query(
            value = "SELECT * FROM assignmentresource WHERE envId=:envId AND isDisable=:isDisable",
            nativeQuery = true
    )
    public List<AssignmentResource> findAllByEnvIdAndIsDisable(@Param("envId") long envId,
            @Param("isDisable") boolean isDisable);

    @Query(
            value = "SELECT * FROM assignmentresource WHERE envId=:envId AND resId=:resId AND isDisable=:isDisable",
            nativeQuery = true
    )
    public AssignmentResource findByIsDisableAndEnvIdAndResId(@Param("isDisable") boolean isDisable, @Param("envId") long envId, @Param("resId") long resId);

    @Query(
            value = "SELECT * FROM assignmentresource WHERE resId=:resId AND isDisable=:isDisable",
            nativeQuery = true
    )
    public AssignmentResource findByIsDisableAndResId(@Param("isDisable") boolean isDisable, @Param("resId") long resId);
    
    
    @Query(
            value = """
                    WITH consulta as ( 
                        SELECT distinct envId, count(typeID) as foundcount FROM AssertAssignments
                            WHERE envId=:envId and typeID in (:types)    
                        GROUP BY envId
                    )SELECT foundcount FROM consulta;
                    """, 
            nativeQuery = true
    )
    public int CountAssertAssignments(@Param("envId") long envId, @Param("types") List<Long> types);

}
