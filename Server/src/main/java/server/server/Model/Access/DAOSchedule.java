/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Access;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.server.Model.Domain.Schedule;

/**
 *
 * @author anmon
 */
@Repository
public interface DAOSchedule extends JpaRepository<Long, Schedule>  {
    
}
