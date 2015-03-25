/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Mal
 */
public class CalendarQuery {
    private String sqlQueryMonth;
    private String sqlQueryDate;
    private String sqlQueryUser;
    private String userName;
    private String dateChosen;
    private String userDateQuery;
    private String userSearch;
    
    public CalendarQuery(String dateChosen, String userName) {
        
       
        
        
            //Hele måneden
            this.sqlQueryMonth = 
                    "SELECT * from (\n" +
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
                
            //Specifik dato(alle brugere)
            this.sqlQueryDate = 
                    "SELECT * from (\n" +
        "SELECT Shifts.shift_id, Users.username, Shifts.`date`, Shifttype.start_time, Shifttype.end_time    \n" +
        "from Shifts \n" +
        "inner join Users on\n" +
        "Users.user_id = Shifts.Users_user_id\n" +
        "inner join Shifttype " +
        "on Shifttype.shifttype_id = Shifts.Shifttype_shifttype_id\n" +
        "WHERE Shifts.`date` = '"+dateChosen+"'\n " +
        "GROUP BY Shifts.shift_id\n" +
        ") AS vagter ORDER BY vagter.`start_time` ASC";
                
            //Kun brugerens egne vagter
            this.sqlQueryUser = 
                "SELECT * from (\n" +
	"SELECT Shifts.shift_id, Users.username, Shifts.`date`, Shifttype.start_time, Shifttype.end_time \n" +   
        "from Shifts \n" +
        "inner join Users on \n" +
        "Users.user_id = Shifts.Users_user_id \n" +
        "inner join Shifttype on \n" +
        "Shifttype.shifttype_id = Shifts.Shifttype_shifttype_id \n" +
        "WHERE MONTH(Shifts.`date`) >= MONTH(current_date) \n" +
		"AND YEAR(Shifts.`date`) = YEAR(current_date) \n" +
		"AND Users.username = '"+userName+"' \n" +
        "GROUP BY Shifts.shift_id \n" +
        ") AS vagter ORDER BY vagter.`date` ASC";
            
            //Brugerens vagter på specifik dato
            this.userDateQuery = "SELECT * from (\n" +
	"SELECT Shifts.shift_id, Users.username, Shifts.`date`, Shifttype.start_time, Shifttype.end_time \n" +   
        "from Shifts \n" +
        "inner join Users on \n" +
        "Users.user_id = Shifts.Users_user_id \n" +
        "inner join Shifttype \n" +
        "WHERE Shifts.`date` = '"+dateChosen+"' \n" +
		
		"AND Users.username = '"+userName+"' \n" +
        "GROUP BY Shifts.shift_id \n" +
        ") AS vagter ORDER BY vagter.`date` ASC";
            this.userSearch =
            "SELECT * from (\n" +
	"SELECT Shifts.shift_id, Users.username, Shifts.`date`, Shifttype.start_time, Shifttype.end_time \n" +   
        "from Shifts \n" +
        "inner join Users on \n" +
        "Users.user_id = Shifts.Users_user_id \n" +
        "inner join Shifttype \n" +
        "WHERE MONTH(Shifts.`date`) >= MONTH(current_date) \n" +
		"AND YEAR(Shifts.`date`) = YEAR(current_date) \n" +
		"AND Users.username LIKE '%"+userName+"%' \n" +
        "GROUP BY Shifts.shift_id \n" +
        ") AS vagter ORDER BY vagter.`date` ASC";
        } 
        
    public void setUserName(String userName) {
        this.userName = userName;
    } 
    public void setDateChosen(String date) {
        this.dateChosen = date;
    }
    public String getUserQuery(String userName) {
        this.userName = userName;
        return this.sqlQueryUser;
    }
    public String getUserSearch() {
        return this.userSearch;
    }
    public String getMonthQuery() {
        return this.sqlQueryMonth;
    }
    public String getDateQuery() {
        return this.sqlQueryDate;
    }
    public String getUserDateQuery() {
        return this.userDateQuery;
    }
}
    

