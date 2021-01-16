/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Preprocessing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import jsastrawi.morphology.DefaultLemmatizer;
import jsastrawi.morphology.Lemmatizer;

public class Stemming {
      public String Stem(String kata) {
        Set<String> dictionary = new HashSet<String>();
        InputStream in = Lemmatizer.class.getResourceAsStream("/kamusa.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String line;
        try {
            while ((line = br.readLine()) != null) {
                dictionary.add(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(Stemming.class.getName()).log(Level.SEVERE, null, ex);
        }

        Lemmatizer lemmatizer = new DefaultLemmatizer(dictionary);
        String StemKata=lemmatizer.lemmatize(kata);
        return StemKata;
    }
}
