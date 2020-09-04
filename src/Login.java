import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.UnknownHostException;

public class Login {
public static void main(String []args) {
	JFrame login = new JFrame("Login");
	JPanel panel = new JPanel();
	JLabel l = new JLabel("USERNAME: ");
	JTextField loginName = new JTextField(20);
	JButton enter = new JButton("Login");

     
	panel.add(l);
	panel.add(loginName);
	panel.add(enter);
	panel.setBackground(new Color(250, 176, 2));
	login.setSize(350,100);
	login.add(panel);
	login.setVisible(true);
	login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	enter.setForeground( new Color(250, 176, 2) );
    enter.setBackground(Color.BLACK);
    
    Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
    loginName.setBorder(border);
	
	
	enter.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				ChatClient client = new ChatClient(loginName.getText());
				login.setVisible(false);
				login.dispose(); //still use resources
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	});
	//*************************__FOR SHOTCUT KEY__*************************************
	loginName.addKeyListener(new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyChar()==KeyEvent.VK_ENTER) {
			try {
				ChatClient client = new ChatClient(loginName.getText());
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			login.setVisible(false);
			login.dispose(); //still use resources
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	});
}
}
