//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Exceptional Bank Tester
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
 * This file contains all the tests for all methods from the ElasticBank.java file. Tests the
 * following methods/functions from the ExceptionalBank class: 
 * 
 * - testExceptionalBankConstructor()
 * - testGoodExceptionalBankConstructor()
 * - testAddCoin()
 * - testRemoveCoinEmpty()
 * - testAddCoinsIllegalArgument()
 * - testAddCoinsInvalidDataFormat()
 * - testAddCoinsNoSuchElement()
 * - testAddCoinsValidFormat()
 * - testLoadCoinsFileFound()
 * - testLoadCoinsFileNotFound()
 * - testLoadCoinsNullReference()
 * - testSaveBankSummary()
 * 
 * @author Matt Thompson
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;

public class ExceptionalBankTester {
  /**
   * This method checks whether the ExceptionalBank constructor throws an IllegalArgumentException
   * with appropriate error message, when it is passed a zero or a negative capacity. This test must
   * fail if another kind of exception is thrown for such test scenario.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testExceptionalBankConstructor() {
    try {
      // Create an exceptional bank with a negative capacity
      ExceptionalBank bank = new ExceptionalBank(-10);
      System.out
          .println("Problem detected. The constructor call of the ExceptionalBank class did not "
              + "throw an IllegalArgumentException when it is passed a negative capacity.");
      return false; // Return false if no exception has been thrown
    } catch (IllegalArgumentException e1) {
      // Check that the caught IllegalArgumentException includes
      // An appropriate error message
      if (e1.getMessage() == null // Your test method should not throw
          // a NullPointerException, but must return false if e1.getMessage is null
          || !e1.getMessage().toLowerCase().contains("must be a non-zero positive integer")) {
        System.out
            .println("Problem detected. The IllegalArgumentException thrown by the constructor "
                + "call of the ExceptionalBank class when it is passed a negative capacity "
                + "   does not contain an appropriate error message.");
        return false;
      }
    } catch (Exception e2) {
      // An exception other than IllegalArgumentException has been thrown
      System.out
          .println("Problem detected. An unexpected exception has been thrown when calling the "
              + "constructor of the ExceptionBank class with a negative argument. "
              + "An IllegalArgumentException was expected to be thrown. "
              + "But, it was NOT the case.");
      e2.printStackTrace(); // To help locate the error within the bad ExceptionalBank
      // constructor code.
      return false;
    }
    return true; // Test passed
  }

  /**
   * This method checks whether the ExceptionalBank constructor creates without errors an empty
   * exceptional bank with capacity 20 when it is passed 20 as input parameter.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGoodExceptionalBankConstructor() {
    try {
      // Create an exceptional bank with capacity 20
      ExceptionalBank bank = new ExceptionalBank(20);

      if (bank.getSize() == 20) {
        return true; // Test passed
      }
    } catch (IllegalArgumentException e1) {
      // Check that the caught IllegalArgumentException includes
      // an appropriate error message
      if (e1.getMessage() == null
          || !e1.getMessage().toLowerCase().contains("must be a non-zero positive integer")) {
        System.out
            .println("Problem detected. The IllegalArgumentException thrown by the constructor "
                + "call of the ExceptionalBank class when it is passed a negative capacity "
                + "   does not contain an appropriate error message.");
        return false;
      }
    } catch (Exception e2) {
      // An exception other than IllegalArgumentException has been thrown
      System.out
          .println("Problem detected. An unexpected exception has been thrown when calling the "
              + "constructor of the ExceptionBank class with a negative argument. "
              + "An IllegalArgumentException was expected to be thrown. "
              + "But, it was NOT the case.");
      e2.printStackTrace(); // To help locate the error within the bad ExceptionalBank
      // Constructor code.
      return false;
    }
    return true; // Test passed
  }

  /**
   * This method checks whether the ExceptionalBank.addCoin() method throws an
   * IllegalArgumentException with an appropriate error message, when it is passed a null reference.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddCoin() {
    try {
      ExceptionalBank bank = new ExceptionalBank(20); // Creates bank
      bank.addCoin(null);
      System.out.println("Problem: Added null as a coin.");
      return false; // Return false if no exception was caught
    } catch (IllegalArgumentException e1) {
      // Checks if has correct error message
      if (!e1.getMessage().toLowerCase().contains("cannot add a null reference to this bank")) {
        System.out.println("Problem: Caught exception, but wrong error message.");
        return false;
      }
    }
    return true; // Test passed
  }

  /**
   * This method checks whether the ExceptionalBank.removeCoin() method throws a
   * NoSuchElementException with an appropriate error message, when it is called on an empty bank.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRemoveCoinEmptyBank() {
    try {
      ExceptionalBank bank = new ExceptionalBank(20); // Creates bank
      bank.removeCoin();
      System.out.println("Problem: Removed a coin in an empty bank.");
      return false; // Return false if no exception was caught
    } catch (NoSuchElementException e1) {
      // Checks if has correct error message
      if (!e1.getMessage().toLowerCase().contains("to remove a coin.")) {
        System.out.println("Problem: Caught exception, but wrong error message.");
        return false;
      }
    }
    return true; // Test passed
  }

  /**
   * This method checks whether ExceptionalBank.addCoins() method throws an IllegalArgumentException
   * with an appropriate error message when it is passed a null reference as input argument.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddCoinsIllegalArgument() {
    try {
      ExceptionalBank bank = new ExceptionalBank(5); // Creates bank
      bank.addCoins(null);
      System.out.println("Problem: Passed null as an input.");
      return false; // Return false if no exception was caught
    } catch (IllegalArgumentException e1) {
      // Checks if has correct error message
      if (!e1.getMessage().toLowerCase()
          .contains("method does not accept a null reference as input.")) {
        System.out.println("Problem: Caught exception, but wrong error message.");
        return false;
      }
    } catch (DataFormatException e2) { // Adds catch block for DataFormatException, although will
                                       // never reach if null.
      return false;
    }
    return true; // Test passed
  }

  /**
   * This method checks whether ExceptionalBank.addCoins() method throws a DataFormatException with
   * an appropriate error message when it is passed an incorrectly formatted string object, for
   * instance "quarter: five", or ": 6", or "DIME:-5"
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddCoinsInvalidDataFormat() {
    // Test 1: "quarter: five"
    try {
      ExceptionalBank bank = new ExceptionalBank(5); // Creates bank
      bank.addCoins("quarter: five");
      System.out.println("Problem: Passed command as an input.");
      return false; // Return false if no exception was caught
    } catch (DataFormatException e1) {
      // Checks if has correct error message
      if (!e1.getMessage().toLowerCase().contains("format of the command line")) {
        System.out.println("Problem: Caught exception, but wrong error message.");
        return false;
      }
    }

    // Test 2: "6 dime"
    try {
      ExceptionalBank bank = new ExceptionalBank(5); // Creates bank
      bank.addCoins("6 dime");
      System.out.println("Problem: Passed command as an input.");
      return false; // Return false if no exception was caught
    } catch (DataFormatException e1) {
      // Checks if has correct error message
      if (!e1.getMessage().toLowerCase().contains("format of the command line")) {
        System.out.println("Problem: Caught exception, but wrong error message.");
        return false;
      }
    }

    // Test 3: "DIME:-5"
    try {
      ExceptionalBank bank = new ExceptionalBank(5); // Creates bank
      bank.addCoins("DIME:-5");
      System.out.println("Problem: Passed command as an input.");
      return false; // Return false if no exception was caught
    } catch (DataFormatException e1) {
      // Checks if has correct error message
      if (!e1.getMessage().toLowerCase().contains("format of the command line")) {
        System.out.println("Problem: Caught exception, but wrong error message.");
        return false;
      }
    }
    return true; // Test passed
  }

  /**
   * This method checks whether ExceptionalBank.addCoins() method throws a NoSuchElementException
   * with an appropriate error message when it is passed a String object with a correct format
   * (meaning "string:positive_number"), but with a coin name not defined in the enum Coin's
   * constants.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddCoinsNoSuchElement() {
    try {
      ExceptionalBank bank = new ExceptionalBank(5); // Creates bank
      bank.addCoins("DOLLAR:5");
      System.out.println("Problem: Passed DOLLAR as an input.");
      return false; // Return false if no exception was caught
    } catch (NoSuchElementException e1) {
      // Checks if has correct error message
      if (!e1.getMessage().toLowerCase().contains("coin name provided in the command line")) {
        System.out.println("Problem: Caught exception, but wrong error message.");
        return false;
      }
    } catch (DataFormatException e2) { // Adds catch block for DataFormatException, although will
                                       // never reach if NoSuchElement is caught.
      return false;
    }
    return true; // Test passed
  }

  /**
   * This method checks whether the ExceptionalBank.addCoins() works appropriately when it is passed
   * a String with a valid format.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddCoinsValidFormat() {
    try {
      ExceptionalBank bank = new ExceptionalBank(5); // Creates bank
      bank.addCoins(" dime: 5 ");
      return true; // Test Passed
    } catch (NoSuchElementException e1) { // NoSuchElementException
      System.out.println("Problem: Caught a NoSuchElementException when it should have passed.");
      return false;
    } catch (DataFormatException e2) { // DataFormatException
      System.out.println("Problem: Caught a DataFormatException when it should have passed.");
      return false;
    } catch (IllegalArgumentException e3) { // IllegalArgumentException
      System.out.println("Problem: Caught a IllegalArgumentException when it should have passed.");
      return false;
    }
  }

  /**
   * This method checks whether ExceptionalBank.loadCoins() method loads appropriately without
   * throwing any exception when it is passed a found file.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLoadCoinsFileFound() {
    ExceptionalBank bank = new ExceptionalBank(10); // Creates bank
    File file = new File("sampleText.txt"); // Creates new file with Strings to put in
    try {
      bank.loadCoins​(file);
    } catch (FileNotFoundException e1) { // FileNotFoundException
      System.out.println("Problem: FileNotFoundException thrown.");
      return false;
    } catch (NullPointerException e2) { // NullPointerException
      System.out.println("Problem: NullPointerException thrown.");
      return false;
    }
    return true; // Test passed
  }

  /**
   * This method checks whether ExceptionalBank.loadCoins() method throws a NullPointerException
   * when it is passed a null reference.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLoadCoinsNullReference() {
    ExceptionalBank bank = new ExceptionalBank(10); // Creates bank
    File file = null; // File becomes null
    try {
      bank.loadCoins​(file);
      System.out.println("Problem: Passed with a null file");
    } catch (NullPointerException e1) { // NullPointerException
      return true;
    } catch (FileNotFoundException e2) {
      System.out.println("Problem: Treated as a FileNotFoundException");
      return false;
    }
    return false; // Test passed
  }

  /**
   * This method checks whether ExceptionalBank.loadCoins() method throws a FileNotFoundException
   * when it is passed a non found file.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLoadCoinsFileNotFound() {
    ExceptionalBank bank = new ExceptionalBank(10); // Creates bank
    File file = new File("noFileFound.txt"); // Assigns a non existent file
    try {
      bank.loadCoins​(file);
      System.out.println("Problem: Passed with a non existing file");
    } catch (FileNotFoundException e1) { // FileNotFoundException
      return true;
    }
    return false; // Test passed
  }

  /**
   * This method checks whether ExceptionalBank.saveBankSummary() method loads appropriately without
   * throwing any exception when it is passed a found file.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testSaveBankSummary() {
    ExceptionalBank bank = new ExceptionalBank(10); // Creates bank
    File file = new File("writer.txt"); // Assigns the file to write in
    try {
      // Adds coins, calls saveBankSummary to test
      bank.addCoin(Coin.DIME);
      bank.addCoin(Coin.QUARTER);
      bank.saveBankSummary​(file);
    } catch (NullPointerException e1) { // NullPointerException
      System.out.println("Problem: NullPointerException thrown.");
      return false;
    }
    return true; // Test passed
  }

  public static void main(String[] args) {
    System.out.println("testExceptionalBankConstructor(): " + testExceptionalBankConstructor());
    System.out
        .println("testGoodExceptionalBankConstructor(): " + testGoodExceptionalBankConstructor());
    System.out.println("testAddCoin(): " + testAddCoin());
    System.out.println("testRemoveCoinEmpty(): " + testRemoveCoinEmptyBank());
    System.out.println("testAddCoinsIllegalArgument(): " + testAddCoinsIllegalArgument());
    System.out.println("testAddCoinsInvalidDataFormat(): " + testAddCoinsInvalidDataFormat());
    System.out.println("testAddCoinsNoSuchElement(): " + testAddCoinsNoSuchElement());
    System.out.println("testAddCoinsValidFormat(): " + testAddCoinsValidFormat());
    System.out.println("testLoadCoinsFileFound(): " + testLoadCoinsFileFound());
    System.out.println("testLoadCoinsFileNotFound(): " + testLoadCoinsFileNotFound());
    System.out.println("testLoadCoinsNullReference(): " + testLoadCoinsNullReference());
    System.out.println("testSaveBankSummary(): " + testSaveBankSummary());
  }
}
