package p5;
import p4.*;

import java.sql.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class front extends JFrame implements ActionListener,ItemListener//,FocusListener,
{
	JLabel passp,namep;
	 String pass1="";
	 String name1="";
	static String a="",b="";
	TextField pass,name;
	JButton SUBMIT;
	public front()
	{
		super();
			JPanel q=new JPanel();
		/////////////////////////
		JLabel ji=new JLabel()
	{
		public void paint(Graphics g)
		{
			ImageIcon ii=new ImageIcon("C:\\Documents and Settings\\RAJESH\\Desktop\\GUI122.jpg");
			Image img123=ii.getImage();
			g.drawImage(img123,0,0,1300,750,this);
		}
		
		};	
		ji.setBounds(0,0,1300,800);
		
		
		q.add(ji);
		//////////////////////////////
		passp=new JLabel ("Password ");
		namep=new JLabel ("User Name ");
	
		pass=new TextField(6);
			pass.setEchoChar('*');
		name=new TextField(15);
		name.addActionListener(this);
		pass.addActionListener(this);
		
		SUBMIT=new JButton("Login");
	SUBMIT.addActionListener(this);
		
			
		
		
		q.setLayout(null);
		namep.setBounds(600,480,100,20);
		q.add(namep);
		name.setBounds(680,450,150,20);
		q.add(name);
		passp.setBounds(600,540,100,20);
		q.add(passp);
		pass.setBounds(680,510,150,20);
		q.add(pass);
		SUBMIT.setBounds(645,612,95,28);
		q.add(SUBMIT);
		
		add(q,BorderLayout.CENTER);
		
		setSize(1365,740);
		setVisible(true);
	}
	public void itemStateChanged(ItemEvent ie)
	{
		repaint();
	}
	

	public void actionPerformed(ActionEvent ae)
  {JButton b=(JButton)ae.getSource();
		if(b==SUBMIT)
		{
			try
			{
				pass1=pass.getText();
				name1=name.getText();
				if (name1.equals("hosp") && pass1.equals("hosp")) 
				{
					
				pass.setText(null);
				name.setText(null);
				welcome w=new welcome();
				}
				 else
				 {
      					System.out.println("enter the valid username and password");
     					 JOptionPane.showMessageDialog(this,"Incorrect login or password",
           																	 "Error",JOptionPane.ERROR_MESSAGE);
				  }
				
			}
			catch (Exception e4)
			{
			}
		}
    
}
	
	
	public static void main(String[]args) 
	{
		
		front pf=new front();
	}
}