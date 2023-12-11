package ru.spbu.apcyb.svp.tasks;

import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Task 1.
 */

public class Task1 {

  static Logger logger = Logger.getLogger(Task1.class.getName());

  /**
   * Receiving the exchange amount from the user.
   */
  public static long getAmount() {
    Scanner scanner = new Scanner(System.in);
    long amount;
    try {
      amount = scanner.nextLong();
      scanner.nextLine();
    } catch (InputMismatchException e) {
      throw new InputMismatchException("Amount entered incorrectly");
    } catch (NoSuchElementException e) {
      throw new NoSuchElementException("Nothing entered");
    }
    if (amount <= 0) {
      throw new ArithmeticException("Invalid value (0 or negative)");
    }
    return amount;
  }

  /**
   * Receiving available denominations for exchange from the user. The array of entered values is
   * sorted.
   */
  public static List<Long> getDenominations() {
    Scanner scanner = new Scanner(System.in);
    String userInput;
    try {
      userInput = scanner.nextLine();
    } catch (NoSuchElementException e) {
      throw new NoSuchElementException("Input Error");
    }

    if (Objects.equals(userInput, "")) {
      throw new NullPointerException("Nothing entered");
    }

    /* Reversed sorted list of denominations without repetitions */
    List<Long> listOfDenominations;
    try {
      listOfDenominations = Arrays.stream(userInput.trim().split(" "))
          .map(Long::parseLong).sorted(Comparator.reverseOrder()).toList();
      listOfDenominations = listOfDenominations.stream().distinct().toList();
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Invalid value");
    }

    if (listOfDenominations.get(listOfDenominations.size() - 1) <= 0) {
      throw new NumberFormatException("Invalid values (there are 0 or negative)");
    }

    return listOfDenominations;
  }

  /**
   * Combination generation function that returns the total number of combinations. The newly
   * created combination is printed.
   */
  public static long getAllCombinations(long sum, List<Long> denominations) {
    return exchangeCombinations(sum, denominations,
        new long[denominations.size()],
        0);
  }

  /**
   * Main component of getAllCombinations function.
   *
   * @param denominationQuantity Array of numbers of each denomination in combination.
   * @param denominationIndex    Index of denomination with which the calculation is made.
   */

  private static long exchangeCombinations(long amount, List<Long> denominations,
      long[] denominationQuantity,
      int denominationIndex) {
    int numberOfDenominations = denominations.size();
    long value;
    long numberOfCombinations = 0;

    if (denominationIndex == numberOfDenominations - 1) {
      if (amount % denominations.get(denominationIndex) == 0) {
        numberOfCombinations = 1;
        denominationQuantity[denominationIndex] = amount / denominations.get(denominationIndex);
        printCombination(denominations, denominationQuantity);
      }
    } else {
      long l = amount / denominations.get(denominationIndex);
      for (long i = l; i >= 0; i--) {
        value = amount - i * denominations.get(denominationIndex);
        denominationQuantity[denominationIndex] = i;
        numberOfCombinations += exchangeCombinations(value, denominations,
            denominationQuantity,
            denominationIndex + 1);
      }
    }

    return numberOfCombinations;
  }

  /**
   * Combination output.
   */
  public static void printCombination(List<Long> denominations,
      long[] distributionOfDenominations) {
    int numberOfDenominations = denominations.size();

    logger.info("Combination start");
    for (int i = 0; i < numberOfDenominations; i++) {
      for (int j = 0; j < distributionOfDenominations[i]; j++) {
        logger.log(Level.INFO, "{0}", denominations.get(i));
      }
    }
    logger.info("Combination end");
  }

  /**
   * Main executive function.
   */
  public static void main(String[] args) {
    logger.info("# Exchange amount:");
    long amount = getAmount();

    logger.info("# Available denominations:");
    List<Long> denominations = getDenominations();

    long numberOfCombinations = getAllCombinations(amount, denominations);

    logger.log(Level.INFO, "# Number of combinations {0}", numberOfCombinations);
  }
}