package user;

// A Singleton class which makes loggedin user object available
// throughout the project
public class LoggedInUser {
       public  static LoggedInUser loggedInUser = null ;
       private String userName;
       private LoggedInUser(String userName) {
    	   this.userName = userName;
       }
       
       public static LoggedInUser getInstance(String userName) {
    	   if(loggedInUser == null) {
    		   loggedInUser = new LoggedInUser(userName);
    	   }
		return loggedInUser;
       }
       
       public static LoggedInUser getInstance() {
    	   
		return loggedInUser;
       }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
