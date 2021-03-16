package Grep.spbstu;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Grep {
    public List<String> result = new ArrayList<String>();

    public List<String> grepFlag(boolean rgx, boolean invert, boolean register, String word, BufferedReader read) {

        try {
            String str;
            while ((str = read.readLine()) != null) {
                String strCase = str;
                if(!rgx) {
                    if(!register) wordSearch(str, word, strCase, invert);
                    else wordSearch(str.toLowerCase(), word.toLowerCase(), strCase, invert);
                } else {
                    if(!register) regularSearch(str, word, strCase, invert);
                    else regularSearch(str.toLowerCase(), word, strCase, invert);
                }
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return result;

    }

    public void wordSearch(String str, String word, String strCase, boolean invert) {
        if(str.contains(word) && !invert) {
            result.add(strCase);
        } else if(invert && !str.contains(word)) {
            result.add(strCase);
        }
    }

    public void regularSearch(String str, String word, String strCase, boolean invert) {
        Pattern pattern = Pattern.compile(word);
        Matcher matcher = pattern.matcher(str);
        boolean mFind = matcher.find();
        if(mFind && !invert) {
            result.add(strCase);
        } else if(!mFind && invert) {
            result.add(strCase);
        }
    }
}
