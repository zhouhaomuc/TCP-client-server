import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class myclient {

	public static void main(String[] args) {
		if(args.length!=2){
			System.out.println("please enter 2 arguments as server address and port!");
		}
		
		//initialize socket for a client
		try {
			Socket socket=new Socket(args[0],Integer.parseInt(args[1]));
			//outputstream
			OutputStream outstr=socket.getOutputStream();
			PrintWriter pw=new PrintWriter(outstr);
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 			 //get zip input and output to server
	          	String outinfo = null; 
	           	System.out.print("enter zip: "); 
	           	outinfo = br.readLine(); 
			pw.write(outinfo);
			pw.flush();
			socket.shutdownOutput();	//shutdown output
			//inputstream, get city and state information from server	
			InputStream instr=socket.getInputStream();
			InputStreamReader inread=new InputStreamReader(instr);
			BufferedReader buffread=new BufferedReader(inread);
			String recinfo=null;
			while(null!=(recinfo=buffread.readLine())){
				System.out.println(recinfo);
			}
			socket.shutdownInput();
			
			//shutdown
			pw.close();
			outstr.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

