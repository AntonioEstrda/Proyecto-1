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
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    
    @NotEmpty
    @Column(length=100, name="NAME")
    private String name;
    
    @NotEmpty 
    @Column(length=100, name="DESCRIPTION")
    private String description;  
    
    @NotNull
    @Column(nullable = false, columnDefinition = "TINYINT(1)", name="ISDISABLE")
    private boolean isDisable;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name="RESSOURCETYPEID")
    private ResourceType resourceType;
    
}
