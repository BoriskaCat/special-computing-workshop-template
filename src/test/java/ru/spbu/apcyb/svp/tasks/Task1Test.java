package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Tests for Task 1.
 */
class Task1Test {
  @Test
  void getAmountTest_correctInput() {
    System.setIn(new ByteArrayInputStream("1\n".getBytes()));
    assertEquals(1L, Task1.getAmount());
  }

  @Test
  void getAmountTest_invalidInput_0() {
    System.setIn(new ByteArrayInputStream("0\n".getBytes()));
    ArithmeticException ex = assertThrows(ArithmeticException.class, Task1::getAmount);
    assertEquals("Invalid value (0 or negative)", ex.getMessage());
  }

  @Test
  void getAmountTest_invalidInput_negative() {
    System.setIn(new ByteArrayInputStream("-1\n".getBytes()));
    ArithmeticException ex = assertThrows(ArithmeticException.class, Task1::getAmount);
    assertEquals("Invalid value (0 or negative)", ex.getMessage());
  }

  @Test
  void getAmountTest_emptyInput() {
    System.setIn(new ByteArrayInputStream("".getBytes()));
    NoSuchElementException ex = assertThrows(NoSuchElementException.class, Task1::getAmount);
    assertEquals("Nothing entered", ex.getMessage());
  }

  @Test
  void getAmountTest_incorrectInput() {
    System.setIn(new ByteArrayInputStream("areena".getBytes()));
    NoSuchElementException ex = assertThrows(NoSuchElementException.class, Task1::getAmount);
    assertEquals("Amount entered incorrectly", ex.getMessage());
  }

  @Test
  void getDenominationsTest_correctInput() {
    System.setIn(new ByteArrayInputStream("1 2\n".getBytes()));
    assertEquals(Arrays.asList(2L, 1L), Task1.getDenominations());
  }

  @ParameterizedTest
  @CsvSource(value = {
      "0, Invalid values (there are 0 or negative)",
      "-1 1, Invalid values (there are 0 or negative)",
      "a b, Invalid value"

  })
  void getDenominationsTest_NumberFormatException(String denomination, String expected) {
    System.setIn(new ByteArrayInputStream(denomination.getBytes()));
    NumberFormatException e = assertThrows(NumberFormatException.class, Task1::getDenominations);
    assertEquals(expected, e.getMessage());
  }

  @Test
  void getDenominationsTest_newLineInput() {
    System.setIn(new ByteArrayInputStream("\n".getBytes()));
    NullPointerException ex = assertThrows(NullPointerException.class, Task1::getDenominations);
    assertEquals("Nothing entered", ex.getMessage());
  }

  @Test
  void getDenominationsTest_emptyInput() {
    System.setIn(new ByteArrayInputStream("".getBytes()));
    NoSuchElementException ex = assertThrows(NoSuchElementException.class,
        Task1::getDenominations);
    assertEquals("Input Error", ex.getMessage());
  }

  @Test
  void getAllCombinationsTest_5__3_2() {
    System.setIn(new ByteArrayInputStream("5\n3 2\n".getBytes()));
    assertEquals(1, Task1.getAllCombinations(5, Arrays.asList(3L, 2L)));
  }

  @Test
  void getAllCombinationsTest_4__2_1() {
    System.setIn(new ByteArrayInputStream("4\n2 1\n".getBytes()));
    assertEquals(3, Task1.getAllCombinations(4, Arrays.asList(2L, 1L)));
  }

  @Test
  void getAllCombinationsTest_4__1_2() {
    System.setIn(new ByteArrayInputStream("4\n1 2\n".getBytes()));
    assertEquals(3, Task1.getAllCombinations(4, Arrays.asList(2L, 1L)));
  }

  @Test
  void getAllCombinationsTest_1000__1() {
    System.setIn(new ByteArrayInputStream("1000\n1\n".getBytes()));
    assertEquals(1, Task1.getAllCombinations(1000, List.of(1L)));
  }

  @Test
  void getAllCombinationsTest_1000__500_1() {
    System.setIn(new ByteArrayInputStream("1000\n500 1\n".getBytes()));
    assertEquals(3, Task1.getAllCombinations(1000, Arrays.asList(500L, 1L)));
  }

  @Test
  void getAllCombinationsTest_5__10_6() {
    System.setIn(new ByteArrayInputStream("5\n10 6\n".getBytes()));
    assertEquals(0, Task1.getAllCombinations(5, Arrays.asList(10L, 6L)));
  }

  @Test
  void getAllCombinationsTest_3000000000__3000000000() {
    System.setIn(new ByteArrayInputStream("3000000000\n3000000000\n".getBytes()));
    assertEquals(1, Task1.getAllCombinations(3000000000L, List.of(3000000000L)));
  }

  @Test
  void getAllCombinationsTest_5__1_1() {
    System.setIn(new ByteArrayInputStream("5\n1 1\n".getBytes()));
    assertEquals(1, Task1.getAllCombinations(5, List.of(1L)));
  }
}