import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

public class User {

	private String fName;
	private String lName;
	private Date dob;
	private Date creationDate;
	private long ID;
	private int activeRecipes;
	private String host;
	private int port;
	public String name;
	
	public User(String host, int port) {
		this.host = host;
		this.port = port;
		this.fName = fName;
		this.lName = lName;
		this.dob = dob;
		this.creationDate = new Date();
		this.ID = -1;
		this.activeRecipes = 0;
	}
	
	public String getFName() {
		return this.fName;
	}
	
	public void setFName(String fName) {
		this.fName = fName;
	}
	
	public String getLName() {
		return this.lName;
	}
	
	public void setLName(String lName) {
		this.lName = lName;
	}
	
	public String getDob() {
		return this.dob.toString();
	}
	
	public String getCreationDate() {
		return this.creationDate.toString();
	}
	
	public void setID(int ID) {
		if(this.ID == -1)
			this.ID = ID;
	}
	
	public long getID() {
		return this.ID;
	}
	
	public int getActiveRecipes() {
		return this.activeRecipes;
	}
		
	public static void main(String[] args) {
		int port = Server.getPort();
		String host = "localhost";
		
		try {
			host = args[0];
		}catch(Exception e) {}
		
		User user = new User(host, port);
		user.connect();
	}
	
	public void connect() {
		try {
			Socket socket = new Socket(host, port);
			
			Thread input = new InputThread(socket.getInputStream());
			Thread output = new OutputThread(socket.getOutputStream(), this, socket);
				
			input.start();
			output.start();
		
			
			
		}catch(UnknownHostException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	private void setName(String name) {
		this.name = name;
	}
	
	class InputThread extends Thread{
		private BufferedReader reader;
		
		public InputThread(InputStream in) {
			reader = new BufferedReader(new InputStreamReader(in));
		}
		
		public void run() {
			
			while(true) {
				try {
					String message = reader.readLine();
					System.out.println(message);						
					
				}catch(IOException e) {
					break;
				}
			}		
		}
	}
	
	class OutputThread extends Thread{
		private PrintWriter writer;
		private User user;
		private Socket socket;
		
		public OutputThread(OutputStream out, User user, Socket socket) {
			this.user = user;
			this.socket = socket;
			this.writer = new PrintWriter(out, true);
		}
		
		public void run() {
			
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				String userName = in.readLine();
				user.setName(userName);
				writer.println(user.getName());
			
				String message;
				
				do {
					message = in.readLine();
					writer.println(message);
				}while(!message.equals("BYE"));
									
				socket.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
