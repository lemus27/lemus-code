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
import javax.persistence.Id;
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
@Table(name = "tblusuarios", catalog = "dbbitacoradiaria", schema = "")
@NamedQueries({@NamedQuery(name = "Tblusuarios.findAll", query = "SELECT t FROM Tblusuarios t"), @NamedQuery(name = "Tblusuarios.findByIdUsuario", query = "SELECT t FROM Tblusuarios t WHERE t.idUsuario = :idUsuario"), @NamedQuery(name = "Tblusuarios.findByNombre", query = "SELECT t FROM Tblusuarios t WHERE t.nombre = :nombre"), @NamedQuery(name = "Tblusuarios.findByApPaterno", query = "SELECT t FROM Tblusuarios t WHERE t.apPaterno = :apPaterno"), @NamedQuery(name = "Tblusuarios.findByApMaterno", query = "SELECT t FROM Tblusuarios t WHERE t.apMaterno = :apMaterno"), @NamedQuery(name = "Tblusuarios.findByDepartamento", query = "SELECT t FROM Tblusuarios t WHERE t.departamento = :departamento"), @NamedQuery(name = "Tblusuarios.findByEmail", query = "SELECT t FROM Tblusuarios t WHERE t.email = :email"), @NamedQuery(name = "Tblusuarios.findByPerfil", query = "SELECT t FROM Tblusuarios t WHERE t.perfil = :perfil"), @NamedQuery(name = "Tblusuarios.findByLogin", query = "SELECT t FROM Tblusuarios t WHERE t.login = :login"), @NamedQuery(name = "Tblusuarios.findByPassword", query = "SELECT t FROM Tblusuarios t WHERE t.password = :password"), @NamedQuery(name = "Tblusuarios.findByFechaCreacion", query = "SELECT t FROM Tblusuarios t WHERE t.fechaCreacion = :fechaCreacion"), @NamedQuery(name = "Tblusuarios.findByHoraCreacion", query = "SELECT t FROM Tblusuarios t WHERE t.horaCreacion = :horaCreacion"), @NamedQuery(name = "Tblusuarios.findByUsuarioCreo", query = "SELECT t FROM Tblusuarios t WHERE t.usuarioCreo = :usuarioCreo"), @NamedQuery(name = "Tblusuarios.findByActivo", query = "SELECT t FROM Tblusuarios t WHERE t.activo = :activo"), @NamedQuery(name = "Tblusuarios.findByBorrado", query = "SELECT t FROM Tblusuarios t WHERE t.borrado = :borrado")})
public class Tblusuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IdUsuario", nullable = false)
    private Long idUsuario;
    @Basic(optional = false)
    @Column(name = "Nombre", nullable = false, length = 60)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "ApPaterno", nullable = false, length = 60)
    private String apPaterno;
    @Basic(optional = false)
    @Column(name = "ApMaterno", nullable = false, length = 60)
    private String apMaterno;
    @Basic(optional = false)
    @Column(name = "Departamento", nullable = false, length = 60)
    private String departamento;
    @Basic(optional = false)
    @Column(name = "Email", nullable = false, length = 60)
    private String email;
    @Basic(optional = false)
    @Column(name = "Perfil", nullable = false, length = 16)
    private String perfil;
    @Basic(optional = false)
    @Column(name = "Login", nullable = false, length = 16)
    private String login;
    @Basic(optional = false)
    @Column(name = "Password", nullable = false, length = 16)
    private String password;
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
    @Column(name = "Activo", nullable = false)
    private boolean activo;
    @Column(name = "Borrado")
    private Boolean borrado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblusuariosIdUsuario")
    private Collection<Tblperfiles> tblperfilesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblusuariosIdUsuario")
    private Collection<Tblactividad> tblactividadCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblusuariosIdUsuario")
    private Collection<Tblinterrupciones> tblinterrupcionesCollection;

    public Tblusuarios() {
    }

    public Tblusuarios(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Tblusuarios(Long idUsuario, String nombre, String apPaterno, String apMaterno, String departamento, String email, String perfil, String login, String password, String usuarioCreo, boolean activo) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.departamento = departamento;
        this.email = email;
        this.perfil = perfil;
        this.login = login;
        this.password = password;
        this.usuarioCreo = usuarioCreo;
        this.activo = activo;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    public Collection<Tblperfiles> getTblperfilesCollection() {
        return tblperfilesCollection;
    }

    public void setTblperfilesCollection(Collection<Tblperfiles> tblperfilesCollection) {
        this.tblperfilesCollection = tblperfilesCollection;
    }

    public Collection<Tblactividad> getTblactividadCollection() {
        return tblactividadCollection;
    }

    public void setTblactividadCollection(Collection<Tblactividad> tblactividadCollection) {
        this.tblactividadCollection = tblactividadCollection;
    }

    public Collection<Tblinterrupciones> getTblinterrupcionesCollection() {
        return tblinterrupcionesCollection;
    }

    public void setTblinterrupcionesCollection(Collection<Tblinterrupciones> tblinterrupcionesCollection) {
        this.tblinterrupcionesCollection = tblinterrupcionesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblusuarios)) {
            return false;
        }
        Tblusuarios other = (Tblusuarios) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mgl.bitacoradiaria.model.Tblusuarios[idUsuario=" + idUsuario + "]";
    }

}
