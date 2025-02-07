package petstore.api.endpoints;

public class Routes {
	
	public static String base = "https://petstore.swagger.io/v2";
	
	public static String post_url = base +"/user";
	
	public static String get_url = base +"user/{username}";
	
	public static String update_url = "https://petstore.swagger.io/v2/user/{username}";
	
	public static String delete_url = "https://petstore.swagger.io/v2/user/{username}";
	
}
