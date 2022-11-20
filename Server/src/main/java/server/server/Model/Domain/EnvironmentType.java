/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Model.Domain;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author anmon
 */
@Entity
@Table(name="EnviromentType")
@Data
public class EnvironmentType implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ENVIROMENTTYPEID")
    private long ResourceTypeId;
    
    @NotEmpty
    @Column(length=100, name="NAME")
    private String name;
    
    @NotNull
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isDisable;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ENV_ENVIROMENTTYPEID")
    private EnvironmentType parent;
    
    @OneToMany(mappedBy="environmentType", fetch = FetchType.LAZY)
    private ArrayList<Environment> enviromentsAssociated;
}
