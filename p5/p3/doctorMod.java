package p3;
import java.sql.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.util.Date;
class doctor2 
{
	static int did;
	static Date jdt=new Date();
	static ImageIcon i;
	static String dfnm,dmnm,dlnm,gen,dadd,cno,ldt,dqul,sft,sts,path;
	static ResultSet rs=null;
	static PreparedStatement pa=null,pm=null,pd=null,pl=null,pps=null,pdls=null,ps1=null;
	static Connection con;
	static FileInputStream fis;
	static 
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql:///hospital","root","root");
			pa=con.prepareStatement("insert into doctor values (?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
				pd=con.prepareStatement("delete from doctor where did=? ");
			pl=con.prepareStatement("select * from doctor order by did ");
			pps=con.prepareStatement("select * from doctor where did=? ");
			ps1=con.prepareStatement("select * from doctor where dlnm=?");
			//pg=con.prepareStatement("update doctor set qty=qty-? where mnm= ? and (qty-?)>=0");
		//	pqt=con.prepareStatement("update doctor set qty=66  where mnm=?");
				
			
		}
		catch (Exception e)
		{ 
			System.out.println(""+e);
		}
	}
	static void add(File x,String s) throws Exception
	{
	
		fis = new FileInputStream(x);
		
		/* executeUpdate() method execute specified sql query. Here this query 
		 insert data and image from specified address. */ 
	
	try
	{		System.out.println("    asasas addd  "+s);
		pa.setInt(1,did);

		pa.setString(2,dfnm);
		pa.setString(3,dmnm);
		pa.setString(4,dlnm);
		pa.setString(5,gen);
		pa.setString(6,dadd);
		pa.setString(7,cno);
		pa.setString(8,""+jdt);
		pa.setString(10,ldt);
		pa.setString(9,dqul);
		pa.setString(11,sft);
		pa.setString(12,sts);
		System.out.println("adding123");
		pa.setBinaryStream(13, (InputStream)fis, (int)(x.length()));
		pa.setString(14,s);		
			pa.executeUpdate();
	}
	catch(Exception e16)
	{
		System.out.println("add " +e16);
		}
	}
	static void mod(int did,File x,String s) throws Exception
	{
		try
		{	System.out.println("mod "+s);
			fis = new FileInputStream(x);
			pm=con.prepareStatement("update doctor set dfnm=?,dmnm=?,dlnm=?,gen=?,dadd=?,cno=?,jdt=?,dqul=?,ldt=?,sft=?,sts=?,i=?,path=? where did="+did);
	//		pm=con.prepareStatement("update doctor set dfnm=?,dmnm=?,dlnm='yuu' where did=?");
	//	pm.setInt(12,did);
		pm.setString(1,dfnm);
		pm.setString(2,dmnm);
		pm.setString(3,dlnm);
		pm.setString(4,gen);
		pm.setString(5,dadd);
		pm.setString(6,cno);
		pm.setString(7,""+jdt);
		pm.setString(9,ldt);
		pm.setString(8,dqul);
		pm.setString(10,sft);
		pm.setString(11,sts);
		pm.setString(13,s);	
		pm.setBinaryStream(12, (InputStream)fis, (int)(x.length()));
		
		

		pm.executeUpdate();
		}
		catch (Exception e7)
		{
			System.out.println("75 "+e7);
		}
	}
	static void del() throws Exception
	{
		pd.setInt(1,did);
		pd.executeUpdate();
	}
	static ResultSet collectRows1()throws Exception
	{
		rs=pl.executeQuery();
		return rs;
	}
	static boolean psearch(int n)throws Exception
	{
		pps.setInt(1,n);
		rs=pps.executeQuery();
		return rs.next();
	}
	
	static  ResultSet collectRows (String s) throws Exception
	{
	
		try
		{
		
		ps1.setString(1,s);
		rs=ps1.executeQuery();
		}
		catch (Exception e13)
		{
			System.out.println("99"+e13+" "+rs);
		}
		return rs;
	}
	
}



