package tcpClient;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException {
		InetAddress ip;
		try {
			ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return;
		}
        int port = 4449;
        Scanner sc = new Scanner(System.in);
  
=
        Socket s;
		try {
			s = new Socket(ip, port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
  
        // Step 2: Communication-get the input and output stream
        DataInputStream dis;
		try {
			dis = new DataInputStream(s.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
  
        while (true)
        {
            System.out.print("Enter the equation in the form: ");
            System.out.println("'operand operator operand'");
  
            String inp = sc.nextLine();
  
            if (inp.equals("stop"))
                break;
  
            // send the equation to server
            dos.writeUTF(inp);
  
            // wait till request is processed and sent back to client
            String ans = dis.readUTF();
            System.out.println("Answer=" + ans);
        }

	}

}
