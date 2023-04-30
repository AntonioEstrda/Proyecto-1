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
 * Faculty Resource Intermediate Entity 
 * @author anmon
 */
@Entity
@Table(name="faculty_resource")
@Data
public class FacultyResource implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="FAC_AND_RES_ID")
    private long facResId;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name="REGISTERDATE", updatable=false)
    private Date registrerDate;  
    
    
    @Temporal(TemporalType.DATE)
    @Column(name="FINALDATE")
    private Date finalDate;  
   
    @NotNull 
    @Column(name="isDisable")
    private boolean isDisable; 
    
    @ManyToOne
    @JoinColumn(name="RESOURCEID")
    private Resource resourceFR; 
    
    @ManyToOne
    @JoinColumn(name="FacultyId")
    private Faculty facultyFR; 
    
}
