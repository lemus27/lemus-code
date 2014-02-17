/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package saf10antibionticos;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "ANTIBIOTICOS", catalog = "", schema = "APP")
@NamedQueries({
    @NamedQuery(name = "Antibioticos.findAll", query = "SELECT a FROM Antibioticos a"),
    @NamedQuery(name = "Antibioticos.findByIdantibioticos", query = "SELECT a FROM Antibioticos a WHERE a.idantibioticos = :idantibioticos"),
    @NamedQuery(name = "Antibioticos.findByProcedencia", query = "SELECT a FROM Antibioticos a WHERE a.procedencia = :procedencia"),
    @NamedQuery(name = "Antibioticos.findByNumfactura", query = "SELECT a FROM Antibioticos a WHERE a.numfactura = :numfactura"),
    @NamedQuery(name = "Antibioticos.findByLote", query = "SELECT a FROM Antibioticos a WHERE a.lote = :lote"),
    @NamedQuery(name = "Antibioticos.findByFechacaducidad", query = "SELECT a FROM Antibioticos a WHERE a.fechacaducidad = :fechacaducidad"),
    @NamedQuery(name = "Antibioticos.findByFecha", query = "SELECT a FROM Antibioticos a WHERE a.fecha = :fecha"),
    @NamedQuery(name = "Antibioticos.findByCantadquirida", query = "SELECT a FROM Antibioticos a WHERE a.cantadquirida = :cantadquirida"),
    @NamedQuery(name = "Antibioticos.findByCantdesechada", query = "SELECT a FROM Antibioticos a WHERE a.cantdesechada = :cantdesechada"),
    @NamedQuery(name = "Antibioticos.findByStock", query = "SELECT a FROM Antibioticos a WHERE a.stock = :stock"),
    @NamedQuery(name = "Antibioticos.findByNombreprescriptor", query = "SELECT a FROM Antibioticos a WHERE a.nombreprescriptor = :nombreprescriptor"),
    @NamedQuery(name = "Antibioticos.findByCedula", query = "SELECT a FROM Antibioticos a WHERE a.cedula = :cedula"),
    @NamedQuery(name = "Antibioticos.findByDomicilio", query = "SELECT a FROM Antibioticos a WHERE a.domicilio = :domicilio"),
    @NamedQuery(name = "Antibioticos.findByNombre", query = "SELECT a FROM Antibioticos a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Antibioticos.findByObservaciones", query = "SELECT a FROM Antibioticos a WHERE a.observaciones = :observaciones"),
    @NamedQuery(name = "Antibioticos.findByFechaventa", query = "SELECT a FROM Antibioticos a WHERE a.fechaventa = :fechaventa")})
