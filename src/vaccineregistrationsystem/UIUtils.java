/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaccineregistrationsystem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class UIUtils {
    private static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");

    public static String formatDate(Date date){
        return dateFormat.format(date);
    }
    
     public static String formatTime(LocalTime time){
        return timeFormatter.format(time);
    }
     
    public static boolean isInputBlank(String input){
        return input.trim().equals("");
    }
    
    public static boolean checkPasswordValid(String passwd){
        if(passwd.trim().equals("")){
            return false;
        }
        
        if(passwd.length() < 6){
            return false;
        }
        
        return true;
    }
    
    public static boolean checkPostcodeValid(String postCode){
        if(postCode.trim().equals("")){
            return false;
        }
        
        if(postCode.length() != 5){
            return false;
        }
        
        if(!isInteger(postCode)){
            return false;
        }
        
        return true;
    }
    
    public static boolean checkPhoneNumberValid(String phoneNo){
        String pattern = "^(\\+?6?01)[0-46-9]-*[0-9]{7,8}$";
        if(phoneNo.matches(pattern)){
            return true;
        }
        return false;        
    }
    
    public static boolean isInteger(String value){
        try{
            int i = Integer.parseInt(value); 
        }catch(NumberFormatException nfe){
            return false;
        }
        return true;
    }
    
    public static boolean isToday(Date date){
         Date today = new Date();
         String todayStr = formatDate(today);
         String dateStr = formatDate(date);
         
         if(todayStr.equals(dateStr)){
             return true;
         }
         
         return false;
    }
    
    public static boolean isEqual(Date date1, Date date2){
        
         String date1Str = formatDate(date1);
         String date2Str = formatDate(date2);
         
         if(date1Str.equals(date2Str)){
             return true;
         }
         
         return false;
    }
    
    public static String encodeMultiLineText(String text){
        return text.replaceAll("[\\n]+","%20%");
    }
    
    public static String decodeMultiLineText(String text){
        return text.replaceAll("%20%","\n");
    }
    
}
