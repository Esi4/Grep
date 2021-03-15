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
                    if(!register) wordSearch(str, word, invert);
                    else wordSearch(str.toLowerCase(), word.toLowerCase(), invert);
                } else {
                    if(!register) regularSearch(str, word, invert);
                    else regularSearch(str.toLowerCase(), word.toLowerCase(), invert);
                }
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return result;

    }

    public void wordSearch(String str, String word, boolean invert) {
        String[] split = str.split(" ");
        for (String s : split) {
            if (invert) {
                if (!s.equals(word)) {
                result.add(str);
                break;
                }
            }
            if(s.equals(word)) {
                result.add(str);
                break;
            }
        }
    }

    public void regularSearch(String str, String word, boolean invert) {
        Pattern pattern = Pattern.compile(word);
        Matcher matcher = pattern.matcher(str);
        if(matcher.find()) {
            result.add(str);
        } else if(invert) {
            result.add(str);
        }
    }
}
