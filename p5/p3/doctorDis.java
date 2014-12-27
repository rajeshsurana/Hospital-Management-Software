package p3;
import java.sql.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;
class doctor4 
{
	static int did;
	static Date jdt=new Date();
	static String dfnm,dmnm,dlnm,gen,dadd,cno,ldt,dqul,sft,sts;
	static ResultSet rs=null;
	static PreparedStatement pa=null,pm=null,pd=null,pl=null,pps=null,pdls=null,ps1=null;
	static Connection con;
	static 
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:hospdsn");
			pa=con.prepareStatement("insert into doctor values (?,?,?,?,?,?,?,?,?,?,?,?) ");
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
	static void add() throws Exception
	{
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
		pa.executeUpdate();
	}
	static void mod(int did) throws Exception
	{
		try
		{
			pm=con.prepareStatement("update doctor set dfnm=?,dmnm=?,dlnm=?,gen=?,dadd=?,cno=?,jdt=?,dqul=?,ldt=?,sft=?,sts=? where did="+did);
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
class stabdd extends JFrame implements ActionListener
{
	JTable jt;
	JButton btn;
	String arr[][]=new String[300][10];
	
	JScrollPane jsp;
	stabdd(ResultSet k)
	{
		super("DOCTOR FOUND");
		
		String[]chd={"Doctor ID","Doctor Name","Gender","Address","Contact No.","Join Date","Qualification","Leave Date","Shift","Status"};
		jt=new JTable();
		jsp=new JScrollPane(jt);
		btn=new JButton("CLOSE");
		btn.addActionListener(this);
		int i=0;

		try
		{
			while(k.next())
			{
			  
			arr[i][0]=""+k.getInt(1);
			arr[i][1]= k.getString(2)+" "+k.getString(3)+" "+k.getString(4);
			arr[i][2]=k.getString(5);
			arr[i][3]=k.getString(6);
			arr[i][4]=k.getString(7);
			arr[i][5]=k.getString(8);
			arr[i][6]=k.getString(9);
			arr[i][7]=k.getString(10);
			arr[i][8]=k.getString(11);
			arr[i][9]=k.getString(12);
			i++;
		}
		}
		catch ( Exception e12)
		{
			System.out.println("151 "+e12);
		}
		jt.setModel(new DefaultTableModel(arr,chd));
		Container con=getContentPane();
		con.setLayout(null); 
		jsp.setBounds(20,20,1330,600);
		btn.setBounds(685,630,100,30);
		add(jsp);
		add(btn);
		setSize(1370,800);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{
		setVisible(false);
	}
}


public class doctorDis extends JFrame implements FocusListener,ActionListener,ItemListener 
{
	JLabel ldid,ldnm,lgen,ldadd,lcno,ljdt,ldqul,lldt,lsft,lsts,j00;
	JTextField tdid,tdfnm,tdmnm,tdlnm,tgen,tcno,tjdt,tldt;
	JButton ba,bm,bd,bl,bs;
	TextArea tdadd,tdqul;
	Choice sft,sts;
	CheckboxGroup cbg;
	Checkbox cm,cf;
	public doctorDis(String s)
	{
		super(s);
		j00=new JLabel("CLICK HERE TO DISPLAY DATABASE");
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
		tdadd=new TextArea(doctor4.dadd);
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
	//	p.add(bm);
	//	p.add(bd);
	//	p.add(bl);
	//	p.add(bs);
		add(p,BorderLayout.SOUTH);
		JPanel q=new JPanel();
		q.setLayout(null);
		j00.setBounds(100,150,250,30);
		bl.setBounds(150,190,90,40);
		q.add(j00);
		q.add(bl);
		//ldt.setBounds(1100,20,60,20);
		//q.add(ldt);
		//tdt.setBounds(1150,20,200,20);
		//q.add(tdt);
		ldid.setBounds(70,60,80,20);
	//q.add(ldid);
		tdid.setBounds(200,60,60,20);
	//	q.add(tdid);
		ldnm.setBounds(70,100,100,20);
	///	q.add(ldnm);
		tdfnm.setBounds(200,100,100,20);
		//q.add(tdfnm);
		tdmnm.setBounds(320,100,100,20);
	//	q.add(tdmnm);
		tdlnm.setBounds(440,100,100,20);
	//	q.add(tdlnm);
		lgen.setBounds(70,140,100,20);
	//	q.add(lgen);
		cm.setBounds(200,140,60,20);
		cf.setBounds(280,140,60,20);
		ldadd.setBounds(70,180,100,20);
	//	q.add(ldadd);
		tdadd.setBounds(200,180,300,70);
	//	q.add(tdadd);
		lcno.setBounds(70,270,100,20);
	//	q.add(lcno);
		tcno.setBounds(200,270,120,20);
	//	q.add(tcno);
		ljdt.setBounds(70,310,100,20);
	//	q.add(ljdt);
		tjdt.setBounds(200,310,120,20);
	//	q.add(tjdt);
		ldqul.setBounds(70,350,100,20);
	//	q.add(ldqul);
		tdqul.setBounds(200,350,300,50);
	//	q.add(tdqul);
		lldt.setBounds(70,420,100,20);
	//	q.add(lldt);
		tldt.setBounds(200,420,120,20);
	//	q.add(tldt);
		lsft.setBounds(70,460,100,20);
	//	q.add(lsft);
		sft.setBounds(200,460,150,20);
		lsts.setBounds(70,500,100,20);
	//	q.add(lsts);
		sts.setBounds(200,500,150,20);
	//	q.add(tldt);
	//	q.add(sft);
	//	q.add(sts);
		
	//	q.add(cm);
	//	q.add(cf);
		add(q,BorderLayout.CENTER);
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				try
				{
					doctor4.con.close();
					System.exit(0);
				}
				catch (Exception e8)
				{
				}
			}
		});
		setSize(400,400);
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
			doctor4.did=Integer.parseInt(tdid.getText());
			doctor4.dfnm=tdfnm.getText();
			doctor4.dmnm=tdmnm.getText();
			doctor4.dlnm=tdlnm.getText();
			doctor4.gen=cbg.getSelectedCheckbox().getLabel();
			doctor4.dadd=tdadd.getText();
			//this.ad=tadd.getText();
			//System.out.println(tadd.getText());
			doctor4.cno=tcno.getText();
			//doctor4.jdt=tjdt.getText();
			doctor4.dqul=tdqul.getText();
			//System.out.println(tsym.getText());

			doctor4.ldt=tldt.getText();
			doctor4.sft=sft.getSelectedItem();
			doctor4.sts=sts.getSelectedItem();
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
				res=doctor4.psearch(id);
			}
			catch (Exception e1)
			{
			}
			
			if(res)
			{
				int no;
				String dfn="",dmn="",dln="",gen="",dadd="",cno="",jdt="",dqul="",ldt="",sft="",sts="";
				try
				{
					no=doctor4.rs.getInt(1);
					dfn=doctor4.rs.getString(2);
					dmn=doctor4.rs.getString(3);
					dln=doctor4.rs.getString(4);
					gen=doctor4.rs.getString(5);
					dadd=doctor4.rs.getString(6);
					cno=doctor4.rs.getString(7);
					jdt=doctor4.rs.getString(8);
					dqul=doctor4.rs.getString(9);
					ldt=doctor4.rs.getString(10);
					sft=doctor4.rs.getString(11);
					sts=doctor4.rs.getString(12);
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
	
		
		if(b==bl)
		{
			stabdd obj=null;
				try
				{
					obj=new stabdd(doctor4.collectRows1());
				}
			
				catch (Exception e7)
				{
				}
		}
	
		
	}
	public static void main(String[]args) 
	{
		doctorDis p=new doctorDis("DOCTOR FORM");
	}
}