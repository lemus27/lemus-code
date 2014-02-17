/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.integrator;

/**
 *
 * @author Edson
 */
public class Valor {
        private  Integer id;
        private String valor;

         public Valor(Integer id, String valor) {
             this.id = id;
             this.valor = valor;
         }

         public Integer getId() {
             return id;
         }

         public String getValor() {
             return valor;
         }

         @Override
         public String toString() {
             return valor;
         }
}
