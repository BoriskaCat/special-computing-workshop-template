package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

/**
 * Tests fot Task 4.
 */
class Task4Test {
  @Test
  void calculationOfTangentsWithLogging_1() throws IOException {
    System.out.println("1:");
    String input = "src/test/Task4TestDirectory/1.txt";
    String outputSingleMode = "src/test/Task4TestDirectory/calculatedTangents1_singleMode.txt";
    String outputMultiMode = "src/test/Task4TestDirectory/calculatedTangents1_multiMode.txt";

    Task4.calculationOfTangentsWithLogging(input, outputSingleMode, outputMultiMode);

    Path singleThreadResult = Path.of(outputSingleMode);
    Path multiThreadResult = Path.of(outputMultiMode);
    assertEquals(-1, Files.mismatch(singleThreadResult, multiThreadResult));
  }

  @Test
  void calculationOfTangentsWithLogging_100() throws IOException {
    System.out.println("100:");
    String input = "src/test/Task4TestDirectory/100.txt";
    String outputSingleMode = "src/test/Task4TestDirectory/calculatedTangents100_singleMode.txt";
    String outputMultiMode = "src/test/Task4TestDirectory/calculatedTangents100_multiMode.txt";

    Task4.calculationOfTangentsWithLogging(input, outputSingleMode, outputMultiMode);

    Path singleThreadResult = Path.of(outputSingleMode);
    Path multiThreadResult = Path.of(outputMultiMode);
    assertEquals(-1, Files.mismatch(singleThreadResult, multiThreadResult));
  }

  @Test
  void calculationOfTangentsWithLogging_1000000() throws IOException {
    System.out.println("1000000:");
    String input = "src/test/Task4TestDirectory/1000000.txt";
    String outputSingleMode = "src/test/Task4TestDirectory/calculatedTangents1mil_singleMode.txt";
    String outputMultiMode = "src/test/Task4TestDirectory/calculatedTangents1mil_multiMode.txt";

    Task4.calculationOfTangentsWithLogging(input, outputSingleMode, outputMultiMode);

    Path singleThreadResult = Path.of(outputSingleMode);
    Path multiThreadResult = Path.of(outputMultiMode);
    assertEquals(-1, Files.mismatch(singleThreadResult, multiThreadResult));
  }
}