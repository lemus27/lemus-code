/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mgl.antibioticos;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "ANTIBIOTICOS", catalog = "", schema = "APP")
@NamedQueries({
    @NamedQuery(name = "Antibioticos.findAll", query = "SELECT a FROM Antibioticos a"),
    @NamedQuery(name = "Antibioticos.findByFecha", query = "SELECT a FROM Antibioticos a WHERE a.fecha = :fecha"),
    @NamedQuery(name = "Antibioticos.findByNumfactura", query = "SELECT a FROM Antibioticos a WHERE a.numfactura = :numfactura"),
    @NamedQuery(name = "Antibioticos.findByLote", query = "SELECT a FROM Antibioticos a WHERE a.lote = :lote"),
    @NamedQuery(name = "Antibioticos.findByCantadquirida", query = "SELECT a FROM Antibioticos a WHERE a.cantadquirida = :cantadquirida"),
    @NamedQuery(name = "Antibioticos.findByCantdesechada", query = "SELECT a FROM Antibioticos a WHERE a.cantdesechada = :cantdesechada"),
    @NamedQuery(name = "Antibioticos.findByStock", query = "SELECT a FROM Antibioticos a WHERE a.stock = :stock"),
    @NamedQuery(name = "Antibioticos.findByCedula", query = "SELECT a FROM Antibioticos a WHERE a.cedula = :cedula"),
    @NamedQuery(name = "Antibioticos.findByIdantibioticos", query = "SELECT a FROM Antibioticos a WHERE a.idantibioticos = :idantibioticos"),
    @NamedQuery(name = "Antibioticos.findByFechacaducidad", query = "SELECT a FROM Antibioticos a WHERE a.fechacaducidad = :fechacaducidad")})
public class Antibioticos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "NUMFACTURA")
    private BigInteger numfactura;
    @Column(name = "LOTE", length = 15)
    private String lote;
    @Column(name = "CANTADQUIRIDA")
    private BigInteger cantadquirida;
    @Column(name = "CANTDESECHADA")
    private BigInteger cantdesechada;
    @Column(name = "STOCK")
    private BigInteger stock;
    @Lob
    @Column(name = "NOMBREPRESCRIPTOR", length = 32700)
    private String nombreprescriptor;
    @Column(name = "CEDULA")
    private BigInteger cedula;
    @Lob
    @Column(name = "DOMICILIO", length = 32700)
    private String domicilio;
    @Lob
    @Column(name = "OBSERVACIONES", length = 32700)
    private String observaciones;
    @Id
    @Basic(optional = false)
    @Column(name = "IDANTIBIOTICOS", nullable = false)
    private Long idantibioticos;
    @Column(name = "FECHACADUCIDAD")
    @Temporal(TemporalType.DATE)
    private Date fechacaducidad;

    public Antibioticos() {
    }

    public Antibioticos(Long idantibioticos) {
        this.idantibioticos = idantibioticos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigInteger getNumfactura() {
        return numfactura;
    }

    public void setNumfactura(BigInteger numfactura) {
        this.numfactura = numfactura;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public BigInteger getCantadquirida() {
        return cantadquirida;
    }

    public void setCantadquirida(BigInteger cantadquirida) {
        this.cantadquirida = cantadquirida;
    }

    public BigInteger getCantdesechada() {
        return cantdesechada;
    }

    public void setCantdesechada(BigInteger cantdesechada) {
        this.cantdesechada = cantdesechada;
    }

    public BigInteger getStock() {
        return stock;
    }

    public void setStock(BigInteger stock) {
        this.stock = stock;
    }

    public String getNombreprescriptor() {
        return nombreprescriptor;
    }

    public void setNombreprescriptor(String nombreprescriptor) {
        this.nombreprescriptor = nombreprescriptor;
    }

    public BigInteger getCedula() {
        return cedula;
    }

    public void setCedula(BigInteger cedula) {
        this.cedula = cedula;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Long getIdantibioticos() {
        return idantibioticos;
    }

    public void setIdantibioticos(Long idantibioticos) {
        this.idantibioticos = idantibioticos;
    }

    public Date getFechacaducidad() {
        return fechacaducidad;
    }

    public void setFechacaducidad(Date fechacaducidad) {
        this.fechacaducidad = fechacaducidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idantibioticos != null ? idantibioticos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Antibioticos)) {
            return false;
        }
        Antibioticos other = (Antibioticos) object;
        if ((this.idantibioticos == null && other.idantibioticos != null) || (this.idantibioticos != null && !this.idantibioticos.equals(other.idantibioticos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mgl.antibioticos.Antibioticos[idantibioticos=" + idantibioticos + "]";
    }

}
