package Model.Mail;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
 
public class PrepareEmail extends EmailUtil{
 
    /**
       Outgoing Mail (SMTP) Server
       requires TLS or SSL: SMTP.gmail.com (use authentication)
       Use Authentication: Yes
       Port for SSL: 465
     */
    protected String toEmail;
    protected String pass;
    protected int mailType;
    protected String first_name;
    protected String Subject;
    protected String Body;
    
    /*
    author Anders Andersen
    Beskrivelse: PrepareEmail gør en email beskeden klar med hvad den skal indeholde, 
    og hvilken email der skal sendens fra og til.  
    Her bruges en gmail og ssl autentificering.  
    */
    public PrepareEmail(String toEmail, String pass, String first_name, int mailType) {
        final String fromEmail = "hal12system@gmail.com"; //requires valid gmail id
        final String password = "ABCD#1234"; // correct password for gmail id
        //final String toEmail = ""; // can be any email id 
        
        this.toEmail = toEmail;
        this.pass = pass;
        this.mailType = mailType;
        this.first_name = first_name;
        
        System.out.println("SSLEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
        props.put("mail.smtp.port", "465"); //SMTP Port
         
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
              
        if(this.mailType == 1){
            //Emne og besked
            Subject = "Hal 12 Medlemssystem";
            Body = "Hej " + this.first_name + ", \n\n"
                 + "Velkommen!\n"
                 + "Du er nu oprettet i Hal 12 Medlemssystem. \n"
                 + "Her er dine login oplysninger\n"
                 + "Brugernavn: " + toEmail +"\n"
                 + "Password: " + pass +"\n\n"
                 + "Mvh Hal 12:-)"; 
        }        
        if(this.mailType == 2){
            //Emne og besked
            Subject = "Mail Test";
            Body = "HEJ GRUPPE, \n\n"
                 + "Dette er en test på at sende mail vis Java. "
                 + "\n\nDet virker:-)"; 
        }
 
        Session session = Session.getDefaultInstance(props, auth);
        System.out.println("Session created");
            EmailUtil.sendEmail(session, this.toEmail, this.Subject, this.Body);        
    }
    

}
