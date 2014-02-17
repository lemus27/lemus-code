package paquete1;



// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.A13E5B45-3192-3B4E-5408-F89A4D706DE4]
// </editor-fold> 
public class arreglo {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A56F6C30-D911-706D-22AF-CB7E6C249F62]
    // </editor-fold> 
    private Integer[] array;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.25CBEE92-1220-1C68-86FB-82AE26D6B495]
    // </editor-fold> 
    private int Tamano=2;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.77391EAA-A129-2928-7A41-B3AA7CB4D3EA]
    // </editor-fold> 
    public arreglo () {
           this.array=new Integer[this.Tamano];
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.01907D62-585C-0665-DD73-E0F92C2C26B0]
    // </editor-fold> 
    public int getTamano () {
        return Tamano;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.8A18EC11-78CF-16C3-3EA5-35BEEBB81B41]
    // </editor-fold> 
    public void setTamano (int val) {
        this.Tamano = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.04B475E6-FD18-EEE6-8A2A-D6B72A6DA62F]
    // </editor-fold> 
    public Integer[] getArray () {
        return array;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.6A90BCC0-4E10-3F42-E1CD-566AF7F98F2E]
    // </editor-fold> 
    public void setArray (Integer[] val) {
        this.array = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.15BAA492-3F62-448F-4B6E-8381BC0F719B]
    // </editor-fold> 
    public void crear () {

              this.array=new Integer[this.Tamano];

    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.8285D350-92E5-8EFC-B173-ECF052B598DE]
    // </editor-fold> 
    public void inicializar (int valores) {
          for( int i=0; i<=Tamano; i++)
        {
              this.array[i]=0;
        }

    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.CAB61919-9C7C-A7E4-99B5-387F5DED0DC4]
    // </editor-fold> 
    public int consultarAtPosicion (int pos) {
        return this.array[pos];
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C0392138-B99E-9900-E68A-0E63A582F77A]
    // </editor-fold> 
    public void insertarAtPosicion (int pos, int valor) {
         this.array[pos]=valor;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.DCD52D14-59E1-F15A-98A3-E9877F5EAB35]
    // </editor-fold> 
    public void modificarAtPosicion (int pos, int valor) {
        this.array[pos]=valor;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.69BD41D2-8580-6D8D-C619-00D9B480BC65]
    // </editor-fold> 
    public void eliminarAtPosicion (int pos) {
        this.array[pos]=null;
    }

}

