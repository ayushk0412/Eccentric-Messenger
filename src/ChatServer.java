
import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
import java.util.Vector;
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
public class ChatServer {
    static Vector ClientSockets;
    static Vector LogInNames;
    
    ChatServer() throws IOException{
    ServerSocket server = new ServerSocket(1234);
    ClientSockets = new Vector();
    LogInNames = new Vector();
  
            while(true){
            Socket client = server.accept();
            AcceptClient acceptClient = new AcceptClient(client);
            
            }
    
    }
    
    public static void main(String []arge) throws IOException{
    ChatServer server = new ChatServer();
    }
    
    class AcceptClient extends Thread{
    Socket ClientSocket;
    DataInputStream din;
    DataOutputStream dout;
    AcceptClient(Socket client) throws IOException {
     ClientSocket = client;
     din = new DataInputStream(ClientSocket.getInputStream());
     dout = new DataOutputStream(ClientSocket.getOutputStream());
     
     String LogInName = din.readUTF();
     
     LogInNames.add(LogInName);
     ClientSockets.add(ClientSocket);
     
     start();
      }
    public void run(){
        while(true){
            try {
                String msgFromClient = din.readUTF();
                StringTokenizer st = new StringTokenizer(msgFromClient);
                String LogInName = st.nextToken();
                String MsgType = st.nextToken();
                int lo = -1;
                
                String msg = "";
                
                while(st.hasMoreTokens()) {
                	
                	msg = msg+" "+st.nextToken();
                }
                System.out.println("loop");
                if(MsgType.equals("LOGIN")) {
                for(int i =0; i<LogInNames.size();i++){
                Socket pSocket = (Socket) ClientSockets.elementAt(i);
                DataOutputStream pOut = new DataOutputStream(pSocket.getOutputStream());
                pOut.writeUTF(LogInName + " has Logged in. ");
                }
                }
                else if(MsgType.equals("LOGOUT")) {
                    for(int i =0; i<LogInNames.size();i++){
                    	if(LogInName.equals(LogInNames.elementAt(i)))
                    	lo = i;
                        Socket pSocket = (Socket) ClientSockets.elementAt(i);
                        DataOutputStream pOut = new DataOutputStream(pSocket.getOutputStream());
                        pOut.writeUTF(LogInName + " has Logged out. ");
                        }
                    if(lo>=0) {
                    	LogInNames.removeElementAt(lo);
                    	ClientSockets.removeElementAt(lo);
                    }
                        }
                else{
                    for(int i =0; i<LogInNames.size();i++){
                        Socket pSocket = (Socket) ClientSockets.elementAt(i);
                        DataOutputStream pOut = new DataOutputStream(pSocket.getOutputStream());
                        pOut.writeUTF(LogInName + " : " +msg);
                        }
                        }
                if(MsgType.contentEquals("LOGOUT"))
                	break;
                
            } catch (IOException ex) {
                Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
      } 
    }
  }
}