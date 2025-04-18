
/**
 * Classe che crea l'oggetto rappresentante l'utente registrato
 */


public class Utente {

	private double contoBancario, portafoglio;
	private int mese;
	private String nome, password;
	private int numero;
	
	/**
	 * Costruttore
	 * @param n : nome dell'utente
	 * @param pass : password dell'utente
	 * @param cB : conto bancario dell'utente
	 * @param p : password dell'utente
	 * @param m : mesi trascorsi dell'utente
	 * @param numb : numero identificativo dell'utente (e anche l'indice di posizione)
	 */
	
	public Utente(String n, String pass,double cB, double p,int m, int numb) {
		
		this.nome=n;
		this.password=pass;
		this.contoBancario=cB;
		this.portafoglio=p;
		this.mese=m;
		this.numero=numb;
		
	}
	
	/**
	 * 
	 * @return : restituisce il numero identificativo dell'utente
	 */
	
	public int getNumero() {
		return this.numero;
	}
	
	/**
	 * 
	 * @return : restituisce il nome dell'utente
	 */
	
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * 
	 * @return : restituisce la password dell'utente
	 */
	
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * 
	 * @return : restituisce il conto bancario dell'utente
	 */
	
	public double getContoBancario() {
		return this.contoBancario;
	}
	
	/**
	 * 
	 * @return : restituisce il portafoglio dell'utente
	 */
	
	public double getPortafoglio() {
		return this.portafoglio;
	}
	
	/**
	 * 
	 * @return : restituisce i mesi trascorsi dall'utente
	 */
	
	public int getMese() {
		return this.mese;
	}
	
	
	/**
	 * Permette di modificare il nome
	 * @param n : indica il nome nuovo
	 */
	public void setNome(String n) {
		this.nome=n;
	}
	
	/**
	 * Permette di modificare la password
	 * @param p : indica la password nuova
	 */
	
	public void setPassword(String p) {
		this.password=p;
	}
	
	/**
	 * Permette di modificare il conto bancario
	 * @param cB : indica il conto nuovo
	 */
	
	public void setContoBancario(double cB) {
		this.contoBancario=cB;
	}
	
	/**
	 * Permette di modificare il portafoglio
	 * @param p : indica il nuovo portafoglio
	 */
	
	public void setPortafoglio(double p) {
		this.portafoglio=p;
	}
	
	/**
	 * Permette di modificare il numero di mesi trascorsi
	 * @param m : indica il nuovo numero di mesi
	 */
	
	public void setMese(int m) {
		this.mese=m;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
