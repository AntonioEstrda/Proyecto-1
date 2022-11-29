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
    
    @NotEmpty
    @Column(length=100, name="NAME")
    private String name;
    
    @NotEmpty 
    @Column(name="CAPACITY")
    private long capacity; 
    
    @NotNull
    @ManyToOne
    @JoinColumn(name="IDSUBJECT")
    private Subject subject;
    
    
    @NotNull
    @ManyToOne
    @JoinColumn(name="ACADEMICPERIDODID")
    private AcademicPeriod academicPeriod;
    
}
