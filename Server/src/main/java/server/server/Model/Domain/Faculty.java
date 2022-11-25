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
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Class Faculty mapped to Faculty Entity 
 * @author anmon
 */

@Entity
@Table(name = "faculty")
@Data
public class Faculty implements Serializable {
    
    private static final long serialVersionUID = 1L; 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="FACULTYID")
    private long facultyId; 
    
    @NotEmpty(message = "FAC103")
    @Size(min = 5, max = 100, message = "FAC105")
    @Column(length=100, name="NAME", nullable = false)
    private String facultyName;
   
    @NotEmpty(message = "FAC104")
    @Size(min = 10, max = 100, message = "FAC106" )
    @Column(length=100, name="location", nullable = false)
    private String location;  
     
}
