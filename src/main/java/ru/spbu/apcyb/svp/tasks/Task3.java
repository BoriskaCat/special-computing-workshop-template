package ru.spbu.apcyb.svp.tasks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Task 3.
 */
public class Task3 {

  /**
   * Checking the validity of arguments:
   *   1) Directory exists.
   *   2) The full name of a file that has not yet been created is specified.
   */
  public static void areArgsCorrect(String[] args) throws IOException {
    if (args.length != 2) {
      throw new IllegalArgumentException("Invalid number of arguments");
    }

    if (!Files.exists(Path.of(args[0]))) {
      throw new FileNotFoundException("Directory does not exist");
    }

    if (new File(args[1]).isDirectory()) {
      throw new FileSystemException("The argument is not a file");
    }

    if (Files.exists(Path.of(args[1]))) {
      throw new FileAlreadyExistsException("The file already exists");
    }
  }

  /**
   * Writes a tree from pathToDirectory.
   */
  public static void getStructureTree(String pathToDirectory, BufferedWriter localBuffer)
      throws IOException {
    fileTreeBuilder(pathToDirectory, 0, localBuffer);
  }

  /**
   * Main component of getStructureTree function.
   */
  public static void fileTreeBuilder(String pathToDirectory, int treeDepth,
                                                BufferedWriter localBuffer)
      throws IOException {
    File[] files = filesByDirectory(pathToDirectory);
    for (File f : files) {
      indentation(treeDepth, localBuffer);
      if (f.isDirectory()) {
        localBuffer.write(f.getName() + "\n");
        fileTreeBuilder(f.getAbsolutePath(), treeDepth + 1, localBuffer);
      } else {
        localBuffer.write(f.getName() + "\n");
      }
    }
  }

  /**
   * Writes the contents of a folder at the same level as treeDepth.
   */
  private static File[] filesByDirectory(String pathToDirectory) {
    File root = new File(pathToDirectory);
    return root.listFiles();
  }


  /**
   * Adds indentation.
   */
  public static void indentation(int value, BufferedWriter localBuffer)
      throws IOException {
    for (int i = 0; i < value; i++) {
      localBuffer.write("  ");
    }
  }

  /**
   * Main executive function.
   */
  public static void main(String[] args) throws IOException {

    areArgsCorrect(args);

    Path result = Files.createFile(Path.of(args[1]).toAbsolutePath());
    BufferedWriter localBuffer = Files.newBufferedWriter(result);

    getStructureTree(args[0], localBuffer);
    localBuffer.close();
  }
}