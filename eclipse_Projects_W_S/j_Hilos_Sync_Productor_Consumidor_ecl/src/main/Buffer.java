

package main;

public class Buffer {
  private char contenido;
  private boolean disponible=false;
  public Buffer() {
  }
/*  public char recoger(){
    if(disponible){
        disponible=false;
        return contenido;
    }
    return ('\t');
  }
  public void poner(char c){
    contenido=c;
    disponible=true;
  }     */

  public synchronized char recoger(){
    while(disponible==false){
        try{
            wait();
        }catch(InterruptedException ex){}
    }
    disponible=false;
    notify();
    return contenido;
  }
  public synchronized void poner(char valor){
     while(disponible==true){
        try{
            wait();
        }catch(InterruptedException ex){}
    }
    contenido=valor;
    disponible=true;
    notify();
  }
    
}