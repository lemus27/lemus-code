/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mgl.bitacoradiaria.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mike
 */
@Entity
@Table(name = "tblactividad", catalog = "dbbitacoradiaria", schema = "")
@NamedQueries({@NamedQuery(name = "Tblactividad.findAll", query = "SELECT t FROM Tblactividad t"), @NamedQuery(name = "Tblactividad.findByIdActividad", query = "SELECT t FROM Tblactividad t WHERE t.idActividad = :idActividad"), @NamedQuery(name = "Tblactividad.findByNombre", query = "SELECT t FROM Tblactividad t WHERE t.nombre = :nombre"), @NamedQuery(name = "Tblactividad.findByHoraInicio", query = "SELECT t FROM Tblactividad t WHERE t.horaInicio = :horaInicio"), @NamedQuery(name = "Tblactividad.findByHoraFin", query = "SELECT t FROM Tblactividad t WHERE t.horaFin = :horaFin"), @NamedQuery(name = "Tblactividad.findByIdCategoria", query = "SELECT t FROM Tblactividad t WHERE t.idCategoria = :idCategoria"), @NamedQuery(name = "Tblactividad.findByIdDepartamento", query = "SELECT t FROM Tblactividad t WHERE t.idDepartamento = :idDepartamento"), @NamedQuery(name = "Tblactividad.findByIdUsuario", query = "SELECT t FROM Tblactividad t WHERE t.idUsuario = :idUsuario"), @NamedQuery(name = "Tblactividad.findByFechaCreacion", query = "SELECT t FROM Tblactividad t WHERE t.fechaCreacion = :fechaCreacion"), @NamedQuery(name = "Tblactividad.findByHoraCreacion", query = "SELECT t FROM Tblactividad t WHERE t.horaCreacion = :horaCreacion"), @NamedQuery(name = "Tblactividad.findByBorrado", query = "SELECT t FROM Tblactividad t WHERE t.borrado = :borrado")})
public class Tblactividad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdActividad", nullable = false)
    private Long idActividad;
    @Basic(optional = false)
    @Column(name = "Nombre", nullable = false, length = 200)
    private String nombre;
    @Column(name = "HoraInicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Column(name = "HoraFin")
    @Temporal(TemporalType.TIME)
    private Date horaFin;
    @Basic(optional = false)
    @Column(name = "IdCategoria", nullable = false)
    private long idCategoria;
    @Basic(optional = false)
    @Column(name = "IdDepartamento", nullable = false)
    private long idDepartamento;
    @Basic(optional = false)
    @Column(name = "IdUsuario", nullable = false)
    private long idUsuario;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Column(name = "HoraCreacion")
    @Temporal(TemporalType.TIME)
    private Date horaCreacion;
    @Basic(optional = false)
    @Column(name = "Borrado", nullable = false)
    private short borrado;
    @Lob
    @Column(name = "Notas", length = 65535)
    private String notas;
    @JoinColumn(name = "tblcategorias_IdCategoria", referencedColumnName = "IdCategoria", nullable = false)
    @ManyToOne(optional = false)
    private Tblcategorias tblcategoriasIdCategoria;
    @JoinColumn(name = "tblusuarios_IdUsuario", referencedColumnName = "IdUsuario", nullable = false)
    @ManyToOne(optional = false)
    private Tblusuarios tblusuariosIdUsuario;

    public Tblactividad() {
    }

    public Tblactividad(Long idActividad) {
        this.idActividad = idActividad;
    }

    public Tblactividad(Long idActividad, String nombre, long idCategoria, long idDepartamento, long idUsuario, short borrado) {
        this.idActividad = idActividad;
        this.nombre = nombre;
        this.idCategoria = idCategoria;
        this.idDepartamento = idDepartamento;
        this.idUsuario = idUsuario;
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

    public Tblcategorias getTblcategoriasIdCategoria() {
        return tblcategoriasIdCategoria;
    }

    public void setTblcategoriasIdCategoria(Tblcategorias tblcategoriasIdCategoria) {
        this.tblcategoriasIdCategoria = tblcategoriasIdCategoria;
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
        return "com.mgl.bitacoradiaria.model.Tblactividad[idActividad=" + idActividad + "]";
    }

}
