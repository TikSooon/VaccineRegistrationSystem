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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Vaccine {
    private int vaccineID;
    private String vaccineName;
    private Date expDate;
    private int balance;
    private int appointedAmt;
    
    //If centre ID is 0 means havent assigned to any centre yet
    private int centreID;
    
    private static final String filePath = "files/vaccine/vaccine.txt";
    
    private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    
    public Vaccine(){
        
    }
    
    public Vaccine(int vaccineID, String vaccineName, Date expDate, int balance, int appointedAmt, int centreID) {
        this.vaccineID = vaccineID;
        this.vaccineName = vaccineName;
        this.expDate = expDate;
        this.balance = balance;
        this.appointedAmt = appointedAmt;
        this.centreID = centreID;
    }

    public int getVaccineID() {
        return vaccineID;
    }

    public void setVaccineID(int vaccineID) {
        this.vaccineID = vaccineID;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getAppointedAmt() {
        return appointedAmt;
    }

    public void setAppointedAmt(int appointedAmt) {
        this.appointedAmt = appointedAmt;
    }

    public int getCentreID() {
        return centreID;
    }

    public void setCentreID(int centreID) {
        this.centreID = centreID;
    }

    public int getNetBalance(){
        return balance - appointedAmt;
    }
    
    public int getValidDays(){
        Date today = new Date();
        
        long diffTime = expDate.getTime() - today.getTime();
        return (int) (diffTime / (24 * 60 * 60 * 1000));  
    }
    
    
    public void addVaccine(){
        try{
            FileWriter fw = new FileWriter("files/vaccine/vaccine.txt",true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(String.valueOf(this.getVaccineID()));
            bw.newLine();
            bw.write(this.getVaccineName());
            bw.newLine();
            bw.write(format.format(this.getExpDate()));
            bw.newLine();
            bw.write(String.valueOf(this.getBalance()));
            bw.newLine();
            bw.write(String.valueOf(this.getCentreID()));
            bw.newLine();
            
            bw.close();
        } catch (IOException e) {
            System.out.println("Unable to create file due to " + e);
        }
    }
    
    public void updateVaccine(){
        ArrayList<Vaccine> vaccineList = getAllVaccines();
        clearFile();
        for(Vaccine v : vaccineList){
            if(v.getVaccineID() != vaccineID){
                v.addVaccine();
            }else{
                this.addVaccine();
            }
        }
    }
    
    
    public static boolean validDelete(int vaccineID){
        int i = Appointment.searchbyVaccineID(vaccineID).size();
        if(searchVaccine(vaccineID).getAppointedAmt() > 0 && i > 0){
            return false;
        }
        return true;
    }
    
    public static boolean deleteVaccine(int vaccineID){
        if(!validDelete(vaccineID)){
            return false;
        }
        
        ArrayList<Vaccine> vaccineList = getAllVaccines();
        clearFile();
        for(Vaccine v : vaccineList){
            if(v.getVaccineID() != vaccineID){
                v.addVaccine();
                
            }
        }
        return true;
    }
    
    private static void clearFile(){
        try{
            FileWriter fw = new FileWriter(filePath,false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("");
        }catch (IOException e) {
            System.out.println("Unable to create file due to " + e);
        }
    }
    
    public static int getNextID(){
        ArrayList<Vaccine> vaccineList = getAllVaccines();
        int maxID = 0;
        for(Vaccine v : vaccineList){
            int tempID = v.getVaccineID();
            if(tempID >= maxID){
                maxID = tempID;
            }
        }
        
        return maxID + 1;
    }
    
    public static Vaccine searchVaccine(int vaccineID){
        ArrayList<Vaccine> vaccineList = getAllVaccines();
        
        for(Vaccine v : vaccineList){
            if(v.getVaccineID() == vaccineID){
               return v;
            }
        }
        return null;
    }
    
    public static ArrayList<Vaccine> searchVaccine(String vaccineName){
        ArrayList<Vaccine> vaccineList = getAllVaccines();
        ArrayList<Vaccine> matchingVaccines = new ArrayList<Vaccine>();
        
        for(Vaccine v : vaccineList){
            if(v.getVaccineName().equals(vaccineName)){
                matchingVaccines.add(v);
            }
        }
        return matchingVaccines;
    }
        
    public static ArrayList<Vaccine> searchVaccine(Date expDate, String vaccineName){
        ArrayList<Vaccine> vaccineList = getAllVaccines();
        ArrayList<Vaccine> matchingVaccines = new ArrayList<Vaccine>();
        
        for(Vaccine v : vaccineList){
            if(v.getExpDate().before(expDate) && v.getVaccineName().equals(vaccineName)){
                matchingVaccines.add(v);
            }
        }
        return matchingVaccines;
    }
    
    public static ArrayList<Vaccine> searchVaccine(Date expDate, int centreID){
        ArrayList<Vaccine> vaccineList = getAllVaccines();
        ArrayList<Vaccine> matchingVaccines = new ArrayList<Vaccine>();
        
        for(Vaccine v : vaccineList){
            if(v.getExpDate().before(expDate) && v.getCentreID() == centreID){
                matchingVaccines.add(v);
            }
        }
        return matchingVaccines;
    }
    
     public static ArrayList<Vaccine> searchVaccinebyCentreID(int centreID){
        ArrayList<Vaccine> vaccineList = getAllVaccines();
        ArrayList<Vaccine> matchingVaccines = new ArrayList<Vaccine>();
        
        for(Vaccine v : vaccineList){
            if(v.getCentreID() == centreID){
                matchingVaccines.add(v);
            }
        }
        return matchingVaccines;
    }
     
    public static ArrayList<Vaccine> searchAvailableVaccinebyCentreID(int centreID){
        ArrayList<Vaccine> vaccineList = getAllVaccines();
        ArrayList<Vaccine> matchingVaccines = new ArrayList<Vaccine>();
        
        for(Vaccine v : vaccineList){
            if(v.getCentreID() == centreID && v.getNetBalance() > 0){
                matchingVaccines.add(v);
            }
        }
        return matchingVaccines;
    }
   
    public static ArrayList<Vaccine> getAllVaccines(){
        File file = new File("files/vaccine/vaccine.txt");
        ArrayList<Vaccine> vaccineList = new ArrayList<Vaccine>();
        
        try{
            Scanner scanFile = new Scanner(file);
            while(scanFile.hasNext()){
               int tempID = Integer.parseInt(scanFile.nextLine());
               String tempName = scanFile.nextLine();
               Date tempExpDate = format.parse(scanFile.nextLine());
               int tempBalance = Integer.parseInt(scanFile.nextLine());
               int tempAppointAmt = Appointment.getVaccineAppointedAmount(tempID);
               int centreID = Integer.parseInt(scanFile.nextLine());
               
               Vaccine v = new Vaccine(tempID,tempName,tempExpDate,tempBalance, tempAppointAmt, centreID);
               
               vaccineList.add(v);
            }
        } catch (FileNotFoundException | ParseException e) {
            //user folder not created yet
        }
        return vaccineList;
    }
    
    
}
