/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Access;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import server.server.Model.Domain.Rol;

/**
 *
 * @author anmon
 */
@Repository
public interface DAORol extends JpaRepository<Rol, Long> {

    @Query(
            value = """
                    WITH Consulta as (
                    	SELECT RL.id FROM rol RL 
                        INNER JOIN userrol UR ON RL.id = UR.id
                        WHERE UR.userId =:userIdEx
                    )SELECT * FROM  rol where id in ( SELECT DISTINCT Consulta.id FROM Consulta);
                    """,
            nativeQuery = true
    )
    public Set<Rol> findByUserId(@Param("userIdEx") Long userId);
}
