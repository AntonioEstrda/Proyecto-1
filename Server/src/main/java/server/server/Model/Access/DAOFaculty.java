/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Access;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.server.Model.Domain.Faculty;

/**
 * Faculty repository
 * @author anmon
 */
@Repository
public interface DAOFaculty extends JpaRepository<Faculty, Long>{
    
}
