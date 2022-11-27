/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.server.Model.Access;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import server.server.Model.Domain.ResourceType;

/**
 * Repository for ResourceType
 * @author anmon
 */
@Repository 
public interface DAOResourceType extends JpaRepository<ResourceType, Long>{

    /**
     * Return globalType of a resource type
     * @param idType 
     * @return
     */
    @Query(
            value= "SELECT globalType(:idType) from dual",
            nativeQuery = true
    )
    public long findGlobalType(@Param("idType") long idType);
        
}
