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
import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 *
 * @author anmon
 */
@Entity
@Table(name = "event")
@Data
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotEmpty(message = "EVT103")
    @Column(length = 100, name = "Name")
    private String name;

    @NotEmpty(message = "EVT104")
    @Column(length = 100, name = "Description")
    private String description;

    @NotEmpty(message = "EVT105")
    @Column(length = 10, name = "Code")
    @Pattern(regexp = "^[A-Z0-9]{3,10}$", message = "EVT105")
    private String code;
    
    @NotNull(message = "EVT109")
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EventType type;  
    
    @NotNull(message = "EVT106")
    @ManyToOne
    @JoinColumn(name = "teacherId")
    private Teacher teacher;

    @NotNull(message = "EVT107")
    @ManyToOne
    @JoinColumn(name = "academicPeriodId")
    private AcademicPeriod ap;
    
    @NotNull(message = "EVT108")
    @ManyToOne
    @JoinColumn(name = "departmentId")
    private Department department;
    
    public enum EventType{
        PRESTAMO_ACADEMICO,GENERAL,PRESTAMO_POR_MATERIA,ACADEMICO; 
    }
}
