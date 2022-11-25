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
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 * @author anmon
 */
@Entity
@Table(name="ResourceT")
@Data
public class Resource implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="RESOURCEID")
    private long ResourceId; 
    
    @NotEmpty(message = "RES105")
    @Column(length=100, name="NAME")
    private String name;
    
    @NotEmpty(message = "RES102")
    @Column(length=100, name="DESCRIPTION")
    private String description;  
    
    @NotNull(message = "RES103")
    @Column(columnDefinition = "TINYINT(1)", name="ISDISABLE")
    private boolean isDisable;
    
    @NotNull(message = "RES104")
    @ManyToOne
    @JoinColumn(name="RESOURCETYPEID")
    private ResourceType resourceType;
    
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
    
    @NotNull(message = "106")
    @Column(columnDefinition="int(4)", name="CAPACITY")
    @Min(value = 1, message = "107")
    private int capacity;  
    
}
