/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Access;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.server.Model.Domain.Event;

/**
 *
 * @author anmon
 */
@Repository
public interface DAOEvent extends JpaRepository<Event, Long> {

    public Event findbyCode(String code);
}
