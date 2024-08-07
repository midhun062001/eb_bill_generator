package com.midhun.eb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CalculateBill extends JFrame implements ActionListener {
    JLabel l1,l2,l3,l4,l5,l6,l7,l31,l41;
    JTextField t1;
    Choice c1,c2;
    JButton b1,b2;
    JPanel p;
    CalculateBill(){
        super("Bill Calculator");
        p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173,216,230));

        l1 = new JLabel("Calculate electricity bill");
        l1.setBounds(60,10,400,30);

        l2 = new JLabel("Meter No");
        l2.setBounds(60,70,200,30);

        l3 = new JLabel("Name");
        l3.setBounds(60,120,200,30);

        l4 = new JLabel("Address");
        l4.setBounds(60,170,200,30);

        l5 = new JLabel("Units Consumed");
        l5.setBounds(60,220,200,30);

        l6 = new JLabel("Month");
        l6.setBounds(60,270,200,30);

        c1 = new Choice();
        c1.setBounds(300,70,200,30);
        try{
            Conn con  = new Conn();
            ResultSet rs = con.stmt.executeQuery("SELECT * FROM customer");
            while(rs.next()){
                c1.add(rs.getString("meter"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        l31 = new JLabel();
        l31.setBounds(300,120,200,30);

        l41 = new JLabel();
        l41.setBounds(300,170,200,30);
        p.add(l31);
        p.add(l41);

        try{
            Conn con = new Conn();
            ResultSet rs = con.stmt.executeQuery("SELECT * FROM customer WHERE meter = '"+c1.getSelectedItem()+"'");
            while (rs.next()){
                l31.setText(rs.getString("name"));
                l41.setText(rs.getString("address"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        c1.addItemListener(ae -> { //lambda expression
            try{
                Conn con = new Conn();
                ResultSet rs = con.stmt.executeQuery("select * from customer where meter = '"+c1.getSelectedItem()+"'");
                while(rs.next()){
                    l31.setText(rs.getString("name"));
                    l41.setText(rs.getString("address"));
                }
            }catch(Exception e){}
        });

        t1 = new JTextField();
        t1.setBounds(300, 220, 200, 20);

        b1 = new JButton("Submit");
        b1.setBounds(140, 350, 100, 25);
        b2 = new JButton("Cancel");
        b2.setBounds(320, 350, 100, 25);

        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);

        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(180, 270,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        l7 = new JLabel(i3);



        l1.setFont(new Font("Senserif",Font.PLAIN,26));
        //Move the label to center
        l1.setHorizontalAlignment(JLabel.CENTER);

        c2 = new Choice();
        c2.setBounds(300, 270, 200, 20);
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

        p.add(l1);
        p.add(l2);
        p.add(l3);
        p.add(l4);
        p.add(l5);
        p.add(l6);
        p.add(c1);
        p.add(c2);
        p.add(t1);
        p.add(b1);
        p.add(b2);

        setLayout(new BorderLayout(30,30));

        add(p,"Center");
        add(l7,"West");

        b1.addActionListener(this);
        b2.addActionListener(this);


        getContentPane().setBackground(Color.WHITE);
        setSize(750,500);
        setLocation(550,220);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == b1){
            String meterNo = c1.getSelectedItem();
            String units = t1.getText();
            String month = c2.getSelectedItem();

            int unitsConsumed = Integer.parseInt(units);

            int totalBill = 0;

            try{
                Conn con = new Conn();
                ResultSet rs = con.stmt.executeQuery("SELECT * FROM tax");
                while (rs.next()){
                    totalBill = unitsConsumed * Integer.parseInt(rs.getString("cost_per_unit")); // 120 * 9
                    totalBill += Integer.parseInt(rs.getString("meter_rent"));
                    totalBill += Integer.parseInt(rs.getString("service_charge"));
                    totalBill += Integer.parseInt(rs.getString("service_tax"));
                    totalBill += Integer.parseInt(rs.getString("swacch_barath_cess"));
                    totalBill += Integer.parseInt(rs.getString("fixed_tax"));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }

            String query ="INSERT INTO bill VALUES('"+meterNo + "','"
                    + month + "','" + totalBill + "','Not Paid','" + units +"')";
            try{
                Conn con =new Conn();
                con.stmt.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Customer bill updated successfully !");
                this.setVisible(false);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        else if(ae.getSource() == b2){
            this.setVisible(false);
        }
    }

    public static void main(String[] args){
        new CalculateBill().setVisible(true);
    }
}
