import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DeleteReceptionist extends JFrame implements ActionListener
{
	private JPanel panelReceptionist;
	private JButton delete,back;
	private JLabel search,labelimage,title;
	private JTextField TFsearch;
	private ImageIcon icon,image;
	private JFrame frame;
	
	public DeleteReceptionist(JFrame frame)
	{
		super("Delete Receptionist");
		this.setSize(800,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		icon=new ImageIcon(getClass().getResource("1.png"));
		this.setIconImage(icon.getImage());
		
		panelReceptionist = new JPanel();
		panelReceptionist.setLayout(null);
		panelReceptionist.setBackground(Color.BLACK);
		
		image = new ImageIcon("4.png");
		labelimage = new JLabel(image);
		labelimage.setBounds(50,200,200,200);
		panelReceptionist.add(labelimage);
		
		search = new  JLabel("ID  :");
		search.setBounds(300,200,100,100);
		search.setBackground(Color.BLACK);
		search.setForeground(Color.ORANGE);
		search.setFont(new Font("Arial",Font.BOLD,40));
	    panelReceptionist.add(search);
		
		title = new JLabel("DELETE RECEPTIONIST");
		title.setBounds(30,40,480,50);
		title.setBackground(Color.BLACK);
		title.setForeground(Color.ORANGE);
		title.setFont(new Font("Arial",Font.BOLD,40));
		title.setOpaque(true);
		panelReceptionist.add(title);
		
		TFsearch = new JTextField();
		TFsearch.setBounds(400,230,250,30);
		TFsearch.setBackground(Color.GRAY);
		panelReceptionist.add(TFsearch);
		
		back = new JButton("BACK");
		back.setBounds(580,40,150,40);
		back.setBackground(Color.GRAY);
		back.setForeground(Color.ORANGE);
		back.setFont(new Font("Consolas",Font.BOLD,40));
		back.addActionListener(this);
		panelReceptionist.add(back);
		
		delete = new JButton("DELETE");
		delete.setBounds(400,300,180,40);
		delete.setBackground(Color.GRAY);
		delete.setForeground(Color.ORANGE);
		delete.setFont(new Font("Consolas",Font.BOLD,40));
		delete.addActionListener(this);
		panelReceptionist.add(delete);
		
		this.frame = frame;
		
		this.add(panelReceptionist);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String elemntText = ae.getActionCommand();
		{
			if(elemntText.equals(back.getText()))
			{
				this.frame.setVisible(true);
				this.setVisible(false);
			}
			else if(elemntText.equals(delete.getText()))
			{
				deleteFromDB();
			}
		}
	}
	public void deleteFromDB()
	{
		String query = "DELETE from receptionist where ID="+TFsearch.getText()+";";
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