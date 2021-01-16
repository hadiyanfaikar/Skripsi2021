
package Preprocessing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import jsastrawi.morphology.Lemmatizer;
import static skripsifaikar.NewClass.removeCharAt;

public class Normalization {
     public static String Normalization(String kata) {
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
        ArrayList<Integer> normal = new ArrayList<>();
        String temp = kata;
        if (dictionary.contains(kata)) {
        }
        else {
            for (int i = 0; i < kata.length()-1; i++) {
                if (temp.charAt(i) == temp.charAt(i + 1)) {
                    normal.add(i);
                }
            }
            for (int i = normal.size()-1; i >= 0; i--) {
                temp = removeCharAt(temp,normal.get(i));
            }
        }
        if ((dictionary.contains(temp))) {
                kata = temp;
            }
        return kata;
    }
}
