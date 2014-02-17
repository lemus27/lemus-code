/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mgl.bitacoradiaria.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mike
 */
@Entity
@Table(name = "tblactividad")
@NamedQueries({@NamedQuery(name = "Tblactividad.findAll", query = "SELECT t FROM Tblactividad t"), @NamedQuery(name = "Tblactividad.findByIdActividad", query = "SELECT t FROM Tblactividad t WHERE t.idActividad = :idActividad"), @NamedQuery(name = "Tblactividad.findByNombre", query = "SELECT t FROM Tblactividad t WHERE t.nombre = :nombre"), @NamedQuery(name = "Tblactividad.findByFechaCreacion", query = "SELECT t FROM Tblactividad t WHERE t.fechaCreacion = :fechaCreacion"), @NamedQuery(name = "Tblactividad.findByHoraCreacion", query = "SELECT t FROM Tblactividad t WHERE t.horaCreacion = :horaCreacion"), @NamedQuery(name = "Tblactividad.findByTipo", query = "SELECT t FROM Tblactividad t WHERE t.tipo = :tipo"), @NamedQuery(name = "Tblactividad.findByBorrado", query = "SELECT t FROM Tblactividad t WHERE t.borrado = :borrado")})
public class Tblactividad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdActividad")
    private Long idActividad;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @Lob
    @Column(name = "Descripcion")
    private String descripcion;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Column(name = "HoraCreacion")
    @Temporal(TemporalType.TIME)
    private Date horaCreacion;
    @Basic(optional = false)
    @Column(name = "Tipo")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "Borrado")
    private boolean borrado;
    @ManyToMany(mappedBy = "tblactividadCollection")
    private Collection<Tblbitacoradiaria> tblbitacoradiariaCollection;
    @JoinColumn(name = "IdCategoriaPertenece", referencedColumnName = "IdCategoria")
    @ManyToOne(optional = false)
    private Tblcategorias idCategoriaPertenece;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idActividadNuevo")
    private TblusuariosCreaActividad tblusuariosCreaActividad;

    public Tblactividad() {
    }

    public Tblactividad(Long idActividad) {
        this.idActividad = idActividad;
    }

    public Tblactividad(Long idActividad, String nombre, String descripcion, String tipo, boolean borrado) {
        this.idActividad = idActividad;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.borrado = borrado;
    }

    public Long getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Long idActividad) {
        this.idActividad = idActividad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getHoraCreacion() {
        return horaCreacion;
    }

    public void setHoraCreacion(Date horaCreacion) {
        this.horaCreacion = horaCreacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public Collection<Tblbitacoradiaria> getTblbitacoradiariaCollection() {
        return tblbitacoradiariaCollection;
    }

    public void setTblbitacoradiariaCollection(Collection<Tblbitacoradiaria> tblbitacoradiariaCollection) {
        this.tblbitacoradiariaCollection = tblbitacoradiariaCollection;
    }

    public Tblcategorias getIdCategoriaPertenece() {
        return idCategoriaPertenece;
    }

    public void setIdCategoriaPertenece(Tblcategorias idCategoriaPertenece) {
        this.idCategoriaPertenece = idCategoriaPertenece;
    }

    public TblusuariosCreaActividad getTblusuariosCreaActividad() {
        return tblusuariosCreaActividad;
    }

    public void setTblusuariosCreaActividad(TblusuariosCreaActividad tblusuariosCreaActividad) {
        this.tblusuariosCreaActividad = tblusuariosCreaActividad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActividad != null ? idActividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblactividad)) {
            return false;
        }
        Tblactividad other = (Tblactividad) object;
        if ((this.idActividad == null && other.idActividad != null) || (this.idActividad != null && !this.idActividad.equals(other.idActividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bitacoradiariajpa.Tblactividad[idActividad=" + idActividad + "]";
    }

   
}
