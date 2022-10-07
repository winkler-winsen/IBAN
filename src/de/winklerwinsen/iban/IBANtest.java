package de.winklerwinsen.iban;

/**
 * @author Lars Winkler
 *
 */
public class IBANtest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String parameter = "";
		for (int i = 0; i < args.length; i++) {
			parameter = parameter + args[i].toString();
		}
		System.out.println("Parameter: " + parameter);
		parameter = parameter + "";
		IBAN iban = new IBAN(parameter);

		System.out.println("IBAN: " + iban);

		if (iban.isValid()) {
			System.out.println("IBAN korrekt!");
		} else {
			System.out.println("IBAN fehlerhaft!");
		}
		System.out.println("IBAN Quads: " + iban.getQuads());

		iban.clearIfInvalid();

		System.out.println("IBAN wenn gültig: " + iban);

	}
}
