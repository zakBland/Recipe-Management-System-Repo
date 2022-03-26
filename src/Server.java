import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Server {

	private static int port;
	private static String urlRecipes = "jdbc:mysql://localhost:3306/recipe_mananger";
	private static String username = "java";
	private static String password = "password";
	private static final long ADMIN_ID = 3;
	

	public Server() {
	}
	
	public static void main(String[] args) {

		Server server = new Server();
		System.out.println("inside");
		server.connectSocket();

	}
	
/*	public static Connection connectDatabase() {
		try {
			return DriverManager.getConnection(urlRecipes, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	} */
	
	public void connectSocket() {
		try(ServerSocket serverSocket = new ServerSocket(port)){
			port = serverSocket.getLocalPort();
			System.out.println("The chat server is running on port " + port);
			
			while(true) {
				Socket socket = serverSocket.accept();
				System.out.println("New client...");
				
				ClientThread user = new ClientThread(socket, this);
				user.start();
			}
		} catch(BindException e) {}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static int getPort() {
		return port;
	}
	
	private class ClientThread extends Thread{
		private Socket socket;
		private Server server;
		private PrintWriter writer;
		private String name;
		
		private ClientThread(Socket socket, Server server) {
			this.socket = socket;
			this.server = server;
		}
		
		public void run() {
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				writer = new PrintWriter(socket.getOutputStream(), true);
				
				this.name = reader.readLine();
				
				//server.broadcast("Chatter: " + name + " has joined the discussion.", this);
			
				String message;
				
				do {
					message = reader.readLine();

					//server.broadcast(name + ": " + message, this);
				}while(!message.equals("BYE"));
				
				//server.broadcast("Chatter: " + name + " has left the discussion", this);

				//server.remove(this);				
				socket.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		private void send(String message) {
				writer.println(message);
		}
	}
}
