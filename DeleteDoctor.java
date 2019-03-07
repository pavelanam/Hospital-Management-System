import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;


public class DeleteDoctor extends JFrame implements ActionListener
{
	private JPanel panelDoctor;
	private JLabel title,id,imglabel;
	private JTextField TFid;
	private JButton delete,back;
	private ImageIcon icon,image;
	private JFrame frame;
	
	public DeleteDoctor(JFrame frame)
	{
		super("Delete Doctor");
		this.setSize(800,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelDoctor = new JPanel();
		panelDoctor.setLayout(null);
		panelDoctor.setBackground(Color.BLACK);
		
		icon=new ImageIcon(getClass().getResource("1.png"));
		this.setIconImage(icon.getImage());
		
		icon = new ImageIcon("5.png");
		imglabel = new JLabel(icon);
		imglabel.setBounds(50,120,200,200);
		panelDoctor.add(imglabel);
		
		title = new JLabel("DELETE DOCTOR");
		title.setBounds(50,45,450,50);
		title.setBackground(Color.GRAY);
		title.setForeground(Color.ORANGE);
		title.setFont(new Font("Consolas",Font.BOLD,40));
		panelDoctor.add(title);
		
		back = new JButton("BACK");
		back.setBounds(550,44,200,40);
		back.setBackground(Color.GRAY);
		back.setForeground(Color.ORANGE);
		back.setFont(new Font("Consolas",Font.BOLD,40));
		back.setOpaque(true);
		back.addActionListener(this);
		panelDoctor.add(back);
		
		delete = new JButton("DELETE");
		delete.setBounds(365,250,180,40);
		delete.setBackground(Color.GRAY);
		delete.setForeground(Color.ORANGE);
		delete.setFont(new Font("Consolas",Font.BOLD,35));
		delete.addActionListener(this);
		panelDoctor.add(delete);
		
		id = new JLabel("ID   :");
		id.setBounds(270,170,120,50);
		id.setBackground(Color.BLACK);
		id.setForeground(Color.ORANGE);
		id.setFont(new Font("Consolas",Font.BOLD,20));
		id.setOpaque(true);
		panelDoctor.add(id);
		
		TFid = new JTextField();
		TFid.setBounds(390,180,250,30);
		TFid.setBackground(Color.GRAY);
		panelDoctor.add(TFid);
		
		this.frame = frame;
		
		this.add(panelDoctor);
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
				deleteFromDB();
			}
			else{}
		}
	}
	public void deleteFromDB()
	{
		String query = "DELETE from doctor where ID="+TFid.getText()+";";
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