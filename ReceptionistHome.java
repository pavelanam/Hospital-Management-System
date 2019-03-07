import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

public class ReceptionistHome extends JFrame implements ActionListener
{
	private JPanel panelReceptionist;
	private JLabel title,imglabel;
	private ImageIcon icon;
	private JButton back,add,delete,update,view,search;
	private JLabel label;
	private JFrame frame;
	
	public ReceptionistHome(JFrame frame)
	{
		super("Receptionist Home");
		
		this.setSize(800,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelReceptionist = new JPanel();
		panelReceptionist.setLayout(null);
		panelReceptionist.setBackground(Color.BLACK);
		
		icon=new ImageIcon(getClass().getResource("1.png"));
		this.setIconImage(icon.getImage());
		
		icon = new ImageIcon("4.png");
		imglabel = new JLabel(icon);
		imglabel.setBounds(50,200,200,200);
		panelReceptionist.add(imglabel);
		
		title = new JLabel("RECEPTIONIST HOME");
		title.setBounds(50,45,450,50);
		title.setBackground(Color.GRAY);
		title.setForeground(Color.ORANGE);
		title.setFont(new Font("Consolas",Font.BOLD,40));
		panelReceptionist.add(title);
		
		
		back = new JButton("BACK");
		back.setBounds(550,44,150,40);
		back.setBackground(Color.GRAY);
		back.setForeground(Color.ORANGE);
		back.setFont(new Font("Consolas",Font.BOLD,40));
		back.setOpaque(true);
		back.addActionListener(this);
		panelReceptionist.add(back);
		
		add = new JButton("ADD");
		add.setBounds(350,150,350,40);
		add.setBackground(Color.GRAY);
		add.setForeground(Color.ORANGE);
		add.setFont(new Font("Consolas",Font.BOLD,40));
		add.addActionListener(this);
		panelReceptionist.add(add);
		
		delete = new JButton("DELETE");
		delete.setBounds(350,220,350,40);
		delete.setBackground(Color.GRAY);
		delete.setForeground(Color.ORANGE);
		delete.setFont(new Font("Consolas",Font.BOLD,40));
		delete.addActionListener(this);
		panelReceptionist.add(delete);
		
		update = new JButton("UPDATE");
		update.setBounds(350,290,350,40);
		update.setBackground(Color.GRAY);
		update.setForeground(Color.ORANGE);
		update.setFont(new Font("Consolas",Font.BOLD,40));
		update.addActionListener(this);
		panelReceptionist.add(update);
		
		view = new JButton("VIEW DETAILS");
		view.setBounds(350,360,350,40);
		view.setBackground(Color.GRAY);
		view.setForeground(Color.ORANGE);
		view.setFont(new Font("Consolas",Font.BOLD,40));
		view.addActionListener(this);
		panelReceptionist.add(view);
		
		search = new JButton("SEARCH");
		search.setBounds(350,430,350,40);
		search.setBackground(Color.GRAY);
		search.setForeground(Color.ORANGE);
		search.setFont(new Font("Consolas",Font.BOLD,40));
		search.addActionListener(this);
		panelReceptionist.add(search);
		
		this.frame = frame;
		
		this.add(panelReceptionist);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String elementText = ae.getActionCommand();
		{
			if(elementText.equals(add.getText()))
			{
				InsertReceptionist isr = new InsertReceptionist(this);
				isr.setVisible(true);
				this.setVisible(false);
			}
			else if(elementText.equals(delete.getText()))
			{
				DeleteReceptionist dr = new DeleteReceptionist(this);
				dr.setVisible(true);
				this.setVisible(false);
			}
			if(elementText.equals(back.getText()))
			{
				this.frame.setVisible(true);
			    this.setVisible(false);
			}
			else if(elementText.equals(update.getText()))
			{
				UpdateReceptionist ur = new UpdateReceptionist(this);
				ur.setVisible(true);
				this.setVisible(false);
			}
			else if(elementText.equals(search.getText()))
			{
			    ReceptionistSearch rs1 = new ReceptionistSearch(this);
				rs1.setVisible(true);
				this.setVisible(false);
			}
			else if(elementText.equals(view.getText()))
			{
				Test();
			}
			else{}
		}
	}	
	public void Test()
	{
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String query;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
			st = con.createStatement();
			query = "select * from receptionist";
			rs = st.executeQuery(query);
			ResultSetMetaData rsmt = rs.getMetaData();
			int c = rsmt.getColumnCount();
			Vector column = new Vector(c);
			
			for(int i = 1; i <= c; i++)
			{
				column.add(rsmt.getColumnName(i));
			}
			Vector data = new Vector();
			Vector row = new Vector();
			
			while(rs.next())
			{
				row = new Vector(c);
				for(int i = 1;i <= c; i++)
				{
					row.add(rs.getString(i));
				}
				data.add(row);
			}
			JFrame f = new JFrame();
			f.setBounds(100,100,500,300);
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			JPanel panel = new JPanel();
			JTable table = new JTable(data,column);
			JScrollPane jsp = new JScrollPane(table);
			panel.setLayout(new BorderLayout());
			panel.add(jsp,BorderLayout.CENTER);
			f.setContentPane(panel);
			f.setVisible(true);
			
		}
		catch(Exception e)
		{
			System.out.println("Exception : " +e.getMessage());
		}
		finally
		{
			try
			{
				st.close();
				rs.close();
				con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception : " +e.getMessage());
			}
		}
	}
}