/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Access;

import org.springframework.data.jpa.repository.JpaRepository;
import server.server.Model.Domain.Department;

/**
 *
 * @author Fernando
 */
public interface DAODepartment extends JpaRepository<Department, Long>{
    
    public Department findByCode(String code); 
   
}
