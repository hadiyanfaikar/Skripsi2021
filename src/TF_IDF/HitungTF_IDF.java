
package TF_IDF;

import java.util.ArrayList;
import java.util.HashMap;
import MKNN.ProcessingData;

public class HitungTF_IDF {
    public static ArrayList<ProcessingData> perhitungan_tf_idf(HashMap<Integer, ArrayList<String>> data,ArrayList<String> label) {
        HashMap<Integer, ArrayList<String>> temp = new HashMap();

        for (int i = 0; i < data.size(); i++) {
            ArrayList<String> tempKata = new ArrayList<>();
            for (int j = 0; j < data.get(i).size(); j++) {
                if (j == 0) {
                    tempKata.add(data.get(i).get(j));
                }
                if (!(tempKata.contains(data.get(i).get(j)))) {
                    tempKata.add(data.get(i).get(j));
                }
            }
            temp.put(i, tempKata);
        }
        ArrayList<String> tempKata1 = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).size(); j++) {
                if (j == 0 && i == 0) {
                    tempKata1.add(data.get(i).get(j));
                }
                if (!(tempKata1.contains(data.get(i).get(j)))) {
                    tempKata1.add(data.get(i).get(j));
                }
            }

        }
        System.out.println("Jumlah seluruh Kata : " +tempKata1.size()+" Kata");
        System.out.println("");
        HashMap<String, Double> df = new HashMap<>();
        double countdf = 0;
        for (int i = 0; i < tempKata1.size(); i++) {
            for (int j = 0; j < data.size(); j++) {
                if (data.get(j).contains(tempKata1.get(i))) {
                    countdf++;
                }
            }

            df.put(tempKata1.get(i), countdf);
            countdf = 0;
        }

        HashMap<String, Double> idf = new HashMap<>();
        for (int i = 0; i < tempKata1.size(); i++) {
            double hitungIdf = Math.log10(data.size() / df.get(tempKata1.get(i)));
            idf.put(tempKata1.get(i), hitungIdf);
//                if (b.get(j).contains(tempKata1.get(i))) {
//                    countdf++;
//          

        }
//         for (int i = 0; i < cdf.size(); i++) {
//             System.out.println(idf.get(i)+" "+cdf.get(i).getDf());
//        }
        //        if (b.get(0).contains("aku")) {
//            System.out.println("ada");
//        }
        HashMap<Integer, ArrayList<ModelTF_IDF>> ctfidf = new HashMap<>();

        int count = 0;

        for (int i = 0; i < data.size(); i++) {
            ArrayList<ModelTF_IDF> c = new ArrayList<>();
            for (int j = 0; j < tempKata1.size(); j++) {
                String perKata = tempKata1.get(j);
                for (int k = 0; k < data.get(i).size(); k++) {
                    if (perKata.equals(data.get(i).get(k))) {
                        count++;
                    }
                }
                ModelTF_IDF ss = new ModelTF_IDF(tempKata1.get(j), count);
                c.add(ss);
                count = 0;
//                System.out.println("");
            }
            ctfidf.put(i, c);
        }

        for (int i = 0; i < ctfidf.size(); i++) {
            for (int j = 0; j < ctfidf.get(i).size(); j++) {
                double wdt = ctfidf.get(i).get(j).getTf() * idf.get(ctfidf.get(i).get(j).getKata());
                ctfidf.get(i).get(j).setWeight(wdt);
            }
        }
        ArrayList<ProcessingData> processData = new ArrayList<>();
        for (int i = 0; i < ctfidf.size(); i++) {
            ArrayList<ModelTF_IDF> wordOfTweet = new ArrayList<>();
            for (int j = 0; j < ctfidf.get(i).size(); j++) {
                wordOfTweet.add(ctfidf.get(i).get(j));
            }
            ProcessingData dataproses = new ProcessingData(i+1, wordOfTweet, label.get(i));
            processData.add(i,dataproses);
        }
        return processData;
    }
}
