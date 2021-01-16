    
package Preprocessing;

import MKNN.PembagianModel;
import MKNN.ProcessingData;
import TF_IDF.HitungTF_IDF;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import jsastrawi.cli.Main;
import jxl.read.biff.BiffException;
import skripsifaikar.Baca_Excel;

public class MainPreprocessing {
    public static void main(String[] args) {
         Baca_Excel test = new Baca_Excel();
        test.setInputFile("datareview.xls");
        try {
            test.read();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BiffException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //TOKEN
        ArrayList<String> data = test.getDataFix();
        ArrayList<String> label = test.getLabelDataFix();
        HashMap<Integer, ArrayList<String>> token = new HashMap<>();
        ArrayList<String> listToken = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            String sentence = data.get(i).toLowerCase();
           sentence = sentence.replaceAll("[^a-zA-Z0-9\\s]", " ");
           sentence = sentence.replaceAll("[0-9]", "");

            StringTokenizer objectToken = new StringTokenizer(sentence);
            int token_count = 0;
            while (objectToken.hasMoreTokens()) {
                listToken.add(objectToken.nextToken());
                token_count++;
            }
            token.put(i, listToken);
            listToken = new ArrayList<>();
        }
        for (int i = 0; i < token.size(); i++) {
            System.out.print((i+1)+"\t");
            for (int j = 0; j < token.get(i).size(); j++) {
                System.out.print(String.format("%-20s",token.get(i).get(j) +" "));
            }
                System.out.println("");
        }
        
        //STEMMING
        HashMap<Integer,ArrayList<String>> ReviewAfterStemming = new HashMap();
        System.out.println("");
        Stemming stem = new Stemming();
        for (int i = 0; i < token.size(); i++) {
            ArrayList<String> WordAfterStemming = new ArrayList();
            for (int j = 0; j < token.get(i).size(); j++) {
                WordAfterStemming.add(stem.Stem(token.get(i).get(j)));
                }
            ReviewAfterStemming.put(i,WordAfterStemming);
            }
        for (int i = 0; i < token.size(); i++) {
            System.out.println((i+1)+"\t");
            for (int j = 0; j < token.get(i).size(); j++) {
                System.out.println("Stemming: " + ReviewAfterStemming.get(i).get(j)+" ");
                }
            System.out.println("");
            }
        
        //NORMALISASI
        HashMap<Integer,ArrayList<String>> ReviewAfterNormalize = new HashMap();
        for (int i = 0; i < ReviewAfterNormalize.size(); i++) {
            ArrayList<String> WordAfterNormalize = new ArrayList();
            for (int j = 0; j < ReviewAfterNormalize.get(i).size(); j++) {
                    WordAfterNormalize.add(Normalization.Normalization(ReviewAfterNormalize.get(i).get(j)));
            }
            ReviewAfterNormalize.put(i, WordAfterNormalize);
        }
       for (int i = 0; i < ReviewAfterNormalize.size(); i++) {
           
            System.out.println((i+1)+"\t");
            for (int j = 0; j < ReviewAfterNormalize.get(i).size(); j++) {
                System.out.println("Normalize: " + ReviewAfterNormalize.get(i).get(j)+" ");
            
            }
            System.out.println("");
            }
       
       //REMOVE STOPWORD
       HashMap<Integer,ArrayList<String>> ReviewAfterStopWord = StopwordRemoval.stopWords(ReviewAfterNormalize);
//        for (int i = 0; i < TweetAfterNormalize.size(); i++) {
//            ArrayList<String> WordAfterStopWord = new ArrayList();
//            for (int j = 0; j < TweetAfterNormalize.get(i).size(); j++) {
//                    WordAfterStopWord.add(StopWordRemoval.stopWords(TweetAfterNormalize.get(i).get(j)));
//            }
//            TweetAfterStopWord.put(i, WordAfterStopWord);
//        }
       
       for (int i = 0; i < ReviewAfterStopWord.size(); i++) {
           
            System.out.println((i+1)+"\t");
            for (int j = 0; j < ReviewAfterStopWord.get(i).size(); j++) {
                System.out.println("Stop Words: " + ReviewAfterStopWord.get(i).get(j)+" ");
            
            }
            System.out.println("");
            }
       
       
       // TF IDF
       ArrayList<ProcessingData> tfidf = HitungTF_IDF.perhitungan_tf_idf(ReviewAfterStopWord,label);
        for (int i = 0; i < tfidf.size(); i++) {
            System.out.println("Review : "+tfidf.get(i).getNomorReview()+" ("+tfidf.get(i).getLabelReview()+") ");
            for (int j = 0; j < tfidf.get(i).getKataReview().size(); j++) {
                System.out.println("Kata : "+tfidf.get(i).getKataReview().get(j).getKata()+"\t"+ "| Term : "+tfidf.get(i).getKataReview().get(j).getTf()+"\t"+ "| Weight : "+tfidf.get(i).getKataReview().get(j).getWeight());
            }
            System.out.println("");
        }
        PembagianModel pm = new PembagianModel();
        pm.bentuk_model(tfidf);
    }
    
}
