import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;


/**
 * Classe per la gestione di un'intera banca e dei suoi utenti
 */
public class Banca {

	private int nUtenti = 0;
	private File numbUtenti;

	private Vector<File> utenti = new Vector<File>();
	private Vector<FileWriter> storico = new Vector<FileWriter>();
	private Vector<Utente> users = new Vector<Utente>();
	
	/**
	 * Costruttore della banca
	 * @throws IOException
	 */
	
	public Banca() throws IOException {
		this.numbUtenti = new File("nUtenti.txt");
		
		if(!numbUtenti.exists()) {
			numbUtenti.createNewFile();
			PrintWriter pw = new PrintWriter(numbUtenti);
			pw.println(""+this.nUtenti);
			pw.close();
		}else {
			Scanner input = new Scanner(numbUtenti);
			this.nUtenti = (int)Integer.parseInt(input.next());
			input.close();
		}
		
		for(int i=0;i<this.nUtenti;i++) {
			utenti.add(new File("utente"+(i+1)+".txt"));
		}
		
		for(int i=0;i<this.nUtenti;i++) {
			storico.add(new FileWriter("storico"+(i+1)+".txt",true));
		}
		
		
		assignment();
	}
	
	/**
	 * 
	 * @return : il numero attuale di utenti
	 */
	
	public int getNUtenti() {
		return this.nUtenti;
	}
	
	/**
	 * Scrive sull'apposito file il nuovo numero di utenti
	 * @param nuovo : nuovo numero di utenti
	 * @throws FileNotFoundException
	 */
	
	public void setNUtenti(int nuovo) throws FileNotFoundException {
		this.nUtenti=nuovo;
		PrintWriter pw = new PrintWriter(numbUtenti);
		pw.print(""+nUtenti);
		pw.close();
	}
	
	/**
	 * Serve ad ottenere i valori, da utilizzare nel codice, dai rispettivi file degli utenti
	 * @throws FileNotFoundException
	 */
	
	
	private void assignment() throws FileNotFoundException {

		Scanner input;
		for(int i=0;i<nUtenti;i++) {
			users.add(i,new Utente("-","-",0,0,0,i));
		}

		for (int i = 0; i < nUtenti; i++) {
			input = new Scanner(utenti.elementAt(i));
			System.out.println(nUtenti);
			String riga;
			
			while (input.hasNextLine()) {
				riga = input.nextLine();
				Scanner t = new Scanner(riga);
				users.elementAt(i).setNome(t.next());
				users.elementAt(i).setPassword(t.next());
				users.elementAt(i).setContoBancario((double)(Double.parseDouble(Tools.virgolaPunto(t.next()))));
				users.elementAt(i).setPortafoglio((double)(Double.parseDouble(Tools.virgolaPunto(t.next()))));
				users.elementAt(i).setMese((int)(Integer.parseInt(t.next())));
				t.close();
			}

			input.close();
		}
		
		
	}
	
	/**
	 * Successivamente alla registrazione, i dati dell'utente vengono salvati in un nuovo file
	 * @param u : l'oggetto utente contenente i suoi dati
	 * @throws IOException
	 */

	private void creaUtente(Utente u) throws IOException {
		users.add(u);
		File nuovo = new File("utente"+(this.nUtenti+1)+".txt");
		
		nuovo.createNewFile();
		
		PrintWriter pw = new PrintWriter(nuovo);
		
		String s="";
		s = u.getNome();
		s = Tools.virgolaPunto(s);
		pw.print(s+" ");
		
		s = u.getPassword();
		s = Tools.virgolaPunto(s);
		pw.print(s+" ");
		
		s = u.getContoBancario()+"";
		s = Tools.virgolaPunto(s);
		pw.print(s+" ");
		
		s = u.getPortafoglio()+"";
		s = Tools.virgolaPunto(s);
		pw.print(s+" ");
		
		s = u.getMese()+"";
		s = Tools.virgolaPunto(s);
		pw.print(u.getMese()+" ");
		
		
		utenti.add(nuovo);
		
		
		setNUtenti(nUtenti+1);
		
		storico.add(new FileWriter("storico"+nUtenti+".txt",true));
		pw.close();
		
	}
	
