
import GUI.CalendarGUI;
import Model.CalendarQuery;
import Model.MemberModel;
import Model.ShiftModel;
import java.util.Calendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mal
 */
public class TestMain {
    public static void main(String[] args) {
        /*java.util.Calendar cal = Calendar.getInstance();
java.util.Date utilDate = new java.util.Date(); // your util date
cal.setTime(utilDate);
cal.set(Calendar.HOUR_OF_DAY, 0);
cal.set(Calendar.MINUTE, 0);
cal.set(Calendar.SECOND, 0);
cal.set(Calendar.MILLISECOND, 0);    
java.sql.Date sqlDate = new java.sql.Date(cal.getTime().getTime()); // your sql date
System.out.println("utilDate:" + utilDate);
System.out.println("sqlDate:" + sqlDate);*/
ShiftModel m = new ShiftModel();
CalendarGUI g = new CalendarGUI();
//System.out.println(m.getShiftsx("2015-01-15", "ole", false));
MemberModel mm = new MemberModel();

System.out.println(mm.getMemberType("ole"));

//CalendarQuery cq = new CalendarQuery("ole", "hej");
//cq.setUserName("ole");
//System.out.println(cq.getUserQuery("ole"));


//System.out.println(m.getShiftsDate("2015-01-15"));
//System.out.println(g.getSQLdate());
    }
    
}
