/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.server.Model.Access;

import org.springframework.data.jpa.repository.JpaRepository;
import server.server.Model.Domain.HourlyAssignment;

/**
 *
 * @author Fernando
 */
public interface DAOHourlyAssignment extends JpaRepository<HourlyAssignment, Long>{
    
}
