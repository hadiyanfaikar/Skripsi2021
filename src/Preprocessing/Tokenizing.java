package Preprocessing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;

public class Tokenizing {
    
    private LinkedHashMap<Integer, ArrayList<String>>map;
    private ArrayList<String>token;
    private String fileLocation;
    private final static String HASHTAG             = "#(\\w+)";
    private final static String NUMBER              = "[0-9]";
    private final static String NON_WORD            = "[^\\p{L}\\p{Nd}]+";
    private final static String URL                 = "((www\\.[\\s]+)|(https?://[^\\s]+))";
    private final static String USERNAME            = "@([^\\s]+)";
    private final static String STARTS_WITH_NUMBER  = "[0-9]\\s*(\\w+)";
    private final static String CONSECUTIVE_CHAR    = "([.!?^\\w])\\1{2,}";
    
    public Tokenizing(String fileLocation) {
        this.fileLocation = fileLocation;
    }
    
    public Tokenizing() {
    }
    
    public LinkedHashMap getAllToken(int j) throws FileNotFoundException, IOException {
        map = new LinkedHashMap<>();
        String line;
        
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileLocation));
        while((line = bufferedReader.readLine()) !=null) {
            StringTokenizer stringTokenizer = new StringTokenizer(line);
            
            token = new ArrayList<>();
            
            while(stringTokenizer.hasMoreTokens()) {
                String nextToken    = stringTokenizer.nextToken();
                nextToken           = nextToken.toLowerCase();
                
                if(nextToken.length()>2) {
                    nextToken = nextToken.replaceAll(HASHTAG, nextToken.substring(1));
                    //nextToken = nextToken.replaceAll(HASHTAG, "");
                }
                
                nextToken = nextToken.replaceAll(URL," ");
                nextToken = nextToken.replaceAll(USERNAME, " ");
                nextToken = nextToken.replaceAll(STARTS_WITH_NUMBER, " ");
                nextToken = nextToken.replaceAll(CONSECUTIVE_CHAR, "$1");
                nextToken = nextToken.replaceAll(NUMBER, "");
                nextToken = nextToken.replaceAll(NON_WORD, " ");
                
                if(nextToken.length()>2) {
                    if(nextToken.contains(" ")) {
                        String[] temp = nextToken.split(" ");
                        token.addAll(Arrays.asList(temp));
                        token.remove("");
                    } else {
                        token.add(nextToken);
                    }
                }
            }
            if(token.size() > 1) {
                map.put(j, token);
                j++;
            }
        }
        return map;
    }
}
