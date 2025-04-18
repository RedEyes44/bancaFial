import java.util.Scanner;
import java.util.Vector;

public class Tools {

	public static String trim(String x) {
		String s = "";
		for (int i = 0; i < x.length(); i++) {
			char c = x.charAt(i);
			if (c != ' ') {
				s = s + c;
			}
		}
		return s;
	}
	
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
	
	public static double arrotondaSecondaCifra(double d) {
		d = Math.round(d * 100);
		d = d / 100;
		return d;
	}
	
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
