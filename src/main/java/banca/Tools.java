import java.util.Scanner;

import java.util.Vector;

/**
 * Classe che mette a disposizione una serie di metodi utili in vari scenari
 */

public class Tools {
	
	/**
	 * Converte tutte le virgole di una stringa in punti
	 * @param s : vecchia stringa
	 * @return : stringa modificata
	 */
	
	public static String virgolaPunto(String s) {
		String nuova = "";

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ',') {
				nuova = nuova + '.';
			} else {
				nuova = nuova + s.charAt(i);
			}
		}

		return nuova;
	}
	
	/**
	 * Arrotonda un double alla seconda cifra decimale
	 * @param d : vecchio valore
	 * @return : valore formattato
	 */
	
	public static double arrotondaSecondaCifra(double d) {
		d = Math.round(d * 100);
		d = d / 100;
		return d;
	}
	
	/**
	 * Permette di acquisire un valore intero da tastiera con la gestione di eventuali eccezioni
	 * @param input : Stringa da convertire
	 * @return : valore numerico 
	 */
	
	public static int conversioneInt(String input) {
		Scanner tastiera = new Scanner(System.in);
        int toInt = -1;
        boolean ok;
        do {
            ok = true;
            try {
                toInt = (int) Integer.parseInt(input);
                if (toInt <= 0) {
                    System.out.println("Il numero deve essere maggiore di 0");
                    ok = false;
                    System.out.print("Reinserisci: ");
                    input = tastiera.nextLine();
                }
            } catch (NumberFormatException e) {
                System.out.println("Formato non valido");
                System.out.print("Reinserisci: ");
                ok = false;
                input = tastiera.nextLine();
            }
        } while (!ok);
        
        
        return toInt;
    }
	
	/**
	 * Permette di acquisire un valore double da tastiera con la gestione di eventuali eccezioni
	 * @param input : Stringa da convertire
	 * @return : valore numerico 
	 */
	
	 public static double conversioneDouble(String input) {
		 Scanner tastiera = new Scanner(System.in);
	        double toDouble = -1;
	        boolean ok;
	        do {
	            ok = true;
	            try {
	                toDouble = (double) Double.parseDouble(input);
	                if (toDouble < 0) {
	                    System.out.println("Il numero deve essere maggiore o uguale di 0");
	                    ok = false;
	                    System.out.print("Reinserisci: ");
	                    input = tastiera.nextLine();
	                }
	            } catch (NumberFormatException e) {
	                System.out.println("Formato non valido");
	                System.out.print("Reinserisci: ");
	                ok = false;
	                input = tastiera.nextLine();
	            }
	        } while (!ok);
	        

	        return toDouble;
	    }
	
}
