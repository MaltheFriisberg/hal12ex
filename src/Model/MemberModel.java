/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Mail.PrepareEmail;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MemberModel extends DatabaseConn{
    
    public MemberModel() {
        super();
    }
    
    /*
    @author Anders Andersen
    Beskrivelse: addMember bliver brugt til gemme en ny bruger i databasen.
    Når brugeren er blevet oprette sendes en mail til brugeren (PrepareEmail).
    */
    public void addMember(String first_nameData, String last_nameData, String addressData, String streetnumberData, String cityData, String postalcodeData, String emailData, int phonenumberData, String dobData, String membertypeData, String memberstatusData){
        
        final String var1 = emailData;  
    	final String var2 = generatePassword(first_nameData);	
    	final String var3 = first_nameData;
    	final String var4 = last_nameData;
    	final String var5 = addressData;
        final String var6 = streetnumberData;
        final String var7 = cityData;
        final String var8 = postalcodeData;
        final String var9 = emailData;
        final int var10 = phonenumberData;
        final String var11 = dobData;
        final String var12 = membertypeData;
        final String var13 = memberstatusData;
    	      
        System.out.println("AddMember");
        
    	try {
    		java.sql.PreparedStatement posted = conn.prepareStatement("INSERT INTO Users (username, password, first_name, last_name, address, street_number, city, postal_code, email, phonenumber, dob, membertype, memberstatus)"
                        + " VALUES ('"+var1+"', md5('"+var2+"'), '"+var3+"', '"+var4+"', '"+var5+"', '"+var6+"', '"+var7+"', '"+var8+"', '"+var9+"', '"+var10+"', '"+var11+"', '"+var12+"', '"+var13+"')");
                System.out.println("PreparedStatement created");    
    		posted.executeUpdate(); 
		System.out.println("PreparedStatement has been Executed");	
                
                PrepareEmail pe = new PrepareEmail(var1, var2, var3, 1);
                
		} catch (SQLException e) {
			System.out.println(e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
        
    }
    /*
    @author Hjalte Dal
    Beskrivelse: changeMember bliver brugt til at opdatere et medlems oplysninger i databasen.
    Medlemmet, hvis oplysninger der bliver opdateret, bestemmes af userId, som vælges i 'AlterMemberGUI'.
    */
    public void changeMember(String first_nameData, String last_nameData, String addressData, String streetnumberData, String cityData, String postalcodeData, String emailData, int phonenumberData, String dobData, String membertypeData, String memberstatusData, String userId){
        
        System.out.println("Change member started 1");
               
    	final String var1 = first_nameData;
    	final String var2 = last_nameData;
    	final String var3 = addressData;
        final String var4 = streetnumberData;
        final String var5 = cityData;
        final String var6 = postalcodeData;
        final String var7 = emailData;
        final int var8 = phonenumberData;
        final String var9 = dobData;
        final String var10 = membertypeData;
        final String var11 = memberstatusData;
        final String var12 = userId;
    	      
        System.out.println("Change member started 2");
        
    	try {
    		java.sql.PreparedStatement posted = conn.prepareStatement("UPDATE Users "
                        + "SET first_name = '"+var1+"', last_name = '"+var2+"', address = '"+var3+"', street_number = '"+var4+"', city = '"+var5+"', postal_code = '"+var6+"', email = '"+var7+"', phonenumber = '"+var8+"', dob = '"+var9+"', membertype = '"+var10+"', memberstatus = '"+var11+"'"
                        + " WHERE user_id = '"+var12+"'");
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
    @author Anders Andersen
    Beskrivelse: generatePassword laver et nyt midlertidig password til brugeren.
    passwordet består af firstname og et tal mellen 0-99.
    */
    public String generatePassword(String first_name){
        
        Random randomGenerator = new Random(); 
        final int randomNumber = randomGenerator.nextInt(99);
        final String firstname = first_name;
        
        final String password = firstname + randomNumber;
        System.out.println("Password: " + password);
        
        return password;
    }
    
    /*
    @author Anders Andersen, Hjalte Dal
    Beskrivelse: getMemberName finder brugerens fornavn og efternavn, ud fra brugerens brugernavn.
    */
    public String getMemberName(String username)
    {
        String list = null;
        
        final String var1 = username;
        
        String sql = "SELECT * FROM Users WHERE username = '" + var1 + "' "; 

        try {
            rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                String firstname = rs.getString("first_name");
                String lastname = rs.getString("last_name");              

                System.out.println(lastname + ", " +firstname);	
           
                list = (lastname + ", " + firstname);
            }

        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }

    return list;	
    }
    /*
    @author Hjalte Dal
    Beskrivelse: getUserId returnerer et userID fra databasen, som defineres af et brugernavn, på medlemmet.
    */
    public String getUserId(String username){
        String list = null;
        
        final String var1 = username;
        
        String sql = "SELECT * FROM Users WHERE username = '" + var1 + "' "; 

        try {
            rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                String userId = rs.getString("user_id");

                //System.out.println(lastname + ", " +firstname);	
           
                list = userId;
            }

        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }

    return list;
    }
    /*
    @author Hjalte Dal
    Beskrivelse: deleteMember sletter et medlem fra databasen. Det pågældende medlem defineres af userId,
    som bestemmes af brugeren i 'AlterMemberGUI'.
    */
    public void deleteMember(String memberId){
          
        final String var1 = memberId;
        
        try {
    		java.sql.PreparedStatement posted = conn.prepareStatement("DELETE FROM Users WHERE user_id =  '" + var1 + "' ");
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
    @author Hjalte Dal
    Beskrivelse: getMemberList returnerer userId, fornavn og efternavn på alle medlemmer
    i databasen.
    */
    public ArrayList getMemberList(){
        
        ArrayList<String> list = new ArrayList<String>();
        String sql = "SELECT user_id, first_name, last_name FROM Users;"; 
        try {
            rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                String firstname = rs.getString("first_name");
                String lastname = rs.getString("last_name");              
                String userId = rs.getString("user_id");

                System.out.println(lastname + ", " +firstname);	
           
                list.add(userId + ", " + lastname + ", " + firstname);
            }

        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }

    return list;
    }
    /*
    @author Anders Andersen
    Beskrivelse: getMemberSearch bruges til at søge i databasen på et søgeord, som søger i alle disse tabeller username, first_name, last_name, 
    address, street_number, city, postal_code, email, phonenumber, dob, membertype, memberstatus.
    getMemberSearch sotere også dataen efter en af disse Username, First Name, Last Name, dob, MemberType.
    */
    public ArrayList getMemberSearch(String SearchWord, String orderby)
    {
        ArrayList<Member> list = new ArrayList<Member>();
        final String var1 = SearchWord;
        String var2 = null;
        final String ob = orderby;
        
        if(ob == "Username"){
            var2 = "username";
        }
        else if(ob == "First Name"){
            var2 = "first_name";
        }
        else if(ob == "Last Name"){
            var2 = "last_name";
        }
        else if(ob == "dob"){
            var2 = "dob";
        }
        else if(ob == "City"){
            var2 = "city";
        }
        else if(ob == "Membertype"){
            var2 = "membertype";
        }
         
        System.out.println(var1);
        System.out.println(var2);
        
        String sql = "SELECT username, first_name, last_name, address, street_number, city, postal_code, email, phonenumber, dob, membertype, memberstatus FROM test.Users \n" +
            "WHERE (username = '" + var1 + "')\n" +
            "OR (first_name = '" + var1 + "')\n" +
            "OR (last_name = '" + var1 + "')\n" +
            "OR (address = '" + var1 + "')\n" +
            "OR (street_number = '" + var1 + "')\n" +
            "OR (city = '" + var1 + "')\n" +
            "OR (postal_code = '" + var1 + "')\n" +
            "OR (email = '" + var1 + "')\n" +
            "OR (phonenumber = '" + var1 + "')\n" +
            "OR (dob = '" + var1 + "')\n" +
            "OR (membertype = '" + var1 + "')\n" +
            "OR (memberstatus = '" + var1 + "') \n" +
            "ORDER BY Users." + var2 + ""; 

        try {
            rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                String username = rs.getString("username");
                String first_name = rs.getString("first_name"); 
                String last_name = rs.getString("last_name");
                String address = rs.getString("address");
                String street_number = rs.getString("street_number");
                String city = rs.getString("city");
                String postal_code = rs.getString("postal_code"); 
                String email = rs.getString("email");
                String phonenumber = rs.getString("phonenumber");
                String dob = rs.getString("dob");
                String membertype = rs.getString("membertype");
                String memberstatus = rs.getString("memberstatus");
                
                //System.out.println(username + ", " + first_name + ", " + last_name + ", " + address + ", " + street_number + ", " + city + ", " + postal_code + ", " + email + ", " + phonenumber + ", " + dob + ", " + membertype + ", " + memberstatus);	
                
                Member x = new Member(username, first_name, last_name, address, street_number, city, postal_code, email, phonenumber, dob, membertype, memberstatus);

                list.add(x);
            }

        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
          
        return list;	
    }
    
    /*
    @author Anders Andersen
    Beskrivelse: getMemberSearch bruges til at søge i databasen efter alle brugere i disse tabeller username, first_name, last_name, 
    address, street_number, city, postal_code, email, phonenumber, dob, membertype, memberstatus.
    getMemberSearch sotere også dataen efter en af disse Username, First Name, Last Name, dob, MemberType.
    */
    public ArrayList getMemberSearch(String orderby)
    {
        ArrayList<Member> list = new ArrayList<Member>();
        String var1 = null;
        final String ob = orderby;
        
        if(ob == "Username"){
            var1 = "username";
        }
        else if(ob == "First Name"){
            var1 = "first_name";
        }
        else if(ob == "Last Name"){
            var1 = "last_name";
        }
        else if(ob == "dob"){
            var1 = "dob";
        }
        else if(ob == "City"){
            var1 = "city";
        }
        else if(ob == "Membertype"){
            var1 = "membertype";
        }
         
        String sql = "SELECT username, first_name, last_name, address, street_number, city, postal_code, email, phonenumber, dob, membertype, memberstatus FROM test.Users \n" +
            "ORDER BY Users." + var1 + ""; 

        try {
            rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                String username = rs.getString("username");
                String first_name = rs.getString("first_name"); 
                String last_name = rs.getString("last_name");
                String address = rs.getString("address");
                String street_number = rs.getString("street_number");
                String city = rs.getString("city");
                String postal_code = rs.getString("postal_code"); 
                String email = rs.getString("email");
                String phonenumber = rs.getString("phonenumber");
                String dob = rs.getString("dob");
                String membertype = rs.getString("membertype");
                String memberstatus = rs.getString("memberstatus");
                
                //System.out.println(username + ", " + first_name + ", " + last_name + ", " + address + ", " + street_number + ", " + city + ", " + postal_code + ", " + email + ", " + phonenumber + ", " + dob + ", " + membertype + ", " + memberstatus);	
                
                Member x = new Member(username, first_name, last_name, address, street_number, city, postal_code, email, phonenumber, dob, membertype, memberstatus);

                list.add(x);

            }

        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }

        System.out.println(list);
        return list;
       
    }
    /*
    @author Hjalte Dal
    Beskrivelse: getMemberDataList returnerer en ArrayList med oplysninger på et medlem, som er defineret af userId.
    */
    public ArrayList getMemberDataList(String userId)
    {
        ArrayList<Member> list = new ArrayList<Member>();
        final String var1 = userId;
        String sql = "SELECT * FROM Users WHERE user_id = '"+var1+"'"; 
        try {
            rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                String first_name = rs.getString("first_name"); 
                String last_name = rs.getString("last_name");
                String address = rs.getString("address");
                String street_number = rs.getString("street_number");
                String city = rs.getString("city");
                String postal_code = rs.getString("postal_code"); 
                String email = rs.getString("email");
                String phonenumber = rs.getString("phonenumber");
                String dob = rs.getString("dob");
                String membertype = rs.getString("membertype");
                String memberstatus = rs.getString("memberstatus");

                Member x = new Member(first_name, last_name, address, street_number, city, postal_code, email, phonenumber, dob, membertype, memberstatus);
           
                list.add(x);
            }

        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }

    return list;
    }
    
    public Boolean getMemberType(String username){
        String memberType = null;
        Boolean admin = false;
        String sql = "SELECT membertype FROM Users WHERE username = '" + username + "' "; 
        try {
            rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                memberType = rs.getString("memberType");
                //System.out.println(memberType);	
           
            }

        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        
        if("Administrator".equals(memberType))
            admin = true;
        System.out.println(admin);
        
    return admin;    
    } 
          
        	
    

}
