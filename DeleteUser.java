import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;


public class DeleteUser extends JFrame implements ActionListener
{
	private JPanel panelUser;
	private JLabel title,id,imglabel;
	private JTextField TFid;
	private JButton delete,back;
	private ImageIcon icon;
	private JFrame frame;
	
	public DeleteUser(JFrame frame)
	{
		super("Delete User");
		this.setSize(800,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelUser = new JPanel();
		panelUser.setLayout(null);
		panelUser.setBackground(Color.BLACK);
		
		icon=new ImageIcon(getClass().getResource("1.png"));
		this.setIconImage(icon.getImage());
		
		
		title = new JLabel("DELETE USER");
		title.setBounds(50,45,450,50);
		title.setBackground(Color.GRAY);
		title.setForeground(Color.ORANGE);
		title.setFont(new Font("Consolas",Font.BOLD,40));
		panelUser.add(title);
		
		back = new JButton("BACK");
		back.setBounds(550,44,200,40);
		back.setBackground(Color.GRAY);
		back.setForeground(Color.ORANGE);
		back.setFont(new Font("Consolas",Font.BOLD,40));
		back.setOpaque(true);
		back.addActionListener(this);
		panelUser.add(back);
		
		delete = new JButton("DELETE");
		delete.setBounds(420,250,180,40);
		delete.setBackground(Color.GRAY);
		delete.setForeground(Color.ORANGE);
		delete.setFont(new Font("Consolas",Font.BOLD,35));
		delete.addActionListener(this);
		panelUser.add(delete);
		
		id = new JLabel("ID   :");
		id.setBounds(270,170,120,50);
		id.setBackground(Color.BLACK);
		id.setForeground(Color.ORANGE);
		id.setFont(new Font("Consolas",Font.BOLD,20));
		id.setOpaque(true);
		panelUser.add(id);
		
		TFid = new JTextField();
		TFid.setBounds(390,180,250,30);
		TFid.setBackground(Color.GRAY);
		panelUser.add(TFid);
		
		this.frame = frame;
		
		this.add(panelUser);
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
		    else if(elementText.equals(delete.getText()))
			{
				deleteUser();
			}
			else{}
		}
	}
	
	public void deleteUser()
	{
		String x = "A-001";
		String h = TFid.getText();
		if(x.equals(h))
		{
			JOptionPane.showMessageDialog(null,"You Can Not Delete Admin","",JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			String query = "DELETE from login where UserID='"+TFid.getText()+"';";
		    System.out.println(query);
        try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
			Statement stm = con.createStatement();
			stm.execute(query);
			stm.close();
			con.close();		
			JOptionPane.showMessageDialog(null,"Successfully Deleted","",JOptionPane.INFORMATION_MESSAGE);	
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
		}
	}
	
}