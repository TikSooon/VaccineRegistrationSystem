/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaccineregistrationsystem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class People extends User{
    private boolean citizenship;
    private String address;
    private int postCode;
    private String phoneNo;
    
    private static final String filePath = "files/user/people.txt";
    
    public People(){
        
    }

    public People(int userID, String name, String passwd, String nric, boolean citizenship, String address, int postCode, String phoneNo) {
        this.setUserID(userID);
        this.setName(name);
        this.setPasswd(passwd);
        this.setNric(nric);
        this.citizenship = citizenship;
        this.address = address;
        this.postCode = postCode;
        this.phoneNo = phoneNo;
    }

    public boolean isCitizenship() {
        return citizenship;
    }

    public void setCitizenship(boolean citizenship) {
        this.citizenship = citizenship;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    
    
    
    public void update(){
        ArrayList<People> peopleList = getAllPeople();
        clearFile();
        
        for(People p : peopleList){
            if(p.getUserID() != this.userID){
                p.register();
            }else{
                this.register();
            }
        }       
    }
    
    private void clearFile(){
        try{
            FileWriter fw = new FileWriter(filePath,false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("");
        }catch (IOException e) {
            System.out.println("Unable to create file due to " + e);
        }
    }
    
    public void register(){
        try{
            FileWriter fw = new FileWriter(filePath,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(String.valueOf(this.getUserID()));
            bw.newLine();
            bw.write(this.getName());
            bw.newLine();
            bw.write(this.getPasswd());
            bw.newLine();
            bw.write(this.getNric());
            bw.newLine();
            bw.write(String.valueOf(this.isCitizenship()));
            bw.newLine();
            bw.write(this.getAddress());
            bw.newLine();
            bw.write(String.valueOf(this.getPostCode()));
            bw.newLine();
            bw.write(this.getPhoneNo());
            bw.newLine();    
             
            bw.close();
        } catch (IOException e) {
            System.out.println("Unable to create file due to " + e);
        }
    }
    
    public static int getNextID(){
        ArrayList<People> peopleList = getAllPeople();
        int maxId = 0;
        for(People p : peopleList){
            int tempId = p.getUserID();
            if(tempId >= maxId){
                maxId = tempId;
            }
        }
        
        return maxId + 1;
    }
    
    //For validation during create
    public static boolean checkIfICExisted(String ic){
        ArrayList<People> peopleList = getAllPeople();
        
        for(People p : peopleList){
            if(p.getNric().equals(ic)){
               return true;
            }
        }
        
        return false;
    }
    
    //For validation during update
    public static boolean checkIfICExisted(String ic, int peopleID){
        ArrayList<People> peopleList = getAllPeople();
        
        for(People p : peopleList){
            if(p.getNric().equals(ic) && p.getUserID() != peopleID){
               return true;
            }
        }
        
        return false;
    }
    
    @Override
    public int verifyCredentials(){
        ArrayList<People> peopleList = getAllPeople();
        for(People p: peopleList){
            if(p.getNric().equals(this.nric) && p.getPasswd().equals(this.passwd)){
                return p.getUserID();
            }
        }
        
        return -1;
    }
    
    public static ArrayList<People> searchPeopleByIC(String nric){
        ArrayList<People> peopleList = getAllPeople();
        ArrayList<People> matchingPeople = new ArrayList<People>();
        
        for(People p : peopleList){
            if(p.getNric().contains(nric)){
                matchingPeople.add(p);
            }
        }
        
        return matchingPeople;
    }
    
     public static People getPeopleByIC(String nric){
        ArrayList<People> peopleList = getAllPeople();
        
        for(People p : peopleList){
            if(p.getNric().equals(nric)){
                return p;
            }
        }
        
        return null;
    }
    
    public static People getPeopleByID(int id){
        ArrayList<People> peopleList = getAllPeople();
        for(People p : peopleList){
            if(p.getUserID() == id){
                return p;
            }
        }
        
        return null;
    }
    
    public static ArrayList<People> getPeopleAvailableForVaccine(){
        ArrayList<People> peopleList = getAllPeople();
        ArrayList<People> availablePeople = new ArrayList<People>();
        
        for(People p : peopleList){
            boolean r = Appointment.getPeopleVaccinationAvailabilty(p.getUserID());
            if(r){
                availablePeople.add(p);
            }
        }
        
        return availablePeople;
    }
        
    public static ArrayList<People> getAllPeople(){
        File file = new File(filePath);
        ArrayList<People> peopleList = new ArrayList<People>();
        
        try{
            Scanner scanFile = new Scanner(file);
            while(scanFile.hasNext()){
               int tempID = Integer.parseInt(scanFile.nextLine());
               String tempName = scanFile.nextLine();
               String tempPassword = scanFile.nextLine();
               String tempIc = scanFile.nextLine();
               
               boolean tempCitizenship  =  Boolean.parseBoolean(scanFile.nextLine());
               String tempAddress = scanFile.nextLine(); 
               int tempPostcode =  Integer.parseInt(scanFile.nextLine());
               String tempPhoneNo =  scanFile.nextLine();
               
               People p = new People(tempID,tempName,tempPassword,tempIc,tempCitizenship,tempAddress,tempPostcode,tempPhoneNo);
               
               peopleList.add(p);
            }
        } catch (FileNotFoundException e) {
            //user folder not created yet
        }
        return peopleList;
    }
    
}
