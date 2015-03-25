/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author ande009
 */
public class Member {
    
    protected String username;
    protected String first_name;
    protected String last_name;
    protected String address;
    protected String street_number;
    protected String city;
    protected String postal_code;
    protected String email;
    protected String phonenumber;
    protected String dob;
    protected String membertype;
    protected String memberstatus;
      
    public Member(String username, String first_name, String last_name, String address, String street_number, String city, 
            String postal_code, String email, String phonenumber, String dob, String membertype, String memberstatus) 
    {       
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.street_number = street_number;
        this.city = city;
        this.postal_code = postal_code;
        this.email = email;
        this.phonenumber = phonenumber;
        this.dob = dob;
        this.membertype = membertype;
        this.memberstatus = memberstatus;
 
    }
    public Member(String first_name, String last_name, String address, String street_number, String city, 
            String postal_code, String email, String phonenumber, String dob, String membertype, String memberstatus) 
    {       
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.street_number = street_number;
        this.city = city;
        this.postal_code = postal_code;
        this.email = email;
        this.phonenumber = phonenumber;
        this.dob = dob;
        this.membertype = membertype;
        this.memberstatus = memberstatus;
 
    }
    
    public String getUserName() {
        return this.username;
    }
    public String getFirstName() {
        return this.first_name;
    }
    public String getLastName() {
        return this.last_name;
    }
    public String getAddress() {
        return this.address;
    }
    public String getStreetNumber() {
        return this.street_number;
    }
    public String getCity() {
        return this.city;
    }
    public String getPostalCode() {
        return this.postal_code;
    }
    public String getEmail() {
        return this.email;
    }
    public String getPhoneNumber() {
        return this.phonenumber;
    }
    public String getdob() {
        return this.dob;
    }
    public String getMemberType() {
        return this.membertype;
    }
    public String getMemberStatus() {
        return this.memberstatus;
    }
      
    public String toString() {
        return ""+this.username+" "+this.first_name+ " "+this.last_name+" "+this.address+" "+this.street_number+" "+this.city
                +" "+this.postal_code+" "+this.email+" "+this.phonenumber+" "+this.dob+" "+this.membertype+" "+this.memberstatus;
    }
}
