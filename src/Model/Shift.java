/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Mal
 * "Dum wrapper klasse" brugt til at gemme info om vagter der skal vises i
 * CalendarGUI.
 */
public class Shift {
    
    protected String username;
    protected String date;
    protected String start_time;
    protected String end_time;
    
    public Shift(String username, String date, String start_time, String end_time) {
        
        this.username = username;
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
        
    }
    public String getName() {
        return this.username;
    }
    public String getDate() {
        return this.date;
    }
    public String getStartTime() {
        return this.start_time;
    }
    public String getEndTime() {
        return this.end_time;
    }
    public String toString() {
        return ""+this.username+" "+this.date+ " "+this.start_time+" "+this.end_time;
    }
    public void setName(String name) {
        
    }
    public void setDate(String date) {
        
    }
    public void setStartTime(String start_time) {
        
    }
    public void setEndTime(String end_time) {
        
    }
}
