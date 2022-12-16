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
 * @author Fernando
 */
@Entity
@Table(name="groupt")
@Data
public class Group implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IDGROUP")
    private long groupId; 
    
    @NotEmpty(message = "GROUP103")
    @Column(length=100, name="NAME")
    @Pattern(regexp = "^[a-zA-Z0-9]{1,2}", message="GROUP107")
    private String name;
    
    @NotNull(message = "GROUP104")
    @Column(name="CAPACITY")
    private long capacity;
    
    @NotNull(message = "GROUP105")
    @ManyToOne
    @JoinColumn(name="IDSUBJECT")
    private Subject subject;
    
    
    @NotNull(message = "GROUP106")
    @ManyToOne
    @JoinColumn(name="ACADEMICPERIDODID")
    private AcademicPeriod academicPeriod;
    
}
