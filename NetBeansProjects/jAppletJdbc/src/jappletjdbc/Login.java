/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jappletjdbc;

/**
 *
 * @author mike
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JApplet;

public class Login extends JApplet
{


    static int i=0;
    static String Logged="";
    static int ID=0;
    double total=0.0;
    double Firsttotal=0.0,x1=0.0,y1=0.0;
    double weight=50.00;
    static int level=10,count=0;
    Connection con = null;
    Statement st = null;
    Statement st2 = null;
    ResultSet rs = null;
    double score=0.0,score1=0.0,score2=0.0,score3=0.0;
    double Game1=0.0,Game2=0.0,Game3=0.0;



private static final String text = "<html> <center><b><font size=+3>Brain</font></b><b><font size=+3>Game</font></b></center> </html>";
//DataB db=new DataB();

JPanel jPanel1 = new JPanel();
BorderLayout borderLayout1 = new BorderLayout();
JLabel jLabel1 = new JLabel();
JPanel jPanel2 = new JPanel();
GridBagLayout gridBagLayout1 = new GridBagLayout();
JLabel jLabel2 = new JLabel();
JLabel jLabel3 = new JLabel();
JTextField loginTextField = new JTextField(20);
JPasswordField passwordTextField = new JPasswordField(20);
JPanel jPanel3 = new JPanel();
JButton registerButton = new JButton();
JButton enterButton = new JButton();
FlowLayout flowLayout1 = new FlowLayout();
static String logged="";
boolean fInBrowser = true;

public void init() {
try {
jbInit();
}
catch(Exception e) {
e.printStackTrace();
}
}

private void authenticate(String l){

    if (!(logged.equals(""))) {
        System.out.println("login successfull");
         JOptionPane.showMessageDialog(this,"Login Sucesfull","ok",JOptionPane.OK_OPTION);

  //showTreeView();
    } else {
        JOptionPane.showMessageDialog(this,"Incorrect login or password","Error",JOptionPane.ERROR_MESSAGE);
        loginTextField.setText("");
        passwordTextField.setText("");
        loginTextField.requestFocusInWindow();
    }
}

private void login(ActionEvent e) {
String login = loginTextField.getText();
String password = new String(passwordTextField.getPassword());
//validate login and password here. validity will be done by sending login/password to the server

if (login.equals("") || password.equals("")){
    JOptionPane.showMessageDialog(this,"Either of the following have not been entered:\n\n-UserName\n-PassWord\n-UserName And PassWord\n\n *Please fill in all fields*","Error",JOptionPane.ERROR_MESSAGE);
}else{
logged=loginit(login,password);
authenticate(logged);
}
}

public String loginit(String login,String password){

        Connection con ;
        Statement st1 = null;
        ResultSet rs1 = null;

        String url = "jdbc:mysql://localhost:3306/users"; //where 3306 is the port, localhost is address of DB, and mysql is name of the database you wanna use...

        try{

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        con = DriverManager.getConnection(url, "root", "root");
        st1 = con.createStatement();
        if (login.equals("") && password.equals("")){
            Logged="";
        }else{
        rs1=st1.executeQuery("select user_id,level from users where name like '%"+ login +"%' and password like '%"+password+"%' ");

        if (rs1.next()) {
            ID=rs1.getInt("user_id");
            level=rs1.getInt("level");
            Logged="yes";
        }
        }

        if (con != null) con.close(); //same for st and rs...

        }

        catch(Exception e){System.out.println(e.getMessage());}

        return Logged;

}


 public static void main (String[] args) {
    //
    int frame_width=450;
    int frame_height=300;

    // Create an instance of this applet an add to a frame.
    Login applet = new Login ();
    applet.fInBrowser = true;
    applet.init ();

    // Following anonymous class used to close window & exit program
    JFrame f = new JFrame ("Demo");
    f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

    // Add applet to the frame
    f.getContentPane ().add ( applet);
    f.setSize (new Dimension (frame_width, frame_height));
    f.setVisible (true);
  } // main


private void jbInit() throws Exception {
jPanel1.setLayout(borderLayout1);
jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
jLabel1.setText(text);
jPanel2.setLayout(gridBagLayout1);
jLabel2.setText("Password:");
jLabel3.setText("Login:");
registerButton.setText("Register");

passwordTextField.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(ActionEvent e) {

    login(e);

}
});

loginTextField.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(ActionEvent e) {
    login(e);

}
});

registerButton.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(ActionEvent e) {
//register(e);
}
});
enterButton.setText("Enter");

enterButton.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(ActionEvent e) {
login(e);
}
});

jPanel3.setLayout(flowLayout1);
flowLayout1.setAlignment(FlowLayout.RIGHT);
this.getContentPane().add(jPanel1, BorderLayout.CENTER);
jPanel1.add(jLabel1, BorderLayout.NORTH);
jPanel1.add(jPanel2, BorderLayout.CENTER);
jPanel2.add(loginTextField, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0
,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 11, 0, 0), 0, 0));
jPanel2.add(jLabel2, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(11, 0, 0, 0), 0, 0));
jPanel2.add(passwordTextField, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0
,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(11, 11, 0, 0), 0, 0));
jPanel2.add(jLabel3, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
jPanel1.add(jPanel3, BorderLayout.SOUTH);
jPanel3.add(enterButton, null);
jPanel3.add(registerButton, null);
}
}
