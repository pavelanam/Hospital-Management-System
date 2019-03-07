import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.*;

public class DoctorSearch extends JFrame implements ActionListener
{
	private JPanel panelSearch;
	private JLabel title,doctorSearch;
	private JTextField TFdoctor;
	private JButton search,back;
	private ImageIcon icon;
	private JFrame frame;
	
	public DoctorSearch(JFrame frame)
	{
		super("Doctor Search");
		this.setSize(800,450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelSearch = new JPanel();
		panelSearch.setLayout(null);
		panelSearch.setBackground(Color.BLACK);
		
		icon=new ImageIcon(getClass().getResource("1.png"));
		this.setIconImage(icon.getImage());
		
		title = new JLabel("DOCTOR SEARCH");
		title.setBounds(50,45,450,50);
		title.setBackground(Color.GRAY);
		title.setForeground(Color.ORANGE);
		title.setFont(new Font("Consolas",Font.BOLD,40));
		panelSearch.add(title);
		
		back = new JButton("BACK");
		back.setBounds(550,44,150,40);
		back.setBackground(Color.GRAY);
		back.setForeground(Color.ORANGE);
		back.setFont(new Font("Consolas",Font.BOLD,40));
		back.setOpaque(true);
		back.addActionListener(this);
		panelSearch.add(back);
		
		search = new JButton("SEARCH");
		search.setBounds(550,180,150,30);
		search.setBackground(Color.GRAY);
		search.setForeground(Color.ORANGE);
		search.setFont(new Font("Consolas",Font.BOLD,30));
		search.addActionListener(this);
		panelSearch.add(search);
		
		doctorSearch = new JLabel("DOCTOR FIND       :");
		doctorSearch.setBounds(50,170,210,50);
		doctorSearch.setBackground(Color.BLACK);
	    doctorSearch.setForeground(Color.ORANGE);
		doctorSearch.setFont(new Font("Consolas",Font.BOLD,20));
		doctorSearch.setOpaque(true);
		panelSearch.add(doctorSearch);
		
		TFdoctor = new JTextField();
		TFdoctor.setBounds(300,180,200,30);
		TFdoctor.setBackground(Color.GRAY);
		panelSearch.add(TFdoctor);
		
		this.frame = frame;
		
		this.add(panelSearch);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String elementText = ae.getActionCommand();
		{
			if(elementText.equals(back.getText()))
			{
				this.frame.setVisible(true);
			    this.setVisible(false);
			}
		    else if(elementText.equals(search.getText()))
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
			query = "select * from doctor where ID='"+TFdoctor.getText()+"' or Name like'%"+TFdoctor.getText()+"%'";
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