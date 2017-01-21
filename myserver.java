import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class myserver {

	public static void main(String[] args) {
		if(args.length!=1){
			System.out.println("please enter one argument as port!");
		}
		try {
			//initialize a server socket, bind port 
			ServerSocket serverSocket=new ServerSocket(Integer.parseInt(args[0]));
			Socket socket=null;
			System.out.println("***server starts!***");
			//accept client continuosly
			while(true){
				socket=serverSocket.accept();
				ServerThread serverThread=new ServerThread(socket);
				serverThread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

