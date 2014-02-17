/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package systemtrayappnb;
// Primero los imports:
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;


/**
 *
 * @author Administrador
 */
    public class Main
{

////Luego las variables:
//java.awt.TrayIcon trayIcon;
//java.awt .SystemTray tray;
//
//
////Y ahora solo tenemos que ejecutar el metodo iconTray:
//public void IconTray()
//{
///*Se verifica si el sistema soporta los try icons*/
//if (SystemTray.isSupported()) {
//
//tray = SystemTray.getSystemTray();
//
////Se asigna la imagen que del tray icon
//ImageIcon im = new ImageIcon(this.getClass().getResource("/iprouter/question.png"));
//
////Este listener permite salir de la aplicacion
//            ActionListener salirListener = new ActionListener() {
//
//                public void actionPerformed(ActionEvent e) {
//                    System.out.println("Cerrando...");
//                    System.exit(0);
//                }
//            };
//
//            /*Creamos un acction listener que se ejecuta cuando le damos
//            doble click al try icon*/
//            ActionListener abrirListener = new ActionListener() {
//
//                public void actionPerformed(ActionEvent e) {
//                    trayIconActionPerformed(e);
//                }
//            };
//
////Aquí se crea un popup menu
//            PopupMenu popup = new PopupMenu();
////Se agrega la opción de salir
//            MenuItem salirItem = new MenuItem("Salir");
//
////Se le asigna al item del popup el listener para salir de la app
//            salirItem.addActionListener(salirListener);
//
//            popup.add(salirItem);
//
//            /*Aqui creamos una instancia del tray icon y asignamos
//            La imagen, el nombre del tray icon y el popup*/
//            trayIcon = new TrayIcon(im.getImage(), "trayiconnnr", popup);
//
//            trayIcon.setImageAutoSize(true);
//            trayIcon.addActionListener(abrirListener);
//
//            try {
//                tray.add(trayIcon);
//
//            } catch (AWTException ex) {
//                ex.printStackTrace();
//            }
//
//        } else {
//            System.err.println("System tray is currently not supported.");
//        }
//    }
////--------------------------------------
//    private void trayIconActionPerformed(java.awt.event.ActionEvent evt) {
//        this.setVisible(true);
//        this.toFront();
//        tray.remove(trayIcon);
//    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new EjemploSystemTray();

    }
}
