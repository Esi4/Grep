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
    private final Path inputNamePath = Paths.get("test/GrepTest.spbstu/input");
    private final String inputName = inputNamePath.toString();

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
        assertEquals("one, one", outContent);
    }

}
