/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
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
import javax.validation.constraints.NotNull;
import lombok.Data;
import server.server.Controller.config.CustomDateDeserializer;
import server.server.Controller.config.CustomDateSerializer;

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
    @Column(name = "IDSCHEDEULE")
    private long id;

    @NotNull()
    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private scheduleType type;

    @NotNull()
    @Column(name = "DAYS")
    @Enumerated(EnumType.STRING)
    private Days days;

    @NotNull()
    @Column(name = "STARTIME")
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonSerialize(using = LocalTimeSerializer.class)
    private LocalTime startime;

    @NotNull()
    @Column(name = "ENDTIME")
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonSerialize(using = LocalTimeSerializer.class)
    private LocalTime endtime;

    @NotNull()
    @Column(name = "initialdate")
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date initialdate;

    @NotNull()
    @Column(name = "finaldate")
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
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
