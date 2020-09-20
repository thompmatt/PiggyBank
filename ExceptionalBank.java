//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Exceptional Bank
// Files: ExceptionalBank.java, ExceptionalBankTester.java
// Course: CS 300, Spring 2020
//
// Author: Matt Thompson
// Email: mathompson23@wisc.edu
// Lecturer's Name: Hobbes LeGault
//
//////////// PAIR PROGRAMMING (MAY SKIP WHEN WORKING INDIVIDUALLY) ////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understood the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Students who get help from sources other than their partner and the course
// staff must fully acknowledge and credit those sources here. If you did not
// receive any help of any kind from outside sources, explicitly indicate NONE
// next to each of the labels below.
//
// Persons: NONE
// Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This program creates an exceptional bank, with the capacity to use external files as input and
 * save to a file. 
 * - Can construct a default piggy bank, or one with a defined capacity. 
 * - Can access and retrieve five elements: capacity of bank, expansions left, current size, get 
 * balance in bank, and a summary of the coins in bank. 
 * - Can randomly remove one coin from the piggy bank. 
 * - Can add one coin to the piggy bank. 
 * - Can fully empty the piggy bank.
 * - Can use a file as input for coins to add into the bank
 * - Can write and save the coins in the bank to another file
 * 
 * @author Matt Thompson
 */

import java.util.Arrays;
import java.util.Random;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.PrintWriter;

/**
 * This class implements an expanded version of elastic bank application.
 * 
 * @author Matt Thompson
 */
public class ExceptionalBank {
  private Coin[] coins; // Array which stores all coins held in this elastic bank
  private int size; // Size of this elastic bank
  private int expansionsLeft; // Number of expansions left for this elastic bank
  private static Random rand = new Random(100); // Random integer generator

  /**
   * Creates a new elastic bank object with a given initial capacity.
   * 
   * @param initialCapacity Initial capacity of this elastic bank
   * @throws IllegalArgumentException if initialCapacity is zero or negative
   */
  public ExceptionalBank(int initialCapacity) throws IllegalArgumentException {
    // If initialCapacity is less or equal to zero, throw IllegalArgumentException
    if (initialCapacity <= 0) {
      throw new IllegalArgumentException(
          "WARNING! The initial capacity of a bank must be a non-zero positive integer.");
    }
    coins = new Coin[initialCapacity];
    this.expansionsLeft = 2;
  }

  /**
   * Creates a new elastic bank object with an initial capacity equal to 10.
   */
  public ExceptionalBank() {
    coins = new Coin[10];
    this.expansionsLeft = 2;
  }

  /**
   * Returns the capacity of this elastic bank.
   * 
   * @return The capacity of this elastic bank
   */
  public int capacity() {
    return coins.length;
  }

  /**
   * Returns the expansions left for this elastic bank.
   * 
   * @return The expansions left for this elastic bank
   */
  public int getExpansions() {
    return this.expansionsLeft;
  }

  /**
   * Returns the number of coins held in this elastic bank.
   * 
   * @return The size of this elastic bank
   */
  public int getSize() {
    return this.size;
  }

  /**
   * Returns the value in cents of coins held in this elastic bank
   * 
   * @return the balance of this elastic bank
   */
  public int getBalance() {
    int balance = 0;
    // add the value of each coin held in this bank to balance, then return it
    for (int i = 0; i < size; i++) {
      balance += coins[i].value();
    }
    return balance;
  }

  /**
   * Returns the number of coins with a specific coinName held in this bank. The coin name
   * comparison is case insensitive.
   * 
   * @param coinName Name of a coin
   * @return the count of coins having the provided coinName, held in this bank
   */
  public int getSpecificCoinCount(String coinName) {
    int count = 0;
    for (int i = 0; i < size; i++) {
      if (coins[i].name().equalsIgnoreCase(coinName))
        count++;
    }
    return count;
  }

  /**
   * Returns a string representation of all the coins held in this elastic bank. Each coin is
   * represented by the pair "(name, value)", and the string representation should contain all of
   * these pairs in one space-separated line. For example: "(PENNY, 1) (QUARTER, 25) (PENNY, 1)
   * (DIME, 10) (NICKEL, 5)"
   * 
   * @return a String representation of the contents of the bank.
   */
  public String getCoins() {
    String contents = "";
    // Traverse the coins oversize array and add each coin's string representation to the string to
    // be returned
    for (int i = 0; i < size; i++) {
      contents += "(" + coins[i].name() + ", " + coins[i].value() + ")";
      if (i < size - 1)
        contents += " ";
    }
    return contents;
  }

