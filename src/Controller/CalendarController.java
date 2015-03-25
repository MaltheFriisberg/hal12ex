/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import GUI.CalendarGUI;
import GUI.CalendarGUIx;
import Model.MemberModel;
import Model.ShiftModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author Mal
 */
public class CalendarController {
    
    protected CalendarGUI calgui;
    protected ShiftModel stm;
    protected String un;
    protected MemberModel mm;
    
    public CalendarController(ShiftModel stm, String un) {
        
        this.stm = stm;
        this.un = un;
        this.calgui = new CalendarGUI();
        this.mm = new MemberModel();
        
        if(this.mm.getMemberType(this.un)) {
            this.calgui.getJTable().setEnabled(true);
        } else {
            this.calgui.getJTable().setEnabled(false);
        }
        this.calgui.addShiftList(stm.getShiftsx("", "", false));
        this.calgui.addActionListener(new CalListener());
        this.calgui.addTableChangedListener(new TableListener());
        calgui.setVisible(true);
        
    }
    class CalListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getSource() == calgui.getSearchBtn()) {
                System.out.println("Søg");
                calgui.clearTable();
                calgui.addShiftList(stm.getShiftsx("", calgui.getSearchText(), true));
            }
            if(e.getSource() == calgui.getboxMonthly()) {
                calgui.clearTable();
                
                calgui.addShiftList(stm.getShiftsx("", "", false)); 
                
            }
            if(e.getSource() == calgui.getboxUser()) {
                
                calgui.clearTable();
                
                calgui.addShiftList(stm.getShiftsx("", un, false));
                
            }
            if(e.getSource() == calgui.getCalPanel()) {
                //calgui.addShiftList(stm.getShifts());
                //calgui.getTableModel().setRowCount(0);
                
                    //clearTable rydder JTablen
                    calgui.clearTable();
                    calgui.addShiftList(stm.getShiftsx(calgui.getSQLdate(), un, false));
                    
                    
                
            }
        }
    
    }
    class TableListener implements TableModelListener {

        @Override
        public void tableChanged(TableModelEvent e) {
            
            int row = e.getFirstRow();
            int column = e.getColumn();
            String columnName = calgui.getTableModel().getColumnName(column);
            
            
        }
        
    }
}
