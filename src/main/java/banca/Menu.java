

import java.util.Scanner;

/**
 * Classe per la gestione di scelte di eventuali menu'
 */

public class Menu {

	private int sceltaMain, sceltaDurata, sceltaRischio, sceltaDeposito, sceltaAccesso;

	/**
	 * Imposta una nuova scelta Main
	 * @param s : nuova scelta
	 */
	
	public void setSceltaMain(int s) {
		this.sceltaMain=s;
	}
	
	/**
	 * Imposta una nuova scelta Durata
	 * @param s : nuova scelta
	 */

	public void setSceltaDurata(int s) {
		this.sceltaDurata=s;
	}
	
	/**
	 * Imposta una nuova scelta Rischio
	 * @param s : nuova scelta
	 */

	public void setSceltaRischio(int s) {
		this.sceltaRischio=s;
	}
	
	/**
	 * Imposta una nuova scelta Deposito
	 * @param s : nuova scelta
	 */

	public void setSceltaDeposito(int s) {
		this.sceltaDeposito=s;
	}
	
	/**
	 * Imposta una nuova scelta Accesso
	 * @param s : nuova scelta
	 */
	
	public void setSceltaAccesso(int s) {
		this.sceltaAccesso=s;
	}
	
	/**
	 * 
	 * @return : scelta Main
	 */
	
	public int getSceltaMain() {
		return this.sceltaMain;
	}
	
	/**
	 * 
	 * @return : scelta Durata
	 */
	
	public int getSceltaDurata() {
		return this.sceltaDurata;
	}
	
	/**
	 * 
	 * @return : scelta Rischio
	 */

	public int getSceltaRischio() {
		
		return this.sceltaRischio;
		
	}
	
	/**
	 * 
	 * @return : scelta Deposito
	 */
	
	public int getSceltaDeposito() {
		return this.sceltaDeposito;
	}
	
	/**
	 * 
	 * @return : scelta Accesso
	 */
	
	public int getSceltaAccesso() {
		return this.sceltaAccesso;
	}
	
	

}
