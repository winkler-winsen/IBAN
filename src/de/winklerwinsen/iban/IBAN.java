package de.winklerwinsen.iban;
import java.math.*;

/**
 * This Class is used to handle IBAN numbers and check them for validity.
 * Stand 04.04.2020
 * @see <a href="https://web.archive.org/web/20171220203336/http://www.europebanks.info/ibanguide.php#2>http://www.europebanks.info/ibanguide.php</a>
 * 
 * @author Lars Winkler lars@winkler-winsen.de
 *
 * @version 1.0
 * 
 */
public class IBAN {
	/** 
	 * Character offset to put (char)'A' to (byte)10
	 */
	private static final byte CHAR_OFFSET = 65 - 10;
	
	/**
	 * Used for modulo operation
	 */
	private static final BigInteger BIG_INT_97 = new BigInteger("97");
	
	/**
	 * String containig IBAN 
	 */
	private String iban;
		
	/**
	 * Don't use this constructor
	 */
	@SuppressWarnings("unused")
	private IBAN() {
	}
	
	/**
	 * Constructor with parameter String newIBAN
	 * 
	 * @param newIBAN
	 */
	IBAN(String newIBAN) {
		setIBAN(newIBAN);
	}
	
	/**
	 * Takes String and returns without spaces ' '
	 * Can be used without object.
	 * 
	 * @param newIBAN
	 * @return newIBAN without spaces ' '
	 */
	static String removeSpaces(String newIBAN) {
		String newIBANnoSpaces = "";
		for (int i = 0; i < newIBAN.length(); i++) {
			if (newIBAN.charAt(i) != ' ') {
				newIBANnoSpaces = newIBANnoSpaces + newIBAN.charAt(i);
			}
		} 
		return newIBANnoSpaces;
	}
	
	/**
	 * Takes String and returns without spaces ' '
	 * @see #removeSpaces(String)
	 * 
	 * @param newIBAN
	 * @return newIBAN without spaces ' '
	 */
	String removeSpaces() {
		return removeSpaces(this.iban);
	}
	
	void setIBAN(String newIBAN) {
		this.iban = removeSpaces(newIBAN);
	}
	
	String getIBAN() {
		return this.iban;
	}
	
	/**
	 * Return IBAN in printable and human readable format
	 * @see #getQuads(String)
	 * 
	 * @return format with spaces between 4 characters
	 */
	String getQuads() {
		return getQuads(this.iban);
	}

	/**
	 * Return IBAN in printable and human readable format
	 * Can be used without object.
	 * 
	 * @param iban
	 * @return format with spaces between 4 characters
	 */
	static String getQuads(String iban) {
		String withSpaces = "";
		
		for (int i = 0; i < iban.length(); i++) {
			if (((i+1) % 4) == 0) {
				withSpaces = withSpaces + iban.charAt(i) + " ";
			} else {
				withSpaces = withSpaces + iban.charAt(i);
			}
		}
		
		return withSpaces;
	}
	
	/**
	 * Clears IBAN is invalid
	 * @see #isValid()
	 */
	void clearIfInvalid() {
		if (this.isValid() == false) {
			this.iban = "";
		}
	}

	/**
	 * Checks if IBAN check digits are correct.
	 * Can be used without object.
	 * 
	 * @param checkIBAN
	 * @return validity of checkIBAN
	 */
	static boolean isValid(String checkIBAN) {
		String umgestellt = "";

		umgestellt = checkIBAN.substring(4, checkIBAN.length());
		umgestellt += ((byte)checkIBAN.charAt(0) - CHAR_OFFSET);
		umgestellt += (byte)(checkIBAN.charAt(1) - CHAR_OFFSET);
		umgestellt += "00";
		
		BigInteger foo = new BigInteger(umgestellt);
		int pruefzifferBerechnet = (98 - foo.mod(BIG_INT_97).intValue());
		if (pruefzifferBerechnet == Integer.parseInt(checkIBAN.substring(2, 4))) {
			return true;
		} else {
    		return false;
		}
	}	
	
	/**
	 * Checks if IBAN check digits are correct.
	 * @see #isValid(String)
	 * 
	 * @return validity of IBAN
	 */
	boolean isValid() {
		return isValid(this.iban);
	}
	
	@Override
	public String toString() {
		return this.iban; 
	}
	
}
