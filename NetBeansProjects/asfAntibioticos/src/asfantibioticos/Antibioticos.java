/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package asfantibioticos;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
import javax.persistence.Transient;

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
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "NUMFACTURA")
    private BigInteger numfactura;
    @Column(name = "LOTE")
    private String lote;
    @Column(name = "CANTADQUIRIDA")
    private BigInteger cantadquirida;
    @Column(name = "CANTDESECHADA")
    private BigInteger cantdesechada;
    @Column(name = "STOCK")
    private BigInteger stock;
    @Lob
    @Column(name = "NOMBREPRESCRIPTOR")
    private String nombreprescriptor;
    @Column(name = "CEDULA")
    private BigInteger cedula;
    @Lob
    @Column(name = "DOMICILIO")
    private String domicilio;
    @Lob
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Id
    @Basic(optional = false)
    @Column(name = "IDANTIBIOTICOS")
    private Long idantibioticos;
    @Column(name = "FECHACADUCIDAD")
    @Temporal(TemporalType.DATE)
    private Date fechacaducidad;
    @Lob
    @Column(name = "NOMBRE")
    private String nombre;

    public Antibioticos() {
    }

    public Antibioticos(Long idantibioticos) {
        this.idantibioticos = idantibioticos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        Date oldFecha = this.fecha;
        this.fecha = fecha;
        changeSupport.firePropertyChange("fecha", oldFecha, fecha);
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        String oldObservaciones = this.observaciones;
        this.observaciones = observaciones;
        changeSupport.firePropertyChange("observaciones", oldObservaciones, observaciones);
    }

    public Long getIdantibioticos() {
        return idantibioticos;
    }

    public void setIdantibioticos(Long idantibioticos) {
        Long oldIdantibioticos = this.idantibioticos;
        this.idantibioticos = idantibioticos;
        changeSupport.firePropertyChange("idantibioticos", oldIdantibioticos, idantibioticos);
    }

    public Date getFechacaducidad() {
        return fechacaducidad;
    }

    public void setFechacaducidad(Date fechacaducidad) {
        Date oldFechacaducidad = this.fechacaducidad;
        this.fechacaducidad = fechacaducidad;
        changeSupport.firePropertyChange("fechacaducidad", oldFechacaducidad, fechacaducidad);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        String oldNombre = this.nombre;
        this.nombre = nombre;
        changeSupport.firePropertyChange("nombre", oldNombre, nombre);
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
        return "asfantibioticos.Antibioticos[idantibioticos=" + idantibioticos + "]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
