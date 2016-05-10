package com.tutorialspoint.struts2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

   private String user_id;
   private String first_name;
   private String role_name;
   //private String pass;

   public String execute() {
      String ret = ERROR;
      Connection conn = null;

      try {
         String URL = "jdbc:mysql://localhost:3306/manage";
         Class.forName("com.mysql.jdbc.Driver");
         conn = DriverManager.getConnection(URL, "root", "12345");
         String sql = "SELECT first_name FROM user_tbl WHERE";
         //String sql = "SELECT A.ROLE_NAME FROM role_tbl A, user_tbl B WHERE B.USER_ID = ? AND A.ROLE_ID = B.ROLE_ID";
         sql+=" user_id = ? ";
         PreparedStatement ps = conn.prepareStatement(sql);
         ps.setString(1, user_id);
         //ps.setString(2, "MANAGER");
         ResultSet rs = ps.executeQuery();

         while (rs.next()) {
            user_id = rs.getString(1);
            ret = SUCCESS;
         }
      } catch (Exception e) {
         ret = ERROR;
      } finally { 
         if (conn != null) {
            try {
               conn.close();
            } catch (Exception e) {
            }
         }
      }
      return ret;
   }

   public String getUser() {
      return user_id;
   }

   public void setUser(String user_id) {
      this.user_id = user_id;
   }

    
   public String getRole_name() {
	  return role_name;
     }

   public void setRole_name(String role_name) {
	  this.role_name = role_name;
}
   
   
   
}