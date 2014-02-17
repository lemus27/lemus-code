package com.myapp.echo2;

import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.app.ContentPane;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.Window;

public class MyApplication extends ApplicationInstance  {
    
    /** Creates a new instance of MyApplication */
    public MyApplication() {
    }
    
    /**
     * @see nextapp.echo2.app.ApplicationInstance#init()
     */
    public Window init() {
        Window mainWindow = new Window();
        mainWindow.setTitle("Echo2 Application");
        ContentPane pane = new ContentPane();
        Label label = new Label("Echo2 Application");
        label.setFont(new Font(Font.ARIAL, Font.BOLD, new Extent(30)));
        pane.add(label);
        mainWindow.setContent(pane);
        return mainWindow;
    }
}
