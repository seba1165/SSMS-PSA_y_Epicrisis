/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 *
 * @author obi
 */
@Entity
public class Auditoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="codigo_audi")
    private Long id;
    @Column(name="tabla_audi")
    private String tabla;
    @Column(name="operacion_audi")
    private String operacion;
    @Lob
    @Basic(fetch=LAZY)
    @Column(columnDefinition = "TEXT",name="old_value_audi")
    private String antiguoValor;
    @Lob
    @Basic(fetch=LAZY)
    @Column(columnDefinition = "TEXT",name="new_value_audi")
    private String nuevoValor;
    @Column(name="fecha_audi")
    private java.sql.Timestamp fecha;
    @Column(name="rut_usuario_audi")
    private String rut_usuario;

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getAntiguoValor() {
        return antiguoValor;
    }

    public void setAntiguoValor(String antiguoValor) {
        this.antiguoValor = antiguoValor;
    }

    public String getNuevoValor() {
        return nuevoValor;
    }

    public void setNuevoValor(String nuevoValor) {
        this.nuevoValor = nuevoValor;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getRut_usuario() {
        return rut_usuario;
    }

    public void setRut_usuario(String rut_usuario) {
        this.rut_usuario = rut_usuario;
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
        if (!(object instanceof Auditoria)) {
            return false;
        }
        Auditoria other = (Auditoria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Auditoria[ id=" + id + " ]";
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
