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
        NOMBRADOTIEMPOCOMPLETO, NOMBRADOMEDIOTIEMPO,OCASIONALTIEMPOCOMPLETO,OCASIONALMEDIOTIEMPO,HORACATEDRA,BECARIO; 
        /*
        NOMBRADOTIEMPOCOMPLETO("Nombrado tiempo completo"),
        NOMBRADOMEDIOTIEMPO("Nombrado medio tiempo"),
        OCASIONALTIEMPOCOMPLETO("Ocasional tiempo completo"),
        OCASIONALMEDIOTIEMPO("Ocasional medio tiempo"),
        HORACÁTEDRA("Hora cátedra"),BECARIO("Becario");
        
        private String name;

        VinculationType(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        } */
    }
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="vinculationId")
    private long vinculationId; 
       
    @NotEmpty(message = "HA105")
    @Column(name="HOURS")
    private long hours; 
    
    @NotEmpty(message = "HA106")
    @Enumerated(EnumType.STRING)
    @Column(name="VINCULATIONTYPE")
    private VinculationType vinculationtype; 
    
    @NotNull(message = "HA107")
    @Column(length=100, name="initialdate")
    private String initialDate;
    
    @NotNull(message = "HA108")
    @Column(length=100, name="finaldate")
    private String finaldate;
    
    @NotNull(message = "HA109")
    @Column(columnDefinition = "TINYINT(1)", name="ISDISABLE")
    private boolean isDisable;
        
    @NotNull(message = "HA103")
    @ManyToOne
    @JoinColumn(name="DEPARTMENTID")
    private Department department;
    
    @NotNull(message = "HA104")
    @ManyToOne
    @JoinColumn(name="TEACHERID")
    private Teacher teacher;
    
}
