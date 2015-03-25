package GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.HashMap;
//import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Christine Kirk
 */
public class CreateShiftGUI extends JFrame {
    
    protected HashMap<String, Integer> memberMap;
    protected HashMap<String, Integer> shiftTypeMap;
    protected JPanel mainpanel;
    protected JComboBox shiftType;
    protected JComboBox member;
    protected JButton generateMember;
    protected JButton save;
    protected JLabel pickDate;
    protected JLabel pickTime;
    protected JLabel pickName;
    protected JFormattedTextField dateShift2;
    protected JLabel dato;
    
    public CreateShiftGUI() {
        super();
        this.memberMap = new HashMap();
        this.shiftTypeMap = new HashMap();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.mainpanel = new JPanel();
        this.setSize(350, 110);
        
        this.mainpanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        
        this.pickDate = new JLabel("Date (yyyy-mm-dd)");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.8;
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.mainpanel.add(pickDate,constraints);        
        
        try {
            MaskFormatter formatter = new MaskFormatter("####-##-##");
            this.dateShift2 = new JFormattedTextField(formatter);
        } catch (ParseException ex) {
            Logger.getLogger(CreateShiftGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipadx = 40;
        constraints.weightx = 0.8;
        constraints.gridx = 1;
        constraints.gridy = 0;
        this.mainpanel.add(dateShift2,constraints);  
        
        this.pickTime = new JLabel("Time");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.8;
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.mainpanel.add(pickTime,constraints); 
        
        this.shiftType = new JComboBox();
        shiftType.setPrototypeDisplayValue("xxxxxxxxxxx");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        //constraints.weightx = 0.8;
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        this.mainpanel.add(shiftType,constraints); 
        
        this.generateMember = new JButton("Find");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipadx = 0;
        constraints.weightx = 0.8;
        constraints.gridx = 2;
        constraints.gridy = 1;
        this.mainpanel.add(generateMember,constraints); 
        
        this.pickName = new JLabel("Member");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.8;
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.mainpanel.add(pickName,constraints); 
        
        this.member = new JComboBox();
        member.setPrototypeDisplayValue("xxxxxxxxxxx");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        //constraints.weightx = 0.8;
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.PAGE_END;
        this.mainpanel.add(member,constraints); 
        
        this.save = new JButton("Save");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.8;
        constraints.weighty = 1;
        //constraints.anchor = GridBagConstraints.LAST_LINE_END;
        constraints.gridx = 2;
        constraints.gridy = 2;
        this.mainpanel.add(save,constraints); 
        
        this.add(this.mainpanel);
    
    }
   

    /*Christine Kirk
    Sætter shifttype i combobox
    Kaldes i Maincontroller
    */
    public void setShiftType(HashMap<String, Integer> map){
        for(String time: map.keySet()){
            this.shiftType.addItem(time);
        }
        this.shiftTypeMap = map;
        System.out.println("setShiftType2: "+map);
        
    } 
    
     /*Author Christine Kirk
    Sætter members i combobox.
    Kaldes i Maincontroller
    */
    public void setMember(HashMap<String,Integer> map){
        for(String username : map.keySet()){
            member.addItem(username);
        }    
        this.memberMap = map;
        System.out.println("setMember2: "+map);
    }
    
     /*Christine Kirk
    Kaldes i Maincontroller når, der trykkes på gem-knap.
    Henter dato fra GUI*/
    public String getDate(){
        System.out.println("getDate: "+dateShift2.getText());
     
        return dateShift2.getText();
    }
     /*Author Christine Kirk
    Henter shifttype der er valgt i gui. 
    Kaldes i Maincontroller
  I*/
    public int getShiftType(){
        String time = shiftType.getSelectedItem().toString();
        int shiftTypeId = this.shiftTypeMap.get(time);
        return shiftTypeId;
    }
    
        /*Christine Kirk
    Henter member, der er valgt i gui.
    Kaldes i Maincontroller*/
    public int getMember(){
        String memberString = member.getSelectedItem().toString();
        int memberId = this.memberMap.get(memberString);
        
        System.out.println("getMember2: "+ memberString +","+ memberId);
        return memberId;
    }
    
    /*Christine Kirk
    Indgår i en if-statement i Maincontroller i ActionPerformed.
    Returnerer gem.knappen*/
    public JButton getSaveBtn(){
        return this.save;
    }
    
    /*Christine Kirk
    Aktivieres i MainController.
    Giver gem-knappen actionlistener*/
    public void addSaveBtnListener(ActionListener listenForSaveBtn){
        this.save.addActionListener(listenForSaveBtn);
    }
      public void showSavePane()
    {
        JOptionPane.showMessageDialog(null, "Shift has been saved");
    }
    
    public JComboBox getMemberBox(){
        return member;
    }
    
    public void addmemberBoxListener(ActionListener listenForBox){
        this.member.addActionListener(listenForBox);
    }
    
    public JComboBox getShiftTypeBox() {
        return shiftType;
    }
    
    public void addShiftTypeBoxListener(ActionListener listenForBox){
        this.shiftType.addActionListener(listenForBox);
    }

    public JFormattedTextField getDateShiftTextField() {
        return dateShift2;
    }
    
    public void addDateShiftTextFieldListener(ActionListener listenForTextField){
        this.dateShift2.addActionListener(listenForTextField);
    }

    public JButton getGenerateMemberBtn() {
        return generateMember;
    }
    
    public void addGenerateMemberBtnListener(ActionListener listenForBtn){
        this.generateMember.addActionListener(listenForBtn);
    }
    
     
   
    
    
    
    
    
    
    
    
}
