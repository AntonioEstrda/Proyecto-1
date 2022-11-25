/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 * Environment entity class 
 * @author anmon
 */
@Entity
@Table(name="enviroment")
@Data
public class Environment implements Serializable {
    
    public enum EnvironmentType {
        SALON, SALA, AUDITORIO;    
    }
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ENVIROMENTID")
    private long EnvironmentId; 
    
    @NotEmpty(message="ENV102")
    @Column(length=100, name="location")
    private String location;
    
    @NotEmpty(message="ENV110")
    @Column(length=5, name="code")
    @Pattern(regexp = "^[a-zA-Z]{2,5}", message="ENV111")
    private String code;
    
    @NotNull(message="ENV109")
    @Column(columnDefinition="int(3)", name="NUMBER")
    @Min(value = 100, message = "ENV103")
    @Max(value = 999, message = "ENV104")
    private int number;  
    
    @NotNull(message = "ENV105")
    @Column(nullable = false, columnDefinition = "TINYINT(1)", name="ISDISABLE")
    private boolean isDisable;
    
    @NotNull(message = "106")
    @Column(columnDefinition="int(4)", name="CAPACITY")
    @Min(value = 1, message = "107")
    private int capacity;  
    
    @NotNull(message = "108")
    @Enumerated(EnumType.STRING)
    @Column(name="type")
    private EnvironmentType type; 
    
}
