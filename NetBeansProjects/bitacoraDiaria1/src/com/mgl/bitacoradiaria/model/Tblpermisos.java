/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mgl.bitacoradiaria.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mike
 */
@Entity
@Table(name = "tblpermisos", catalog = "dbbitacoradiaria", schema = "")
@NamedQueries({@NamedQuery(name = "Tblpermisos.findAll", query = "SELECT t FROM Tblpermisos t"), @NamedQuery(name = "Tblpermisos.findByIdPermiso", query = "SELECT t FROM Tblpermisos t WHERE t.idPermiso = :idPermiso"), @NamedQuery(name = "Tblpermisos.findByNombre", query = "SELECT t FROM Tblpermisos t WHERE t.nombre = :nombre"), @NamedQuery(name = "Tblpermisos.findByFechaCreacion", query = "SELECT t FROM Tblpermisos t WHERE t.fechaCreacion = :fechaCreacion"), @NamedQuery(name = "Tblpermisos.findByHoraCreacion", query = "SELECT t FROM Tblpermisos t WHERE t.horaCreacion = :horaCreacion"), @NamedQuery(name = "Tblpermisos.findByUsuarioCreo", query = "SELECT t FROM Tblpermisos t WHERE t.usuarioCreo = :usuarioCreo"), @NamedQuery(name = "Tblpermisos.findByLoginCreo", query = "SELECT t FROM Tblpermisos t WHERE t.loginCreo = :loginCreo"), @NamedQuery(name = "Tblpermisos.findByPermiso", query = "SELECT t FROM Tblpermisos t WHERE t.permiso = :permiso")})
public class Tblpermisos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IdPermiso", nullable = false)
    private Long idPermiso;
    @Basic(optional = false)
    @Column(name = "Nombre", nullable = false, length = 60)
    private String nombre;
    @Basic(optional = false)
    @Lob
    @Column(name = "Descripcion", nullable = false, length = 65535)
    private String descripcion;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Column(name = "HoraCreacion")
    @Temporal(TemporalType.TIME)
    private Date horaCreacion;
    @Basic(optional = false)
    @Column(name = "UsuarioCreo", nullable = false, length = 16)
    private String usuarioCreo;
    @Basic(optional = false)
    @Column(name = "LoginCreo", nullable = false, length = 16)
    private String loginCreo;
    @Column(name = "permiso", length = 45)
    private String permiso;
    @ManyToMany(mappedBy = "tblpermisosCollection")
    private Collection<Tblperfiles> tblperfilesCollection;

    public Tblpermisos() {
    }

    public Tblpermisos(Long idPermiso) {
        this.idPermiso = idPermiso;
    }

    public Tblpermisos(Long idPermiso, String nombre, String descripcion, String usuarioCreo, String loginCreo) {
        this.idPermiso = idPermiso;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.usuarioCreo = usuarioCreo;
        this.loginCreo = loginCreo;
    }

    public Long getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Long idPermiso) {
        this.idPermiso = idPermiso;
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

    public String getUsuarioCreo() {
        return usuarioCreo;
    }

    public void setUsuarioCreo(String usuarioCreo) {
        this.usuarioCreo = usuarioCreo;
    }

    public String getLoginCreo() {
        return loginCreo;
    }

    public void setLoginCreo(String loginCreo) {
        this.loginCreo = loginCreo;
    }

    public String getPermiso() {
        return permiso;
    }

    public void setPermiso(String permiso) {
        this.permiso = permiso;
    }

    public Collection<Tblperfiles> getTblperfilesCollection() {
        return tblperfilesCollection;
    }

    public void setTblperfilesCollection(Collection<Tblperfiles> tblperfilesCollection) {
        this.tblperfilesCollection = tblperfilesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPermiso != null ? idPermiso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblpermisos)) {
            return false;
        }
        Tblpermisos other = (Tblpermisos) object;
        if ((this.idPermiso == null && other.idPermiso != null) || (this.idPermiso != null && !this.idPermiso.equals(other.idPermiso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mgl.bitacoradiaria.model.Tblpermisos[idPermiso=" + idPermiso + "]";
    }

}
