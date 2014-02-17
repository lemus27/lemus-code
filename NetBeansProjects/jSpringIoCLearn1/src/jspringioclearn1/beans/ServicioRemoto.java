/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jspringioclearn1.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author mike
 */
@Service(value="servicioRemoto")//APRENDER:cada Beans-spring  tiene unId  asociado= nombre de clase con primera letra minuscula
public class ServicioRemoto
{
    @Value(value="5")
    private Integer repeticiones;
    private Proceso proceso;

    public ServicioRemoto()
    {
    }

    @Autowired
    public ServicioRemoto(@Qualifier("calculo")Proceso proceso)
    {
        this.proceso = proceso;
    }

    public Object consultaDato()
    {

    StringBuilder stringBuilder = new StringBuilder();

    for(int i = 0; i < repeticiones; i++)
    {
        stringBuilder.append(i + 1).append(" ").append(proceso.ejecuta()).append("\n");
    }

    return stringBuilder.toString();
    }


public void setRepeticiones(Integer repeticiones)
{
 this.repeticiones = repeticiones;
}
}
