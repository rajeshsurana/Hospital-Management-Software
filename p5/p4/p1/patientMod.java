package p1;
import java.sql.*;
import java.util.Date;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.swing.table.*;
 class patient 
{
	static int pid,age,fee;
	static Date dt=new Date();
	static ImageIcon i;
	static String pfnm,pmnm,plnm,gen,wt,addr,cno,dnm,sym,dig,bg,path;
	static ResultSet rs=null;
	static PreparedStatement pa=null,pm=null,pd=null,pl=null,pps=null,tmp=null,pps1=null;
	static Connection con;
	static FileInputStream fis;
	static 
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql:///hospital","root","root");
			pa=con.prepareStatement("insert into patient values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
			pd=con.prepareStatement("delete from patient where pid=? ");
		  	pl=con.prepareStatement("select * from patient order by pid");
			pps=con.prepareStatement("select * from patient where pid=? ");
			pps1=con.prepareStatement("select * from patient where plnm= ?");
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
	public static void add(String a,String b,File x,String s) throws Exception
	{
		fis = new FileInputStream(x);
		
		pa.setString(1,""+dt);
		pa.setInt(2,pid);
		pa.setString(3,pfnm);
		pa.setString(4,pmnm);
		pa.setString(5,plnm);
		pa.setString(6,gen);
		pa.setInt(7,age);
		pa.setString(8,wt);
		pa.setString(9,a);
		pa.setString(10,cno);
		pa.setString(11,dnm);
		pa.setString(12,b);
		pa.setString(13,dig);
		pa.setInt(14,fee);
		pa.setString(15,bg);
		pa.setString(17,s);
		pa.setBinaryStream(16, (InputStream)fis, (int)(x.length()));
		
		pa.executeUpdate();
	}
	public static void mod(File x,String s) throws Exception
	{
			
			
		try
		{
			System.out.println(x);
			fis = new FileInputStream(x);
			//tmp=con.prepareStatement("update patient set dt=?,pfnm=?,pmnm=?,plnm=?,gen=?,age=?,wt=?,addr=?,cno=?,dnnm=?,sym=?,dig=?,fee=? where pid=1");
			   tmp=con.prepareStatement("update patient set dt=?,pfnm=?,pmnm=?,plnm=?,gen=?,age=?,wt=?,addr=?,cno=?,dnm=?,sym=?,dig=?,fee=?,bg=?,i=?,path=? where pid="+pid);
             //tmp.setString(1,pfnm);
			 tmp.setString(1,""+dt);
			tmp.setString(2,pfnm);
			tmp.setString(3,pmnm);
			tmp.setString(4,plnm);
			tmp.setString(5,gen);
			tmp.setInt(6,age);
			tmp.setString(7,wt);
			tmp.setString(8,addr);
			tmp.setString(9,cno);
			tmp.setString(10,dnm);
			tmp.setString(11,sym);
			tmp.setString(12,dig);
			tmp.setInt(13,fee);
			tmp.setString(14,bg);
					
			tmp.setString(16,s);	
			tmp.setBinaryStream(15, (InputStream)fis, (int)(x.length()));
			
			tmp.executeUpdate();
		}
		catch (Exception ee)
		{
			System.out.println("mod123 "+ee);
		}
	
	}
	public static void del() throws Exception
	{
		pd.setInt(1,pid);
		pd.executeUpdate();
	}
	public static ResultSet collectRows()throws Exception
	{
		rs=pl.executeQuery();
		return rs;		
	}
	public static boolean psearch(int n)throws Exception
	{
		pps.setInt(1,n);
		rs=pps.executeQuery();
		return rs.next();
	}
	public static  ResultSet collectRows1 (String s) throws Exception
	{
	
		try
		{
		
		pps1.setString(1,s);
		rs=pps1.executeQuery();
		}
		catch (Exception e13)
		{
			System.out.println(""+e13);
		}
		return rs;
	}
}
 class stab extends JFrame implements ActionListener
{
	JTable jt;
	JButton btn;
	String arr[][]=new String[300][14];
	
