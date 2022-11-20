/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.server.Model.Access;

import org.springframework.data.jpa.repository.JpaRepository;
import server.server.Model.Domain.Teacher;

/**
 *
 * @author Fernando
 */
public interface DAOTeacher extends JpaRepository<Teacher, Long>{
   
}