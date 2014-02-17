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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
@Table(name = "tblperfiles")
@NamedQueries({@NamedQuery(name = "Tblperfiles.findAll", query = "SELECT t FROM Tblperfiles t"), @NamedQuery(name = "Tblperfiles.findByIdPerfil", query = "SELECT t FROM Tblperfiles t WHERE t.idPerfil = :idPerfil"), @NamedQuery(name = "Tblperfiles.findByNombre", query = "SELECT t FROM Tblperfiles t WHERE t.nombre = :nombre"), @NamedQuery(name = "Tblperfiles.findByFechaCreacion", query = "SELECT t FROM Tblperfiles t WHERE t.fechaCreacion = :fechaCreacion"), @NamedQuery(name = "Tblperfiles.findByHoraCreacion", query = "SELECT t FROM Tblperfiles t WHERE t.horaCreacion = :horaCreacion"), @NamedQuery(name = "Tblperfiles.findByBorrado", query = "SELECT t FROM Tblperfiles t WHERE t.borrado = :borrado")})
public class Tblperfiles implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdPerfil")
    private Long idPerfil;
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
    @Column(name = "Borrado")
    private Short borrado;
    @JoinTable(name = "tblperfiles_tiene_permisos", joinColumns = {@JoinColumn(name = "tblPerfiles_IdPerfil", referencedColumnName = "IdPerfil")}, inverseJoinColumns = {@JoinColumn(name = "tblPermisos_IdPermiso", referencedColumnName = "IdPermiso")})
    @ManyToMany
    private Collection<Tblpermisos> tblpermisosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerfilNuevo")
    private Collection<TblusuariosCreaPerfiles> tblusuariosCreaPerfilesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerfilTiene")
    private Collection<Tblusuarios> tblusuariosCollection;

    public Tblperfiles() {
    }

    public Tblperfiles(Long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Tblperfiles(Long idPerfil, String nombre, String descripcion) {
        this.idPerfil = idPerfil;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Long getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
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

    public Short getBorrado() {
        return borrado;
    }

    public void setBorrado(Short borrado) {
        this.borrado = borrado;
    }

    public Collection<Tblpermisos> getTblpermisosCollection() {
        return tblpermisosCollection;
    }

    public void setTblpermisosCollection(Collection<Tblpermisos> tblpermisosCollection) {
        this.tblpermisosCollection = tblpermisosCollection;
    }

    public Collection<TblusuariosCreaPerfiles> getTblusuariosCreaPerfilesCollection() {
        return tblusuariosCreaPerfilesCollection;
    }

    public void setTblusuariosCreaPerfilesCollection(Collection<TblusuariosCreaPerfiles> tblusuariosCreaPerfilesCollection) {
        this.tblusuariosCreaPerfilesCollection = tblusuariosCreaPerfilesCollection;
    }

    public Collection<Tblusuarios> getTblusuariosCollection() {
        return tblusuariosCollection;
    }

    public void setTblusuariosCollection(Collection<Tblusuarios> tblusuariosCollection) {
        this.tblusuariosCollection = tblusuariosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerfil != null ? idPerfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblperfiles)) {
            return false;
        }
        Tblperfiles other = (Tblperfiles) object;
        if ((this.idPerfil == null && other.idPerfil != null) || (this.idPerfil != null && !this.idPerfil.equals(other.idPerfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bitacoradiariajpa.Tblperfiles[idPerfil=" + idPerfil + "]";
    }

}
