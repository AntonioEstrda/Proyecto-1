/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Domain;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author anmon
 */
@Entity
@Table(name = "schedule")
@Data
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public enum scheduleType {
        ACADEMICO, EVENTO;
    }

    public enum Days {
        LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDSCHEDEULE ")
    private long id;

    @NotNull()
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private scheduleType type;

    @NotNull()
    @Enumerated(EnumType.STRING)
    @Column(name = "DAYS")   
    private Days days;

    @NotNull()
    @Column(name = "STARTIME")
    private LocalTime startime;

    @NotNull()
    @Column(name = "ENDTIME")
    private LocalTime endtime;

    @NotNull()
    @Temporal(TemporalType.DATE)
    @Column(name = "initialdate")
    private Date initialdate;

    @NotNull()
    @Temporal(TemporalType.DATE)
    @Column(name = "finaldate")
    private Date finaldate;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "resourceId")
    private Resource res;

    @ManyToOne()
    @JoinColumn(name = "eventId")
    private Event event;

    @ManyToOne()
    @JoinColumn(name = "IDGROUP")
    private Group group;

}