	/**
	 * Permette di verificare la disponibilita' del deposito
	 * @param portafoglio : portafoglio disponibile
	 * @param saldo : soldi da depositare
	 * @return : true se si puo' depositare, in caso contrario false
	 */
	
	
	public static boolean deposita(double portafoglio, double saldo) {

		if (saldo > portafoglio)
			return false;

		return true;
	}
	
	/**
	 * Permette di verificare la disponibilita' del prelievo
	 * @param contoBancario : conto disponibile
	 * @param saldo : soldi da depositare
	 * @return : true se si puo' prelevare, in caso contrario false
	 */

	public static boolean preleva(double contoBancario, double saldo) {

		if (saldo > contoBancario)
			return false;

		return true;
	}
	
	/**
	 * Avvia l'investimento
	 * @param grandezzaRischio : la grandezza del rischio
	 * @param soldiDaInvestire : i soldi che si vogliono investire
	 * @return : il guadagno ottenuto dall'investimento
	 */

	public static double investimento(int grandezzaRischio, double soldiDaInvestire) {
		double guadagno;
		int rischio = (int) (Math.random() * grandezzaRischio);
		if (rischio <= 50) {
			guadagno = (soldiDaInvestire * (grandezzaRischio / 20.0));
		} else {
			guadagno = (double) ((20.0 / grandezzaRischio) * soldiDaInvestire);
		}

		return guadagno;
	}
	
	/**
	 * Salva una qualsiasi transazione di denaro sullo storico dell'utente
	 * @param message : il messaggio di notifica
	 * @param index : il numero identificativo dell'utente
	 */

	public void transazione(String message, int index) {

		PrintWriter pw = new PrintWriter(storico.elementAt(index), true);

		pw.println(message);
	}

	/**
	 * Permette la registrazione di un nuovo utente e l'inserimento dei suoi dati nella banca
	 * @param nome : il nome del nuovo utente
	 * @param password  : la password del nuovo utente
	 * @return true se la registrazione e' andata a buon fine, in caso contrario false;
	 * @throws IOException
	 */
	
	public boolean registrazione(String nome, String password) throws IOException {
		
		int pos=0;
		boolean ok=true;
		for(int i=0;i<users.size() && ok;i++) {
			if(users.elementAt(i).getNome().equalsIgnoreCase(nome)) {
				ok=false;
			}else {
				if(users.elementAt(i).getPassword().equalsIgnoreCase(password)) {
					ok=false;
				}else {
					pos=i;
				}
			}
		}
		
		if(ok) {
			creaUtente(new Utente(nome,password,1000,0,0,pos));
		}
		
		return ok;
	}
	
	/**
	 * Permette ad un utente registrato di accedere al proprio account
	 * @param nome : il nome dell'utente
	 * @param password : la password dell'utente
	 * @return : l'Utente trovato se e' registrato, null se e' assente
	 */
	
	public Utente login(String nome, String password) {
		for(int i=0;i<users.size();i++) {
			if(users.elementAt(i).getNome().equalsIgnoreCase(nome)) {
				if(users.elementAt(i).getPassword().equalsIgnoreCase(password)) {
					return users.elementAt(i);
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Salva i dati dell'utente sul suo file dopo essere stati modificati nell'uso dell'applicativo
	 * @param u : l'utente da salvare
	 * @throws FileNotFoundException
	 */

	public void salvaUtente(Utente u) throws FileNotFoundException {

		PrintWriter pw = new PrintWriter(utenti.elementAt(u.getNumero()));
		String s = u.getNome();
		s = Tools.virgolaPunto(s).trim();
		System.out.println(s);
		pw.print(s + " ");

		s = u.getPassword();
		s = Tools.virgolaPunto(s).trim();
		System.out.println(s);
		pw.print(s + " ");

		s = ("" + u.getContoBancario()).trim();
		s = Tools.virgolaPunto(s);
		System.out.println(s);
		pw.print(s + " ");

		s = ("" + u.getPortafoglio()).trim();
		s = Tools.virgolaPunto(s);
		System.out.println(s);
		pw.print(s + " ");

		s = ("" + u.getMese()).trim();
		s = Tools.virgolaPunto(s);
		System.out.println(s);
		pw.print(s + " ");
		
		
		
		

		pw.close();
	}

}
