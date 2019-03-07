import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.*;

public class Receptionist extends JFrame implements ActionListener
{
	private JLabel recep1,recep2;
	private JButton appointment,viewdetails,logout,search;
	private JPanel panelStaff;
	private ImageIcon icon,image;
	private Login l;
	
	public Receptionist(Login l)
	{
		super("Receptionist");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		icon = new ImageIcon(getClass().getResource("1.png"));
		this.setIconImage(icon.getImage());
		
		panelStaff = new JPanel();
		panelStaff.setLayout(null);
		panelStaff.setBackground(Color.BLACK);
		
		recep1 = new JLabel("RECEPTIONIST PORTAL");
		recep1.setBounds(30,40,450,50);
		recep1.setBackground(Color.BLACK);
		recep1.setForeground(Color.ORANGE);
		recep1.setFont(new Font("Consolas",Font.BOLD,40));
		recep1.setOpaque(true);
		panelStaff.add(recep1);
		
		image = new ImageIcon("4.png");
		recep2 = new JLabel(image);
		recep2.setBounds(100,200,200,200);
		panelStaff.add(recep2);
		
		logout = new JButton("LOGOUT");
		logout.setBounds(550,44,200,40);
		logout.setFont(new Font("Consolas",Font.BOLD,40));
		logout.setBackground(Color.GRAY);
		logout.setForeground(Color.ORANGE);
		logout.addActionListener(this);
		panelStaff.add(logout);
		
		appointment = new JButton("APPOINTMENT");
		appointment.setBounds(400,200,300,40);
		appointment.setFont(new Font("Consolas",Font.BOLD,40));
		appointment.setBackground(Color.GRAY);
		appointment.setForeground(Color.ORANGE);
		appointment.addActionListener(this);
		panelStaff.add(appointment);
		
		viewdetails = new JButton("VIEW DETAILS");
		viewdetails.setBounds(400,300,300,40);
		viewdetails.setFont(new Font("Consolas",Font.BOLD,40));
		viewdetails.setBackground(Color.GRAY);
		viewdetails.setForeground(Color.ORANGE);
		viewdetails.addActionListener(this);
		panelStaff.add(viewdetails);
		
		search = new JButton("SEARCH");
		search.setBounds(400,400,300,40);
		search.setFont(new Font("Consolas",Font.BOLD,40));
		search.setBackground(Color.GRAY);
		search.setForeground(Color.ORANGE);
		search.addActionListener(this);
		panelStaff.add(search);
		
		this.add(panelStaff);
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
			else if(elementText.equals(appointment.getText()))
			{
				Appointment ap = new Appointment(this);
				ap.setVisible(true);
				this.setVisible(false);
			}
			else if(elementText.equals(viewdetails.getText()))
			{
				Test();
			}
			else if(elementText.equals(search.getText()))
			{
				SearchPatient sp = new SearchPatient(this);
				sp.setVisible(true);
				this.setVisible(false);
			}
			else{}
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