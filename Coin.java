/**
 * This enum represents an enumeration of the US Currency Coin constants
 * 
 * @author Mouna
 *
 */
public enum Coin {
  PENNY(1), NICKEL(5), DIME(10), QUARTER(25);

  private final int value; // value of this coins

  /**
   * The constructor for an enum type must be package-private or private access. This private
   * constructor is only used to create the constants that are defined at the beginning of this
   * enumeration's body.
   * 
   * @param value value in cents of this coin
   */
  private Coin(int value) {
    this.value = value;
  }

  /**
   * Returns the value in cents of this coin
   * 
   * @return the value of this coin
   */
  public int value() {
    return value;
  }

}
