import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class DoctorHome extends JFrame implements ActionListener
{
	private JPanel panelDoctor;
	private JButton back,add,delete,update,viewdetails,search;
	private ImageIcon icon,image;
	private JLabel imagelabel,doctor;
	private JFrame frame;
	
	public DoctorHome(JFrame frame)
	{
		super("Doctor Home");
		
		this.setSize(800,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelDoctor = new JPanel();
		panelDoctor.setLayout(null);
		panelDoctor.setBackground(Color.BLACK);
		
		icon=new ImageIcon(getClass().getResource("1.png"));
		this.setIconImage(icon.getImage());
		
		image = new ImageIcon("5.png");
		imagelabel = new JLabel(image);
		imagelabel.setBounds(50,200,200,200);
		panelDoctor.add(imagelabel);
		
		doctor = new JLabel("DOCTOR HOME");
		doctor.setBounds(50,45,320,50);
		doctor.setBackground(Color.BLACK);
		doctor.setForeground(Color.ORANGE);
		doctor.setFont(new Font("Consolas",Font.BOLD,40));
		doctor.setOpaque(true);
		panelDoctor.add(doctor);
		
		back = new JButton("BACK");
		back.setBounds(550,44,150,40);
		back.setFont(new Font("Consolas",Font.BOLD,40));
		back.setBackground(Color.GRAY);
		back.setForeground(Color.ORANGE);
		back.addActionListener(this);
		panelDoctor.add(back);
		
		add = new JButton("ADD");
		add.setBounds(350,150,350,40);
		add.setFont(new Font("Consolas",Font.BOLD,40));
		add.setBackground(Color.GRAY);
		add.setForeground(Color.ORANGE);
		add.addActionListener(this);
		panelDoctor.add(add);
		
		delete = new JButton("DELETE");
		delete.setBounds(350,220,350,40);
		delete.setFont(new Font("Consolas",Font.BOLD,40));
		delete.setBackground(Color.GRAY);
		delete.setForeground(Color.ORANGE);
		delete.addActionListener(this);
		panelDoctor.add(delete);
		
		update = new JButton("UPDATE");
		update.setBounds(350,290,350,40);
		update.setFont(new Font("Consolas",Font.BOLD,40));
		update.setBackground(Color.GRAY);
		update.setForeground(Color.ORANGE);
		update.addActionListener(this);
		panelDoctor.add(update);
		
		viewdetails = new JButton("VIEW DETAILS");
		viewdetails.setBounds(350,360,350,40);
		viewdetails.setFont(new Font("Consolas",Font.BOLD,40));
		viewdetails.setBackground(Color.GRAY);
		viewdetails.setForeground(Color.ORANGE);
		viewdetails.addActionListener(this);
		panelDoctor.add(viewdetails);
		
		search = new JButton("SEARCH");
		search.setBounds(350,430,350,40);
		search.setFont(new Font("Consolas",Font.BOLD,40));
		search.setBackground(Color.GRAY);
		search.setForeground(Color.ORANGE);
		search.addActionListener(this);
		panelDoctor.add(search);
		
		this.frame = frame;
		
		this.add(panelDoctor);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String elementText = ae.getActionCommand();
		{
			if(elementText.equals(add.getText()))
			{
				InsertDoctor isd = new InsertDoctor(this);
				isd.setVisible(true);
				this.setVisible(false);
			}
			else if(elementText.equals(delete.getText()))
			{
				DeleteDoctor dd = new DeleteDoctor(this);
				dd.setVisible(true);
				this.setVisible(false);
			}
			else if(elementText.equals(back.getText()))
			{
				this.frame.setVisible(true);
			    this.setVisible(false);
			}
			else if(elementText.equals(update.getText()))
			{
				UpdateDoctor ud = new UpdateDoctor(this);
				ud.setVisible(true);
				this.setVisible(false);
			}
			else if(elementText.equals(search.getText()))
			{
				DoctorSearch ds = new DoctorSearch(this);
				ds.setVisible(true);
				this.setVisible(false);
			}
			else if(elementText.equals(viewdetails.getText()))
			{
				Test();
			}
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
			query = "select * from doctor";
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