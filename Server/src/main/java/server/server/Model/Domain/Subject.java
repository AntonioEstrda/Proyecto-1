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
@Table(name="subject")
@Data
public class Subject implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IDSUBJECT")
    private long subjectID; 
    
    @NotEmpty(message = "SUBJ103") 
    @Column(length=100, name="NAME")
    private String name;
    
    @NotEmpty(message = "SUBJ104") 
    @Column(length=100, name="REQUISITS")
    private String requisits;
    
    @NotEmpty(message = "SUBJ105") 
    @Column(length=100, name="SEMESTER")
    private String semester;
    
    @NotEmpty(message = "SUBJ106") 
    @Column(length=100, name="INTENSITY")
    private String intensity;
    
    @NotNull(message = "SUBJ107") 
    @Column(length=8, name="Modality")
    @Enumerated(EnumType.STRING)
    private Modality modality;
    
    @NotNull(message = "SUBJ108") 
    @Column(nullable = false, columnDefinition = "TINYINT(1)", name="ISDISABLE")
    private String isDisable;
    
    @NotNull(message = "SUBJ109") 
    @Column(length=8, name="type")
    @Enumerated(EnumType.STRING)
    private Type type;
    
    @NotNull(message = "SUBJ110") 
    @ManyToOne
    @JoinColumn(name="IDPROGRAM")
    private Program program;
        
    
    public enum Modality {
        Semestral, Anual;
    }
    
    public enum Type {
        Teórica,Práctica,Híbrida;
    }
    
}


