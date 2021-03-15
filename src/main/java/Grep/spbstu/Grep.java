package Grep.spbstu;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {
    public List<String> grepFlag(boolean rgx, boolean invert, boolean register, String word, BufferedReader read) {
        List<String> result = null;


        try {
            String str;
            while ((str = read.readLine()) != null) {
                assert false;
                if(!rgx) {
                    if(!register) result.add(wordSearch(str, word, invert));
                    else result.add(wordSearch(str.toLowerCase(), word.toLowerCase(), invert));
                } else {
                    if(!register) result.add(regularSearch(str, word, invert));
                    else result.add(regularSearch(str.toLowerCase(), word.toLowerCase(), invert));
                }
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return result;

    }

    public String wordSearch(String str, String word, boolean invert) {
        String[] split = str.split(" ");
        for (String s : split) {
            if (s.equals(word)) {
                return str;
            } else {
                if (invert) {
                    return str;
                }
            }
        }

        return "";
    }

    public String regularSearch(String str, String word, boolean invert) {
        Pattern pattern = Pattern.compile(word);
        Matcher matcher = pattern.matcher(str);
        if(matcher.find()) {
            return str;
        } else if(invert) {
            return str;
        }
        return "";
    }
}
