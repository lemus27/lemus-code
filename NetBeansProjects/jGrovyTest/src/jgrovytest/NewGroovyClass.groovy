/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgrovytest

/**
 *
 * @author mike
 */
class MiClase {
    def x = "Esto es un String"

    void metodo() {
        println x.class
        x = 12
        println x.class
    }
}


class MiInteger {
  def valor = 0;

def MiInteger(int valor) {
        this.valor = valor
    }

   def plus(MiInteger otro) {
        valor + otro.valor
    }
}


public class Main{
    public static void main (args)
    {def mc1 = new MiInteger(8)
def mc2 = new MiInteger(7)
println mc1 + mc2

def lista = []
// ...
if(lista) {
    println "ArrayList con elementos"
} else {
    println "ArrayList vacio"
}

    }
}