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
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 *
 * @author anmon
 */
@Entity
@Table(name = "location")
@Data
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long LocationId;

    @NotEmpty(message = "LOC102")
    @Column(length = 100, name = "Name")
    private String name;
    
    @NotEmpty(message = "LOC103")
    @Column(length = 100, name = "city")
    private String city;

    @Column(name = "parentId")
    private Long parent;
}
