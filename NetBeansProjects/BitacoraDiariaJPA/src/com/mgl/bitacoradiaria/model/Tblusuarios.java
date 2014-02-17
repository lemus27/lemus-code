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
@Table(name = "tblusuarios")
@NamedQueries({@NamedQuery(name = "Tblusuarios.findAll", query = "SELECT t FROM Tblusuarios t"), @NamedQuery(name = "Tblusuarios.findByIdUsuario", query = "SELECT t FROM Tblusuarios t WHERE t.idUsuario = :idUsuario"), @NamedQuery(name = "Tblusuarios.findByNombre", query = "SELECT t FROM Tblusuarios t WHERE t.nombre = :nombre"), @NamedQuery(name = "Tblusuarios.findByApPaterno", query = "SELECT t FROM Tblusuarios t WHERE t.apPaterno = :apPaterno"), @NamedQuery(name = "Tblusuarios.findByApMaterno", query = "SELECT t FROM Tblusuarios t WHERE t.apMaterno = :apMaterno"), @NamedQuery(name = "Tblusuarios.findByDepartamento", query = "SELECT t FROM Tblusuarios t WHERE t.departamento = :departamento"), @NamedQuery(name = "Tblusuarios.findByEmail", query = "SELECT t FROM Tblusuarios t WHERE t.email = :email"), @NamedQuery(name = "Tblusuarios.findByLogin", query = "SELECT t FROM Tblusuarios t WHERE t.login = :login"), @NamedQuery(name = "Tblusuarios.findByPassword", query = "SELECT t FROM Tblusuarios t WHERE t.password = :password"), @NamedQuery(name = "Tblusuarios.findByFechaCreacion", query = "SELECT t FROM Tblusuarios t WHERE t.fechaCreacion = :fechaCreacion"), @NamedQuery(name = "Tblusuarios.findByHoraCreacion", query = "SELECT t FROM Tblusuarios t WHERE t.horaCreacion = :horaCreacion"), @NamedQuery(name = "Tblusuarios.findByActivo", query = "SELECT t FROM Tblusuarios t WHERE t.activo = :activo"), @NamedQuery(name = "Tblusuarios.findByBorrado", query = "SELECT t FROM Tblusuarios t WHERE t.borrado = :borrado")})
public class Tblusuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdUsuario")
    private Long idUsuario;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "ApPaterno")
    private String apPaterno;
    @Basic(optional = false)
    @Column(name = "ApMaterno")
    private String apMaterno;
    @Basic(optional = false)
    @Column(name = "Departamento")
    private String departamento;
    @Basic(optional = false)
    @Column(name = "Email")
    private String email;
    @Basic(optional = false)
    @Column(name = "Login")
    private String login;
    @Basic(optional = false)
    @Column(name = "Password")
    private String password;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Column(name = "HoraCreacion")
    @Temporal(TemporalType.TIME)
    private Date horaCreacion;
    @Basic(optional = false)
    @Column(name = "Activo")
    private boolean activo;
    @Column(name = "Borrado")
    private Boolean borrado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuarioCreador")
    private Collection<TblusuariosCreaUsuarios> tblusuariosCreaUsuariosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblusuariosIdUsuario")
    private Collection<TblusuariosCreaUsuarios> tblusuariosCreaUsuariosCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuarioCreo")
    private Collection<Tblbitacoradiaria> tblbitacoradiariaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuarioCrea")
    private Collection<TblusuariosCreaPerfiles> tblusuariosCreaPerfilesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuarioCrea")
    private Collection<TblusuariosCreaTblpermisos> tblusuariosCreaTblpermisosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuarioCrea")
    private Collection<TblusuariosCreaCategorias> tblusuariosCreaCategoriasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuarioCrea")
    private Collection<TblusuariosCreaInterrupciones> tblusuariosCreaInterrupcionesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuarioCrea")
    private Collection<TblusuariosCreaActividad> tblusuariosCreaActividadCollection;
    @JoinColumn(name = "IdPerfilTiene", referencedColumnName = "IdPerfil")
    @ManyToOne(optional = false)
    private Tblperfiles idPerfilTiene;

    public Tblusuarios() {
    }

    public Tblusuarios(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Tblusuarios(Long idUsuario, String nombre, String apPaterno, String apMaterno, String departamento, String email, String login, String password, boolean activo) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.departamento = departamento;
        this.email = email;
        this.login = login;
        this.password = password;
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

    public Collection<TblusuariosCreaUsuarios> getTblusuariosCreaUsuariosCollection() {
        return tblusuariosCreaUsuariosCollection;
    }

    public void setTblusuariosCreaUsuariosCollection(Collection<TblusuariosCreaUsuarios> tblusuariosCreaUsuariosCollection) {
        this.tblusuariosCreaUsuariosCollection = tblusuariosCreaUsuariosCollection;
    }

    public Collection<TblusuariosCreaUsuarios> getTblusuariosCreaUsuariosCollection1() {
        return tblusuariosCreaUsuariosCollection1;
    }

    public void setTblusuariosCreaUsuariosCollection1(Collection<TblusuariosCreaUsuarios> tblusuariosCreaUsuariosCollection1) {
        this.tblusuariosCreaUsuariosCollection1 = tblusuariosCreaUsuariosCollection1;
    }

    public Collection<Tblbitacoradiaria> getTblbitacoradiariaCollection() {
        return tblbitacoradiariaCollection;
    }

    public void setTblbitacoradiariaCollection(Collection<Tblbitacoradiaria> tblbitacoradiariaCollection) {
        this.tblbitacoradiariaCollection = tblbitacoradiariaCollection;
    }

    public Collection<TblusuariosCreaPerfiles> getTblusuariosCreaPerfilesCollection() {
        return tblusuariosCreaPerfilesCollection;
    }

    public void setTblusuariosCreaPerfilesCollection(Collection<TblusuariosCreaPerfiles> tblusuariosCreaPerfilesCollection) {
        this.tblusuariosCreaPerfilesCollection = tblusuariosCreaPerfilesCollection;
    }

    public Collection<TblusuariosCreaTblpermisos> getTblusuariosCreaTblpermisosCollection() {
        return tblusuariosCreaTblpermisosCollection;
    }

    public void setTblusuariosCreaTblpermisosCollection(Collection<TblusuariosCreaTblpermisos> tblusuariosCreaTblpermisosCollection) {
        this.tblusuariosCreaTblpermisosCollection = tblusuariosCreaTblpermisosCollection;
    }

    public Collection<TblusuariosCreaCategorias> getTblusuariosCreaCategoriasCollection() {
        return tblusuariosCreaCategoriasCollection;
    }

    public void setTblusuariosCreaCategoriasCollection(Collection<TblusuariosCreaCategorias> tblusuariosCreaCategoriasCollection) {
        this.tblusuariosCreaCategoriasCollection = tblusuariosCreaCategoriasCollection;
    }

    public Collection<TblusuariosCreaInterrupciones> getTblusuariosCreaInterrupcionesCollection() {
        return tblusuariosCreaInterrupcionesCollection;
    }

    public void setTblusuariosCreaInterrupcionesCollection(Collection<TblusuariosCreaInterrupciones> tblusuariosCreaInterrupcionesCollection) {
        this.tblusuariosCreaInterrupcionesCollection = tblusuariosCreaInterrupcionesCollection;
    }

    public Collection<TblusuariosCreaActividad> getTblusuariosCreaActividadCollection() {
        return tblusuariosCreaActividadCollection;
    }

    public void setTblusuariosCreaActividadCollection(Collection<TblusuariosCreaActividad> tblusuariosCreaActividadCollection) {
        this.tblusuariosCreaActividadCollection = tblusuariosCreaActividadCollection;
    }

    public Tblperfiles getIdPerfilTiene() {
        return idPerfilTiene;
    }

    public void setIdPerfilTiene(Tblperfiles idPerfilTiene) {
        this.idPerfilTiene = idPerfilTiene;
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
        return "bitacoradiariajpa.Tblusuarios[idUsuario=" + idUsuario + "]";
    }

}
