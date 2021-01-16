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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import jsastrawi.morphology.Lemmatizer;

public class StopwordRemoval {
      public static HashMap<Integer, ArrayList<String>> stopWords(HashMap<Integer, ArrayList<String>> data) {
        Set<String> dictionary = new HashSet<String>();
        InputStream in = Lemmatizer.class.getResourceAsStream("/stopwordlist.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String line;
        try {
            while ((line = br.readLine()) != null) {
                dictionary.add(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(StopwordRemoval.class.getName()).log(Level.SEVERE, null, ex);
        }

        HashMap<Integer, ArrayList<Integer>> indexRemove = new HashMap<>();

        for (int i = 0; i < data.size(); i++) {
            data.get(i).removeAll(dictionary);
        }
        return data;
    }
}
