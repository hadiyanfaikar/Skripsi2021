/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TF_IDF;

public class ModelTF_IDF {
    private String kata;
    private int tf;
    private double weight;

    public ModelTF_IDF(String kata, int tf) {
        this.kata = kata;
        this.tf = tf;
    }

    public String getKata() {
        return kata;
    }

    public int getTf() {
        return tf;
    }

    public double getWeight() {
        return weight;
    }

    public void setKata(String kata) {
        this.kata = kata;
    }

    public void setTf(int tf) {
        this.tf = tf;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
