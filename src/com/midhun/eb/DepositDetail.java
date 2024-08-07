package com.midhun.eb;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterAbortException;
import java.awt.print.PrinterException;
import java.io.IOError;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.proteanit.sql.DbUtils;


public class DepositDetail extends JFrame implements ActionListener {

    JTable t1;
    JButton b1,b2;
    JLabel l1,l2;
    Choice c1,c2;
    String[] x = {"Meter Number","Month","Units","Total Bill","Status"};
    String[][] y = new String[40][8];
    int i=0,j=0;

    DepositDetail(){
        super("Deposit Details");
        setLocation(600,150);
        setSize(700,750);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        l1 = new JLabel("Short by Meter Number");
        l1.setBounds(20,20,150,20);
        add(l1);

        c1 = new Choice();

        l2 =new JLabel("Sort by month");
        l2.setBounds(400,20,100,20);
        add(l2);

        c2 = new Choice();

        t1 = new JTable(y,x);

        try{
            Conn con = new Conn();
            String query1 = "SELECT * FROM bill";
            ResultSet rs = con.stmt.executeQuery(query1);

            t1.setModel(DbUtils.resultSetToTableModel(rs));

            String query2 = "SELECT * FROM customer";
            rs = con.stmt.executeQuery(query2);

            while(rs.next()){
                c1.add(rs.getString("meter"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        c1.setBounds(180,20,150,20);
        add(c1);

        c2.setBounds(520,20,150,20);
        c2.add("January");
        c2.add("February");
        c2.add("March");
        c2.add("April");
        c2.add("May");
        c2.add("June");
        c2.add("July");
        c2.add("August");
        c2.add("September");
        c2.add("October");
        c2.add("November");
        c2.add("December");
        add(c2);

        b1 = new JButton("Search");
        b1.setBounds(20,70,120,20);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Print");
        b2.setBounds(180,70,120,20);
        b2.addActionListener(this);
        add(b2);

        JScrollPane sp = new JScrollPane(t1);
        sp.setBounds(0,100,700,650);
        add(sp);

    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            String query  ="SELECT * FROM bill WHERE meter = '"+c1.getSelectedItem()+"' AND month = '"+c2.getSelectedItem()+"'";

            try{
                Conn con = new Conn();
                ResultSet rs = con.stmt.executeQuery(query);
                t1.setModel(DbUtils.resultSetToTableModel(rs));
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        else if(ae.getSource() == b2){
            try{
                t1.print();
            }catch (PrinterException e ){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args){
        new DepositDetail().setVisible(true);
    }
}
