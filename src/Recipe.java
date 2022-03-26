
public class Recipe {
	String recipeName;
	long userId;
	String recipeInfo;
	boolean approved = false;
	
	Recipe(String recipeName, long userId, String recipeInfo){
		this.recipeName = recipeName;
		this.userId = userId;
		this.recipeInfo = recipeInfo;
	}
}
