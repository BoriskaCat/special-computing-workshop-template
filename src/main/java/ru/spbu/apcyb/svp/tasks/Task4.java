package ru.spbu.apcyb.svp.tasks;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Task 4.
 */
public class Task4 {

  private static final Logger logger = Logger.getLogger(Task4.class.getName());

  /**
   * Checking the validity of arguments:
   *   1) Calculation data file.
   *   2) File with calculation results (not yet created).
   */
  public static void areArgsCorrect(String[] args) {
    if (args.length != 2) {
      throw new IllegalArgumentException("Invalid number of arguments");
    }
  }

  /**
   * Reading data from a file.
   */
  private static List<Double> extractInputData(Path directory) {
    List<Double> values = new ArrayList<>();

    try (Scanner input = new Scanner(directory)) {
      while (input.hasNextLine()) {
        values.add(Double.valueOf(input.nextLine()));
      }

    } catch (IOException e) {
      logger.info("An error while reading the file: " + e.getMessage());
    }

    return values;
  }

  private static void writeToFile(Path directory, List<Double> data) {
    try (BufferedWriter output = Files.newBufferedWriter(directory)) {
      for (Double value : data) {
        output.write(value + "\n");
      }

    } catch (IOException e) {
      logger.info("An error while recording the file: " + e.getMessage());
    }
  }

  /**
   * Calculation of tangents in single thread mode.
   */
  public static List<Double> singleThreadTangentCalculation(List<Double> data) {
    List<Double> result = new ArrayList<>();

    for (Double x : data) {
      result.add(Math.tan(x));
    }

    return result;
  }

  /**
   * Calculation of tangents in multi thread mode.
   */
  public static List<Double> multiThreadTangentCalculation(List<Double> inputData,
                                                           int threadsNumber)
      throws Exception {
    ExecutorService executorService = Executors.newFixedThreadPool(threadsNumber);

    try {
      Future<List<Double>> futures = executorService.submit(
          () -> inputData.parallelStream().map(Math::tan).toList());
      return futures.get();
    } finally {
      executorService.shutdownNow();
    }
  }

  /**
   * Logging calculation results in various modes.
   *
   * @param input file with input values
   * @param outputSingleThreadMode file for single-thread output
   * @param outputMultiThreadMode file for multi-thread output
   */
  public static void calculationOfTangentsWithLogging(String input,
                                                      String outputSingleThreadMode,
                                                      String outputMultiThreadMode) {
    try {

      tangentsCalculation(Path.of(input),
                          Path.of(outputSingleThreadMode),
                          Path.of(outputMultiThreadMode));

    } catch (InvalidPathException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  /**
   * Main component of calculationOfTangentsWithLogging function.
   */
  private static void tangentsCalculation(Path inputPath,
                                          Path outputSingleThreadModePath,
                                          Path outputMultiThreadModeOutput)
      throws RuntimeException {
    List<Double> inputData = extractInputData(inputPath);

    Instant startTimeSingleMode = Instant.now();
    List<Double> resultsSingleMode = singleThreadTangentCalculation(inputData);
    Instant endTimeSingleMode = Instant.now();
    writeToFile(outputSingleThreadModePath, resultsSingleMode);

    logger.log(Level.INFO, "Time in single thread mode: {0} ms",
        Duration.between(startTimeSingleMode, endTimeSingleMode).toMillis());

    try {
      Instant startTimeMultiMode = Instant.now();
      List<Double> resultsMultiMode = multiThreadTangentCalculation(inputData, 4);
      Instant endTimeMultiMode = Instant.now();
      writeToFile(outputMultiThreadModeOutput, resultsMultiMode);

      logger.log(Level.INFO, "Time in multi thread mode: {0} ms",
          Duration.between(startTimeMultiMode, endTimeMultiMode).toMillis());
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  /**
   * Main executive function.
   */
  public static void main(String[] args) {

    areArgsCorrect(args);

    try {
      List<Double> inputData = extractInputData(Path.of(args[0]));
      List<Double> calculatedTangents = multiThreadTangentCalculation(inputData, 5);

      writeToFile(Path.of(args[1]), calculatedTangents);
    } catch (Exception e) {
      String stackTrace = Arrays.stream(e.getStackTrace())
          .map(StackTraceElement::toString)
          .collect(Collectors.joining("\n"));
      logger.info("The thread was interrupted at:\n" + stackTrace);
    }
  }
}