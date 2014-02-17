/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jspringioclearn1;

import jspringioclearn1.beans.ServicioRemoto;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 *
 * @author mike
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

/*obteniendo los beans desde el  arhivo de  configuracion xml
    BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));

    ServicioRemoto servicio = beanFactory.getBean("servicioRemoto", ServicioRemoto.class);

    System.out.println("El valor es " + servicio.consultaDato());

    */
/*
 * obteniendo los beans sin  el  arhivo de  configuracion xml
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

    ServicioRemoto servicio = applicationContext.getBean("servicioRemoto", ServicioRemoto.class);

    System.out.println("El valor es " + servicio.consultaDato());
    */
      
     // usandoi  Inyeccion d  dDependencia DI  sin usar  archivo xml
     ApplicationContext applicationContext = new AnnotationConfigApplicationContext("jspringioclearn1.beans");

    ServicioRemoto servicio = applicationContext.getBean("servicioRemoto", ServicioRemoto.class);

    System.out.println("El valor es " + servicio.consultaDato());


    }

}
