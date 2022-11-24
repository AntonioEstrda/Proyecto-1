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
@Table(name="department")
@Data
public class Department implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="DEPARTMENTID")
    private long departmentId; 
    
    @NotNull
    @Column(length=100, name="NAME")
    private String name;
        
    @NotEmpty 
    @Column(length=25, name="code")
    private String code; 
    
    @NotEmpty 
    @Column(length=100, name="location")
    private String location; 
    
    @NotNull
    @ManyToOne
    @JoinColumn(name="FACULTYID")
    private Faculty facultad;
    
}
