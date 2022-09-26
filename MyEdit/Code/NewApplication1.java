/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

import java.awt.event.ActionEvent;
import java.nio.channels.MembershipKey;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListModel;


/**
 *
 * @author karim
 */
public class NewApplication1 extends javax.swing.JFrame {
    ArrayList<My_File> File_info;
    boolean Open_Nf = true;
    static NewFile nf;
    /**
     * Creates new form NewApplication
     */
   
    public NewApplication1(String name, String Pass) throws SQLException {
        
        initComponents(name, Pass);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("checked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code"> 
    // the name of the method should be self explanatory
    
    // seperate method for file info such as members and admins
    public My_File get_file_info(String name) throws SQLException{
        java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb_2", "root", "pokemon12");
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM files WHERE File_name = ?");
        ps.setString(1,name);
        ResultSet rs = ps.executeQuery();
        rs.next();
        String creator = rs.getString("Creator");
        String[] admins = rs.getString("Admin").split(" ");
        String[] members = rs.getString("Members").split(" ");
        String[] only_view = rs.getString("Spectators").split(" ");
        String content = rs.getString("File_Content");
        
        rs.close();
        My_File curr = new My_File(name,creator,members,admins,only_view,content);
        return curr;
        
    }
    
    public String get_file_namesDB(String username) throws SQLException{
        java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb_2", "root", "pokemon12");
        PreparedStatement ps = conn.prepareStatement("SELECT File_names FROM users WHERE Username = ?");
        ps.setString(1,username);
        ResultSet myRs = ps.executeQuery();
        myRs.next();
        String Files = myRs.getString("File_names");
        myRs.close();
        return Files;
    }
    
    
    
    public void initComponents(String name, String Pass) throws SQLException {
        
        
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        
        //this part of the code is to display the names of the files that the user has
        java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb_2", "root", "pokemon12");
        String[] st_files = null;
        String Files = get_file_namesDB(name);
        User Curr = new User(name,Pass);
        if(Files != null){
            st_files = Files.split(" ");
            Curr.setFile_Names(st_files);
        }
        //this part of the code is the part where we create the files for the user to be able to open and edit them.
        if(st_files != null){
            int size = st_files.length;
            File_info = new ArrayList<My_File>();
            PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM files");
            ResultSet rs = ps2.executeQuery();
            int i = 0;
            while(rs.next() && i<size){
                String curr_name = rs.getString("File_name");
                String creat = rs.getString("Creator");
                String[] admins = rs.getString("Admin").split(" ");
                String[] members = rs.getString("Members").split(" ");  
                String[] spects = rs.getString("Spectators").split(" ");
                String content = rs.getString("File_Content");
                if(st_files[i].equals(curr_name)){
                    My_File temp = new My_File(st_files[i],creat,members,admins,spects,content);
                    File_info.add(temp);
                }
                i++;
            }
            rs.close();
        }
        PreparedStatement ps4 = conn.prepareStatement("SELECT Username FROM users");
        ResultSet toRs = ps4.executeQuery();
        ArrayList<String> user = new ArrayList<String>();
        while(toRs.next()){
            String names = toRs.getString("Username");
            user.add(names);
        }


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = Curr.getFile_Names();
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = user.toArray(new String[user.size()]);
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList2);

        jButton1.setText("Open");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String sel = jList1.getSelectedValue();
                    jButton1ActionPerformed(e,sel,name);
                } catch (SQLException ex) {
                    Logger.getLogger(NewApplication1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
       

        jButton2.setText("New");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt,Curr.getName());
                
            }
        });

        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton3ActionPerformed(evt, name);
                } catch (SQLException ex) {
                    Logger.getLogger(NewApplication1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        

        jButton4.setText("Refresh");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton4ActionPerformed(evt, name);
                } catch (SQLException ex) {
                    Logger.getLogger(NewApplication1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        jButton5.setText("Info");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton5ActionPerformed(evt, name);
                } catch (SQLException ex) {
                    Logger.getLogger(NewApplication1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5)))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        pack();
        
        
        
    }// </editor-fold>       
    public void jButton1ActionPerformed(java.awt.event.ActionEvent evt, String name, String username) throws SQLException{
        
        My_File curr = get_file_info(name);
        String[] only_view = curr.getOnly_view();
        String cont = curr.getContent();
        System.out.println(Arrays.asList(only_view).contains(username));
        //System.out.println(only_view.toString());

        if(Arrays.asList(only_view).contains(username)){
            Only_View_ed OVE = new Only_View_ed(cont);
            OVE.setVisible(true);
            JOptionPane.showMessageDialog(this, "You can only view this document");
        }else{
            System.out.println("HERE!!");
            Open_Editor od = new Open_Editor(cont,name);
            od.setVisible(true);
        
        }
    }

    public void jButton2ActionPerformed(java.awt.event.ActionEvent evt,String username) {                                         
        // TODO add your handling code here:
        nf = new NewFile(username);
        nf.setVisible(true);
        
    }
    public void jButton3ActionPerformed(java.awt.event.ActionEvent evt, String name) throws SQLException {                                         
        // TODO add your handling code here:
        
        String sel = jList1.getSelectedValue();
        System.out.println(sel);
        My_File curr = get_file_info(sel);
        String[] admins = curr.getAdmins();
        String[] members = curr.getMembers();
        if(Arrays.asList(admins).contains(name)){
            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb_2", "root", "pokemon12");
            PreparedStatement ps;
            ps = conn.prepareStatement("DELETE FROM files WHERE File_name = ?");
            ps.setString(1,sel);
            int rs = ps.executeUpdate();
            String[] temp = get_file_namesDB(name).split(" ");
            String nw = "";
            for(int i=0; i<temp.length ; i++){
                nw += temp[i];
                nw += " ";
            }
            String replaceAll = nw.replace(sel, "");
            PreparedStatement ps2;
            ps2 = conn.prepareStatement("UPDATE users SET File_names = ? WHERE Username = ?");
            ps2.setString(1,replaceAll);
            for(int i = 0; i<members.length; i++ ){
                ps2.setString(2,name);
                boolean rs2 = ps2.execute();
            }
        } else {
            JOptionPane.showMessageDialog(this, "You dont have permission to delete this document");
        }
        
        

    }
    
    public void jButton4ActionPerformed(java.awt.event.ActionEvent evt, String name) throws SQLException {
        String[] files = get_file_namesDB(name).split(" ");
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = files;
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);
        
    }
    public void jButton5ActionPerformed(java.awt.event.ActionEvent evt, String name) throws SQLException {
        String sel = jList1.getSelectedValue();
        My_File temp = get_file_info(sel);
        String creator = temp.getCreator();
        String Admins = Arrays.toString(temp.getAdmins());
        String members = Arrays.toString(temp.getMembers());
        String Specs = Arrays.toString(temp.getOnly_view());
        JOptionPane.showMessageDialog(this, "Creator : " + creator + "\n" + 
        "Admins : " + Admins + "\n" + "Members : " + members + "\n" + "Only view : " + Specs );
    
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewApplication1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewApplication1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewApplication1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewApplication1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new NewApplication().setVisible(true);
            }
        });
    }
    
    

    // Variables declaration - do not modify   
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    public javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration                   
}

