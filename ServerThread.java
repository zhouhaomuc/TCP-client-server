import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerThread extends Thread {
	
	Socket socket=null;
	int flag=0;	//a flag to judge whether the zip is found	
	public ServerThread(Socket socket){
		this.socket=socket;
	}
	
	public void run(){
		//get input information from client
		InputStream instr=null;
		InputStreamReader inread=null;
		BufferedReader buffread=null;
		String info=null;
		try {
			instr = socket.getInputStream();
			inread = new InputStreamReader(instr);
			buffread = new BufferedReader(inread);
			info = buffread.readLine();
			if(info!=null){
				System.out.println("client input: "+info);
			}
		socket.shutdownInput();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//open file cityzip.csv, read file line by line, store data in array
		try { 
			String path="cityzip.csv";
            		BufferedReader reader = new BufferedReader(new FileReader(path));
            		reader.readLine();
			String line =null; 
			while((line=reader.readLine())!=null){
				//split line attributes by array 
                		String attributes[] = line.split(",");
               			String zip = attributes[2];
				//if input zip equals zip listed in cityzip.csv, zip found
                		if(zip.equals(info)){
                			String state=attributes[0];
					String city=attributes[1];
               		 		OutputStream outstr=socket.getOutputStream();
        				PrintWriter pw=new PrintWriter(outstr);
        				pw.write("City: ");
        				pw.write(city);
					pw.write("\n");
        				pw.flush();
        				pw.write("State: ");
        				pw.write(state);
        				pw.flush();
					flag=1;	//flag=1 means zip found
                			break;
				}
//		System.out.println("Zip not exist, check your input!");
            		}
       		} catch (Exception e) { 
            		e.printStackTrace(); 
        	}
 		//if flag=0 after while loop, then zip is not found in file.
		if(flag==0){
			try{
				OutputStream out=socket.getOutputStream();
				PrintWriter wt=new PrintWriter(out);
				wt.write("Sorry, zip does not exsits, please check your input!");
				wt.flush();
			} catch (Exception e){
				e.printStackTrace();
			}
		}	
		//close
		try {
			buffread.close();
			inread.close();
			instr.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {

	}

}

