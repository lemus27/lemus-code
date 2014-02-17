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
@Table(name = "tblusuarios_crea_usuarios")
@NamedQueries({@NamedQuery(name = "TblusuariosCreaUsuarios.findAll", query = "SELECT t FROM TblusuariosCreaUsuarios t"), @NamedQuery(name = "TblusuariosCreaUsuarios.findByIdCreador", query = "SELECT t FROM TblusuariosCreaUsuarios t WHERE t.idCreador = :idCreador")})
public class TblusuariosCreaUsuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdCreador")
    private Long idCreador;
    @JoinColumn(name = "IdUsuarioCreador", referencedColumnName = "IdUsuario")
    @ManyToOne(optional = false)
    private Tblusuarios idUsuarioCreador;
    @JoinColumn(name = "tblusuarios_IdUsuario", referencedColumnName = "IdUsuario")
    @ManyToOne(optional = false)
    private Tblusuarios tblusuariosIdUsuario;

    public TblusuariosCreaUsuarios() {
    }

    public TblusuariosCreaUsuarios(Long idCreador) {
        this.idCreador = idCreador;
    }

    public Long getIdCreador() {
        return idCreador;
    }

    public void setIdCreador(Long idCreador) {
        this.idCreador = idCreador;
    }

    public Tblusuarios getIdUsuarioCreador() {
        return idUsuarioCreador;
    }

    public void setIdUsuarioCreador(Tblusuarios idUsuarioCreador) {
        this.idUsuarioCreador = idUsuarioCreador;
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
        hash += (idCreador != null ? idCreador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblusuariosCreaUsuarios)) {
            return false;
        }
        TblusuariosCreaUsuarios other = (TblusuariosCreaUsuarios) object;
        if ((this.idCreador == null && other.idCreador != null) || (this.idCreador != null && !this.idCreador.equals(other.idCreador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bitacoradiariajpa.TblusuariosCreaUsuarios[idCreador=" + idCreador + "]";
    }

}