  /**
   * Returns a summary of this bank contents.
   * 
   * @return An empty string if this bank is empty, and a string representation of the summary of
   *         this bank otherwise. The summary of the bank is a set of lines. Each line is formatted
   *         as follows "coin_name:coin_count"
   */
  public String getSummary() {
    String summary = "";
    Coin[] values = Coin.values();
    // Traverse this bank contents and update its summary
    for (int i = 0; i < values.length; i++) {
      String name = values[i].name();
      int count = getSpecificCoinCount(name);
      if (count != 0) {
        summary += name + ":" + count + "\n";
      }
    }
    return summary.trim(); // Remove whitespace (spaces, new lines etc.) from the beginning and end
                           // of summary and return the resulting string
  }

  /**
   * Removes and returns a coin at a random position from this elastic bank
   * 
   * @return the removed coin or null if this bank is empty
   * @throws NoSuchElementException if this bank is empty
   */
  public Coin removeCoin() throws NoSuchElementException {
    // If size is 0, throw NoSuchElementException
    if (size == 0) {
      throw new NoSuchElementException("WARNING! This bank is empty. Unable to remove a coin.");
    }
    int randPosition = rand.nextInt(size); // Get a random position from 0 .. size-1
    Coin removedCoin = coins[randPosition]; // Store the coin to be removed
    // The order of the coins within this bank (a piggy bank) is not important
    // so, move the coin at the end of the coins array to the random position
    // and set that last element to null.
    coins[randPosition] = coins[size - 1];
    coins[size - 1] = null;
    size--; // update size
    return removedCoin;
  }

  /**
   * Removes all the coins from this elastic bank
   */
  public void empty() {
    // Set all the non-null references within the coins array to null
    for (int i = 0; i < size; i++) {
      coins[i] = null;
    }
    // Set the size of this bank to 0
    size = 0;
  }

  /**
   * Adds a Coin to the bank and adjusts the capacity of coins if necessary and possible
   * 
   * @param c coin to be added to this elastic bank
   * @throws IllegalArgumentException if Coin c is null
   */
  public void addCoin(Coin c) throws IllegalArgumentException {
    // If Coin c is null, throw IllegalArgumentException
    if (c == null) {
      throw new IllegalArgumentException("WARNING! You cannot add a null reference to this bank.");
    }

    // Check if this bank is full
    if (size == coins.length) {
      // Check whether there are expansions left
      if (this.expansionsLeft > 0) {
        // Expand the capacity of this elastic bank by 10
        coins = Arrays.copyOf(coins, coins.length + 10);
        this.expansionsLeft--;
      } else { // No expansions left
        // Empty this elastic bank
        empty();
      }
    }
    // Add c at the end of this bank
    coins[size] = c;
    size++;
  }

  /**
   * Adds a number of the same coin type with respect to a provided command line. The format of the
   * command line is "coin_name:coins_count". Such command line refers to adding coins_count of
   * coin_name to this bank. For instance, "PENNY:5", or " Penny : 5 " refer to adding 5 pennies to
   * this bank. If the format of the provided command line is incorrect, no coins.
   * 
   * @param command Command line to add a number of coins of the same type to this bank.
   * @throws IllegalArgumentException if command is null
   * @throws DataFormatException      if the format of the provided command is incorrect
   * @throws NoSuchElementException   if coin_name argument within the provided command line does
   *                                  not refer to a valid coin name with respect to the constant
   *                                  names defined in the enum Coin
   */
  public void addCoins(String command) throws DataFormatException {
    // If command is null, throw IllegalArgumentException
    if (command == null) {
      throw new IllegalArgumentException(
          "WARNING! The addCoins() method does not accept a null reference as input.");
    }

    // If command does not contain a colon or negative sign, throw DataFormatException
    if (!command.contains(":") || command.contains("-")) {
      throw new DataFormatException("The format of the command line " + command + " is incorrect.");
    }

    // Separates both values
    String[] split = command.split(":");
    String coinName = split[0].trim(); // Name of coin
    String coinCount = split[1].trim(); // Times added to bank
    Scanner sc = new Scanner(coinCount); // Creates Scanner for coinValue

    // Checks if value of coin is not a number, if not throw DataFormatException
    if (!sc.hasNextInt()) {
      sc.close();
      throw new DataFormatException("The format of the command line " + command + " is incorrect.");
    }

    int count = sc.nextInt();
    sc.close();

    // If the coin name does not match any of the coin names in the enum, throw a
    // NoSuchElementException
    if (!(coinName.equalsIgnoreCase("PENNY") || coinName.equalsIgnoreCase("NICKEL")
        || coinName.equalsIgnoreCase("DIME") || coinName.equalsIgnoreCase("QUARTER"))) {
      throw new NoSuchElementException(
          "The coin name provided in the command line " + command + " is invalid.");
    }

    // Checks which coin is the requested coin, and adds the coin count amount of times.
    if (coinName.equalsIgnoreCase("PENNY")) { // Pennies
      for (int i = 0; i < count; i++) {
        addCoin(Coin.PENNY);
      }
    } else if (coinName.equalsIgnoreCase("NICKEL")) { // Nickels
      for (int i = 0; i < count; i++) {
        addCoin(Coin.NICKEL);
      }
    } else if (coinName.equalsIgnoreCase("DIME")) { // Dimes
      for (int i = 0; i < count; i++) {
        addCoin(Coin.DIME);
      }
    } else if (coinName.equalsIgnoreCase("QUARTER")) { // Quarters
      for (int i = 0; i < count; i++) {
        addCoin(Coin.QUARTER);
      }
    }
  }

