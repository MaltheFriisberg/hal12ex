package Model;

import Model.Mail.PrepareEmail;
import java.awt.List;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShiftModel extends DatabaseConn {
    
        private CalendarQuery cq;
    
    public ShiftModel(){
        super();
        
    }
    
    public void addShiftType(String startTime, String endTime, String activity) {
        
        final String var1 = startTime;
        final String var2 = endTime;
        final int  var3 = calculatePoints(startTime, endTime);
        final String var4 = activity;
        
        System.out.println("Ready");
        try {
            java.sql.PreparedStatement shift = conn.prepareStatement("INSERT INTO Shifttype (start_time, end_time, points, Activity)"
                    + "VALUES ('" + var1 + "', '" + var2 + "', '" + var3 + "', '" + var4 + "' )");
            System.out.println("Preparred statement ready");
            shift.executeUpdate();
            System.out.println("Updatet");
        } catch (SQLException sQLException) {
        }
        
    } 
    
    public void deleteShiftType(String stId){
          
        final String var1 = stId;
        
        try {
    		java.sql.PreparedStatement posted = conn.prepareStatement("DELETE FROM Shifttype WHERE shifttype_id =  '" + var1 + "' ");
                System.out.println("PreparedStatement created");    
    		posted.executeUpdate(); 
		System.out.println("PreparedStatement has been Executed");	
                
		} catch (SQLException e) {
			System.out.println(e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     
    } 
    /*author Christine Kirk
    gemmer vagt i database.
    Kaldes i MainController
    */
    
    public void saveShift(String date, int user, int shiftType){
       
        final int availableShiftTrue = 0;
        
        try {
            java.sql.PreparedStatement saveShift = conn.prepareStatement(" INSERT INTO Shifts (`date`, available_shift, Users_user_id, ShiftType_shifttype_id)\n" +
                    " VALUES ('"+date+"','"+availableShiftTrue+"', '"+user+"', '"+shiftType+"')");
            saveShift.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(ShiftModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*author Christine Kirk
    Henter ShiftType og id i database og loader i combobox i CreateShiftGui. 
    kaldes i Maincontroller
    */
    
    public HashMap<String, Integer> getShiftType2(){
        HashMap<String, Integer> map = new HashMap();
         String sql = "SELECT * FROM Shifttype"; 

        try {
            rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                int shifttype_id = rs.getInt("shifttype_id");
                String start_time = rs.getString("start_time");  
                String end_time = rs.getString("end_time");
                String time = start_time+"-"+end_time;
                map.put(time, shifttype_id);                
            }

        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        
        return map;
    }
    
     
    public ArrayList getShiftType(){
        
        ArrayList<String> list = new ArrayList<String>();
        String sql = "SELECT * FROM Shifttype"; 

        try {
            rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                String shifttype_id = rs.getString("shifttype_id");
                String start_time = rs.getString("start_time");  
                String end_time = rs.getString("end_time");

                list.add(shifttype_id + ", " + start_time.substring(0, 5) + "-" + end_time.substring(0, 5));             
            }

        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        System.out.println(list);
        return list;   
    }     
    
    /*
    author Anders Andersen
    Beskrivelse: Her udregnes hvor manger point en vagt giver ud fra start og slut tidspunkt på vagten. 
    */
    public int calculatePoints(String startTime, String endTime){
        
        System.out.println("calculatePoints");

        int intStartTime = Integer.parseInt(startTime.substring(0, 2));
        int intEndTime = Integer.parseInt(endTime.substring(0, 2));

        int rs = (intEndTime - intStartTime);
        System.out.println(intStartTime + "-" + intEndTime + "=" + rs);

        System.out.println(rs);

        return rs;
    } 
    
    /*
    author Anders Andersen
    Beskrivelse: retunere en liste over shiftTypes med tid, point og activity.  
    */
    public ArrayList getActivityList()
    {
        ArrayList<String> aList = new ArrayList<String>();
        String sql = "SELECT * FROM Shifttype"; 

        try {
            rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                String shifttype_id = rs.getString("shifttype_id");
                String start_time = rs.getString("start_time");  
                String end_time = rs.getString("end_time");
                String points = rs.getString("points");
                String Activity = rs.getString("Activity");
                
                //System.out.println(shifttype_id + ", " + start_time + ", " + end_time + ", " + points + ", " +  Activity);	
                
                String s = new String(shifttype_id + ", " + start_time + " - " + end_time + ", " + points + ", (" + Activity + ")");
                
                //System.out.println(s);
                aList.add(s);
            }

        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }        

        System.out.println(aList);

    return aList;	
    }
   /*author Christine Kirk og Malthe Friisberg
    viser medlemmer, der kan tage vagten i combobox i CreateShiftGui.
    Kaldes i MainController
    */
    public HashMap<String, Integer> getFreeMember(String date, int shiftType){
       
        final String var1 = date;
       final int var2 = shiftType;
 
    HashMap<String, Integer> map = new HashMap();
      try {
       
       String sql = "SELECT username, user_id from Users "
               + "WHERE username NOT IN"
               + " (SELECT username FROM Users INNER JOIN ShiftPref ON Users.user_id = ShiftPref.fk_user_id "
               + "INNER JOIN Shifttype ON  ShiftPref.fk_shifttype_id = Shifttype.shifttype_id"
               + " WHERE ShiftPref.datex = '"+var1+"'"
               + " AND ShiftPref.fk_shifttype_id = '"+var2+"')"
               + " AND memberstatus = 'aktiv'";
           
      
       rs = stmt.executeQuery(sql);
       
       while(rs.next()) {
           int user_id = rs.getInt("user_id");
           String username = rs.getString("username");
           map.put(username, user_id);
           
       }
       }catch(SQLException e) {
           System.out.print("xx");
       }
    
        return map;  
    }
    /*
    @author Christine Kirk, Malthe Friisberg
    Metoden skal bruges af controller til createShiftGUI,
    Til at vælge de frivillige der er aktive og ikke har valgt
    at de ikke kan arbejde den valgte dato i shiftPref.
    */
    
    /*public ArrayList<String> getFreeVolunteer(String date, int shiftType) {

       final String var1 = date;
       final int var2 = shiftType;
       
       ArrayList<String> x = new ArrayList();
       try {
       
       String sql = "SELECT username, user_id from Users "
               + "WHERE username NOT IN"
               + " (SELECT username FROM Users INNER JOIN ShiftPref ON Users.user_id = ShiftPref.fk_user_id "
               + "INNER JOIN Shifttype ON  ShiftPref.fk_shifttype_id = Shifttype.shifttype_id"
               + " WHERE ShiftPref.datex = '"+var1+"'"
               + " AND ShiftPref.fk_shifttype_id = '"+var2+"')"
               + " AND memberstatus = 'aktiv'";
           
      
       rs = stmt.executeQuery(sql);
       
       while(rs.next()) {
           int user_id = rs.getInt("user_id");
           String username = rs.getString("username");
           String idUsername = ""+user_id + ", "+username;
           //System.out.println(idUsername);
           x.add(idUsername);
           
       }
       }catch(SQLException e) {
           System.out.print("xx");
       }
       System.out.println("getFreeVolunteer: "+x);
       return x;    
    }*/
    /*
    @author Anders Andersen, Hjalte Dal
    Beskrivelse: addShiftPref opretter en shiftpreference i databasen.
    */
    public void addShiftPref(String date, String note, String user_id, int shiftType_id) {
        
        final String var1 = date;
        final String var2 = note;
        final int  var3 = Integer.parseInt(user_id);
        final int var4 = shiftType_id;
        
        System.out.println("Ready");
        try {
            java.sql.PreparedStatement shift = conn.prepareStatement("INSERT INTO ShiftPref "
                    + "(datex, note, fk_user_id, fk_shifttype_id)"
                    + "VALUES ('" + var1 + "', '" + var2 + "', '" + var3 + "', '" + var4 + "' )");
            System.out.println("Preparred statement ready");
            System.out.println(shift);
            shift.executeUpdate();
            System.out.println("Updatet");
        } catch (SQLException sQLException) {
        }
        
    }
    /*
    @author Anders Andersen, Hjalte Dal
    Beskrivelse: getShiftPrefList returnerer en ArrayList med shiftpreferences fra databasen.
    */
    public ArrayList getShiftPrefList(String userId)
    {
        ArrayList<String> list = new ArrayList<String>();
        final String var1 = userId;
        String sql = "SELECT * FROM test.ShiftPref INNER JOIN Shifttype WHERE ShiftPref.fk_user_id = '"+var1+"'"
                + " AND Shifttype.shifttype_id = ShiftPref.fk_shifttype_id"; 

        try {
            rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                String shiftPref_id = rs.getString("shiftPref_id");
                String datex = rs.getString("datex"); 
                String startTime = rs.getString("start_time");
                String endTime = rs.getString("end_time");
                String note = rs.getString("note");
                

                System.out.println(shiftPref_id + ", " + datex + " , " + startTime + "-" +endTime);	
                
                String s = new String(shiftPref_id + ", " + datex + " , " + startTime + "-" +endTime);
                
                //System.out.println(s);
                list.add(s);
            }

        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }        

        System.out.println(list);

    return list;	
    } 
    /*
    @author Anders Andersen, Hjalte Dal
    Beskrivelse: deleteShiftPref sletter et medlem fra databasen. Den pågældende shiftpreference defineres af 
    shiftpreferenceID.
    */
    public void deleteShiftPref(String spId){
          
        final String var1 = spId;
        
        try {
    		java.sql.PreparedStatement posted = conn.prepareStatement("DELETE FROM ShiftPref WHERE shiftPref_id =  '" + var1 + "' ");
                System.out.println("PreparedStatement created");    
    		posted.executeUpdate(); 
		System.out.println("PreparedStatement has been Executed");	
                
		} catch (SQLException e) {
			System.out.println(e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     
    }
    
    /*
    @author Malthe Friisberg
    Beskrivelse: returnerer data til CalenderGUI, denne måneds vagter
    */
    public ArrayList<Shift> getShifts() {
        
        ArrayList<Shift> list = new ArrayList<Shift>();
        try {
       String sql = "SELECT * from (\n" +
        "SELECT Shifts.shift_id, Users.username, Shifts.`date`, Shifttype.start_time, Shifttype.end_time    \n" +
        "from Shifts \n" +
        "inner join Users on\n" +
        "Users.user_id = Shifts.Users_user_id\n" +
        "inner join Shifttype " +
        "on Shifttype.shifttype_id = Shifts.Shifttype_shifttype_id\n" +
        "WHERE MONTH(Shifts.`date`) >= MONTH(current_date)\n " +
        "AND YEAR(Shifts.`date`) = YEAR(current_date)\n " +
        "GROUP BY Shifts.shift_id\n" +
        ") AS vagter ORDER BY vagter.`date` ASC";
        rs = stmt.executeQuery(sql);
            while(rs.next()) {
                String username = rs.getString("username");
                String date = rs.getString("date");
                String startTime = rs.getString("start_time");
                String endTime = rs.getString("end_time");
                Shift x = new Shift(username, date, startTime, endTime);
                list.add(x);
            }
        } catch (SQLException e) {
            System.out.print("fail : "+e);
        }
        return list;
    }
    public ArrayList<Shift> getShiftsDate(String dateChosen) {
        
        //java.sql.Date sqlDate = new java.sql.Date(dateChosen);
        
        ArrayList<Shift> list = new ArrayList<Shift>();
        try {
       String sql = "SELECT * from (\n" +
        "SELECT Shifts.shift_id, Users.username, Shifts.`date`, Shifttype.start_time, Shifttype.end_time    \n" +
        "from Shifts \n" +
        "inner join Users on\n" +
        "Users.user_id = Shifts.Users_user_id\n" +
        "inner join Shifttype " +
        "on Shifttype.shifttype_id = Shifts.Shifttype_shifttype_id\n" +
        "WHERE Shifts.`date` = '"+dateChosen+"'\n " +
        "GROUP BY Shifts.shift_id\n" +
        ") AS vagter ORDER BY vagter.`start_time` ASC";
        rs = stmt.executeQuery(sql);
            while(rs.next()) {
                String username = rs.getString("username");
                String date = rs.getString("date");
                String startTime = rs.getString("start_time");
                String endTime = rs.getString("end_time");
                Shift x = new Shift(username, date, startTime, endTime);
                list.add(x);
            }
        } catch (SQLException e) {
            System.out.print("fail : "+e);
        }
        return list;
    }
        public ArrayList<Shift> getShiftsx(String dateChosen, String userName, boolean search) {
        CalendarQuery cq = new CalendarQuery(dateChosen, userName);
        String empty = "";
        String sql = new String();
        if(dateChosen.equals(empty) && userName.equals(empty)) {
            
            sql = cq.getMonthQuery();
            
        } else if(!userName.equals(empty) && dateChosen.equals(empty)) {
            
            sql = cq.getUserQuery(userName);
            //sql = this.cq.getQuery();
        } else if(userName.equals(empty) && !dateChosen.equals(empty)) {
            
            sql = cq.getDateQuery();
        } else if(!userName.equals(empty) && !dateChosen.equals(empty)) {
            System.out.println("både dato og username");
            sql = cq.getUserDateQuery();
        } else if(search == true) {
            sql = cq.getUserQuery(userName);
        }
        ArrayList<Shift> list = new ArrayList<>();
        try {
       
        rs = stmt.executeQuery(sql);
            while(rs.next()) {
                String username = rs.getString("username");
                String date = rs.getString("date");
                String startTime = rs.getString("start_time");
                String endTime = rs.getString("end_time");
                Shift x = new Shift(username, date, startTime, endTime);
                list.add(x);
            }
        } catch (SQLException e) {
            System.out.print("fail : "+e);
        }
        return list;
    }
    
    
    
}
