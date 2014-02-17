package paquete1;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.B8B2836B-B724-ED59-3153-A043611E53E6]
// </editor-fold> 
public class presentacion {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9B56AC2D-995C-698B-4F00-65B3C4B8D6D2]
    // </editor-fold> 
    private arreglo objArr;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.01E92EC4-C82C-0F5E-FC2C-26A2BD418891]
    // </editor-fold> 
    private int suma = 0;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.026BAEB6-3CB8-789A-9D71-1041AFDC875D]
    // </editor-fold> 
    public presentacion () {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.A42A9490-0DD6-FEEC-D03E-F2812CE3DD9A]
    // </editor-fold> 
    public arreglo getObjArr () {
        return objArr;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.B681D38F-A55F-ED09-C3CC-BA40BD2C1CCB]
    // </editor-fold> 
    public void setObjArr (arreglo val) {
        this.objArr = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.3358C49C-9BAE-0262-0A81-FD92647A16DB]
    // </editor-fold> 
    public int getSuma () {
        return suma;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.DE7601F6-62F1-F691-3945-1B3997B973BB]
    // </editor-fold> 
   public void setSuma (int val) {
        this.suma = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.631A46B6-BF7A-6105-7C91-F5DAF935DE9D]
    // </editor-fold> 
    public void calcularSuma () {
        int tam=this.objArr.getTamano();
        for (int i=0;i<tam;i++)
        {
       this.suma= suma+this.objArr.consultarAtPosicion(i);
    }
       
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.FF583EAF-079F-13D7-D481-EF8CE02A7074]
    // </editor-fold> 
    public void MostrarArreglo () {
        int tam=this.objArr.getTamano();
        int valor=0;
        for (int i=0;i<tam;i++)
        {
      valor= this.objArr.consultarAtPosicion(i);
        System.out.println("el valor del arreglo en la posicion["+i+"] es :"+valor);
        }

    }
     public void MostrarSuma() {


        System.out.println("la suma delo elemntos del arreglo es:"+this.getSuma());


    }

}

