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
@Table(name = "tblbitacoradiaria")
@NamedQueries({@NamedQuery(name = "Tblbitacoradiaria.findAll", query = "SELECT t FROM Tblbitacoradiaria t"), @NamedQuery(name = "Tblbitacoradiaria.findByIdBitacora", query = "SELECT t FROM Tblbitacoradiaria t WHERE t.idBitacora = :idBitacora"), @NamedQuery(name = "Tblbitacoradiaria.findByNombre", query = "SELECT t FROM Tblbitacoradiaria t WHERE t.nombre = :nombre"), @NamedQuery(name = "Tblbitacoradiaria.findByHoraInicio", query = "SELECT t FROM Tblbitacoradiaria t WHERE t.horaInicio = :horaInicio"), @NamedQuery(name = "Tblbitacoradiaria.findByHoraFin", query = "SELECT t FROM Tblbitacoradiaria t WHERE t.horaFin = :horaFin"), @NamedQuery(name = "Tblbitacoradiaria.findByIdCategoria", query = "SELECT t FROM Tblbitacoradiaria t WHERE t.idCategoria = :idCategoria"), @NamedQuery(name = "Tblbitacoradiaria.findByIdDepartamento", query = "SELECT t FROM Tblbitacoradiaria t WHERE t.idDepartamento = :idDepartamento"), @NamedQuery(name = "Tblbitacoradiaria.findByIdUsuario", query = "SELECT t FROM Tblbitacoradiaria t WHERE t.idUsuario = :idUsuario"), @NamedQuery(name = "Tblbitacoradiaria.findByFechaCreacion", query = "SELECT t FROM Tblbitacoradiaria t WHERE t.fechaCreacion = :fechaCreacion"), @NamedQuery(name = "Tblbitacoradiaria.findByHoraCreacion", query = "SELECT t FROM Tblbitacoradiaria t WHERE t.horaCreacion = :horaCreacion"), @NamedQuery(name = "Tblbitacoradiaria.findByBorrado", query = "SELECT t FROM Tblbitacoradiaria t WHERE t.borrado = :borrado")})
public class Tblbitacoradiaria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdBitacora")
    private Long idBitacora;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "HoraInicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Column(name = "HoraFin")
    @Temporal(TemporalType.TIME)
    private Date horaFin;
    @Basic(optional = false)
    @Column(name = "IdCategoria")
    private long idCategoria;
    @Basic(optional = false)
    @Column(name = "IdDepartamento")
    private long idDepartamento;
    @Basic(optional = false)
    @Column(name = "IdUsuario")
    private long idUsuario;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Column(name = "HoraCreacion")
    @Temporal(TemporalType.TIME)
    private Date horaCreacion;
    @Basic(optional = false)
    @Column(name = "Borrado")
    private short borrado;
    @Lob
    @Column(name = "Notas")
    private String notas;
    @JoinTable(name = "tblbitacoradiaria_tiene_actividad", joinColumns = {@JoinColumn(name = "tblbitacoradiaria_IdBitacora", referencedColumnName = "IdBitacora")}, inverseJoinColumns = {@JoinColumn(name = "tblactividad_IdActividad", referencedColumnName = "IdActividad")})
    @ManyToMany
    private Collection<Tblactividad> tblactividadCollection;
    @JoinColumn(name = "IdUsuarioCreo", referencedColumnName = "IdUsuario")
    @ManyToOne(optional = false)
    private Tblusuarios idUsuarioCreo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBitacoraPertenece")
    private Collection<Tblinterrupciones> tblinterrupcionesCollection;

    public Tblbitacoradiaria() {
    }

    public Tblbitacoradiaria(Long idBitacora) {
        this.idBitacora = idBitacora;
    }

    public Tblbitacoradiaria(Long idBitacora, String nombre, long idCategoria, long idDepartamento, long idUsuario, short borrado) {
        this.idBitacora = idBitacora;
        this.nombre = nombre;
        this.idCategoria = idCategoria;
        this.idDepartamento = idDepartamento;
        this.idUsuario = idUsuario;
        this.borrado = borrado;
    }

    public Long getIdBitacora() {
        return idBitacora;
    }

    public void setIdBitacora(Long idBitacora) {
        this.idBitacora = idBitacora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public long getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(long idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Collection<Tblactividad> getTblactividadCollection() {
        return tblactividadCollection;
    }

    public void setTblactividadCollection(Collection<Tblactividad> tblactividadCollection) {
        this.tblactividadCollection = tblactividadCollection;
    }

    public Tblusuarios getIdUsuarioCreo() {
        return idUsuarioCreo;
    }

    public void setIdUsuarioCreo(Tblusuarios idUsuarioCreo) {
        this.idUsuarioCreo = idUsuarioCreo;
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
        hash += (idBitacora != null ? idBitacora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblbitacoradiaria)) {
            return false;
        }
        Tblbitacoradiaria other = (Tblbitacoradiaria) object;
        if ((this.idBitacora == null && other.idBitacora != null) || (this.idBitacora != null && !this.idBitacora.equals(other.idBitacora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bitacoradiariajpa.Tblbitacoradiaria[idBitacora=" + idBitacora + "]";
    }

}
