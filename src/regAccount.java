
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LENOVO
 */
public class regAccount {
    public int regAcc(String fname, String lname, String uname, String pass){
        int x = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/berioreg?","root","");
            String sql = "insert into register values(?,?,?,md5(?));";
            
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, fname);
             pstmt.setString(2, lname);
              pstmt.setString(3, uname);
               pstmt.setString(4, pass);
               
               x= pstmt.executeUpdate();
            
            
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(regAccount.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(regAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return x;
    }
    public int confirmPass(String pass, String npass){
        int x = 0 ;
        if(pass.equals(npass)){
            x=1;
        }else{
            x=0;
        }
        return x;
    }
    public int confirmUser(String username){
        int x =0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/berioreg?","root","");
            String sql = "select * from register where username=?;";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                x=1;
            }else{
                x=0;
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(regAccount.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(regAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }
}
