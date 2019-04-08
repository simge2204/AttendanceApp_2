/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attapp.gui.model;

/**
 *
 * @author Henrik Ferrari
 */
public class TableModel {
    
    String date, prescense;

    public TableModel(String date, String prescense) {
        this.date = date;
        this.prescense = prescense;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrescense() {
        return prescense;
    }

    public void setPrescense(String prescense) {
        this.prescense = prescense;
    }
    
    
}