	JScrollPane jsp;
	stab(ResultSet k)
	{
		super("PATIENT FOUND");
		String[]chd={"Date","Patient ID","Name","Gender","Age","Weight","Address","Contact No.","Doctor Name","Symptoms","Dignosis","Fee: Rs.","Blood Group"};
		//DefaultTableModel dtm=new DefaultTableModel();
		//jt.setModel(new DefaultTableModel(arr,chd));
		jt=new JTable();
		jsp=new JScrollPane(jt);
		btn=new JButton("CLOSE");
		btn.addActionListener(this);
		int i=0;

		try
		{
			while(k.next())
			{
			  
			arr[i][0]=k.getString(1);
			//System.out.println("ddddddddddddd"+arr[i][0]);
			arr[i][1]= ""+k.getInt(2);
			arr[i][2]=k.getString(3)+" "+k.getString(4)+" "+k.getString(5);
			arr[i][3]=k.getString(6);
			arr[i][4]=""+k.getInt(7);
			arr[i][5]=k.getString(8);
			arr[i][6]=k.getString(9);
			arr[i][7]=k.getString(10);
			arr[i][8]=k.getString(11);
			arr[i][9]=k.getString(12);
			arr[i][10]=k.getString(13);
			arr[i][11]=""+k.getInt(14);
			arr[i][12]=k.getString(15);
			arr[i][13]=k.getString(16);
			//dtm.insertRow(i,new Object[]{patient.rs.getString(1), patient.rs.getInt(2),patient.rs.getString(3)+patient.rs.getString(4)+patient.rs.getString(5),patient.rs.getString(6),patient.rs.getInt(7),patient.rs.getString(8),patient.rs.getString(9),patient.rs.getString(10),patient.rs.getString(11),patient.rs.getString(12),patient.rs.getString(13),patient.rs.getInt(14)});
			i++;
		}
		}
		catch ( Exception e12)
		{
			System.out.println(""+e12);
		}
		jt.setModel(new DefaultTableModel(arr,chd));
		Container con=getContentPane();
		con.setLayout(null); 
		jsp.setBounds(20,20,1230,600);
		btn.setBounds(685,630,100,30);
		add(jsp);
		add(btn);
		setSize(1330,800);
		setVisible(true);
	}
	 public void actionPerformed(ActionEvent ae)
	{
		setVisible(false);
	}
}

 class disptable extends JFrame implements ActionListener
{
	JTable jt;
	JButton btn;
	//DefaultTableModel dtm;
	String arr[][]=new String[300][14];
	
