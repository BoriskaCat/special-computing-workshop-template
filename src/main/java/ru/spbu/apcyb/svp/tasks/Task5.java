package ru.spbu.apcyb.svp.tasks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Task 5.
 */
public class Task5 {
  private static final Logger logger = Logger.getLogger(Task5.class.getName());

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
   * Function of writing a word to a unique file.
   */
  private static CompletableFuture<Void> uniqueWordToNewFile(Path directory,
                                                             String uniqueWord,
                                                             Long numberOfRepetitions,
                                                             ExecutorService executorService) {

    return CompletableFuture.runAsync(() -> {

      try (BufferedWriter file = Files.newBufferedWriter(directory)) {
        for (int i = 0; i < numberOfRepetitions; i++) {
          file.write(uniqueWord + " ");
        }

      } catch (IOException e) {
        logger.info("An error while recording the file: " + e.getMessage());
      }
    }, executorService);
  }

  /**
   * Function for recording a new word and the number of its repetitions.
   */
  private static void writeToFile(Path directory, Map<String, Long> words) {
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    List<CompletableFuture<Void>> futures = new ArrayList<>();

    try (BufferedWriter file = Files.newBufferedWriter(directory)) {
      String stringFinalDirectory = directory
                                      .toString()
                                      .substring(0, directory
                                                      .toString()
                                                      .lastIndexOf("."));
      Path finalDirectory = Path.of(Path.of(stringFinalDirectory) + File.separator);
      
      if (!Files.exists(finalDirectory)) {
        Files.createDirectory(finalDirectory);
      }

      for (Entry<String, Long> word : words
                                        .entrySet()
                                        .stream()
                                        .sorted(Map.Entry.comparingByKey(Comparator
                                                                          .naturalOrder()))
                                                                          .toList()) {

        String resultString = word.getKey() + " " + word.getValue() + "\n";
        file.write(resultString);

        Path resultDirectory = Path.of(stringFinalDirectory
                                        + File.separator
                                        + word.getKey()
                                        + ".txt");
        futures.add(
            uniqueWordToNewFile(resultDirectory, word.getKey(), word.getValue(), executorService));
      }

    } catch (IOException e) {
      logger.info("An error while recording the file: " + e.getMessage());
    } finally {
      futures.forEach(CompletableFuture::join);
      executorService.shutdown();
    }
  }

  /**
   * Function for counting the number of repetitions of the word.
   */
  private static Map<String, Long> numberOfWordRepetitions(Path directory) {

    try (Stream<String> lines = Files.lines(directory).parallel()) {

      return lines.flatMap(line -> Arrays.stream(line.split(" ")))
          .map(word -> word.trim().replaceAll("\\p{Punct}", "").toLowerCase())
          .filter(word -> !word.isEmpty())
          .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Main executive function.
   */
  public static void main(String[] args) {

    areArgsCorrect(args);

    try {
      Map<String, Long> words = numberOfWordRepetitions(Path.of(args[0]));

      writeToFile(Path.of(args[1]), words);
    } catch (Exception e) {
      String stackTrace = Arrays.stream(e.getStackTrace())
          .map(StackTraceElement::toString)
          .collect(Collectors.joining("\n"));
      logger.info("The thread was interrupted at:\n" + stackTrace);
    }
  }
}