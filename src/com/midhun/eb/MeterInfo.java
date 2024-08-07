package com.midhun.eb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MeterInfo extends JFrame implements ActionListener {
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11;
    Choice c1,c2,c3,c4;
    JButton b1,b2;

    public MeterInfo(String meter) {
        super("Meter Information");
        setLocation(600,200);
         setSize(810,500);

         JPanel p = new JPanel();
         p.setLayout(null);
         p.setBackground(new Color(173,216,230));

         JLabel title = new JLabel("Meter Information");
         title.setBounds(100,30,300,26);
         title.setFont(new Font("Tahoma", Font.BOLD,24));
         p.add(title);

         l1 = new JLabel("Meter Number");
         l1.setBounds(100,80,200,20);

         l2 = new JLabel(meter);
         l2.setBounds(340,80,200,20);

         p.add(l1);
         p.add(l2);

         l3 = new JLabel("Meter Location");
         l3.setBounds(100,120,200,20);

         c1 = new Choice();
         c1.add("Inside");
         c1.add("Outside");
         c1.setBounds(340,120,200,20);

         p.add(l3);
         p.add(c1);

         l4 = new JLabel("Meter Type");
         l4.setBounds(100,160,200,20);

         c2 = new Choice();
         c2.add("Electric Meter");
         c2.add("Smart Meter");
         c2.add("Solar Meter");
         c2.setBounds(340,160,200,20);

         p.add(l4);
         p.add(c2);

         l5 = new JLabel("Phase Code");
         l5.setBounds(100,200,200,20);

         c3 = new Choice();
         c3.add("011");
         c3.add("022");
         c3.add("033");
         c3.add("044");
         c3.add("055");
         c3.add("066");
         c3.add("077");
         c3.add("088");
         c3.add("099");
         c3.setBounds(340,200,200,20);

         p.add(l5);
         p.add(c3);

         l6 = new JLabel("Bill Type");
         l6.setBounds(100,240,200,20);

         c4 = new Choice();
         c4.add("Industrial");
         c4.add("Normal");
         c4.setBounds(340,240,200,20);

         p.add(l6);
         p.add(c4);

         l7 = new JLabel("Days");
         l7.setBounds(100,280,200,20);

         l8 = new JLabel("30");
         l8.setBounds(340,280,200,20);

         p.add(l7);
         p.add(l8);

         l9 = new JLabel("Note: By default bill is generated for 30 days !");
         l9.setBounds(100,320,440,20);

         p.add(l9);

         b1 = new JButton("Submit");
         b1.setBounds(200,390,100,20);

         b2 = new JButton("Cancel");
         b2.setBounds(340,390,100,20);

         p.add(b1);
         p.add(b2);
         setLayout(new BorderLayout());

         add(p,"Center");

        ImageIcon ic1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i3 = ic1.getImage().getScaledInstance(170, 300,Image.SCALE_DEFAULT);
        ImageIcon ic2 = new ImageIcon(i3);
        l11 = new JLabel(ic2);


        add(l11,"West");
        //for changing the color of the whole Frame
        getContentPane().setBackground(Color.WHITE);

        b1.addActionListener(this);
        b2.addActionListener(this);


    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            String meterNumber = l2.getText();
            String meterLocation = c1.getSelectedItem();
            String meterType = c2.getSelectedItem();
            String phaseCode = c3.getSelectedItem();
            String billType = c4.getSelectedItem();
            String days = "30";

            String query = "INSERT INTO meter_info VALUES('"+meterNumber+"','"
                    + meterLocation + "','" + meterType + "','"
                    + phaseCode + "','" + billType + "','"
                    + days + "')";
            try{
                Conn con =new Conn();
                con.stmt.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"meter info added successfully");
                this.setVisible(false);
            }catch (SQLException e){
                e.printStackTrace();
            }
        } else if (ae.getSource() == b2) {
            this.setVisible(false);
        }
    }
    public static void main(String... args){
        new MeterInfo("sample").setVisible(true);
    }
}
