/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Member;
import GUI.CalendarGUI;
import GUI.AlterMemberGUI;
import GUI.CreateMemberGUI;
import GUI.LoginGUI;
import GUI.CreateShiftGUI;
import GUI.InfoBox;
import GUI.MainGUI;
import GUI.SearchMemberGUI;
import GUI.ShiftPrefGUI;
import GUI.ShiftTypeGUI;
import Model.MemberModel;
import Model.ShiftModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Malthe Friisberg
 * Har både GUI og Model klasser som fields.
 * Styrer klasserne i GUI laget. Har indre klasse med actionperformed metode.
 * Henter data fra model laget. Konstruktor bør tage imod en Collection.
 */
public class MainController {
    
    protected MainGUI gui;
    protected MemberModel mm;
    protected CreateMemberGUI cmg;
    protected ShiftTypeGUI stgui;
    protected ShiftModel stm;
    protected ShiftPrefGUI spgui;
    protected AlterMemberGUI amgui;       
    protected SearchMemberGUI smgui;
    protected CreateShiftGUI csg;
    protected CalendarGUI calgui;
    protected InfoBox infobox;
    protected Member m;
    protected String un;
    
    
public MainController(MainGUI gui, CreateMemberGUI cmg, 
            MemberModel mm, ShiftTypeGUI stgui, ShiftModel stm, ShiftPrefGUI spgui, SearchMemberGUI smgui, CreateShiftGUI csg, CalendarGUI calgui, AlterMemberGUI amgui, String un, InfoBox infobox) {
    
        this.gui = gui;
        this.cmg = cmg;
        this.mm = mm;
        this.stgui = stgui;
        this.stm = stm;
        this.spgui = spgui;
        this.amgui = amgui;  
        this.smgui = smgui;
        this.csg = csg;
        this.calgui = calgui;
        calgui.addShiftList(stm.getShiftsx("", "", false));
        this.gui.setWelcomeLabel(mm.getMemberName(un));
        this.un = un;
        this.infobox = infobox; 
        gui.setVisible(true);
        
        this.calgui.addActionListener(new BtnListener());
        this.gui.addBtnListener(new BtnListener());
        this.cmg.addBtnListener(new BtnListener());
        this.stgui.addSaveListener(new BtnListener());
        this.stgui.addDeleteListener(new BtnListener());
        this.stgui.addStartTimeBoxListener(new BtnListener());
        this.stgui.addEndTimeBoxListener(new BtnListener());
        this.gui.addShiftPrefListener(new BtnListener());
        this.gui.addAlterMemberListener(new BtnListener());
        this.amgui.addDeleteListener(new BtnListener());
        this.amgui.addChangeInformation(new BtnListener());
        this.amgui.addSaveListener(new BtnListener());   
        this.gui.addSearchMemberListener(new BtnListener());
        this.gui.addCalListener(new BtnListener());
        this.smgui.addSearchListener(new BtnListener());
        this.gui.addCreateShiftBtn(new BtnListener());
        this.csg.addDateShiftTextFieldListener(new BtnListener());
        this.csg.addGenerateMemberBtnListener(new BtnListener());
        this.csg.addSaveBtnListener(new BtnListener());
        this.spgui.addDeleteListener(new BtnListener());
        this.spgui.addSaveListener(new BtnListener());
        this.gui.addRemoveShiftBtn(new BtnListener());
        this.gui.addRewardPointsBtn(new BtnListener());
        this.gui.addTakeShiftBtn(new BtnListener());
        this.gui.addMakeShiftBtn(new BtnListener());
        this.gui.addChangePassBtn(new BtnListener());  
        
    }   
  
    class BtnListener implements ActionListener
    {
        
