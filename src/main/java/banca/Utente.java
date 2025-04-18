
public class Utente {

	private double contoBancario, portafoglio;
	private int mese;
	private String nome, password;
	private int numero;
	
	public Utente(String n, String pass,double cB, double p,int m, int numb) {
		
		this.nome=n;
		this.password=pass;
		this.contoBancario=cB;
		this.portafoglio=p;
		this.mese=m;
		this.numero=numb;
		
	}
	
	public int getNumero() {
		return this.numero;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public double getContoBancario() {
		return this.contoBancario;
	}
	
	public double getPortafoglio() {
		return this.portafoglio;
	}
	
	public int getMese() {
		return this.mese;
	}
	
	public void setNome(String n) {
		this.nome=n;
	}
	
	public void setPassword(String p) {
		this.password=p;
	}
	
	public void setContoBancario(double cB) {
		this.contoBancario=cB;
	}
	
	public void setPortafoglio(double p) {
		this.portafoglio=p;
	}
	
	public void setMese(int m) {
		this.mese=m;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
