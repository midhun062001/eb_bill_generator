package com.midhun.eb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateInformation extends JFrame implements ActionListener {

    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9;
    JTextField t1,t2,t3,t4,t5;
    Button b1,b2;
    String meter;
    UpdateInformation(String meter){
        this.meter = meter;

        setBounds(500,220,1060,450);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel title = new JLabel("Update Customer Information");
        title.setBounds(110,20,400,30);
        title.setFont(new Font("Tahoma",Font.PLAIN,20));
        add(title);

        l1 = new JLabel("Name");
        l1.setBounds(110,60,200,20);
        add(l1);

        l2 = new JLabel();
        l2.setBounds(340,60,200,20);
        add(l2);

        l3 = new JLabel("Meter No");
        l3.setBounds(110,100,200,20);
        add(l3);

        l4 = new JLabel(meter);
        l4.setBounds(340,100,200,20);
        add(l4);

        l5 = new JLabel("Address");
        l5.setBounds(110,140,200,20);
        add(l5);

        t1 = new JTextField();
        t1.setBounds(340,140,200,20);
        add(t1);

        l6 = new JLabel("City");
        l6.setBounds(110,180,200,20);
        add(l6);

        t2 = new JTextField();
        t2.setBounds(340,180,200,20);
        add(t2);

        l7 = new JLabel("State");
        l7.setBounds(110,220,200,20);
        add(l7);

        t3 = new JTextField();
        t3.setBounds(340,220,200,20);
        add(t3);

        l8 = new JLabel("Email");
        l8.setBounds(110,260,200,20);
        add(l8);

        t4 = new JTextField();
        t4.setBounds(340,260,200,20);
        add(t4);

        l9 = new JLabel("Phone");
        l9.setBounds(110,300,200,20);
        add(l9);

        t5 = new JTextField();
        t5.setBounds(340,300,200,20);
        add(t5);

        b1 = new Button("Update");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setBounds(210,340,100,20);

        b2 = new Button("Cancel");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.setBounds(350,340,100,20);

        b1.addActionListener(this);
        b2.addActionListener(this);

        add(b1);
        add(b2);

        try{
            Conn con = new Conn();
            ResultSet rs = con.stmt.executeQuery("SELECT * FROM customer WHERE meter = '" + meter +"'");
            while (rs.next()) {
                l2.setText(rs.getString(1));
                l4.setText(rs.getString(2));
                t1.setText(rs.getString(3));
                t2.setText(rs.getString(4));
                t3.setText(rs.getString(5));
                t4.setText(rs.getString(6));
                t5.setText(rs.getString(7));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        ImageIcon ic1 = new ImageIcon(ClassLoader.getSystemResource("icon/update.jpg"));
        Image i1 = ic1.getImage().getScaledInstance(400,300,Image.SCALE_DEFAULT);
        ImageIcon ic2 = new ImageIcon(i1);
        JLabel l9 = new JLabel(ic2);

        l9.setBounds(600,50,400,300);
        add(l9);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == b1){
            String s1 = l2.getText();
            String s2 = l4.getText();
            String s3 = t1.getText();
            String s4 = t2.getText();
            String s5 = t3.getText();
            String s6 = t4.getText();
            String s7 = t5.getText();

            try{
                Conn con = new Conn();
                con.stmt.executeUpdate("UPDATE customer SET address = '"+s3+"', city = '"+s4+"', state = '"+s5+"', email = '"+s6+"', phone = '"+s7+"' WHERE meter = '"+meter+"'");
                JOptionPane.showMessageDialog(null, "Details Updated Successfully");
                this.setVisible(false);

            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        else if(ae.getSource() == b2){
            this.setVisible(false);
        }
    }
    public static void main(String... args){
        new UpdateInformation("sample").setVisible(true);
    }
}