        @Override
        public void actionPerformed(ActionEvent e)
        {   
        
            if(e.getSource() == gui.getCMbtn()) {
                
                if(mm.getMemberType(un) == true){
                    System.out.print("Open Create Member");
                    cmg.setVisible(true);
                }
                else 
                    infobox.InfoBox(1); 
            }
            
            if(e.getSource() == gui.getAlterMemberBtn()) {
                 
                if(mm.getMemberType(un) == true){
                    amgui.setMemberList(mm.getMemberList());
                    amgui.setVisible(true);
                }
                else 
                    infobox.InfoBox(1);       
            }
            
            if(e.getSource() == amgui.getDeleteBtn()) {
                
                mm.deleteMember(amgui.getDeleteMember());
                amgui.setMemberList(mm.getMemberList());
                amgui.repaintPane();
                amgui.showDeletedPane();
            }
            if(e.getSource() == spgui.getDeleteBtn()) {
                stm.deleteShiftPref(spgui.getDeletePrefType());
                spgui.setShiftPrefList(stm.getShiftPrefList(mm.getUserId(un)));
                spgui.repaintPane();
                
                spgui.showDeletedPane();
            }
            if(e.getSource() == amgui.getChangeInformationBtn()) {
                
                amgui.setMemberData(mm.getMemberDataList(amgui.getDeleteMember()));
                
                //amgui.setMemberList(mm.getMemberList());
                
            }
            
             
            if(e.getSource() == cmg.getConfirmBtn()) {
                  
                String firstName = cmg.getFirstName();
                String lastName = cmg.getLastName();
                String address = cmg.getAddress();
                String streetnumber = cmg.getStreetNumber();
                String city = cmg.getCity();
                String postalcode = cmg.getPostalCode();
                String email = cmg.getEmail();
                int phoneNumber = cmg.getPhoneNumber();
                String dob= cmg.getDob();
                String membertype = cmg.getMemberType();
                String memberStatus = cmg.getMemberStatus();

                System.out.println(firstName + "-" +  lastName + "-" +  address + "-" +  streetnumber + "-" +  city + "-" +  postalcode + "-" +  email + "-" +  phoneNumber + "-" +  dob + "-" +  membertype + "-" +  memberStatus);

                mm.addMember(firstName, lastName, address, streetnumber, city, postalcode, email, phoneNumber, dob, membertype, memberStatus);
                //igui.setMessageLabel(message.getcreateMemberMessage());
                cmg.setVisible(false);
            }
            
            if(e.getSource() == amgui.getSaveBtn()) {
                
                String firstName = amgui.getFirstName();
                String lastName = amgui.getLastName();
                String address = amgui.getAddress();
                String streetnumber = amgui.getStreetNumber();
                String city = amgui.getCity();
                String postalcode = amgui.getPostalCode();
                String email = amgui.getEmail();
                int phoneNumber = amgui.getPhoneNumber();
                String dob = amgui.getDob();
                String membertype = amgui.getMemberType();
                String memberStatus = amgui.getMemberStatus();
                String userId = amgui.getDeleteMember();

                //System.out.println(firstName + "-" +  lastName + "-" +  address + "-" +  streetnumber + "-" +  city + "-" +  postalcode + "-" +  email + "-" +  phoneNumber + "-" + membertype + "-" +  memberStatus);

                mm.changeMember(firstName, lastName, address, streetnumber, city, postalcode, email, phoneNumber, dob, membertype, memberStatus, userId);
                amgui.showSavePane();
            }
            
            if(e.getSource() == gui.getshiftTypeBtn()) {
                
                if(mm.getMemberType(un) == true){
                    System.out.print("Open ShiftType");             
                    stgui.setActivityList(stm.getActivityList());  
                    stgui.setVisible(true);
                }
                else 
                    infobox.InfoBox(1);
            }
            
            if(e.getSource() == gui.getCreateShiftBtn()){
                  
                if(mm.getMemberType(un) == true){
                    System.out.println("Open Create Shift"); 
                    csg.setVisible(true);
                }
                else 
                    infobox.InfoBox(1);
            }
            
            if(e.getSource() == csg.getDateShiftTextField()){
                //csg.getDate();
                csg.setShiftType(stm.getShiftType2());
                System.out.println("ifGetDate OK");
            }
            
            if(e.getSource()== csg.getGenerateMemberBtn()){
                csg.setMember(stm.getFreeMember(csg.getDate(), csg.getShiftType()));
                System.out.println("ifGetGenerateMemberBtn");
            }
  
            if(e.getSource() == csg.getSaveBtn()){
                
                String date = csg.getDate();
                int shiftType = csg.getShiftType();
                //int member = csg.getMember();
                int member = csg.getMember();
                System.out.println("ifGetSaveBtn: "+ date +", "+shiftType+", "+ member);
                stm.saveShift(date, member, shiftType );
                csg.showSavePane();
            }

            if(e.getSource() == gui.getShiftPrefBtn())
            {
                System.out.print("Open ShiftPref");
                spgui.setShiftType(stm.getShiftType());
                //System.out.println(mm.getUserId(un));
                spgui.setShiftPrefList(stm.getShiftPrefList(mm.getUserId(un)));
                
                spgui.setVisible(true);
            }
            if(e.getSource() == spgui.getSaveBtn())
            {
                stm.addShiftPref(spgui.getDate(), spgui.getComment(), mm.getUserId(un), spgui.getShiftTypeId());
                
                spgui.setShiftPrefList(stm.getShiftPrefList(mm.getUserId(un)));
                
                spgui.showSavePane();
            }
 
          
 
            if(e.getSource() == gui.getSearchUserBtn())
            {
                System.out.print("Open Search User");
                
                smgui.setMemberData(mm.getMemberSearch(smgui.getOrderBy()));
                
                smgui.setVisible(true);
            }
            
            if(e.getSource() == stgui.getSaveButton()) {
                String startTime = stgui.getStartTime();
                String endTime = stgui.getEndTime();
                String activity = stgui.getActivity();
                
                System.out.println(startTime + "-" +  endTime + "-" +  activity);
                
                stm.addShiftType(startTime, endTime, activity);
                
                stgui.setActivityList(stm.getActivityList());
                stgui.repaintPane();
                
                stgui.showSavePane();
            }
            
            if(e.getSource() == stgui.getDeleteButton()) {
                
                stm.deleteShiftType(stgui.getDeleteShiftType());
                stgui.setActivityList(stm.getActivityList());
                stgui.repaintPane();
                stgui.showDeletedPane();
      
            }
            
            if(e.getSource() == stgui.getStartTimeBox() || e.getSource() == stgui.getEndTimeBox()){
                
                //System.out.println("1234");
                
                String startTime = stgui.getStartTime();
                String endTime = stgui.getEndTime();
                
                System.out.println(startTime + "--" + endTime);
                
                System.out.println(stm.calculatePoints(startTime, endTime));
                
                stgui.setPoints(stm.calculatePoints(startTime, endTime));
                    
            }
            
            if(e.getSource() == smgui.getSearchBtn()) {
                 
                if("".equals(smgui.getSearchMemberTF())){
                smgui.setMemberData(mm.getMemberSearch(smgui.getOrderBy()));
                } 
                else if(smgui.getSearchMemberTF() != ""){
                smgui.setMemberData(mm.getMemberSearch(smgui.getSearchMemberTF(), smgui.getOrderBy()));
                }
   
            }
            
            if(e.getSource() == gui.getCalendarBtn() ) {
                System.out.print("test");
                CalendarController calcon = new CalendarController(stm, un);
                //calgui.setVisible(true);    
            }
            
            if(e.getSource() == gui.getRemoveShiftBtn()){
                infobox.InfoBox(2);
            }
            
            if(e.getSource() == gui.getRewardPointsBtn()){
                infobox.InfoBox(2);
            }
            
            if(e.getSource() == gui.getTakeShiftBtn()){
                infobox.InfoBox(2);
            }
            
            if(e.getSource() == gui.getMakeShiftBtn()){
                infobox.InfoBox(2);
            }
            
            if(e.getSource() == gui.getChangePassBtn()){
                infobox.InfoBox(2);
            }
            
        }
    }
    
}
    

