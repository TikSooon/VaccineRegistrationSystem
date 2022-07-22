/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaccineregistrationsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Personnel extends User{
    
    public Personnel(){
        
    }
    
    public Personnel(int userID, String name, String passwd, String nric) {
        this.setUserID(userID);
        this.setName(name);
        this.setPasswd(passwd);
        this.setNric(nric);
    }
    
    @Override
    public int verifyCredentials(){
        ArrayList<Personnel> personnelList = getAllPersonnel();
        
        for(Personnel p : personnelList){
            if(p.getNric().equals(this.nric) && p.getPasswd().equals(this.passwd)){
                return p.getUserID();
            }
        }
        
        return -1;
    }
    
    public static Personnel getPersonnelbyID(int id){
         ArrayList<Personnel> personnelList = getAllPersonnel();
        for(Personnel p : personnelList){
            if(p.getUserID() == id){
                return p;
            }
        }
        
        return null;
    }
    
    private static ArrayList<Personnel> getAllPersonnel(){
        File file = new File("files/user/personnel.txt");
        ArrayList<Personnel> personnelList = new ArrayList<Personnel>();
        
        try{
            Scanner scanFile = new Scanner(file);
            while(scanFile.hasNext()){
               int tempID = Integer.parseInt(scanFile.nextLine());
               String tempName = scanFile.nextLine();
               String tempPassword = scanFile.nextLine();
               String tempIc = scanFile.nextLine();
                
               Personnel p = new Personnel(tempID,tempName,tempPassword,tempIc);
               
               personnelList.add(p);
            }
        } catch (FileNotFoundException e) {
            //user folder not created yet
        }
        return personnelList;
    }
}
