import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class InsertDoctor extends JFrame implements ActionListener
{
	private JPanel panelDoctor;
	private JLabel name,age,contact,city,gender,qualification,specialist,id,title,salary;
	private JTextField TFname,TFage,TFcontact,TFcity,TFgender,TFqualification,TFspecialist,TFid,TFsalary;
	private ImageIcon icon;
	private JButton confirm,back;
	private JFrame frame;
	
	public InsertDoctor(JFrame frame)
	{
		super("Insert Doctor");
		this.setSize(800,650);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelDoctor = new JPanel();
		panelDoctor.setLayout(null);
		panelDoctor.setBackground(Color.BLACK);
		
		icon=new ImageIcon(getClass().getResource("1.png"));
		this.setIconImage(icon.getImage());
		
		title = new JLabel("INSERT DOCTOR");
		title.setBounds(30,40,400,50);
		title.setBackground(Color.BLACK);
		title.setForeground(Color.ORANGE);
		title.setFont(new Font("Consolas",Font.BOLD,40));
		title.setOpaque(true);
		panelDoctor.add(title);
		
		back = new JButton("BACK");
		back.setBounds(560,40,150,40);
		back.setBackground(Color.GRAY);
		back.setForeground(Color.ORANGE);
		back.setFont(new Font("Consolas",Font.BOLD,40));
		back.addActionListener(this);
		panelDoctor.add(back);
		
		confirm = new JButton("CONFIRM");
		confirm.setBounds(540,535,200,40);
		confirm.setBackground(Color.GRAY);
		confirm.setForeground(Color.ORANGE);
		confirm.setFont(new Font("Consolas",Font.BOLD,40));
		confirm.addActionListener(this);
		panelDoctor.add(confirm);
		
		name = new JLabel("Name         :");
		name.setBounds(30,130,250,50);
		name.setBackground(Color.BLACK);
		name.setForeground(Color.ORANGE);
		name.setFont(new Font("Consolas",Font.BOLD,20));
		name.setOpaque(true);
		panelDoctor.add(name);
		
		age = new JLabel("Age          :");
		age.setBounds(30,180,250,50);
		age.setBackground(Color.BLACK);
		age.setForeground(Color.ORANGE);
		age.setFont(new Font("Consolas",Font.BOLD,20));
		age.setOpaque(true);
		panelDoctor.add(age);
		
		contact = new JLabel("Contact      :");
		contact.setBounds(30,230,250,50);
		contact.setBackground(Color.BLACK);
		contact.setForeground(Color.ORANGE);
		contact.setFont(new Font("Consolas",Font.BOLD,20));
		contact.setOpaque(true);
		panelDoctor.add(contact);
		
		city = new JLabel("City         :");
		city.setBounds(30,280,250,50);
		city.setBackground(Color.BLACK);
		city.setForeground(Color.ORANGE);
		city.setFont(new Font("Consolas",Font.BOLD,20));
		city.setOpaque(true);
		panelDoctor.add(city);
		
		gender = new JLabel("Gender       :");
		gender.setBounds(30,330,250,50);
		gender.setBackground(Color.BLACK);
		gender.setForeground(Color.ORANGE);
		gender.setFont(new Font("Consolas",Font.BOLD,20));
		gender.setOpaque(true);
		panelDoctor.add(gender);
		
		id = new JLabel("ID           :");
		id.setBounds(30,380,250,50);
		id.setBackground(Color.BLACK);
		id.setForeground(Color.ORANGE);
		id.setFont(new Font("Consolas",Font.BOLD,20));
		id.setOpaque(true);
		panelDoctor.add(id);
		
		
		qualification = new JLabel("Qualification:");
		qualification.setBounds(30,430,250,50);
		qualification.setBackground(Color.BLACK);
		qualification.setForeground(Color.ORANGE);
		qualification.setFont(new Font("Consolas",Font.BOLD,20));
		qualification.setOpaque(true);
		panelDoctor.add(qualification);
		
		specialist = new JLabel("Specialist   :");
		specialist.setBounds(30,480,250,50);
		specialist.setBackground(Color.BLACK);
		specialist.setForeground(Color.ORANGE);
		specialist.setFont(new Font("Consolas",Font.BOLD,20));
		specialist.setOpaque(true);
		panelDoctor.add(specialist);
	
		salary = new JLabel("Salary       :");
		salary.setBounds(30,530,250,50);
		salary.setBackground(Color.BLACK);
		salary.setForeground(Color.ORANGE);
		salary.setFont(new Font("Consolas",Font.BOLD,20));
		salary.setOpaque(true);
		panelDoctor.add(salary);
		
		TFname = new JTextField();
		TFname.setBounds(220, 135, 300, 30);
		TFname.setBackground(Color.GRAY);
		panelDoctor.add(TFname);
		
		TFage = new JTextField();
		TFage.setBounds(220, 185, 300, 30);
		TFage.setBackground(Color.GRAY);
		panelDoctor.add(TFage);
		
		TFcontact = new JTextField();
		TFcontact.setBounds(220, 235, 300, 30);
		TFcontact.setBackground(Color.GRAY);
		panelDoctor.add(TFcontact);
		
		TFcity = new JTextField();
		TFcity.setBounds(220,285,300,30);
		TFcity.setBackground(Color.GRAY);
		panelDoctor.add(TFcity);
		
		TFgender = new JTextField();
		TFgender.setBounds(220,335,300,30);
		TFgender.setBackground(Color.GRAY);
		panelDoctor.add(TFgender);
		
		TFid = new JTextField();
		TFid.setBounds(220,385,300,30);
		TFid.setBackground(Color.GRAY);
		panelDoctor.add(TFid);
		
		TFqualification = new JTextField();
		TFqualification.setBounds(220,435,300,30);
		TFqualification.setBackground(Color.GRAY);
		panelDoctor.add(TFqualification);
		
		TFspecialist = new JTextField();
		TFspecialist.setBounds(220,485,300,30);
		TFspecialist.setBackground(Color.GRAY);
		panelDoctor.add(TFspecialist);
		
		TFsalary = new JTextField();
		TFsalary.setBounds(220,535,300,30);
		TFsalary.setBackground(Color.GRAY);
		panelDoctor.add(TFsalary);
		
		this.frame = frame;
		
		this.add(panelDoctor);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String elementText = ae.getActionCommand();
		if(elementText.equals(back.getText()))
		{
			this.frame.setVisible(true);
			this.setVisible(false);
		}
		else if(elementText.equals(confirm.getText()))
		{
			insertIntoDB();
		}
		else{}
	}	
	
	public void insertIntoDB()
	{
		String query = "INSERT INTO doctor VALUES ('"+TFname.getText()+"','"+TFage.getText()+"','"+TFcontact.getText()+"','"+TFcity.getText()+"','"+TFgender.getText()+"','"+TFid.getText()+"','"+TFqualification.getText()+"','"+TFspecialist.getText()+"',"+TFsalary.getText()+");";
		System.out.println(query);
        try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
			Statement stm = con.createStatement();
			stm.execute(query);
			stm.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Successfully Inseted","",JOptionPane.INFORMATION_MESSAGE);	
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
    }
}