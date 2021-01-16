package MKNN;

import TF_IDF.ModelTF_IDF;
import java.util.ArrayList;

public class ProcessingData {
    private int nomorReview;
    private ArrayList<ModelTF_IDF> kataReview;
    private String labelReview;
    
    public ProcessingData(int nomorReview, ArrayList<ModelTF_IDF> kataReview, String labelReview){
        this.nomorReview = nomorReview;
        this.kataReview = kataReview;
        this.labelReview = labelReview;
    }

    public int getNomorReview() {
        return nomorReview;
    }

    public void setNomorReview(int nomorReview) {
        this.nomorReview = nomorReview;
    }

    public ArrayList<ModelTF_IDF> getKataReview() {
        return kataReview;
    }

    public void setKataReview(ArrayList<ModelTF_IDF> kataReview) {
        this.kataReview = kataReview;
    }

    public String getLabelReview() {
        return labelReview;
    }

    public void setLabelReview(String labelReview) {
        this.labelReview = labelReview;
    }
    
    
}
