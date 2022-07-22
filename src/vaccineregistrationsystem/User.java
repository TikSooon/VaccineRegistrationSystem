
package vaccineregistrationsystem;

public abstract class User {
    protected int userID;
    protected String name;
    protected String passwd;
    protected String nric;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getNric() {
        return nric;
    }

    public void setNric(String nric) {
        this.nric = nric;
    }
    
    public abstract int verifyCredentials();
    
    public static boolean login(User user, boolean isPersonnel){
        int result;
        
        result =  user.verifyCredentials();
        
        if(result != -1){
            UserSession.setCurrentUserID(result);
        }
        
        return result != -1;
        
    }
    
}
