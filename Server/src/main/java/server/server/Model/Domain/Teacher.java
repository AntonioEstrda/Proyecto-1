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
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name="teacher")
@Data
public class Teacher implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TEACHERID")
    private long teacherID; 
    
    @NotEmpty
    @Column(length=100, name="FISRTSNAME")
    private String firstName;
    
    @NotEmpty
    @Column(length=100, name="LASTNAME")
    private String lastName;
    
    @NotEmpty
    @Column(length=100, name="NUMIDEN")
    private String numIden;
    
    @NotNull
    @Column(nullable = false, columnDefinition = "TINYINT(1)", name="ISDISABLE")
    private String isDisable;
       
}
