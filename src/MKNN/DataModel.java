package MKNN;

import java.util.ArrayList;

public class DataModel {

    private int nomorModel;
    private ArrayList<ProcessingData> DataPerModel;
    
    public DataModel(int nomorModel, ArrayList<ProcessingData> DataPerModel){
        this.nomorModel = nomorModel;
        this.DataPerModel = DataPerModel;
    }

    public int getNomorModel() {
        return nomorModel;
    }

    public void setNomorModel(int nomorModel) {
        this.nomorModel = nomorModel;
    }

    public ArrayList<ProcessingData> getDataPerModel() {
        return DataPerModel;
    }

    public void setDataPerModel(ArrayList<ProcessingData> DataPerModel) {
        this.DataPerModel = DataPerModel;
    }
    
}
