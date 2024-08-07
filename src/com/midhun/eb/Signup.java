package com.midhun.eb;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Signup extends JFrame implements ActionListener {

    JPanel p1;
    JTextField t1,t2,t3,t4;
    JLabel l1,l2,l3,l4,l5,l6;
    Choice c1;
    JButton b1,b2;

    Signup(){
        super("Signup");
        setBounds(600, 250, 700, 400);

        p1 = new JPanel();
        p1.setBounds(30,30,450,300);
        p1.setLayout(null);
        p1.setBackground(Color.WHITE);
        p1.setForeground(new Color(36,139,34));
        p1.setBorder(new TitledBorder(new LineBorder(new Color(173,216,230),2), "Create-Account",TitledBorder.LEADING,TitledBorder.TOP,null,new Color(173, 216, 230)));
        add(p1);

        l1 = new JLabel("Username");
        l1.setForeground(Color.DARK_GRAY);
        l1.setFont(new Font("Tahoma",Font.BOLD,14));
        l1.setBounds(100,50,100,20);
        p1.add(l1);

        t1 = new JTextField();
        t1.setBounds(260,50,150,20);
        p1.add(t1);

        l2 = new JLabel("Name");
        l2.setForeground(Color.DARK_GRAY);
        l2.setFont(new Font("Tahoma",Font.BOLD,14));
        l2.setBounds(100,90,100,20);
        p1.add(l2);

        t2 = new JTextField();
        t2.setBounds(260,90,150,20);
        p1.add(t2);

        l3 = new JLabel("Password");
        l3.setForeground(Color.DARK_GRAY);
        l3.setFont(new Font("Tahoma",Font.BOLD,14));
        l3.setBounds(100,130,100,20);
        p1.add(l3);

        t3 = new JTextField();
        t3.setBounds(260,130,150,20);
        p1.add(t3);

        l4 = new JLabel("Create Account As");
        l4.setForeground(Color.DARK_GRAY);
        l4.setFont(new Font("Tahoma",Font.BOLD,14));
        l4.setBounds(100,170,140,20);
        p1.add(l4);

        c1 = new Choice();
        c1.add("Admin");
        c1.add("Customer");
        c1.setBounds(260,170,150,20);
        p1.add(c1);

        l5 = new JLabel("Meter Number");
        l5.setForeground(Color.DARK_GRAY);
        l5.setFont(new Font("Tahoma",Font.BOLD,14));
        l5.setBounds(100,210,100,20);
        l5.setVisible(false);
        p1.add(l5);

        t4 = new JTextField();
        t4.setBounds(260,210,150,20);
        t4.setVisible(false);
        p1.add(t4);

        c1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                String user = c1.getSelectedItem();
                if(user.equals("Customer")){
                    l5.setVisible(true);
                    t4.setVisible(true);
                } else{
                    l5.setVisible(false);
                    t4.setVisible(false);
                }
            }
        });

        b1 = new JButton("create");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setBounds(140,290,120,30);
        b1.addActionListener(this);
        p1.add(b1);

        b2 = new JButton("login");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.setBounds(300,290,120,30);
        b2.addActionListener(this);
        p1.add(b2);

        ImageIcon ic1 = new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
        Image i1 = ic1.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon icc1 = new ImageIcon(i1);
        l6 = new JLabel(icc1);
        l6.setBounds(450,30,250,250);
        p1.add(l6);



    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == b1){
            String userName = t1.getText();
            String name = t2.getText();
            String password = t3.getText();
            String user = c1.getSelectedItem();
            String meter = t4.getText();

            try{
                Conn con = new Conn();
                String query = null;
                if(user.equals("Admin")){
                    query = "insert into login values('"+meter+"','"+userName+"','"+name+"','"+password+"','"+user+"')";
                } else{
                    query = "update login set username = '"+userName+"', name = '"+name+"', password = '"+password+"', user = '"+user+"' where meter_no = '"+t4.getText()+"'";
                }
                con.stmt.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Account created Successfully");
                this.setVisible(false);
                new Login().setVisible(true);
            } catch(Exception e){
                e.printStackTrace();
            }
        }

        else if(actionEvent.getSource() == b2){
            this.setVisible(false);
            new Login().setVisible(true);
        }
    }

    public static void main(String[] args){
        new Signup().setVisible(true);
    }
}
