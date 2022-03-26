import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Pattern;

public class Systems {
	private static String urlRecipes = "jdbc:mysql://localhost:3306/recipe_mananger";
	private static String username = "java";
	private static String password = "password";
	private static HashMap<Long, ArrayList<Request>> requests = new HashMap<Long, ArrayList<Request>>();
	private static ArrayList<Request> recipesList = new ArrayList<Request>();
	private static final long ADMIN_ID = 3;

	public static Connection connect() {
		try {
			return DriverManager.getConnection(urlRecipes, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static long registerUser(String fName, String lName, String emailAddress, String dob, String username, String password) {
		try(Connection connection = connect()){
			System.out.println("Database connected!");
			
			String query = "SELECT emailAddress, Username FROM users WHERE emailAddress='" + emailAddress + "' OR Username='" + username + "'";
			
			if(!validateInput(fName, 0)){
				// -3 == invalid first
				
				return -3;
			}
			if(!validateInput(lName, 0)) {
				//-4 == invalid last
				
				return -4;

			}
			if(!validateInput(emailAddress, 2)) {
				//-5 == invalid email
				
				return -5;

			}
			if(!validateInput(username, 1)) {
				//-6 == invalid username
				
				return -6;

			}
			if(!validateInput(password, 1)) {
				//-7 == invalid password
				
				return -7;

			}
			
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			if(rs.next()) {
				System.out.println("Already exists");

				if(rs.getString("emailAddress").equals(emailAddress)) {
					return -1;
				}
				if(rs.getString("Username").equals(username)) {
					return -2;
				}
				
			}else {
				statement.executeUpdate("INSERT INTO users(FirstName, LastName, JoinDate, emailAddress, Username, Password, DoB) " 
						+ "VALUES ('"  + fName + "', '" + lName 
						+ "', '" + (new Date().toString()) + "', '" + emailAddress + "', '" + username + "', '" 
						+ password + "', '" + dob + "')", Statement.RETURN_GENERATED_KEYS);					
				
				
				query = "SELECT UserID FROM users WHERE Username='" + username + "'";
				
				statement = connection.createStatement();
				rs = statement.executeQuery(query);
				if(rs.next()) {
					long s = rs.getLong("UserID");
					System.out.println(s);	
					return s;
				}
			}
			
			return -1;
			
		} catch(SQLException e) {
			throw new IllegalStateException("Cannot connect", e);
		}
	}
	
	public static void addRecipe(String recipeName, long userID, String recipeInfo) {
		try(Connection connection = connect()){
			System.out.println("Database connected!");
			
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO recipes(CreationDate, LastModified, RecipeName, UserId, recipeInfo) " + "VALUES ('" + (new Date().toString()) + "', '" + (new Date().toString()) + "', '" + recipeName + "', '" + userID + "', '" + recipeInfo + "')", Statement.RETURN_GENERATED_KEYS);		
			
			Request r = new Request(userID, recipeName, recipeInfo);
			r.approved = true;
			if(requests.get(userID) == null) {
				requests.put(userID, new ArrayList<Request>());
			}
			
			requests.get(userID).add(r);
		} catch(SQLException e) {
			throw new IllegalStateException("Cannot connect", e);
		}
		
	}
	
	public static void removeRecipe(long recipeID, long userID, String recipeName, String recipeInfo) {			
		String query = "DELETE FROM recipes WHERE RecipeID = ?";

		//remove recipe from database
		try(Connection connection = connect()){
			System.out.println("Database connected!");
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, recipeID);
			statement.executeUpdate();
			
			System.out.println("done");
			requests.remove(userID, new Request(userID, recipeName, recipeInfo));
			
		} catch(SQLException e) {
			throw new IllegalStateException("Cannot connect", e);
		}
	}
	
	public static void removeRecipe(String recipeName, long userID, String recipeInfo) {			
		String query = "DELETE FROM recipes WHERE RecipeName = ?";

		//remove recipe from database
		try(Connection connection = connect()){
			System.out.println("Database connected!");
			
			System.out.println(recipeName + " is recipe name");
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, recipeName);
			statement.executeUpdate();
			
			requests.remove(userID, new Request(userID, recipeName, recipeInfo));
			
		} catch(SQLException e) {
			throw new IllegalStateException("Cannot connect", e);
		}
	}
	
	public static String[] findRecipe(long recipeID, long id) {
		try(Connection connection = connect()){
			System.out.println("Database connected!");
			
			String query = "";
			if(id == ADMIN_ID) {
				query = "SELECT recipeInfo, RecipeName, UserID FROM recipes WHERE RecipeID='" + recipeID+ "'";
			}else {
				query = "SELECT recipeInfo, RecipeName, UserID FROM recipes WHERE RecipeID='" + recipeID + "' AND UserID='" + id + "'";

			}			
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			if(rs.next()) {
				String s1 = rs.getString("recipeInfo");
				String s2 = rs.getString("RecipeName");
				String s3 = getUName(rs.getLong("UserID"));
				return new String[] {s2, s1, s3};

			}else {
				System.out.println("Doesn't exist");
				return null;
			}
			
		} catch(SQLException e) {
			throw new IllegalStateException("Cannot connect", e);
		}
	}
	
	
	public static String[] findRecipe(String recipeName, long id) {
		try(Connection connection = connect()){
			System.out.println("Database connected!");

			String query = "";
			if(id == ADMIN_ID) {//change to 0
				query = "SELECT recipeInfo, RecipeName, UserID FROM recipes WHERE RecipeName='" + recipeName + "'";
			}else {
				query = "SELECT recipeInfo, RecipeName, UserID FROM recipes WHERE RecipeName='" + recipeName + "' AND UserID='" + id + "'";

			}
			recipeName = recipeName.trim();
			System.out.println("recipe name input is " + recipeName);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			if(rs.next()) {
				String s1 = rs.getString("recipeInfo");
				String s2 = rs.getString("RecipeName");
				String s3 = getUName(rs.getLong("UserID"));

				return new String[] {s2, s1, s3};
			}else {
				System.out.println("Doesn't exist");
				return null;
			}
			
		} catch(SQLException e) {
			throw new IllegalStateException("Cannot connect", e);
		}
	}
	
	public static void editRecipe(long recipeID, long userID, String edit) {
		try(Connection connection = connect()){
			System.out.println("Database connected!");
			
			String query = "UPDATE recipes SET recipeInfo='" + edit + "' WHERE RecipeID = '" + recipeID +"'";
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			
			query = "UPDATE recipes SET LastModified='" + (new Date().toString()) + "' WHERE RecipeID = '" + recipeID + "'";
			statement = connection.createStatement();
			statement.executeUpdate(query);
			
			query = "SELECT RecipeName, recipeInfo FROM recipes WHERE RecipeID = '" + recipeID + "'";
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			String recipeName =  "";
			String recipeInfo = "";
			
			if(rs.next()) {
				recipeName = rs.getString("RecipeName");
				recipeInfo = rs.getString("recipeInfo");
			}
			
			//Request r = requests.get(userID).get(requests.get(userID).indexOf(new Request(userID, recipeName, recipeInfo)));
			System.out.println("Here");
			ArrayList<Request> re = requests.get(userID);
			System.out.println("There");

			if(re == null || re.size() == 0) return;
			
			for(Request req : re) {
				System.out.println("yee");
				if(req.getRecipeName().equals(recipeName)) {
					System.out.println("haw");
					req.setRecipeInfo(recipeInfo);
					break;
				}
				System.out.println("man");
			}		
		} catch(SQLException e) {
			throw new IllegalStateException("Cannot connect", e);
		}
	}
	
	public static void editRecipe(long userID, String recipeName, String edit) {
		try(Connection connection = connect()){
			System.out.println("Database connected!");
			
			String query = "UPDATE recipes SET recipeInfo='" + edit + "' WHERE RecipeName = '" + recipeName +"'";
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			
			query = "UPDATE recipes SET LastModified='" + (new Date().toString()) + "' WHERE RecipeName = '" + recipeName + "'";
			statement = connection.createStatement();
			statement.executeUpdate(query);
			
			query = "SELECT recipeInfo FROM recipes WHERE RecipeName = '" + recipeName + "'";
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			String recipeInfo = "";
			
			if(rs.next()) {
				recipeInfo = rs.getString("recipeInfo");
			}
			
			//Request r = requests.get(userID).get(requests.get(userID).indexOf(new Request(userID, recipeName, recipeInfo)));

			System.out.println("Here");
			ArrayList<Request> re = requests.get(userID);
			System.out.println("There");

			if(re == null || re.size() == 0) return;
			
			for(Request req : re) {
				System.out.println("yee");
				if(req.getRecipeName().equals(recipeName)) {
					System.out.println("haw");
					req.setRecipeInfo(recipeInfo);
					break;
				}
				System.out.println("man");
			}
			
		} catch(SQLException e) {
			throw new IllegalStateException("Cannot connect", e);
		}
	}
	
	public static long login(String username, String password) {
		try(Connection connection = connect()){
			System.out.println("Database connected!");
			
			String query = "SELECT UserID FROM users WHERE Username='" + username + "' AND Password='" + password + "'";
			
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			if(rs.next()) {
				long s = rs.getLong("UserID");
				System.out.println(s);	
				System.out.println("login successful");
				return s;

			}else {
				System.out.println("Incorrect login Information/user doesn't exist.");
				return -1;
			}
			
		} catch(SQLException e) {
			throw new IllegalStateException("Cannot connect", e);
		}
	}
	
	public static void removeUser(long id) {
		
		if(id == ADMIN_ID) return; 
		
		String query = "DELETE FROM users WHERE UserID = ?";

		//TODO: make sure you can't remove user 0;
		try(Connection connection = connect()){
			System.out.println("Database connected!");
			
			String query1 = "SELECT * FROM recipes WHERE UserID='" + id + "'";
			
			Statement statement1 = connection.createStatement();
			ResultSet rs = statement1.executeQuery(query1);
			while(rs.next()) {
				Long s = rs.getLong("RecipeID");
				String s1 = rs.getString("RecipeName");
				String s2 = rs.getString("recipeInfo");
				removeRecipe(s, id, s1, s2);	

			}
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			statement.executeUpdate();
			
			requests.remove(id);
			
			System.out.println("done");
		} catch(SQLException e) {
			throw new IllegalStateException("Cannot connect", e);
		}
	}
	
	public static void removeUser(String username, long id) {
		String query = "DELETE FROM users WHERE Username = ? AND UserID != ?";

		//MAYBE FIND IF USER EXISTS FIRST!!
		
		//remove recipe from database
		try(Connection connection = connect()){
			System.out.println("Database connected!");
			
			//TODO: make sure you can't remove user 0;
				
				String query1 = "SELECT * FROM recipes WHERE UserID='" + id + "'";
				
				Statement statement1 = connection.createStatement();
				ResultSet rs = statement1.executeQuery(query1);
				while(rs.next()) { //maybe if
					Long s = rs.getLong("RecipeID");
					String s1 = rs.getString("RecipeName");
					String s2 = rs.getString("recipeInfo");
					removeRecipe(s, id, s1, s2);	

				}
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			statement.setLong(2,  ADMIN_ID); //fix back to 0 later
			statement.executeUpdate();
			
			requests.remove(id);
		} catch(SQLException e) {
			throw new IllegalStateException("Cannot connect", e);
		}
	}
	
	//TODO: FIX ITTT!!
	public static String[] findUser(String username) {
		String[] r = new String[6];
		
		String query = "SELECT * FROM users WHERE Username ='" + username + "' AND UserID !='3'";

		//remove recipe from database
		try(Connection connection = connect()){
			System.out.println("Database connected!");	
			
			Statement statement1 = connection.createStatement();
			ResultSet rs = statement1.executeQuery(query);
			if(rs.next()) {
				r[0] = rs.getLong("UserID") + "";
				r[1] = rs.getString("FirstName");
				r[2] = rs.getString("LastName");
				r[3] = rs.getString("JoinDate");
				r[4] = rs.getString("emailAddress");
				r[5] = rs.getString("Username");

			}
			
			return r;
			
			
		} catch(SQLException e) {
			throw new IllegalStateException("Cannot connect", e);
		}
	}
	
	public static String[] findUser(long id) {
		String query = "SELECT * FROM users WHERE UserID = ? AND UserID != ?";

		String[] r = new String[6];

		//remove recipe from database
		try(Connection connection = connect()){
			System.out.println("Database connected!");
			
			Statement statement1 = connection.createStatement();
			ResultSet rs = statement1.executeQuery(query);
			if(rs.next()) {
				r[0] = rs.getLong("UserID") + "";
				r[1] = rs.getString("FirstName");
				r[2] = rs.getString("LastName");
				r[3] = rs.getString("JoinDate");
				r[4] = rs.getString("emailAddress");
				r[5] = rs.getString("Username");

			}else {}
			
			return r;
		} catch(SQLException e) {
			throw new IllegalStateException("Cannot connect", e);
		}
	}
	
	//TODO: add request to list and database
	public static void sendRequest(long id, String recipeName, String recipeInfo) {	
		try(Connection connection = connect()){
			System.out.println("Database connected!");
			
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO requests(RecipeName, UserID, RecipeInfo, CreationDate) " + "VALUES ('"+ recipeName + "', '" + id + "', '" + recipeInfo + "', '" + (new Date().toString()) +  "')", Statement.RETURN_GENERATED_KEYS);		
			
			Request r = new Request(id, recipeName, recipeInfo);
			if(requests.get(id) == null) {
				requests.put(id, new ArrayList<Request>());
			}
			
			requests.get(id).add(r);		
		} catch(SQLException e) {
			throw new IllegalStateException("Cannot connect", e);
		}
		
	}
	
	public static void acceptRequest(long userId, int requestNumber) {
		System.out.println("request num is " + requestNumber + " and id is " + userId + " in acceptRequest");
		System.out.println(requests.size());
		Request r = requests.get(userId).get(requestNumber);
		r.approved = true;
		String name = r.getRecipeName();
		String info = r.getRecipeInfo();
		addRecipe(name, userId, info);
				
		String query = "DELETE FROM requests WHERE RequestID = ?";

		//remove recipe from database
		try(Connection connection = connect()){
			System.out.println("Database connected!");
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, r.getRequestID());
			statement.executeUpdate();
			
			
		} catch(SQLException e) {
			throw new IllegalStateException("Cannot connect", e);
		}
	}
	
