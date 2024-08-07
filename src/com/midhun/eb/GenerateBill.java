package com.midhun.eb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class GenerateBill extends JFrame implements ActionListener {

    JButton b1;
    Choice c2;
    JTextArea t1;
    String meter;

    GenerateBill(String meter){
        this.meter = meter;
        setBounds(750,100,500,900);
        setLayout(new BorderLayout());

        JPanel p = new JPanel();

        JLabel l1 = new JLabel("Generate Bill");

        JLabel l2 = new JLabel(meter);

        c2 = new Choice();
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
        p.add(c2);

        t1 = new JTextArea();
        t1.setText("\n\n\t------- Click on the -------\n\t Generate Bill Button to get\n\tthe bill of the Selected Month\n\n");
        JScrollPane jsp = new JScrollPane(t1);
        t1.setFont(new Font("Sanserif",Font.ITALIC,18));

        b1 = new JButton("Generate Bill");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);

        add(p,"North");
        add(jsp,"Center");
        add(b1,"South");
        b1.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        try{
            Conn con = new Conn();

            String month = c2.getSelectedItem();
            t1.setText("\tMJ Power Limited\n ELECTRICITY BILL FOR THE MONTH OF "+month+" , 2024\n\n\n");

            ResultSet rs = con.stmt.executeQuery("SELECT * FROM customer WHERE meter = '" + meter + "'");

            if(rs.next()){
                t1.append("\n    Customer Name:"+rs.getString("name"));
                t1.append("\n    Meter Number:  "+rs.getString("meter"));
                t1.append("\n    Address:            "+rs.getString("address"));
                t1.append("\n    State:                 "+rs.getString("state"));
                t1.append("\n    City:                   "+rs.getString("city"));
                t1.append("\n    Email:                "+rs.getString("email"));
                t1.append("\n    Phone Number  "+rs.getString("phone"));
                t1.append("\n-------------------------------------------------------------");
                t1.append("\n");
            }

            rs = con.stmt.executeQuery("SELECT * FROM meter_info WHERE meter_no = '" + meter + "'");

            if(rs.next()){
                t1.append("\n    Meter Location:"+rs.getString("meter_location"));
                t1.append("\n    Meter Type:      "+rs.getString("meter_type"));
                t1.append("\n    Phase Code:    "+rs.getString("phase_code"));
                t1.append("\n    Bill Type:         "+rs.getString("bill_type"));
                t1.append("\n    Days:               "+rs.getString("days"));
                t1.append("\n");
            }

            rs = con.stmt.executeQuery("select * from tax");
            if(rs.next()){
                t1.append("---------------------------------------------------------------");
                t1.append("\n\n");
                t1.append("\n Cost per Unit:             Rs "+rs.getString("cost_per_unit"));
                t1.append("\n Meter Rent:                Rs "+rs.getString("meter_rent"));
                t1.append("\n Service Charge:            Rs "+rs.getString("service_charge"));
                t1.append("\n Service Tax:               Rs "+rs.getString("service_tax"));
                t1.append("\n Swacch Bharat Cess:        Rs "+rs.getString("swacch_barath_cess"));
                t1.append("\n Fixed Tax:                 Rs "+rs.getString("fixed_tax"));
                t1.append("\n");

            }

            rs = con.stmt.executeQuery("select * from bill where meter="+meter+" AND month = '"+c2.getSelectedItem()+"'");

            if(rs.next()){
                t1.append("\n    Current Month :\t"+rs.getString("month"));
                t1.append("\n    Units Consumed:\t"+rs.getString("unit"));
                t1.append("\n    Total Charges :\t"+rs.getString("total_bill"));
                t1.append("\n---------------------------------------------------------------");
                t1.append("\n    TOTAL PAYABLE :\t"+rs.getString("total_bill"));
            }

        }catch (Exception e){}
    }

    public static void main(String... args){
        new GenerateBill("sample").setVisible(true);
    }
}
