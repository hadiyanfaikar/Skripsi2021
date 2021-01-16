
package MKNN;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author acer
 */
public class PembagianModel {
     public void bentuk_model(ArrayList<ProcessingData> prosesData) {
        ArrayList<Integer> saveIndexPositiveTweet = new ArrayList<>();
        ArrayList<Integer> saveIndexNegativeTweet = new ArrayList<>();
        
        for (int i = 0; i < prosesData.size(); i++) {
            if(prosesData.get(i).getLabelReview().equals("Positif")) {
                saveIndexPositiveTweet.add(i);
            }
            else {
                saveIndexNegativeTweet.add(i);
            }
        }
        System.out.println(saveIndexPositiveTweet);
        System.out.println(saveIndexNegativeTweet);
        ArrayList<ProcessingData> dataPositif = new ArrayList<>();
        ArrayList<ProcessingData> dataNegatif = new ArrayList<>();
        for (int i = 0; i < saveIndexNegativeTweet.size(); i++) {
            dataPositif.add(prosesData.get(saveIndexPositiveTweet.get(i)));
            dataNegatif.add(prosesData.get(saveIndexNegativeTweet.get(i)));
        }
//        int k = 5;
//        
//        ArrayList<Model_Data> dataModel = new ArrayList<>();
//        int countModel = 0;
//        for (int i = 0; i < k; i++) {
//            ArrayList<ProcessingData> dataPerModel = new ArrayList<>();
//            for (int j = 0; j < dataNegatif.size()/k; j++) {
//                dataPerModel.add(dataPositif.get(countModel));
//                dataPerModel.add(dataNegatif.get(countModel));
//                countModel++;
//            }
//            Model_Data datdat = new Model_Data(i+1, dataPerModel);
//            dataModel.add(i, datdat);
//        }

        double zz = prosesData.size() * (2.0 / 3);
        ArrayList<DataModel> dataModel = new ArrayList<>();
        int countModel = 0;
        for (int i = 0; i < 1; i++) {
            ArrayList<ProcessingData> dataPerModel = new ArrayList<>();
            for (int j = 0; j < 50; j++) {
                dataPerModel.add(dataPositif.get(countModel));
                dataPerModel.add(dataNegatif.get(countModel));
                countModel++;
            }
            DataModel datdat = new DataModel(i + 1, dataPerModel);
            dataModel.add(datdat);
        }
        
        ArrayList<DataModel> dataTraining = new ArrayList<>();
        ArrayList<DataModel> dataTesting = new ArrayList<>();
        
        for (int i = 0; i < dataModel.size(); i++) {
            ArrayList<ProcessingData> dataPerModel = new ArrayList<>();
            for (int j = 0; j < dataModel.get(i).getDataPerModel().size(); j++) {
                if (j < Math.round(zz)) {
                    dataPerModel.add(dataModel.get(i).getDataPerModel().get(j));
                }
            }
            dataTraining.add(new DataModel(i + 1, dataPerModel));
            System.out.println("");
        }
        for (int i = 0; i < dataModel.size(); i++) {
            ArrayList<ProcessingData> dataPerModel = new ArrayList<>();
            for (int j = 0; j < dataModel.get(i).getDataPerModel().size(); j++) {
                if(j >= Math.round(zz)) {
                    dataPerModel.add(dataModel.get(i).getDataPerModel().get(j));
                }
            }
            dataTesting.add(new DataModel(i + 1, dataPerModel));
            System.out.println("");
        }
        
        
        for (int i = 0; i < dataTraining.size(); i++) {
            System.out.println("Model - "+dataTraining.get(i).getNomorModel());
            for (int j = 0; j < dataTraining.get(i).getDataPerModel().size(); j++) {
                System.out.println("Tweet - "+dataTraining.get(i).getDataPerModel().get(j).getNomorReview());
            }
            System.out.println("");
        }
        for (int i = 0; i < dataTesting.size(); i++) {
            System.out.println("Model - "+dataTesting.get(i).getNomorModel());
            for (int j = 0; j < dataTesting.get(i).getDataPerModel().size(); j++) {
                System.out.println("Tweet - "+dataTesting.get(i).getDataPerModel().get(j).getNomorReview());
            }
            System.out.println("");
        }
        
        ArrayList<Search> jarak = new ArrayList<>();
        HashMap<Integer, ArrayList<Search>> aaa = new HashMap();
        int cout = 1;
        double jarakPerReview = 0.0;
        double tampil = 0.0;
        for (int i = 0; i < dataTesting.size(); i++) {
            for (int j = 0; j < dataTraining.size(); j++) {
                for (int k = 0; k < dataTesting.get(i).getDataPerModel().size(); k++) {
                    jarak = new ArrayList<>();
                    for (int l = 0; l < dataTraining.get(i).getDataPerModel().size(); l++) {
                        double jarakPerKata = 0.0;
                        tampil = 0;
                        
                        for (int m = 0; m < dataTraining.get(i).getDataPerModel().get(i).getKataReview().size(); m++) {
                            jarakPerKata = Math.pow((dataTesting.get(i).getDataPerModel().get(k).getKataReview().get(m).getWeight() - dataTraining.get(i).getDataPerModel().get(l).getKataReview().get(m).getWeight()),2 );
                            tampil += jarakPerKata;
                            cout++;
                        }
                        jarakPerReview = Math.sqrt(tampil);
                        jarak.add(new Search(dataTraining.get(i).getDataPerModel().get(l).getLabelReview(), jarakPerReview));
                    }
                    aaa.put(k, jarak);
                }
            }
        }
        for (int i = 0; i < aaa.size(); i++) {
            System.out.println("Jarak Klasifikasi "+(i+1));
            for (int j = 0; j < aaa.get(i).size(); j++) {
                System.out.println("Jarak "+aaa.get(i).get(j).getJarak()+ "Label : "+aaa.get(i).get(j).getLabel());
            }
        }
        String [] labelKla = new String[dataTesting.get(0).getDataPerModel().size()];
        for (int i = 0; i < aaa.size(); i++) {
            double tempJarak = Double.MAX_VALUE;
            for (int j = 0; j < aaa.get(i).size(); j++) {
                if(aaa.get(i).get(j).getJarak() < tempJarak) {
                    labelKla[i] = aaa.get(i).get(j).getLabel();
                    tempJarak = aaa.get(i).get(j).getJarak();
                }
            }
        }
        int jumBenar = 0;
        for (int i = 0; i < labelKla.length; i++) {
                if (labelKla[i].equals(dataTesting.get(0).getDataPerModel().get(i).getLabelReview())) {
                    jumBenar++;
                }
        }
        System.out.println(jumBenar);
        
        double akurasi = (jumBenar*1.0/dataTesting.get(0).getDataPerModel().size())* 100;
        System.out.println("Akurasi = "+akurasi);
        for (int i = 0; i < labelKla.length; i++) {
            System.out.println("Klasifikasi "+(i + 1 + "= "+labelKla[i])+" ("+dataTesting.get(0).getDataPerModel().get(i).getLabelReview()+")");
        }
        System.out.println(labelKla);
        System.out.println(jarak.size());
    }
}
