/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "schedule")
@Data
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotEmpty()
    @Column(length = 100, name = "Name")
    private String name;

    @NotEmpty()
    @Column(length = 100, name = "Description")
    private String description;

    @NotEmpty()
    @Column(length = 10, name = "Code")
    @Pattern(regexp = "^[a-zA-Z0-9]{3,10}")
    private String code;

    @NotNull()
    @ManyToOne
    @JoinColumn(name = "teacherId")
    private Teacher teacher;

}
