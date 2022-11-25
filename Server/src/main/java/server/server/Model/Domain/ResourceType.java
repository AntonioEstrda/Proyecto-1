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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author anmon
 */

@Entity
@Table(name="ResourceType")
@Data 
public class ResourceType implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="RESSOURCETYPEID")
    private long ResourceTypeId;
    
    @NotEmpty(message = "RESTYP103") 
    @Column(length=100, name="NAME")
    private String name;
    
    @NotNull(message = "RESTYP102")
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isDisable;
    
    @Column(name="RES_RESSOURCETYPEID")
    private Long parent;  
    
    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="RES_RESSOURCETYPEID")
    private ResourceType parent;
    */
}
