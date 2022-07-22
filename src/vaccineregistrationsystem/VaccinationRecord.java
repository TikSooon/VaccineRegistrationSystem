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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class VaccinationRecord {
    
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm:ss a").withLocale(Locale.US);
    private LocalTime injectionTime;
    private int appointmentID;
    private static final String filePath = "files/vaccinerecord/vacrecord_";
    
    public VaccinationRecord(LocalTime injectionTime, int appointmentID) {
        this.injectionTime = injectionTime;
        this.appointmentID = appointmentID;
    }
    
    public int getAppointmentID() {
        return appointmentID;
    }

    public LocalTime getInjectionTime() {
        return injectionTime;
    }

    public void setInjectionTime(LocalTime injectionTime) {
        this.injectionTime = injectionTime;
    }
    
    public static VaccinationRecord getVaccinationRecord(int appointmentID){
        String path = filePath + String.valueOf(appointmentID) +".txt";
        File file = new File(path);
        Scanner scanFile;
        try {
            scanFile = new Scanner(file);
            int tempID = Integer.parseInt(scanFile.nextLine());
            LocalTime tempTime = LocalTime.parse(scanFile.nextLine(), dtf);
            VaccinationRecord vacRecord = new VaccinationRecord(tempTime, tempID);
            
            return vacRecord;
        } catch (FileNotFoundException ex) {
            
        }
        return null;
    }
    
    
    public void addRecord(){
        try{
            String appoinmentID = String.valueOf(this.getAppointmentID());
            String path = filePath + appointmentID +".txt";
            FileWriter fw = new FileWriter(path,false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(String.valueOf(appointmentID));
            bw.newLine();
            bw.write(dtf.format(injectionTime));
            bw.newLine();
             
            bw.close();
        } catch (IOException e) {
            System.out.println("Unable to create file due to " + e);
        }
    }
}
