/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * Faculty - Environment Intermediate Entity
 * @author anmon
 */
@Entity
@Table(name="faculty_enviroment")
@Data
public class FacultyEnviroment implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="FAC_AND_ENV_ID")
    private long FacEnvId;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name="REGISTERDATE", updatable=false)
    private Date registrerDateFE;  
    
    
    @Temporal(TemporalType.DATE)
    @Column(name="FINALDATE")
    private Date finalDateFE;  
    
  
    @NotNull
    @ManyToOne
    @JoinColumn(name="ENVIROMENTID")
    private Environment environmentFR; 
    
    @NotNull
    @ManyToOne
    @JoinColumn(name="FACULTYID")
    private Faculty facultyFE; 
}
