/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import GUI.CalendarGUI;
import GUI.AlterMemberGUI;
import GUI.CreateMemberGUI;
import GUI.CreateShiftGUI;
import GUI.InfoBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GUI.LoginGUI;
import Model.LoginModel;
import GUI.MainGUI;
import GUI.SearchMemberGUI;
import GUI.ShiftPrefGUI;
import GUI.ShiftTypeGUI;
import Model.MemberModel;
import Model.ShiftModel;
import java.util.ArrayList;


/**
 *
 * @author ande009
 */
public class LoginController
{
    //Fields
    protected LoginModel lm;
    protected LoginGUI lg;    
    
    //Constructor
    public LoginController(LoginModel lm, LoginGUI lg)
    {
        this.lm = lm;
        this.lg = lg;
        
        this.lg.addLoginListener(new LoginListener());
    }
    class LoginListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                String userName = lg.getUsername();
                String password = lg.getPassword(); 
                
                if(lm.login(userName, password)) {
                    ArrayList<Object> list = new ArrayList<>();
                    //open the new frame
                    MainGUI maingui = new MainGUI();
                    CreateMemberGUI cmg = new CreateMemberGUI();
                    MemberModel mm = new MemberModel();
                    ShiftTypeGUI stgui = new ShiftTypeGUI();
                    ShiftModel stm = new ShiftModel();
                    ShiftPrefGUI spgui = new ShiftPrefGUI();
                    AlterMemberGUI amgui = new AlterMemberGUI();
                    SearchMemberGUI smgui = new SearchMemberGUI(); 
                    CreateShiftGUI csg = new CreateShiftGUI();
                    CalendarGUI calgui = new CalendarGUI();
                    InfoBox infobox = new InfoBox();
                    stgui.setVisible(false);
                    MainController mainc = new MainController(maingui, cmg, mm, stgui, stm, spgui, smgui, csg, calgui, amgui, userName, infobox);

                    //close the login frame
                    lg.dispose();
                } else {
                    lg.setLoginLabel("Forkert Kode eller brugernavn");
                }
                
            } catch (Exception err)
            {
                System.err.println(err);
            }
        }
    }
   
    
}
