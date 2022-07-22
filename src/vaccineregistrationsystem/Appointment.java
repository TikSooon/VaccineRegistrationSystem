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

/**
 *
 * @author DELL
 */
public class Appointment {
    private int appointmentID;
    private int peopleID;
    private int personnelID;
    private Date date;
    private String time;
    private String status;
    private int vaccineID;
    private int centreID;
    
    private VaccinationRecord vacRecord;
    
    private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    
    private static final String filePath = "files/appointment/appointment.txt";
    
    public Appointment(){
        
    }

    public Appointment(int appointmentID, int peopleID, int personnelID, Date date, String time, String status, int vaccineID, int centreID) {
        this.appointmentID = appointmentID;
        this.peopleID = peopleID;
        this.personnelID = personnelID;
        this.date = date;
        this.time = time;
        this.status = status;
        this.vaccineID = vaccineID;
        this.centreID = centreID;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public int getPeopleID() {
        return peopleID;
    }

    public void setPeopleID(int peopleID) {
        this.peopleID = peopleID;
    }

    public int getPersonnelID() {
        return personnelID;
    }

    public void setPersonnelID(int personnelID) {
        this.personnelID = personnelID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getVaccineID() {
        return vaccineID;
    }

    public void setVaccineID(int vaccineID) {
        this.vaccineID = vaccineID;
    }

    public int getCentreID() {
        return centreID;
    }

    public void setCentreID(int centreID) {
        this.centreID = centreID;
    }

    public VaccinationRecord getVacRecord() {
        return vacRecord;
    }

    public void setVacRecord(VaccinationRecord vacRecord) {
        this.vacRecord = vacRecord;
    }

    public void updateAppointment(){
        ArrayList<Appointment> appointmentList = getAllAppointments();
        clearFile();
        for(Appointment a : appointmentList){
            if(a.getAppointmentID() != this.getAppointmentID()){
                a.addAppointment();
            }else{
                this.addAppointment();
            }
        }    
    }
    
    public static boolean deleteAppointment(int appointmentID){   
        if(appointmentIsComplete(appointmentID)){
            return false;
        }
        
        ArrayList<Appointment> appointmentList = getAllAppointments();
        clearFile();
        for(Appointment a : appointmentList){
            if(a.getAppointmentID() != appointmentID){
                a.addAppointment();
            }
        } 
        
        return true;
    }
    
    private static boolean appointmentIsComplete(int appointmentID){
        Appointment app = searchbyAppointmentID(appointmentID);
        
        if(app.getStatus().equals("Completed")){
            return true;
        }
        
        return false;
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
    
    public void addAppointment(){
        try{
            FileWriter fw = new FileWriter(filePath,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(String.valueOf(this.getAppointmentID()));
            bw.newLine();
            bw.write(String.valueOf(this.getPeopleID()));
            bw.newLine();
            bw.write(String.valueOf(this.getPersonnelID()));
            bw.newLine();
            bw.write(format.format(this.getDate()));
            bw.newLine();
            bw.write(this.getTime());
            bw.newLine();
            bw.write(this.getStatus());
            bw.newLine();
            bw.write(String.valueOf(this.getVaccineID()));
            bw.newLine();
            bw.write(String.valueOf(this.getCentreID()));
            bw.newLine(); 
            
            if(vacRecord != null){
                vacRecord.addRecord();
            }
             
            bw.close();
        } catch (IOException e) {
            System.out.println("Unable to create file due to " + e);
        }
    }
    
    public static int getNextID(){
        ArrayList<Appointment> appointmentList = getAllAppointments();
        int maxID = 0;
        for(Appointment a : appointmentList){
            int tempID = a.getAppointmentID();
            if(tempID >= maxID){
                maxID = tempID;
            }
        }
        
        return maxID + 1;
    }
    
    public static ArrayList<Appointment> searchbyCentreID(int centreID){
        ArrayList<Appointment> appointmentList = getAllAppointments();
        ArrayList<Appointment> matchedAppointments = new ArrayList<Appointment>();
        
        for(Appointment a : appointmentList){
            if(a.getCentreID() == centreID){
                matchedAppointments.add(a);
            }
        }
        
        return matchedAppointments;
    }
    
    public static ArrayList<Appointment> searchbyVaccineID(int vaccineID){
        ArrayList<Appointment> appointmentList = getAllAppointments();
        ArrayList<Appointment> matchedAppointments = new ArrayList<Appointment>();
        
        for(Appointment a : appointmentList){
            if(a.getVaccineID()== vaccineID){
                matchedAppointments.add(a);
            }
        }
        
        return matchedAppointments;
    }
    
    public static int getVaccineAppointedAmount(int vaccineID){
        ArrayList<Appointment> appointmentList = getAllAppointments();
        ArrayList<Appointment> matchedAppointments = new ArrayList<Appointment>();
        
        for(Appointment a : appointmentList){
            if(a.getVaccineID()== vaccineID && a.getStatus().equals("Pending")){
                matchedAppointments.add(a);
            }
        }
        
        return matchedAppointments.size();
    }
    
    public static ArrayList<Appointment> searchbyCentreDateTime(int centreID, Date date, String time){
        ArrayList<Appointment> appointmentList = getAllAppointments();
        ArrayList<Appointment> matchedAppointments = new ArrayList<Appointment>();
        
        for(Appointment a : appointmentList){
            String appointmentDate = format.format(a.getDate());
            String inputDate = format.format(date);
            if(a.getCentreID() == centreID && appointmentDate.equals(inputDate) && a.getTime().equals(time)){
                matchedAppointments.add(a);
            }
        }
        
        return matchedAppointments;
    }
    
    public static ArrayList<Appointment> searchbyPeoplePartialICNo(String nric){
        ArrayList<Appointment> appointmentList = getAllAppointments();
        ArrayList<Appointment> matchedAppointments = new ArrayList<Appointment>();
        ArrayList<People> peopleList = People.searchPeopleByIC(nric);
        
        if(peopleList.isEmpty()){
           return matchedAppointments;
        }
        
        for(Appointment a : appointmentList){
            for(People p : peopleList){
                if(a.getPeopleID() == p.getUserID()){
                    matchedAppointments.add(a);
                } 
            }
            
        }
        
        
        return matchedAppointments;
    }
    
    public static ArrayList<Appointment> searchbyPeopleFullICNo(String nric){
        ArrayList<Appointment> appointmentList = getAllAppointments();
        ArrayList<Appointment> matchedAppointments = new ArrayList<Appointment>();
        People p = People.getPeopleByIC(nric);
        
        if(p == null){
           return matchedAppointments;
        }
        
        for(Appointment a : appointmentList){
            if(a.getPeopleID() == p.getUserID()){
                matchedAppointments.add(a);
            } 
        }
        
        
        return matchedAppointments;
    }
    
    public static boolean getPeopleVaccinationAvailabilty(int peopleID){
        ArrayList<Appointment> appointmentList = getAllAppointments();
        
        int noOfPending = 0;
        int noOfCompleted = 0;
            
        for(Appointment a : appointmentList){
            if(a.getPeopleID() == peopleID){
                if(a.getStatus().equals("Pending")){
                    return false;
                    
                }else if(a.getStatus().equals("Completed")){
                    noOfCompleted++;
                    
                    if(noOfCompleted >= 2){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public static ArrayList<Appointment> searchbyPeopleICDate(String nric, Date date){
        ArrayList<Appointment> appointmentList = getAllAppointments();
        ArrayList<Appointment> matchedAppointments = new ArrayList<Appointment>();
        People people = People.getPeopleByIC(nric);
        
        if(people == null){
           return matchedAppointments;
        }
        
        for(Appointment a : appointmentList){
            String appointmentDate = format.format(a.getDate());
            String inputDate = format.format(date);
            if(a.getPeopleID() == people.getUserID() && appointmentDate.equals(inputDate)){
                matchedAppointments.add(a);
            }
        }
        return matchedAppointments;
    }
    
    public static Appointment searchbyAppointmentID(int appointmentID){
        ArrayList<Appointment> appointmentList = getAllAppointments();
       
        for(Appointment a : appointmentList){
            if(a.getAppointmentID() == appointmentID){
                return a;
            }
        }
        return null;
    }
    
    public static int getOccupantsAmount(int centreID, Date date, String time){
          ArrayList<Appointment> matchingAppointment = searchbyCentreDateTime(centreID, date, time);
 
        return matchingAppointment.size();
    }
    
    public static String getVaccinationStatus(String nric){
        ArrayList<Appointment> matchingAppointment = searchbyPeopleFullICNo(nric);
        int n = getNoOfCompletedAppointment(matchingAppointment);
        if(n == 0){
            return "Not Vaccinated";
        }else if(n == 2){
            Date today = new Date();
            long diffTime =  today.getTime() - getLatestInjectedDate(matchingAppointment).getTime();
            int diffDay = (int) (diffTime / (24 * 60 * 60 * 1000));
            
            if(diffDay >= 14){
                return "Fully Vaccinated";
            }
        }
       
        return "Partially Vaccinated";
    }
    
    private static int getNoOfCompletedAppointment(ArrayList<Appointment> appointmentList){
        int n = 0;
        for(Appointment a : appointmentList){
            if(a.getStatus().equals("Completed")){
               n++;
            }
        }
         
        return n;
    }
    
    private static Date getLatestInjectedDate(ArrayList<Appointment> appointmentList){
        Date latestDate = new Date();
        for(Appointment a : appointmentList){
            if(a.getStatus().equals("Completed")){
                latestDate = a.getDate();    
            }
        }
        
        for(Appointment a : appointmentList){
            if(a.getStatus().equals("Completed")){
                if(a.getDate().after(latestDate)){
                    latestDate = a.getDate();
                }
            }
        }
        
        return latestDate;
    }
    
    public static ArrayList<Appointment> getAllAppointments(){
        File file = new File(filePath);
        ArrayList<Appointment> appointmentList = new ArrayList<Appointment>();
        
        try{
            Scanner scanFile = new Scanner(file);
            while(scanFile.hasNext()){
               int tempID = Integer.parseInt(scanFile.nextLine());
               int tempPeopleID = Integer.parseInt(scanFile.nextLine());
               int tempPersonnelID = Integer.parseInt(scanFile.nextLine());
               Date tempDate = format.parse(scanFile.nextLine());
               
               String tempTime  =  scanFile.nextLine();
               String tempStatus = scanFile.nextLine(); 
               int tempVaccineID =  Integer.parseInt(scanFile.nextLine());
               int tempCentreID =  Integer.parseInt(scanFile.nextLine());
               
               Appointment a = new Appointment(tempID,tempPeopleID,tempPersonnelID,tempDate,tempTime,tempStatus,tempVaccineID,tempCentreID);
               
               if(tempStatus.equals("Completed")){
                   VaccinationRecord vacRecord = VaccinationRecord.getVaccinationRecord(tempID);
                   a.setVacRecord(vacRecord);
               }
               
               appointmentList.add(a);
            }
        } catch (FileNotFoundException | ParseException e) {
            //Appointment folder not created yet
        }
        return appointmentList;
    }
    
}
