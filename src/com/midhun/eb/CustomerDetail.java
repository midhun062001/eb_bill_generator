package com.midhun.eb;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDetail extends JFrame implements ActionListener {

    //constructor
    JTable t1;
    JButton b1;
    String x[] = {"Customer Name","Meter Number","Address","City","State","Email","Phone"};
    String y[][] = new String[40][8];
    int i=0,j=0;
    CustomerDetail(){
        super("Customer Detail");
        setSize(1200,650);
        setLocation(400,150);

        try{
            Conn con = new Conn();
            String query = "SELECT * FROM customer";
            ResultSet rs = con.stmt.executeQuery(query);
            while(rs.next()) {
                y[i][j++] = rs.getString("name");
                y[i][j++] = rs.getString("meter");
                y[i][j++] = rs.getString("address");
                y[i][j++] = rs.getString("city");
                y[i][j++] = rs.getString("state");
                y[i][j++] = rs.getString("email");
                y[i][j++] = rs.getString("phone");
                i++;
                j = 0;
            }
            t1 = new JTable(y,x);
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        b1 = new JButton("Print");
        add(b1,"South");
        JScrollPane sp =new JScrollPane(t1);
        add(sp);

        b1.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        try{
            t1.print();
        } catch (PrinterException e) {
           e.printStackTrace();
        }
    }

    public static  void main(String[] args){
        new CustomerDetail().setVisible(true);
    }
}
