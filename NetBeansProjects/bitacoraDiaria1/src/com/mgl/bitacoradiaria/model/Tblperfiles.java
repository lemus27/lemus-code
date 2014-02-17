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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "tblperfiles", catalog = "dbbitacoradiaria", schema = "")
@NamedQueries({@NamedQuery(name = "Tblperfiles.findAll", query = "SELECT t FROM Tblperfiles t"), @NamedQuery(name = "Tblperfiles.findByIdPerfil", query = "SELECT t FROM Tblperfiles t WHERE t.idPerfil = :idPerfil"), @NamedQuery(name = "Tblperfiles.findByNombre", query = "SELECT t FROM Tblperfiles t WHERE t.nombre = :nombre"), @NamedQuery(name = "Tblperfiles.findByFechaCreacion", query = "SELECT t FROM Tblperfiles t WHERE t.fechaCreacion = :fechaCreacion"), @NamedQuery(name = "Tblperfiles.findByHoraCreacion", query = "SELECT t FROM Tblperfiles t WHERE t.horaCreacion = :horaCreacion"), @NamedQuery(name = "Tblperfiles.findByUsuarioCreo", query = "SELECT t FROM Tblperfiles t WHERE t.usuarioCreo = :usuarioCreo"), @NamedQuery(name = "Tblperfiles.findByLoginCreo", query = "SELECT t FROM Tblperfiles t WHERE t.loginCreo = :loginCreo"), @NamedQuery(name = "Tblperfiles.findByBorrado", query = "SELECT t FROM Tblperfiles t WHERE t.borrado = :borrado")})
public class Tblperfiles implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IdPerfil", nullable = false)
    private Long idPerfil;
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
    @Column(name = "Borrado")
    private Short borrado;
    @JoinTable(name = "tblperfiles_has_tblpermisos", joinColumns = {@JoinColumn(name = "tblPerfiles_IdPerfil", referencedColumnName = "IdPerfil", nullable = false)}, inverseJoinColumns = {@JoinColumn(name = "tblPermisos_IdPermiso", referencedColumnName = "IdPermiso", nullable = false)})
    @ManyToMany
    private Collection<Tblpermisos> tblpermisosCollection;
    @JoinColumn(name = "tblusuarios_IdUsuario", referencedColumnName = "IdUsuario", nullable = false)
    @ManyToOne(optional = false)
    private Tblusuarios tblusuariosIdUsuario;

    public Tblperfiles() {
    }

    public Tblperfiles(Long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Tblperfiles(Long idPerfil, String nombre, String descripcion, String usuarioCreo, String loginCreo) {
        this.idPerfil = idPerfil;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.usuarioCreo = usuarioCreo;
        this.loginCreo = loginCreo;
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

    public Tblusuarios getTblusuariosIdUsuario() {
        return tblusuariosIdUsuario;
    }

    public void setTblusuariosIdUsuario(Tblusuarios tblusuariosIdUsuario) {
        this.tblusuariosIdUsuario = tblusuariosIdUsuario;
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
        return "com.mgl.bitacoradiaria.model.Tblperfiles[idPerfil=" + idPerfil + "]";
    }

}
