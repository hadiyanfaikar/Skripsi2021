/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MKNN;

/**
 *
 * @author acer
 */
public class Search {
    private String label;
    private double jarak;

    public Search(String label, double jarak) {
        this.label = label;
        this.jarak = jarak;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getJarak() {
        return jarak;
    }

    public void setJarak(double jarak) {
        this.jarak = jarak;
    }
}
