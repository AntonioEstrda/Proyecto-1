/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.server.Model.Access;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.server.Model.Domain.FacultyEnviroment;

/**
 * Repository for faculty environment intermediate entity 
 * @author anmon
 */
@Repository
public interface DAOFacultyEnvironment extends JpaRepository<FacultyEnviroment, Long>{
    
}
