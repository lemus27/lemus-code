/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mgl.bitacoradiaria.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "tblusuarios_crea_categorias")
@NamedQueries({@NamedQuery(name = "TblusuariosCreaCategorias.findAll", query = "SELECT t FROM TblusuariosCreaCategorias t"), @NamedQuery(name = "TblusuariosCreaCategorias.findByIdCreador", query = "SELECT t FROM TblusuariosCreaCategorias t WHERE t.idCreador = :idCreador")})
public class TblusuariosCreaCategorias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IdCreador")
    private String idCreador;
    @JoinColumn(name = "IdUsuarioCrea", referencedColumnName = "IdUsuario")
    @ManyToOne(optional = false)
    private Tblusuarios idUsuarioCrea;
    @JoinColumn(name = "IdCategoriaNuevo", referencedColumnName = "IdCategoria")
    @ManyToOne(optional = false)
    private Tblcategorias idCategoriaNuevo;

    public TblusuariosCreaCategorias() {
    }

    public TblusuariosCreaCategorias(String idCreador) {
        this.idCreador = idCreador;
    }

    public String getIdCreador() {
        return idCreador;
    }

    public void setIdCreador(String idCreador) {
        this.idCreador = idCreador;
    }

    public Tblusuarios getIdUsuarioCrea() {
        return idUsuarioCrea;
    }

    public void setIdUsuarioCrea(Tblusuarios idUsuarioCrea) {
        this.idUsuarioCrea = idUsuarioCrea;
    }

    public Tblcategorias getIdCategoriaNuevo() {
        return idCategoriaNuevo;
    }

    public void setIdCategoriaNuevo(Tblcategorias idCategoriaNuevo) {
        this.idCategoriaNuevo = idCategoriaNuevo;
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
        if (!(object instanceof TblusuariosCreaCategorias)) {
            return false;
        }
        TblusuariosCreaCategorias other = (TblusuariosCreaCategorias) object;
        if ((this.idCreador == null && other.idCreador != null) || (this.idCreador != null && !this.idCreador.equals(other.idCreador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bitacoradiariajpa.TblusuariosCreaCategorias[idCreador=" + idCreador + "]";
    }

}
