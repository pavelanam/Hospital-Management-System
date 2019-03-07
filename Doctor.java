import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class Doctor extends JFrame implements ActionListener
{
	private JPanel panelDoctor;
	private JLabel title,imgLabel;
	private JButton viewdetails,logout;
	private ImageIcon icon,image;
	private Login l;
	
	public Doctor(Login l)
	{
		super("Doctor");
		
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelDoctor = new JPanel();
		panelDoctor.setLayout(null);
		panelDoctor.setBackground(Color.BLACK);
		
		icon=new ImageIcon(getClass().getResource("1.png"));
		this.setIconImage(icon.getImage());
		
		image = new ImageIcon("5.png");
		imgLabel = new JLabel(image);
		imgLabel.setBounds(100,150,200,200);
		panelDoctor.add(imgLabel);
		
		title = new JLabel("DOCTOR PORTAL");
		title.setBounds(50,40,350,50);
		title.setBackground(Color.BLACK);
		title.setForeground(Color.ORANGE);
		title.setFont(new Font("Consolas",Font.BOLD,40));
		title.setOpaque(true);
		panelDoctor.add(title);
		
		logout = new JButton("LOGOUT");
		logout.setBounds(550,44,200,40);
		logout.setFont(new Font("Consolas",Font.BOLD,40));
		logout.setBackground(Color.GRAY);
		logout.setForeground(Color.ORANGE);
		logout.addActionListener(this);
		panelDoctor.add(logout);
		
		viewdetails = new JButton("VIEW LIST");
		viewdetails.setBounds(400,250,300,40);
		viewdetails.setFont(new Font("Consolas",Font.BOLD,40));
		viewdetails.setBackground(Color.GRAY);
		viewdetails.setForeground(Color.ORANGE);
		viewdetails.addActionListener(this);
		panelDoctor.add(viewdetails);
		
		this.add(panelDoctor);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String elementText = ae.getActionCommand();
		try
		{
			if(elementText.equals(logout.getText()))
			{
				l = new Login();
				l.setVisible(true);
				this.setVisible(false);
			}
			else if(elementText.equals(viewdetails.getText()))
			{
				Test();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
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
			query = "select * from patient";
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