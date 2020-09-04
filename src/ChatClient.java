
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ayush
 */
public class ChatClient extends JFrame implements Runnable{

    Socket socket;
    JTextArea ta;
    JButton send,logout;
    JTextField tf;
    
    Thread thread;
    
    DataInputStream din;
    DataOutputStream dout;
    
    String LogInName;
    
    ChatClient(String login) throws UnknownHostException, IOException{
    super(login);
    LogInName = login;
    
    ta = new JTextArea(27 , 50);
    tf = new JTextField(50);
    
    Border border = BorderFactory.createLineBorder(new Color(250, 176, 2), 3);
    tf.setBorder(border);
   
    
   
    
    send = new JButton("SEND");
    logout = new JButton("LOGOUT");
    
    send.setForeground(Color.black);
    send.setBackground( new Color(250, 176, 2) );
    
    logout.setForeground(Color.black);
    logout.setBackground( new Color(250, 176, 2) );
    
    send.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				dout.writeUTF(LogInName+ " " +"DATA "+ tf.getText().toString());
				tf.setText("");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
    	
    	
    });
    logout.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				dout.writeUTF(LogInName+ " " +"LOGOUT");
				System.exit(1);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
    	
    	
    });
    
    tf.addKeyListener(new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyChar()==KeyEvent.VK_ENTER) {
			try {
				dout.writeUTF(LogInName+ " " +"DATA "+ tf.getText().toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			tf.setText("");
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

	
	});
    
    socket = new Socket("localhost",1234);
    
    din = new DataInputStream(socket.getInputStream());
     dout = new DataOutputStream(socket.getOutputStream());
     
     dout.writeUTF(LogInName);
     dout.writeUTF(LogInName+" "+"LOGIN");
     
     thread = new Thread(this);
     thread.start();
     setup();
    }
    
    @Override
    public void run() {
       while(true){
           try {
               ta.append("\n"+din.readUTF());
           } catch (IOException ex) {
               Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
    }

    private void setup() {
    	JFrame f = new JFrame("Eccentric Messenger");
    	
        
        
        setSize(570,550);
        JPanel panel = new JPanel();
        
        panel.setPreferredSize(new Dimension(320, 500));
        panel.add(new JScrollPane(ta));
        panel.add(tf);
        panel.add(send);
        panel.add(logout);
        panel.setBackground(Color.BLACK);
        Border border1 = BorderFactory.createLineBorder(new Color(250, 176, 2), 4);
        panel.setBorder(border1);
        
       
        
        
        f.add(panel);
        f.setSize(570,550);
        f.setVisible(true);
        
        
        ta.setForeground(new Color(250, 176, 2));
        
    
         
        
    }


    
}
