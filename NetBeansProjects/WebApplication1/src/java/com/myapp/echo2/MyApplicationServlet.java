package com.myapp.echo2;

import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.webcontainer.WebContainerServlet;

public class MyApplicationServlet extends WebContainerServlet {
    
     /**
     * @see nextapp.echo2.webcontainer.WebContainerServlet#newApplicationInstance()
     */
    public ApplicationInstance newApplicationInstance() {
        return new MyApplication();
    }

}

