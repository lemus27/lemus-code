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
import javax.persistence.Table;

/**
 *
 * @author mike
 */
@Entity
@Table(name = "tblusuarios_crea_perfiles")
@NamedQueries({@NamedQuery(name = "TblusuariosCreaPerfiles.findAll", query = "SELECT t FROM TblusuariosCreaPerfiles t"), @NamedQuery(name = "TblusuariosCreaPerfiles.findByIdCreador", query = "SELECT t FROM TblusuariosCreaPerfiles t WHERE t.idCreador = :idCreador")})
public class TblusuariosCreaPerfiles implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdCreador")
    private Long idCreador;
    @JoinColumn(name = "IdUsuarioCrea", referencedColumnName = "IdUsuario")
    @ManyToOne(optional = false)
    private Tblusuarios idUsuarioCrea;
    @JoinColumn(name = "IdPerfilNuevo", referencedColumnName = "IdPerfil")
    @ManyToOne(optional = false)
    private Tblperfiles idPerfilNuevo;

    public TblusuariosCreaPerfiles() {
    }

    public TblusuariosCreaPerfiles(Long idCreador) {
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

    public Tblperfiles getIdPerfilNuevo() {
        return idPerfilNuevo;
    }

    public void setIdPerfilNuevo(Tblperfiles idPerfilNuevo) {
        this.idPerfilNuevo = idPerfilNuevo;
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
        if (!(object instanceof TblusuariosCreaPerfiles)) {
            return false;
        }
        TblusuariosCreaPerfiles other = (TblusuariosCreaPerfiles) object;
        if ((this.idCreador == null && other.idCreador != null) || (this.idCreador != null && !this.idCreador.equals(other.idCreador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bitacoradiariajpa.TblusuariosCreaPerfiles[idCreador=" + idCreador + "]";
    }

}
