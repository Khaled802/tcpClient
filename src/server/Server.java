package server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;
import server.Calculator;

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

        ServerSocket ss;
        int port = 4449;
		try {
			ss = new ServerSocket(port);
			System.out.println("server started at port"+ port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
        Socket s;
		try {
			s = ss.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
  

        DataInputStream dis;
		try {
			dis = new DataInputStream(s.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
        DataOutputStream dos;
		try {
			dos = new DataOutputStream(s.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ;
		}
  
        while (true)
        {
            // wait for input
            String input;
			try {
				input = dis.readUTF();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
  
            if(input.equals("stop"))
                break;
  
            System.out.println("Equation received:-" + input);
  
            // Use StringTokenizer to break the equation into operand and
            // operation 
            Calculator calc = new Calculator(input);
            float result;
			try {
				result = calc.getResult();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				try {
					dos.writeUTF(e1.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}
            
            System.out.println("Sending the result...");
  
            // send the result back to the client.
            try {
				dos.writeUTF(Float.toString(result));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

	}

}