	public static void denyRequest(long userId, String recipeName, String recipeInfo) {
		ArrayList<Request> request = requests.get(userId);
		Request r = null;
		
		for(Request re : request) {
			if(re.getRecipeName().equals(recipeName) && re.getRecipeInfo().equals(recipeInfo)) {
				r = re;
				break;
			}
		}
		
		if(r == null) return;
		
		String query = "DELETE FROM requests WHERE RequestID = ?";

		//remove recipe from database
		try(Connection connection = connect()){
			System.out.println("Database connected!");
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, r.getRequestID());
			statement.executeUpdate();
			
			
		} catch(SQLException e) {
			throw new IllegalStateException("Cannot connect", e);
		}
		
		//TODO: send message to user
	}
	
	public static void initData() {
		
	}
	public static Object viewRequests(long userID) {
		
		requests = new HashMap<Long, ArrayList<Request>>();
		
		try(Connection connection = connect()){
			System.out.println("Database connected!");
							
			String query1 = "SELECT * FROM requests";
			String query2 = "SELECT * FROM recipes";

			String query3 = "SELECT * FROM requests WHERE UserID='" + userID + "'";
			String query4 = "SELECT * FROM recipes WHERE UserID='" + userID + "'";

			Statement statement1 = connection.createStatement();
				
			System.out.println("1");
			
			if(userID == ADMIN_ID) { // change to 0
				System.out.println("2");

				ResultSet rs1 = statement1.executeQuery(query1);

				System.out.println("3");

				/*if(!requests.containsKey(userID)) {
					requests.put(userID, new ArrayList<Request>());
				}*/
				
				while(rs1.next()) { //maybe if statement
					String s1 = rs1.getString("RecipeName");
					String s2 = rs1.getString("RecipeInfo");
					Long s3 = rs1.getLong("UserID");

					Long s4 = rs1.getLong("RequestID");
					
					Request r = new Request(s3, s1, s2);
					r.setRequestID(s4);
					//requests.getOrDefault(s3, new ArrayList<Request>()).add(r);
					
					if(requests.get(s3) == null) {
						requests.put(s3, new ArrayList<Request>());
					}
					
					requests.get(s3).add(r);
					System.out.println(requests.values().toString());
					System.out.println(requests.get(s3).isEmpty());
					System.out.println("4");

				}
				System.out.println("5");

				ResultSet rs2 = statement1.executeQuery(query2);

				System.out.println("6");
				while(rs2.next()) {
					String s1 = rs2.getString("RecipeName");
					String s2 = rs2.getString("RecipeInfo");
					Long s3 = rs2.getLong("UserID");

					Request r = new Request(s3, s1, s2);
					r.setRequestID(-2);
					r.approved = true;
					
					System.out.println("7");

					System.out.println(s3 + " is userID");
					System.out.println(requests.get(s3) == null);
					
					if(requests.get(s3) == null) {
						requests.put(s3, new ArrayList<Request>());
					}
					
					if(!requests.get(s3).contains(r)) {
						requests.get(s3).add(r);
					}
					System.out.println("8");

				}
				System.out.println("9");

				return requests; 
			}else {
				ResultSet rs1 = statement1.executeQuery(query3);

				while(rs1.next()) {
					String s1 = rs1.getString("RecipeName");
					String s2 = rs1.getString("RecipeInfo");
					Long s3 = rs1.getLong("RequestID");
					
					Request r = new Request(userID, s1, s2);
					r.setRequestID(s3);
					if(requests.get(userID) == null) {
						requests.put(userID, new ArrayList<Request>());
					}
					
					requests.get(userID).add(r);
				}
				ResultSet rs2 = statement1.executeQuery(query4);

				while(rs2.next()) {
					String s1 = rs2.getString("RecipeName");
					String s2 = rs2.getString("RecipeInfo");
					
					Request r = new Request(userID, s1, s2);
					r.setRequestID(-2);
					r.approved = true;

					if(requests.get(userID) == null) {
						requests.put(userID, new ArrayList<Request>());
					}
					
					if(!requests.get(userID).contains(r)) {
						requests.get(userID).add(r);
					}
								
				}
				
				return requests.get(userID);
			}
		
		} catch(SQLException e) {
			throw new IllegalStateException("Cannot connect", e);
		}
		//TODO: send message to user

	}
	
	public static ArrayList<Request> viewRecipes(long userId) {
		recipesList = new ArrayList<Request>();
		try(Connection connection = connect()){
			System.out.println("Database connected!");
							
			String query1 = "SELECT * FROM recipes";
			String query2 = "SELECT * FROM recipes WHERE UserID='" + userId + "'";

			Statement statement1 = connection.createStatement();
					
			if(userId == ADMIN_ID) { // change to 0
				ResultSet rs = statement1.executeQuery(query1);
				
				/*if(!requests.containsKey(userID)) {
					requests.put(userID, new ArrayList<Request>());
				}*/
				
				while(rs.next()) {
					
					String s1 = rs.getString("RecipeName");
					String s2 = rs.getString("RecipeInfo");
					Long s3 = rs.getLong("UserID");
					Long s4 = rs.getLong("RecipeID");
					
					Request r = new Request(s3, s1, s2);
					
					ArrayList<Request> re = requests.get(userId);
					System.out.println(re.size() + " is size");
					System.out.println("There");

					for(Request req : re) {
						System.out.println("yee");
						if(req.getRecipeName().equals(s1)) {
							System.out.println("haw");
							req.setRecipeID(s4);
							break;
						}
						System.out.println("man");
					}
					
					r.approved = true;					
					
					r.setRecipeID(s4);
					recipesList.add(r);
					
				}
				
				return recipesList; 
			}else {
				ResultSet rs = statement1.executeQuery(query2);

				while(rs.next()) {
					String s1 = rs.getString("RecipeName");
					String s2 = rs.getString("RecipeInfo");
					Long s3 = rs.getLong("RecipeID");
					
					Request r = new Request(userId, s1, s2);
					System.out.println("index is " + requests.get(userId).indexOf(r));
					
					//Request re = requests.get(userId).get(requests.get(userId).indexOf(r));
					
					System.out.println("recipe info is " + s1);
					//Request r = new Request(userId, s1, s2);

					System.out.println("Here");
					ArrayList<Request> re = requests.get(userId);
					System.out.println(re.size() + " is size");
					System.out.println("There");

					for(Request req : re) {
						System.out.println("yee");
						if(req.getRecipeName().equals(s1)) {
							System.out.println("haw");
							req.setRecipeID(s3);
							break;
						}
						System.out.println("man");
					}
					
					r.approved = true;
					r.setRecipeID(s3);
					//re.setRecipeID(s3);
					recipesList.add(r);
				}
				
				return recipesList;
			}
		
		} catch(SQLException e) {
			throw new IllegalStateException("Cannot connect", e);
		}

	}
	
	public static Set<Long> viewUsers() {
		return requests.keySet();
	}
	
	public static boolean validateInput(String text, int format) {
		boolean valid = false;
		String specialSymbols = "-'$*#%?&!";
		
		if(format == 0) { //names
			//regex
			valid = Pattern.matches("[a-zA-Z0-9]{1,25}", text);
			System.out.println(text + " matches format " + format + " is " + valid);
		}else if (format == 1) { //username && password
			valid = Pattern.matches("[a-zA-Z]{1}[a-zA-Z-0-9" + specialSymbols + "]{1,25}", text);
			System.out.println(text + " matches format " + format + " is " + valid);

		}else if(format == 2) { //email
			valid = Pattern.matches("[a-zA-Z0-9]+[@]{1}[a-zA-Z-0-9" + specialSymbols + "]+[.]{1}[a-zA-Z]+", text);
			System.out.println(text + " matches format " + format + " is " + valid);
		}
		return valid;
	}
	
	public static long getUId(String text) {
		String query1 = "SELECT UserID FROM users WHERE Username='" + text + "'";
		
		System.out.println("Text in GetUID is " + text);
		try(Connection connection = connect()){

			Statement statement1 = connection.createStatement();
			ResultSet rs = statement1.executeQuery(query1);
			if(rs.next()) {
				System.out.println("inside");
				return rs.getLong("UserID");

			}else {}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public static long getRId(String text) {
		String query1 = "SELECT RecipeID FROM recipes WHERE RecipeName='" + text + "'";
		
		try(Connection connection = connect()){

			Statement statement1 = connection.createStatement();
			ResultSet rs = statement1.executeQuery(query1);
			if(rs.next()) {
				return rs.getLong("RecipeID");

			}else {
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public static String getUName(long id) {
		String query1 = "SELECT Username FROM users WHERE UserID='" + id + "'";
		
		try(Connection connection = connect()){

			Statement statement1 = connection.createStatement();
			ResultSet rs = statement1.executeQuery(query1);
			if(rs.next()) {
				return rs.getString("Username");

			}else {}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static long getAdminID() {
		return ADMIN_ID;
	}
}
