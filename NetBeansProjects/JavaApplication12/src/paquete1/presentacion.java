package paquete1;
import javax.swing.JOptionPane;

// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.B8B2836B-B724-ED59-3153-A043611E53E6]
// </editor-fold>  
public class presentacion { 
    arreglobidi arrys;
       // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.026BAEB6-3CB8-789A-9D71-1041AFDC875D]
    // </editor-fold> 
    public presentacion () {
        arrys= new arreglobidi();
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A42A9490-0DD6-FEEC-D03E-F2812CE3DD9A]
    // </editor-fold> 
  public void Capturar() {
       for (int alumno=0;alumno<3;alumno++)
   {
       for (int unidad=0;unidad<3;unidad++)
       {
           int calificacion=Integer.parseInt(JOptionPane.showInputDialog(null,"Calificacion "));
           arrys.agregar(alumno, unidad, calificacion);
       }
   }
    }
        // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A42A9490-0DD6-FEEC-D03E-F2812CE3DD9A]
    // </editor-fold> 
    public void Mostrar() {

        for (int alumno=0;alumno<3;alumno++)
        {
            System.out.println("Alumno "+(alumno+1));
            for(int unidad=0;unidad<3;unidad++)
            {
                    System.out.println(arrys.calificacion(alumno,unidad));

            }
       }
    }
   
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A42A9490-0DD6-FEEC-D03E-F2812CE3DD9A]
    // </editor-fold> 
  public void Promedio() {
          int suma=0;
     int promedios=0;
     for (int alumno=0;alumno<3;alumno++)
     {
       for (int unidad=0;unidad<3;unidad++)
       {
           suma=suma+arrys.calificacion(alumno, unidad);
        promedios=0;
       }

       promedios=suma/3;
       suma=0;
       System.out.println("su promedio es: "+(alumno+1)+" = "+promedios);

   }
    }

     // <editor-fold defaultstate="collapsed" desc=" UML Marker ">
    // #[regen=yes,id=DCE.A42A9490-0DD6-FEEC-D03E-F2812CE3DD9A]
    // </editor-fold>
   public void  aprobados() {
        int suma=0,aprobados= 0,totalaprobados=0;
     int promedios=0;
     String salida;
     for (int alumno=0;alumno<3;alumno++)
     {
         
       for (int unidad=0;unidad<3;unidad++)
       {
           suma=suma+arrys.calificacion(alumno, unidad);
        promedios=0;
       }

       promedios=suma/3;
       suma=0;
       if(promedios>70)
      aprobados = aprobados + 1; 
   
   }
     System.out.println("total de aprobados="+aprobados);
 
    }
      // <editor-fold defaultstate="collapsed" desc=" UML Marker ">
    // #[regen=yes,id=DCE.A42A9490-0DD6-FEEC-D03E-F2812CE3DD9A]
    // </editor-fold>
   public void reprobados () {
        int suma=0,reprobados=0;
     int promedios=0;
     String salida;
     for (int alumno=0;alumno<3;alumno++)
     {

       for (int unidad=0;unidad<3;unidad++)
       {
           suma=suma+arrys.calificacion(alumno, unidad);
        promedios=0;
       }

       promedios=suma/3;
       suma=0;
       if(promedios<70)
    reprobados =reprobados + 1;

   }
     System.out.println("total de reprobados="+reprobados);


    }
   // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A42A9490-0DD6-FEEC-D03E-F2812CE3DD9A]
    // </editor-fold> 
  public void alto()
    {
        int suma=0,reprobados=0;
     int promedios=0;
     int max;
     for (int alumno=0;alumno<3;alumno++)
     {

       for (int unidad=0;unidad<3;unidad++)
       {

       max=100;
           if(arrys.calificacion(alumno, unidad)< max)
       max=arrys.calificacion(alumno, unidad);
         
      System.out.println("total de reprobados="+max);
      
   

   }
     }
    }
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A42A9490-0DD6-FEEC-D03E-F2812CE3DD9A]
    // </editor-fold> 
    public arreglobidi getObjArr () {
        return objArr;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B681D38F-A55F-ED09-C3CC-BA40BD2C1CCB]
    // </editor-fold> 
    public void setObjArr (arreglobidi val) {
         this.objArr = val;
    }

 // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.EB2E02F0-7DC6-148C-403D-FBEA57CBDD5D]
    // </editor-fold> 
    private arreglobidi objArr;

}

  