	JScrollPane jsp;
	disptable(ResultSet k)
	{
		super("PATIENT DATA");
		
		String[]chd={"Date: ","Patient ID:","Name: ","Gender: ","Age: ","Weight: ","Address: ","Contact No.: ","Doctor Name: ","Symptoms: ","Dignosis: ","Fee: Rs.","Blood Group"};
		//DefaultTableModel dtm=new DefaultTableModel();
		//jt.setModel(new DefaultTableModel(arr,chd));
		jt=new JTable();
		jsp=new JScrollPane(jt);
		btn=new JButton("CLOSE");
		btn.addActionListener(this);
		int i=0;

		try
		{
			while(k.next())
			{
			  
			arr[i][0]=k.getString(1);
			//System.out.println("ddddddddddddd"+arr[i][0]);
			arr[i][1]= ""+k.getInt(2);
			arr[i][2]=k.getString(3)+" "+k.getString(4)+" "+k.getString(5);
			arr[i][3]=k.getString(6);
			arr[i][4]=""+k.getInt(7);
			arr[i][5]=k.getString(8);
			arr[i][6]=k.getString(9);
			arr[i][7]=k.getString(10);
			arr[i][8]=k.getString(11);
			arr[i][9]=k.getString(12);
			arr[i][10]=k.getString(13);
			arr[i][11]=""+k.getInt(14);
			arr[i][12]=k.getString(15);
			arr[i][13]=k.getString(17);
			//dtm.insertRow(i,new Object[]{patient.rs.getString(1), patient.rs.getInt(2),patient.rs.getString(3)+patient.rs.getString(4)+patient.rs.getString(5),patient.rs.getString(6),patient.rs.getInt(7),patient.rs.getString(8),patient.rs.getString(9),patient.rs.getString(10),patient.rs.getString(11),patient.rs.getString(12),patient.rs.getString(13),patient.rs.getInt(14)});
			i++;
		}
		}
		catch ( Exception e9)
		{
			System.out.println(""+e9);
		}
		jt.setModel(new DefaultTableModel(arr,chd));
		Container con=getContentPane();
		con.setLayout(null);
		jsp.setBounds(20,20,1230,600);
		btn.setBounds(685,630,100,30);
		add(jsp);
		add(btn);
		setSize(1330,800);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{
		setVisible(false);
	}
}
public class patientMod extends JFrame implements FocusListener,ActionListener,ItemListener 
{
	JLabel ldt,lpid,lpnm,lgen,lage,lwt,ladd,lcno,ldnm,lsym,ldig,lfee,lbg,j14;
	JTextField tdt,tpid,tpfnm,tpmnm,tplnm,tage,twt,tcno,tdnm,tdig,tfee,tbg;
	TextArea tadd,tsym;
	JButton ba,bm,bd,bl,bs,bu;
	CheckboxGroup cbg;
	Checkbox cm,cf;
 	static String ad="",sym="";
 	JLabel image,j13;
 	String str="",dir="",s;
	File f=null;
 	JPanel q;
	public patientMod(String s)
	{
		super(s);
		image=new JLabel()
			{
				public void paint(Graphics g)
				{
				ImageIcon ic=new ImageIcon(str);
				Image img=ic.getImage();
				g.drawImage(img,0,0,150,150,this);
				
				}
			};
			
		image.setBounds(670,270,300,300);
		bu=new JButton("UPLOAD");
		bu.setBounds(700,230,100,20);
		bu.addActionListener(this);
	//	q.add(bu);
		
		ldt=new JLabel("Date");
		lpid=new JLabel ("Patient ID");
		lpnm=new JLabel ("Patient Name");
		lgen=new JLabel ("Gender");
		lbg=new JLabel ("Blood Group");
		lage=new JLabel ("Age");
		lwt=new JLabel ("Weight                                          Kg");
		ladd=new JLabel("Address");
		lcno=new JLabel ("Cont. no.");
		ldnm=new JLabel ("Doctor Name            Dr.");
		lsym=new JLabel("Symptoms");
		ldig=new JLabel("Diagnosis");
		lfee=new JLabel ("Fee                            Rs.");
		cbg=new CheckboxGroup();
		cm=new Checkbox("Male",cbg,false);
		cm.addItemListener(this);
		cf=new Checkbox("Female",cbg,false);
		cf.setState(true);
		cf.addItemListener(this);
		tdt=new JTextField(15);
		tpid=new JTextField(6);
		tpid.addFocusListener(this);
		tpfnm=new JTextField("first");
		tpmnm=new JTextField("middle");
		tplnm=new JTextField("last");
		tage=new JTextField(4);
		tage.addFocusListener(this);
		tbg=new JTextField(6);
		twt=new JTextField(5);
		tadd=new TextArea(patient.addr);
		tcno=new JTextField(20);
		tdnm=new JTextField(20);
		tsym=new TextArea();
		
		tdig=new JTextField(20);
		tfee=new JTextField(6);
		tfee.addFocusListener(this);
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
		//p.add(ba);
		p.add(bm);
	//	p.add(bd);
		//p.add(bl);
	//	p.add(bs);
		add(p,BorderLayout.SOUTH);
		q=new JPanel();
		q.setLayout(null);
		ldt.setBounds(1015,20,60,20);
		q.add(ldt);
		tdt.setBounds(1060,20,170,20);
		q.add(tdt);
		lpid.setBounds(70,60,80,20);
		q.add(lpid);
		tpid.setBounds(200,60,60,20);
		q.add(tpid);
		lpnm.setBounds(70,100,80,20);
		q.add(lpnm);
		tpfnm.setBounds(200,100,100,20);
		q.add(tpfnm);
		tpmnm.setBounds(320,100,100,20);
		q.add(tpmnm);
		tplnm.setBounds(440,100,100,20);
		q.add(tplnm);
		lgen.setBounds(70,140,60,20);
		q.add(lgen);
		lbg.setBounds(370,140,80,20);
		q.add(lbg);
		tbg.setBounds(470,140,60,20);
		q.add(tbg);
		cm.setBounds(200,140,60,20);
		cf.setBounds(280,140,60,20);
		lage.setBounds(70,180,40,20);
		q.add(lage);
		tage.setBounds(200,180,50,20);
		q.add(tage);
		lwt.setBounds(370,180,200,20);
		q.add(lwt);
		twt.setBounds(470,180,60,20);
		q.add(twt);
		ladd.setBounds(70,220,60,20);
		q.add(ladd);
		tadd.setBounds(200,220,350,50);
		q.add(tadd);
		lcno.setBounds(70,310,60,20);
		q.add(lcno);
		tcno.setBounds(200,310,120,20);
		q.add(tcno);
		ldnm.setBounds(70,350,200,20);
		q.add(ldnm);
		tdnm.setBounds(200,350,150,20);
		q.add(tdnm);
		lsym.setBounds(70,390,100,20);
		q.add(lsym);
		tsym.setBounds(200,390,300,70);
		q.add(tsym);
		ldig.setBounds(70,480,60,20);
		q.add(ldig);
		tdig.setBounds(200,480,100,20);
		q.add(tdig);
		lfee.setBounds(70,520,200,20);
		q.add(lfee);
		tfee.setBounds(200,520,40,20);
		q.add(tfee);
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
					patient.con.close();
					System.exit(0);
				}
				catch (Exception e8)
				{
				}
			}
		});
		System.out.println(tadd.getText());
		setSize(1330,740);
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
			//patient.dt=""+tdt.getText();
			patient.pid=Integer.parseInt(tpid.getText());
			patient.pfnm=tpfnm.getText();
			patient.pmnm=tpmnm.getText();
			patient.plnm=tplnm.getText();
			patient.gen=cbg.getSelectedCheckbox().getLabel();
			patient.age=Integer.parseInt(tage.getText());
			patient.wt=twt.getText();
			patient.addr=tadd.getText();
			this.ad=tadd.getText();
			System.out.println(tadd.getText());
			patient.cno=tcno.getText();
			patient.dnm=tdnm.getText();
			patient.sym=tsym.getText();
			System.out.println(tsym.getText());

			patient.dig=tdig.getText();
			patient.fee=Integer.parseInt(tfee.getText());
			patient.bg=tbg.getText();
			patient.path=str;
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
		if(t==tpid)
		{
			try
			{
				id=Integer.parseInt(tpid.getText());
				res=patient.psearch(id);
			}
			catch (Exception e1)
			{
			}
			
			if(res)
			{
				int no,ag=0,fee=0;
				String pfn="",bg="",pmn="",dt1="",pln="",add="",sym="",dn="",dig="",cn="",gender="",wgt="";
				try
				{
					dt1=patient.rs.getString(1);
					no=patient.rs.getInt(2);
					pfn=patient.rs.getString(3);
					pmn=patient.rs.getString(4);
					pln=patient.rs.getString(5);
					gender=patient.rs.getString(6);
					ag=patient.rs.getInt(7);
					wgt=patient.rs.getString(8);
					add=patient.rs.getString(9);
					cn=patient.rs.getString(10);
					dn=patient.rs.getString(11);
					sym=patient.rs.getString(12);
					dig=patient.rs.getString(13);
					fee=patient.rs.getInt(14);
					bg=patient.rs.getString(15);
						dir=patient.rs.getString(17);
				
				s=dir;
				repaint();
				j13=new JLabel("OLD IMAGE");
				j13.setBounds(840,5,300,300);	
				 JLabel j12=new JLabel("OLD PHOTO"){
					public void paint(Graphics g)
					{
						ImageIcon ic=new ImageIcon(s);
						Image img=ic.getImage();
						g.drawImage(img,0,0,150,150,this);
						
							
					}};
					
				//	q.flushAll()
				
						/*ImageIcon ic=new ImageIcon(dir);
						JLabel j12=new JLabel("",ic,JLabel.CENTER);*/
						j12.setBounds(670,70,300,300);
					//	image.setBounds(700,300,100,100);
			
				q.add(j12);
				q.add(j13);
				repaint();
					System.out.println("8743t32874"+dir);
				}
				catch (Exception e2)
				{
				}
                if(gender.equals("Male"))
					 cm.setState(true);
				else
					 cf.setState(true);
				tdt.setText(dt1);
				tpfnm.setText(pfn);
				tpmnm.setText(pmn);
				tplnm.setText(pln);
				tage.setText(""+ag);
				tfee.setText(""+fee);
				tadd.setText(add);
				tdig.setText(dig);
				tdnm.setText(dn);
				tsym.setText(sym);
				tcno.setText(cn);
				twt.setText(wgt);
				tbg.setText(bg);
				
		  		//cm.setState(set1State());
				//cf.setSelectedCheckbox(gender);
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
		if(b==bu)
		{
				j14=new JLabel("NEW IMAGE");
				j14.setBounds(840,200,300,300);
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
				patient.mod(f,str);
				JOptionPane.showMessageDialog(null,"Patient ID: "+patient.pid+" Record Modified");
			}
			catch (Exception e5)
			{
			}
		}
	
	
	}
	public static void main(String[]args)
	{
		patientMod pf=new patientMod("PATIENT FORM                                                                                                                                                   KRISHNA CLINIC");
	}
}