public class doctorMod extends JFrame implements FocusListener,ActionListener,ItemListener 
{
	JLabel ldid,ldnm,lgen,ldadd,lcno,ljdt,ldqul,lldt,lsft,lsts,j14;
	JTextField tdid,tdfnm,tdmnm,tdlnm,tgen,tcno,tjdt,tldt;
	JButton ba,bm,bd,bl,bs,bu;
	TextArea tdadd,tdqul;
	Choice sft,sts;
	CheckboxGroup cbg;
	Checkbox cm,cf;
	JLabel image,j13;
	;
	String str="",dir="",s;
	File f=null;
		JPanel q;
	public doctorMod(String s)
	{
		super(s);
	
			image=new JLabel(){
			public void paint(Graphics g)
			{
				ImageIcon ic=new ImageIcon(str);
				Image img=ic.getImage();
				g.drawImage(img,0,0,100,100,this);
				
				}
			};
		
		image.setBounds(700,300,100,100);
			bu=new JButton("UPLOAD");
		bu.setBounds(700,230,100,20);
		bu.addActionListener(this);
		ldid=new JLabel ("Doctor ID");
		ldnm=new JLabel ("Doctor Name");
		lgen=new JLabel ("Gender");
		ldadd=new JLabel ("Address");
		lcno=new JLabel ("Contact No.");
		ljdt=new JLabel ("Join Date");
		ldqul=new JLabel ("Qualification");
		lldt=new JLabel ("Leave Date");
		lsft=new JLabel ("Shift");
		lsts=new JLabel ("Status");
		cbg=new CheckboxGroup();
		cm=new Checkbox("Male",cbg,true);
		cm.addItemListener(this);
		cf=new Checkbox("Female",cbg,false);
		cf.addItemListener(this);
		tjdt=new JTextField(15);
		tdid=new JTextField(6);
		tdid.addFocusListener(this);
		tdfnm=new JTextField("first");
		tdmnm=new JTextField("middle");
		tdlnm=new JTextField("last");
		//tage=new JTextField(4);
		//tage.addFocusListener(this);
		//tbg=new JTextField(6);
		//twt=new JTextField(5);
		tdadd=new TextArea(doctor2.dadd);
		tcno=new JTextField(20);
		tjdt=new JTextField(20);
		tdqul=new TextArea();
		tldt=new JTextField(20);
		sft=new Choice();
		sft.add("8AM-4PM");
		sft.add("4PM-12AM");
		sft.add("12AM-8AM");
		sft.addItemListener(this);
		sts=new Choice();
		sts.add("Working");
		sts.add("Non-Working");
		sts.addItemListener(this);
		
		
		ba=new JButton("ADD");
		ba.addActionListener(this);
		bm=new JButton("MODIFY");
		bm.addActionListener(this);
		bd=new JButton("DELETE");
		bd.addActionListener(this);
		bl=new JButton("DISPLAY");
		bl.addActionListener(this);
		bs=new JButton("SEARCH");
		bs.addActionListener(this);
		
		JPanel p=new JPanel();
	//	p.add(ba);
		p.add(bm);
	//	p.add(bd);
	//	p.add(bl);
	//	p.add(bs);
		add(p,BorderLayout.SOUTH);
		 q=new JPanel();
		q.setLayout(null);
		//ldt.setBounds(1100,20,60,20);
		//q.add(ldt);
		//tdt.setBounds(1150,20,200,20);
		//q.add(tdt);
		ldid.setBounds(70,60,80,20);
		q.add(ldid);
		tdid.setBounds(200,60,60,20);
		q.add(tdid);
		ldnm.setBounds(70,100,100,20);
		q.add(ldnm);
		tdfnm.setBounds(200,100,100,20);
		q.add(tdfnm);
		tdmnm.setBounds(320,100,100,20);
		q.add(tdmnm);
		tdlnm.setBounds(440,100,100,20);
		q.add(tdlnm);
		lgen.setBounds(70,140,100,20);
		q.add(lgen);
		cm.setBounds(200,140,60,20);
		cf.setBounds(280,140,60,20);
		ldadd.setBounds(70,180,100,20);
		q.add(ldadd);
		tdadd.setBounds(200,180,300,70);
		q.add(tdadd);
		lcno.setBounds(70,270,100,20);
		q.add(lcno);
		tcno.setBounds(200,270,120,20);
		q.add(tcno);
		ljdt.setBounds(70,310,100,20);
		q.add(ljdt);
		tjdt.setBounds(200,310,120,20);
		q.add(tjdt);
		ldqul.setBounds(70,350,100,20);
		q.add(ldqul);
		tdqul.setBounds(200,350,300,50);
		q.add(tdqul);
		lldt.setBounds(70,420,100,20);
		q.add(lldt);
		tldt.setBounds(200,420,120,20);
		q.add(tldt);
		lsft.setBounds(70,460,100,20);
		q.add(lsft);
		sft.setBounds(200,460,150,20);
		lsts.setBounds(70,500,100,20);
		q.add(lsts);
		sts.setBounds(200,500,150,20);
		q.add(tldt);
		q.add(sft);
		q.add(sts);
		
		q.add(cm);
		q.add(cf);
		q.add(image);
		q.add(bu);
		add(q,BorderLayout.CENTER);
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				try
				{
					doctor2.con.close();
					System.exit(0);
				}
				catch (Exception e8)
				{
				}
			}
		});
		setSize(1365,740);
		setVisible(true);
	}
	public void itemStateChanged(ItemEvent ie)
	{
		repaint();
	}
	public void read()
	{
		try
		{
			doctor2.did=Integer.parseInt(tdid.getText());
			doctor2.dfnm=tdfnm.getText();
			doctor2.dmnm=tdmnm.getText();
			doctor2.dlnm=tdlnm.getText();
			doctor2.gen=cbg.getSelectedCheckbox().getLabel();
			doctor2.dadd=tdadd.getText();
			//this.ad=tadd.getText();
			//System.out.println(tadd.getText());
			doctor2.cno=tcno.getText();
			//doctor2.jdt=tjdt.getText();
			doctor2.dqul=tdqul.getText();
			//System.out.println(tsym.getText());

			doctor2.ldt=tldt.getText();
			doctor2.sft=sft.getSelectedItem();
			doctor2.sts=sts.getSelectedItem();
			doctor2.path=str;
		}
		catch (Exception e)
		{
		}
	}
	public void focusGained(FocusEvent e){}
	public void focusLost(FocusEvent e)
	{
		int id=0;
		boolean res=false;
		JTextField t=(JTextField)e.getSource();
		if(t==tdid)
		{
			try
			{
				id=Integer.parseInt(tdid.getText());
				res=doctor2.psearch(id);
			}
			catch (Exception e1)
			{
			}
			
			if(res)
			{
				int no;
				JLabel image1;
				String dfn="",dmn="",dln="",gen="",dadd="",cno="",jdt="",dqul="",ldt="",sft="",sts="",dir="";
				try
				{
					no=doctor2.rs.getInt(1);
					dfn=doctor2.rs.getString(2);
					dmn=doctor2.rs.getString(3);
					dln=doctor2.rs.getString(4);
					gen=doctor2.rs.getString(5);
					dadd=doctor2.rs.getString(6);
					cno=doctor2.rs.getString(7);
					jdt=doctor2.rs.getString(8);
					dqul=doctor2.rs.getString(9);
					ldt=doctor2.rs.getString(10);
					sft=doctor2.rs.getString(11);
					sts=doctor2.rs.getString(12);
					dir=doctor2.rs.getString(14);
				
				s=dir;
				repaint();
				j13=new JLabel("OLD IMAGE");
				j13.setBounds(820,5,300,300);	
				 JLabel j12=new JLabel("OLD PHOTO"){
					public void paint(Graphics g)
					{
						ImageIcon ic=new ImageIcon(s);
						Image img=ic.getImage();
						g.drawImage(img,0,0,100,100,this);
						
							
					}};
					
				//	q.flushAll()
				
						/*ImageIcon ic=new ImageIcon(dir);
						JLabel j12=new JLabel("",ic,JLabel.CENTER);*/
						j12.setBounds(700,100,300,300);
					//	image.setBounds(700,300,100,100);
			
				q.add(j12);
				q.add(j13);
				repaint();
					System.out.println("8743t32874"+dir);
					
				}
				catch (Exception e2)
				{
				}
			
                if(gen.equals("Male"))
					 cm.setState(true);
				else
					 cf.setState(true);
				tjdt.setText(jdt);
				tdfnm.setText(dfn);
				tdmnm.setText(dmn);
				tdlnm.setText(dln);
				
				tdadd.setText(dadd);
				tcno.setText(cno);
				tdqul.setText(dqul);
				tldt.setText(ldt);
				
				
				//sft.setText(sft);
				//sts.setText(sts);
		  		ba.setEnabled(false);
				bm.setEnabled(true);
				bd.setEnabled(true);
			}
			else
			{
				if(id==0)
				{
					bl.setEnabled(true);
					ba.setEnabled(false);
				}
				else
					ba.setEnabled(true);
				bm.setEnabled(false);
				bd.setEnabled(false);
			}
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		JButton b=(JButton)e.getSource();
		//File f=null;
		if(b==bu)
		{
				j14=new JLabel("NEW IMAGE");
				j14.setBounds(830,200,300,300);
				q.add(j14);
			
				JFileChooser	filechooser=new JFileChooser();
               int result = filechooser.showOpenDialog(this);
               f=filechooser.getSelectedFile();               
               try
               {
                   String dir1=f.getAbsolutePath();
                   //setText(dir);
                   str=dir1;
                  // ResizeImage.resize(dir); 
                   f=new File(dir1);
               }
               catch(Exception e1){}
               repaint();
              // b=(JButton)e.getSource();
               	
		
		}
		
		if(b==bm)
		{
			try
			{
				read();
				
			System.out.println("ganesh "+str);
				doctor2.mod(doctor2.did,f,str);
				JOptionPane.showMessageDialog(null,"Doctor ID: "+doctor2.did+"  Record Modified");

			}
			catch (Exception e5)
			{
			}}
	
	
	}
	public static void main(String[]args) 
	{
		doctorMod pf=new doctorMod("DOCTOR FORM");
	}
}