package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

/**
 * Tests for Task 3.
 */
class Task3Test {

  @Test
  void illegalArgumentTest() {
    String[] args = {"..//"};
    assertThrows(IllegalArgumentException.class, () -> Task3.main(args));
  }

  @Test
  void fileNotFoundTest() {
    String[] args = {"non-existent directory/", "recordingFile.txt"};
    assertThrows(FileNotFoundException.class, () -> Task3.main(args));
  }

  @Test
  void fileSystemTest() {
    String[] args = {"..//", "..//"};
    assertThrows(FileSystemException.class, () -> Task3.main(args));
  }

  @Test
  void fileAlreadyExistsTest() {
    String[] args = {"..//", "src/test/CorrectStructure.txt"};
    assertThrows(FileAlreadyExistsException.class, () -> Task3.main(args));
  }

  @Test
  void test_preparedFileStructure() throws IOException {
    String pathToDirectory = "src/test/Task3TestDirectory/";
    Path test = Path.of("src/test/TestStructure.txt");
    String[] args = {pathToDirectory, test.toString()};
    Task3.main(args);
    boolean areEqual = true;
    try (BufferedReader recorder = new BufferedReader(new FileReader(
        test.toFile())); BufferedReader respondent = new BufferedReader(
            new FileReader(Path.of("src/test/CorrectStructure.txt").toFile()))) {

      String recorderLine = recorder.readLine();
      String respondentLine = respondent.readLine();
      while (recorderLine != null && respondentLine != null) {
        if (!respondentLine.equals(recorderLine)) {
          areEqual = false;
        }
        recorderLine = recorder.readLine();
        respondentLine = respondent.readLine();
      }
      if (recorderLine != null || respondentLine != null) {
        areEqual = false;
      }
    }
    Files.delete(test);
    assertTrue(areEqual);
  }
}