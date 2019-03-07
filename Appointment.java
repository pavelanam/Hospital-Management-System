import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Appointment extends JFrame implements ActionListener
{
	private JPanel panelAppointment;
	private JTextField TFname,TFage,TFcity,TFgender,TFnumber,TFtoken,TFproblem;
	private JButton back,confirm;
	private JLabel title,name,age,city,gender,number,token,problem;
	private ImageIcon icon;
	private JFrame frame; //for back previous GUI
	
	public Appointment(JFrame frame)
	{
		super("Appointment");
		
		this.setSize(800,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelAppointment = new JPanel();
		panelAppointment.setLayout(null);
		panelAppointment.setBackground(Color.BLACK);
		
		icon=new ImageIcon(getClass().getResource("1.png"));
		this.setIconImage(icon.getImage());
		
		title = new JLabel("ADD PATIENT");
		title.setBounds(30,40,400,50);
		title.setBackground(Color.BLACK);
		title.setForeground(Color.ORANGE);
		title.setFont(new Font("Consolas",Font.BOLD,40));
		title.setOpaque(true);
		panelAppointment.add(title);
		
		back = new JButton("BACK");
		back.setBounds(560,40,150,40);
		back.setBackground(Color.GRAY);
		back.setForeground(Color.ORANGE);
		back.setFont(new Font("Consolas",Font.BOLD,40));
		back.addActionListener(this);
		panelAppointment.add(back);
		
		confirm = new JButton("CONFIRM");
		confirm.setBounds(540,485,200,40);
		confirm.setBackground(Color.GRAY);
		confirm.setForeground(Color.ORANGE);
		confirm.setFont(new Font("Consolas",Font.BOLD,40));
		confirm.addActionListener(this);
		panelAppointment.add(confirm);
		
		name = new JLabel("Name       :");
		name.setBounds(30,130,150,50);
		name.setBackground(Color.BLACK);
		name.setForeground(Color.ORANGE);
		name.setFont(new Font("Consolas",Font.BOLD,20));
		name.setOpaque(true);
		panelAppointment.add(name);
		
		age = new JLabel("Age        :");
		age.setBounds(30,180,150,50);
		age.setBackground(Color.BLACK);
		age.setForeground(Color.ORANGE);
		age.setFont(new Font("Consolas",Font.BOLD,20));
		age.setOpaque(true);
		panelAppointment.add(age);
		
		gender = new JLabel("Gender     :");
		gender.setBounds(30,230,150,50);
		gender.setBackground(Color.BLACK);
		gender.setForeground(Color.ORANGE);
		gender.setFont(new Font("Consolas",Font.BOLD,20));
		gender.setOpaque(true);
		panelAppointment.add(gender);
		
        number = new JLabel("Phone No.  :");
		number.setBounds(30,280,150,50);
		number.setBackground(Color.BLACK);
		number.setForeground(Color.ORANGE);
		number.setFont(new Font("Consolas",Font.BOLD,20));
		number.setOpaque(true);
		panelAppointment.add(number);
		
		city = new JLabel("City       :");
		city.setBounds(30,330,150,50);
		city.setBackground(Color.BLACK);
		city.setForeground(Color.ORANGE);
		city.setFont(new Font("Consolas",Font.BOLD,20));
		city.setOpaque(true);
		panelAppointment.add(city);
		
		token = new JLabel("Token No.  :");
		token.setBounds(30,380,150,50);
		token.setBackground(Color.BLACK);
		token.setForeground(Color.ORANGE);
		token.setFont(new Font("Consolas",Font.BOLD,20));
		token.setOpaque(true);
		panelAppointment.add(token);
		
		problem = new JLabel("Problem    :");
		problem.setBounds(30,430,150,50);
		problem.setBackground(Color.BLACK);
		problem.setForeground(Color.ORANGE);
		problem.setFont(new Font("Consolas",Font.BOLD,20));
		problem.setOpaque(true);
		panelAppointment.add(problem);
		
		
		TFname = new JTextField();
		TFname.setBounds(200, 135, 300, 30);
		TFname.setBackground(Color.GRAY);
		panelAppointment.add(TFname);
		
		TFage = new JTextField();
		TFage.setBounds(200, 185, 300, 30);
		TFage.setBackground(Color.GRAY);
		panelAppointment.add(TFage);
		
		TFgender = new JTextField();
		TFgender.setBounds(200, 235, 300, 30);
		TFgender.setBackground(Color.GRAY);
		panelAppointment.add(TFgender);
		
		TFnumber = new JTextField();
		TFnumber.setBounds(200, 285, 300, 30);
		TFnumber.setBackground(Color.GRAY);
		panelAppointment.add(TFnumber);
		
		TFcity = new JTextField();
		TFcity.setBounds(200, 335, 300, 30);
		TFcity.setBackground(Color.GRAY);
		panelAppointment.add(TFcity);
		
		TFtoken = new JTextField();
		TFtoken.setBounds(200, 385, 300, 30);
		TFtoken.setBackground(Color.GRAY);
		panelAppointment.add(TFtoken);
		
		TFproblem = new JTextField();
		TFproblem.setBounds(200, 435, 300, 30);
		TFproblem.setBackground(Color.GRAY);
		panelAppointment.add(TFproblem);
		
		this.frame = frame; // put this frame into object reference  
		
		this.add(panelAppointment);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String elementText = ae.getActionCommand();
		if(elementText.equals(back.getText()))
		{
			this.frame.setVisible(true); //current GUI back to previous GUI
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
		String query = "INSERT INTO patient VALUES ('"+TFname.getText()+"','"+TFage.getText()+"','"+TFgender.getText()+"','"+TFnumber.getText()+"','"+TFcity.getText()+"','"+TFtoken.getText()+"','"+TFproblem.getText()+"');";
		System.out.println(query);
        try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
			Statement stm = con.createStatement();
			stm.execute(query);
			stm.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Successfully Inserted","",JOptionPane.INFORMATION_MESSAGE);	
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
    }
}