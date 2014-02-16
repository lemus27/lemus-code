package main;

import javax.swing.JMenuItem;

import java.awt.event.*;
import java.io.File;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.JPanel;

import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
 
public class FileTreeFrame extends JFrame {
 
  private JTree fileTree;
  private FileSystemModel fileSystemModel;
  private JTextArea fileDetailsTextArea = new JTextArea();
  JMenuBar mbarra;
  
//build a controller
	SwingController controller = new SwingController();

	// Build a SwingViewFactory configured with the controller
	SwingViewBuilder factory = new SwingViewBuilder(controller);

	// Use the factory to build a JPanel that is pre-configured
	//with a complete, active Viewer UI.
	JPanel viewerComponentPanel = factory.buildViewerPanel();
	  String filePath = "C:/Documents and Settings/mike/Mis documentos/descargas/2.4 Bases teóricas/1_INDICE_BASES_TEORICAS- est es el indice que se deb cumpler en este  apartado.pdf";
  public FileTreeFrame(String directory) {
    super("JTree FileSystem Viewer - JavaProgrammingForums.com");
    fileDetailsTextArea.setEditable(false);
    fileSystemModel = new FileSystemModel(new File(directory));
    fileTree = new JTree(fileSystemModel);
    fileTree.setEditable(true);
 
    fileTree.addTreeSelectionListener(new TreeSelectionListener() {
      public void valueChanged(TreeSelectionEvent event) {
        File file = (File) fileTree.getLastSelectedPathComponent();
       
        fileDetailsTextArea.setText(getFileDetails(file));
      }
    });
    mbarra = new JMenuBar();
    JMenuItem itemNuevo;
    itemNuevo= new JMenuItem( "Nuevo" );
    JMenuItem itemAbrir;
    itemAbrir= new JMenuItem( "Abrir" );
    JMenuItem itemGuardar;
    itemGuardar= new JMenuItem( "Guardar" );
    JMenuItem itemGuardar_como;
    itemGuardar_como= new JMenuItem( "Guardar como" );
    JMenuItem itemImprimir;
    itemImprimir= new JMenuItem( "Imprimir" );
    JMenuItem itemSalir;
    itemSalir= new JMenuItem( "Salir" );
    
    JMenu m = new JMenu( "Archivo" );
  
    m.add( itemNuevo);
    m.add( itemAbrir );
    m.add( itemGuardar );
    m.add( itemGuardar_como );
    m.add( itemImprimir );
    m.addSeparator();
    m.add( itemSalir );
    mbarra.add( m );

    m = new JMenu( "Ayuda" );
    m.add( new JMenuItem( "Ayuda!" ) );
    m.addSeparator();
    m.add( new JMenuItem( "Acerca de..." ) );
    mbarra.add( m );

    setJMenuBar(mbarra );
    itemNuevo.setMnemonic( 'n' );
          itemNuevo.addActionListener(
   
            new ActionListener() {  // clase interna anónima
   
                 // mostrar cuadro de diálogo de mensaje cuando el usuario seleccione Acerca de...
              public void actionPerformed( ActionEvent evento )
              {
                  JOptionPane.showMessageDialog( FileTreeFrame.this,
                     "Éste es un ejemplo\ndel uso de menús",
                     "Acerca de", JOptionPane.PLAIN_MESSAGE );
                }
    
             }  // fin de la clase interna anónima
    
          ); // fin de la llamada a addActionListener
          itemAbrir.setMnemonic( 'a' );
          itemAbrir.addActionListener(
   
            new ActionListener() {  // clase interna anónima
   
                 // mostrar cuadro de diálogo de mensaje cuando el usuario seleccione Acerca de...
              public void actionPerformed( ActionEvent evento )
              {
            	//se crea el objeto, que en sí es el explorador
            	//se le puede pasar una ruta por defecto o no le podemos pasar nada par aque inicie en "Equipo"
            	JFileChooser explorador = new JFileChooser();

            	//Le cambiamos el titulo
            	explorador.setDialogTitle("Seleccionar Directorio...");

            	//Agregamos un filtro de extensiones
            	explorador.setFileFilter(new FileNameExtensionFilter("JPG & GIF", "jpg", "gif","pdf"));


            	explorador.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY );
            	

            	//Muestro un dialogo sin pasarle parent con el boton de abrir
            	int seleccion = explorador.showDialog(null, "Abrir!");
            	  
            	//analizamos la respuesta
            	switch(seleccion) {
            	case JFileChooser.APPROVE_OPTION:
            	 //seleccionó abrir
            		String Ruta= explorador.getSelectedFile().toString();
            		 JOptionPane.showMessageDialog(null,Ruta);
            		    fileSystemModel = new FileSystemModel(new File(Ruta));
            		    fileTree.setModel(fileSystemModel);
            		    invalidate();
            		    repaint();
            	 break;

            	case JFileChooser.CANCEL_OPTION:
            	 //dio click en cancelar o cerro la ventana
            	 break;

            	case JFileChooser.ERROR_OPTION:
            	 //llega aqui si sucede un error
            	 break;
            	}
                }
    
             }  // fin de la clase interna anónima
    
          ); // fin de la llamada a addActionListener

	

