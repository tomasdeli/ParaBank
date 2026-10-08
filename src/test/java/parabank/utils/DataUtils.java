package parabank.utils;

public class DataUtils {
	/*** CONSTRUCTOR ***/
	private DataUtils() {
		
	}
	
	/*** METHODS ***/
	public static String resolveUsername(String username) {
        if ("AUTO".equalsIgnoreCase(username)) {
            return generateUniqueUsername();
        }

        return username;
    }
	
	public static String generateUniqueUsername() {
		return "user" + System.currentTimeMillis();
	}
}
