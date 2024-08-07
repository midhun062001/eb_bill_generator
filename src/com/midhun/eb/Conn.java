package com.midhun.eb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Conn {
    Connection con;
    Statement stmt;

    String url = "jdbc:mysql://localhost:3306/ebs";
    String user = "midhun";
    String pass = "123";

    public Conn(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,user,pass);
            stmt = con.createStatement();
        } catch (ClassNotFoundException e) {
            System.out.println(e);;
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
