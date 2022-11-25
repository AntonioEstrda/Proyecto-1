/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name="hourlyassignment")
@Data
public class HourlyAssignment implements Serializable {
    public enum VinculationType {
        NOMBRADOTIEMPOCOMPLETO, NOMBRADOMEDIOTIEMPO,OCASIONALTIEMPOCOMPLETO,OCASIONALMEDIOTIEMPO,HORACÁTEDRA,BECARIO; 
    }
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="vinculationId")
    private long vinculationId; 
       
    @NotEmpty 
    @Column(name="HOURS")
    private long hours; 
    
    @NotNull(message = "108")
    @Enumerated(EnumType.STRING)
    @Column(name="type")
    private VinculationType vinculationtype; 
    
    @NotEmpty
    @Column(length=100, name="initialdate")
    private String initialDate;
    
    @NotEmpty
    @Column(length=100, name="finaldate")
    private String finaldate;
        
    @NotNull
    @ManyToOne
    @JoinColumn(name="DEPARTMENTID")
    private Department department;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name="TEACHERID")
    private Teacher teacher;
    
}
