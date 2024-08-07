package com.midhun.eb;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
public class About extends JFrame implements ActionListener {
    JButton b1;
    JLabel l1;
    Font f,f1,f2;
    TextArea t1;
    String s;

    public About(){
        setLayout(null);

        b1 = new JButton("Exit");
        add(b1);
        b1.setBounds(180,430,120,20);
        b1.addActionListener(this);
        f = new Font("RALEWAY",Font.BOLD,180);
        setFont(f);
        String s = "The EB Bill Generator project aims to create an efficient, user-friendly, " +
                "and automated system for generating electricity bills. This project addresses " +
                "the need for accurate and timely billing in the electricity supply industry, " +
                "reducing manual errors and improving the overall customer experience. The objectives " +
                "include automating bill calculation based on units consumed and tariff rates, implementing " +
                "a user management system for account management, generating detailed bills with consumption " +
                "details and payment options, integrating secure online payment gateways, and developing a " +
                "notification system for bill generation and due dates. Key features include a user-friendly " +
                "interface for web and mobile platforms, automated meter reading integration, tariff management, " +
                "consumption analytics, multi-language support, and robust data security. The technical " +
                "requirements involve using modern web technologies for the frontend, reliable server-side " +
                "technology for the backend, a scalable database system, RESTful APIs for communication, and " + "cloud hosting for scalability and reliability. The development phases encompass requirement " +
                "gathering, design, development, testing, deployment, and maintenance. Benefits include enhanced " +
                "efficiency, accuracy, convenience, insights into consumption, and scalability. The EB Bill " +
                "Generator project is a significant step towards modernizing the electricity billing process, " +
                "aiming to improve accuracy, convenience, and customer satisfaction in the electricity supply industry.";

        t1 = new TextArea(s,10,40,Scrollbar.VERTICAL);
        t1.setEditable(false);
        t1.setBounds(20,100,450,300);

        add(t1);

        f1 = new Font("RALEWAY",Font.BOLD,16);
        t1.setFont(f1);

        Container contentPane = this.getContentPane();
        t1 = new TextArea();

        l1 = new JLabel("About Project");
        add(l1);
        l1.setBounds(170,10,180,80);
        l1.setForeground(Color.red);

        f2 = new Font("RALEWAY",Font.BOLD,20);
        l1.setFont(f2);

        setBounds(700,220,500,550);

        setLayout(null);
        setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        dispose();
    }

    public static void main(String[] args){
        new About().setVisible(true);
    }
}
