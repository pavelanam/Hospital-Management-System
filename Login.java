import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener
{
	private JLabel nameLabel, passLabel, titleLabel, imgLabel;
	private JTextField userNameTF;
	private JPasswordField passPF;
	private JButton buttonLogin;
	private JPanel panel;
	private ImageIcon image, icon;
	private boolean flag;
	
	public Login()
	{
		super("Orange Health Care");
		
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.BLACK);
		
		icon=new ImageIcon(getClass().getResource("1.png"));
		this.setIconImage(icon.getImage());
		
		image = new ImageIcon("Health.png");
		imgLabel = new JLabel(image);
		imgLabel.setBounds(0,100,370,380);
		panel.add(imgLabel);
		
		titleLabel = new JLabel("ORANGE HEALTH CARE");
		titleLabel.setBounds(150, 50, 700, 45);
		titleLabel.setBackground(Color.BLACK);
		titleLabel.setForeground(Color.BLUE);
		titleLabel.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,45));
		titleLabel.setOpaque(true);
		panel.add(titleLabel);
		
		nameLabel = new JLabel("UserID   : ");
		nameLabel.setBounds(375, 200, 130, 25);
		nameLabel.setBackground(Color.BLACK);
		nameLabel.setForeground(Color.BLUE);
		nameLabel.setFont(new Font("Consolas",Font.BOLD,20));
		nameLabel.setOpaque(true);
		panel.add(nameLabel);
		
		userNameTF = new JTextField();
		userNameTF.setBounds(520, 200, 200, 28);
		userNameTF.setBackground(Color.LIGHT_GRAY);
		panel.add(userNameTF);
		
		passLabel = new JLabel("Password : ");
		passLabel.setBounds(375, 280, 130, 25);
		passLabel.setBackground(Color.BLACK);
		passLabel.setForeground(Color.BLUE);
		passLabel.setFont(new Font("Consolas",Font.BOLD,20));
		passLabel.setOpaque(true);
		panel.add(passLabel);
		
		passPF = new JPasswordField();
		passPF.setBounds(520, 280, 200, 28);
		passPF.setBackground(Color.LIGHT_GRAY);
		panel.add(passPF);
		
		buttonLogin = new JButton("LOGIN");
		buttonLogin.setBounds(430,340,200,35);
		buttonLogin.setBackground(Color.LIGHT_GRAY);
		buttonLogin.addActionListener(this);
		buttonLogin.setFont(new Font("Consolas",Font.BOLD,20));
		panel.add(buttonLogin);
		
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String buttonClicked = ae.getActionCommand();
		
		if(buttonClicked.equals(buttonLogin.getText()))
		{
			System.out.println("Check");
			flag=true;
			check();
		}
		else{}
	}
	
	public void check()
	{
        String query = "SELECT `UserName`, `UserID`, `Password`, `UserType`FROM `login`;";     
        Connection con=null;//for connection
        Statement st = null;//for query execution
		ResultSet rs = null;//to get row by row result from DB
		System.out.println(query);
        try
		{
			Class.forName("com.mysql.jdbc.Driver");//load driver
			System.out.println("driver loaded");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
			System.out.println("connection done");//connection with database established
			st = con.createStatement();//create statement
			System.out.println("statement created");
			rs = st.executeQuery(query);//getting result
			System.out.println("results received");
					
			while(rs.next())
			{
                String username = rs.getString("UserName");
				String userId = rs.getString("UserID");
                String password = rs.getString("Password");
				String usertype = rs.getString("UserType");

				
				
				if(userId.equals(userNameTF.getText()))
				{
					flag=false;
					if(password.equals(passPF.getText()))
					{
						if(usertype.equals("Admin"))
						{
							Admin  a = new Admin(this);
							a.setVisible(true);
							this.setVisible(false);
							
							System.out.println("Hello Admin");
						}
						else if (usertype.equals("Receptionist"))
						{
							Receptionist r = new Receptionist(this);
							r.setVisible(true);
							this.setVisible(false);
							System.out.println("Hello Staff");
						}
						else if(usertype.equals("Doctor"))
						{
							Doctor d = new Doctor(this);
							d.setVisible(true);
							this.setVisible(false);
							System.out.println("Hello Doctor");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Invalid Password","Warning",JOptionPane.ERROR_MESSAGE); 
					}
				}			
			}
			if(flag){JOptionPane.showMessageDialog(null,"Invalid User ID","Warning",JOptionPane.ERROR_MESSAGE); }
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
        finally
		{
            try
			{
                if(rs!=null)
					rs.close();

                if(st!=null)
					st.close();

                if(con!=null)
					con.close();
            }
            catch(Exception ex){}
        }
    } 
}