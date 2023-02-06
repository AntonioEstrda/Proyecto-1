/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Access;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import server.server.Model.Domain.User;

/**
 *
 * @author anmon
 */
@Repository
public interface DAOUser extends JpaRepository<User, Long> {

    public Optional<User> findByUsername(String username);

    public boolean existsByUsername(String username);

    @Query(
            value = """
                    SELECT VC.DEPARTMENTID FROM  `user` US  
                    	INNER JOIN teacher TI ON US.TeacherId = TI.TEACHERID 
                    	INNER JOIN vinculations VC ON VC.TEACHERID = TI.TEACHERID
                    WHERE US.username =:usernameEx;
                    """,
            nativeQuery = true
    )
    public Long findByDepartmentId(@Param("usernameEx") String username);

}