public class Antibioticos implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDANTIBIOTICOS", nullable = false)
    private Long idantibioticos;
    @Column(name = "PROCEDENCIA", length = 200)
    private String procedencia;
    @Column(name = "NUMFACTURA")
    private BigInteger numfactura;
    @Column(name = "LOTE", length = 50)
    private String lote;
    @Column(name = "FECHACADUCIDAD", length = 10)
    private String fechacaducidad;
    @Column(name = "FECHA", length = 10)
    private String fecha;
    @Column(name = "CANTADQUIRIDA")
    private BigInteger cantadquirida;
    @Column(name = "CANTDESECHADA")
    private BigInteger cantdesechada;
    @Column(name = "STOCK")
    private BigInteger stock;
    @Column(name = "NOMBREPRESCRIPTOR", length = 100)
    private String nombreprescriptor;
    @Column(name = "CEDULA")
    private BigInteger cedula;
    @Column(name = "DOMICILIO", length = 200)
    private String domicilio;
    @Column(name = "NOMBRE", length = 100)
    private String nombre;
    @Column(name = "OBSERVACIONES", length = 200)
    private String observaciones;
    @Column(name = "FECHAVENTA", length = 1000)
    private String fechaventa;

    public Antibioticos() {
    }

    public Antibioticos(Long idantibioticos) {
        this.idantibioticos = idantibioticos;
    }

    public Long getIdantibioticos() {
        return idantibioticos;
    }

    public void setIdantibioticos(Long idantibioticos) {
        Long oldIdantibioticos = this.idantibioticos;
        this.idantibioticos = idantibioticos;
        changeSupport.firePropertyChange("idantibioticos", oldIdantibioticos, idantibioticos);
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        String oldProcedencia = this.procedencia;
        this.procedencia = procedencia;
        changeSupport.firePropertyChange("procedencia", oldProcedencia, procedencia);
    }

    public BigInteger getNumfactura() {
        return numfactura;
    }

    public void setNumfactura(BigInteger numfactura) {
        BigInteger oldNumfactura = this.numfactura;
        this.numfactura = numfactura;
        changeSupport.firePropertyChange("numfactura", oldNumfactura, numfactura);
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        String oldLote = this.lote;
        this.lote = lote;
        changeSupport.firePropertyChange("lote", oldLote, lote);
    }

    public String getFechacaducidad() {
        return fechacaducidad;
    }

    public void setFechacaducidad(String fechacaducidad) {
        String oldFechacaducidad = this.fechacaducidad;
        this.fechacaducidad = fechacaducidad;
        changeSupport.firePropertyChange("fechacaducidad", oldFechacaducidad, fechacaducidad);
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        String oldFecha = this.fecha;
        this.fecha = fecha;
        changeSupport.firePropertyChange("fecha", oldFecha, fecha);
    }

    public BigInteger getCantadquirida() {
        return cantadquirida;
    }

    public void setCantadquirida(BigInteger cantadquirida) {
        BigInteger oldCantadquirida = this.cantadquirida;
        this.cantadquirida = cantadquirida;
        changeSupport.firePropertyChange("cantadquirida", oldCantadquirida, cantadquirida);
    }

    public BigInteger getCantdesechada() {
        return cantdesechada;
    }

    public void setCantdesechada(BigInteger cantdesechada) {
        BigInteger oldCantdesechada = this.cantdesechada;
        this.cantdesechada = cantdesechada;
        changeSupport.firePropertyChange("cantdesechada", oldCantdesechada, cantdesechada);
    }

    public BigInteger getStock() {
        return stock;
    }

    public void setStock(BigInteger stock) {
        BigInteger oldStock = this.stock;
        this.stock = stock;
        changeSupport.firePropertyChange("stock", oldStock, stock);
    }

    public String getNombreprescriptor() {
        return nombreprescriptor;
    }

    public void setNombreprescriptor(String nombreprescriptor) {
        String oldNombreprescriptor = this.nombreprescriptor;
        this.nombreprescriptor = nombreprescriptor;
        changeSupport.firePropertyChange("nombreprescriptor", oldNombreprescriptor, nombreprescriptor);
    }

    public BigInteger getCedula() {
        return cedula;
    }

    public void setCedula(BigInteger cedula) {
        BigInteger oldCedula = this.cedula;
        this.cedula = cedula;
        changeSupport.firePropertyChange("cedula", oldCedula, cedula);
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        String oldDomicilio = this.domicilio;
        this.domicilio = domicilio;
        changeSupport.firePropertyChange("domicilio", oldDomicilio, domicilio);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        String oldNombre = this.nombre;
        this.nombre = nombre;
        changeSupport.firePropertyChange("nombre", oldNombre, nombre);
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        String oldObservaciones = this.observaciones;
        this.observaciones = observaciones;
        changeSupport.firePropertyChange("observaciones", oldObservaciones, observaciones);
    }

    public String getFechaventa() {
        return fechaventa;
    }

    public void setFechaventa(String fechaventa) {
        String oldFechaventa = this.fechaventa;
        this.fechaventa = fechaventa;
        changeSupport.firePropertyChange("fechaventa", oldFechaventa, fechaventa);
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
        return "saf10antibionticos.Antibioticos[idantibioticos=" + idantibioticos + "]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
