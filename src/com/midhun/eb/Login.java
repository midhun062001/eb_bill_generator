package com.midhun.eb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JLabel l1,l2,l3,l4;
    JTextField tf1;
    JPasswordField pf2;
    JButton b1,b2,b3;
    JPanel p1,p2,p3,p4;
    Choice c1;

    Login(){
        super("Login Page");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        l1 = new JLabel("username");
        l1.setBounds(300,20,100,20);
        add(l1);

        tf1 = new JTextField(15);
        tf1.setBounds(400,20,150,20);
        add(tf1);

        l2 = new JLabel("password");
        l2.setBounds(300,60,100,20);
        add(l2);

        pf2 = new JPasswordField(15);
        pf2.setBounds(400,60,150,20);
        add(pf2);

        l3 = new JLabel("Logging in as");
        l3.setBounds(300,100,100,20);
        add(l3);

        c1 = new Choice();
        c1.add("Admin");
        c1.add("Customer");
        c1.setBounds(400,100,150,20);
        add(c1);

        ImageIcon ic1 = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image i1 = ic1.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT);
        b1 = new JButton("Login", new ImageIcon(i1));
        b1.setBounds(330,160,100,20);
        add(b1);

        ImageIcon ic2 = new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image i2 = ic2.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT);
        b2 = new JButton("Cancel", new ImageIcon(i2));
        b2.setBounds(450,160,100,20);
        add(b2);

        ImageIcon ic3 = new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image i3 = ic3.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT);
        b3 = new JButton("Signup", new ImageIcon(i3));
        b3.setBounds(380,200,100,20);
        add(b3);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        ImageIcon ic4 = new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
        Image i4 = ic4.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon icc4 = new ImageIcon(i4);
        l4 = new JLabel(icc4);
        l4.setBounds(0,0,250,250);
        add(l4);

        setLayout(new BorderLayout());

        setSize(640,300);
        setLocation(600,300);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == b1){
            try {
                Conn con = new Conn();

                String userName = tf1.getText();
                String pass = pf2.getText();
                String userType = c1.getSelectedItem();
                String query = "select * from login where username = '" + userName +"' and password = '"+pass +"' and user = '"+userType+"'";
                ResultSet rs = con.stmt.executeQuery(query);
                if(rs.next()){
                    String meter = rs.getString("meter_no");
                    new Project(meter,userType).setVisible(true);
                    this.setVisible(false);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Invalid login");
                    tf1.setText("");
                    pf2.setText("");
                }
            }
            catch (Exception e){
                e.printStackTrace();
                System.out.println("error "+e);
            }
        }
        else if(actionEvent.getSource() == b2){
            this.setVisible(false);
        }
        else if(actionEvent.getSource() == b3){
            this.setVisible(false);
            new Signup().setVisible(true);
        }

    }

    public static void main(String[] args){
        new Login().setVisible(true);
    }


}
