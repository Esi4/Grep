package GrepTest.spbstu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import Grep.spbstu.Main;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GrepTest {
    private final Path inputNamePath = Paths.get("src","inputFiles", "input");
    private final String inputName = inputNamePath.toString();
    private final String ls = System.lineSeparator();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void returnStream() {
        System.setOut(System.out);
    }

    @Test
    public void word() {
        Main.begin(new String[]{"one", inputName});
        assertEquals("one, one" + ls, outContent.toString());
    }

    @Test
    public void regular() {
        Main.begin(new String[]{"-r", "(^o)", inputName});
        assertEquals("one, one" + ls +"o ne, two" + ls, outContent.toString());
    }

    @Test
    public void invertWord() {
        Main.begin(new String[]{"-v", "one", inputName});
        assertEquals("One, Two, Three, Four" + ls
                + "Five, One, Six," + ls
                + "Seven, Eight," + ls
                + "o ne, two" + ls, outContent.toString());
    }

    @Test
    public void invertRegular() {
        Main.begin(new String[]{"-r", "-v", "(^o)", inputName});
        assertEquals("One, Two, Three, Four" + ls
                + "Five, One, Six," + ls
                + "Seven, Eight," + ls, outContent.toString());
    }

    @Test
    public void registerWord() {
        Main.begin(new String[]{"-i", "one", inputName});
        assertEquals("One, Two, Three, Four" + ls
                + "Five, One, Six," + ls
                + "one, one" + ls, outContent.toString());
    }

    @Test
    public void registerRegular() {
        Main.begin(new String[]{"-i", "-r", "^o", inputName});
        assertEquals("One, Two, Three, Four" + ls
                + "one, one" + ls
                +"o ne, two" + ls, outContent.toString());
    }

    @Test
    public void allTest() {
        Main.begin(new String[]{"-v","-i", "-r", "^o", inputName});
        assertEquals("Five, One, Six," + ls
                + "Seven, Eight," + ls, outContent.toString());
    }


}
