/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author obi
 */
@Entity
public class cesfam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="codigo_cesfam")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="comuna_cesfam")
    private comuna comuna;
    
    @Column(name="nombre_tipo_cesfam")
    private String nombre_tipo;
    
    @Column(name="nombre_cesfam")
    private String nombre;

    public comuna getComuna() {
        return comuna;
    }

    public void setComuna(comuna comuna) {
        this.comuna = comuna;
    }

    public String getNombre_tipo() {
        return nombre_tipo;
    }

    public void setNombre_tipo(String nombre_tipo) {
        this.nombre_tipo = nombre_tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof cesfam)) {
            return false;
        }
        cesfam other = (cesfam) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CESFAM[ id=" + id + " ]";
    }
    
    @Override
    public Object clone()
    {
        Object clone = null;
        try
        {
            clone = super.clone();
        } 
        catch(CloneNotSupportedException e)
        {
            // No deberia suceder
        }
        return clone;
    }
}
