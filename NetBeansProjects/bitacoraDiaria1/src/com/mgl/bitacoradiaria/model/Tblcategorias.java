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
import javax.persistence.Lob;
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
@Table(name = "tblcategorias", catalog = "dbbitacoradiaria", schema = "")
@NamedQueries({@NamedQuery(name = "Tblcategorias.findAll", query = "SELECT t FROM Tblcategorias t"), @NamedQuery(name = "Tblcategorias.findByIdCategoria", query = "SELECT t FROM Tblcategorias t WHERE t.idCategoria = :idCategoria"), @NamedQuery(name = "Tblcategorias.findByNombre", query = "SELECT t FROM Tblcategorias t WHERE t.nombre = :nombre"), @NamedQuery(name = "Tblcategorias.findByFechaCreacion", query = "SELECT t FROM Tblcategorias t WHERE t.fechaCreacion = :fechaCreacion"), @NamedQuery(name = "Tblcategorias.findByHoraCreacion", query = "SELECT t FROM Tblcategorias t WHERE t.horaCreacion = :horaCreacion"), @NamedQuery(name = "Tblcategorias.findByLoginCreo", query = "SELECT t FROM Tblcategorias t WHERE t.loginCreo = :loginCreo"), @NamedQuery(name = "Tblcategorias.findByPasswordCreo", query = "SELECT t FROM Tblcategorias t WHERE t.passwordCreo = :passwordCreo"), @NamedQuery(name = "Tblcategorias.findByCategoriaPadre", query = "SELECT t FROM Tblcategorias t WHERE t.categoriaPadre = :categoriaPadre"), @NamedQuery(name = "Tblcategorias.findByTipo", query = "SELECT t FROM Tblcategorias t WHERE t.tipo = :tipo"), @NamedQuery(name = "Tblcategorias.findByBorrado", query = "SELECT t FROM Tblcategorias t WHERE t.borrado = :borrado")})
public class Tblcategorias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdCategoria", nullable = false)
    private Long idCategoria;
    @Basic(optional = false)
    @Column(name = "Nombre", nullable = false, length = 200)
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
    @Column(name = "LoginCreo", nullable = false, length = 16)
    private String loginCreo;
    @Basic(optional = false)
    @Column(name = "PasswordCreo", nullable = false, length = 16)
    private String passwordCreo;
    @Basic(optional = false)
    @Column(name = "CategoriaPadre", nullable = false)
    private long categoriaPadre;
    @Basic(optional = false)
    @Column(name = "Tipo", nullable = false, length = 1)
    private String tipo;
    @Basic(optional = false)
    @Column(name = "Borrado", nullable = false)
    private boolean borrado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblcategoriasIdCategoria")
    private Collection<Tblactividad> tblactividadCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblcategoriasIdCategoria")
    private Collection<Tblinterrupciones> tblinterrupcionesCollection;

    public Tblcategorias() {
    }

    public Tblcategorias(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Tblcategorias(Long idCategoria, String nombre, String descripcion, String loginCreo, String passwordCreo, long categoriaPadre, String tipo, boolean borrado) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.loginCreo = loginCreo;
        this.passwordCreo = passwordCreo;
        this.categoriaPadre = categoriaPadre;
        this.tipo = tipo;
        this.borrado = borrado;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
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

    public String getLoginCreo() {
        return loginCreo;
    }

    public void setLoginCreo(String loginCreo) {
        this.loginCreo = loginCreo;
    }

    public String getPasswordCreo() {
        return passwordCreo;
    }

    public void setPasswordCreo(String passwordCreo) {
        this.passwordCreo = passwordCreo;
    }

    public long getCategoriaPadre() {
        return categoriaPadre;
    }

    public void setCategoriaPadre(long categoriaPadre) {
        this.categoriaPadre = categoriaPadre;
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
        hash += (idCategoria != null ? idCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblcategorias)) {
            return false;
        }
        Tblcategorias other = (Tblcategorias) object;
        if ((this.idCategoria == null && other.idCategoria != null) || (this.idCategoria != null && !this.idCategoria.equals(other.idCategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mgl.bitacoradiaria.model.Tblcategorias[idCategoria=" + idCategoria + "]";
    }

}
