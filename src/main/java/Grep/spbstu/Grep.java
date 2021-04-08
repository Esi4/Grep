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
                if(!rgx) {
                    if(!register) wordSearch(str, word, str, invert);
                    else wordSearch(str.toLowerCase(), word.toLowerCase(), str, invert);
                } else {
                    if(!register) regularSearch(str, word, str, invert);
                    else regularSearch(str.toLowerCase(), word, str, invert);
                }
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return result;

    }

    private void wordSearch(String str, String word, String strCase, boolean invert) {
        if(str.contains(word) && !invert) {
            result.add(strCase);
        } else if(invert && !str.contains(word)) {
            result.add(strCase);
        }
    }

    private void regularSearch(String str, String word, String strCase, boolean invert) {
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
