import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Classe per l'utilizzo di una GUI e per l'avvio del metodo main
 */

public class BankGUI {
    private JFrame frame;
    private Menu menu;
    private Utente utente;
    private Banca banca;
    private double soldiDaInvestire=0;
    
    /**
     * Costruttore della classe
     * @throws IOException
     */
    
    public BankGUI() throws IOException {
        menu = new Menu();
        try {
			banca = new Banca();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        initLoginMenu();
    }
    
    /**
     * Schermata di login/registrazione
     */

    private void initLoginMenu() {
        frame = new JFrame("Accesso Utente");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        JTextField nomeField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Registrazione");

        panel.add(new JLabel("Nome Utente:"));
        panel.add(nomeField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);

        loginButton.addActionListener(e -> {
            menu.setSceltaAccesso(1);
            String nome = nomeField.getText();
            String password = new String(passwordField.getPassword());
            utente = banca.login(nome, password);
            if (utente != null) {
                JOptionPane.showMessageDialog(frame, "Login effettuato con successo!");
                showMainMenu();
            } else {
                JOptionPane.showMessageDialog(frame, "Login fallito. Controlla le credenziali.");
            }
        });

        registerButton.addActionListener(e -> {
            menu.setSceltaAccesso(2);
            String nome = nomeField.getText();
            String password = new String(passwordField.getPassword());
            try {
				if (banca.registrazione(nome, password)) {
				    JOptionPane.showMessageDialog(frame, "Registrazione completata!");
				    showMainMenu();
				} else {
				    JOptionPane.showMessageDialog(frame, "Registrazione fallita. Utente potrebbe esistere già.");
				}
			} catch (HeadlessException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });

        frame.add(panel);
        frame.setVisible(true);
    }
    
    /**
     * Menu' principale, che mette a disposizione le azioni che si possono compiere con il proprio account
     * 
     */

    private void showMainMenu() {
        frame.getContentPane().removeAll();
        frame.setTitle("Menu Principale");
        frame.setSize(400, 300);

        JPanel panel = new JPanel(new GridLayout(7, 1));
        String[] options = {
            "Depositare soldi",
            "Prelevare soldi",
            "Investire soldi",
            "Avanzare di n mesi",
            "Visualizzare conto",
            "Visualizzare portafoglio",
            "Uscire"
        };

        for (int i = 0; i < options.length; i++) {
            int choice = i + 1;
            JButton button = new JButton(options[i]);
            button.addActionListener(e -> {
				try {
					handleMainChoice(choice);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
            panel.add(button);
        }

        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }
    
    /**
     * Gestisce lo switch per selezionare l'operazione desiderata
     * @param choice : scelta dell'utente
     * @throws FileNotFoundException
     */

    private void handleMainChoice(int choice) throws FileNotFoundException {
        menu.setSceltaMain(choice);
        switch (choice) {
            case 1:
                showAmountInput("Inserisci importo da depositare", 1);
                break;
            case 2:
                showAmountInput("Inserisci importo da prelevare", 2);
                break;
            case 3:
            	showAmountInput("Inserisci il denaro da investire",3);
                showInvestmentMenu();
                break;
            case 4:
                advanceMonths();
                break;
            case 5:
            case 6:
                showStatus(choice);
                break;
            case 7:{
                frame.dispose();
                banca.salvaUtente(utente);
                break;
            }
        }
    }
    
    /**
     * Permette di inserire un valore e svolgere un operazione
     * @param title : titolo operazione
     * @param type : scelta operazione
     */ 

    private void showAmountInput(String title, int type) {
        
            if (type == 1) {
                String input = JOptionPane.showInputDialog(frame, title);
                double amount = Tools.conversioneDouble(input);
                
                while(!Banca.deposita(utente.getPortafoglio(),amount)) {
                	JOptionPane.showMessageDialog(frame, "Importo non valido.");
                	input = JOptionPane.showInputDialog(frame, title);
                    amount = Tools.conversioneDouble(input);
                }
                
                
                JOptionPane.showMessageDialog(frame, "Hai depositato " + amount + " euro.");
                
                String message = "Sono stati aggiunti " + amount + " euro al conto";
                utente.setContoBancario(utente.getContoBancario()+amount);
                banca.transazione(message, utente.getNumero());
                
                
                message = "Sono stati tolti " + amount + " euro dal portafoglio";
                utente.setPortafoglio(utente.getPortafoglio()-amount);
                banca.transazione(message, utente.getNumero());
                
            } else {
                String input = JOptionPane.showInputDialog(frame, title);
                double amount = Tools.conversioneDouble(input);
                
                while(!Banca.preleva(utente.getContoBancario(),amount)) {
                	JOptionPane.showMessageDialog(frame, "Importo non valido.");
                	input = JOptionPane.showInputDialog(frame, title);
                	amount = Tools.conversioneDouble(input);
                	
                }
                
                
                JOptionPane.showMessageDialog(frame, "Hai prelevato " + amount + " euro.");
                
                String message = "Sono stati tolti " + amount + " euro dal conto";
                utente.setContoBancario(utente.getContoBancario()-amount);
                banca.transazione(message, utente.getNumero());
                
                message = "Sono stati aggiunti " + amount + " euro al portafoglio";
                utente.setPortafoglio(utente.getPortafoglio()+amount);
                banca.transazione(message, utente.getNumero());
            }
        
            if(type == 3) {
            	String input = JOptionPane.showInputDialog(frame, title);
                double amount = Tools.conversioneDouble(input);
                
                while(amount<0 || amount>utente.getContoBancario()) {
                	JOptionPane.showMessageDialog(frame, "Importo non valido.");
                	input = JOptionPane.showInputDialog(frame, title);
                	amount = Tools.conversioneDouble(input);
                }
                
                utente.setContoBancario(utente.getContoBancario()-amount);
                String message = "Sono stati tolti " + amount + " euro dal conto";
                banca.transazione(message, utente.getNumero());
                soldiDaInvestire = amount;
            }
            
        }
    
    /**
     * Schermata investimenti
     */
    

    private void showInvestmentMenu() {
        String[] rischi = {"Basso", "Medio", "Alto"};
        String[] durate = {"Breve", "Media", "Lunga"};

        int rischio = JOptionPane.showOptionDialog(frame, "Scegli il rischio", "Rischio",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, rischi, rischi[0]);
        int durata = JOptionPane.showOptionDialog(frame, "Scegli la durata", "Durata",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, durate, durate[0]);
        
        System.out.println("Rischio : " + rischio);
        System.out.println("Durata : " + durata);
        
        menu.setSceltaRischio(rischio);
        menu.setSceltaDurata(durata);
        
        double guadagno=0;
        
        
        switch(menu.getSceltaRischio()) {
        
        case 0:{
        	guadagno = Banca.investimento(100, soldiDaInvestire);
        	break;
        }
        
        case 1:{
        	guadagno = Banca.investimento(250, soldiDaInvestire);
        	break;
        }
        
        case 2:{
        	guadagno = Banca.investimento(500, soldiDaInvestire);
        	break;
        }
        
        
        }
        
        switch(menu.getSceltaDurata()) {
        
        case 0:{
        	
        	utente.setMese(utente.getMese() + 1);

			utente.setContoBancario(Tools.arrotondaSecondaCifra(utente.getContoBancario() + 100));
			String message = "Sono stati aggiunti " + (100) + " al conto";
			banca.transazione(message, utente.getNumero());
			break;
        	
        }
        
        case 1:{
        	
        	utente.setMese(utente.getMese() + 3);

			utente.setContoBancario(Tools.arrotondaSecondaCifra(utente.getContoBancario() + (100*3)));
			String message = "Sono stati aggiunti " + (100*3) + " al conto";
			banca.transazione(message, utente.getNumero());
			break;
        	
        }
        
        case 2:{
        	utente.setMese(utente.getMese() + 6);
			utente.setContoBancario(Tools.arrotondaSecondaCifra(utente.getContoBancario() + (100 * 6)));
			String message = "Sono stati aggiunti " + (100 * 6) + " al conto";
			banca.transazione(message, utente.getNumero());
			break;
        }
        
        }
        
        utente.setContoBancario(Tools.arrotondaSecondaCifra(utente.getContoBancario() + guadagno));
        
        String message = "";
        
        if(guadagno<=soldiDaInvestire) {
        	JOptionPane.showMessageDialog(frame, "Hai perso "  + guadagno + " euro");
        	message = "Sono stati tolti " + guadagno + " euro dal conto";
        	banca.transazione(message, utente.getNumero());
        }else {
        	JOptionPane.showMessageDialog(frame,"Hai guadagnato " + guadagno + " euro");
        	message = "Sono stati aggiunti " + guadagno + " euro al conto";
        	banca.transazione(message, utente.getNumero());
        }
        
    }
    
    /**
     * Schermata avanzamento mesi
     */

    private void advanceMonths() {
        String input = JOptionPane.showInputDialog(frame, "Inserisci numero di mesi da avanzare:");
        int mesi = Tools.conversioneInt(input);
        if (mesi > 0) {
            int guadagno = mesi * 100;
            String[] opzioni = {"Portafoglio", "Conto"};
            int scelta = JOptionPane.showOptionDialog(frame, "Hai guadagnato " + guadagno + " euro. Dove vuoi metterli?",
                    "Guadagno", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opzioni, opzioni[0]);

            if (scelta == 0) {
            	String message = "Sono stati aggiunti " + guadagno + " euro al portafoglio";
                utente.setPortafoglio(utente.getPortafoglio()+guadagno);
                banca.transazione(message, utente.getNumero());
            } else {
            	String message = "Sono stati aggiunti " + guadagno + " euro al conto";
                utente.setContoBancario(utente.getContoBancario()+guadagno);
                banca.transazione(message, utente.getNumero());
            }

            JOptionPane.showMessageDialog(frame, "Hai aggiunto " + guadagno + " euro al tuo " + opzioni[scelta]);
        } else {
            JOptionPane.showMessageDialog(frame, "Numero di mesi non valido.");
        }
    }
    
    /**
     * Visualizzazione conto o portafoglio
     * @param tipo : scelta
     */

    private void showStatus(int tipo) {
        if (tipo == 5) {
            JOptionPane.showMessageDialog(frame, "Saldo conto: " + utente.getContoBancario() + " euro");
        } else {
            JOptionPane.showMessageDialog(frame, "Portafoglio: " + utente.getPortafoglio() + " euro");
        }
    }
    
    /**
     * Avvio del programma con il metodo main
     * @param args
     */

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
			try {
				new BankGUI();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
    }
}
