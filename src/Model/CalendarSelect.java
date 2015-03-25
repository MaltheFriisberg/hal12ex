/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Arrays;

/**
 *
 * @author Mal
 */
public class CalendarSelect {
    
    public CalendarSelect() {
        
    }
    
    public int CalendarSelect(boolean[] array) {
        boolean[] Array1  = {false, false, false};
        boolean[] Array2  = {true, false, false};
        boolean[] Array3  = {true, false, true};
        boolean[] Array4 = {false, true, false};
        //boolean[] Array5 = {
        
        if(Arrays.equals(Array1, array) || Arrays.equals(Array2, array) ) {
            return 1;
        } else if(Arrays.equals(Array3, array)) {
            return 2;
        } else if(Arrays.equals(Array4, array)) {
            return 3;
        } else {
            return 0;
        }
        
    }
    
}
