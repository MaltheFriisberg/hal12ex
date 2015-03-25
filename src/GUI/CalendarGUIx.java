package GUI;
import Model.Shift;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author Mal
 * GUI bygget af Malthe, mangler at DatePicker bliver implementeret helt.
 * Måned skal minus 1.
 */
public class CalendarGUIx extends JFrame{
    
    //protected JFrame frame;
    protected UtilDateModel model;
    protected JPanel mainpanel;
    protected JPanel center;
    protected JPanel west;
    protected JPanel south;
    protected JTable table;
    protected DefaultTableModel tablemodel;
    protected JScrollPane scrollpane;
    protected ArrayList<Shift> list;
    protected ArrayList<Shift> list1;
    protected JDatePanelImpl datePanel;
    protected JDatePickerImpl datePicker;
    protected Calendar cal;
    protected String CurrDate;
    protected JRadioButton boxMonthly;
    protected JRadioButton boxUser;
    protected JRadioButton boxDay;
    protected ButtonGroup btnGroup;
    protected JLabel vagter1;
    protected JLabel vagter2;
    protected JTextField søg;
    protected VagtTableModel vagtmodel;
    
    protected JButton btnSearch;
    public CalendarGUIx(ArrayList<Shift> list1) {
        
        this.list1 = list1;
        
        //this.frame = new JFrame();
        //this.frame.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Vagt KalenderX");
        this.mainpanel = new JPanel();
        
        
        this.mainpanel.setLayout(new BorderLayout());
        addDatePicker();
        this.center = new JPanel(new FlowLayout());
        this.south = new JPanel(new GridLayout(1,3));
        
        this.tablemodel = new DefaultTableModel(new Object[]{"Frivillig", 
            "Dato", "Vagt Start", "Vagt Slut"}, 0);
        
        //this.tablemodel.addRow(new Object[]{"0", "1", "2", "3"});
        //fillTable();
        this.table = new JTable(this.vagtmodel);
        this.boxMonthly = new JRadioButton();
        this.boxUser = new JRadioButton();
        this.boxDay = new JRadioButton();
        boxUser.setToolTipText("Vis dine egne vagter");
        boxDay.setToolTipText("Vis dine egne vagter på valgte dato");
        boxMonthly.setToolTipText("Se vagter hele måneden for alle brugere");
        this.btnGroup = new ButtonGroup();
        this.vagter1 = new JLabel("Vagter denne måned");
        this.vagter2 = new JLabel("Kun mine vagter");
        this.søg = new JTextField("Søg Frivillig");
        this.btnSearch = new JButton("Søg");
        this.scrollpane = new JScrollPane( this.table );
        
        
        //setUpTableModel();
        
        this.south = new JPanel();
        this.west = new JPanel(new GridLayout(3,1));
        addComponents();
        
        
        this.pack();
    
    }
    public final void fillTable() {
        for(Shift s : this.list) {
            String username = s.getName();
            String date = s.getDate();
            String startTime = s.getStartTime();
            String endTime = s.getEndTime();
            this.tablemodel.addRow(new Object[]{username, date, startTime, endTime});
        }
    }
    
    private void addComponents() {
        
        this.mainpanel.add(this.center, BorderLayout.CENTER);
        this.mainpanel.add(this.west, BorderLayout.WEST);
        this.mainpanel.add(this.south, BorderLayout.SOUTH);
        //add buttons to ButtonGroup
        this.btnGroup.add(boxMonthly);
        //this.btnGroup.add(boxDay);
        this.btnGroup.add(boxUser);
        //add the btns to JPanel south
        this.south.add(vagter1);
        this.south.add(boxMonthly);
        this.south.add(vagter2);
        this.south.add(boxUser);
        
        this.south.add(søg);
        this.south.add(btnSearch);
        this.add(this.mainpanel);
        this.center.add(this.scrollpane);
        
    }
    private void addDatePicker() {
        
        this.model= new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        this.datePanel = new JDatePanelImpl(model, p);
        
        this.datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
    
        //returns the day selected. Use for action listener in the controller?
        Date selectedDate = (Date) datePicker.getModel().getValue();
    
         System.out.print(selectedDate);
    
        //get the current date. add to the datepicker model
        DateFormat dateFormat = new SimpleDateFormat("yyyy, MM, dd");
        this.cal = Calendar.getInstance();
        this.cal.add(Calendar.MONTH, -1);
        Date timeNow = this.cal.getTime();
        //System.out.println(dateFormat.format(cal.getTime()));
        
        this.CurrDate = dateFormat.format(timeNow);
        //Split the the current date with the getCurrDate method
        ArrayList<Integer> list = getCurrDate(this.CurrDate);
        //add date to datepicker model
        this.model.setDate(list.get(0), list.get(1), list.get(2));
        this.model.setSelected(true);
   
        this.mainpanel.add(datePicker, BorderLayout.NORTH);
        
    }
    
    public void addShiftList(ArrayList<Shift> list) {
        this.list = list;
        //this.vagtmodel = new VagtTableModel(list);
        fillTable();
        //resizes the row count 
        tablemodel.setRowCount(list.size());
    }
    /*
    @author Malthe
    Beskrivelse: splitter en streng til et array, bruges til at sætte
    datoen i addDatePicker()
    */
    private static ArrayList<Integer> getCurrDate(String date) {
        
        String input = date;

        String[] parts = input.split(",");
        ArrayList<Integer> list = new ArrayList();
        //int[] numbers = new int[parts.length];

        for(int i = 0; i < parts.length; i++)
        list.add(Integer.parseInt(parts[i].trim()));
    

    return list;
    }
    public void addActionListener(ActionListener listenForCalendar) {
        this.datePanel.addActionListener(listenForCalendar);
        this.boxUser.addActionListener(listenForCalendar);
        this.boxMonthly.addActionListener(listenForCalendar);
        this.boxDay.addActionListener(listenForCalendar);
        this.btnSearch.addActionListener(listenForCalendar);
    }
    public JDatePanelImpl getCalPanel() {
        return this.datePanel;
    }
    
    public Date getDateSelected() throws ParseException {
        Date selectedDate = (Date) this.datePicker.getModel().getValue();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");      
        Date dateWithoutTime = sdf.parse(sdf.format(selectedDate));
        
        return dateWithoutTime;
    }
    public JTable getJTable() {
        return this.table;
    }
    public DefaultTableModel getTableModel() {
        return this.tablemodel;
    }
    //returnerer den valgte dato i JDatePicker som en String
    public String getSQLdate() {
        //java.sql.Date sqlDate = new java.sql.Date(this.cal.getTime().getTime());
        Date selectedDate = (Date) datePicker.getModel().getValue();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(selectedDate);
        return date;
    }
    public void clearTable() {
       for( int i = tablemodel.getRowCount() - 1; i >= 0; i-- ) {
        tablemodel.removeRow(i);
        //this.vagtmodel.clearTable();
       }
    }
    public void updateView(ArrayList<Shift> list) {
           //this.vagtmodel.updateView(list);
    }
    
    public JRadioButton getboxMonthly() {
        return this.boxMonthly;
    }
    public JRadioButton getboxDay() {
        return this.boxDay;
    }
    public JRadioButton getboxUser() {
        return this.boxUser;
    }
    public boolean[] getSelection() {
        boolean[] array = {this.boxMonthly.isSelected(), this.boxDay.isSelected(), this.boxMonthly.isSelected()};
        
        return array;
    }
    public JButton getSearchBtn() {
        return this.btnSearch;
    }
    public String getSearchText() {
        return this.søg.getText();
    }
}
