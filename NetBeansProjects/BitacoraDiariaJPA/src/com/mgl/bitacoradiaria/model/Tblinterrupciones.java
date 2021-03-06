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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mike
 */
@Entity
@Table(name = "tblinterrupciones")
@NamedQueries({@NamedQuery(name = "Tblinterrupciones.findAll", query = "SELECT t FROM Tblinterrupciones t"), @NamedQuery(name = "Tblinterrupciones.findByIdInterrupcion", query = "SELECT t FROM Tblinterrupciones t WHERE t.idInterrupcion = :idInterrupcion"), @NamedQuery(name = "Tblinterrupciones.findByNombre", query = "SELECT t FROM Tblinterrupciones t WHERE t.nombre = :nombre"), @NamedQuery(name = "Tblinterrupciones.findByDuracionMinutos", query = "SELECT t FROM Tblinterrupciones t WHERE t.duracionMinutos = :duracionMinutos"), @NamedQuery(name = "Tblinterrupciones.findByFechaCreacion", query = "SELECT t FROM Tblinterrupciones t WHERE t.fechaCreacion = :fechaCreacion"), @NamedQuery(name = "Tblinterrupciones.findByHoraCreacion", query = "SELECT t FROM Tblinterrupciones t WHERE t.horaCreacion = :horaCreacion"), @NamedQuery(name = "Tblinterrupciones.findByBorrado", query = "SELECT t FROM Tblinterrupciones t WHERE t.borrado = :borrado")})
public class Tblinterrupciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdInterrupcion")
    private Long idInterrupcion;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "DuracionMinutos")
    private String duracionMinutos;
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
    @Column(name = "Borrado")
    private short borrado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInterrupcionNuevo")
    private Collection<TblusuariosCreaInterrupciones> tblusuariosCreaInterrupcionesCollection;
    @JoinColumn(name = "IdCategoriaPertenece", referencedColumnName = "IdCategoria")
    @ManyToOne(optional = false)
    private Tblcategorias idCategoriaPertenece;
    @JoinColumn(name = "IdBitacoraPertenece", referencedColumnName = "IdBitacora")
    @ManyToOne(optional = false)
    private Tblbitacoradiaria idBitacoraPertenece;

    public Tblinterrupciones() {
    }

    public Tblinterrupciones(Long idInterrupcion) {
        this.idInterrupcion = idInterrupcion;
    }

    public Tblinterrupciones(Long idInterrupcion, String nombre, String duracionMinutos, String descripcion, short borrado) {
        this.idInterrupcion = idInterrupcion;
        this.nombre = nombre;
        this.duracionMinutos = duracionMinutos;
        this.descripcion = descripcion;
        this.borrado = borrado;
    }

    public Long getIdInterrupcion() {
        return idInterrupcion;
    }

    public void setIdInterrupcion(Long idInterrupcion) {
        this.idInterrupcion = idInterrupcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(String duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
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

    public short getBorrado() {
        return borrado;
    }

    public void setBorrado(short borrado) {
        this.borrado = borrado;
    }

    public Collection<TblusuariosCreaInterrupciones> getTblusuariosCreaInterrupcionesCollection() {
        return tblusuariosCreaInterrupcionesCollection;
    }

    public void setTblusuariosCreaInterrupcionesCollection(Collection<TblusuariosCreaInterrupciones> tblusuariosCreaInterrupcionesCollection) {
        this.tblusuariosCreaInterrupcionesCollection = tblusuariosCreaInterrupcionesCollection;
    }

    public Tblcategorias getIdCategoriaPertenece() {
        return idCategoriaPertenece;
    }

    public void setIdCategoriaPertenece(Tblcategorias idCategoriaPertenece) {
        this.idCategoriaPertenece = idCategoriaPertenece;
    }

    public Tblbitacoradiaria getIdBitacoraPertenece() {
        return idBitacoraPertenece;
    }

    public void setIdBitacoraPertenece(Tblbitacoradiaria idBitacoraPertenece) {
        this.idBitacoraPertenece = idBitacoraPertenece;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInterrupcion != null ? idInterrupcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblinterrupciones)) {
            return false;
        }
        Tblinterrupciones other = (Tblinterrupciones) object;
        if ((this.idInterrupcion == null && other.idInterrupcion != null) || (this.idInterrupcion != null && !this.idInterrupcion.equals(other.idInterrupcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bitacoradiariajpa.Tblinterrupciones[idInterrupcion=" + idInterrupcion + "]";
    }

}
