/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.server.Model.Access;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import server.server.Model.Domain.Program;

/**
 *
 * @author Fernando
 */
public interface DAOProgram extends JpaRepository<Program, Long> {

    public Program findByCode(String code);

    @Query(
            value
            = """
            WITH consulta as (
                  SELECT P.IDPROGRAM FROM `program` P
                WHERE P.DEPARTMENTID =:departmentId 

            )SELECT * FROM `program` P2 
            WHERE P2.IDPROGRAM IN (SELECT DISTINCT IDPROGRAM FROM consulta);
            """,
            nativeQuery = true
    )
    public List<Program> findAllByDepartmentId(@Param("departmentId") long departmentId);

}
