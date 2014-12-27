package p2;
import java.sql.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class medicine5 
{
	static int mid,qty;
	static String mnm,mnf,tp,prc;
	static ResultSet rs=null;
	static PreparedStatement pa,pm,pd,pl,pps,pms=null,pg=null,pqt=null;
	static Connection con;
	static 
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql:///hospital","root","root");
			pa=con.prepareStatement("insert into medicine values (?,?,?,?,?,?) ");
			pm=con.prepareStatement("update medicine set mnm=?,mnf=?,tp=?, prc=?,qty=? where mid=? ");
			pd=con.prepareStatement("delete from medicine where mid=? ");
			pl=con.prepareStatement("select * from medicine order by mid ");
			pps=con.prepareStatement("select * from medicine where mid=? ");
			pms=con.prepareStatement("select * from medicine where mnm= ?");
			pg=con.prepareStatement("update medicine set qty=qty-? where mnm= ? and (qty-?)>=0");
		//	pqt=con.prepareStatement("update medicine set qty=66  where mnm=?");
				
			
		}
		catch (Exception e)
		{ 
			System.out.println(""+e);
		}
	}
	static void add() throws Exception
	{
		pa.setInt(1,mid);
		pa.setString(2,mnm);
		pa.setString(3,mnf);
		pa.setString(4,tp);
		pa.setString(5,prc);
		pa.setInt(6,qty);
		pa.executeUpdate();
	}
	static void mod() throws Exception
	{
		pm.setInt(6,mid);
		pm.setString(1,mnm);
		pm.setString(2,mnf);
		pm.setString(3,tp);
		pm.setString(4,prc);
		pm.setInt(5,qty);
		pm.executeUpdate();
	}
	static void del() throws Exception
	{
		pd.setInt(1,mid);
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
		
		pms.setString(1,s);
		rs=pms.executeQuery();
		}
		catch (Exception e13)
		{
			System.out.println(""+e13);
		}
		return rs;
	}
	static void unknown(String s,String t)throws Exception
	{
		try
		{	
				System.out.println(s);
				Integer.parseInt(t);
		//		pqt=con.prepareStatement("select qty from medicine where mnm=");
				pg.setInt(1,Integer.parseInt(t));
				pg.setString(2,s);
				pg.setInt(3,Integer.parseInt(t));
				pg.executeUpdate();
		
	
		}
		catch (Exception e14)
		{
			System.out.println(""+e14);
		}
	}
}

public class medicineRem extends JFrame implements FocusListener,ActionListener,ItemListener 
{
	JLabel lmid,lmnm,lmnf,ltp,lprc,lqty;
	JTextField tmid,tmnm,tmnf,ttp,tprc,tqty;
	JButton ba,bm,bd,bl,bs,bg;
	CheckboxGroup cbg;
	Checkbox ct,cl;
	public medicineRem(String s)
	{
		super(s);
		lmid=new JLabel ("Medicine ID");
		lmnm=new JLabel ("Medicine Name");
		lmnf=new JLabel ("Manufacturer");
		ltp=new JLabel ("Type");
		lprc=new JLabel ("Price");
		lqty=new JLabel ("Quantity");
		cbg=new CheckboxGroup();
		ct=new Checkbox("Tablet",cbg,false);
		ct.addItemListener(this);
		cl=new Checkbox("Liquid",cbg,false);
		cl.addItemListener(this);
		tmid=new JTextField(6);
		tmid.addFocusListener(this);
		tmnm=new JTextField(15);
		tmnf=new JTextField(15);
		tprc=new JTextField(20);
		tqty=new JTextField(4);
		tqty.addFocusListener(this);
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
		bg=new JButton("REMOVE STOCK");
		bg.addActionListener(this);
		JPanel p=new JPanel();
		//p.add(ba);
	//	p.add(bm);
	//	p.add(bd);
	//	p.add(bl);
	//	p.add(bs);
		p.add(bg);
		add(p,BorderLayout.SOUTH);
		JPanel q=new JPanel();
		q.setLayout(null);
		lmid.setBounds(20,20,100,20);
		//q.add(lmid);
		tmid.setBounds(200,20,60,20);
		//q.add(tmid);
		lmnm.setBounds(20,60,100,20);
		//q.add(lmnm);
		tmnm.setBounds(200,60,150,20);
		///q.add(tmnm);
		lmnf.setBounds(20,100,100,20);
		//q.add(lmnf);
		tmnf.setBounds(200,100,150,20);
		//q.add(tmnf);
		lprc.setBounds(20,180,100,20);
		//q.add(lprc);
		tprc.setBounds(200,180,60,20);
		//q.add(tprc);
		ltp.setBounds(20,140,60,20);
		//q.add(ltp);
		ct.setBounds(200,140,60,20);
		cl.setBounds(280,140,60,20);
		lqty.setBounds(20,220,60,20);
		//q.add(lqty);
		tqty.setBounds(200,220,60,20);
		//q.add(tqty);
		//q.add(ct);
	//	q.add(cl);
		add(q,BorderLayout.CENTER);
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				try
				{
					medicine5.con.close();
					System.exit(0);
				}
				catch (Exception e8)
				{
				}
			}
		});
		setSize(700,400);
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
			medicine5.mid=Integer.parseInt(tmid.getText());
			medicine5.mnm=tmnm.getText();
			medicine5.mnf=tmnf.getText();
			medicine5.prc=tprc.getText();
			medicine5.tp=cbg.getSelectedCheckbox().getLabel();
			medicine5.qty=Integer.parseInt(tqty.getText());
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
		if(t==tmid)
		{
			try
			{
				id=Integer.parseInt(tmid.getText());
				res=medicine5.psearch(id);
			}
			catch (Exception e1)
			{
			}
			
			if(res)
			{
				int no;
				String mn="",qty="",mnf="",prc="",tp="";
				try
				{
					no=medicine5.rs.getInt(1);
					mn=medicine5.rs.getString(2);
					mnf=medicine5.rs.getString(3);
					prc=medicine5.rs.getString(5);
					tp=medicine5.rs.getString(4);
					qty=""+medicine5.rs.getInt(6);
				}
				catch (Exception e2)
				{
				}
                if(tp.equals("Tablet"))
					 ct.setState(true);
				else
					 cl.setState(true);
				tmnm.setText(mn);
				tmnf.setText(mnf);
				tprc.setText(prc);
				tqty.setText(qty);
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
	
		if(b==bg)
		{
				try
				{
					read();
					medicine5.unknown(JOptionPane.showInputDialog("medicine name:"),JOptionPane.showInputDialog("Stock Removed"));
				}
			
				catch (Exception e10)
				{
				}
		}
	}
	public static void main(String[]args) 
	{
		medicineRem pf=new medicineRem("MEDICINE FORM");
	}
}