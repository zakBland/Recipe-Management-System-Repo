
public class Request {

	private long requestID = -1;
	private long userID;
	private long recipeID = -1;
	private String recipeName;
	private String recipeInfo;
	boolean approved = false;

	public Request(long userID, String recipeName, String recipeInfo) {
		this.userID = userID;
		this.recipeName = recipeName;
		this.recipeInfo = recipeInfo;
	}
	
	public long getRequestID() {
		return requestID;
	}
	
	public long getUserID() {
		return userID;
	}
	
	public String getRecipeName() {
		return recipeName;
	}
	
	public String getRecipeInfo() {
		return recipeInfo;
	}
	
	public long getRecipeID() {
		return recipeID;
	}
	
	public void setRecipeID(long id) {
		if(recipeID == -1) {
			recipeID = id;
		}
	}
	
	public void setRecipeInfo(String edit) {
		recipeInfo = edit;
	}
	
	public void setRecipeName(String name) {
		recipeName = name;
	}
	public void setRequestID(long id) {
		if(requestID == -1) {
			requestID = id;
		}
	}
}
