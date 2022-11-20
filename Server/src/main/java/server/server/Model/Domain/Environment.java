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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * Environment entity class 
 * @author anmon
 */
@Entity
@Table(name="enviroment")
@Data
public class Environment implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ENVIROMENTID")
    private long EnvironmentId; 
    
    @NotEmpty 
    @Column(length=100, name="UBICATION")
    private String ubication;
    
    @NotNull 
    @Column(columnDefinition="int(3)", name="NUMBER")
    @Min(value = 100, message = "Number must be higher or equal than 100")
    @Max(value = 999, message = "Number must be less than 999")
    private int number;  
    
    @NotNull
    @Column(nullable = false, columnDefinition = "TINYINT(1)", name="ISDISABLE")
    private boolean isDisable;
    
    @NotNull 
    @Column(columnDefinition="int(4)", name="CAPACITY")
    @Min(value = 0, message = "Capacity should not be less than 0")
    private int capacity;  
    
    @NotNull
    @ManyToOne
    @JoinColumn(name="ENVIROMENTTYPEID")
    private EnvironmentType environmentType;
}
