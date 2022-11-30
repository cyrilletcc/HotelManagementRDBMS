/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagementrdbms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.sql.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.internal.objects.NativeString.toUpperCase;
/**
 *
 * @author cyril
 */
public class Menu {

    final String url = "jbdc:oracle:thin:@localhost:1521:xe";
    final String username = "CPS510";
    final String password = "oracletcc";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.StartDB(menu);
       
        
    }
    
    public void StartDB(Menu menu){
        final String url1 = "jbdc:oracle:thin:@localhost:1521:xe";
        final String username1 = "CPS510";
        final String password1 = "oracletcc";
        try {
                      
            //menu.DropTable();
            ResultSetTableModel emptable = new ResultSetTableModel(url1, 
                    username1, password1);
            ResultSetTableModel roomtable = new ResultSetTableModel(url1, 
                    username1, password1);
            ResultSetTableModel guesttable = new ResultSetTableModel(url1, 
                    username1, password1);
            ResultSetTableModel occupytable = new ResultSetTableModel(url1, 
                    username1, password1);
            
            JFrame frame1 = new JFrame("Hotel Management by Guarav, Joshua and "
                    + "Cyrille");
            JPanel optionPanel = new JPanel();
            JTabbedPane tablePanel = new JTabbedPane(JTabbedPane.LEFT);
            JPanel header = new JPanel();
            header.setLayout(new BorderLayout());
            JPanel body = new JPanel();
            body.setLayout(new BorderLayout());
            JPanel empPane, roomPane, guestPane,oqPane;
            JTextArea welcometext1 = new JTextArea("");
            JTextArea welcometext = new JTextArea("WELCOME IN THE HOTEL "
                    + "MANAGEMENT SOFTWARE\n CLICK ON A BUTTON BELOW!");
            welcometext.setEditable(false);
            JTextArea welcometext2 = new JTextArea("");
            optionPanel.setLayout(new GridLayout(2,3));
            empPane = new JPanel(new GridLayout(1,1));
            guestPane = new JPanel(new GridLayout(1,1));
            roomPane = new JPanel(new GridLayout(1,1));
            oqPane = new JPanel(new GridLayout(1,1));
            
            tablePanel.addTab("Employee Data", empPane);
            tablePanel.addTab("Room Data", roomPane);
            tablePanel.addTab("Guest Data", guestPane);
            tablePanel.addTab("Room Occupation Data", oqPane);
            
            JButton droptableButton = new JButton("1) Drop Table");
            JButton createtableButton = new JButton("2) Create Table");
            JButton populatetableButton = new JButton("3) Populate Table");
            JButton queryfromtableButton = new JButton("4) Run Query From "
                    + "Table");
            JButton userqueryButton = new JButton("5) Run User Query");
            JButton exitButton = new JButton("E) Exit");
            exitButton.setBackground(Color.red);
            optionPanel.add(droptableButton);
            optionPanel.add(createtableButton);
            optionPanel.add(populatetableButton);
            optionPanel.add(queryfromtableButton);
            optionPanel.add(userqueryButton);
            optionPanel.add(exitButton);
            JTable resultTable1 ;
            JTable resultTable2 ;
            JTable resultTable3 ;
            JTable resultTable4 ;
            try{
                String query ="SELECT e.emp_id as ID, e.emp_f_name as "
                        + "First_Name, e.emp_l_name as Last_Name, e.address as "
                        + "Address, e.phone_number as Phone, e.dob as DOB, "
                        + "e.emp_role as Role, e.emp_type as Contract, e.team "
                        + "as Team, d.dept_name as Departement FROM Employee e,"
                        + " department d WHERE d.dept_id = e.dept_id";
                emptable.setQuery(query);
                resultTable1 = new JTable(emptable);
                empPane.add(new JScrollPane(resultTable1,JScrollPane.
                        VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.
                                HORIZONTAL_SCROLLBAR_AS_NEEDED ));
            }catch(SQLSyntaxErrorException e){
                resultTable1 = new JTable();
                JTextField dis = new JTextField("  No or Missing Data to "
                        + "display Properly ");
                dis.setEditable(false);
                empPane.add(dis);
            }
            
            try{
                String query2 ="SELECT r.room_id as ROOM_ID,r.booking_ref as "
                        + "BOOKING_REF_NO, r.booking_date as RESERVATION_DATE, "
                        + "r.book_in asCheck_In, r.check_out as Check_out, "
                        + "g.guest_id as Guest_ID, d.dept_name as Departement "
                        + "FROM Room r, department d, guest g WHERE "
                        + "d.dept_id = r.dept_id AND r.guest_id = g.guest_id";
                roomtable.setQuery(query2);
                resultTable2 = new JTable(roomtable);
                roomPane.add(new JScrollPane(resultTable2));
            }catch(SQLSyntaxErrorException e){
                resultTable2 = new JTable();
                JTextField dis = new JTextField("  No or Missing Data to "
                        + "display Properly ");
                dis.setEditable(false);
                roomPane.add(dis);
            }
            try{
                String query3 = " Select g.guest_id as Guest_ID, g.guest_f_name"
                        + " as Guest_First_Name, g.guest_l_name as "
                        + "Guest_Last_Name,  d.dept_name as "
                        + "Departement_In_Charge from department d, "
                        + "guest g WHERE d.dept_id = g.dept_id ";
                guesttable.setQuery(query3);
                resultTable3 = new JTable(guesttable);
                guestPane.add(new JScrollPane(resultTable3));
            }catch(SQLSyntaxErrorException e){
                resultTable3 = new JTable();
                JTextField dis = new JTextField("  No or Missing Data to "
                        + "display Properly");
                dis.setEditable(false);
                guestPane.add(dis);
            }
            try{
                String query4 ="SELECT r.booking_ref as Booking_ref_no,"
                        + "r.room_id as ROOM_ID, r.booking_date as "
                        + "RESERVATION_DATE, o.start_date as Check_in, "
                        + "o.end_date as Check_out,g.guest_id as "
                        + "Guest_ID, g.guest_f_name as Guest_First_Name, "
                        + "g.guest_l_name as Guest_Last_Name  FROM occupies o, "
                        + "room r, guest g WHERE o.room_id = r.room_id AND "
                        + "o.guest_id = g.guest_id";
                occupytable.setQuery(query4);
                resultTable4 = new JTable(occupytable);
                oqPane.add(new JScrollPane(resultTable4));
            }catch(SQLSyntaxErrorException e){
                resultTable4 = new JTable();
                JTextField dis = new JTextField("  No or Missing Data to "
                        + "display Properly ");
                dis.setEditable(false);
                oqPane.add(dis);
            }
            
            Container c = frame1.getContentPane();
            header.add(welcometext1,BorderLayout.NORTH);
            header.add(welcometext,BorderLayout.CENTER);
            header.add(welcometext2,BorderLayout.SOUTH);
            
            body.add(optionPanel,BorderLayout.NORTH);
            body.add(tablePanel,BorderLayout.CENTER);
            c.add(header,BorderLayout.NORTH);
            c.add(body,BorderLayout.CENTER);
            
            droptableButton.addActionListener(
                   new ActionListener(){
                       public void actionPerformed(ActionEvent event){
                           menu.DropTableGUI(menu);
                           frame1.setVisible(false);
                       }
                   }
            );
            
            createtableButton.addActionListener(
                   new ActionListener(){
                       public void actionPerformed(ActionEvent event){
                           menu.CreateTableGUI(menu);
                           frame1.setVisible(false);
                       }
                   }
            );
            
            populatetableButton.addActionListener(
                   new ActionListener(){
                       public void actionPerformed(ActionEvent event){
                           menu.PopulateTableGUI(menu);
                           frame1.setVisible(false);
                       }
                   }
            );
            userqueryButton.addActionListener(
                   new ActionListener(){
                       public void actionPerformed(ActionEvent event){
                           menu.UserQueryGUI(menu);
                           frame1.setVisible(false);
                       }
                   }
            );
            queryfromtableButton.addActionListener(
                   new ActionListener(){
                       public void actionPerformed(ActionEvent event){
                           menu.QueryTableGUI(menu);
                           frame1.setVisible(false);
                       }
                   }
            );
            exitButton.addActionListener(
                   new ActionListener(){
                       public void actionPerformed(ActionEvent event){
                           System.exit(0);
                       }
                   }
            );
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setMinimumSize(new Dimension(1000, 400));
        //frame.getContentPane().setBackground(Color.ORANGE);
        frame1.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    /**
     
     * @param menu 
     */
    public void DropTableGUI(Menu menu){
        try {
            final ResultSetTableModel table = new ResultSetTableModel(url, 
                    username, password);
            JFrame frame = new JFrame("Drop Table Form");
            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent e) {
                    menu.StartDB(menu);
                }
            });
            
            //JFrame frame2 = new JFrame("Drop Table");
            final JTextArea queryArea = new JTextArea(6,100);
            queryArea.setWrapStyleWord(true);
            queryArea.setLineWrap(true);
            
            JScrollPane scrollPane = new JScrollPane(queryArea,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            
            JButton submitButton = new JButton("SQL to drop a table");
            JButton fromfileButton = new JButton("Drop Tables from File");
            JButton returnButton = new JButton("Return");
            returnButton.setBackground(Color.red);
            
            JPanel butpane = new JPanel();
            butpane.setLayout(new GridLayout(3,1));
            butpane.add(submitButton);
            butpane.add(fromfileButton);
            butpane.add(returnButton);
            
            Box hbox = Box.createHorizontalBox();
            hbox.add(scrollPane);
            hbox.add(butpane);
            JTextArea message = new JTextArea();
            
            Container c = frame.getContentPane();
            c.add(hbox,BorderLayout.NORTH);
            c.add(new JScrollPane(message),BorderLayout.CENTER);
            submitButton.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent event){
                        String msg = queryArea.getText();
                        System.out.println(msg);
                        int res = menu.DropTable(msg);
                        if(res==0){
                            message.setText("No such Table in the Database");
                        }else{
                            String msg2 = "Table "+msg+" Dropped!";
                            message.setText(msg2);
                        }
                    }
                }
            );
            fromfileButton.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent event){
                        ArrayList<String> res = menu.DropTableALL();
                        if ((res.get(0)==null)&&res.size()<2){
                            message.setText("No table Created from the File");
                        }else{
                            for(String msg:res){
                                message.append(msg);
                            };
                        }
                    }
                }
            );
            returnButton.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent event){
                        frame.setVisible(false);
                        menu.StartDB(menu);
                    }
                }
            );
            frame.setMinimumSize(new Dimension(800, 400));
            frame.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     
     * @param menu 
     */
    public void CreateTableGUI(Menu menu){
        final String url1 = "jbdc:oracle:thin:@localhost:1521:xe";
        final String username1 = "CPS510";
        final String password1 = "oracletcc";
        try {
            final ResultSetTableModel table = new ResultSetTableModel(url, 
                    username, password);
            JFrame frame = new JFrame("Create Table Form");
            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent e) {
                    menu.StartDB(menu);
                }
            });
            JFrame frame2 = new JFrame("Create Table Form");
            frame2.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent e) {
                    menu.StartDB(menu);
                }
            });
            
            //JFrame frame2 = new JFrame("Create Table");
            final JTextArea queryArea = new JTextArea(3,100);
            queryArea.setWrapStyleWord(true);
            queryArea.setLineWrap(true);
            
            JScrollPane scrollPane = new JScrollPane(queryArea,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            
            JButton submitButton = new JButton("SQL to Create a table");
            JButton fromfileButton = new JButton("Create Tables from File");
            JButton returnButton = new JButton("Return");
            returnButton.setBackground(Color.red);
            
            JPanel butpane = new JPanel();
            butpane.setLayout(new GridLayout(3,1));
            butpane.add(submitButton);
            butpane.add(fromfileButton);
            butpane.add(returnButton);
            
            Box hbox = Box.createHorizontalBox();
            hbox.add(scrollPane);
            hbox.add(butpane);
            
            JTextArea messagedisplay = new JTextArea(10,100);
            JPanel body = new JPanel();
            body.setLayout(new BorderLayout());
          
            ResultSetTableModel createdtable = new ResultSetTableModel(url1, 
                    username1, password1);           
            
            JTable resultcreateTable = new JTable() ;
            JScrollPane headtabpan = new JScrollPane(messagedisplay);
            JScrollPane tabpan = new JScrollPane(resultcreateTable); 
            body.add(headtabpan,BorderLayout.NORTH);
            body.add(tabpan,BorderLayout.CENTER);
            
            Container c = frame.getContentPane();
            c.add(hbox,BorderLayout.NORTH);
            c.add(body,BorderLayout.CENTER);
            
            submitButton.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent event){
                        String msg = queryArea.getText();
                        System.out.println(msg);
                        String res = menu.CreateTable(msg);
                        System.out.println(res);
                        int siz = res.trim().length();
                        String trimres= res.trim();
                        System.out.println("RES:" +res);
                        System.out.println("TACLE: "+res.substring(0,5));
                        System.out.println("CREATED: "+res.substring(siz-8, siz));
                        System.out.println("trimRES:" +trimres);
                        System.out.println("trimTACLE: "+trimres.substring(0,5).
                                trim());
                        System.out.println("trimCREATED: "+trimres.substring
                                                    (siz-8, siz-1).trim());
                        
                        if(res == null){
                            messagedisplay.setText("Table Not Created in the "
                                    + "Database. Check if not already in the "
                                    + "Database!");
                        }else if(!res.contains("Not Created!")){
                            String restrim = res.trim();
                            restrim = restrim.substring(5,restrim.length()-1);
                            restrim.trim();
                            ArrayList<Character> chararr = new ArrayList<>();
                            int restrimsize;
                            String table_name="";

                            //extracting the table name
                            boolean stop = false;
                            restrimsize = restrim.length();
                            for(int i = 0;i<restrimsize;i++){
                                if(restrim.charAt(i)!= ' '){
                                    chararr.add(restrim.charAt(i));
                                    stop = true;
                                } else if(restrim.charAt(i)== ' '&& stop==true){
                                    break;
                                }
                            }
                            for(int i = 0; i<=chararr.size()-1;i++){
                                table_name += chararr.get(i);
                            }
                            try {
                                JTable resultTable1 ;
                                JPanel tab ;
                                JTextArea messagedisplay = new JTextArea(10,100);
                                JPanel body = new JPanel();
                                body.setLayout(new BorderLayout());
                                tab = new JPanel(new GridLayout(1,1));
                                String msg2 = "\nTable "+table_name+" Created!\n";
                                messagedisplay.setText(msg2);
                                String query = "Select * From "+ table_name;
                                createdtable.setQuery(query);
                                JScrollPane headtab = new JScrollPane(messagedisplay,
                                    ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                                resultTable1 = new JTable(createdtable);
                                tab.add(new JScrollPane(resultTable1));
                                
                                body.remove(tabpan);
                                body.remove(headtabpan);
                                frame.setVisible(false);
                                
                                body.setLayout(new BorderLayout());
                                body.add(tab,BorderLayout.CENTER);
                                body.add(headtab,BorderLayout.NORTH);
                                Container c2 = frame2.getContentPane();
                                c2.add(hbox,BorderLayout.NORTH);
                                c2.add(body,BorderLayout.CENTER);
                                frame2.setMinimumSize(new Dimension(800, 400));
                                frame2.setVisible(true);
                            } catch (SQLException ex) {
                                Logger.getLogger(Menu.class.getName()).
                                        log(Level.SEVERE, null, ex);
                            } catch (IllegalStateException ex) {
                                Logger.getLogger(Menu.class.getName()).
                                        log(Level.SEVERE, null, ex);
                            }
                        }else{messagedisplay.setText("Table Not Created in the "
                                    + "Database.");
                        }
                    }
                }
            );
            fromfileButton.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent event){
                        ArrayList<String> res = menu.CreateTableFile();
                            for(String msg:res){
                                messagedisplay.append(msg);
                            };
                        
                    }
                }
            );
            returnButton.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent event){
                        JFrame currentframe = (JFrame)SwingUtilities.
                                getWindowAncestor(returnButton);
                        currentframe.setVisible(false);
                        menu.StartDB(menu);
                    }
                }
            );
            
            frame.setMinimumSize(new Dimension(800, 400));
            frame.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     
     * @param menu 
     */
    public void PopulateTableGUI(Menu menu){
        try {
            final ResultSetTableModel table = new ResultSetTableModel(url, 
                    username, password);
            JFrame frame = new JFrame("Populate Table Form");
            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent e) {
                    menu.StartDB(menu);
                }
            });
            
            //JFrame frame2 = new JFrame("Drop Table");
            final JTextArea queryArea = new JTextArea(6,100);
            queryArea.setWrapStyleWord(true);
            queryArea.setLineWrap(true);
            
            JScrollPane scrollPane = new JScrollPane(queryArea,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            
            JButton submitButton = new JButton("SQL to Insert a Record Into "
                    + "a Table");
            JButton fromfileButton = new JButton("Insert Records from File");
            JButton returnButton = new JButton("Return");
            returnButton.setBackground(Color.red);
            
            JPanel butpane = new JPanel();
            butpane.setLayout(new GridLayout(3,1));
            butpane.add(submitButton);
            butpane.add(fromfileButton);
            butpane.add(returnButton);
            
            Box hbox = Box.createHorizontalBox();
            hbox.add(scrollPane);
            hbox.add(butpane);
            JTextArea message = new JTextArea();
            
            Container c = frame.getContentPane();
            c.add(hbox,BorderLayout.NORTH);
            c.add(new JScrollPane(message),BorderLayout.CENTER);
            submitButton.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent event){
                        String msg = queryArea.getText();
                        System.out.println(msg);
                        String res = menu.PopulateTable(msg);
                        if(res == null){
                            //
                        }else{
                            String msg2 = "Transaction complete!";
                            message.append(res);
                            message.append("\n");
                        }
                    }
                }
            );
            fromfileButton.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent event){
                        ArrayList<String> res = menu.PopulateTableFile(menu);
                        if ((res.get(0)==null)&&res.size()<2){
                            message.setText("No table Created from the File");
                        }else{
                            for(String msg:res){
                                message.append(msg);
                            };
                        }
                    }
                }
            );
            returnButton.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent event){
                        JFrame currentframe = (JFrame)SwingUtilities.
                                getWindowAncestor(returnButton);
                        currentframe.setVisible(false);
                        menu.StartDB(menu);
                    }
                }
            );
            frame.setMinimumSize(new Dimension(800, 400));
            frame.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     
     * @param menu 
     */
    public void QueryTableGUI(Menu menu){
        try {
            final ResultSetTableModel table = new ResultSetTableModel(url, 
                    username, password);
            JFrame frame = new JFrame("Query from File Form");
            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent e) {
                    menu.StartDB(menu);
                }
            });
            
            //JFrame frame2 = new JFrame("Drop Table");
            final JTextArea queryArea = new JTextArea(6,100);
            queryArea.setWrapStyleWord(true);
            queryArea.setLineWrap(true);
            
            JScrollPane scrollPane = new JScrollPane(queryArea,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            
            JButton submitButton = new JButton("Submit SQL Query");
            JButton returnButton = new JButton("Return");
            returnButton.setBackground(Color.red);
            
            JPanel butpane = new JPanel();
            butpane.setLayout(new GridLayout(2,1));
            butpane.add(submitButton);
            butpane.add(returnButton);
            
            Box hbox = Box.createHorizontalBox();
            hbox.add(scrollPane);
            hbox.add(butpane);
            JTextArea message = new JTextArea();
            
            Container c = frame.getContentPane();
            c.add(hbox,BorderLayout.NORTH);
            c.add(new JScrollPane(message),BorderLayout.CENTER);
            submitButton.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent event){
                        String msg = queryArea.getText();
                        System.out.println(msg);
                        ResultSet res = menu. UserQueryTable(msg);
                        if(res == null){
                            //
                        }else{
                            String msg2 = "Transaction complete!";
                        }
                    }
                }
            );
            returnButton.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent event){
                        JFrame currentframe = (JFrame)SwingUtilities.getWindowAncestor(returnButton);
                        currentframe.setVisible(false);
                        menu.StartDB(menu);
                    }
                }
            );
            frame.setMinimumSize(new Dimension(800, 400));
            frame.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * This Method drops a table from the Database. 
     * @param name of the table to drop from the database
     * @param menu 
     */
    public void UserQueryGUI(Menu menu){
        try {
            final ResultSetTableModel table = new ResultSetTableModel(url, 
                    username, password);
            JFrame frame = new JFrame("User Query Form");
            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent e) {
                    menu.StartDB(menu);
                }
            });
            
            //JFrame frame2 = new JFrame("Drop Table");
            final JTextArea queryArea = new JTextArea(6,100);
            queryArea.setWrapStyleWord(true);
            queryArea.setLineWrap(true);
            
            JScrollPane scrollPane = new JScrollPane(queryArea,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            
            JButton submitButton = new JButton("Submit SQL Query");
//            JButton fromfileButton = new JButton("Insert Records from File");
            JButton returnButton = new JButton("Return");
            returnButton.setBackground(Color.red);
            
            JPanel butpane = new JPanel();
            butpane.setLayout(new GridLayout(2,1));
            butpane.add(submitButton);
            //butpane.add(fromfileButton);
            butpane.add(returnButton);
            
            Box hbox = Box.createHorizontalBox();
            hbox.add(scrollPane);
            hbox.add(butpane);
            JTextArea message = new JTextArea();
            
            Container c = frame.getContentPane();
            c.add(hbox,BorderLayout.NORTH);
            c.add(new JScrollPane(message),BorderLayout.CENTER);
            submitButton.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent event){
                        String msg = queryArea.getText();
                        System.out.println(msg);
                        ResultSet res = menu. UserQueryTable(msg);
                        //ArrayList<String[]> out = Printresult(res); 
                        //int count = out.size();
                        if(res == null){
                            //
                        }else{
                            String msg2 = "Transaction complete!";
                           
                        }
                    }
                }
            );
            returnButton.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent event){
                        JFrame currentframe = (JFrame)SwingUtilities.getWindowAncestor(returnButton);
                        currentframe.setVisible(false);
                        menu.StartDB(menu);
                    }
                }
            );
            frame.setMinimumSize(new Dimension(800, 400));
            frame.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * This Method drops a table from the Database. 
     * @param name of the table to drop from the database
    */
    int DropTable(String name){
        //connect to the database
        try {
            Connection con = DriverManager.getConnection(url, username,
                    password);
            if (con != null){
                String sql = "drop table " +name+ " CASCADE CONSTRAINTS";                 
                PreparedStatement statement = con.prepareStatement(sql);
                statement.executeUpdate();
                System.out.println("\nTable "+toUpperCase(name) + " dropped.");
                con.close();
                return 1;
            }
        }catch (SQLSyntaxErrorException ex1){
            System.out.println("The table "+toUpperCase(name)+" does'n exist");
            return 0;
        }catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        return 0;
    }    

 /**
     * This Method drops all the tables from the Database. Requires no parameter
     * List of table to be stored in "Tables.txt" at "src/hotelmanagementrdbms/"
     * @return transaction report
    */
    public ArrayList<String> DropTableALL(){
        ArrayList<String> tables = new ArrayList<>();
        String message = "";
        ArrayList<String> msgs = new ArrayList<>();
        try{
            //Read list of tables to drop from the file
            File file = new File("src/hotelmanagementrdbms/Tables.txt");
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                String inp = sc.nextLine().trim();
                tables.add(inp);
            }
        }catch(Exception ex){
            ex.printStackTrace();
            message = "The File does not Exit in src directory";
        }
        int filesize = tables.size();
        if(filesize==0){
            message = "There is no Table's Name in Tables.txt File!";
            msgs.add(message);
        }
        for (int i=0;i<filesize;i++) {
            String name = tables.get(i);
            //Establish connexion to Oracle database
            try {
                Connection con = DriverManager.getConnection(url, username,
                        password);
                if (con != null){
                    String sql = "drop table "+name+" CASCADE CONSTRAINTS"; 
                    PreparedStatement statement = con.prepareStatement(sql);
                    statement.executeUpdate();
                    System.out.println("Table "+toUpperCase(name)+" dropped.");
                    message = "\n  Table "+toUpperCase(name)+" dropped.\n" ;
                    msgs.add(message);
                    con.close();
                }
            }catch (SQLSyntaxErrorException ex1){
                System.out.println("The table "+toUpperCase(name)+" does "
                        + "not exist");
                message = " \n  The table "+toUpperCase(name)+" does "
                        + "not exist\n";
                msgs.add(message);
            }catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, 
                        null, ex);
            }
        }
        //Erase the list of table from the "Tables.txt" file
        File file = new File("src/hotelmanagementrdbms/Tables.txt");
        try{
            PrintWriter pw = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return msgs;
    }
    /**
     * This Method creates a table in the Database. 
     * @param query : SQL query for creating a table. e
    */
    String CreateTable(String query){
        String rep = null;
        String createString = query.trim(); //extract the query
        int size = createString.length();
        if(createString.charAt(size-1)==';'){//remove semicolon at the end
            createString = createString.substring(0,size-1);
        }
        ArrayList<Character> chararr = new ArrayList<>();
        int indexstart;
        String table_name="";
        
        //extracting the table name
        boolean stop = false;
        indexstart=createString.indexOf('(');
        for(int i = indexstart-1;i>0;i--){
            if(createString.charAt(i)!= ' '){
                chararr.add(createString.charAt(i));
                stop = true;
            } else if(createString.charAt(i)== ' '&& stop==true){
                break;
            }
        }
        for(int i = chararr.size()-1; i>=0;i--){
            table_name += chararr.get(i);
        }
        
        //Sending the request
        try {
            Connection con = DriverManager.getConnection(url, username,
                    password);
            if (con != null){
                System.out.println("Connected!");
                
                Statement statement = con.createStatement();
                statement.executeUpdate(createString);
                System.out.println("New Table "+table_name+" Created");
                con.close();
                rep = " \n  Table "+table_name+" Created \n";
            }
            //adding the new created table to the table list file
           try {
                File tablefile =new File("src/hotelmanagementrdbms/Tables.txt");
                if (tablefile.createNewFile()) {
                    System.out.println("File created: " + tablefile.getName());
                } 
                FileWriter myfile = new FileWriter("src/hotelmanagementrdbms/"
                        + "Tables.txt",true);
                myfile.write(table_name);
                myfile.write("\n");
                System.out.println("added to Tables.txt");
                myfile.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            } 
            
        }catch (SQLException ex1){
            System.out.println("The table not created");
            //Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex1);
            String temp = "\n Table "+toUpperCase(table_name)+" Not Created!  \n";
            return temp;
        }
        return rep;
    }
    /**
     * This Method creates table(s) from a file into the Database. 
     * The user to enter the path to the file or enter "file" to create from 
     * the default create_tables.sh saved in "src/hotelmanagementrdbms/"
    */
    ArrayList<String>  CreateTableFile(){
        
        ArrayList<String> msgs = new ArrayList<>();
        String msg = null;
        String sql="";
        String filepath="";
        File file;
        Scanner sc2 = null;
        
        try{
            file = new File("src/hotelmanagementrdbms/create_tables.sh");
            sc2 = new Scanner(file);
        }catch(Exception ex){
            System.out.println("File not found! check that the file exits "
                    + "in src/hotelmanagementrdbms/ ");
        }
        
        boolean add = false;
        while(sc2.hasNextLine()){
            String inp = sc2.nextLine().trim();
            if((inp.length()>7)&&(inp.substring(0, 6).equalsIgnoreCase("cre"
                    + "ate"))&&(add==false)){
                sql+=inp;
                add=true;
            }else if ((inp.length()>=2) && (!(inp.substring(inp.length()-2).
                    equalsIgnoreCase(");")))&&(add==true)){
                sql+=inp;
            }else if ((inp.length()>=2) && (inp.substring((inp.length()-2)).
                    equalsIgnoreCase(");"))&&(add==true)){
                sql+=inp.substring(0,inp.length()-1);
                add = false;
                msg = this.CreateTable(sql);
                msgs.add(msg);
                sql="";
            }else{}
        }
        //System.out.println(msgs.toString());
        return msgs;
    }
    
    /**
     * This Method insert data the Database. 
     * @param query : SQL query for inserting the data in a table in database.
     * @return 0 if no data inserted and 1 if data inserted in database
    */
    String PopulateTable(String query){
        String temp = "";
        String createString = query.trim(); //extract the query
        int size = createString.length();
        if(createString.charAt(size-1)==';'){//remove semicolon at the end
            createString = createString.substring(0,size-1);
        }
        try {
            Connection con = DriverManager.getConnection(url, username, 
                    password);
            if (con != null){
                System.out.println("Connected!");
                Statement statement = con.createStatement();
                statement.executeUpdate(createString);
                con.close();
                temp += createString;
                temp += "\n 1 new row inserted!\n";
                System.out.println("Connected terminated");
            }            
        }catch(SQLIntegrityConstraintViolationException ex){
            System.out.println("Integrity constraint violated");
            return "Record not created because of Integrity constraints violation";
        }
        catch (SQLException ex1){
            System.out.println("row not added");
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex1);
            return "Record not added";
        }
        return temp;
    }
    /**
     * This Method inserts data from a file into the Database. 
     * User to enter the path to the file when prompted or enter "file"
     * to insert data from the default populate_tables.sh saved 
     * in "src/hotelmanagementrdbms/"
     * @return 
     * @param menu
    */
    ArrayList<String> PopulateTableFile(Menu menu){
        ArrayList<String> count = new ArrayList() ;
        String sql="";
        File file;
        Scanner sc2 = null;
        
            try{
                file = new File("src/hotelmanagementrdbms/populate_tables.sh");
                sc2 = new Scanner(file);
            }catch(Exception ex){
                System.out.println("File not found! check that the file exits "
                        + "in src/hotelmanagementrdbms/ ");
                ex.printStackTrace();}
        
        boolean add = false;
        int slep = 0;
        while(sc2.hasNextLine()){
            String inp = sc2.nextLine().trim();
            if((inp.length()>7)&&(inp.substring(0, 6).equalsIgnoreCase("ins"
                    + "ert"))&&(add==false)&& ((inp.substring(inp.length()-2).
                            equalsIgnoreCase(");")))){
                sql += inp.substring(0,inp.length()-1);
                count.add(menu.PopulateTable(sql));
                sql="";
            }else if((inp.length()>7)&&(inp.substring(0, 6).equalsIgnoreCase(""
                    + "insert"))&&(add==false)&& (!(inp.substring(inp.
                            length()-2).equalsIgnoreCase(");")))){
                sql+=inp;
                add=true;
            }else if ((inp.length()>=2) && (!(inp.substring(inp.length()-2).
                    equalsIgnoreCase(");")))&&(add==true)){
                sql+=inp;
            }else if ((inp.length()>=2) && (inp.substring((inp.length()-2)).
                    equalsIgnoreCase(");"))&&(add==true)){
                sql+=inp.substring(0,inp.length()-1);
                add = false;
                count.add(menu.PopulateTable(sql));
                sql="";
            }else{}
            slep+=1;
            if(slep == 10){
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE,
                            null, ex);
                }
                slep=0;
            }
        }
        
        return count;
    }
    
    /**
     * This Method runs queries from a file. 
     * The program read the queries from the default file queries.sh saved 
     * in "src/hotelmanagementrdbms/"
    */
    void QueryTablesFile(){
        String sql="";
        File file;
        Scanner sc2 = null;
        System.out.println("Reading queries from file...");
        try{
            file = new File("src/hotelmanagementrdbms/queries.sh");
            sc2 = new Scanner(file);
        }catch(Exception ex){
            System.out.println("File not found! check that the file exits in"
                    + " src/hotelmanagementrdbms/ ");
            ex.printStackTrace();}
       
        boolean add = false;
        boolean send = false;
        int slep = 0;
        while(sc2.hasNextLine()){
            String inp = sc2.nextLine().trim();
            if((inp.length()>=6)&&(inp.substring(0, 6).equalsIgnoreCase("sele"
                    + "ct"))&&(add==false)&& ((inp.substring(inp.length()-1).
                            equalsIgnoreCase(";")))){
                sql += inp.substring(0,inp.length()-1);
                System.out.println(sql);
                this.UserQueryTable(sql);
                sql="";
            }else if((inp.length()>=6)&&(inp.substring(0, 6).equalsIgnoreCase(""
                    + "select"))&&(add==false)&& (!(inp.substring(inp.
                            length()-1).equalsIgnoreCase(";")))){
                sql+=inp;
                add=true;
            }else if ((inp.length()>=1) && (!(inp.substring(inp.length()-1).
                    equalsIgnoreCase(";")))&&(add==true)){
                sql=sql+" "+inp;
            }else if ((inp.length()>=2) && (inp.substring((inp.length()-1)).
                    equalsIgnoreCase(";"))&&(add==true)){
                sql=sql+" "+inp.substring(0,inp.length()-1);
                send = true;
            }else{}
            slep+=1;
            if(slep == 20){
                try {
                    TimeUnit.MILLISECONDS.sleep(300);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, 
                            null, ex);
                }
                slep=0;
            }
            if(send==true){
                System.out.println();
                System.out.println("\nYour query: ");
                System.out.println(sql);
                this.UserQueryTable(sql);
                sql="";
                add=false;
                send = false;
            }
        }
        System.out.println("\nQueries Completed! \n \n");
    }
    /**
     * This Method allows the user to enter any query to the Database. 
     * @param query : SQL query for inserting the data in a table in database.
    */
    ResultSet UserQueryTable(String query){
        ResultSet resultSet = null  ;
        String inquery = query.trim(); //extract the query
        int size = inquery.length();
        if(inquery.charAt(size-1)==';'){//remove semicolon at the end
            inquery = inquery.substring(0,size-1);
        }
        try {
            Connection con = DriverManager.getConnection(url, username, 
                    password);
            if (con != null){
                System.out.println("Connected!");
                Statement statement = con.createStatement();
                
                resultSet = statement.executeQuery(inquery);
                System.out.println("\nThe result:\n");
                Printresult(resultSet);
                con.close();
                System.out.println("Connected terminated");
            }            
        }catch (SQLException ex1){
            System.out.println("Query aborted!");
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, 
                    ex1);
            return null;
        }
        return resultSet;
    }
    
    /**
     * This Method Prints the data in ResultSet to a formatted table.     
     * @param rs: ResultSet data to be displayed
     */
    public ArrayList<String> Printresult(ResultSet rs){
        ArrayList<String[]> attributes = new ArrayList<String[]>();
        ArrayList<String[]> queryres = new ArrayList<String[]>();
        ArrayList<String> outpt = new ArrayList<String>();
        String columnwidth[];
        int colwidth[];
       
        try {
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int count = rsMetaData.getColumnCount();
            columnwidth = new String[count];
            colwidth = new int[count];
            //System.out.println(count);
            String[] temprow = new String[count];
            for(int i = 0; i<count; i++) {
                /* because the column indexing in ResultSet class start from 1*/
                int k = i+1; 
                String[] temp = new String[3];
                temprow[i]= rsMetaData.getColumnName(k);
                temp[0] = rsMetaData.getColumnName(k);
                temp[1] = rsMetaData.getColumnTypeName(k);
                if(temp[1].equalsIgnoreCase("date")){
                    columnwidth[i] = "| %-10s ";
                }else{
                    columnwidth[i] = "| %-"+Integer.toString(rsMetaData.
                            getColumnDisplaySize(k))+"s ";
                }
                colwidth[i] = rsMetaData.getColumnDisplaySize(k);
                attributes.add(temp);
            }
            queryres.add(temprow);
            //System.out.println(resultSet);
            while(rs.next()){
                String[] temprow2 = new String[count];
                for(int i = 0; i<count; i++){
                    if(attributes.get(i)[1].equalsIgnoreCase("number")){
                        temprow2[i]= Long.toString(rs.getLong(attributes.
                                get(i)[0]));
                    }else if (attributes.get(i)[1].equalsIgnoreCase("float")){
                        temprow2[i]= Float.toString(rs.getInt(attributes.
                                get(i)[0]));
                    }else if (attributes.get(i)[1].equalsIgnoreCase("date")){
                        temprow2[i]= rs.getDate(attributes.get(i)[0]).
                                toString();
                    }else {
                        temprow2[i]= rs.getString(attributes.get(i)[0]);
                    }
                }
                queryres.add(temprow2);
            }
            //Printing formatted table
            String addi="";
            for(int i=0; i<count;i++){//line
                for (int j = 0;j<colwidth[i]+3;j++){
                    System.out.printf("-");
                    addi += "-";
                }
            }
            outpt.add(addi);
            System.out.println();
            for(int i=0; i<count;i++){//Table header
                System.out.printf(columnwidth[i],queryres.get(0)[i]);
                addi+= "columnwidth[i],queryres.get(0)[i]";
            }
            System.out.println("|");
            
            for(int i=0; i<count;i++){
                for (int j = 0;j<colwidth[i]+3;j++){
                    System.out.printf("-");
                }
            }
            System.out.println();
            int size = queryres.size();
            for(int i=1;i<size;i++){
                for(int j = 0; j<count;j++){
                    System.out.printf(columnwidth[j],queryres.get(i)[j]);
                }
                System.out.println("|");
                for(int n=0; n<count;n++){
                    for (int m = 0;m<colwidth[n]+3;m++){
                        System.out.printf("-");
                }
            }
            System.out.println();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return outpt;
    }
}
