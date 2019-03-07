import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.*;

public class ReceptionistSearch extends JFrame implements ActionListener
{
	private JPanel panelSearch;
	private JLabel title,staffSearch;
	private JTextField TFstaff;
	private JButton search,back;
	private ImageIcon icon;
	private JFrame frame;
	
	public ReceptionistSearch(JFrame frame)
	{
		super("Search Receptionist");
		this.setSize(700,450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelSearch = new JPanel();
		panelSearch.setLayout(null);
		panelSearch.setBackground(Color.BLACK);
		
		icon=new ImageIcon(getClass().getResource("1.png"));
		this.setIconImage(icon.getImage());
		
		title = new JLabel("SEARCH RECEPTIONIST");
		title.setBounds(50,45,450,50);
		title.setBackground(Color.GRAY);
		title.setForeground(Color.ORANGE);
		title.setFont(new Font("Consolas",Font.BOLD,40));
		panelSearch.add(title);
		
		back = new JButton("BACK");
		back.setBounds(500,48,150,40);
		back.setBackground(Color.GRAY);
		back.setForeground(Color.ORANGE);
		back.setFont(new Font("Consolas",Font.BOLD,40));
		back.setOpaque(true);
		back.addActionListener(this);
		panelSearch.add(back);
		
		search = new JButton("SEARCH");
		search.setBounds(350,250,150,40);
		search.setBackground(Color.GRAY);
		search.setForeground(Color.ORANGE);
		search.setFont(new Font("Consolas",Font.BOLD,35));
		search.addActionListener(this);
		panelSearch.add(search);
		
		staffSearch = new JLabel("RECEPTIONIST FIND   :");
		staffSearch.setBounds(40,170,240,50);
		staffSearch.setBackground(Color.BLACK);
	    staffSearch.setForeground(Color.ORANGE);
		staffSearch.setFont(new Font("Consolas",Font.BOLD,20));
		staffSearch.setOpaque(true);
		panelSearch.add(staffSearch);
		
		TFstaff = new JTextField();
		TFstaff.setBounds(300,180,250,30);
		TFstaff.setBackground(Color.GRAY);
		panelSearch.add(TFstaff);
		
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
			query = "select * from receptionist where ID='"+TFstaff.getText()+"' or Name like'%"+TFstaff.getText()+"%'";
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