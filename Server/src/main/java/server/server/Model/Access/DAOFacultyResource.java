/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.server.Model.Access;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import server.server.Model.Domain.FacultyResource;

/**
 * Repository for Faculty Resource intermediate entity 
 * @author anmon
 */
@Repository
public interface DAOFacultyResource extends JpaRepository<FacultyResource, Long>{
    
    
    @Query(
        value = "SELECT * FROM faculty_resource fr where fr.RESOURCEID=:resourceId AND fr.FACULTYID=:facultyId"
                + " AND fr.isDisable=0" , 
        nativeQuery=true
    )
    public FacultyResource findByFacultyIdResourceId(@Param("facultyId") long facultyId, 
                                                    @Param("resourceId") long resourceId); 
    
    @Query(
        value = "SELECT * FROM faculty_resource fr where fr.RESOURCEID=:resourceId"
                + " AND fr.isDisable=0" , 
        nativeQuery=true
    )
    public List<FacultyResource> findByResourceId(@Param("resourceId") long resourceId); 
    
    
    @Query(
        value = "SELECT * FROM faculty_resource fr where fr.FACULTYID=:facultyId"
                + " AND fr.isDisable=0" , 
        nativeQuery=true
    )
    public List<FacultyResource> findByFacultyId(@Param("facultyId")long facultyId);
    
    
    
    @Query(
        value = """
                SELECT R.RESOURCEID FROM resourcet R
                INNER JOIN faculty_resource FR ON  FR.RESOURCEID = R.RESOURCEID 
                INNER JOIN resourcetype  RT ON  RT.RESSOURCETYPEID = R.RESOURCETYPEID
                WHERE FR.isDisable = 0 
                AND RT.RESSOURCETYPEID in :tipos
                AND FR.FACULTYID=:facultyId
                """, 
        nativeQuery=true
    )
    public List<Long> findByFacultyId(@Param("facultyId") long facultyId, @Param("tipos")List<Long> tipos);
    
}
