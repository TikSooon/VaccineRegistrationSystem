/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaccineregistrationsystem;


public class UserSession {
    private static int userID;
    private static String userName;
    
    public static void setCurrentUserID(int loginUserID){
        userID = loginUserID;
    }
    
    public static void setCurrentUsername(String loginUsername){
        userName = loginUsername;
    }
    
    public static int getCurrentUserID(){
        return userID;
    }
    
    public static String getCurrentUsername(){
        return userName;
    }
    
    public static void destorySession(){
        userID = -1;
    }
}