    /*JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, new JScrollPane(
        fileTree), new JScrollPane(fileDetailsTextArea));*/
	JSplitPane splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true,new JScrollPane(viewerComponentPanel),new JScrollPane(fileDetailsTextArea));
	splitPane2.setDividerLocation(850);
	JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, new JScrollPane(
	        fileTree), new JScrollPane(splitPane2));
	splitPane.setDividerLocation(300);
    getContentPane().add(splitPane);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(1000, 1000);
    setVisible(true);
    //controller.openDocument(filePath);
  }
 
  private String getFileDetails(File file) {
    if (file == null)
      return "";
   StringBuffer buffer = new StringBuffer();
    buffer.append("Name: " + file.getName() + "\n");
    buffer.append("Path: " + file.getPath() + "\n");
    buffer.append("Size: " + file.length() + "\n");
 // add copy keyboard command
 	ComponentKeyBinding.install(controller, viewerComponentPanel);

 	// add interactive mouse link annotation support via callback
 	controller.getDocumentViewController().setAnnotationCallback(
 	      new org.icepdf.ri.common.MyAnnotationCallback(
 	             controller.getDocumentViewController()));
    //controller.closeDocument();
    controller.openDocument(file.getPath());
    JOptionPane.showMessageDialog(this,file.getPath());
    return buffer.toString();
  }
 
 
  public static void main(String args[]) {
	

	

	  
	  
    new FileTreeFrame("C:\\Documents and Settings\\mike\\Mis documentos\\descargas\\2.4 Bases teóricas");
    
  
  }
}
 
class FileSystemModel implements TreeModel {
  private File root;
 
  private Vector listeners = new Vector();
 
  public FileSystemModel(File rootDirectory) {
    root = rootDirectory;
  }
 
  public Object getRoot() {
    return root;
  }
 
  public Object getChild(Object parent, int index) {
    File directory = (File) parent;
    String[] children = directory.list();
    return new TreeFile(directory, children[index]);
  }
 
  public int getChildCount(Object parent) {
    File file = (File) parent;
    if (file.isDirectory()) {
      String[] fileList = file.list();
      if (fileList != null)
        return file.list().length;
    }
    return 0;
  }
 
  public boolean isLeaf(Object node) {
    File file = (File) node;
    return file.isFile();
  }
 
  public int getIndexOfChild(Object parent, Object child) {
    File directory = (File) parent;
    File file = (File) child;
    String[] children = directory.list();
    for (int i = 0; i < children.length; i++) {
      if (file.getName().equals(children[i])) {
        return i;
      }
    }
    return -1;
 
  }
 
  public void valueForPathChanged(TreePath path, Object value) {
    File oldFile = (File) path.getLastPathComponent();
    String fileParentPath = oldFile.getParent();
    String newFileName = (String) value;
    File targetFile = new File(fileParentPath, newFileName);
    oldFile.renameTo(targetFile);
    File parent = new File(fileParentPath);
    int[] changedChildrenIndices = { getIndexOfChild(parent, targetFile) };
    Object[] changedChildren = { targetFile };
    fireTreeNodesChanged(path.getParentPath(), changedChildrenIndices, changedChildren);
 
  }
 
  private void fireTreeNodesChanged(TreePath parentPath, int[] indices, Object[] children) {
    TreeModelEvent event = new TreeModelEvent(this, parentPath, indices, children);
    Iterator iterator = listeners.iterator();
    TreeModelListener listener = null;
    while (iterator.hasNext()) {
      listener = (TreeModelListener) iterator.next();
      listener.treeNodesChanged(event);
    }
  }
 
  public void addTreeModelListener(TreeModelListener listener) {
    listeners.add(listener);
  }
 
  public void removeTreeModelListener(TreeModelListener listener) {
    listeners.remove(listener);
  }
 
  private class TreeFile extends File {
    public TreeFile(File parent, String child) {
      super(parent, child);
    }
 
    public String toString() {
      return getName();
    }
  }
}