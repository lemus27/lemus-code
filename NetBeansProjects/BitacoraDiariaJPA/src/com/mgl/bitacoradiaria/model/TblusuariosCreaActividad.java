/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mgl.bitacoradiaria.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author mike
 */
@Entity
@Table(name = "tblusuarios_crea_actividad")
@NamedQueries({@NamedQuery(name = "TblusuariosCreaActividad.findAll", query = "SELECT t FROM TblusuariosCreaActividad t"), @NamedQuery(name = "TblusuariosCreaActividad.findByIdCreador", query = "SELECT t FROM TblusuariosCreaActividad t WHERE t.idCreador = :idCreador")})
public class TblusuariosCreaActividad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdCreador")
    private Long idCreador;
    @JoinColumn(name = "IdUsuarioCrea", referencedColumnName = "IdUsuario")
    @ManyToOne(optional = false)
    private Tblusuarios idUsuarioCrea;
    @JoinColumn(name = "IdActividadNuevo", referencedColumnName = "IdActividad")
    @OneToOne(optional = false)
    private Tblactividad idActividadNuevo;

    public TblusuariosCreaActividad() {
    }

    public TblusuariosCreaActividad(Long idCreador) {
        this.idCreador = idCreador;
    }

    public Long getIdCreador() {
        return idCreador;
    }

    public void setIdCreador(Long idCreador) {
        this.idCreador = idCreador;
    }

    public Tblusuarios getIdUsuarioCrea() {
        return idUsuarioCrea;
    }

    public void setIdUsuarioCrea(Tblusuarios idUsuarioCrea) {
        this.idUsuarioCrea = idUsuarioCrea;
    }

    public Tblactividad getIdActividadNuevo() {
        return idActividadNuevo;
    }

    public void setIdActividadNuevo(Tblactividad idActividadNuevo) {
        this.idActividadNuevo = idActividadNuevo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCreador != null ? idCreador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblusuariosCreaActividad)) {
            return false;
        }
        TblusuariosCreaActividad other = (TblusuariosCreaActividad) object;
        if ((this.idCreador == null && other.idCreador != null) || (this.idCreador != null && !this.idCreador.equals(other.idCreador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bitacoradiariajpa.TblusuariosCreaActividad[idCreador=" + idCreador + "]";
    }

}
