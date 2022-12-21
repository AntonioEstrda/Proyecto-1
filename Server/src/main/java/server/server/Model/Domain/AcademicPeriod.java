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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name="academicperiod")
@Data
public class AcademicPeriod implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ACADEMICPERIDODID")
    private long academicPeriodID; 
    
    @NotEmpty(message = "AP103")
    @Column(length=100, name="NAME")
    private String name;
    
    @NotNull(message = "AP104")
    @Temporal(TemporalType.DATE)
    @Column(length=100, name="INITDATE")
    private Date initDate;
    
    @NotNull(message = "AP105")
    @Temporal(TemporalType.DATE)
    @Column(length=100, name="FINALDATE")
    private Date finalDate;
        
}
