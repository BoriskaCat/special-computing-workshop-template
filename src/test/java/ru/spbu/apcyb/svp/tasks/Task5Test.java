package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

/**
 * Tests go Task 5.
 */
class Task5Test {
  @Test
  void areArgumentsCorrectTest() {
    String[] args = new String[] {"123", "123123", "13333111"};
    Exception e = assertThrows(IllegalArgumentException.class, () -> Task5.main(args));
    assertEquals("Invalid number of arguments", e.getMessage());
  }
  @Test
  void shortFileTest() throws IOException {
    String[] args = new String[]{"src/test/Task5TestDirectory/short.txt",
                                 "src/test/Task5TestDirectory/short_results.txt"};
    Task5.main(args);
    assertEquals(-1, Files.mismatch(
            Path.of("src/test/Task5TestDirectory/short_results_reference.txt"),
            Path.of("src/test/Task5TestDirectory/short_results.txt")
        )
    );
    assertTrue(Files.exists(Path.of("src/test/Task5TestDirectory/short_results/")));
  }

  @Test
  void longFileTest() {
    String[] args = new String[]{"src/test/Task5TestDirectory/long.txt",
                                 "src/test/Task5TestDirectory/long_results.txt"};
    Task5.main(args);
    assertTrue(Files.exists(Path.of("src/test/Task5TestDirectory/long_results/")));
  }
}
