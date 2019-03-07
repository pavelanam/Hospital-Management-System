import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Admin extends JFrame implements ActionListener
{
	private JPanel panelAdmin;
	private JLabel titleLabel,imgLabel;
	private JButton buttonLogout,doctor,receptionist,user,deleteuser;
	private ImageIcon icon,image;
	private Login l;
	
	public Admin(Login l)
	{
		super("Admin");
		
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelAdmin = new JPanel();
		panelAdmin.setLayout(null);
		panelAdmin.setBackground(Color.BLACK);
		
		icon=new ImageIcon(getClass().getResource("1.png"));
		this.setIconImage(icon.getImage());
		
		titleLabel = new JLabel("ADMIN PORTAL");
		titleLabel.setBounds(30, 40, 350, 45);
		titleLabel.setBackground(Color.BLACK);
		titleLabel.setForeground(Color.BLUE);
		titleLabel.setFont(new Font("Consolas",Font.BOLD,45));
		titleLabel.setOpaque(true);
		panelAdmin.add(titleLabel);
		
		image = new ImageIcon("3.png");
		imgLabel = new JLabel(image);
		imgLabel.setBounds(30,120,370,360);
		panelAdmin.add(imgLabel);
		
		buttonLogout = new JButton("LOGOUT");
		buttonLogout.setBounds(550,40,200,40);
		buttonLogout.setBackground(Color.GRAY);
		buttonLogout.setForeground(Color.ORANGE);
		buttonLogout.setFont(new Font("Consolas",Font.BOLD,40));
		buttonLogout.addActionListener(this);
		
		panelAdmin.add(buttonLogout);
		
		doctor = new JButton("DOCTOR");
		doctor.setBounds(450,200,300,40);
		doctor.setBackground(Color.GRAY);
		doctor.setForeground(Color.ORANGE);
		doctor.setFont(new Font("Consolas",Font.BOLD,40));
		doctor.addActionListener(this);
		panelAdmin.add(doctor);
		
		receptionist = new JButton("RECEPTIONIST");
		receptionist.setBounds(450,275,300,40);
		receptionist.setBackground(Color.GRAY);
		receptionist.setForeground(Color.ORANGE);
		receptionist.setFont(new Font("Consolas",Font.BOLD,40));
		receptionist.addActionListener(this);
		panelAdmin.add(receptionist);
		
		user = new JButton("ADD USER");
		user.setBounds(450,350,300,40);
		user.setBackground(Color.GRAY);
		user.setForeground(Color.ORANGE);
		user.setFont(new Font("Consolas",Font.BOLD,40));
		user.addActionListener(this);
		panelAdmin.add(user);
		
		deleteuser = new JButton("DELETE USER");
		deleteuser.setBounds(450,425,300,40);
		deleteuser.setBackground(Color.GRAY);
		deleteuser.setForeground(Color.ORANGE);
		deleteuser.setFont(new Font("Consolas",Font.BOLD,40));
		deleteuser.addActionListener(this);
		panelAdmin.add(deleteuser);
		
		this.add(panelAdmin);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String elementText = ae.getActionCommand();
			if(elementText.equals(buttonLogout.getText()))
			{
				l = new Login();
				l.setVisible(true);
				this.setVisible(false);
			}
			else if(elementText.equals(doctor.getText()))
			{
				DoctorHome dh = new DoctorHome(this);
				dh.setVisible(true);
				this.setVisible(false);
			}
			else if(elementText.equals(receptionist.getText()))
			{
				ReceptionistHome rh = new ReceptionistHome(this);
				rh.setVisible(true);
				this.setVisible(false);
			}
			else if(elementText.equals(user.getText()))
			{
				CreateUser cu = new CreateUser(this);
				cu.setVisible(true);
				this.setVisible(false);
			}
			else if(elementText.equals(deleteuser.getText()))
			{
				DeleteUser du = new DeleteUser(this);
				du.setVisible(true);
				this.setVisible(false);
			}
			else{}
	}
}