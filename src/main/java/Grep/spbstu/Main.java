package Grep.spbstu;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {

    @Option(name = "-r", usage = "Regular expression")
    private boolean rgx;

    @Option(name = "-v", usage = "Invert expression")
    private boolean invert;

    @Option(name = "-i", usage = "Ignore register")
    private boolean register;

    @Argument(metaVar = "InputName", usage = "InputName")
    private String inputName;

    @Argument(metaVar = "word", usage = "Word")
    private String word;

    public static void begin(String[] args) {
        new Main().launch(args);
    }

    public void launch(String[] args) {

        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("Grep [-v] [-i] [-r] word inputName.txt");
            parser.printUsage(System.err);
        }

        try {
            if(inputName != null) {
                BufferedReader read = new BufferedReader(new FileReader(inputName));
                Grep res = new Grep();
                List<String> result = res.grepFlag(rgx, invert, register, word, read);
               for(String line : result) {
                   System.out.println(line);
               }

            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }


    }

}
