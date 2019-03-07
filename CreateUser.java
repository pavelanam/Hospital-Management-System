import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class CreateUser extends JFrame implements ActionListener
{
	private JPanel panelUser;
	private JLabel name,id,password,type,title;
	private JTextField TFname,TFid,TFpassword,TFtype;
	private ImageIcon icon;
	private JButton confirm,back;
	private JFrame frame;
	
	public CreateUser(JFrame frame)
	{
		super("Create User");
		this.setSize(800,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelUser = new JPanel();
		panelUser.setLayout(null);
		panelUser.setBackground(Color.BLACK);
		
		icon=new ImageIcon(getClass().getResource("1.png"));
		this.setIconImage(icon.getImage());
		
		title = new JLabel("CREATE USER");
		title.setBounds(30,40,400,50);
		title.setBackground(Color.BLACK);
		title.setForeground(Color.ORANGE);
		title.setFont(new Font("Consolas",Font.BOLD,40));
		title.setOpaque(true);
		panelUser.add(title);
		
		back = new JButton("BACK");
		back.setBounds(560,40,150,40);
		back.setBackground(Color.GRAY);
		back.setForeground(Color.ORANGE);
		back.setFont(new Font("Consolas",Font.BOLD,40));
		back.addActionListener(this);
		panelUser.add(back);
		
		name = new JLabel("User Name     :");
		name.setBounds(30,130,240,50);
		name.setBackground(Color.BLACK);
		name.setForeground(Color.ORANGE);
		name.setFont(new Font("Consolas",Font.BOLD,20));
		name.setOpaque(true);
		panelUser.add(name);
		
		id = new JLabel("User ID       :");
		id.setBounds(30,180,240,50);
		id.setBackground(Color.BLACK);
		id.setForeground(Color.ORANGE);
		id.setFont(new Font("Consolas",Font.BOLD,20));
		id.setOpaque(true);
		panelUser.add(id);
		
		password = new JLabel("Password      :");
		password.setBounds(30,230,240,50);
		password.setBackground(Color.BLACK);
		password.setForeground(Color.ORANGE);
		password.setFont(new Font("Consolas",Font.BOLD,20));
		password.setOpaque(true);
		panelUser.add(password);
		
		type = new JLabel("User Type     :");
		type.setBounds(30,280,240,50);
		type.setBackground(Color.BLACK);
		type.setForeground(Color.ORANGE);
		type.setFont(new Font("Consolas",Font.BOLD,20));
		type.setOpaque(true);
		panelUser.add(type);
		
		TFname = new JTextField();
		TFname.setBounds(250, 135, 300, 30);
		TFname.setBackground(Color.GRAY);
		panelUser.add(TFname);
		
		TFid = new JTextField();
		TFid.setBounds(250, 185, 300, 30);
		TFid.setBackground(Color.GRAY);
		panelUser.add(TFid);
		
		TFpassword = new JTextField();
		TFpassword.setBounds(250, 235, 300, 30);
		TFpassword.setBackground(Color.GRAY);
		panelUser.add(TFpassword);
		
		TFtype = new JTextField();
		TFtype.setBounds(250,285,300,30);
		TFtype.setBackground(Color.GRAY);
		panelUser.add(TFtype);
		
		confirm = new JButton("CONFIRM");
		confirm.setBounds(250,360,200,40);
		confirm.setBackground(Color.GRAY);
		confirm.setForeground(Color.ORANGE);
		confirm.setFont(new Font("Consolas",Font.BOLD,40));
		confirm.addActionListener(this);
		panelUser.add(confirm);
		
		this.frame = frame;
		
		this.add(panelUser);
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
		String query = "INSERT INTO login VALUES ('"+TFname.getText()+"','"+TFid.getText()+"','"+TFpassword.getText()+"','"+TFtype.getText()+"');";
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