  /**
   * Load a list of coins from a file object which refers to a data file written in a specific
   * format (a set of lines each formatted as follows "coin_name:coin_count"). Lines formatted
   * correctly will be added as new coins to this elastic bank. Lines formatted incorrectly must be
   * skipped (go to the next line). This method prints/displays the following message for every
   * skipped line "WARNING! Skipping line. " + "raison_of_the error".
   * 
   * @param file file object which refers to a data file of coin_names and their counts.
   * @throws FileNotFoundException if file is not found
   * @throws NullPointerException  if file is null
   */
  public void loadCoins​(File file) throws FileNotFoundException {
    // Throws NullPointerException if the file is null.
    if (file == null) {
      throw new NullPointerException("The provided file is null.");
    }

    // Scanner throws FileNotFound if file is not found
    Scanner scnr = new Scanner(file);

    // Checks through whole file, adds or skips whether line is a valid String
    while (scnr.hasNextLine()) {
      String coin = scnr.nextLine(); // Reads the file's String
      Scanner line = new Scanner(coin); // Creates Scanner object to read the line

      if (!line.hasNext()) { // If the line is empty, skip
        System.out.println("WARNING! Skipping line. " + "The line is empty.");
        continue;
      } else if (!coin.contains(":") || coin.contains("-")) { // If the line does not have a colon
                                                              // or has a minus sign, skip
        System.out.println("WARNING! Skipping line. " + "Incorrect format.");
        continue;
      }

      String[] split = coin.split(":"); // Separates both values into an array
      String coinName = split[0].trim(); // Name of coin
      String coinCount = split[1].trim(); // Times added to bank
      Scanner sc = new Scanner(coinCount); // Creates Scanner for coinValue

      // Checks if value of coin is not a number, if not skip
      if (!sc.hasNextInt()) {
        sc.close();
        System.out.println("WARNING! Skipping line. " + "Incorrect format.");
        continue;
      }
      sc.close();

      // If the coin name does not match any of the coin names in the enum, skip.
      if (!(coinName.equalsIgnoreCase("PENNY") || coinName.equalsIgnoreCase("NICKEL")
          || coinName.equalsIgnoreCase("DIME") || coinName.equalsIgnoreCase("QUARTER"))) {
        System.out.println("WARNING! Skipping line. " + "Incorrect format.");
        continue;
      }

      // Adds coin to the bank
      try {
        addCoins(coin);
      } catch (DataFormatException e) {
        e.printStackTrace();
      }
    }
    scnr.close();
  }

  /**
   * Save the summary of this bank to the provided file in a specific format for each line:
   * coin_name:coin_count. For instance, if a bank contains 2 quarters, 1 dime, 5 nickels, and 10
   * pennies, its contents will be saved as follows: PENNY:10 NICKEL:5 DIME:1 QUARTER:2 Note that
   * the order of lines does not matter.
   * 
   * @param file File object where a summary of the contents of this bank will be saved
   */
  public void saveBankSummary​(File file) {
    // If file is null, throw NullPointerException
    if (file == null) {
      throw new NullPointerException("Problem: File is null.");
    }

    try {
      PrintWriter printWriter = new PrintWriter(file); // Creates PrintWriter
      String summary = getSummary(); // Gets the summary
      printWriter.print(summary); // Prints it to file
      printWriter.close();
    } catch (FileNotFoundException e) { // If file is not found
      System.out.println("Problem: File not found.");
    }
  }
}
