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

public class Centre {
    private int centreID;
    private String centreName;
    private String centreAddress;
    private int occupantCapacity;

    public Centre(int centreID, String centreName, String centreAddress, int occupantCapacity) {
        this.centreID = centreID;
        this.centreName = centreName;
        this.centreAddress = centreAddress;
        this.occupantCapacity = occupantCapacity;
    }

    public int getCentreID() {
        return centreID;
    }

    public void setCentreID(int centreID) {
        this.centreID = centreID;
    }

    public String getCentreName() {
        return centreName;
    }

    public String getCentreAddress() {
        return centreAddress;
    }

    public int getOccupantCapacity() {
        return occupantCapacity;
    }

    public static Centre searchByID(int id){
        ArrayList<Centre> centreList = getAllCentres();
        
        for(Centre c : centreList){
            if(c.getCentreID() == id){
                return c;
            }
        }
        
        return null;
    }
    
    public static ArrayList<Centre> getAllCentres(){
        File file = new File("files/centre/centre.txt");
        ArrayList<Centre> centreList = new ArrayList<Centre>();
        
        try{
            Scanner scanFile = new Scanner(file);
            while(scanFile.hasNext()){
               int tempID = Integer.parseInt(scanFile.nextLine());
               String tempName = scanFile.nextLine();
               String tempAddress = scanFile.nextLine();
               int tempCapacity = Integer.parseInt(scanFile.nextLine());
                 
               Centre c = new Centre(tempID,tempName,tempAddress,tempCapacity);
               
               centreList.add(c);
            }
        } catch (FileNotFoundException e) {
            //centre folder havent created
        }
        return centreList;
    }
}
