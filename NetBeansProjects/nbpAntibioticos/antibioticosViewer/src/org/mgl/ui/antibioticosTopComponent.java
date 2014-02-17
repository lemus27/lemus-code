/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mgl.ui;

import com.mgl.antibioticos.Antibioticos;
import java.util.List;
import java.util.logging.Logger;
import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

//import org.openide.util.ImageUtilities;
import org.netbeans.api.settings.ConvertAsProperties;


/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//org.mgl.ui//antibioticos//EN",
autostore = false)
public final class antibioticosTopComponent extends TopComponent {

    private static antibioticosTopComponent instance;
    /** path to the icon used by the component and its open action */
//    static final String ICON_PATH = "SET/PATH/TO/ICON/HERE";
    private static final String PREFERRED_ID = "antibioticosTopComponent";

    public antibioticosTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(antibioticosTopComponent.class, "CTL_antibioticosTopComponent"));
        setToolTipText(NbBundle.getMessage(antibioticosTopComponent.class, "HINT_antibioticosTopComponent"));
//        setIcon(ImageUtilities.loadImage(ICON_PATH, true));
        EntityManager entityManager = Persistence.createEntityManagerFactory("antibioticosLibraryPU").createEntityManager();
        javax.persistence.Query query =  entityManager.createQuery("SELECT c FROM ANTIBIOTICOS c");
List<Antibioticos> resultList = query.getResultList();
for (Antibioticos c : resultList) {
  jTextArea1.append(c.getCedula() + " (" + c.getDomicilio() + ")" + "\n");
}

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link #findInstance}.
     */
    public static synchronized antibioticosTopComponent getDefault() {
        if (instance == null) {
            instance = new antibioticosTopComponent();
        }
        return instance;
    }

    /**
     * Obtain the antibioticosTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized antibioticosTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(antibioticosTopComponent.class.getName()).warning(
                    "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof antibioticosTopComponent) {
            return (antibioticosTopComponent) win;
        }
        Logger.getLogger(antibioticosTopComponent.class.getName()).warning(
                "There seem to be multiple components with the '" + PREFERRED_ID
                + "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    Object readProperties(java.util.Properties p) {
        if (instance == null) {
            instance = this;
        }
        instance.readPropertiesImpl(p);
        return instance;
    }

    private void readPropertiesImpl(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }
}
