package GUI;


import Model.Shift;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;


public class VagtTableModel extends AbstractTableModel
{
    
    private ArrayList<TableModelListener> listeners = new ArrayList<TableModelListener>();
 
    private static String[] columnNames = {"Frivillig", "Dato", "Vagt Start", "Vagt Slut"};
    private ArrayList<Object[]> data;
 
    public VagtTableModel(ArrayList<Object[]> data)
    {
        this.data = data;
        
    }

    @Override
    public int getRowCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data.get(rowIndex)[columnIndex] = aValue;
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        if (!listeners.contains(l))
            listeners.add(l);

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);

    }
    
    public void clearData() {
        this.data.clear();
    }
     
    
 
}